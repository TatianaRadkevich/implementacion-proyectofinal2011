
import BaseDeDatos.HibernateUtil;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


       List p=HibernateUtil.ejecutarConsulta("from Producto");
      int var=0;
       //System.out.println(p.codigoMerge());
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
