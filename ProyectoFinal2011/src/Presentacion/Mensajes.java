/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion;

import javax.swing.JOptionPane;

/**
 *
 * @author Gabriela
 */
public class Mensajes
{
    public static void mensajeError(String elemento,String info)
    {
        String mensaje=elemento+":El dato ingresado es incorrecto"+"\n"+info;
        JOptionPane.showMessageDialog(null, mensaje,"Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void mensajeGuardoCorrectamente()
    {
        String mensaje="El elemento se ha registrado satisfactoriamente";
        JOptionPane.showMessageDialog(null, mensaje,"Informacion", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean mensajeConfirmacion(String elemento)
    {
        String mensaje="¿Desea agregar un nuevo "+elemento+"?";
        int salida=JOptionPane.showConfirmDialog(null, mensaje,"Pregunta",JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE);
        if(salida==JOptionPane.YES_OPTION)
            return true;
        return false;
    }

    public static boolean mensajeConfirmacionGenerico(String mensaje)
    {
        int salida=JOptionPane.showConfirmDialog(null, mensaje,"Pregunta",JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE);
        if(salida==JOptionPane.YES_OPTION)
            return true;
        return false;
    }

     public static void mensajeErrorGuardar(String elemento)
    {
        String mensaje="Error: no se puedo guardar  "+elemento;
       JOptionPane.showMessageDialog(null, mensaje,"Error",JOptionPane.ERROR_MESSAGE);
      
    }

      public static void mensajeErrorGenerico(String elemento)
    {
        String mensaje="Error: "+elemento;
       JOptionPane.showMessageDialog(null, mensaje,"Error",JOptionPane.ERROR_MESSAGE);

    }

      /*
       * Guardar: "El xxxx nombrexxx ha sido guardado exitosamente."
       * Moficar: "El xxxx nombrexxx ha sido modificado exitosamente."
       * Eliminar: "El xxxx nombrexxx ha sido eliminado exitosamente."
       * Reactivar: "El xxxx nombrexxx ha sido dado reactivado exitosamente"
       * Cancelar: "¿Esta seguro que desea cancelar la operacion?
       */

      public static void mensajeInformacion(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje,"Informacion",JOptionPane.INFORMATION_MESSAGE);
      }
}
