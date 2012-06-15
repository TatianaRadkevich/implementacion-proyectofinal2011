/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.ZLinkers;

import Presentacion.JCheckList;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class ZLinkerCheckList<T,V> extends ZLinkerItem<T> {

    protected JCheckList<V> chkList;

    public ZLinkerCheckList(Class<T> c, String campo,JCheckList item) {
        super(c,campo);
        this.chkList = item;
        this.atarcomponente(chkList);
    }

    @Override
    protected void setJComponentValue(Object value) throws Exception {
        chkList.setSelectedItems((List<V>) value);
    }

    @Override
    protected Object getJComponentValue() throws Exception {
       return chkList.getSelectedItems();
    }
}
