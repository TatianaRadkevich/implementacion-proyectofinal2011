/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;
import BaseDeDatos.Produccion.ProductoBD;
import BaseDeDatos.Produccion.TipoProductoBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Produccion.PantallaABMProducto;
import java.awt.Dialog;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class GestorRegistrarProducto extends GestorProducto{


    public void nuevoProducto(Dialog parent){
        PantallaABMProducto producto=new PantallaABMProducto(parent, true, this);
        producto.nuevo();
        producto.setVisible(true);
    }

    @Override
    public List<TipoProducto> traerTiposProductos() throws ExceptionGestor {
        return TipoProductoBD.listarTiposProductos();
    }

    @Override
    public Producto ejecutarOperacion(Producto producto) throws ExceptionGestor {
        return ProductoBD.guardar(producto);
    }


}
