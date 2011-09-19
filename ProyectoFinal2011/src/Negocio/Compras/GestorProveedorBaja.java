/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Compras;

import BaseDeDatos.Compras.ProveedorBD;
import Negocio.Compras.Proveedor;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Compras.PantallaProveedorABM;
import Presentacion.Utilidades;

/**
 *
 * @author Rodrigo
 */
public class GestorProveedorBaja extends GestorProveedor{

    public GestorProveedorBaja(Proveedor p)
    {
        proveedor=p;
    }

    @Override
    public void iniciarCU() {
        if(proveedor==null)
            throw new RuntimeException("GestorProveedorEliminar: Se debe definir el proveedor a eliminar");

        interfaz=new PantallaProveedorABM(this);
        interfaz.cargar(proveedor);
        interfaz.habilitarBaja(true, true, Utilidades.getFechaActual(), "");
        interfaz.habilitarCarga(false);
        interfaz.setTitle("Eliminar Proveedor");
        interfaz.setVisible(true);
    }

    private void validar(Proveedor p) throws ExceptionGestor
    {
        String mensage="";


        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);
    }

    @Override
    public void ejecutarCU(Proveedor p) throws ExceptionGestor {
        validar(p);
        p.setFecBaja(Utilidades.getFechaActual());
        ProveedorBD.modificar(p);
    }

}
