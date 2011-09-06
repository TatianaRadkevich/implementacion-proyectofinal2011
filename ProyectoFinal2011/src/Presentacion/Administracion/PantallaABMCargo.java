/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AdministrarCargos.java
 *
 * Created on 13/08/2011, 18:02:17
 */

package Presentacion.Administracion;

import Negocio.Administracion.Cargo;
import Negocio.Administracion.GestorCargo;
import Presentacion.IniciadorDeVentanas;
import Presentacion.Mensajes;
import Presentacion.Operacion;
import gui.GUILocal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author Ivan
 */
public class PantallaABMCargo extends javax.swing.JDialog {

    private int operacion;
    private Cargo cargo_actual=null;
    private GestorCargo gestor=new GestorCargo();
    /** Creates new form AdministrarCargos */
    public PantallaABMCargo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        GUILocal.establecerGUILocal(this);
        initComponents();

          this.activarCargo(false);
       this.activarBaja(false);
       this.activarDisponible(true);
       this.activarBotones(true, false, true, false, true,false);
       this.cargarCargos();
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

        pnlCargo = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        pnlBaja = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtFechaBaja = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMotivoBaja = new javax.swing.JTextArea();
        pnlDisponible = new javax.swing.JPanel();
        btnBaja = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstDisponible = new javax.swing.JList();
        btnReactivar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlCargo.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo producto"));

        jLabel1.setText("Nombre:");

        jLabel2.setText("Descripción:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        pnlBaja.setBorder(javax.swing.BorderFactory.createTitledBorder("Baja"));

        jLabel4.setText("Fecha:");

        jLabel5.setText("Motivo:");

        txtMotivoBaja.setColumns(20);
        txtMotivoBaja.setRows(5);
        jScrollPane3.setViewportView(txtMotivoBaja);

        javax.swing.GroupLayout pnlBajaLayout = new javax.swing.GroupLayout(pnlBaja);
        pnlBaja.setLayout(pnlBajaLayout);
        pnlBajaLayout.setHorizontalGroup(
            pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBajaLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtFechaBaja, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlBajaLayout.setVerticalGroup(
            pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBajaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout pnlCargoLayout = new javax.swing.GroupLayout(pnlCargo);
        pnlCargo.setLayout(pnlCargoLayout);
        pnlCargoLayout.setHorizontalGroup(
            pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCargoLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCargoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlCargoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlCargoLayout.setVerticalGroup(
            pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCargoLayout.createSequentialGroup()
                .addGroup(pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addComponent(pnlBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlDisponible.setBorder(javax.swing.BorderFactory.createTitledBorder("Disponible"));

        btnBaja.setText("Baja");
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

        jScrollPane2.setViewportView(lstDisponible);

        btnReactivar.setText("Reactivar");
        btnReactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReactivarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDisponibleLayout = new javax.swing.GroupLayout(pnlDisponible);
        pnlDisponible.setLayout(pnlDisponibleLayout);
        pnlDisponibleLayout.setHorizontalGroup(
            pnlDisponibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDisponibleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReactivar)
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDisponibleLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143))
        );
        pnlDisponibleLayout.setVerticalGroup(
            pnlDisponibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDisponibleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDisponibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDisponibleLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addGroup(pnlDisponibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnModificar)
                            .addComponent(btnBaja)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDisponibleLayout.createSequentialGroup()
                        .addComponent(btnReactivar)
                        .addContainerGap())))
        );

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(192, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pnlDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGap(297, 297, 297))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNuevo))
                    .addComponent(pnlDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed
        // TODO add your handling code here:
        if(lstDisponible.getSelectedIndex()==-1){
            Mensajes.mensajeErrorGenerico("Debe seleccionar un tipo de producto que desea dar de baja");
            return;
        }

        cargo_actual=(Cargo) lstDisponible.getSelectedValue();
        this.cargarDatos(cargo_actual);
        Format formato=new SimpleDateFormat("dd/MM/yyyy");
        String fecha=formato.format(new Date());
        this.txtFechaBaja.setText(fecha);
        this.activarBaja(true);
        this.activarCargo(false);
        this.activarDisponible(false);
        this.activarBotones(false, false, false, true, true,false);
        txtMotivoBaja.requestFocus();
        this.operacion=Operacion.baja;
}//GEN-LAST:event_btnBajaActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        if(lstDisponible.getSelectedIndex()==-1){
            Mensajes.mensajeErrorGenerico("Debe seleccionar un tipo de producto que desea modificar");
            return;
        }
        this.activarDisponible(false);
        this.activarBaja(false);
        this.activarCargo(true);
       this.activarBotones(false, false, false, true, true,false);

        cargo_actual=(Cargo) lstDisponible.getSelectedValue();
        this.cargarDatos(cargo_actual);
        this.operacion=Operacion.modificar;
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        nuevo();
}//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        if(validar()){

        }
        if(operacion==Operacion.nuevo){
            Cargo cargo_temp=new Cargo();
            cargo_temp.setNombre(txtNombre.getText().toUpperCase());
            cargo_temp.setDescripcion(txtDescripcion.getText());

            gestor.guardar(cargo_temp);
            Mensajes.mensajeInformacion("El tipo de producto "+cargo_temp.getNombre()+"\n ha sido guardado exitosamente");
            this.vaciar();
            cancelar();
            this.cargarCargos();
            return;
        }

        if(operacion==Operacion.modificar){
            cargo_actual.setNombre(txtNombre.getText().toUpperCase());
            cargo_actual.setDescripcion(txtDescripcion.getText());
            gestor.modificar(cargo_actual);
            Mensajes.mensajeInformacion("El tipo de producto "+cargo_actual.getNombre()+"\n ha sido modificado exitosamente");
            this.vaciar();
            cancelar();
            cargo_actual=null;
            return;
        }
        if(operacion==Operacion.baja){
            cargo_actual.setFecBaja(new Date());
            cargo_actual.setMotivoBaja(txtMotivoBaja.getText());
            gestor.modificar(cargo_actual);

            Mensajes.mensajeInformacion("El tipo de producto "+cargo_actual.getNombre()+"\n ha sido dado de baja exitosamente");
            this.vaciar();
            cargo_actual=null;
            cancelar();
            return;
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:

        if(!btnAceptar.isEnabled()){
            this.dispose();
            return;
        }

         this.cargarCargos();
        this.vaciar();

        this.cargo_actual=null;
        this.cancelar();
}//GEN-LAST:event_btnCancelarActionPerformed

    private void btnReactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReactivarActionPerformed
        // TODO add your handling code here:
        this.cargo_actual=(Cargo) lstDisponible.getSelectedValue();
        cargo_actual.setFecBaja(null);
        this.cargo_actual.setMotivoBaja(null);
        this.activarBaja(false);
        this.activarCargo(false);
        this.activarBotones(false, false, false, true, true, false);
        this.txtFechaBaja.setText("");
        this.txtMotivoBaja.setText("");
        this.operacion=Operacion.modificar;
}//GEN-LAST:event_btnReactivarActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PantallaABMCargo dialog = new PantallaABMCargo(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnReactivar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList lstDisponible;
    private javax.swing.JPanel pnlBaja;
    private javax.swing.JPanel pnlCargo;
    private javax.swing.JPanel pnlDisponible;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtFechaBaja;
    private javax.swing.JTextArea txtMotivoBaja;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables


      //---------------------------------------------------------------------------

    private void cargarCargos(){
        try {
            lstDisponible.removeAll();
            DefaultListModel modelo = new DefaultListModel();

            List<Cargo> tipo = GestorCargo.listarCargos();
            for(int i=0;i<tipo.size();i++){
                modelo.addElement(tipo.get(i));

            lstDisponible.setModel(modelo);


            }
        } catch (Exception ex) {
          
        }
    }
    private void activarCargo(boolean flag){       
        this.txtNombre.setEnabled(flag);
        this.txtDescripcion.setEnabled(flag);
    }
    private void activarBaja(boolean flag){
        this.txtFechaBaja.setEnabled(false);
        this.txtMotivoBaja.setEnabled(flag);
    }
    private void activarDisponible(boolean  flag)
    {
        this.lstDisponible.setEnabled(flag);
    }

     private void activarBotones(boolean nuevo, boolean modificar, boolean baja, boolean aceptar, boolean cancelar, boolean reactivar){
        this.btnNuevo.setEnabled(nuevo);
        this.btnModificar.setEnabled(modificar);
        this.btnBaja.setEnabled(baja);
        this.btnAceptar.setEnabled(aceptar);
        this.btnCancelar.setEnabled(cancelar);
        this.btnReactivar.setEnabled(reactivar);

    }
    //--------------------------------
     private void vaciar(){        
        this.txtDescripcion.setText("");
        this.txtNombre.setText("");
        this.txtFechaBaja.setText("");
        this.txtMotivoBaja.setText("");

    }
    private boolean validar(){
        return true;
    }
    private void cargarDatos(Cargo carg){

        this.txtDescripcion.setText(carg.getDescripcion());
        this.txtNombre.setText(carg.getNombre());

        if(carg.getFecBaja()==null)
            this.txtFechaBaja.setText("");
        else
        {
            Format formato=new SimpleDateFormat("dd/MM/yyyy");
            String fecha=formato.format(carg.getFecBaja());
            this.txtFechaBaja.setText(fecha);
        }

        if(carg.getMotivoBaja()==null)
            this.txtMotivoBaja.setText("");
        else
            this.txtMotivoBaja.setText(carg.getMotivoBaja());


    }

     private void cancelar(){
         this.activarCargo(false);
       this.activarBaja(false);
       this.activarDisponible(true);
       this.activarBotones(true, false, false,  false, true,false);
       this.vaciar();
    }

     public void nuevo() {
        // TODO add your handling code here:
        this.activarDisponible(false);
        this.activarBaja(false);
        this.activarCargo(true);
        this.activarBotones(false, false, false, true, true,false);
        this.operacion = Operacion.nuevo;
    }

}