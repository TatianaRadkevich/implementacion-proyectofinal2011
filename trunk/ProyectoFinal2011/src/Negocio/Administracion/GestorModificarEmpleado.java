/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Administracion;

import BaseDeDatos.Administracion.EmpleadoBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Administracion.PantallaABMEmpleado;
import java.awt.Dialog;

/**
 *
 * @author Ivan
 */
public class GestorModificarEmpleado extends GestorEmpleado {

    @Override
    public Empleado ejecutarOperacion(Empleado empleado) throws ExceptionGestor {
        return EmpleadoBD.modificar(empleado);
    }

    @Override
    public String mensajeResultado(String nombreEmpleado) {
        return "El empleado " + nombreEmpleado + "\nha sido modificado exitosamente";
    }
}
