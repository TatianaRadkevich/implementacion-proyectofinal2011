/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 *
 * @author Rodrigo
 */
public abstract class AutoFill<E> {

    String busq = "";
    private JComboBox combo;
    private JTextField txt;
    private E value;
    private String temp = "";

    public AutoFill(JTextField text) {
        combo = new JComboBox();
        txt = text;

        txt.getParent().add(combo);
        combo.setModel(new DefaultComboBoxModel());
        combo.setSize(text.getWidth(), 0);
        combo.setLocation(text.getX(), text.getY() + text.getHeight());
        combo.setVisible(true);

        combo.setModel(new DefaultComboBoxModel(getData(txt.getText()).toArray()));

        cargarEventos();


    }

    public E getValue() {
        return value;
    }

    private void setValue(E value, boolean txtUpdate) {
        if (this.value == value) {
            return;
        }
        this.value = value;
        if (txtUpdate) {
            txt.setText((value == null) ? "" : value.toString());
        }
        combo.setSelectedItem(value);

        for (SelectionListener<E> s : objListener) {
            s.objectSelected(value);
        }
    }

    public void setValue(E value) {
        setValue(value, true);
    }

    public JTextField getJTextField() {
        return txt;
    }

    private void cargarEventos() {


        final ActionListener cmbAct = new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                setValue((E) combo.getSelectedItem(), true);
            }
        };
        combo.addActionListener(cmbAct);



        final FocusAdapter lostFoc = new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent fe) {
                if (combo.getItemCount() > 0) {
                    combo.showPopup();
                }
            }
        };

        //combo.addFocusListener(lostFoc);
        txt.addFocusListener(lostFoc);

        combo.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                txt.addFocusListener(lostFoc);
                combo.addFocusListener(lostFoc);
            }

            @Override
            public void keyPressed(KeyEvent e) {

                combo.removeActionListener(cmbAct);
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (combo.getSelectedIndex() == 0) {
                        txt.requestFocus();
                        txt.setText(temp);

                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (combo.getSelectedIndex() == (combo.getItemCount() - 1)) {
                        txt.requestFocus();
                        txt.setText(temp);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    setValue((E) combo.getSelectedItem(), true);
                    txt.requestFocus();
                    combo.setVisible(false);
                    combo.setVisible(true);
                }
                combo.addActionListener(cmbAct);
            }
        });


        txt.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                showList();
            }
        });


        txt.addKeyListener(new KeyAdapter() {

            private boolean ban = true;

            @Override
            public void keyPressed(KeyEvent ke) {
                ban = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {

                if (ban == false) {
                    return;
                }
                ban = false;

                if (combo.isPopupVisible() == false) {
                    showList();
                    return;
                } else {
                    showList();
                }

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    int index = combo.getItemCount() - 1;
                    if (combo.getItemCount() > 1) {
                        combo.setSelectedIndex(index);
                        combo.requestFocus();

                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (combo.getItemCount() > 1) {
                        if (combo.getSelectedIndex() == 0) {
                            combo.setSelectedIndex(1);
                        } else {
                            combo.setSelectedIndex(0);
                        }
                        combo.requestFocus();

                    }
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (combo.isPopupVisible()) {
                        combo.hidePopup();
                    }
                }
            }
        });

    }

    public interface SelectionListener<T> {

        public void objectSelected(T object);
    }
    private ArrayList<SelectionListener<E>> objListener = new ArrayList();

    public void addSelectionListener(SelectionListener<E> sol) {
        objListener.add(sol);
    }

    public void removeActionListener(SelectionListener<E> sol) {
        objListener.remove(sol);
    }

    public void showList(String text) {

        if (txt.isEditable() == false || txt.isEnabled() == false) {
            combo.hidePopup();
            return;
        }

        if (temp.equals(text)) {
            if (combo.isVisible() && combo.getItemCount() != 0) {
                combo.showPopup();
            }
            return;
        }
        this.temp = text;
        combo.hidePopup();
        combo.setModel(new DefaultComboBoxModel(getData(text).toArray()));

        if (combo.isVisible() && combo.getItemCount() != 0) {
            combo.showPopup();
            setValue((E) combo.getSelectedItem(), false);
        }

    }

    public void showList() {

        showList(txt.getText());
    }

    protected abstract List<E> getData(String texto);
}
