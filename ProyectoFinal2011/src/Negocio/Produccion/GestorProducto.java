/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.Produccion.TProductos;
import BaseDeDatos.Produccion.TTproducto;
import Negocio.Exceptiones.ExceptionGestor;
import java.util.List;

/**
 *
 * @author Ivan
 */
public abstract class GestorProducto {

    public abstract List<TTproducto> traerTiposProductos() throws ExceptionGestor;
    public abstract TProductos ejecutarOperacion(TProductos producto) throws ExceptionGestor;

}
