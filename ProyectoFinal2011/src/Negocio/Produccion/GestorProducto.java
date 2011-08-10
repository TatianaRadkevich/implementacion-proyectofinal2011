/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import Negocio.Exceptiones.ExceptionGestor;

/**
 *
 * @author Ivan
 */
public abstract class GestorProducto {

    public abstract void ejectarOperacion(Producto producto) throws ExceptionGestor;

}
