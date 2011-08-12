/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos;

import Negocio.Ventas.Cliente;
import Negocio.Ventas.Pedido;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Rodrigo
 */
public class PedidoBD {

    private static String nombre="Pedidos";
    private static  ArrayList<Pedido> info=new ArrayList<Pedido>();
    private static int ultimoNro=0;

    static
    {
//        Pedido p=new Pedido(new Cliente(432432432,"Proa"), null, true, 585, 2, new Date(2010,5,16), null);
//        info.add(p);
    }

    private static void grabar()
    {
        //BaseDatos.Grabar(nombre, info);
    }

    public static void addPedido(Pedido pedido)
    {
        info.add(pedido);
        grabar();
    }


    public static void eliminar(int nro)
    {
//        info.remove(new Pedido(nro));
        grabar();
    }

    public static ArrayList<Pedido> getPedidos()
    {
        return (ArrayList<Pedido>) info.clone();
    }
    public static int generarNro()
    {
        ultimoNro++;
        return ultimoNro;
    }
}
