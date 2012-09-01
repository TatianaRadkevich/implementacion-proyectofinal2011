/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;


import BaseDeDatos.Produccion.MaquinaBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Produccion.PantallaMaquinaABM;

/**
 *
 * @author Ivan
 */
public class GestorMaquinaModificar extends GestorMaquina
{


    public GestorMaquinaModificar(MaquinaParticular mh)
    {
        maquinaHerramienta=mh;
    }

    @Override
    public void iniciarCU() {
         if(maquinaHerramienta==null)
            throw new RuntimeException("GestorMaquinaHerramientaModificar: Se debe definir el pedido a modificar");

        interfaz=new PantallaMaquinaABM(this);
        interfaz.cargar(maquinaHerramienta);
        interfaz.setVisible(true);
    }

    private void validar(MaquinaParticular p) throws ExceptionGestor
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
    public void ejecutarCU(MaquinaParticular p) throws ExceptionGestor {
        validar(p);
        MaquinaBD.modificar(p);
    }

    @Override
    public String mensajeResultado(String nombreProducto) {
         return "La máquina " + nombreProducto + "\nha sido modificado exitosamente";
    }


  


}
