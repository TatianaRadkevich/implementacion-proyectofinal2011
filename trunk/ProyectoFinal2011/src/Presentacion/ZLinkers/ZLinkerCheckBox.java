/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.ZLinkers;

import Negocio.Exceptiones.NegocioException;
import javax.swing.JCheckBox;

/**
 *
 * @author Rodrigo
 */
public class ZLinkerCheckBox extends ZLinkerItem {

    protected JCheckBox chekBox;

    public ZLinkerCheckBox(JCheckBox item) {       
        this.chekBox = item;
        this.chekBox.addFocusListener(this.lostFocusEvent);
        this.chekBox.addActionListener(this.actionEvnt);
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

    @Override
    protected void setJComponentError(NegocioException ne) {
    }
}
