/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Administracion;

import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.Factura;
import Presentacion.Mensajes;
import java.util.Collection;
import java.util.List;

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

    public static List<FormaPago> getFormaPagos() {
        List<FormaPago> l= HibernateUtil.ejecutarConsulta("FROM FormaPago");
        if(l.isEmpty())
            Mensajes.mensajeErrorGenerico("El sistema no tiene cargado formas de pago");
        return l;
    }

}
