/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.ZLinkers;

import Negocio.Exceptiones.NegocioException;
import Presentacion.Utilidades;
import Presentacion.ValidarTexbox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Rodrigo
 */
public class ZLTextField extends ZLItem<Object,Object,JTextComponent> {

    protected JTextComponent txt;

    protected KeyAdapter ka = new KeyAdapter() {

        public void keyTyped(KeyEvent evt) {
            txt.setToolTipText(null);
            int pos = txt.getCaretPosition();
            String texto = txt.getText();
            texto = texto.substring(0, pos) + evt.getKeyChar() + texto.substring(pos);

            //\d{0,X}(\.\d{0,X})?
            if (prop.precision > 0) {
                if (texto.matches("^\\d{0," + (prop.precision - prop.scale) + "}(\\.\\d{0," + prop.scale + "})?$") == false) {
                    String mensage = "Solo se permiten numeros con " + (prop.precision - prop.scale) + " dígitos como máximo";
                    mensage += (prop.scale == 0) ? "" : " y " + prop.scale + " dígitos decimales";
                    txt.setToolTipText(mensage);
                    evt.consume();

                }
            } else if (prop.length > 0) {
                if (texto.length() > prop.length) {
                    String mensage = "Solo se permiten como máximo " + prop.length + " caracter/es";
                    txt.setToolTipText(mensage);
                    evt.consume();
                }
            }
        }
    };

    public ZLTextField(JTextComponent item) {
        super(item);        
        this.txt=super.jComp;
        txt.addKeyListener(ka);
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
        Class c = prop.getTipoValor();
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
