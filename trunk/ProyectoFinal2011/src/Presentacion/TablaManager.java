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
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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

    public void addListenerModificacionTabla(TableModelListener tml)
    {
        tabla.getModel().addTableModelListener(tml);
    }

    public void addListenerModificaionSelecion(ListSelectionListener lst)
    {
        tabla.getSelectionModel().addListSelectionListener(lst);
    }

    public void add(E objeto)
    {
        contenido.add(objeto);
        estructura.addRow(ObjetoFila(objeto));
    }

    public void add(int index,E objeto)
    {
        contenido.add(index,objeto);
        estructura.insertRow(index,ObjetoFila(objeto));        
    }

    public void removeSelectedRow()
    {        
        removeRow(tabla.getSelectedRow());
    }

    public void removeRow(int index)
    {
        contenido.remove(index);
        estructura.removeRow(index);        
    }

    public void replaceRow(int index,E objeto)
    {
        contenido.set(index, objeto);
        Vector fila = ObjetoFila(objeto);
        for(int i=0;i<fila.size();i++)
            estructura.setValueAt(fila.get(i), index, i);
    }

    public void updateTabla()
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

    public E getSeletedObject()
    {
        if(tabla.getSelectedRow()==-1)
            return null;
        return contenido.get(tabla.getSelectedRow());
    }

    public List<E> getDatos()
    {
        return contenido;
    }
}
