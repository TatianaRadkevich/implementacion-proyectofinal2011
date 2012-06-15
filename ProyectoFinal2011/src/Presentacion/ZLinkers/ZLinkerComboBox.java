/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.ZLinkers;

import Presentacion.ValidarTexbox;
import javax.swing.JComboBox;

/**
 *
 * @author Rodrigo
 */
public class ZLinkerComboBox<T, V> extends ZLinkerItem<T> {

    protected JComboBox cmb;

    public ZLinkerComboBox(Class<T> c, String campo, JComboBox item) {
        super(c, campo);
        this.cmb = item;

        if (nullable == false) {
            ValidarTexbox.campoObligatorio(this.cmb);
        }

        this.atarcomponente(cmb);
    }

    @Override
    protected void setJComponentValue(Object value) throws Exception {
        if (super.atributo.getType().isAssignableFrom(value.getClass()) == false) {
            throw new Exception("Debe asignar un elemento válido");
        }
        cmb.setSelectedItem(value);
    }

    @Override
    protected Object getJComponentValue() throws Exception {
        Object value = cmb.getSelectedItem();

        if(value ==null )
            return null;

        if (super.atributo.getType().isAssignableFrom(value.getClass()) == false) {
            throw new Exception("Debe elegir un elemento válido");
        }
        return value;
    }
}
