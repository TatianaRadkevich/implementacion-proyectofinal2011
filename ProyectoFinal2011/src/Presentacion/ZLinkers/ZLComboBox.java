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
public class ZLComboBox<T> extends ZLItem<Object,T,JComboBox> {    

    public ZLComboBox( JComboBox cmb) {
        super(cmb);
    }

    @Override
    protected void setJComponentValue(T value) throws Exception {
        if (value!=null && this.prop.getTipoValor().isAssignableFrom(value.getClass()) == false) {
            throw new Exception("Debe asignar un elemento válido");
        }
        this.jComp.setSelectedItem(value);
    }

    @Override
    protected T getJComponentValue() throws Exception {
        Object value =  this.jComp.getSelectedItem();

        if(value ==null )
            return null;

        if (this.prop.getTipoValor().isAssignableFrom(value.getClass()) == false) {
            throw new NegocioException("Debe elegir un elemento válido");
        }
        return (T) value;
    }
}
