/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion.Produccion;

import Negocio.Produccion.Producto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ivan
 */
public class ModelerProducto extends AbstractTableModel{
    private List<Producto> filas;

 public ModelerProducto(List<Producto> productos)
    {
        this.filas = productos;
    }

    public int getRowCount() {
        return filas.size();
    }

    public int getColumnCount() {
        return 3;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return filas.get(rowIndex).getInfoColumna(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
         switch (column){
             case 0:
                return "Codigo";
            case 1:
                return "Nombre";
            case 2:
                return "Tipo producto";
            
                 }
        return null;
    }

}
