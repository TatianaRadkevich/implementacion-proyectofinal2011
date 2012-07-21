/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Compras;

import Negocio.Compras.Proveedor;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Compras.PantallaProveedorABM;

/**
 *
 * @author Rodrigo
 */
public abstract class GestorProveedor {


    private PantallaProveedorABM interfaz;
    protected Proveedor proveedor;

    public abstract void iniciarCU();
    public abstract void ejecutarCU(Proveedor p) throws ExceptionGestor;

    public void finalizarCU()
    {
        getInterfaz().setVisible(false);
        getInterfaz().dispose();
    }

    public Proveedor getProveedor() {
        return proveedor;
    }


    public abstract String mensajeResultado(String nombreProveedor);

    /**
     * @return the interfaz
     */
    public PantallaProveedorABM getInterfaz() {
        return interfaz;
    }

    /**
     * @param interfaz the interfaz to set
     */
    public void setInterfaz(PantallaProveedorABM interfaz) {
        this.interfaz = interfaz;
    }

    public boolean existeProveedor(String text) { return false;}

    

}
