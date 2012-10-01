/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Exceptiones;

/**
 *
 * @author Rodrigo
 */
public class OperacionCancelada extends NegocioException {

   public OperacionCancelada() {
        super("La operacion ha sido cancelada");
    }

    public OperacionCancelada(String message) {
        super(message);
    }
}
