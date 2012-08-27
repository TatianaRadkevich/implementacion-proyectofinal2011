/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion.Planificacion.Evento;

import java.util.Date;

/**
 *
 * @author Dicsys
 */
public class Evento {
    private Date tiempo;

    public Evento()
    {
    }

    public Evento(Date tiempo)
    {
        this.tiempo=tiempo;
    }

    public Date getTiempo() {
        return tiempo;
    }

    public void setTiempo(Date tiempo) {
        this.tiempo = tiempo;
    }

}
