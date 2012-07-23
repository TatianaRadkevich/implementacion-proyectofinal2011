/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Compras;

import BaseDeDatos.Compras.MaterialBD;
import BaseDeDatos.Compras.ProveedorBD;
import BaseDeDatos.HibernateUtil;
import BaseDeDatos.Produccion.UnidadMedidaBD;
import Negocio.Exceptiones.ExceptionGestor;
import Negocio.Produccion.UnidadMedida;
import Presentacion.Compras.PantallaMaterialABM;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public abstract class GestorMaterial {
protected PantallaMaterialABM interfaz;
    protected Material material;

    public abstract void iniciarCU();
    public abstract void ejecutarCU(Material p) throws ExceptionGestor;
    public abstract void validar(Material p) throws ExceptionGestor;

    public void finalizarCU()
    {
        interfaz.setVisible(false);
        interfaz.dispose();
    }

    public Material getMaterial() {
        return material;
    }

    public List<Proveedor> getProveedores() {
        return ProveedorBD.listarProveedores();
    }

     public List<UnidadMedida>  getUnidadMedida() {
        return UnidadMedidaBD.listarUnidadMedida();
    }

    public int getUltimoID() {
        return MaterialBD.getUltimoID();
    }


}
