/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Gabriela
 */
public class ValidarTexbox {

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
}
