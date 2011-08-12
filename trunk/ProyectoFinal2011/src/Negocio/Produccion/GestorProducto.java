/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;
import Negocio.Exceptiones.ExceptionGestor;
import java.util.List;

/**
 *
 * @author Ivan
 */
public abstract class GestorProducto {

    public abstract List<Producto> traerTiposProductos() throws ExceptionGestor;
    public abstract Producto ejecutarOperacion(Producto producto) throws ExceptionGestor;

}
