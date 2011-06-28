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
    
private ArrayList<TipoMaquinaOHerramienta> tipoMaquinasYHerramientas;

    public GestorRegistrarMaquinaOHerramienta() {
        this.tipoMaquinasYHerramientas = new ArrayList<TipoMaquinaOHerramienta>();

    }

    public ArrayList<TipoMaquinaOHerramienta> buscarMaquinasYHerramientas()
    {
        TipoMaquinaOHerramienta tipo1 = new TipoMaquinaOHerramienta("Cortadora", true, "corta pvc");
        TipoMaquinaOHerramienta tipo2 = new TipoMaquinaOHerramienta("Amoladoras", true, "moldea los caños");
        TipoMaquinaOHerramienta tipo3 = new TipoMaquinaOHerramienta("Perforadora", true, "perfora los caños");

        tipoMaquinasYHerramientas.add(tipo1);
        tipoMaquinasYHerramientas.add(tipo2);
        
        return tipoMaquinasYHerramientas;
    }


}
