/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import com.toedter.calendar.JCalendar;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

        public static String parseHora(Date fecha) {
        if (fecha == null) {
            return "";
        }
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(fecha);
        return String.format("%s:%s Hs.",c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
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

    public static Date agregarTiempoFecha(Date fecha ,int dia, int mes, int año) {
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

       public static Date agregarTiempoFecha(Date fecha ,int min,int hora,int dia, int mes, int año) {
        if (fecha == null) {
            return null;
        }

        Calendar c = GregorianCalendar.getInstance();
        c.setTime(fecha);
        c.add(Calendar.MINUTE, min);
        c.add(Calendar.HOUR_OF_DAY, hora);
        c.add(Calendar.DATE, dia);
        c.add(Calendar.MONTH, mes);
        c.add(Calendar.YEAR, año);

        return c.getTime();
    }

    public static void habilitarPanel(Container panel,boolean valor)
    {
        panel.setEnabled(valor);
        for(Component componente:panel.getComponents())
            if(componente instanceof Container)
                habilitarPanel((Container) componente,valor);
        
    }

    public static Date getFechaActual() {
        return GregorianCalendar.getInstance().getTime();
    }

    public Calendar getFechaCalendar() {
        return GregorianCalendar.getInstance();
    }

    public static String fechaHoraMinutoSegundoActual() {
    Date fecha = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    return formato.format(fecha);
}
     public static BigDecimal parseBigDecimal(String value) {
        try {
            return new BigDecimal(value);
        } catch (Exception ex) {
            return null;
        }
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

   public static Byte parseByte(String value) {
        try {
            return new Byte(value);
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

    public static void componenteError(Component componente){
        componente.setBackground(new Color(226, 90, 14));
    }

    public static void componenteError( com.toedter.calendar.JDateChooser calendar){
        calendar.getDateEditor().getUiComponent().setBackground(new Color(226, 90, 14));
    }

    public static void componenteCorrecto(Component componente){
        componente.setBackground(Color.white);
    }

    public static void componenteCorrecto( com.toedter.calendar.JDateChooser calendar){
         calendar.getDateEditor().getUiComponent().setBackground(Color.white);
    }
}
