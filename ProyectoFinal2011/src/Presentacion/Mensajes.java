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
    public void mensajeError(String elemento,String info)
    {
        String mensaje=elemento+":El dato ingresado es incorrecto"+"\n"+info;
        JOptionPane.showMessageDialog(null, mensaje,"Error", JOptionPane.ERROR_MESSAGE);
    }

    public boolean mensajeConfirmacion(String elemento)
    {
        String mensaje="¿Desea agregar un nuevo "+elemento+"?";
        int salida=JOptionPane.showConfirmDialog(null, mensaje,"Pregunta",JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE);
        if(salida==JOptionPane.YES_OPTION)
            return true;
        return false;
    }

     public void mensajeErrorGuardar(String elemento)
    {
        String mensaje="Error: no se puedo guardar  "+elemento;
       JOptionPane.showMessageDialog(null, mensaje,"Error",JOptionPane.ERROR_MESSAGE);
      
    }

      public void mensajeErrorGenerico(String elemento)
    {
        String mensaje="Error: "+elemento;
       JOptionPane.showMessageDialog(null, mensaje,"Error",JOptionPane.ERROR_MESSAGE);

    }
}
