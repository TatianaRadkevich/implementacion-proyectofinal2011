/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.Produccion.DetallePlanProduccionBD;
import BaseDeDatos.Produccion.EstadoDetallePlanBD;
import BaseDeDatos.Produccion.EstadoOrdenTrabajoBD;
import BaseDeDatos.Produccion.PlanProduccionBD;
import BaseDeDatos.Ventas.EstadoDetallePedidoBD;
import BaseDeDatos.Produccion.EstadoPlanBD;
import BaseDeDatos.Produccion.OrdenTrabajoBD;
import BaseDeDatos.Ventas.EstadoPedidoBD;
import BaseDeDatos.Ventas.PedidoBD;
import Negocio.Ventas.DetallePedido;
import Negocio.Ventas.EstadoDetallePedido;
import Presentacion.Produccion.PantallaABMAvanceProduccion;
import java.util.List;
import javax.swing.JDialog;

/**
 *
 * @author Ivan
 */
public class GestorAvanceProduccion {

    public GestorAvanceProduccion() {
    }


    public void iniciarCU(JDialog dialog){
        PantallaABMAvanceProduccion pantalla=new PantallaABMAvanceProduccion(dialog, true, this);
        pantalla.setVisible(true);
    }

    public void registrarAvance(DetallePlanProduccion detalle, int cantidad_producida, String observacion) {
        
        detalle.getTOrdenesTrabajo().setObservaciones(observacion.toString());
        if(cantidad_producida < detalle.getCantidad())
        {
            detalle.getTOrdenesTrabajo().setTEordenTrabajo(EstadoOrdenTrabajoBD.traerEstadoFinalizadoParcial());
            detalle.setTEdetallePlan(EstadoDetallePlanBD.getEstadoPlanificadoParcial());
        }
        else
        {
            detalle.getTOrdenesTrabajo().setTEordenTrabajo(EstadoOrdenTrabajoBD.traerEstadoFinalizao());
            detalle.setTEdetallePlan(EstadoDetallePlanBD.traerEstadoFinalizado());
            detalle.getTDetallesPedido().setEstadoDetallePedido(EstadoDetallePedidoBD.getEstadoTerminado());
        }

        OrdenTrabajoBD.modificar(detalle.getTOrdenesTrabajo());
        
        detalle.setCantidadProducida(cantidad_producida);
        DetallePlanProduccionBD.modificar(detalle);
        
        List<DetallePlanProduccion> plan = detalle.getPlanProduccion().getDetallePlan();
        List<DetallePedido> pedido = detalle.getPedido().getDetallePedido();
        
        boolean terminado = true;
        
        if(detalle.getPlanProduccion().getDetallePlan().toArray().length > 1)
        {
            for(DetallePlanProduccion det : plan)
            {
                if(!det.getTEdetallePlan().getNombre().equals("Terminado"))
                {
                    terminado = false;
                    break;
                }
            }
        }

        if(terminado)
        {
            detalle.getPlanProduccion().setEstado(EstadoPlanBD.getEstadoTerminado());
            PlanProduccionBD.Modificar(detalle.getPlanProduccion());
        }
        
        terminado = true;
        if(detalle.getPedido().getDetallePedido().toArray().length > 1)
        {
            for(DetallePedido det : pedido)
            {
                if(!det.getEstadoDetallePedido().getNombre().equals("TERMINADO"))
                {
                    terminado = false;
                    break;
                }
            }
        }
        
        if(terminado)
        {
            detalle.getPedido().setEstadoPedido(EstadoPedidoBD.getEstadoAlmacenadoYTerminado());
            PedidoBD.modificar(detalle.getPedido());
        }
       
    }


}
