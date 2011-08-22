/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;


import BaseDeDatos.Produccion.MaquinaHerramientaBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Produccion.PantallaMaquinaHerramientaABM;

/**
 *
 * @author Ivan
 */
public class GestorMaquinaHerramientaModificar extends GestorMaquinaHerramienta
{


    public GestorMaquinaHerramientaModificar(MaquinaHerramientaParticular mh)
    {
        maquinaHerramienta=mh;
    }

    @Override
    public void iniciarCU() {
         if(maquinaHerramienta==null)
            throw new RuntimeException("GestorMaquinaHerramientaModificar: Se debe definir el pedido a modificar");

        interfaz=new PantallaMaquinaHerramientaABM(this);
        interfaz.cargar(maquinaHerramienta);
        interfaz.setVisible(true);
    }

    private void validar(MaquinaHerramientaParticular p) throws ExceptionGestor
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
    public void ejecutarCU(MaquinaHerramientaParticular p) throws ExceptionGestor {
        validar(p);
        MaquinaHerramientaBD.modificar(p);
    }


  


}
