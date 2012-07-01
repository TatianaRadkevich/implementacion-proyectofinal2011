/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.ZLinkers;

import Negocio.Exceptiones.NegocioException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Rodrigo
 */
public class ZLFormatedTextField extends ZLItem<Object, Object, JFormattedTextField> {

    protected JFormattedTextField txt;

    public ZLFormatedTextField(JFormattedTextField item, Formato f) {
        super(item);
        this.txt = super.jComp;
        this.txt.setFocusLostBehavior(JFormattedTextField.COMMIT);
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
        try {
            txt.commitEdit();
        } catch (ParseException pe) {
            throw new NegocioException("Formato inválido");
        }
        return txt.getValue();
    }

    public enum Formato {

        DateTime(new DateFormatter(new SimpleDateFormat("d/MM/yy - H:mm 'Hs.'"))),
        Date(new DateFormatter(new SimpleDateFormat("d/MM/yy"))),
        CUIT("##-########-#"),
        Default(new DefaultFormatter());
        private AbstractFormatter af;

        private Formato(String mask) {
            try {
                MaskFormatter mf = new MaskFormatter(mask);
                mf.setPlaceholderCharacter('_');
                this.af=mf;
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
}
