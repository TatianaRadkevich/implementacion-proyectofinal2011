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
public class ZLDomicilio extends ZLItem<Object,Domicilio,PnlDomicilio>{    

    public ZLDomicilio(PnlDomicilio dom) {
        super(dom);
    }

    @Override
    protected void setJComponentValue(Domicilio value) throws Exception {
        this.jComp.setDomicilio(value);
    }

    @Override
    protected Domicilio getJComponentValue() throws Exception {
        return this.jComp.getDomicilio();
    }
}
