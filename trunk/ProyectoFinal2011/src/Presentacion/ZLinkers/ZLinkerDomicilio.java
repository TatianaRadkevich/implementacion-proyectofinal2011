/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion.ZLinkers;

import Negocio.Exceptiones.NegocioException;
import Negocio.UbicacionGeografica.Domicilio;
import Presentacion.PnlDomicilio;

/**
 *
 * @author Rodrigo
 */
public class ZLinkerDomicilio extends ZLinkerItem{

    private PnlDomicilio dom;

    public ZLinkerDomicilio(PnlDomicilio dom) {       
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

    @Override
    protected void setJComponentError(NegocioException ne) {
       
    }

}
