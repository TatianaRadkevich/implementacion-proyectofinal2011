/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Compras;

import BaseDeDatos.Compras.ProveedorBD;
import Negocio.Compras.Proveedor;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Compras.PantallaProveedorABM;

/**
 *
 * @author Rodrigo
 */
public class GestorProveedorAlta extends GestorProveedor{

     @Override
    public void iniciarCU() {
         //antes
//        interfaz=new PantallaProveedorABM(this);
//        proveedor=new Proveedor();

        proveedor=new Proveedor();
        setInterfaz(new PantallaProveedorABM(this));

        getInterfaz().setTitle("Registrar Proveedor");
        getInterfaz().setVisible(true);
    }

    private void validar(Proveedor p) throws ExceptionGestor
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
    public void ejecutarCU(Proveedor p) throws ExceptionGestor {
        validar(p);
        ProveedorBD.guardar(p);
    }

    @Override
    public String mensajeResultado(String nombreProveedor) {
        return "El proveedor "+nombreProveedor+ "\nha sido dado de registrado exitosamente";
    }


}
