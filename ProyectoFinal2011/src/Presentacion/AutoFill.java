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

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        if (this.value == value) {
            return;
        }
        this.value = value;
        txt.setText((value == null) ? "" : value.toString());
        combo.setSelectedItem(value);

        for (SelectionListener<E> s : objListener) {
            s.objectSelected(value);
        }
    }

    public JTextField getJTextField() {
        return txt;
    }

    public AutoFill(JTextField text) {
        combo = new JComboBox();
        txt = text;

        txt.getParent().add(combo);
        combo.setModel(new DefaultComboBoxModel());
        combo.setSize(text.getWidth(), 0);
        combo.setLocation(text.getX(), text.getY() + text.getHeight());
        combo.setVisible(true);

        cargarEventos();

    }

    private void cargarEventos() {

        FocusAdapter focus = new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                if (combo.getItemCount() != 0) {
                    combo.showPopup();
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                combo.hidePopup();
            }
        };
        combo.addFocusListener(focus);
        txt.addFocusListener(focus);




        combo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                setValue((E) combo.getSelectedItem());
            }
        });


        combo.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (combo.getSelectedIndex() == 0) {
                        txt.requestFocus();
                        txt.setText(temp);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (combo.getSelectedIndex() == combo.getItemCount() - 1) {                        
                        txt.requestFocus();
                        txt.setText(temp);                        
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txt.requestFocus();
                    setValue((E) combo.getSelectedItem());
                    combo.hidePopup();
                }


            }
        });

        txt.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                showList();
            }
        });


        txt.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (combo.isPopupVisible()) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        return;
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        return;
                    } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (combo.isPopupVisible()) {
                            return;
                        }
                    }
                }
                showList();
            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    int index = combo.getItemCount() - 1;
                    if (combo.getItemCount() > 1) {

                        combo.requestFocus();
                        combo.setSelectedIndex(index);
                        combo.showPopup();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (combo.getItemCount() > 1) {

                        combo.requestFocus();
                        combo.setSelectedIndex(1);
                        combo.showPopup();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (combo.isPopupVisible()) {
                        setValue((E) combo.getSelectedItem());
                        combo.hidePopup();
                    }
                }

            }
//
//            @Override
//            public void keyTyped(KeyEvent e) {
//                int pos = txt.getCaretPosition();
//                String texto = txt.getText();
//                texto = texto.substring(0, pos) + e.getKeyChar() + texto.substring(pos);
//                showList(texto);
//            }
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

        if (temp.isEmpty() == false && temp.equals(text)) {
            return;
        }
        this.temp = text;
        combo.hidePopup();
        combo.setModel(new DefaultComboBoxModel(getData(text).toArray()));

        if (combo.getItemCount() != 0) {
            combo.showPopup();
        }

    }

    public void showList() {

        showList(txt.getText());
    }

    protected abstract List<E> getData(String texto);
}
