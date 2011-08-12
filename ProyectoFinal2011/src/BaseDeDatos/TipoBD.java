/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos;

import BaseDeDatos.Produccion.TipoProductoBD;
import Negocio.Tipo;
import Negocio.Ventas.*;
import java.util.ArrayList;

/**
 *
 * @author Rodrigo
 */
public class TipoBD {

        private static String nombre="Tipos";
    private static  ArrayList<Tipo> info=new ArrayList<Tipo>();

    static{
//        info.add(new TipoProducto("Ramal", ""));
//        info.add(new TipoProducto("Codo", " "));
//        info.add(new TipoProducto("Reduccion", " "));
//
//        info.add(new TipoPedido("Materia Prima Propia", " "));
//        info.add(new TipoPedido("Materia Prima Cliente", " "));
//
//        info.add(new TipoCliente("Minorista", " "));
//        info.add(new TipoCliente("Mayorista", " "));
    }

    private static void grabar()
    {
        //BaseDatos.Grabar(nombre, info);
    }

    public static void addTipo(Tipo cliente)
    {
        info.add(cliente);
        grabar();
    }

    public static void eliminar(String nombre)
    {
        info.remove(new Tipo(nombre,""));
        grabar();
    }



    public static ArrayList<TipoProductoBD> getTipoProducto()
    {
        ArrayList<TipoProductoBD> aux=new ArrayList<TipoProductoBD>();
        for(int i=0;i<info.size();i++)
  {
//            if(info.get(i) instanceof TipoProducto)
//            {
//                aux.add((TipoProducto) info.get(i));
//            }
        }
        return aux;
    }

    public static ArrayList<TipoPedido> getTipoPedido() {
        ArrayList<TipoPedido> aux=new ArrayList<TipoPedido>();
        for(int i=0;i<info.size();i++)
  {
//            if(info.get(i) instanceof TipoPedido)
//            {
//                aux.add((TipoPedido) info.get(i));
//            }
        }
        return aux;
    }

  public static ArrayList<TipoCliente> getTipoCliente() {
        ArrayList<TipoCliente> aux=new ArrayList<TipoCliente>();
        for(int i=0;i<info.size();i++)
  {
//            if(info.get(i) instanceof TipoCliente)
//            {
//                aux.add((TipoCliente) info.get(i));
//            }
        }
        return aux;
    }

}
