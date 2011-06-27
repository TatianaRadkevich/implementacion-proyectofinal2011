/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigo
 */
public class BaseDatos
{    
    public static void Grabar(String nombre,Object data)
    {
        try {

            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(new File(nombre),false));
            
            salida.writeObject(data);

        } catch (Exception ex) {
            //String info= "Error BaseDatos | Grabar-> Archivo: "+nombre+"\n"+ex.getMessage();
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null,ex);
        }

    }

    public static Object Leer(String nombre)
    {

        try {

            ObjectInputStream entrada=new ObjectInputStream(new FileInputStream(nombre));           
            return entrada.readObject();

        }catch (Exception ex) {
            String info= "Error BaseDatos | Leer-> Archivo: "+nombre+"\n"+ex.toString();
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null,info);
        }       
        return null;
    }


}
