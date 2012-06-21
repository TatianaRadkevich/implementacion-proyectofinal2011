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
public class ZLinkerRadioButon<C,T> extends ZLinkerItem<C,T> {

    private class Detalle<T> {

        JRadioButton rb;
        T value;

        public Detalle(JRadioButton rb, T value) {
            this.rb = rb;
            this.value = value;
        }
    }

    protected ArrayList<Detalle> det;

    public ZLinkerRadioButon() {        
        this.det = new ArrayList<Detalle>();
    }

    public void addRadioButon(JRadioButton item, T valor) {
        det.add(new Detalle(item, valor));

        item.addFocusListener(lostFocusEvent);
        item.addActionListener(actionEvnt);
    }

    @Override
    protected void setJComponentValue(T value) throws Exception {
        for (Detalle d : det) {
            if (d.value.equals(value)) {
                d.rb.setSelected(true);
            }
        }
    }
   @Override
    protected T getJComponentValue() throws Exception {
        for (Detalle<T> d : det) {
            if (d.rb.isSelected()) {
                return d.value;
            }
        }
        return null;
    }

    @Override
    protected void setJComponentError(NegocioException ne) {
       
    }
}
