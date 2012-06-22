/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.ZLinkers;

import Negocio.Exceptiones.NegocioException;
import java.util.ArrayList;
import javax.swing.JRadioButton;

/**
 *
 * @author Rodrigo
 */
public class ZLRadioButon<T> extends ZLItem<Object, T, JRadioButton> {

    protected T value;

    public ZLRadioButon(JRadioButton item, T value) {
        super(item);
        this.value = value;        
    }

    @Override
    protected void setJComponentValue(T value) throws Exception {
        if (value.equals(this.value)) {
            this.jComp.setSelected(true);
        } else if (this.jComp.isSelected()) {
            this.jComp.setSelected(false);
        }
    }

    @Override
    protected T getJComponentValue() throws Exception {
        T salida = (this.jComp.isSelected()) ? this.value : null;
        return salida;
    }
}
