/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.ZLinkers;

import Negocio.Exceptiones.NegocioException;
import Presentacion.AutoFill;
import Presentacion.JCheckList;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.util.Date;
import java.util.List;
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
