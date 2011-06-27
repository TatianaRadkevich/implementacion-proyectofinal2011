
import BaseDeDatos.BaseDatos;
import Negocio.Ventas.Cliente;
import Negocio.Ventas.Pedido;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rodrigo
 */
public class Test {

    public static void main(String[] arg) throws Exception
    {
//        Pedido p=new Pedido(new Cliente(432432432,"Proa"), null, true, 585, 2, new Date(2010,5,16), null);
//        ArrayList<Pedido> pl=new  ArrayList<Pedido>();
//        pl.add(p);
//
//        BaseDatos.Grabar("Pedidos", pl);

        System.out.println(BaseDatos.Leer("Pedidos"));
        
      

    }




}
