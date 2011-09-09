/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Ventas;

import BaseDeDatos.Ventas.ClienteBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Ventas.PantallaClienteABM;

/**
 *
 * @author Rodrigo
 */
public class GestorClienteModificar extends GestorCliente {


    public GestorClienteModificar(Cliente p)
    {
        cliente=p;
    }

    @Override
    public void iniciarCU() {
         if(cliente==null)
            throw new RuntimeException("GestorClienteModificar: Se debe definir el Cliente a modificar");

        interfaz=new PantallaClienteABM(this);
        interfaz.cargar(cliente);
        interfaz.setTitle("Modificar Cliente");
        interfaz.setVisible(true);
    }

    private void validar(Cliente p) throws ExceptionGestor
    {
        String mensage="";

//
//        if(p.getCliente()==null)
//            mensage+="\n no se asigno un cliente al Cliente";
//        if(p.getDetalleCliente().isEmpty())
//            mensage+="\n El detalle debe contener al menos un elemento";

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);

    }

    @Override
    public void ejecutarCU(Cliente p) throws ExceptionGestor {

        validar(p);
        ClienteBD.modificar(p);
    }


}
