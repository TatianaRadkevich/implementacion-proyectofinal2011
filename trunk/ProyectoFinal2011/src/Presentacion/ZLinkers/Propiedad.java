/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.ZLinkers;

import Negocio.Exceptiones.NegocioException;
import Negocio.Exceptiones.TipoDatoException;
import Presentacion.Utilidades;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigo
 */
public class Propiedad<C> {

    protected Method set;
    protected Method get;
    protected Field atributo;
    protected Class<C> clase;
    protected boolean editable = true;
    protected boolean nulable = true;
    protected int length = -1, precision = -1, scale = 0;//precision = digitos; scale=decimales

    protected Propiedad(Class<C> clase, String propNameRegex, boolean editable) {
        this(clase, propNameRegex);

        this.setEditable(editable);
    }

    protected Propiedad(Class<C> clase, String propNameRegex) {

        this.clase = clase;
        this.atributo = Utilidades.getCampo(clase, "^.*" + propNameRegex + ".*$");
        this.get = Utilidades.getMetodo(clase, "^(get|is).*" + propNameRegex + ".*$");
        this.set = Utilidades.getMetodo(clase, "^set.*" + propNameRegex + ".*$", get.getReturnType());

        if (atributo != null) {
            this.cargarPropiedades(atributo.getDeclaredAnnotations());
        }
        this.cargarPropiedades(get.getDeclaredAnnotations());

        if (set == null) {
            editable = false;
        }

    }

    private void cargarPropiedades(Annotation[] anns) {
        Method m;
        for (Annotation a : anns) {
            try {
                this.nulable = (Boolean) a.getClass().getDeclaredMethod("nulable").invoke(a);
            } catch (Exception e) {
            }

            try {
                this.length = (Integer)  a.getClass().getDeclaredMethod("length").invoke(a);
            } catch (Exception e) {
            }
            try {
                this.precision = (Integer) a.getClass().getDeclaredMethod("precision").invoke(a);
            } catch (Exception e) {
            }
            try {
                this.scale = (Integer)  a.getClass().getDeclaredMethod("scale").invoke(a);
            } catch (Exception e) {
            }
        }

        if(set!=null&&set.getParameterTypes()[0].isPrimitive())//los set's solo tiene un parametro
            nulable=false;
    }

    protected void setValue(C tarjet, Object value) throws TipoDatoException, NegocioException, Exception {
        try {

            if(nulable==false)
                Utilidades.validarNULL(value);

            if (set != null && editable == true) {
                set.invoke(tarjet, value);
            }
        } catch (InvocationTargetException e) {
            Throwable ex = e.getTargetException();
            if (ex instanceof TipoDatoException) {
                throw (TipoDatoException) ex;
            }
            if (ex instanceof NegocioException) {
                throw (NegocioException) ex;
            }
            if (ex instanceof Exception) {
                throw (Exception) ex;
            }
        }
    }

    protected Object getValue(C tarjet) throws Exception {
        return get.invoke(tarjet);
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = (set == null) ? false : editable;
    }

    public boolean isNulable() {
        return nulable;
    }

    public int getPrecision() {
        return precision;
    }

    public int getScale() {
        return scale;
    }

    public Class<?> getTipoValor() {
        return get.getReturnType();
    }
}
