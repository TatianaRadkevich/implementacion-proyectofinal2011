/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ToolTipManager;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Gabriela
 */
public class ValidarTexbox {

    static {
        ToolTipManager.sharedInstance().setInitialDelay(250);
        ToolTipManager.sharedInstance().setReshowDelay(200);
    }

    public static void validarInt(final JTextField txt) {
        txt.addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_V && e.getModifiers() == KeyEvent.CTRL_MASK) {
                    e.consume();
                }
            }

            public void keyTyped(java.awt.event.KeyEvent evt) {

                int pos = txt.getCaretPosition();
                String texto = txt.getText();
                texto = texto.substring(0, pos) + evt.getKeyChar() + texto.substring(pos);

                try {

                    Integer.parseInt(texto);
                } catch (Exception ex) {
                    evt.consume();
                }
            }
        });
    }

    public static void desabilitarEdicion(final JTextField txt) {
        txt.addKeyListener(
                new KeyAdapter() {

                    @Override
                    public void keyTyped(KeyEvent e) {
                        e.consume();
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_LEFT
                                || e.getKeyCode() == KeyEvent.VK_RIGHT
                                || e.getKeyCode() == KeyEvent.VK_UP
                                || e.getKeyCode() == KeyEvent.VK_DOWN) {
                            return;
                        }
                        e.consume();
                    }
                });
    }

    public static void validarShort(final JTextField txt) {
        txt.addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_V && e.getModifiers() == KeyEvent.CTRL_MASK) {
                    e.consume();
                }
            }

            public void keyTyped(java.awt.event.KeyEvent evt) {

                int pos = txt.getCaretPosition();
                String texto = txt.getText();
                texto = texto.substring(0, pos) + evt.getKeyChar() + texto.substring(pos);

                try {

                    Short.parseShort(texto);
                } catch (Exception ex) {
                    evt.consume();
                }
            }
        });
    }

    public static void validarLong(final JTextField txt) {
        txt.addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_V && e.getModifiers() == KeyEvent.CTRL_MASK) {
                    e.consume();
                }
            }

            public void keyTyped(java.awt.event.KeyEvent evt) {
                int pos = txt.getCaretPosition();
                String texto = txt.getText();
                texto = texto.substring(0, pos) + evt.getKeyChar() + texto.substring(pos);

                try {

                    Long.parseLong(texto);
                } catch (Exception ex) {
                    evt.consume();
                }
            }
        });
    }

    public static void validarFloat(final JTextField txt) {
        txt.addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_V && e.getModifiers() == KeyEvent.CTRL_MASK) {
                    e.consume();
                }
            }

            public void keyTyped(java.awt.event.KeyEvent evt) {
                int pos = txt.getCaretPosition();
                String texto = txt.getText();
                texto = texto.substring(0, pos) + evt.getKeyChar() + texto.substring(pos);

                if (Character.toLowerCase(evt.getKeyChar()) == 'f') {
                    evt.consume();
                }
                try {

                    Float.parseFloat(texto);
                } catch (Exception ex) {
                    evt.consume();
                }
            }
        });
    }

    public static void validarMoneda(final JTextField txt) {
        txt.addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_V && e.getModifiers() == KeyEvent.CTRL_MASK) {
                    e.consume();
                }
            }

            public void keyTyped(java.awt.event.KeyEvent evt) {

                int pos = txt.getCaretPosition();
                String texto = txt.getText();
                texto = texto.substring(0, pos) + evt.getKeyChar() + texto.substring(pos);

                if (Character.toLowerCase(evt.getKeyChar()) == 'f') {
                    evt.consume();
                }

                try {
                    texto = Float.parseFloat(texto) + "";
                    if (texto.split("\\.")[1].length() >= 3) {
                        evt.consume();
                    }
                } catch (Exception ex) {
                    evt.consume();
                }

            }
        });
    }

    public static void validarLongitud(final JTextComponent txt, final int longitud) {

        txt.addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_V && e.getModifiers() == KeyEvent.CTRL_MASK) {
                    e.consume();
                }
            }

            public void keyTyped(java.awt.event.KeyEvent evt) {

                int pos = txt.getCaretPosition();
                String texto = txt.getText();
                texto = texto.substring(0, pos) + evt.getKeyChar() + texto.substring(pos);

                if (texto.length() > longitud) {
                    evt.consume();
                }
            }
        });
    }

    public static void validarNumero(final JTextComponent txt, final int digitos, final int decimales) {
       validarNumero(txt,digitos,decimales,null,null);
    }

    public static void validarNumero(final JTextComponent txt, final int digitos, final int decimales, final Float maxValue, final Float minValue) {
        //ToolTipManager.sharedInstance().setInitialDelay(1);
        txt.addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyTyped(java.awt.event.KeyEvent evt) {

                txt.setToolTipText("");
                int pos = txt.getCaretPosition();
                String texto = txt.getText();
                texto = texto.substring(0, pos) + evt.getKeyChar() + texto.substring(pos);

                //\d{0,X}(\.\d{0,X})?
                if (texto.matches("^\\d{0," + (digitos - decimales) + "}(\\.\\d{0," + decimales + "})?$") == false) {
                    String mensage = "Solo se permiten numeros con " + (digitos - decimales) + " dígitos como máximo";
                    mensage += (decimales == 0) ? "" : " y " + decimales + " dígitos decimales";
                    txt.setToolTipText(mensage);
                    evt.consume();

                }

                try {
                    float aux = Float.parseFloat(texto);
                    if ((maxValue != null && maxValue < aux) || (minValue != null && aux < minValue)) {
                        evt.consume();
                    }
                } catch (Exception e) {
                    evt.consume();
                }


            }
        });
    }

    public static void campoObligatorio(final JComponent comp) {
        comp.setBorder(new LineBorder(Color.black, 1, true));
        final JLabel lab = new JLabel();
        lab.setFont(new java.awt.Font("Tahoma", Font.BOLD, 10)); // NOI18N
        lab.setText("*");
        lab.setToolTipText("Este campo es obligatorio");
        lab.setSize(lab.getPreferredSize());
        lab.setLocation(comp.getX() + comp.getWidth(), comp.getY());
        comp.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                lab.setLocation(comp.getX() + comp.getWidth(), comp.getY());
            }
        });
        comp.getParent().add(lab);
    }
}
