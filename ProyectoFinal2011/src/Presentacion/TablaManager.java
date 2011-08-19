/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodrigo
 */
public abstract class TablaManager <E>
{
    private DefaultTableModel estructura;
    private List<E> contenido;
    private JTable tabla;
    /** Creates new form Tableta */
    public TablaManager(JTable tb) {
        this.tabla=tb;
        contenido=new ArrayList<E>();
        estructura= new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//celdas no van poder ser editadas
            }
        };
        tabla.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        estructura.setColumnIdentifiers(getCabecera());
        tabla.setModel(estructura);
    }

    public abstract Vector ObjetoFila(E elemento);

    public abstract Vector getCabecera();


    public void add(E objeto)
    {
        estructura.addRow(ObjetoFila(objeto));
        contenido.add(objeto);
    }

    public void add(int index,E objeto)
    {
        estructura.insertRow(index,ObjetoFila(objeto));
        contenido.add(index,objeto);
    }

    public void removeSelectedRow()
    {        
        removeRow(tabla.getSelectedRow());
    }

    public void removeRow(int index)
    {     
        estructura.removeRow(index);
        contenido.remove(index);
    }

    public void replaceSelectedRow(E objeto)
    {
        contenido.set(tabla.getSelectedRow(), objeto);
        updateTabla();
    }

    private void updateTabla()
    {        
        estructura.setRowCount(0);
        for(E obj:contenido)
            estructura.addRow(ObjetoFila(obj));      
    }

    public void limpiar()
    {
        contenido.clear();
        updateTabla();
    }

    public void setDatos(List<E> data)
    {
        contenido=data;
        updateTabla();
    }

    public List<E> getDatos()
    {
        return contenido;
    }
}
