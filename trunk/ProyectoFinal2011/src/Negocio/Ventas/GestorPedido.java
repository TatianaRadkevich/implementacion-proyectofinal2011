/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Ventas;

import Presentacion.Ventas.PantallaRegistrarPedido;

/**
 *
 * @author Rodrigo
 */
public abstract class GestorPedido {

    protected PantallaRegistrarPedido interfaz;

    public abstract void iniciar();
    public abstract void ejecutar(Pedido p) throws Exception;

}
