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
public class GestorModificarEmpleado extends GestorEmpleado{



    @Override
    public Empleado ejecutarOperacion(Empleado empleado) throws ExceptionGestor {
        return EmpleadoBD.modificar(empleado);
    }

    public void modificarProducto(Dialog parent,int legajo) {
        PantallaABMEmpleado pantalla_producto=new PantallaABMEmpleado(parent, true, this);
        pantalla_producto.modificar(EmpleadoBD.traerEmpleado(legajo));
        pantalla_producto.setVisible(true);
    }

    @Override
    public String mensajeResultado(String nombreEmpleado) {
        return "El empleado "+nombreEmpleado+ "\nha sido modificado exitosamente";
    }



}
