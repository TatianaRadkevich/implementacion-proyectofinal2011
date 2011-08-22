/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaRegistrarDetallePedido.java
 *
 * Created on 21/06/2011, 11:33:42
 */
package Presentacion.Ventas;

import BaseDeDatos.ProductoBD;
import BaseDeDatos.TipoBD;
import Negocio.Exceptiones.ExceptionGestor;
import Negocio.Produccion.Producto;
import Negocio.Produccion.TipoProducto;
import Negocio.Ventas.DetallePedido;
import Negocio.Ventas.GestorPedido;
import Negocio.Ventas.GestorPedidoAlta;
import Negocio.Ventas.Pedido;
import Presentacion.ValidarTexbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author Heber Parrucci
 */
public class PantallaPedidoDetalleABM extends javax.swing.JDialog {

    private boolean ok;// si salio por el boton aceptar
    private GestorPedido gestor;
    private DetallePedido detalle;

    /** Creates new form PantallaRegistrarDetallePedido */
    public PantallaPedidoDetalleABM(java.awt.Frame parent, boolean modal, GestorPedido gp) {
        super(parent, modal);
        initComponents();
        gestor = gp;
        ok = false;
        detalle = new DetallePedido();

        ValidarTexbox.validarInt(txtCantidad);
        ValidarTexbox.validarLongitud(txtCantidad, 5);
        cargarCombos();
    }

    public PantallaPedidoDetalleABM(GestorPedido gp) {
        this(null, true, gp);
    }

    public void cargarCombos() {
        cmbTipoProducto.setModel(new DefaultComboBoxModel(gestor.getTipoProductos().toArray()));
        cmbTipoProducto.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                TipoProducto tp = (TipoProducto) cmbTipoProducto.getSelectedItem();
                cmbProducto.setModel(new DefaultComboBoxModel(gestor.getProductos(tp).toArray()));
            }
        });
    }

    public boolean isOk() {
        return ok;
    }

    public DetallePedido getDetalle() {
        return detalle;
    }

    public void setDetalle(DetallePedido dp)
    {
        detalle=dp;
        txtCantidad.setText(dp.getCantidad()+"");
        cmbTipoProducto.setSelectedItem(dp.getProducto().getTTproducto());
        cmbProducto.setSelectedItem(dp.getProducto());

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paneCargar = new javax.swing.JPanel();
        cmbTipoProducto = new javax.swing.JComboBox();
        cmbProducto = new javax.swing.JComboBox();
        txtCantidad = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        paneCargar.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));

        jLabel10.setText("Tipo Producto:");

        jLabel11.setText("Producto:");

        jLabel12.setText("Cantidad:");

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneCargarLayout = new javax.swing.GroupLayout(paneCargar);
        paneCargar.setLayout(paneCargarLayout);
        paneCargarLayout.setHorizontalGroup(
            paneCargarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneCargarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneCargarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(paneCargarLayout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar))
                    .addGroup(paneCargarLayout.createSequentialGroup()
                        .addGroup(paneCargarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(paneCargarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCantidad)
                            .addComponent(cmbProducto, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbTipoProducto, javax.swing.GroupLayout.Alignment.TRAILING, 0, 107, Short.MAX_VALUE))))
                .addGap(271, 271, 271))
        );
        paneCargarLayout.setVerticalGroup(
            paneCargarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneCargarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneCargarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(paneCargarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cmbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(paneCargarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(paneCargarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneCargar, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneCargar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        Producto prod = (Producto) cmbProducto.getSelectedItem();
        
        detalle.setCantidad(Integer.parseInt(txtCantidad.getText()));
        detalle.setProducto(prod);
        detalle.setPrecio(prod.getPrecioUnitario().floatValue());
        ok = true;
        this.setVisible(false);

}//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        ok = false;
        this.setVisible(false);
}//GEN-LAST:event_btnCancelarActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new PantallaPedidoDetalleABM(new GestorPedidoAlta()).setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox cmbProducto;
    private javax.swing.JComboBox cmbTipoProducto;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel paneCargar;
    private javax.swing.JTextField txtCantidad;
    // End of variables declaration//GEN-END:variables
}
