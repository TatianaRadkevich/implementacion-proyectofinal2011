/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.ZLinkers;

import Negocio.Exceptiones.NegocioException;
import Negocio.Exceptiones.TipoDatoException;
import Presentacion.AutoFill;
import Presentacion.JCheckList;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author Rodrigo
 */
public class ZLAutoFill extends ZLItem<Object,Object , JTextField> {

    protected AutoFill autoFill;
    public ZLAutoFill(AutoFill item) {
        super(item.getJTextField());
        this.autoFill=item;
        item.addSelectionListener(new AutoFill.SelectionListener() {

            public void objectSelected(Object object) {
                try {
                    save();
                } catch (Exception ex) {}
            }
        });
    }   

  

    @Override
    protected void setJComponentValue(Object value) throws Exception {
        this.autoFill.setValue(value);
    }

    @Override
    protected Object getJComponentValue() throws Exception {
        return this.autoFill.getValue();
    }
}
