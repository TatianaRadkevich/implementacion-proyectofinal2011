/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.Produccion.ProductoBD;
import Negocio.Exceptiones.ExceptionGestor;
import Negocio.Produccion.GestorProducto;
import Negocio.Produccion.Producto;
import Presentacion.Produccion.PantallaABMProducto;
import java.awt.Dialog;

/**
 *
 * @author Ivan
 */
public class GestorModificarProducto extends GestorProducto{

   

    @Override
    public Producto ejecutarOperacion(Producto producto) throws ExceptionGestor {
        return ProductoBD.modificar(producto);
    }

    void modificarProducto(Dialog parent,String codigo) {
        PantallaABMProducto pantalla_producto=new PantallaABMProducto(parent, true, this);
        pantalla_producto.modificar(ProductoBD.traerProducto(codigo));
        pantalla_producto.setVisible(true);
    }


}