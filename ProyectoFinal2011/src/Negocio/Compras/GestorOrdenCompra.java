/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Compras;

import BaseDeDatos.Compras.MaterialBD;
import BaseDeDatos.Compras.ProveedorBD;
import BaseDeDatos.HibernateUtil;
import BaseDeDatos.Compras.OrdenCompraBD;
import Negocio.Deposito.Faltante;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Compras.PantallaOrdenCompraABM;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public abstract class GestorOrdenCompra {

    protected PantallaOrdenCompraABM interfaz;
    protected OrdenCompra ordenCompra;

    public abstract void iniciarCU();

    public abstract void ejecutarCU(OrdenCompra p) throws ExceptionGestor;

    public void finalizarCU() {
        interfaz.setVisible(false);
        interfaz.dispose();
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public List<Proveedor> getProveedores() {
        return ProveedorBD.listarProveedoresDisponibles();
    }

    public int getCodigo() {
       
        try{
        return (Integer) HibernateUtil.ejecutarConsulta("SELECT   max(idOrdenCompra) FROM OrdenCompra").get(0);
        }catch(Exception e)
        {return 0;}
        
    }

    public List<Faltante> getFaltantes() {
       return HibernateUtil.ejecutarConsulta("FROM Faltante");
    }

    public List<Material> listarMateriales() {
                return HibernateUtil.ejecutarConsulta("FROM Material where esPendiente=False ORDER BY nombre");
    }

    public void eliminarDetalle(DetalleOrdenCompra seletedObject) {



           }


}
