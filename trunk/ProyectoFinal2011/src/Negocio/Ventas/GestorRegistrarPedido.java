/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Ventas;

import Presentacion.Ventas.PantallaRegistrarPedido;

/**
 *
 * @author Ivan
 */
public class GestorRegistrarPedido extends GestorPedido
{

    @Override
    public void iniciar() {
        interfaz=new PantallaRegistrarPedido(null, true);
    }

    @Override
    public void ejecutar(Pedido p) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
