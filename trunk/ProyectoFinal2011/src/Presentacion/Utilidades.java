/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Rodrigo
 */
public class Utilidades {

    public static String parseFecha(Date fecha) {
        if (fecha == null) {
            return "";
        }
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(fecha);
        return String.format("%s/%s/%s", c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));
    }

    public static String parseFechaHora(Date fecha) {
        if (fecha == null) {
            return "";
        }
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(fecha);
        return String.format("%s/%s/%s   %s:%s",
                c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR),
                c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
    }

    public static Date agregarTiempoFecha(Date fecha, int dia, int mes, int año) {
        if (fecha == null) {
            return null;
        }

        Calendar c = GregorianCalendar.getInstance();
        c.setTime(fecha);
        c.add(Calendar.DATE, dia);
        c.add(Calendar.MONTH, mes);
        c.add(Calendar.YEAR, año);

        return c.getTime();
    }

    public static Date getFechaActual() {
        return GregorianCalendar.getInstance().getTime();
    }

    public Calendar getFechaCalendar() {
        return GregorianCalendar.getInstance();
    }

    public static Integer parseInteger(String value) {
        try {
            return new Integer(value);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Long parseLong(String value) {
        try {
            return new Long(value);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Short parseShort(String value) {
        try {
            return new Short(value);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String parseString(Object value) {
        try {
            return value.toString();
        } catch (Exception ex) {
            return "";
        }
    }
}
