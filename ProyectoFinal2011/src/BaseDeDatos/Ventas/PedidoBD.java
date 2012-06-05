/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos.Ventas;

import BaseDeDatos.HibernateUtil;
import Negocio.Ventas.Cliente;
import Negocio.Ventas.Pedido;
import Negocio.Ventas.TipoPedido;
import Presentacion.Utilidades;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class PedidoBD
{

    public static void guardar(Pedido p)
    {
        HibernateUtil.guardarObjeto(p);
    }

    public static void modificar(Pedido p)
    {
        HibernateUtil.guardarObjeto(p);
    }

     public static Pedido getPedido(int id)
    {
         return (Pedido) HibernateUtil.getObjeto(Pedido.class, id);
     }

    public static List<Pedido> getPedidos(
            String RazonSocial,String CUIL,String NroPedido,
            Date desde,Date hasta,boolean vigentes,boolean cancelados){

        if(vigentes==false&&cancelados==false)
            return new ArrayList<Pedido>(0);


        String auxDesde=Utilidades.parseFecha(Utilidades.agregarTiempoFecha(desde, -1, 0, 0));
        String auxHasta=Utilidades.parseFecha(Utilidades.agregarTiempoFecha(hasta, 1, 0, 0));
        String HQL=String.format(
                "FROM Pedido as p "
                + "WHERE LOWER(p.TClientes.razonSocial) like  LOWER('%s%%') "
                + "AND  p.TClientes.cuit  like '%s%%' "
                + "AND p.idPedido like '%s%%' "
                + ((desde==null)?"":"AND p.fecHoraGeneracion >= '%4$s' ")
                + ((hasta==null)?"":"AND p.fecHoraGeneracion <= '%5$s' ")
                ,RazonSocial,CUIL,NroPedido,auxDesde,auxHasta);

        if(vigentes==true&&cancelados==true)
            return HibernateUtil.ejecutarConsulta(HQL);
        
        if(vigentes==true&&cancelados==false)
            HQL+="AND p.fecBaja IS NULL ";
        
        if(vigentes==false&&cancelados==true)
            HQL+="AND p.fecBaja IS NOT NULL ";
             
        return HibernateUtil.ejecutarConsulta(HQL);
    }
    
    public static List<Pedido> getPedidosAlamacenadoYTerminado(Cliente cliente)
    {
        String HQL=String.format(
                "FROM Pedido as p "
                + "WHERE p.TClientes.idCliente = " + cliente.getIdCliente()
                + "AND p.TEpedido.nombre like 'ALMACENADO Y TERMINADO'");
             
        return HibernateUtil.ejecutarConsulta(HQL);
    }

    
    public static List<Pedido> getPedidosPlanificados(
            String RazonSocial,String CUIL,String NroPedido,
            Date desde,Date hasta,boolean vigentes,boolean cancelados){

        if(vigentes==false&&cancelados==false)
            return new ArrayList<Pedido>(0);


        String auxDesde=Utilidades.parseFecha(Utilidades.agregarTiempoFecha(desde, -1, 0, 0));
        String auxHasta=Utilidades.parseFecha(Utilidades.agregarTiempoFecha(hasta, 1, 0, 0));
        String HQL=String.format(
                "FROM Pedido as p "
                + "WHERE LOWER(p.TClientes.razonSocial) like  LOWER('%s%%') "
                + "AND  p.TClientes.cuit  like '%s%%' "
                + "AND p.idPedido like '%s%%' "
                + ((desde==null)?"":"AND p.fecHoraGeneracion >= '%4$s' ")
                + ((hasta==null)?"":"AND p.fecHoraGeneracion <= '%5$s' ")
                ,RazonSocial,CUIL,NroPedido,auxDesde,auxHasta);

        if(vigentes==true&&cancelados==true)
            return HibernateUtil.ejecutarConsulta(HQL);
        
        if(vigentes==true&&cancelados==false)
            HQL+="AND p.fecBaja IS NULL ";
        
        if(vigentes==false&&cancelados==true)
            HQL+="AND p.fecBaja IS NOT NULL ";
             
        
         HQL+=String.format("AND LOWER(p.TEpedido.nombre) like  LOWER('%s%%') ",
                 "Planificado");
       
        
    
        return HibernateUtil.ejecutarConsulta(HQL);
    }
    
        public static List<Pedido> getPedidos(
            String RazonSocial,TipoPedido tp,int prioridad,boolean vigentes,boolean cancelados){

        if(vigentes==false&&cancelados==false)
            return new ArrayList<Pedido>(0);



        String HQL=String.format(
                "FROM Pedido as p "
                + "WHERE LOWER(p.TClientes.razonSocial) like  LOWER('%s%%') "
                + ((0<=prioridad && prioridad<=3)?"AND p.prioridad = "+ prioridad:"")
                + ((tp==null)?"":"AND p.TTpedido.idTpedido = "+ tp.getIdTipoPedido())
                ,RazonSocial);

        if(vigentes==true&&cancelados==true)
            return HibernateUtil.ejecutarConsulta(HQL);

        if(vigentes==true&&cancelados==false)
            HQL+=" AND p.fecBaja IS NULL ";

        if(vigentes==false&&cancelados==true)
            HQL+=" AND p.fecBaja IS NOT NULL ";

        return HibernateUtil.ejecutarConsulta(HQL);
    }

    public static List<Pedido> getPedidosPendientesPago(){
        return HibernateUtil.ejecutarConsulta("FROM Pedido as p WHERE LOWER(p.TEpedido.nombre) like  LOWER('Pendiente de pago')");
    }
 
  
}
