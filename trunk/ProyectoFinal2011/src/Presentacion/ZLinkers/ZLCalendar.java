/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.ZLinkers;

import Negocio.Exceptiones.NegocioException;
import Presentacion.JCheckList;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class ZLCalendar extends ZLItem<Object, Date, JDateChooser> {

    public ZLCalendar(JDateChooser item) {
        super(item);
        ((JTextFieldDateEditor) item.getDateEditor()).setEditable(false);
        item.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                try {
                    save();
                } catch (Exception ex) {
                }
            }
        });
    }

    public ZLCalendar setMaxDate(Date d) {
        super.jComp.setMaxSelectableDate(d);
        return this;
    }

    public ZLCalendar setMaxDate(final JDateChooser d) {
        d.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComp.setMaxSelectableDate(d.getDate());
            }
        });
        return this;
    }

    public ZLCalendar setMinDate(Date d) {
        super.jComp.setMinSelectableDate(d);
        return this;
    }

    public ZLCalendar setMinDate(final JDateChooser d) {
        d.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComp.setMinSelectableDate(d.getDate());
            }
        });
        return this;
    }

    @Override
    protected void setJComponentValue(Date value) throws Exception {
        super.jComp.setDate(value);
    }

    @Override
    protected Date getJComponentValue() throws Exception {
        return super.jComp.getDate();
    }
}
