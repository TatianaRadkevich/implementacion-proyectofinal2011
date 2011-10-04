/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Principal.java
 *
 * Created on 17/08/2011, 17:53:23
 */
package Presentacion;

import Negocio.Produccion.GestorEstructura;
import Presentacion.Administracion.*;
import Presentacion.Compras.PantallaMaterialConsultar;
import Presentacion.Compras.PantallaOrdenCompraConsultar;
import Presentacion.Compras.PantallaProveedorConsultar;
import Presentacion.Produccion.PantallaABMTipoProducto;
import Presentacion.Produccion.PantallaConsultarPedido;
import Presentacion.Produccion.PantallaConsultarProducto;
import Presentacion.Produccion.PantallaMaquinaHerramientaConsultar;
import Presentacion.Ventas.PantallaClienteConsultar;


import Presentacion.Ventas.PantallaPedidoConsultar;
import gui.GUILocal;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Ivan
 */
public class Principal extends javax.swing.JFrame {

    /** Creates new form Principal */
    ImageIcon imagen;

    public Principal() {
        GUILocal.SyntheticaBlueIceLookAndFeel(this);
        this.setContentPane(new Fondo("fondo.png"));
        initComponents();
        pnlAdministracion.setImagen("transparencia.png");
        pnlCompras.setImagen("transparencia.png");
        pnlProduccion.setImagen("transparencia.png");
        pnlVentas.setImagen("transparencia.png");

    }

    private void visible(boolean valor) {
        pnlProduccion.setVisible(valor);
        pnlVentas.setVisible(valor);
        pnlCompras.setVisible(valor);
        pnlAdministracion.setVisible(valor);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();
        btnAdministracion = new javax.swing.JButton();
        btnCompras = new javax.swing.JButton();
        btnProduccion = new javax.swing.JButton();
        btnVenta = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        pnlContenerdor = new javax.swing.JPanel();
        pnlAdministracion = new Presentacion.Fondo();
        txtAdm = new javax.swing.JTextField();
        btnEmpleado = new javax.swing.JButton();
        btnCargo = new javax.swing.JButton();
        btnTipoDocumento = new javax.swing.JButton();
        pnlCompras = new Presentacion.Fondo();
        txtComp = new javax.swing.JTextField();
        btnProveedor = new javax.swing.JButton();
        btnOrdenCompra = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        pnlProduccion = new Presentacion.Fondo();
        txtProd = new javax.swing.JTextField();
        btnProducto = new javax.swing.JButton();
        btnTipoProducto = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        pnlVentas = new Presentacion.Fondo();
        txtVentas = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlMenu.setLayout(new java.awt.GridLayout(1, 0));

        btnAdministracion.setText("Administracion");
        btnAdministracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministracionActionPerformed(evt);
            }
        });
        pnlMenu.add(btnAdministracion);

        btnCompras.setText("Compras");
        btnCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprasActionPerformed(evt);
            }
        });
        pnlMenu.add(btnCompras);

        btnProduccion.setText("Producción");
        btnProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProduccionActionPerformed(evt);
            }
        });
        pnlMenu.add(btnProduccion);

        btnVenta.setText("Venta");
        btnVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentaActionPerformed(evt);
            }
        });
        pnlMenu.add(btnVenta);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        pnlMenu.add(btnSalir);

        pnlContenerdor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        pnlContenerdor.setOpaque(false);
        pnlContenerdor.setLayout(new java.awt.CardLayout());

        txtAdm.setEditable(false);
        txtAdm.setText("Administracion");
        txtAdm.setFocusable(false);

        btnEmpleado.setText("Empleado");
        btnEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpleadoActionPerformed(evt);
            }
        });

        btnCargo.setText("Cargo");
        btnCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargoActionPerformed(evt);
            }
        });

        btnTipoDocumento.setText("Tipo documento");
        btnTipoDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTipoDocumentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAdministracionLayout = new javax.swing.GroupLayout(pnlAdministracion);
        pnlAdministracion.setLayout(pnlAdministracionLayout);
        pnlAdministracionLayout.setHorizontalGroup(
            pnlAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtAdm, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
            .addGroup(pnlAdministracionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(556, Short.MAX_VALUE))
        );
        pnlAdministracionLayout.setVerticalGroup(
            pnlAdministracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAdministracionLayout.createSequentialGroup()
                .addComponent(txtAdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlContenerdor.add(pnlAdministracion, "card2");

        txtComp.setEditable(false);
        txtComp.setText("Compras");
        txtComp.setFocusable(false);

        btnProveedor.setText("Proveedor");
        btnProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedorActionPerformed(evt);
            }
        });

        btnOrdenCompra.setText("Oeden Compra");
        btnOrdenCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenCompraActionPerformed(evt);
            }
        });

        jButton3.setText("Material");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlComprasLayout = new javax.swing.GroupLayout(pnlCompras);
        pnlCompras.setLayout(pnlComprasLayout);
        pnlComprasLayout.setHorizontalGroup(
            pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtComp, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
            .addGroup(pnlComprasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(556, Short.MAX_VALUE))
            .addGroup(pnlComprasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(556, Short.MAX_VALUE))
            .addGroup(pnlComprasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(556, Short.MAX_VALUE))
        );
        pnlComprasLayout.setVerticalGroup(
            pnlComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlComprasLayout.createSequentialGroup()
                .addComponent(txtComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlContenerdor.add(pnlCompras, "card3");

        txtProd.setEditable(false);
        txtProd.setText("Producción");
        txtProd.setFocusable(false);

        btnProducto.setText("Producto");
        btnProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductoActionPerformed(evt);
            }
        });

        btnTipoProducto.setText("Tipo producto");
        btnTipoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTipoProductoActionPerformed(evt);
            }
        });

        jButton5.setText("Maquina");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton1.setText("Estructura de producto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Planificacion");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlProduccionLayout = new javax.swing.GroupLayout(pnlProduccion);
        pnlProduccion.setLayout(pnlProduccionLayout);
        pnlProduccionLayout.setHorizontalGroup(
            pnlProduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtProd, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
            .addGroup(pnlProduccionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTipoProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(556, Short.MAX_VALUE))
            .addGroup(pnlProduccionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(556, Short.MAX_VALUE))
            .addGroup(pnlProduccionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(556, Short.MAX_VALUE))
            .addGroup(pnlProduccionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(556, Short.MAX_VALUE))
        );
        pnlProduccionLayout.setVerticalGroup(
            pnlProduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProduccionLayout.createSequentialGroup()
                .addComponent(txtProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        pnlContenerdor.add(pnlProduccion, "card4");

        txtVentas.setEditable(false);
        txtVentas.setText("Ventas");
        txtVentas.setFocusable(false);

        jButton4.setText("Pedido");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText("Cliente");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlVentasLayout = new javax.swing.GroupLayout(pnlVentas);
        pnlVentas.setLayout(pnlVentasLayout);
        pnlVentasLayout.setHorizontalGroup(
            pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtVentas, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
            .addGroup(pnlVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(556, Short.MAX_VALUE))
            .addGroup(pnlVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(556, Short.MAX_VALUE))
        );
        pnlVentasLayout.setVerticalGroup(
            pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVentasLayout.createSequentialGroup()
                .addComponent(txtVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(296, Short.MAX_VALUE))
        );

        pnlContenerdor.add(pnlVentas, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlContenerdor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
                    .addComponent(pnlMenu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlContenerdor, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProduccionActionPerformed
        // TODO add your handling code here:
        visible(false);
        pnlProduccion.setVisible(true);
    }//GEN-LAST:event_btnProduccionActionPerformed

    private void btnVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentaActionPerformed
        // TODO add your handling code here:
        visible(false);
        pnlVentas.setVisible(true);
    }//GEN-LAST:event_btnVentaActionPerformed

    private void btnComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprasActionPerformed
        // TODO add your handling code here:
        visible(false);
        pnlCompras.setVisible(true);
    }//GEN-LAST:event_btnComprasActionPerformed

    private void btnAdministracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministracionActionPerformed
        // TODO add your handling code here:
        visible(false);
        pnlAdministracion.setVisible(true);
        ;
    }//GEN-LAST:event_btnAdministracionActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        if (Mensajes.mensajeConfirmacionGenerico("¿Realmente desea salir?")) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleadoActionPerformed
        // TODO add your handling code here:
        PantallaConsultarEmpleado empleado = new PantallaConsultarEmpleado(this, true);
        empleado.setVisible(true);
}//GEN-LAST:event_btnEmpleadoActionPerformed

    private void btnCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargoActionPerformed
        // TODO add your handling code here:
        PantallaABMCargo cargo = new PantallaABMCargo(this, true);
        cargo.setVisible(true);
}//GEN-LAST:event_btnCargoActionPerformed

    private void btnProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductoActionPerformed
        // TODO add your handling code here:
        PantallaConsultarProducto producto = new PantallaConsultarProducto(this, true);
        producto.setVisible(true);
}//GEN-LAST:event_btnProductoActionPerformed

    private void btnTipoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTipoProductoActionPerformed
        // TODO add your handling code here:
        PantallaABMTipoProducto tipo = new PantallaABMTipoProducto(this, true);
        tipo.setVisible(true);
}//GEN-LAST:event_btnTipoProductoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        PantallaMaquinaHerramientaConsultar pantalla = new PantallaMaquinaHerramientaConsultar(this, true);
        pantalla.setVisible(true);
}//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new GestorEstructura().iniciarCU();
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        PantallaConsultarPedido pantalla = new PantallaConsultarPedido(this, true);
        pantalla.setVisible(true);
}//GEN-LAST:event_jButton2ActionPerformed

    private void btnProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedorActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                PantallaProveedorConsultar dialog = new PantallaProveedorConsultar(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }//GEN-LAST:event_btnProveedorActionPerformed

    private void btnOrdenCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenCompraActionPerformed
        // TODO add your handling code here:
        new PantallaOrdenCompraConsultar(new javax.swing.JFrame(), true).setVisible(true);

    }//GEN-LAST:event_btnOrdenCompraActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        PantallaMaterialConsultar pantalla = new PantallaMaterialConsultar(this, true);
        pantalla.setVisible(true);
}//GEN-LAST:event_jButton3ActionPerformed

    private void btnTipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTipoDocumentoActionPerformed
        // TODO add your handling code here:
        PantallaABMTipoDocumento documento = new PantallaABMTipoDocumento(this, true);
        documento.setVisible(true);
}//GEN-LAST:event_btnTipoDocumentoActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        new PantallaPedidoConsultar(this, true).setVisible(true);
}//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:

        new PantallaClienteConsultar(this, true).setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdministracion;
    private javax.swing.JButton btnCargo;
    private javax.swing.JButton btnCompras;
    private javax.swing.JButton btnEmpleado;
    private javax.swing.JButton btnOrdenCompra;
    private javax.swing.JButton btnProduccion;
    private javax.swing.JButton btnProducto;
    private javax.swing.JButton btnProveedor;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnTipoDocumento;
    private javax.swing.JButton btnTipoProducto;
    private javax.swing.JButton btnVenta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private Presentacion.Fondo pnlAdministracion;
    private Presentacion.Fondo pnlCompras;
    private javax.swing.JPanel pnlContenerdor;
    private javax.swing.JPanel pnlMenu;
    private Presentacion.Fondo pnlProduccion;
    private Presentacion.Fondo pnlVentas;
    private javax.swing.JTextField txtAdm;
    private javax.swing.JTextField txtComp;
    private javax.swing.JTextField txtProd;
    private javax.swing.JTextField txtVentas;
    // End of variables declaration//GEN-END:variables
}
