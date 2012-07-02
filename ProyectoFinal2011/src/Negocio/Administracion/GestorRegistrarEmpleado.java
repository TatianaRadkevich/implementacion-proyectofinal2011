/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Administracion;

import BaseDeDatos.Administracion.EmpleadoBD;
import BaseDeDatos.UbicacionGeografica.DomicilioBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Administracion.PantallaABMEmpleado;
import java.awt.Dialog;

/**
 *
 * @author Ivan
 */
public class GestorRegistrarEmpleado extends GestorEmpleado{

    @Override
    public Empleado ejecutarOperacion(Empleado empleado) throws ExceptionGestor {
        DomicilioBD.guardar(empleado.getDomicilio());
        return EmpleadoBD.guardar(empleado);
    }

    @Override
    public String mensajeResultado(String nombreEmpleado) {
        return "El empleado "+nombreEmpleado+ "\nha sido dado de registrado exitosamente";
    }
}
