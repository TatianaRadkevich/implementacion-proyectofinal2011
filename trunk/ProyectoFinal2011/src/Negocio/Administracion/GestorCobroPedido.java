/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Administracion;

import Negocio.Administracion.Factura;

/**
 *
 * @author Rodrigo
 */
public class GestorCobroPedido {

    private Factura factura;

    public GestorCobroPedido (Factura facturaCobrar)
    {
        this.factura=facturaCobrar;
    }

    public Factura getFactura()
    {
        return factura;
    }

}
