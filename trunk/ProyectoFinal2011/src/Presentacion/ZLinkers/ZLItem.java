/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.ZLinkers;

import Negocio.Exceptiones.NegocioException;
import Negocio.Exceptiones.TipoDatoException;
import Presentacion.Utilidades;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComponent;

/**
 *
 * @author Rodrigo
 */
public abstract class ZLItem<C,T, J extends JComponent> {

    protected Propiedad<C, T> prop;
    protected ZLObject<C> zlo;
    protected J jComp;
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

    public ZLItem(J comp) {
        this.jComp = comp;
        this.jComp.addFocusListener(lostFocusEvent);
        try {
            Utilidades.getMetodo(this.jComp.getClass(), "addActionListener", ActionListener.class).invoke(this.jComp, actionEvnt);
        } catch (Exception e) {
        }
    }

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
            if(this.getPropiedad().isEditable()==false)
                return;
            
            setJComponentError(null);
            setValue(getJComponentValue());
        } catch (NegocioException ne) {
            setJComponentError(ne);
            throw ne;
        }
    }

    public Propiedad<C,T> getPropiedad() {
        return prop;
    }

    public void setPropiedad(Propiedad<C,T> prop) {
        this.prop = prop;
    }

    public ZLObject<C> getZLinkerObject() {
        return zlo;
    }

    public void setZLinkerObject(ZLObject<C> zlo) {
        this.zlo = zlo;
    }

    protected void setJComponentError(NegocioException ne) {
        Utilidades.componenteCorrecto(jComp);
        if (ne != null) {
            Utilidades.componenteError(jComp, ne.getMessage());
        }
    }

    protected abstract void setJComponentValue(T value) throws Exception;

    protected abstract T getJComponentValue() throws Exception;
}
