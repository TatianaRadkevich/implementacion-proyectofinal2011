/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.ZLinkers;

import Negocio.Exceptiones.NegocioException;
import Presentacion.Utilidades;
import Presentacion.ValidarTexbox;
import javax.swing.JComboBox;

/**
 *
 * @author Rodrigo
 */
public class ZLinkerComboBox extends ZLinkerItem {

    protected JComboBox combo;

    public ZLinkerComboBox( JComboBox cmb) {
        this.combo = cmb;
        this.combo.addFocusListener(lostFocusEvent);
        this.combo.addActionListener(actionEvnt);
    }

    @Override
    protected void setJComponentValue(Object value) throws Exception {
        if (this.prop.getTipoValor().isAssignableFrom(value.getClass()) == false) {
            throw new Exception("Debe asignar un elemento válido");
        }
        combo.setSelectedItem(value);
    }

    @Override
    protected Object getJComponentValue() throws Exception {
        Object value = combo.getSelectedItem();

        if(value ==null )
            return null;

        if (this.prop.getTipoValor().isAssignableFrom(value.getClass()) == false) {
            throw new Exception("Debe elegir un elemento válido");
        }
        return value;
    }

    @Override
    protected void setJComponentError(NegocioException ne) {
        if(ne==null)
            Utilidades.componenteCorrecto(combo);
        else
            Utilidades.componenteError(combo,ne.getMessage());
    }
}
