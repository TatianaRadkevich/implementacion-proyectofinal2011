/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.ZLinkers;

import Negocio.Exceptiones.NegocioException;
import Presentacion.JCheckList;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class ZLCheckList<T> extends ZLItem {

    protected JCheckList<T> chkList;

    public ZLCheckList(JCheckList<T> item) {
        this.chkList = item;
        this.chkList.addFocusListener(this.lostFocusEvent);
        
    }

    @Override
    protected void setJComponentValue(Object value) throws Exception {
        chkList.setSelectedItems((List<T>) value);
    }

    @Override
    protected Object getJComponentValue() throws Exception {
       return chkList.getSelectedItems();
    }

    @Override
    protected void setJComponentError(NegocioException ne) {

    }
}
