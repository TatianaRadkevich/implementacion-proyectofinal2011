/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaABMProducto.java
 *
 * Created on 07/06/2011, 09:00:49
 */
package Presentacion.Produccion;

import BaseDeDatos.Compras.MaterialBD;
import Negocio.Compras.Material;
import Negocio.Exceptiones.ExceptionGestor;
import Negocio.Produccion.DetalleProducto;
import Negocio.Produccion.GestorEstructura;
import Negocio.Produccion.GestorProducto;
import Negocio.Produccion.GestorTipoProducto;
import Negocio.Produccion.GestorUnidadMedida;
import Negocio.Produccion.Producto;
import Negocio.Produccion.TipoProducto;
import Negocio.Produccion.UnidadMedida;
import Negocio.Ventas.DetallePedido;
import Presentacion.IniciadorDeVentanas;
import Presentacion.Mensajes;
import Presentacion.TablaManager;
import gui.GUILocal;
import java.awt.Dialog;
import java.awt.Frame;
import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Ivan
 */
public class PantallaABMProducto extends javax.swing.JDialog {

    private GestorProducto gestor;
    private Producto producto = null;

    /** Creates new form PantallaABMProducto */
    public PantallaABMProducto(java.awt.Frame parent, boolean modal) {
        super((Frame) null, modal);
        //GUILocal.establecerGUILocal(this);
        //  GUILocal.establecerGUISyntheticaClassy(this);

        initComponents();
        IniciadorDeVentanas.iniciarVentana(this, this.getWidth(), this.getHeight());
    }

    public PantallaABMProducto(Dialog owner, boolean modal, GestorProducto gestor, String title) {
        super((Dialog) null, modal);
//        GUILocal.establecerGUILocal(this);

        initComponents();
        this.gestor = gestor;
        cargarTipoProductos();
        cargarUnidadDeMedida();
        setTitle(title);


        IniciadorDeVentanas.iniciarVentana(this, this.getWidth(), this.getHeight());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl_codigo = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaDescripcion = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbTipoProducto = new javax.swing.JComboBox();
        btnAgregarTipo = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbUnidadMedida = new javax.swing.JComboBox();
        btnAgregarUnidadMedida = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlBaja = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtFechaBaja = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMotivoBaja = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Nombre:");

        lbl_codigo.setFont(new java.awt.Font("Tahoma", 1, 11));
        lbl_codigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_codigo.setText("Código:");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtCodigo.setText("Xxxxxx");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Descripción:");

        txtAreaDescripcion.setColumns(20);
        txtAreaDescripcion.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtAreaDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtAreaDescripcion);

        jButton1.setText("Registrar estructura");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Precio:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Tipo:");

        btnAgregarTipo.setText("Agregar");
        btnAgregarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTipoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("$");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Unidad de medida:");

        btnAgregarUnidadMedida.setText("Agregar");
        btnAgregarUnidadMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUnidadMedidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(lbl_codigo))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNombre)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(cmbTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnAgregarTipo)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmbUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAgregarUnidadMedida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_codigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarTipo)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarUnidadMedida)
                    .addComponent(jButton1))
                .addGap(20, 20, 20))
        );

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

        pnlBaja.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Baja", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Fecha:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Motivo:");

        txtMotivoBaja.setColumns(20);
        txtMotivoBaja.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtMotivoBaja.setRows(5);
        jScrollPane3.setViewportView(txtMotivoBaja);

        javax.swing.GroupLayout pnlBajaLayout = new javax.swing.GroupLayout(pnlBaja);
        pnlBaja.setLayout(pnlBajaLayout);
        pnlBajaLayout.setHorizontalGroup(
            pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBajaLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(96, 96, 96))
        );
        pnlBajaLayout.setVerticalGroup(
            pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBajaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlBaja, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtNombreActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        if (validar()) {
            producto.setNombre(txtNombre.getText().toUpperCase());
            producto.setDescripcion(txtAreaDescripcion.getText());
            producto.setPrecioUnitario(new BigDecimal(txtPrecio.getText()));
            producto.setTTproducto((TipoProducto) cmbTipoProducto.getSelectedItem());
            producto.setTUnidadesMedida((UnidadMedida) cmbUnidadMedida.getSelectedItem());

            if (txtFechaBaja.getText().compareTo("") != 0) {
                producto.setFecBaja(new Date());
                producto.setMotivoBaja(txtMotivoBaja.getText());
            }



            try {
                gestor.ejecutarOperacion(producto);
                Mensajes.mensajeInformacion(gestor.mensajeResultado(producto));
                this.vaciar();
                gestor.reiniciar(this);

            } catch (ExceptionGestor ex) {
                Logger.getLogger(PantallaABMProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnAgregarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTipoActionPerformed
        // TODO add your handling code here:       
        GestorTipoProducto.administarTipoProductoAgregar(this);
        this.cargarTipoProductos();
    }//GEN-LAST:event_btnAgregarTipoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAgregarUnidadMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUnidadMedidaActionPerformed
        // TODO add your handling code here:
        new GestorUnidadMedida().administrarUnidadMedidaAgregar(this);
        this.cargarUnidadDeMedida();

    }//GEN-LAST:event_btnAgregarUnidadMedidaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new GestorEstructura().iniciarCU(producto, false);

    }//GEN-LAST:event_jButton1ActionPerformed

    public boolean validar() {
        return true;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                PantallaABMProducto dialog = new PantallaABMProducto(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAgregarTipo;
    private javax.swing.JButton btnAgregarUnidadMedida;
    private javax.swing.JButton btnCancelar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbTipoProducto;
    private javax.swing.JComboBox cmbUnidadMedida;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_codigo;
    private javax.swing.JPanel pnlBaja;
    private javax.swing.JTextArea txtAreaDescripcion;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtFechaBaja;
    private javax.swing.JTextArea txtMotivoBaja;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables

    private void cargarDatos(Producto prod) {

        this.txtCodigo.setText(prod.codigoMerge());
        this.txtNombre.setText(prod.getNombre());
        this.txtPrecio.setText(prod.getPrecioUnitario() + "");
        this.txtAreaDescripcion.setText(prod.getDescripcion());

        if (prod.getFecBaja() == null) {
            this.txtFechaBaja.setText("");
            
        } else {
            Format formato = new SimpleDateFormat("dd/MM/yyyy");
            String fecha = formato.format(prod.getFecBaja());
            this.txtFechaBaja.setText(fecha);
        }

        if (prod.getMotivoBaja() == null) {
            this.txtMotivoBaja.setText("");
            
        } else {
            this.txtMotivoBaja.setText(prod.getMotivoBaja());



        }
        TipoProducto tipo = null;
        for (int i = 0; i < cmbTipoProducto.getItemCount(); i++) {
            tipo = (TipoProducto) cmbTipoProducto.getItemAt(i);
            if (tipo.getNombre().compareTo(this.producto.getTTproducto().getNombre()) == 0) {
                cmbTipoProducto.setSelectedIndex(i);
                break;
            }
        }
        UnidadMedida unidadMedida = null;
        for (int i = 0; i < cmbUnidadMedida.getItemCount(); i++) {
            unidadMedida = (UnidadMedida) cmbUnidadMedida.getItemAt(i);
            if (unidadMedida.getNombre().compareTo(this.producto.getTUnidadesMedida().getNombre()) == 0) {
                cmbTipoProducto.setSelectedIndex(i);
                break;
            }
        }

    }

    private void cargarTipoProductos() {
        try {
//            for(int i=0;i<cmbTipoProducto.getItemCount();i++){
//                cmbTipoProducto.remove(i);
//            }
            cmbTipoProducto.removeAllItems();

            List<TipoProducto> tipo = GestorProducto.traerTiposProductos();
            for (int i = 0; i < tipo.size(); i++) {
                cmbTipoProducto.addItem(tipo.get(i));
            }
        } catch (ExceptionGestor ex) {
            Logger.getLogger(PantallaABMProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
//        cmbTipoProducto.setSelectedIndex(2);
        cmbTipoProducto.repaint();
    }

    private void cargarUnidadDeMedida() {
        try {

            cmbUnidadMedida.removeAllItems();

            List<UnidadMedida> tipo = gestor.traerUnidadesMedida();
            for (int i = 0; i < tipo.size(); i++) {
                cmbUnidadMedida.addItem(tipo.get(i));
            }
        } catch (ExceptionGestor ex) {
            Logger.getLogger(PantallaABMProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
//        cmbTipoProducto.setSelectedIndex(2);
        cmbUnidadMedida.repaint();
    }

    public void nuevo() {
        this.txtCodigo.setVisible(false);
        this.lbl_codigo.setVisible(false);
        producto = new Producto();
        activarBaja(false);
        activarProducto(true);
        this.activarBotones(true, true);
        this.txtNombre.requestFocus();
    }

    public void modificar(Producto producto) {
        this.producto = producto;

        this.activarProducto(true);
        this.activarBaja(false);
        this.activarBotones(true, true);


        if (producto.getFecBaja() != null) {
//            this.btnAlta.setEnabled(true);
        }

        this.txtNombre.requestFocus();
        this.cargarDatos(this.producto);
    }

    public void baja(Producto prod) {


        this.producto = prod;
        this.activarProducto(false);
        this.activarBaja(true);
        this.activarBotones(true, true);
        this.txtMotivoBaja.requestFocus();
        this.cargarDatos(this.producto);
        Format formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formato.format(new Date());
        this.txtFechaBaja.setText(fecha);
    }

    public void vaciar() {
        this.txtCodigo.setText("");
        this.txtNombre.setText("");
        this.txtAreaDescripcion.setText("");
        this.txtPrecio.setText("");
        this.txtFechaBaja.setText("");
        this.txtMotivoBaja.setText("");
        this.cmbTipoProducto.setSelectedIndex(1);
    }

    private void activarProducto(boolean flag) {
        this.txtCodigo.setEnabled(flag);
        this.txtNombre.setEnabled(flag);
        this.txtAreaDescripcion.setEnabled(flag);
        this.txtPrecio.setEnabled(flag);
        this.cmbTipoProducto.setEnabled(flag);
        this.cmbUnidadMedida.setEnabled(flag);

    }

    private void activarBaja(boolean flag) {
        this.txtFechaBaja.setEnabled(false);
        this.txtMotivoBaja.setEnabled(flag);
    }

    private void activarBotones(boolean aceptar, boolean cancelar) {
        this.btnAceptar.setEnabled(aceptar);
        this.btnCancelar.setEnabled(cancelar);
    }

    public void reactivar(Producto traerProducto) {
        this.producto = traerProducto;
        this.activarProducto(false);
        this.activarBaja(false);
        this.activarBotones(true, true);
        this.cargarDatos(this.producto);

    }
}
