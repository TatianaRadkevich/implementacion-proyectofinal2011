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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Rodrigo
 */
public class ZLFormatedTextField extends ZLItem<Object, Object, JFormattedTextField> {

    public enum Formato {

        DateTime(new DateFormatter(new SimpleDateFormat("d/MM/yy - H:mm 'Hs.'"))),
        Date(new DateFormatter(new SimpleDateFormat("d/MM/yy"))),
        CUIT("##-########-#"),
        Default(new DefaultFormatter());
        private AbstractFormatter af;

        private Formato(String mask) {
            try {
                this.af = new MaskFormatter(mask);
            } catch (ParseException ex) {
            }
        }

        private Formato(AbstractFormatter af) {
            this.af = af;
        }

        public AbstractFormatter getAbstractFormatter() {
            return af;
        }

        public AbstractFormatterFactory getAbstractFormatterFactory() {
            return new DefaultFormatterFactory(af);
        }
    };
    
    protected JFormattedTextField txt;

    public ZLFormatedTextField(JFormattedTextField item, Formato f) {
        super(item);
        this.txt = super.jComp;
        this.txt.addPropertyChangeListener("value", new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                try {
                    save();
                } catch (Exception e) {
                }
            }
        });

        this.txt.setFormatterFactory(f.getAbstractFormatterFactory());

    }

    @Override
    protected void setJComponentValue(Object value) throws Exception {

        txt.setValue(value);
    }

    @Override
    protected Object getJComponentValue() throws Exception {
        return txt.getValue();
    }
}
