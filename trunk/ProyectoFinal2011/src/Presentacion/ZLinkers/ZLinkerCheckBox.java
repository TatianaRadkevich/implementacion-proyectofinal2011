/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.ZLinkers;

import javax.swing.JCheckBox;

/**
 *
 * @author Rodrigo
 */
public class ZLinkerCheckBox<T> extends ZLinkerItem<T> {

    protected JCheckBox chekBox;

    public ZLinkerCheckBox(Class<T> c,String campo, JCheckBox item) {
        super(c,campo);
        this.chekBox = item;
        atarcomponente(chekBox);
    }

    @Override
    protected void setJComponentValue(Object value) throws Exception {
        Boolean valor=(Boolean) value;
        chekBox.setSelected((valor==null)?false:valor);
    }

    @Override
    protected Object getJComponentValue() throws Exception {
        return chekBox.isSelected();
    }
}
