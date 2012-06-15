/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion.ZLinkers;

import Negocio.UbicacionGeografica.Domicilio;
import Presentacion.PnlDomicilio;

/**
 *
 * @author Rodrigo
 */
public class ZLinkerDomicilio<T> extends ZLinkerItem<T>{

    private PnlDomicilio dom;

    public ZLinkerDomicilio(Class<T> c,String atrib,PnlDomicilio dom) {
        super(c,atrib);
        this.dom=dom;
    }

    @Override
    protected void setJComponentValue(Object value) throws Exception {
        dom.setDomicilio((Domicilio) value);
    }

    @Override
    protected Object getJComponentValue() throws Exception {
        return dom.getDomicilio();
    }

}
