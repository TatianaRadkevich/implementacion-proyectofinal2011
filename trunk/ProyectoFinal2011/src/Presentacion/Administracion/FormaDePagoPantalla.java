/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormaDePagoPantalla.java
 *
 * Created on 21/12/2011, 10:27:11
 */

package Presentacion.Administracion;

import Negocio.Administracion.FormaPago;
import Negocio.Administracion.GestorFormaPago;
import Negocio.TipoDatoException;
import Presentacion.Mensajes;
import Presentacion.Operacion;
import Presentacion.Utilidades;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author Gabriela
 */
public class FormaDePagoPantalla extends javax.swing.JDialog {
    // Estos variables deben declararse para un ADMINISTRAR...
    private int operacion;
    private FormaPago tipo_actual=null;
    private GestorFormaPago gestor=new GestorFormaPago();
    private boolean True;



    /** Creates new form FormaDePagoPantalla */
    public FormaDePagoPantalla(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializar();
        cargarFormaPago();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        txtNombre = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMotivo = new javax.swing.JTextArea();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstDisponible = new javax.swing.JList();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnReactivar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Forma de pago"));

        jLabel1.setText("Nombre:");

        jLabel2.setText("Descripción:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        txtDescripcion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDescripcionFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(txtDescripcion);

        txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreFocusLost(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Baja"));

        jLabel3.setText("Fecha:");

        jLabel4.setText("Motivo:");

        txtMotivo.setColumns(20);
        txtMotivo.setRows(5);
        txtMotivo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMotivoFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(txtMotivo);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addGap(224, 224, 224))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(btnAceptar)
                .addGap(26, 26, 26)
                .addComponent(btnCancelar)
                .addContainerGap(153, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Disponible"));

        lstDisponible.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstDisponible.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstDisponibleMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(lstDisponible);

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnReactivar.setText("Reactivar");
        btnReactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReactivarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnReactivar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(btnNuevo)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnReactivar)))
                .addContainerGap(51, Short.MAX_VALUE))
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
                .addGap(65, 65, 65)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
 this.txtNombre.setEnabled(true);
 this.txtDescripcion.setEnabled(true);
 this.btnNuevo.setEnabled(false);
 this.btnModificar.setEnabled(false);
 this.btnEliminar.setEnabled(false);
 this.btnReactivar.setEnabled(false);
 this.btnSalir.setEnabled(false);
 this.btnAceptar.setEnabled(true);
 this.btnCancelar.setEnabled(true);

this.operacion= Operacion.nuevo;


    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
        
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
             this.inicializar();
             
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        this.activarBotones(false, false, false, false, false, true, true);
        this.txtNombre.setEnabled(true);
        this.txtDescripcion.setEnabled(true);
        tipo_actual=(FormaPago) lstDisponible.getSelectedValue();
        this.operacion= Operacion.modificar;
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        this.activarBotones(false, false, false, false, false, true, true);
        this.txtFecha.setEnabled(false);
        this.txtMotivo.setEnabled(true);
        Format formato=new SimpleDateFormat("dd/MM/yyyy");
        String fecha=formato.format(new Date());
        this.txtFecha.setText(fecha);
        this.operacion= Operacion.baja;
    }//GEN-LAST:event_btnEliminarActionPerformed


    private void lstDisponibleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstDisponibleMouseClicked
         if(lstDisponible.getSelectedIndex()!=-1){
        tipo_actual=(FormaPago) lstDisponible.getSelectedValue();
        this.cargarDatos(tipo_actual);
        this.txtNombre.setEnabled(false);
        this.txtDescripcion.setEnabled(false);
        this.operacion=Operacion.modificar;
        this.txtNombre.requestFocus();
        this.activarBotones(false, true, true, true, true, false, false);
        if (tipo_actual.getFecha()!=null) {
         this.btnEliminar.setEnabled(false);
         this.btnModificar.setEnabled(false);
        }
        }
    }//GEN-LAST:event_lstDisponibleMouseClicked


    private boolean validar(){
        return true;
    }

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed

          try {
            // TODO add your handling code here:
            tipo_actual.isOk();
        } catch (TipoDatoException ex) {
            Logger.getLogger(FormaDePagoPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
       

        try{
          if(validar()){

              tipo_actual.setNombre(txtNombre.getText().toUpperCase());

              if(operacion==Operacion.nuevo){

              FormaPago tipo=new FormaPago();

              tipo.setNombre(txtNombre.getText().toUpperCase());
              tipo.setDescripcion(txtDescripcion.getText());

              gestor.guardar(tipo);
            Mensajes.mensajeInformacion("La forma de pago "+tipo.getNombre()+"\n ha sido guardado exitosamente");
            this.cargarFormaPago();
            this.inicializar();
            this.lstDisponible.setSelectedIndex(-1);
            return;
        }

        if(operacion==Operacion.modificar){

                tipo_actual.setNombre(txtNombre.getText().toUpperCase());
                tipo_actual.setDescripcion(txtDescripcion.getText());

            gestor.modificar(tipo_actual);
            Mensajes.mensajeInformacion("La forma de pago "+tipo_actual.getNombre()+"\n ha sido modificado exitosamente");
            tipo_actual=null;
            this.inicializar();
            this.lstDisponible.setSelectedIndex(-1);
            return;
        }
        if(operacion==Operacion.baja){
            tipo_actual.setFecha(new Date());

                tipo_actual.setMotivo(txtMotivo.getText());

            gestor.modificar(tipo_actual);
            Mensajes.mensajeInformacion("La forma de pago "+tipo_actual.getNombre()+"\n ha sido eliminado exitosamente");
            tipo_actual=null;
            this.inicializar();
            this.lstDisponible.setSelectedIndex(-1);
            return;
        }
          if(operacion==Operacion.reactivar){
            tipo_actual.setFecha(null);
            tipo_actual.setMotivo(null);

            gestor.modificar(tipo_actual);
            this.inicializar();
            this.lstDisponible.setSelectedIndex(-1);
            Mensajes.mensajeInformacion("La forma de pago "+tipo_actual.getNombre()+"\n ha sido dado reactivado exitosamente");
        }
          }
        } catch(TipoDatoException e){
            Mensajes.mensajeErrorGenerico("Algunos campos no han sido ingresado correctamente.");
        }
        
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnReactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReactivarActionPerformed

         this.activarBotones(false, false, false, false, false, true, true);
         tipo_actual=(FormaPago) lstDisponible.getSelectedValue();
         this.operacion= Operacion.reactivar;
        
        
    }//GEN-LAST:event_btnReactivarActionPerformed

    private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusLost
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
            tipo_actual.setNombre(txtNombre.getText().toUpperCase());
            Utilidades.componenteCorrecto(txtNombre);
        } catch (TipoDatoException ex) {
            txtNombre.setToolTipText(ex.getMessage());
            Utilidades.componenteError(txtNombre);}
    }//GEN-LAST:event_txtNombreFocusLost

    private void txtDescripcionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescripcionFocusLost
            // TODO add your handling code here:
        try {
            tipo_actual.setDescripcion(txtDescripcion.getText().toUpperCase());
            Utilidades.componenteCorrecto(txtDescripcion);
        } catch (TipoDatoException ex) {
            txtDescripcion.setToolTipText(ex.getMessage());
            Utilidades.componenteError(txtDescripcion);
        }
    }//GEN-LAST:event_txtDescripcionFocusLost

    private void txtMotivoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMotivoFocusLost
        // TODO add your handling code here:
         try {
            tipo_actual.setMotivo(txtMotivo.getText().toUpperCase());
            Utilidades.componenteCorrecto(txtMotivo);
        } catch (TipoDatoException ex) {
            txtMotivo.setToolTipText(ex.getMessage());
            Utilidades.componenteError(txtMotivo);
        }

    }//GEN-LAST:event_txtMotivoFocusLost
 private void cargarDatos(FormaPago tipo_actual) {

        this.txtDescripcion.setText(tipo_actual.getDescripcion());
        this.txtNombre.setText(tipo_actual.getNombre());

    }

  private void activarBotones(boolean nuevo, boolean modificar, boolean eliminar, boolean reactivar, boolean salir, boolean aceptar, boolean cancelar){
        this.btnNuevo.setEnabled(nuevo);
        this.btnModificar.setEnabled(modificar);
        this.btnEliminar.setEnabled(eliminar);
        this.btnReactivar.setEnabled(reactivar);
        this.btnSalir.setEnabled(salir);
        this.btnAceptar.setEnabled(aceptar);
        this.btnCancelar.setEnabled(cancelar);
        }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormaDePagoPantalla dialog = new FormaDePagoPantalla(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnReactivar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList lstDisponible;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextArea txtMotivo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    public void nuevo() {
        throw new UnsupportedOperationException("Not yet implemented");
    }


    private void inicializar ()
    { this.txtDescripcion.setEnabled(false);
      this.txtFecha.setEnabled(false);
      this.txtMotivo.setEnabled(false);
      this.txtNombre.setEnabled(false);
      this.txtDescripcion.setText("");
      this.txtFecha.setText("");
      this.txtMotivo.setText("");
      this.txtNombre.setText("");
      this.activarBotones(true, false, false, false, true, false,false);
   
      
 }

    private void cargarFormaPago()
    { this.lstDisponible.removeAll();
      DefaultListModel modelo=new DefaultListModel();
      List<FormaPago> forPag= GestorFormaPago.listarFormaPago();
      for (int i=0; i<forPag.size(); i++)
      {modelo.addElement(forPag.get(i));}

      this.lstDisponible.setModel(modelo);
      this.lstDisponible.setSelectedIndex(-1);
    }


}
