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
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author Rodrigo
 */
public abstract class AuroFill<E> {

        String busq = "";
        private JComboBox combo;
        private JTextField txt;
        private boolean ban = false;
        private E value;

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
            txt.setText(value.toString());
            combo.setSelectedItem(value);
        }


        public AuroFill(JTextField text) {
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


            final ActionListener fact = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
//                    if (combo.getSelectedItem() != null) {
//                        txt.setText(combo.getSelectedItem().toString());
//                    }
                }
            };

            combo.addActionListener(fact);

            FocusAdapter fa = new FocusAdapter() {

                @Override
                public void focusGained(FocusEvent e) {
                    if (txt.hasFocus()) {
                        ban = false;
                    }

                    if (combo.isPopupVisible() == false) {
                        updateList();
                        combo.showPopup();
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (txt.hasFocus() == false) {
                        ban = true;
                    }

                    if (txt.hasFocus() == false && combo.hasFocus() == false) {
                        if (combo.isPopupVisible()) {
                            combo.hidePopup();
                        }
                    }
                }
            };

            combo.addFocusListener(fa);

            txt.addFocusListener(fa);
            txt.addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    updateList();
                }
            });

            txt.addKeyListener(new KeyAdapter() {

                @Override
                public void keyReleased(KeyEvent e) {
                    updateList();
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        int index = combo.getItemCount() - 1;
                        if (combo.getItemCount() > 1) {

                            combo.removeActionListener(fact);
                            combo.requestFocus();
                            combo.setSelectedIndex(index);
                            combo.addActionListener(fact);

                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        if (combo.getItemCount() > 1) {

                            combo.removeActionListener(fact);
                            combo.requestFocus();
                            combo.setSelectedIndex(1);
                            combo.addActionListener(fact);

                        }
                    }
                }

                @Override
                public void keyTyped(KeyEvent e) {

                    int pos = txt.getCaretPosition();
                    String texto = txt.getText();
                    texto = texto.substring(0, pos) + e.getKeyChar() + texto.substring(pos);
                    List<E> l = getData(texto);
                    if (l.isEmpty()) {
                        e.consume();
                    } else {
                        updateList(l);
                    }
                }
            });

            combo.addKeyListener(new KeyAdapter() {

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        if (combo.getSelectedIndex() == 0) {
                            txt.requestFocus();
                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        if (combo.getSelectedIndex() == combo.getItemCount() - 1) {
                            txt.requestFocus();
                        }
                    } else if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    {
                        setValue((E)combo.getSelectedItem());
                    }
                }
            });

            txt.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (combo.getSelectedItem() != null) {
                        setValue((E)combo.getSelectedItem());

                    }
                }
            });




        }

        public void updateList(List<E> l) {
            if (ban) {
                return;
            }
            combo.hidePopup();
            combo.setModel(new DefaultComboBoxModel(l.toArray()));

            if (combo.getItemCount() != 0) {
                combo.showPopup();
            }

        }

        public void updateList() {

            updateList(getData(txt.getText()));
        }

        protected abstract List<E> getData(String texto);
          
    }
