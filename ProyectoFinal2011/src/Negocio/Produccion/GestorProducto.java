/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;
import BaseDeDatos.Compras.MaterialBD;
import BaseDeDatos.Produccion.ProductoBD;
import Negocio.Compras.Material;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Produccion.PantallaABMProducto;
import java.util.List;

/**
 *
 * @author Ivan
 */
public abstract class GestorProducto {

    public static List<TipoProducto> traerTiposProductos() throws ExceptionGestor {
        return GestorTipoProducto.listarTiposProductoAlta();
    }

    public List<UnidadMedida> traerUnidadesMedida() throws ExceptionGestor {
        return GestorUnidadMedida.listarUnidadMedida();
    }
    public abstract Producto ejecutarOperacion(Producto producto) throws ExceptionGestor;

    public static List<Producto> listarProductos(){
        return ProductoBD.listarProductos();
    }

    public abstract String mensajeResultado(Producto producto);

    public abstract void reiniciar(PantallaABMProducto aThis) ;

    public static Producto traerProducto(String codigo){
        return ProductoBD.traerProducto(codigo);
    }
    public static List<Material> traerMateriales(boolean esMateriaPrima){
        return MaterialBD.getMateriales(esMateriaPrima);
    }
}
