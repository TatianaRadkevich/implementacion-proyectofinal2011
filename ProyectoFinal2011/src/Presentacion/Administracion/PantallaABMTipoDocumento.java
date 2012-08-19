/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AdministrarTipoDocumento.java
 *
 * Created on 13/08/2011, 18:40:32
 */
package Presentacion.Administracion;

import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Operacion;
import Negocio.Administracion.GestorTipoDocumento;
import Negocio.Administracion.TipoDocumento;
import Negocio.Exceptiones.TipoDatoException;
import Presentacion.IniciadorDeVentanas;
import Presentacion.Mensajes;
import Presentacion.Utilidades;
import gui.GUILocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author Ivan
 */
public class PantallaABMTipoDocumento extends javax.swing.JDialog {

    private int operacion;
    private TipoDocumento tipo_actual = null;
    private GestorTipoDocumento gestor = new GestorTipoDocumento();

    /** Creates new form AdministrarTipoDocumento */
    public PantallaABMTipoDocumento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.activarTipoDocumento(false);
        this.btnBaja.setEnabled(false);
        this.activarDisponible(true);
        this.activarBotones(true, false, false, false, false);
        this.cargarTipoDocumento();
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

        jPanel1 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstDisponible = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Tipo Documento");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo Documento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreFocusLost(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Nombre:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Descripción:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDescripcionFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(txtDescripcion);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(179, Short.MAX_VALUE)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Disponible", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lstDisponible.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstDisponibleValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lstDisponible);

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnBaja.setText("Eliminar");
        btnBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajaActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBaja)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalir)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed

        try {
            tipo_actual.validarOk();
        } catch (TipoDatoException ex) {
            Mensajes.mensajeErrorGenerico(ex.getMessage());
            return;
        }

        if (operacion == Operacion.nuevo) {

//            try{
//            tipo_actual.setNombre(txtNombre.getText().toUpperCase());
//            } catch(TipoDatoException e){
//            Mensajes.mensajeErrorGenerico("Algunos campos no han sido ingresados correctamente.");
//               }
            tipo_actual.setDescripcion(txtDescripcion.getText());

            gestor.guardar(tipo_actual);
            Mensajes.mensajeInformacion("El tipo de documento " + tipo_actual.getNombre() + "\n ha sido guardado exitosamente");
            this.vaciar();
            cancelar();
            this.cargarTipoDocumento();
            return;
        }

        if (operacion == Operacion.modificar) {
            tipo_actual.setDescripcion(txtDescripcion.getText());
            gestor.modificar(tipo_actual);
            Mensajes.mensajeInformacion("El tipo de documento " + tipo_actual.getNombre() + "\n ha sido modificado exitosamente");
            this.vaciar();
            cancelar();
            tipo_actual = null;
            return;
        }

        if (operacion == Operacion.baja) {
            try {
                gestor.eliminar(tipo_actual);
                Mensajes.mensajeInformacion("El tipo de documento " + tipo_actual.getNombre() + "\n ha sido eliminado exitosamente");
                this.vaciar();
                tipo_actual = null;
                cancelar();
                this.cargarTipoDocumento();
                return;
            } catch (Exception e) {
                Mensajes.mensajeErrorGenerico(e.getMessage());
                return;
            }
        }


    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        nuevo();

        txtNombre.requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (lstDisponible.getSelectedIndex() == -1) {
            Mensajes.mensajeErrorGenerico("Debe seleccionar el tipo de documento que desea modificar");
            return;
        }
        this.activarDisponible(false);
        this.btnBaja.setEnabled(false);
        this.activarTipoDocumento(true);
        this.activarBotones(false, false, false, true, true);
        tipo_actual = (TipoDocumento) lstDisponible.getSelectedValue();
        this.cargarDatos(tipo_actual);
        this.operacion = Operacion.modificar;
        this.txtNombre.requestFocus();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed
        if (lstDisponible.getSelectedIndex() == -1) {
            Mensajes.mensajeErrorGenerico("Debe seleccionar el tipo de documento que desea dar de baja");
            return;
        }

        tipo_actual = (TipoDocumento) lstDisponible.getSelectedValue();
        this.cargarDatos(tipo_actual);
        this.activarTipoDocumento(false);
        this.activarDisponible(false);
        this.activarBotones(false, false, false, true, true);
        this.operacion = Operacion.baja;
    }//GEN-LAST:event_btnBajaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.activarTipoDocumento(false);
        this.btnBaja.setEnabled(false);
        this.activarDisponible(true);
        this.activarBotones(true, false, false, false, false);
        this.vaciar();
        this.cargarTipoDocumento();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusLost
        try {
            Utilidades.componenteCorrecto(txtNombre);
            tipo_actual.setNombre(txtNombre.getText().toUpperCase());
        } catch (TipoDatoException ex) {
            Utilidades.componenteError(txtNombre, ex.getMessage());
        }
    }//GEN-LAST:event_txtNombreFocusLost

    private void lstDisponibleValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstDisponibleValueChanged
        // TODO add your handling code here:
        if (lstDisponible.getSelectedIndex() != -1) {
            TipoDocumento temp = (TipoDocumento) lstDisponible.getSelectedValue();
            this.cargarDatos(temp);
            this.activarDisponible(true);
            this.activarTipoDocumento(false);
            this.activarBotones(true, true, true, false, false);
        }
    }//GEN-LAST:event_lstDisponibleValueChanged

    private void txtDescripcionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescripcionFocusLost
        // TODO add your handling code here:
        try {
            Utilidades.componenteCorrecto(txtDescripcion);
            tipo_actual.setDescripcion(txtDescripcion.getText());
        } catch (TipoDatoException ex) {
            Utilidades.componenteError(txtDescripcion, ex.getMessage());
        }
    }//GEN-LAST:event_txtDescripcionFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                PantallaABMTipoDocumento dialog = new PantallaABMTipoDocumento(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lstDisponible;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    private void cargarTipoDocumento() {
        try {
            lstDisponible.removeAll();
            DefaultListModel modelo = new DefaultListModel();

            List<TipoDocumento> tipo = GestorTipoDocumento.listarTipoDocumentos();
            for (int i = 0; i < tipo.size(); i++) {
                modelo.addElement(tipo.get(i));                
            }
            lstDisponible.setModel(modelo);
            lstDisponible.updateUI();
        } catch (Exception ex) {
            Logger.getLogger(PantallaABMTipoDocumento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void activarTipoDocumento(boolean flag) {
        this.txtNombre.setEnabled(flag);
        this.txtDescripcion.setEnabled(flag);
    }

    private void activarDisponible(boolean flag) {
        this.lstDisponible.setEnabled(flag);
    }

    private void activarBotones(boolean nuevo, boolean modificar, boolean baja, boolean aceptar, boolean cancelar) {
        this.btnNuevo.setEnabled(nuevo);
        this.btnModificar.setEnabled(modificar);
        this.btnBaja.setEnabled(baja);
        this.btnAceptar.setEnabled(aceptar);
        this.btnCancelar.setEnabled(cancelar);
    }

    private void vaciar() {
        this.txtDescripcion.setText("");
        this.txtNombre.setText("");
        Utilidades.componenteCorrecto(txtDescripcion);
        Utilidades.componenteCorrecto(txtNombre);
    }

    private void cancelar() {
        this.activarTipoDocumento(false);
        this.btnBaja.setEnabled(false);
        this.activarDisponible(true);
        this.activarBotones(true, false, false, false, false);
        this.vaciar();
    }

    public void nuevo() {
        // TODO add your handling code here:
        this.vaciar();
        this.activarDisponible(false);
        this.btnBaja.setEnabled(false);
        this.activarTipoDocumento(true);
        this.activarBotones(false, false, false, true, true);
        this.operacion = Operacion.nuevo;
        tipo_actual = new TipoDocumento();
    }

    private void cargarDatos(TipoDocumento tipo_actual) {

        this.txtDescripcion.setText(tipo_actual.getDescripcion());
        this.txtNombre.setText(tipo_actual.getNombre());

    }

    private boolean validar() {
        return true;
    }
}
