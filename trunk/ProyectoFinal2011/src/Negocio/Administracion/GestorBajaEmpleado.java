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
public class GestorBajaEmpleado extends GestorEmpleado{

    @Override
    public Empleado ejecutarOperacion(Empleado empleado) throws ExceptionGestor {
        return EmpleadoBD.modificar(empleado);
    }

    public void bajaProducto(Dialog parent,int legajo) {
        PantallaABMEmpleado pantalla_empleado=new PantallaABMEmpleado(parent, true, this);
        pantalla_empleado.baja(EmpleadoBD.traerEmpleado(legajo));
        pantalla_empleado.setVisible(true);
    }

    @Override
    public String mensajeResultado(String nombreEmpleado) {
        return "El empleado "+nombreEmpleado+ "\nha sido dado de baja exitosamente";
    }


}
