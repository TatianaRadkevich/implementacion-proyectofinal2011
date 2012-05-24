/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.HibernateUtil;
import BaseDeDatos.Ventas.PedidoBD;
import BaseDeDatos.Produccion.DetallePlanProduccionBD;
import BaseDeDatos.Produccion.EstadoDetallePlanBD;
import BaseDeDatos.Produccion.EstadoOrdenTrabajoBD;
import BaseDeDatos.Produccion.OrdenTrabajoBD;
import BaseDeDatos.Ventas.EstadoPedidoBD;
import Negocio.Ventas.Pedido;
import Presentacion.Produccion.PantallaABMOrdenTrabajo;
import java.util.List;
import javax.swing.JDialog;

/**
 *
 * @author Ivan
 */
public class GestorOrdenTrabajo {



    public void iniciarCU(JDialog dialog){
        PantallaABMOrdenTrabajo pantalla=new PantallaABMOrdenTrabajo(dialog, true,this);
        pantalla.setVisible(true);

    }

    public void ejecutarOperacion(OrdenTrabajo orden){
        
    }
    
    public OrdenTrabajo buscarOrdenTrabajo (int nro)
    {
        return OrdenTrabajoBD.traerOrdenTrabajo((long)nro);
    }

    public EstadoOrdenTrabajo estadoGenerado() {
        return EstadoOrdenTrabajoBD.traerEstadoGenerado();
    }

    public EstadoDetallePlan estadoDetalleEnEjecucion() {
        return EstadoDetallePlanBD.traerEstadoEnEjecucion();
    }

    public void guardarOrdenTrabajo(OrdenTrabajo tempOrden) {
        OrdenTrabajoBD.guardar(tempOrden);
    }
    
    public void actualizarDetalle(List<DetallePlanProduccion> detalles){
        for(int i=0;i<detalles.size();i++)
        {
            DetallePlanProduccionBD.modificar(detalles.get(i));
        }
    }

    public void modificarEstadoPedido(Pedido pedido) {
       
        pedido.setEstadoPedido(EstadoPedidoBD.getEstadoProduccion());
        PedidoBD.modificar(pedido);
    }

    public void modificarEstadoPlan(PlanProduccion tPlanesProduccion) {
        tPlanesProduccion.setEstado(EstadoPlanProduccion.getEstadoIniciado());
        HibernateUtil.modificarObjeto(tPlanesProduccion);
    }





}
