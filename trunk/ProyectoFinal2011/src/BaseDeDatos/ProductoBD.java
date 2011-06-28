/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos;

import Negocio.Produccion.Producto;
import Negocio.Produccion.TipoProducto;
import java.util.ArrayList;

/**
 *
 * @author Ivan
 */
public class ProductoBD {
        private static String nombre="Productos";
    private static  ArrayList<Producto> info=new ArrayList<Producto>();
    private static int ultimoNro=0;

    static
    {
        Producto p=new Producto(423, "Algo es mejor es nada", "Super Caño Pvc",6, new TipoProducto("Codo",""));

        info.add(p);
    }

    private static void grabar()
    {
        //BaseDatos.Grabar(nombre, info);
    }

    public static void addProducto(Producto pedido)
    {
        info.add(pedido);
        grabar();
    }


    public static void eliminar(int nro)
    {
        Producto aux=new Producto();
        aux.setCodigo(nro);
        info.remove(aux);
        grabar();
    }

    public static ArrayList<Producto> getProductos()
    {
        return (ArrayList<Producto>) info.clone();
    }
    public static int generarNro()
    {
        ultimoNro++;
        return ultimoNro;
    }

}
