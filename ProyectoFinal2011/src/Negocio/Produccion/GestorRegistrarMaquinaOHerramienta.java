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
        TipoMaquinaOHerramienta tipo1 = new TipoMaquinaOHerramienta("cortadora", true, "corta pvc");
        TipoMaquinaOHerramienta tipo2 = new TipoMaquinaOHerramienta("Moldeadora", true, "moldea los caños");

        tipoMaquinasYHerramientas.add(tipo1);
        tipoMaquinasYHerramientas.add(tipo2);
        
        return tipoMaquinasYHerramientas;
    }


}
