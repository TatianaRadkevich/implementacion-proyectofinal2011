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


    protected PantallaProveedorABM interfaz;
    protected Proveedor proveedor;

    public abstract void iniciarCU();
    public abstract void ejecutarCU(Proveedor p) throws ExceptionGestor;

    public void finalizarCU()
    {
        interfaz.setVisible(false);
        interfaz.dispose();
    }

    public Proveedor getProveedor() {
        return proveedor;
    }


}
