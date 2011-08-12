/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;
import BaseDeDatos.Produccion.TipoProductoBD;
import Negocio.Exceptiones.ExceptionGestor;
import java.util.List;

/**
 *
 * @author Ivan
 */
public abstract class GestorProducto {

    public List<TipoProducto> traerTiposProductos() throws ExceptionGestor {
        return TipoProductoBD.listarTiposProductos();
    }

    public abstract Producto ejecutarOperacion(Producto producto) throws ExceptionGestor;

}
