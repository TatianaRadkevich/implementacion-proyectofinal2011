/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Administracion;

import BaseDeDatos.Administracion.EmpleadoBD;
import Negocio.Exceptiones.ExceptionGestor;
import java.util.List;

/**
 *
 * @author Ivan
 */
public abstract class GestorEmpleado {



    public abstract Empleado ejecutarOperacion(Empleado Empleado) throws ExceptionGestor;

    public static List<Empleado> listarProductos(){
        return EmpleadoBD.listarEmpleado();
    }

    public abstract String mensajeResultado(String nombreProducto);

    
}
