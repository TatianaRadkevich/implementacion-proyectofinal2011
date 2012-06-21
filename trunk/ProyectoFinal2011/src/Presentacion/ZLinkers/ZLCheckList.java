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
public class ZLCheckList<T> extends ZLItem<Object,List<T>,JCheckList<T>> {

    public ZLCheckList(JCheckList<T> item) {
       super(item);
        
    }

    @Override
    protected void setJComponentValue(List<T> value) throws Exception {
        super.jComp.setSelectedItems( value);
    }

    @Override
    protected List<T> getJComponentValue() throws Exception {
       return super.jComp.getSelectedItems();
    }
}
