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

        setInterfaz(new PantallaProveedorABM(this));
        getInterfaz().cargar(proveedor);
        getInterfaz().habilitarBaja(true, true, Utilidades.getFechaActual(), "");
        getInterfaz().habilitarCarga(false);
        getInterfaz().setTitle("Eliminar Proveedor");
        getInterfaz().setVisible(true);
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

    @Override
    public String mensajeResultado(String nombreProveedor) {
        return "El proveedor "+nombreProveedor+ " ha sido dado de baja exitosamente";
    }

}
