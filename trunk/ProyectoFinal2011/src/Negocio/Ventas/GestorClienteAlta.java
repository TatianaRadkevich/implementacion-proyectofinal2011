/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Ventas;

import BaseDeDatos.BaseDatos;
import BaseDeDatos.Ventas.ClienteBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Mensajes;
import Presentacion.Ventas.PantallaClienteABM;

/**
 *
 * @author Rodrigo
 */
public class GestorClienteAlta extends GestorCliente {

    @Override
    public void iniciarCU() {
        interfaz=new PantallaClienteABM(this);
        cliente=new Cliente();
        interfaz.setTitle("Registrar Cliente");
        interfaz.setVisible(true);
    }

    private void validar(Cliente p) throws ExceptionGestor
    {
        String mensage="";
//        if(p.getCliente()==null)
//            mensage+="\n no se asigno un cliente al pedido";
//        if(p.getDetallePedido().isEmpty())
//            mensage+="\n El detalle debe contener al menos un elemento";

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);

    }

    @Override
    public void ejecutarCU(Cliente p) throws ExceptionGestor {
        validar(p);
        ClienteBD.guardar(p);
        Mensajes.mensajeInformacion("El Cliente \""+p.getRazonSocial()+"\" ha sido guardado exitosamente.");
    }





}
