/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion.Administracion;

import Negocio.Administracion.Empleado;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ivan
 */
public class ModelerEmpleado extends AbstractTableModel{
 private List<Empleado> filas;

 public ModelerEmpleado(List<Empleado> productos)
    {
        this.filas = productos;
    }

    public int getRowCount() {
        return filas.size();
    }

    public int getColumnCount() {
        return 8;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return filas.get(rowIndex).getInfoColumna(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
         switch (column){
             case 0:
                return "Legajo";
            case 1:
                return "Nombre";
            case 2:
                return "Apellido";
            case 3:
                return "Sexo";
             case 4:
                return "Tipo documento";
            case 5:
                return "Num. documento";
            case 6:
                 return "Telefono";
             case 7:
                 return "E-mail";

                 }
        return null;
    }

}
