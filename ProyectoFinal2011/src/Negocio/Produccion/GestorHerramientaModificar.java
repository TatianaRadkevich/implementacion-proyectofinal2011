/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;


import BaseDeDatos.Produccion.HerramientaBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Produccion.PantallaHerramientaABM;

/**
 *
 * @author Ivan
 */
public class GestorHerramientaModificar extends GestorHerramienta
{


    public GestorHerramientaModificar(HerramientaParticular mh)
    {
        HerramientaHerramienta=mh;
    }

    @Override
    public void iniciarCU() {
         if(HerramientaHerramienta==null)
            throw new RuntimeException("GestorHerramientaHerramientaModificar: Se debe definir el pedido a modificar");

        interfaz=new PantallaHerramientaABM(this);
        interfaz.cargar(HerramientaHerramienta);
        interfaz.setVisible(true);
    }

    private void validar(HerramientaParticular p) throws ExceptionGestor
    {
        String mensage="";
        
//
//        if(p.getCliente()==null)
//            mensage+="\n no se asigno un cliente al pedido";
//        if(p.getDetallePedido().isEmpty())
//            mensage+="\n El detalle debe contener al menos un elemento";

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);

    }

    @Override
    public void ejecutarCU(HerramientaParticular p) throws ExceptionGestor {
        validar(p);
        HerramientaBD.modificar(p);
    }


  


}
