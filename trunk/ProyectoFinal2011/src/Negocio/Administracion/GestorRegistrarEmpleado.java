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
public class GestorRegistrarEmpleado extends GestorEmpleado{


    public void nuevoEmpleado(Dialog parent){
        PantallaABMEmpleado producto=new PantallaABMEmpleado(parent, true, this,"Registrar Empleado");
        producto.nuevo();
        producto.setVisible(true);
    }

    @Override
    public Empleado ejecutarOperacion(Empleado empleado) throws ExceptionGestor {
        return EmpleadoBD.guardar(empleado);
    }

    @Override
    public String mensajeResultado(String nombreEmpleado) {
        return "El empleado "+nombreEmpleado+ "\nha sido dado de registrado exitosamente";
    }

   

}
