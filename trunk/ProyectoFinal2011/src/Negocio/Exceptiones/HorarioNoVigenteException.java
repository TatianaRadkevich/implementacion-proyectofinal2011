/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Exceptiones;

/**
 *
 * @author Rodrigo
 */
public class HorarioNoVigenteException extends NegocioException {

   public HorarioNoVigenteException() {
        super("El empleado no ctiene asignado ningun Horario");
    }

    public HorarioNoVigenteException(String message) {
        super(message);
    }
}
