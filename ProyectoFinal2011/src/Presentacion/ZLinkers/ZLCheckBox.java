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
public class ZLCheckBox extends ZLItem<Object,Boolean,JCheckBox> {   

    public ZLCheckBox(JCheckBox item) {
        super(item);
    }

    @Override
    protected void setJComponentValue(Boolean value) throws Exception {
        this.jComp.setSelected((value==null)?false:value);
    }

    @Override
    protected Boolean getJComponentValue() throws Exception {
        return this.jComp.isSelected();
    }
}
