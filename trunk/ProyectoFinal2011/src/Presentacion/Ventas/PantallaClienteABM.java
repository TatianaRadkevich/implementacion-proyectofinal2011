/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaAMBCliente.java
 *
 * Created on 26/06/2011, 16:26:04
 */
package Presentacion.Ventas;

import Negocio.Exceptiones.NegocioException;
import Negocio.Ventas.Cliente;
import Negocio.Ventas.GestorClienteABM;
import Negocio.Ventas.TipoCliente;
import Presentacion.Mensajes;
import Presentacion.Utilidades;
import Presentacion.ValidarTexbox;
import Presentacion.ZLinkers.*;
import java.awt.Frame;
import java.awt.Window;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;

/**
 *
 * @author Ivan
 */
public class PantallaClienteABM extends javax.swing.JDialog {

    /** Creates new form PantallaAMBCliente */
    private GestorClienteABM gestor;
    private ZLObject<Cliente> linker;

    private PantallaClienteABM(JDialog parent, boolean modal, GestorClienteABM g) {
        super(parent, modal);
        initComponents();
        this.gestor = g;
        /////////// Precargas Necesarias //////////
        habilitarBaja(false, false, null, "");
        // cargarValidaciones();
        pnlDomicilio.inicializar();
        recargarComboTipoCleinte();

        linker = new ZLObject<Cliente>(Cliente.class, gestor.getCliente());
        linker.add("apellidoResponsable", new ZLTextField(txtApellido));
        linker.add("nombreResponsable", new ZLTextField(txtNombre));
        linker.add("telefonoResponsable", new ZLTextField(txtTelefono));
        linker.add("razonSocial", new ZLTextField(txtRazonSocial));
        linker.add("cuit", new ZLTextField(txtCUIT));
        linker.add("correoElectronico", new ZLTextField(txtCorreoElectronico));
        linker.add("motivoBaja", new ZLTextField(txtMotivoBaja));

        linker.add("tipoCliente", new ZLComboBox(cmbTipoCliente));
        linker.add("domicilio", new ZLDomicilio(pnlDomicilio));

    }

    private void recargarComboTipoCleinte() {
        cmbTipoCliente.setModel(new DefaultComboBoxModel(gestor.getTipoClientes().toArray()));
    }

    public void cargarValidaciones() {
        //ValidarTexbox.validarInt(txtCUIT);
        ValidarTexbox.validarInt(txtTelefono);
        //ValidarTexbox.validarLongitud(txtCUIT, 11);
        ValidarTexbox.validarLongitud(txtNombre, 50);
        ValidarTexbox.validarLongitud(txtApellido, 200);
        ValidarTexbox.validarLongitud(txtCorreoElectronico, 50);
        ValidarTexbox.validarLongitud(txtRazonSocial, 50);
    }

    public void cargar(Cliente c) {
        txtApellido.setText(c.getApellidoResponsable());
        txtCUIT.setText(c.getCuit() + "");
        txtCorreoElectronico.setText(c.getCorreoElectronico());
        txtNombre.setText(c.getNombreResponsable());
        txtRazonSocial.setText(c.getRazonSocial());
        txtTelefono.setText(c.getTelefonoResponsable() + "");
        cmbTipoCliente.setSelectedItem(c.getTipoCliente());
        pnlDomicilio.setDomicilio(c.getDomicilio());
        if (c.getFechaBaja() != null) {
            // pnlBaja.setVisible(true);
            txtFechaBaja.setText(Utilidades.parseFecha(c.getFechaBaja()));
            txtMotivoBaja.setText(Utilidades.parseString(c.getMotivoBaja()));
        }
    }

    public void habilitarCarga(boolean b) {
        txtApellido.setEditable(b);
        txtCUIT.setEditable(b);
        txtCorreoElectronico.setEditable(b);
        pnlDomicilio.habilitar(b);
        txtNombre.setEditable(b);
        txtTelefono.setEditable(b);
        cmbTipoCliente.setEnabled(b);
        txtRazonSocial.setEditable(b);
        pnlDomicilio.setEnabled(b);

    }

    public void habilitarBaja(boolean visible, boolean edittable, Date fecha, String motivo) {
        // pnlBaja.setVisible(visible);
        scrollBaja.setEnabled(edittable);
        txtMotivoBaja.setEnabled(edittable);
        txtMotivoBaja.setText(Utilidades.parseString(motivo));
        txtFechaBaja.setText(Utilidades.parseFecha(fecha));
    }

    private void salir() {
        this.setVisible(false);
        this.dispose();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        pnlCliente = new javax.swing.JPanel();
        pnlResponsable = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtRazonSocial = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCorreoElectronico = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmbTipoCliente = new javax.swing.JComboBox();
        txtCUIT = new javax.swing.JFormattedTextField();
        pnlDomicilio = new Presentacion.PnlDomicilio();
        pnlBaja = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtFechaBaja = new javax.swing.JTextField();
        scrollBaja = new javax.swing.JScrollPane();
        txtMotivoBaja = new javax.swing.JTextArea();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo CLiente");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        pnlCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        pnlResponsable.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Responsable", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Nombre:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("Apellido:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setText("Teléfono:");

        javax.swing.GroupLayout pnlResponsableLayout = new javax.swing.GroupLayout(pnlResponsable);
        pnlResponsable.setLayout(pnlResponsableLayout);
        pnlResponsableLayout.setHorizontalGroup(
            pnlResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResponsableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)))
        );
        pnlResponsableLayout.setVerticalGroup(
            pnlResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResponsableLayout.createSequentialGroup()
                .addGroup(pnlResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos generales:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Tipo:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Correo electrónico:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("CUIT:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Razón social:");

        cmbTipoCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        try {
            txtCUIT.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-########-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCUIT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCUIT.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCUIT, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cmbTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCorreoElectronico, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                        .addComponent(txtRazonSocial, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCUIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCorreoElectronico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlClienteLayout = new javax.swing.GroupLayout(pnlCliente);
        pnlCliente.setLayout(pnlClienteLayout);
        pnlClienteLayout.setHorizontalGroup(
            pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClienteLayout.createSequentialGroup()
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlResponsable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDomicilio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlClienteLayout.setVerticalGroup(
            pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClienteLayout.createSequentialGroup()
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlDomicilio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlClienteLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pnlBaja.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Baja", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setText("Fecha:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setText("Motivo:");

        txtFechaBaja.setEditable(false);

        scrollBaja.setEnabled(false);

        txtMotivoBaja.setLineWrap(true);
        txtMotivoBaja.setWrapStyleWord(true);
        scrollBaja.setViewportView(txtMotivoBaja);

        javax.swing.GroupLayout pnlBajaLayout = new javax.swing.GroupLayout(pnlBaja);
        pnlBaja.setLayout(pnlBajaLayout);
        pnlBajaLayout.setHorizontalGroup(
            pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBajaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollBaja))
                .addContainerGap())
        );
        pnlBajaLayout.setVerticalGroup(
            pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBajaLayout.createSequentialGroup()
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(scrollBaja, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlBaja, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSalir)
                        .addGap(18, 18, 18)
                        .addComponent(btnAceptar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAceptar)
                        .addComponent(btnSalir))
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
//        Cliente cli = gestor.getCliente();
//        cli.setApellidoResponsable(txtApellido.getText());
//        cli.setCorreoElectronico(txtCorreoElectronico.getText());
//        cli.setCuit(Utilidades.parseString(txtCUIT.getText()));
//        cli.setNombreResponsable(txtNombre.getText());
//        cli.setRazonSocial(txtRazonSocial.getText());
//        cli.setTelefonoResponsable(Utilidades.parseLong(txtTelefono.getText()));
//        cli.setTipoCliente((TipoCliente) cmbTipoCliente.getSelectedItem());
//        cli.setDomicilio(pnlDomicilio.getDomicilio());
//        cli.setMotivoBaja(Utilidades.parseString(txtMotivoBaja.getText()));
//        cli.setMotivoBaja(null);


        try {
            gestor.setMotivoBaja(txtMotivoBaja.getText());
            linker.save();
            gestor.aceptar();
            salir();
        } catch (NegocioException ex) {
            Mensajes.mensajeErrorGenerico(ex.getMessage());
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        if (Mensajes.mensajeConfirmacionGenerico("¿Realmentes desea salir?")) {
            salir();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                iniciarClienteAlta(null);
            }
        });
    }

    public static Cliente iniciarClienteAlta(JDialog parent) {
        GestorClienteABM g = GestorClienteABM.getGestorClienteAlta();
        PantallaClienteABM interfaz = new PantallaClienteABM(parent, true, g);
        interfaz.setTitle("Registrar Cliente");
        interfaz.btnSalir.setVisible(false);
        interfaz.setVisible(true);

        return (g.isCorrecto()) ? g.getCliente() : null;
    }

    public static void iniciarClienteBaja(JDialog parent, Cliente cli) {

        if (cli == null) {
            throw new RuntimeException("GestorClienteEliminar: Se debe definir el cliente a eliminar");
        }
        PantallaClienteABM interfaz = new PantallaClienteABM(parent, true, GestorClienteABM.getGestorClienteBaja(cli));

        interfaz.cargar(cli);
        interfaz.btnSalir.setVisible(false);
        interfaz.habilitarBaja(true, true, Utilidades.getFechaActual(), "");
        interfaz.habilitarCarga(false);
        interfaz.setTitle("Eliminar Cliente");
        interfaz.setVisible(true);
    }

    public static void iniciarClienteVer(JDialog parent, Cliente cli) {

        if (cli == null) {
            throw new RuntimeException("GestorClienteEliminar: Se debe definir el cliente a ver");
        }
        PantallaClienteABM interfaz = new PantallaClienteABM(parent, true, GestorClienteABM.getGestorClienteBaja(cli));

        interfaz.cargar(cli);
        Utilidades.habilitarPanel(interfaz.getContentPane(), false);

        interfaz.btnAceptar.setVisible(false);
        interfaz.btnCancelar.setVisible(false);

        interfaz.btnSalir.setEnabled(true);

        interfaz.setTitle("Ver Cliente");
        interfaz.setVisible(true);
    }

    public static void iniciarClienteModificar(JDialog parent, Cliente cli) {

        if (cli == null) {
            throw new RuntimeException("GestorClienteModificar: Se debe definir el Cliente a modificar");
        }

        PantallaClienteABM interfaz = new PantallaClienteABM(parent, true, GestorClienteABM.getGestorClienteModificar(cli));
        interfaz.btnSalir.setVisible(false);
        interfaz.cargar(cli);
        interfaz.setTitle("Modificar Cliente");
        interfaz.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cmbTipoCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnlBaja;
    private javax.swing.JPanel pnlCliente;
    private Presentacion.PnlDomicilio pnlDomicilio;
    private javax.swing.JPanel pnlResponsable;
    private javax.swing.JScrollPane scrollBaja;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JFormattedTextField txtCUIT;
    private javax.swing.JTextField txtCorreoElectronico;
    private javax.swing.JTextField txtFechaBaja;
    private javax.swing.JTextArea txtMotivoBaja;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
