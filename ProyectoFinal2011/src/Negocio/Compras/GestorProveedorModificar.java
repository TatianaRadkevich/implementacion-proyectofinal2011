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
public class GestorProveedorModificar extends GestorProveedor{

    public GestorProveedorModificar(Proveedor p)
    {
        proveedor=p;
    }

    @Override
    public void iniciarCU() {
         if(proveedor==null)
            throw new RuntimeException("GestorProveedorModificar: Se debe definir el Proveedor a modificar");

        interfaz=new PantallaProveedorABM(this);
        interfaz.cargar(proveedor);
        interfaz.setTitle("Modificar Proveedor");
        interfaz.setVisible(true);
    }

    private void validar(Proveedor p) throws ExceptionGestor
    {
        String mensage="";

//
//        if(p.getProveedor()==null)
//            mensage+="\n no se asigno un proveedor al Proveedor";
//        if(p.getDetalleProveedor().isEmpty())
//            mensage+="\n El detalle debe contener al menos un elemento";

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);

    }

    @Override
    public void ejecutarCU(Proveedor p) throws ExceptionGestor {

        validar(p);
        ProveedorBD.modificar(p);
    }
}
