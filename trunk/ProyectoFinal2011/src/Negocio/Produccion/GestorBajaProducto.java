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
public class GestorBajaProducto extends GestorProducto{

    @Override
    public Producto ejecutarOperacion(Producto producto) throws ExceptionGestor {
        return ProductoBD.modificar(producto);
    }

    public void bajaProducto(Dialog parent,String codigo) {
        PantallaABMProducto pantalla_producto=new PantallaABMProducto(parent, true, this,"Eliminar Producto");
        pantalla_producto.baja(ProductoBD.traerProducto(codigo));
       
    }

    @Override
    public String mensajeResultado(Producto producto) {
        return "El producto "+producto.getNombre()+ "\nha sido dado de baja exitosamente";
    }

    @Override
    public void reiniciar(PantallaABMProducto aThis) {
       aThis.dispose();
    }
}
