/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;


import BaseDeDatos.Produccion.HerramientaBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.PantallaEliminar;
import Presentacion.Produccion.PantallaHerramientaABM;
import Presentacion.Utilidades;


/**
 *
 * @author Ivan
 */
public class GestorHerramientaBaja extends GestorHerramienta
{

    public GestorHerramientaBaja(HerramientaParticular mh)
    {
        HerramientaHerramienta=mh;
    }

    @Override
    public void iniciarCU() {
        if(HerramientaHerramienta==null)
            throw new RuntimeException("GestorHerramientaHerramienteBaja: Se debe definir el pedido a eliminar");
        
        interfaz=new PantallaHerramientaABM(this);
        interfaz.cargar(HerramientaHerramienta);
        interfaz.habilitarTodo(false);
        interfaz.habilitarBaja(true, true, Utilidades.getFechaActual(), "");
        interfaz.setVisible(true);
    }

    private void validar(HerramientaParticular p) throws ExceptionGestor
    {
        String mensage="";

//        String auxEstado=p.getEstadoPedido().getNombre();
//        if(!(auxEstado.equals(PedidoBD.EP_AutorizadoPendiente)||
//                auxEstado.equals(PedidoBD.EP_NoAutorizado)||
//                auxEstado.equals(PedidoBD.EP_Planificado)))
//            mensage+="\nNo es posible cancelar un pedido con el estado de "+auxEstado;

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);
    }

    @Override
    public void ejecutarCU(HerramientaParticular mh) throws ExceptionGestor {
        validar(mh);
        
        //mh.setTEHerramienta(null);
        mh.setFecBaja(Utilidades.getFechaActual());
        HerramientaBD.modificar(mh);
    }

    @Override
    public String mensajeResultado(String nombreProducto) {
        return "La herramienta "+nombreProducto+ " ha sido dado de baja exitosamente";
    }



  


}
