/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion.Planificacion;

import BaseDeDatos.Ventas.EstadoPedidoBD;
import BaseDeDatos.Ventas.PedidoBD;
import Negocio.Produccion.Planificacion.Recursos.Recursos;
import Negocio.Ventas.Pedido;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dicsys
 */
public class Jefe {
    
    public List<Pedido> lp;
    public Recursos recursos;

    public Jefe(Recursos r)
    {
        cargarPedidos();
        recursos=r;
    }


    public Tarea getProximaTarea()
    {
        return null;
    }

    public boolean hayTareas()
    {
        return false;
    }

    public void cargarPedidos()
    {
        lp=PedidoBD.getPedidos(EstadoPedidoBD.getEstadoPlanificado());
        lp.addAll(PedidoBD.getPedidos(EstadoPedidoBD.getEstadoProduccion()));
        
    }

}
