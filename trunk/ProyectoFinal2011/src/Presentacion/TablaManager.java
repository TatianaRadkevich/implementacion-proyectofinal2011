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
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodrigo
 */
public abstract class TablaManager<E> {

    private DefaultTableModel estructura;
    private List<E> contenido;
    private JTable tabla;

    /** Creates new form Tableta */
    public TablaManager(JTable tb) {
        this.tabla = tb;
        contenido = new ArrayList<E>();

        final TablaManager tm = this;

        estructura = new DefaultTableModel() {

            @Override
            public void fireTableCellUpdated(int row, int column) {
                tm.fireTableCellUpdated(row, column);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return tm.isCellEditable(column);//celdas no van poder ser editadas
            }
        };
        tabla.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        estructura.setColumnIdentifiers(getCabecera());
        tabla.setModel(estructura);
    }

    public boolean isCellEditable(int columna) {
        return false;
    }

    public abstract Vector ObjetoFila(E elemento);

    public abstract Vector getCabecera();

    public void addModificationListener(TableModelListener tml) {
        tabla.getModel().addTableModelListener(tml);
    }

    public void addSelectionListener(ListSelectionListener lst) {
        tabla.getSelectionModel().addListSelectionListener(lst);
    }

    public void fireTableCellUpdated(int row, int column) {
    }

    public void add(E objeto) {
        contenido.add(objeto);
        estructura.addRow(ObjetoFila(objeto));
    }

    public void insert(int index, E objeto) {
        contenido.add(index, objeto);
        estructura.insertRow(index, ObjetoFila(objeto));
    }

    public int getSelectedRow() {
        return tabla.getSelectedRow();
    }

    public int getSize() {
        return contenido.size();
    }

    public E removeSelectedRow() {
        return removeRow(tabla.getSelectedRow());
    }

    public E removeRow(int index) {

        estructura.removeRow(index);
        return contenido.remove(index);
    }

    public void replaceRow(int index, E objeto) {
        contenido.set(index, objeto);
        Vector fila = ObjetoFila(objeto);
        for (int i = 0; i < fila.size(); i++) {
            estructura.setValueAt(fila.get(i), index, i);
        }
    }

    public void updateTabla() {
        estructura.setRowCount(0);
        for (E obj : contenido) {
            estructura.addRow(ObjetoFila(obj));
        }
    }

    public void limpiar() {
        contenido.clear();
        updateTabla();
    }

    public void setDatos(List<E> data) {
        contenido = data;
        updateTabla();
    }

    public E getSeletedObject() {
        if (tabla.getSelectedRow() == -1) {
            return null;
        }
        return contenido.get(tabla.getSelectedRow());
    }

    public List<E> getDatos() {
        return new ArrayList<E>(contenido);
    }

    public E getDato(int fila) {
        return contenido.get(fila);
    }

    public void setSelectedRow(int i) {
        tabla.setRowSelectionInterval(i, i);
    }
}
