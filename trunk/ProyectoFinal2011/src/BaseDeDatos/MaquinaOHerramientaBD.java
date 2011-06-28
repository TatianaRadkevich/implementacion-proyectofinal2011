/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BaseDeDatos;

import Negocio.Produccion.MaquinaOHerramienta;
import java.util.ArrayList;

/**
 *
 * @author Heber Parrucci
 */
public class MaquinaOHerramientaBD {

    private static ArrayList<MaquinaOHerramienta> listaMaquinasYHerramientas = new  ArrayList<MaquinaOHerramienta>();
    private static int codigo=0;
    private static MaquinaOHerramienta maquina;

    public MaquinaOHerramientaBD() {
        this.listaMaquinasYHerramientas = new ArrayList<MaquinaOHerramienta>();
        maquina = new MaquinaOHerramienta(1, 123, "Perforadora", "Buena", 12, "ms-po", null, null, null);
        this.listaMaquinasYHerramientas.add(maquina);
    }

    public static  void addMaquinaOHerramienta(MaquinaOHerramienta maqOHerr )
    {
        listaMaquinasYHerramientas.add(maqOHerr);
    }

public static int generarCodigo()
{
     codigo++;
     return codigo;
}

public static ArrayList getMaquinasYHerramientas()
{
    return (ArrayList) listaMaquinasYHerramientas.clone();
}


}
