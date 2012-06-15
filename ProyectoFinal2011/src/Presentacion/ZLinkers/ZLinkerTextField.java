/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.ZLinkers;

import Presentacion.ValidarTexbox;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Rodrigo
 */
public class ZLinkerTextField<T> extends ZLinkerItem<T> {

    protected JTextComponent txt;

    public ZLinkerTextField(Class<T> c, String campo, JTextComponent item) {
        super(c, campo);
        this.txt = item;

        if (nullable == false) {
            ValidarTexbox.campoObligatorio(this.txt);
        }

        if (length > 0) {
            ValidarTexbox.validarLongitud(txt, length);
        }

        if (precision > 0) {
            ValidarTexbox.validarNumero(txt, precision, scale);
        }

        this.atarcomponente(txt);

    }

    @Override
    protected void setJComponentValue(Object value) throws Exception {
        String valor = (value == null) ? "" : value.toString();
        if (txt != null) {
            txt.setText(valor);
        }
    }

    @Override
    protected Object getJComponentValue() throws Exception {
        Class c = atributo.getType();
        String value = txt.getText().trim();
        Object salida = null;

        if (String.class.isAssignableFrom(c)) {
            salida = value;
        } else if (value.length() == 0) {
            salida = null;
        } else if (BigInteger.class.isAssignableFrom(c)) {
            salida = new BigInteger(value);
        } else if (Long.class.isAssignableFrom(c) || long.class.isAssignableFrom(c)) {
            salida = new Long(value);
        } else if (Integer.class.isAssignableFrom(c) || int.class.isAssignableFrom(c)) {
            salida = new Integer(value);
        } else if (Short.class.isAssignableFrom(c) || short.class.isAssignableFrom(c)) {
            salida = new Short(value);
        } else if (Byte.class.isAssignableFrom(c) || byte.class.isAssignableFrom(c)) {
            salida = new Byte(value);
        } else if (BigDecimal.class.isAssignableFrom(c)) {
            salida = new BigDecimal(value);
        } else if (Double.class.isAssignableFrom(c) || double.class.isAssignableFrom(c)) {
            salida = new Double(value);
        } else if (Float.class.isAssignableFrom(c) || float.class.isAssignableFrom(c)) {
            salida = new Float(value);
        }

        return salida;
    }
}
