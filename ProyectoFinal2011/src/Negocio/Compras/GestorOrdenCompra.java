/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Compras;

import BaseDeDatos.Compras.ProveedorBD;
import BaseDeDatos.HibernateUtil;
import Negocio.Deposito.Faltante;
import Presentacion.Compras.PantallaOrdenCompraABM;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class GestorOrdenCompra {

    protected PantallaOrdenCompraABM interfaz;
    protected OrdenCompra ordenCompra;
//
//    public abstract void iniciarCU();
//
//    public abstract void ejecutarCU(OrdenCompra p) throws ExceptionGestor;

    public void finalizarCU() {
        interfaz.setVisible(false);
        interfaz.dispose();
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public List<Proveedor> getProveedores() {
        return ProveedorBD.listarProveedores();
    }

    public Integer getCodigo() {
       
        try{
        Integer salida= (Integer) HibernateUtil.ejecutarConsulta("SELECT   max(idOrdenCompra) FROM OrdenCompra").get(0);
         if(salida==null)
             return new Integer(0);
              else
                  return salida;


        }catch(Exception e){return new Integer(0);}
        
    }

    public List<Faltante> getFaltantes() {
       return HibernateUtil.ejecutarConsulta("FROM Faltante");
    }
}
