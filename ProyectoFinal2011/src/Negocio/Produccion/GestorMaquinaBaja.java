/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;


import BaseDeDatos.Produccion.EstadoMaquinaBD;
import BaseDeDatos.Produccion.MaquinaBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.PantallaEliminar;
import Presentacion.Produccion.PantallaMaquinaABM;
import Presentacion.Utilidades;


/**
 *
 * @author Ivan
 */
public class GestorMaquinaBaja extends GestorMaquina
{

    public GestorMaquinaBaja(MaquinaParticular mh)
    {
        maquinaHerramienta=mh;
    }

    @Override
    public void iniciarCU() {
        if(maquinaHerramienta==null)
            throw new RuntimeException("GestorMaquinaHerramienteBaja: Se debe definir el pedido a eliminar");
        
        interfaz=new PantallaMaquinaABM(this);
        interfaz.cargar(maquinaHerramienta);
        interfaz.habilitarTodo(false);
        interfaz.habilitarBaja(true, true, Utilidades.getFechaActual(), "");
        interfaz.setVisible(true);
    }

    private void validar(MaquinaParticular p) throws ExceptionGestor
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
    public void ejecutarCU(MaquinaParticular mh) throws ExceptionGestor {
        validar(mh);
        
        mh.setTEmaquina(EstadoMaquinaBD.getEstadoBaja());
        mh.setFecBaja(Utilidades.getFechaActual());
        MaquinaBD.modificar(mh);
    }



  


}
