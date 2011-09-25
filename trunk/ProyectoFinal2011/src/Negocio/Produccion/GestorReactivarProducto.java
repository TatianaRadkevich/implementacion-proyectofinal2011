/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.Produccion.ProductoBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Produccion.PantallaABMProducto;
import java.awt.Dialog;

/**
 *
 * @author Ivan
 */
public class GestorReactivarProducto extends GestorProducto{


       @Override
    public Producto ejecutarOperacion(Producto producto) throws ExceptionGestor {
           producto.setFecBaja(null);
           producto.setMotivoBaja(null);
        return ProductoBD.modificar(producto);
    }

    public void reactivarProducto(Dialog parent,String codigo) {
        PantallaABMProducto pantalla_producto=new PantallaABMProducto(parent, true, this,"Modificar Producto");
        pantalla_producto.reactivar(ProductoBD.traerProducto(codigo));
      
    }

    @Override
    public String mensajeResultado(Producto producto) {
        return "El producto "+producto.getNombre()+ "\nha sido reactivado exitosamente";
    }

    @Override
    public void reiniciar(PantallaABMProducto aThis) {
        aThis.dispose();
    }

}
