/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Ventas;
import BaseDeDatos.HibernateUtil;
import Negocio.Ventas.Cliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class ClienteBD {


    public static void guardar(Cliente c)
    {
        HibernateUtil.guardarObjeto(c);
    }

    public static void modificar(Cliente c)
    {
        HibernateUtil.guardarObjeto(c);
    }

    public static List<Cliente> getClientes(String cuit, String rz,
            String nombre, String apellido,boolean vigentes,boolean cancelados){

        if(vigentes==false&&cancelados==false)
            return new ArrayList<Cliente>(0);

        String HQL=String.format(
                "FROM Cliente as c "
                + "WHERE LOWER(c.cuit) like  LOWER('%s%%') "
                + "AND LOWER(c.razonSocial) like  LOWER('%s%%') "
                + "AND LOWER(c.nombreResponsable) like  LOWER('%s%%') "
                + "AND LOWER(c.apellidoResponsable) like  LOWER('%s%%') "
                ,cuit,rz,nombre,apellido);

        if(vigentes==true&&cancelados==true)
            return HibernateUtil.ejecutarConsulta(HQL);

        if(vigentes==true&&cancelados==false)
            HQL+="AND c.fecBaja IS NULL ";

        if(vigentes==false&&cancelados==true)
            HQL+="AND c.fecBaja IS NOT NULL ";

        return HibernateUtil.ejecutarConsulta(HQL);
    }

}
