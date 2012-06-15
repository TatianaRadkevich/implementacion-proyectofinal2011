/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.ZLinkers;

import Negocio.NegocioException;
import Presentacion.Utilidades;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.swing.JComponent;

/**
 *
 * @author Rodrigo
 */
public abstract class ZLinkerItem<T> {

    protected Method set;
    protected Method get;
    protected Field atributo;
    protected ZLinkerObject<T> linkObj;
    protected Class clase;
    protected boolean nullable = true;
    protected int length = -1;// default 255;
    protected int precision = -1;// default 0;
    protected int scale = -1;// default 0;
    private String atribName = "", setgetNames = "";
    private JComponent componente;

    protected ZLinkerItem(Class<T> clase, String atrib) {
        this(clase, atrib, atrib);
    }

    protected ZLinkerItem(Class<T> clase, String atrib, String setget) {
        this.setgetNames = setget;
        this.atribName = atrib;
        this.setClass(clase);
    }

    private void setClass(Class<T> clase) {

        this.clase = clase;

        //^(get|is).*algo.*$
        this.atributo = this.getCampo(clase, "^.*" + atribName + ".*$");
        this.set = this.getMetodo(clase, "^set.*" + setgetNames + ".*$", atributo.getType());
        this.get = this.getMetodo(clase, "^(get|is).*" + setgetNames + ".*$");

        if (get.getDeclaredAnnotations().length != 0) {
            cargarCaracteristicas(get.getDeclaredAnnotations());
        } else if (atributo.getDeclaredAnnotations().length != 0) {
            cargarCaracteristicas(atributo.getDeclaredAnnotations());
        }

    }

    private void cargarCaracteristicas(Annotation[] anns) {

        for (Annotation a : anns) {
            try {
                this.nullable = (Boolean) a.getClass().getMethod("nullable").invoke(a);
            } catch (Exception e) {
            }

            try {
                this.length = (Integer) a.getClass().getMethod("length").invoke(a);
            } catch (Exception e) {
            }

            try {
                this.precision = (Integer) a.getClass().getMethod("precision").invoke(a);
            } catch (Exception e) {
            }

            try {
                this.scale = (Integer) a.getClass().getMethod("scale").invoke(a);
            } catch (Exception e) {
            }
        }
    }

    private Method getMetodo(Class<T> clase, String regex, Class<?>... parameterTypes) {

        Method[] metodos = clase.getDeclaredMethods();

        for (Method met : metodos) {
            String nombre = met.getName();
            //String regex="get.*"+regex.replaceAll("*", ".*")+".*";
            if (nombre.toUpperCase().matches(regex.trim().toUpperCase())&&met.getParameterTypes().length==parameterTypes.length) {
                Class[] param = met.getParameterTypes();
                int conincidencias = 0;
                for (int i = 0; i < param.length; i++) {
                    if (param[i].equals(parameterTypes[i])) {
                        conincidencias++;
                    }
                }
                if (conincidencias == parameterTypes.length) {
                    return met;
                }
            }
        }

        System.err.println("No se encontro ningun metodo en la clase " + clase.getName() + " que cumpla con la siguiente exprecion " + regex);
        return null;
    }

    private Field getCampo(Class<T> clase, String regex) {

        Field[] campos = clase.getDeclaredFields();

        for (Field camp : campos) {
            String nombre = camp.getName();
            //String regex="get.*"+regex.replaceAll("*", ".*")+".*";
            if (nombre.toUpperCase().matches(regex.trim().toUpperCase())) {
                return camp;
            }
        }

        System.err.println("No se encontro ningun campo en la clase " + clase.getName() + " que cumpla con la siguiente exprecion " + regex);
        return null;
    }

    protected void atarcomponente(final JComponent componente) {
        this.componente = componente;

        try {
            Method aux = componente.getClass().getDeclaredMethod("addActionListener", ActionListener.class);
            ActionListener act = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    try {
                        save();
                    } catch (Exception ex) {
                    }
                }
            };
            aux.invoke(componente, act);
        } catch (Exception e) {
        }

        componente.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    save();
                } catch (Exception ex) {
                }
            }
        });
    }

    public void setLinkObj(ZLinkerObject<T> linkObj) {
        this.linkObj = linkObj;
    }

    protected void setValue(Object... params) throws NegocioException, Exception {
        try {
            if (set != null) {
                set.invoke(linkObj.getObjeto(), params);
            }
        } catch (InvocationTargetException e) {
            throw (NegocioException) e.getTargetException();
        }
    }

    protected Object getValue() throws Exception {
        return get.invoke(linkObj.getObjeto());
    }

    public void load() throws Exception {
        setJComponentValue(getValue());
    }

    public void save() throws NegocioException, Exception {
        try {
            Utilidades.componenteCorrecto(componente);
            setValue(getJComponentValue());
        } catch (NegocioException ne) {
            Utilidades.componenteError(componente, ne.getMessage());
            throw ne;
        } catch (Exception ex) {

            String mensaje = Utilidades.extraerMensajeError(ex);
            Utilidades.componenteError(componente, mensaje);
            Utilidades.registrarPilaError(ex);
            throw ex;
        }

    }

    protected abstract void setJComponentValue(Object value) throws Exception;

    protected abstract Object getJComponentValue() throws Exception;
}
