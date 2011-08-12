/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos;

import Negocio.Ventas.Cliente;
import java.util.ArrayList;

/**
 *
 * @author Rodrigo
 */
public class ClientesBD {

    private static String nombre="Clientes";
    private static  ArrayList<Cliente> info=new ArrayList<Cliente>();

    static{
//        info.add(new Cliente(434564, "Promesa"));
//        info.add(new Cliente(024657, "Hernan Herramientas"));
//        info.add(new Cliente(987324, "Carlos Accesorios"));
    }

    private static void grabar()
    {
        //BaseDatos.Grabar(nombre, info);
    }

    public static void addCliente(Cliente cliente)
    {
        info.add(cliente);
        grabar();
    }

    public static void eliminar(String RazonSocial)
    {
//        info.remove(new Cliente(0, RazonSocial));
        grabar();
    }

    public static void eliminar(int CUIT)
    {
//        info.remove(new Cliente(CUIT, ""));
        grabar();
    }

    public static ArrayList<Cliente> getClientes()
    {
        return (ArrayList<Cliente>) info.clone();
    }  



}
