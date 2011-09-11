/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaABMUnidadMedida.java
 *
 * Created on 10/09/2011, 16:30:39
 */

package Presentacion.Produccion;

import Presentacion.Operacion;
import Negocio.Produccion.UnidadMedida;
import Negocio.Produccion.GestorUnidadMedida;
import Presentacion.IniciadorDeVentanas;
import Presentacion.Mensajes;
import gui.GUILocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
/**
 *
 * @author Heber Parrucci
 */
public class PantallaABMUnidadMedida extends javax.swing.JDialog {

    private int operacion;
    private UnidadMedida unidad_actual=null;
    private GestorUnidadMedida gestor=new GestorUnidadMedida();

    /** Creates new form PantallaABMUnidadMedida */
    public PantallaABMUnidadMedida(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        GUILocal.establecerGUILocal(this);
        initComponents();

       this.activarUnidadMedida(false);
       this.btnBaja.setEnabled(false);
       this.activarDisponible(true);
       this.activarBotones(true, false, false, false, false);
       this.cargarUnidadMedida();
       IniciadorDeVentanas.iniciarVentana(this, this.getWidth(),this.getHeight());
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
        btnBaja = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Unidad de Medida"));

        jLabel1.setText("Nombre:");

        jLabel2.setText("Descripción:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
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
                        .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(132, Short.MAX_VALUE)
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Disponible"));

        lstDisponible.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstDisponibleMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstDisponible);

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

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBaja)))
                .addContainerGap(72, Short.MAX_VALUE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        nuevo();
        txtNombre.requestFocus();
}//GEN-LAST:event_btnNuevoActionPerformed

    private void lstDisponibleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstDisponibleMouseClicked
        if(lstDisponible.getSelectedIndex()!=-1){
        UnidadMedida temp= (UnidadMedida) lstDisponible.getSelectedValue();
        this.cargarDatos(temp);
        this.activarDisponible(true);
        this.activarUnidadMedida(false);
        this.activarBotones(true, true, true, false, false);
        }
}//GEN-LAST:event_lstDisponibleMouseClicked

    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed
        if(lstDisponible.getSelectedIndex()==-1){
            Mensajes.mensajeErrorGenerico("Debe seleccionar la unidad de medida que desea dar de baja");
            return;
        }

        unidad_actual =(UnidadMedida) lstDisponible.getSelectedValue();
        this.cargarDatos(unidad_actual);
        this.activarUnidadMedida(false);
        this.activarDisponible(false);
        this.activarBotones(false, false, false, true, true);
        this.operacion=Operacion.baja;
}//GEN-LAST:event_btnBajaActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if(lstDisponible.getSelectedIndex()==-1){
            Mensajes.mensajeErrorGenerico("Debe seleccionar la unidad de medida que desea modificar");
            return;
        }
        this.activarDisponible(false);
        this.btnBaja.setEnabled(false);
        this.activarUnidadMedida(true);
        this.activarBotones(false, false, false, true, true);
        unidad_actual=(UnidadMedida) lstDisponible.getSelectedValue();
        this.cargarDatos(unidad_actual);
        this.operacion=Operacion.modificar;
        this.txtNombre.requestFocus();
}//GEN-LAST:event_btnModificarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        

        
        if(operacion==Operacion.nuevo){
            if(validar()){
            UnidadMedida unidad=new UnidadMedida();
            unidad.setNombre(txtNombre.getText().toUpperCase());
            unidad.setDescripcion(txtDescripcion.getText());

            gestor.guardar(unidad);
            Mensajes.mensajeInformacion("La unidad de medida "+unidad.getNombre()+"\n ha sido guardada exitosamente");
            this.vaciar();
            cancelar();
            this.cargarUnidadMedida();
            return;
        }
        else return;
        }

        if(operacion==Operacion.modificar){
            if(validar()){
            unidad_actual.setNombre(txtNombre.getText().toUpperCase());
            unidad_actual.setDescripcion(txtDescripcion.getText());
            gestor.modificar(unidad_actual);
            Mensajes.mensajeInformacion("La unidad de medida "+unidad_actual.getNombre()+"\n ha sido modificada exitosamente");
            this.vaciar();
            cancelar();
            unidad_actual=null;
            return;
        }
        else return;
        }
        if(operacion==Operacion.baja){
            gestor.eliminar(unidad_actual);
            Mensajes.mensajeInformacion("La unidad de medida "+unidad_actual.getNombre()+"\n ha sido eliminada exitosamente");
            this.vaciar();
            unidad_actual=null;
            cancelar();
            this.cargarUnidadMedida();
            return;
        }
     
}//GEN-LAST:event_btnAceptarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
        System.exit(0);
}//GEN-LAST:event_btnSalirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.activarUnidadMedida(false);
        this.btnBaja.setEnabled(false);
        this.activarDisponible(true);
        this.activarBotones(true, false, false, false, false);
        this.vaciar();
}//GEN-LAST:event_btnCancelarActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PantallaABMUnidadMedida dialog = new PantallaABMUnidadMedida(new javax.swing.JFrame(), true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lstDisponible;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

     private void cargarUnidadMedida(){
        try {
            lstDisponible.removeAll();
            DefaultListModel modelo = new DefaultListModel();

            List<UnidadMedida> tipo = GestorUnidadMedida.listarUnidadMedida();
            for(int i=0;i<tipo.size();i++){
                modelo.addElement(tipo.get(i));

            lstDisponible.setModel(modelo);


            }
        } catch (Exception ex) {
            Logger.getLogger(PantallaABMUnidadMedida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void activarUnidadMedida(boolean flag){
        this.txtNombre.setEnabled(flag);
        this.txtDescripcion.setEnabled(flag);
    }

    private void activarDisponible(boolean  flag)
    {
        this.lstDisponible.setEnabled(flag);
    }

    private void activarBotones(boolean nuevo, boolean modificar, boolean baja, boolean aceptar, boolean cancelar){
        this.btnNuevo.setEnabled(nuevo);
        this.btnModificar.setEnabled(modificar);
        this.btnBaja.setEnabled(baja);
        this.btnAceptar.setEnabled(aceptar);
        this.btnCancelar.setEnabled(cancelar);
}

       private void vaciar(){
        this.txtDescripcion.setText("");
        this.txtNombre.setText("");
 }


     private void cancelar(){
       this.activarUnidadMedida(false);
       this.btnBaja.setEnabled(false);
       this.activarDisponible(true);
       this.activarBotones(true, false, false,  false, false);
       this.vaciar();
    }

     public void nuevo() {
        // TODO add your handling code here:
        this.vaciar();
        this.activarDisponible(false);
        this.btnBaja.setEnabled(false);
        this.activarUnidadMedida(true);
        this.activarBotones(false, false, false, true, true);
        this.operacion = Operacion.nuevo;
    }

    private void cargarDatos(UnidadMedida unidad_actual) {

        this.txtDescripcion.setText(unidad_actual.getDescripcion());
        this.txtNombre.setText(unidad_actual.getNombre());

    }

    private boolean validar() {
             if (txtNombre.getText().compareTo("")==0)
        {
            Mensajes.mensajeErrorGenerico("Debe ingresar el nombre");
            txtNombre.requestFocus();
            return false;
        }
        return true;
    }
}
