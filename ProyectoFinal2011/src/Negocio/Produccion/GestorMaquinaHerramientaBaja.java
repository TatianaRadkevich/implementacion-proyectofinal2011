/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;


import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.PantallaEliminar;
import Presentacion.Produccion.PantallaMaquinaHerramientaABM;
import Presentacion.Utilidades;
import BaseDeDatos.Produccion.MaquinaHerramientaBD;


/**
 *
 * @author Ivan
 */
public class GestorMaquinaHerramientaBaja extends GestorMaquinaHerramienta
{

    public GestorMaquinaHerramientaBaja(MaquinaHerramientaParticular mh)
    {
        maquinaHerramienta=mh;
    }

    @Override
    public void iniciarCU() {
        if(maquinaHerramienta==null)
            throw new RuntimeException("GestorMaquinaHerramienteBaja: Se debe definir el pedido a eliminar");
        
        interfaz=new PantallaMaquinaHerramientaABM(this);
        interfaz.cargar(maquinaHerramienta);
        interfaz.habilitarTodo(false);
        interfaz.setVisible(true);
    }

    private void validar(MaquinaHerramientaParticular p) throws ExceptionGestor
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
    public void ejecutarCU(MaquinaHerramientaParticular mh) throws ExceptionGestor {
        validar(mh);
        
        PantallaEliminar pe=new PantallaEliminar();
        pe.setVisible(true);
        if(pe.isOk()==false)
            finalizarCU();

        mh.setMotivoBaja(pe.getMotivo());
        mh.setEstadoMaquina(null);
        mh.setFechaBaja(Utilidades.getFechaActual());
        MaquinaHerramientaBD.modificar(mh);
    }



  


}
