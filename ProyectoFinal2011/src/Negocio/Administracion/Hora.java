/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Administracion;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Rodrigo
 */
public class Hora implements Comparable<Hora> {

    private final int hora;
    private final int minuto;
    private final int segundo;

    public Hora(Date tiempo) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(tiempo);

        hora = gc.get(GregorianCalendar.HOUR_OF_DAY);
        minuto = gc.get(GregorianCalendar.MINUTE);
        segundo = gc.get(GregorianCalendar.SECOND);
    }

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public int getSegundo() {
        return segundo;
    }

    public int getid() {
        return hora * 10000 + minuto * 100 + segundo;
    }

    public int compareTo(Hora o) {
        return this.getid() - o.getid();
    }
}
