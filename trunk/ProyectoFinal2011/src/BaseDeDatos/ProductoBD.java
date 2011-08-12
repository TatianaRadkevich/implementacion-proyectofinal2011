/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos;

import BaseDeDatos.Produccion.TipoProductoBD;
import java.util.ArrayList;

/**
 *
 * @author Ivan
 */
public class ProductoBD {
        private static String nombre="Productos";
    private static  ArrayList<ProductoBD> info=new ArrayList<ProductoBD>();
    private static int ultimoNro=0;

    static
    {
//        Producto p=new Producto(423, "Algo es mejor es nada", "Super Caño Pvc",6, new TipoProducto("Codo",""));

//        info.add(p);
    }

    private static void grabar()
    {
        //BaseDatos.Grabar(nombre, info);
    }

    public static void addProducto(ProductoBD pedido)
    {
//        info.add(pedido);
        grabar();
    }


    public static void eliminar(int nro)
    {
        ProductoBD aux=new ProductoBD();
//        aux.setCodigo(nro);
        info.remove(aux);
        grabar();
    }

    public static ArrayList<ProductoBD> getProductos()
    {
        return (ArrayList<ProductoBD>) info.clone();
    }
    public static int generarNro()
    {
        ultimoNro++;
        return ultimoNro;
    }

}
