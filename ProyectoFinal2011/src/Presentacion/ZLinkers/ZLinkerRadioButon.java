/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.ZLinkers;

import java.util.ArrayList;
import javax.swing.JRadioButton;

/**
 *
 * @author Rodrigo
 */
public class ZLinkerRadioButon<T, V> extends ZLinkerItem<T> {

    private class Detalle<V> {

        JRadioButton rb;
        V value;

        public Detalle(JRadioButton rb, V value) {
            this.rb = rb;
            this.value = value;
        }
    }
    protected ArrayList<Detalle> det;

    public ZLinkerRadioButon(Class<T> c,String campo) {
        super(c,campo);
        this.det = new ArrayList<Detalle>();
    }

    public void addRadioButon(JRadioButton item, V valor) {
        det.add(new Detalle(item, valor));

        this.atarcomponente(item);

//        item.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    saveAtributo();
//                } catch (Exception ex) {
//                    Logger.getLogger(LinkerRadioButon.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });

    }

    @Override
    protected void setJComponentValue(Object value) throws Exception {
        for (Detalle d : det) {
            if (d.value.equals(value)) {
                d.rb.setSelected(true);
            }
        }
    }

    @Override
    protected Object getJComponentValue() throws Exception {
        for (Detalle d : det) {
            if (d.rb.isSelected()) {
                return d.value;
            }
        }
        return null;
    }
}
