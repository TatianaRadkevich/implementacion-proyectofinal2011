/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import java.util.ArrayList;

/**
 *
 * @author Heber Parrucci
 */
public class GestorRegistrarMaquinaOHerramienta {
    
private ArrayList<TipoMaquinaHerramienta> tipoMaquinasYHerramientas;

    public GestorRegistrarMaquinaOHerramienta() {
        this.tipoMaquinasYHerramientas = new ArrayList<TipoMaquinaHerramienta>();

    }

    public ArrayList<TipoMaquinaHerramienta> buscarMaquinasYHerramientas()
    {
//        TipoMaquinaHerramienta tipo1 = new TipoMaquinaHerramienta("Cortadora", true, "corta pvc");
//        TipoMaquinaHerramienta tipo2 = new TipoMaquinaHerramienta("Amoladoras", true, "moldea los caños");
//        TipoMaquinaHerramienta tipo3 = new TipoMaquinaHerramienta("Perforadora", true, "perfora los caños");
//
//        tipoMaquinasYHerramientas.add(tipo1);
//        tipoMaquinasYHerramientas.add(tipo2);
        
        return tipoMaquinasYHerramientas;
    }


}
