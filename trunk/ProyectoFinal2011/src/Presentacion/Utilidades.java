/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

// <editor-fold defaultstate="collapsed" desc="imports">
import Negocio.Exceptiones.NegocioException;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.String;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.ToolTipManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
// </editor-fold>

/**
 *
 * @author Rodrigo
 */
public class Utilidades {

    //// <editor-fold defaultstate="collapsed" desc="Utilidades Fechas">
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
        return String.format("%s:%s Hs.", c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
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

    public static Date agregarTiempoFecha(Date fecha, int min, int hora, int dia, int mes, int año) {
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

    public static void habilitarPanel(Container panel, boolean valor) {
        panel.setEnabled(valor);
        for (Component componente : panel.getComponents()) {
            if (componente instanceof Container) {
                habilitarPanel((Container) componente, valor);
            }
        }

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
    // </editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Utilidades Parsers">

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

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Utilidades Tratamiento Errores">
    public static void componenteError(Component componente) {
        componente.setBackground(new Color(226, 90, 14));
    }

    public static void componenteError(com.toedter.calendar.JDateChooser calendar) {
        calendar.getDateEditor().getUiComponent().setBackground(new Color(226, 90, 14));
    }

    public static void componenteCorrecto(Component componente) {
        componente.setBackground(Color.white);
    }

    public static void componenteCorrecto(com.toedter.calendar.JDateChooser calendar) {
        calendar.getDateEditor().getUiComponent().setBackground(Color.white);
    }
    /////////////////////////////////////////////
    private static HashMap<JComponent, Border> cache = new HashMap(100);

    public static void componenteError(JComponent componente, String sms) {
        if (componente == null) {
            return;
        }

        cache.put(componente, componente.getBorder());

        Border bIn = componente.getBorder();
        Border bOut = BorderFactory.createLineBorder(Color.RED);
        Border bComp = BorderFactory.createCompoundBorder(bOut, bIn);
        componente.setBorder(bComp);

        ToolTipManager.sharedInstance().setInitialDelay(500);
        setToolTipText(componente, sms);

    }

    public static void componenteCorrecto(JComponent componente) {
        if (componente == null) {
            return;
        }
        setToolTipText(componente, null);

        if (cache.containsKey(componente)) {
            componente.setBorder(cache.remove(componente));
        }
    }

    public static void setToolTipText(JComponent componente, String sms) {
        componente.setToolTipText(sms);        
        if (componente instanceof JDateChooser) {
            for (Component c : componente.getComponents()) {
                try {
                    setToolTipText((JComponent) c, sms);
                } catch (Exception e) {
                }
            }
        }
    }

    public static void registrarPilaError(Throwable error) {
        registrarPilaError(error, 5);
    }

    public static void registrarPilaError(Throwable error, int nivel) {

        System.err.println(extraerMensajeError(error));
        StackTraceElement[] st = error.getStackTrace();
        for (int i = 0; i < nivel && i < st.length; i++) {
            System.err.println(st[i]);
        }
    }

    public static String extraerMensajeError(Throwable ex) {
        String mensaje = ex.getMessage();
        if (mensaje == null || mensaje.isEmpty()) {
            if (ex.getCause() != null) {
                mensaje = ex.getCause().getMessage();
            } else {
                mensaje = ex.getLocalizedMessage();
            }
        }
        return mensaje;
    }

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Validaciones">
    public static <T> T validarNULL(T obj) throws NegocioException {
        if (obj == null) {
            throw new NegocioException("Este campo es obligatorio, debe ingresar un valor válido.");
        }

        return obj;
    }

    public static enum RegexType {

        MAIL("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", "mi_mail@mi_cuenta.com"),
        CUIT("\\d{2}-\\d{8}-\\d$", "21-89656598-7 (##-########-#)");
        private String regex;
        private String ejemplo;

        private RegexType(String regex, String ejemplo) {
            this.regex = regex;
            this.ejemplo = ejemplo;
        }
    }

    public static String validarString(String obj, boolean permitNull, int maxLen) throws NegocioException {
        return validarString(obj, permitNull, 0, maxLen, null);
    }

    public static String validarString(String obj, boolean permitNull, int maxLen, RegexType regex) throws NegocioException {
        return validarString(obj, permitNull, 0, maxLen, regex);
    }

    public static String validarString(String obj, boolean permitNull, int minLen, int maxLen, RegexType regex) throws NegocioException {
        obj = (obj == null || obj.trim().isEmpty()) ? null : obj;//si obj es '' le meto Null

        if (permitNull == true && obj == null)//si se permite nulos y es nulo se las toma
        {
            return null;
        }

        validarNULL(obj);
        if (obj.length() < minLen) {
            throw new NegocioException("Eeste campo debe tener como mínimo " + minLen + " caracter/es.");
        } else if (maxLen < obj.length()) {
            throw new NegocioException("Este campo puede tener como máximo " + maxLen + " caracter/es.");
        }

        if (regex != null) {
            if (obj.matches(regex.regex) == false) {
                String mensaje = "Ingrese un valor con un formato válido";
                mensaje += (regex.ejemplo.isEmpty()) ? "." : ", por ejemplo: " + regex.ejemplo + " .";
                throw new NegocioException(mensaje);
            }
        }

        return obj.trim();
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Reflexion">

    public static Object ejecutarMetodo(Object o, String regex, Object... params) throws Exception {
        ArrayList<Class<?>> paramsType = new ArrayList();
        for (Object p : params) {
            paramsType.add(p.getClass());
        }

        return getMetodo(o.getClass(), regex, (Class<?>[]) paramsType.toArray()).invoke(o, params);
    }

    public static Method getMetodo(Class<?> clase, String regex, Class<?>... parameterTypes) {

        Method salida = null;
        regex = regex.trim().toUpperCase();
        ArrayList<Method> candidatos = new ArrayList();
        ArrayList<Class> clases = new ArrayList();

        Class aux = clase;
        clases.add(aux);
        while (aux.getSuperclass() != null) {
            aux = aux.getSuperclass();
            clases.add(aux);
        }

        for (Class cc : clases) {

            for (Method m : cc.getDeclaredMethods()) {
                String nombreMetodo = m.getName().toUpperCase();
                if (nombreMetodo.matches(regex)) {
                    try {
                        candidatos.add(cc.getDeclaredMethod(m.getName(), parameterTypes));
                    } catch (Exception e) {
                    }
                }
            }
        }

        for (Method method : candidatos) {
            if (salida == null || salida.getName().length() > method.getName().length()) {
                salida = method;
            }
        }

        if (salida == null) {
            System.err.println("No se encontro ningun metodo en la clase " + clase.getName() + " que cumpla con la siguiente exprecion " + regex);
        }

        return salida;
    }

    public static Field getCampo(Class<?> clase, String regex) {

        Field salida = null;
        regex = regex.trim().toUpperCase();
        ArrayList<Field> candidatos = new ArrayList();

        for (Field f : clase.getDeclaredFields()) {
            String nombre = f.getName().toUpperCase();
            if (nombre.matches(regex)) {
                candidatos.add(f);
            }
        }

        for (Field f : candidatos) {
            if (salida == null || salida.getName().length() > f.getName().length()) {
                salida = f;
            }
        }

        if (salida == null) {
            System.err.println("No se encontro ningun campo en la clase " + clase.getName() + " que cumpla con la siguiente exprecion " + regex);
        }
        return salida;
    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Otros">
    public static void comboCargar(JComboBox combo, Collection l) {
        String msg = "Cargue un item";
        String nullItem = "<Vacio>";

        if (combo.getSelectedItem() != null && combo.getSelectedItem().equals(nullItem)) {
            combo.removeAllItems();
            combo.setEnabled(true);
            combo.setToolTipText(null);
        }

        l = (l == null) ? new ArrayList() : l;
        Object o = combo.getSelectedItem();
        DefaultComboBoxModel model = new DefaultComboBoxModel(l.toArray());
        model.setSelectedItem((l.contains(o)) ? o : null);
        combo.setModel(model);

        if (l.isEmpty()) {
            combo.addItem(nullItem);
            combo.setSelectedIndex(0);
            combo.setEnabled(false);
            combo.setToolTipText(msg);
        }

    }

    public static ActionListener getComboNullListener() {
        return new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JComboBox)//valido para combos
                {
                    JComboBox cmb = (JComboBox) e.getSource();
                    if (cmb.getSelectedItem() == null) {
                    }
                }
            }
        };
    }

    public static void comboSeleccionarSinEvento(JComboBox combo, Object o) {
        ActionListener[] actionListeners = combo.getActionListeners();
        for (ActionListener al : actionListeners) {
            combo.removeActionListener(al);
        }
        combo.setSelectedItem(o);
        for (ActionListener al : actionListeners) {
            combo.addActionListener(al);
        }
    }

    public static void iniciarVentana(Window ventana) {

        IniciadorDeVentanas.iniciarVentana(ventana, ventana.getWidth(), ventana.getHeight());
    }

    public static void iniciarVentana(Window ventana, int anchoComponente, int altoComponente) {

        IniciadorDeVentanas.iniciarVentana(ventana, anchoComponente, altoComponente);
    }
    // </editor-fold>
}
