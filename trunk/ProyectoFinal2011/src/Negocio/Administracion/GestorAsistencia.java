/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Administracion;

import BaseDeDatos.Administracion.AsistenciaEmpleadoBD;
import Presentacion.Administracion.PantallaAsistenciaEmpleado;
import java.awt.Dialog;
import java.awt.Frame;

/**
 *
 * @author Ivan
 */
public class GestorAsistencia {

    public void registrarAsistencia(Frame parent){

         PantallaAsistenciaEmpleado pantalla_asistencia=new PantallaAsistenciaEmpleado(parent, true, this,"Registar baja Empleado");       
        pantalla_asistencia.setVisible(true);
    }

    public void guardarAsistencia(AsistenciaEmpleado asistencia) {
        AsistenciaEmpleadoBD.guardar(asistencia);
    }
    public void traerEmpleadosVigentesSinIngreso(){

    }


}
