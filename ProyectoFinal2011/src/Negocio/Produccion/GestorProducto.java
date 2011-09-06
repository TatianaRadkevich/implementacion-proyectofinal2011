/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;
import BaseDeDatos.Produccion.ProductoBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Produccion.PantallaABMProducto;
import java.util.List;

/**
 *
 * @author Ivan
 */
public abstract class GestorProducto {

    public List<TipoProducto> traerTiposProductos() throws ExceptionGestor {
        return GestorTipoProducto.listarTiposProductoAlta();
    }

    public abstract Producto ejecutarOperacion(Producto producto) throws ExceptionGestor;

    public static List<Producto> listarProductos(){
        return ProductoBD.listarProductos();
    }

    public abstract String mensajeResultado(String nombreProducto);

    public abstract void reiniciar(PantallaABMProducto aThis) ;
}