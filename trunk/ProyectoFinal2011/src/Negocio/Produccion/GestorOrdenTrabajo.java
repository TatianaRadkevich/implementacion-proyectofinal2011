/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.Produccion.DetallePlanProduccionBD;
import BaseDeDatos.Produccion.EstadoDetallePlanBD;
import BaseDeDatos.Produccion.EstadoOrdenTrabajoBD;
import BaseDeDatos.Produccion.OrdenTrabajoBD;
import Presentacion.Produccion.PantallaABMOrdenTrabajo;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class GestorOrdenTrabajo {



    public void iniciarCU(){
        PantallaABMOrdenTrabajo pantalla=new PantallaABMOrdenTrabajo(null, true,this);
    }

    public void ejecutarOperacion(OrdenTrabajo orden){
        
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
        for(int i=0;0<detalles.size();i++)
        {
            DetallePlanProduccionBD.modificar(detalles.get(i));
        }
    }





}
