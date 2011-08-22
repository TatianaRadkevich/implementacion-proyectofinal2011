/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaABMEtapaProduccion.java
 *
 * Created on 13/08/2011, 19:08:28
 */

package Presentacion.Produccion;

import Negocio.Produccion.EstadoEtapaProduccion;
import Negocio.Produccion.GestorEtapaProduccion;
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
public class PantallaABMEtapaProduccion extends javax.swing.JDialog {

        private int operacion;
        private EstadoEtapaProduccion etapa_actual=null;
        private GestorEtapaProduccion gestor=new GestorEtapaProduccion();
    /** Creates new form PantallaABMEtapaProduccion */
    public PantallaABMEtapaProduccion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        GUILocal.establecerGUILocal(this);
        initComponents();
         this.activarCargo(false);
       this.activarBaja(false);
       this.activarDisponible(true);
       this.activarBotones(true, true, true, false, false, true);
       this.cargarEtapasProduccion();
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
        btnAlta = new javax.swing.JButton();
        pnlDisponible = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstDisponible = new javax.swing.JList();
        btnBaja = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
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

        btnAlta.setText("Alta");
        btnAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBajaLayout = new javax.swing.GroupLayout(pnlBaja);
        pnlBaja.setLayout(pnlBajaLayout);
        pnlBajaLayout.setHorizontalGroup(
            pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBajaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBajaLayout.createSequentialGroup()
                        .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlBajaLayout.setVerticalGroup(
            pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBajaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAlta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout pnlCargoLayout = new javax.swing.GroupLayout(pnlCargo);
        pnlCargo.setLayout(pnlCargoLayout);
        pnlCargoLayout.setHorizontalGroup(
            pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCargoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCargoLayout.createSequentialGroup()
                        .addGroup(pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addComponent(pnlBaja, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlCargoLayout.setVerticalGroup(
            pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCargoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pnlBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnlDisponible.setBorder(javax.swing.BorderFactory.createTitledBorder("Disponible"));

        jScrollPane2.setViewportView(lstDisponible);

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

        javax.swing.GroupLayout pnlDisponibleLayout = new javax.swing.GroupLayout(pnlDisponible);
        pnlDisponible.setLayout(pnlDisponibleLayout);
        pnlDisponibleLayout.setHorizontalGroup(
            pnlDisponibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDisponibleLayout.createSequentialGroup()
                .addGroup(pnlDisponibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDisponibleLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDisponibleLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDisponibleLayout.setVerticalGroup(
            pnlDisponibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDisponibleLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(pnlDisponibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btnBaja)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevo))
                    .addComponent(pnlDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltaActionPerformed
        // TODO add your handling code here:

        this.etapa_actual.setMotivoBaja(null);
        this.etapa_actual.setFecBaja(null);
        this.txtFechaBaja.setText("");
        this.txtMotivoBaja.setText("");
    }//GEN-LAST:event_btnAltaActionPerformed

    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed
        // TODO add your handling code here:
        if(lstDisponible.getSelectedIndex()==-1){
            Mensajes.mensajeErrorGenerico("Debe seleccionar un tipo de producto que desea dar de baja");
            return;
        }

        etapa_actual=(EstadoEtapaProduccion) lstDisponible.getSelectedValue();
        this.cargarDatos(etapa_actual);
        Format formato=new SimpleDateFormat("dd/MM/yyyy");
        String fecha=formato.format(new Date());
        this.txtFechaBaja.setText(fecha);
        this.activarBaja(true);
        this.activarCargo(false);
        this.activarDisponible(false);
        this.activarBotones(false, false, false, false, true, true);
        txtMotivoBaja.requestFocus();
        this.operacion=Operacion.baja;
}//GEN-LAST:event_btnBajaActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        if(lstDisponible.getSelectedIndex()==-1){
            Mensajes.mensajeErrorGenerico("Debe seleccionar la etapa de produccion que desea modificar");
            return;
        }
        this.activarDisponible(false);
        this.activarBaja(false);
        this.activarCargo(true);
        this.activarBotones(false, false, false, false, true, true);

        etapa_actual=(EstadoEtapaProduccion) lstDisponible.getSelectedValue();
        this.cargarDatos(etapa_actual);
        if(etapa_actual.getFecBaja()!=null){
            this.btnAlta.setEnabled(true);
        }
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
            EstadoEtapaProduccion tipo=new EstadoEtapaProduccion();
            tipo.setNombre(txtNombre.getText().toUpperCase());
            tipo.setDescripcion(txtDescripcion.getText());

            gestor.guardar(tipo);
            Mensajes.mensajeInformacion("La etapa de producción "+tipo.getNombre()+"\n ha sido guardado exitosamente");
            this.vaciar();
            cancelar();
            this.cargarEtapasProduccion();
            return;
        }

        if(operacion==Operacion.modificar){
            etapa_actual.setNombre(txtNombre.getText().toUpperCase());
            etapa_actual.setDescripcion(txtDescripcion.getText());
            gestor.modificar(etapa_actual);
            
            Mensajes.mensajeInformacion("La etapa de producción "+etapa_actual.getNombre()+"\n ha sido modificado exitosamente");
            this.vaciar();
            cancelar();
            etapa_actual=null;
            return;
        }
        if(operacion==Operacion.baja){
            etapa_actual.setFecBaja(new Date());
            etapa_actual.setMotivoBaja(txtMotivoBaja.getText());
            gestor.modificar(etapa_actual);

            Mensajes.mensajeInformacion("La etapa de producción "+etapa_actual.getNombre()+"\n ha sido dado de baja exitosamente");
            this.vaciar();
            etapa_actual=null;
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

        this.cancelar();
}//GEN-LAST:event_btnCancelarActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PantallaABMEtapaProduccion dialog = new PantallaABMEtapaProduccion(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAlta;
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
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

    private void cargarEtapasProduccion(){
        try {
            lstDisponible.removeAll();
            DefaultListModel modelo = new DefaultListModel();

            List<EstadoEtapaProduccion> tipo = GestorEtapaProduccion.listarTipoProducto();
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

    private void activarBotones(boolean nuevo, boolean modificar, boolean baja, boolean alta, boolean aceptar, boolean cancelar){
        this.btnNuevo.setEnabled(nuevo);
        this.btnModificar.setEnabled(modificar);
        this.btnBaja.setEnabled(baja);
        this.btnAceptar.setEnabled(aceptar);
        this.btnCancelar.setEnabled(cancelar);
        this.btnAlta.setEnabled(alta);
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
    private void cargarDatos(EstadoEtapaProduccion tipo){

        this.txtDescripcion.setText(tipo.getDescripcion());
        this.txtNombre.setText(tipo.getNombre());

        if(tipo.getFecBaja()==null)
            this.txtFechaBaja.setText("");
        else
        {
            Format formato=new SimpleDateFormat("dd/MM/yyyy");
            String fecha=formato.format(tipo.getFecBaja());
            this.txtFechaBaja.setText(fecha);
        }

        if(tipo.getMotivoBaja()==null)
            this.txtMotivoBaja.setText("");
        else
            this.txtMotivoBaja.setText(tipo.getMotivoBaja());


    }

    private void cancelar(){
         this.activarCargo(false);
       this.activarBaja(false);
       this.activarDisponible(true);
       this.activarBotones(true, true, true, false, false, true);
       this.vaciar();
    }

     public void nuevo() {
        // TODO add your handling code here:
        this.activarDisponible(false);
        this.activarBaja(false);
        this.activarCargo(true);
        this.activarBotones(false, false, false, false, true, true);
        this.operacion = Operacion.nuevo;
    }


}
