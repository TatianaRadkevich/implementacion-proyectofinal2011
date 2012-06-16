/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Deposito;

import BaseDeDatos.HibernateUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Class EAlmacenamientoProductoTerminadoBD
 *
 *
 * @author Federico Bobbio (federicobobbio@gmail.com)
 * @version 16-jun-2012 
 */
public class EAlmacenamientoProductoTerminadoBD {

     public static List<EAlmacenamientoProductoTerminado> listarAlmacenamientos() throws ExceptionInInitializerError{

        List<EAlmacenamientoProductoTerminado> result=new ArrayList<EAlmacenamientoProductoTerminado>(0);

        try{
            result = HibernateUtil.ejecutarConsulta("from EAlmacenamientoProductoTerminado");

        }catch(Exception  ex) {}

        return result;
    }
}