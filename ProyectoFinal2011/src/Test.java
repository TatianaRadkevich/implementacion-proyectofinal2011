
import BaseDeDatos.BaseDatos;
import BaseDeDatos.HibernateUtil;
import BaseDeDatos.Ventas.TTpedido;
import Negocio.Ventas.Cliente;
import Negocio.Ventas.Pedido;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import org.hibernate.Session;

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

        Session ss=HibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        TTpedido tp=(TTpedido) ss.get(TTpedido.class, (byte)1);
        System.out.println(tp.getNombre());            

    }




    private static void mapear()
    {
               ArrayList<File> afa=new ArrayList<File>(50);
        File f=new File("D:\\Mis documentos\\NetBeansProjects\\Proyecto2011\\ProyectoFinal2011\\src\\BaseDeDatos");
        FileFilter ft=new FileFilter()
        {
            public boolean accept(File pathname)
            {
                if(pathname.getName().endsWith(".hbm.xml"))
                    return true;
                return false;
            }
        };


        for(int i=0;i< f.listFiles().length;i++)
        {
            File aux=f.listFiles()[i];
            if(aux.isDirectory())
            {
                afa.addAll(Arrays.asList(aux.listFiles(ft)));
            }
        }


        for(int i=0;i<afa.size();i++)
        {
            System.out.println(
                        String.format(
                        "<mapping resource=\"BaseDeDatos/%s/%s\"/>",
                        afa.get(i).getParentFile().getName(),
                        afa.get(i).getName()
                        )
                    );
        }
    }


}
