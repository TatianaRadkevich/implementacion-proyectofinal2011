/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.ZLinkers;

import Negocio.Exceptiones.NegocioException;
import Negocio.Exceptiones.TipoDatoException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 *
 * @author Rodrigo
 */
public abstract class ZLinkerItem<C, T> {

    protected Propiedad<C> prop;
    protected ZLinkerObject<C> zlo;
    protected FocusAdapter lostFocusEvent = new FocusAdapter() {

        @Override
        public void focusLost(FocusEvent e) {
            try {
                save();
            } catch (Exception ex) {
            }
        }
    };
    protected ActionListener actionEvnt = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            try {
                save();
            } catch (Exception ex) {
            }
        }
    };

    protected void setValue(T value) throws TipoDatoException, NegocioException, Exception {
        prop.setValue(zlo.getObjeto(), value);
    }

    protected T getValue() throws Exception {
        return (T) prop.getValue(zlo.getObjeto());
    }

    public void load() throws Exception {
        setJComponentValue(getValue());
    }

    public void save() throws TipoDatoException, NegocioException, Exception {
        try {
            setJComponentError(null);
            setValue(getJComponentValue());
        } catch (NegocioException ne) {
            setJComponentError(ne);
            throw ne;
        }
    }

    public Propiedad getProp() {
        return prop;
    }

    public void setProp(Propiedad prop) {
        this.prop = prop;
    }

    public ZLinkerObject getZLinkerObject() {
        return zlo;
    }

    public void setZLinkerObject(ZLinkerObject zlo) {
        this.zlo = zlo;
    }

    protected abstract void setJComponentError(NegocioException ne);

    protected abstract void setJComponentValue(T value) throws Exception;

    protected abstract T getJComponentValue() throws Exception;
}
