/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaMaquinaHerramientaTipoABM.java
 *
 * Created on 22/08/2011, 13:09:19
 */
package Presentacion.Produccion;

import Negocio.Exceptiones.ExceptionGestor;
import Negocio.Produccion.GestorTipoMaquina;
import Negocio.Produccion.GestorTipoMaquina;
import Negocio.Produccion.TipoMaquina;
import Presentacion.Mensajes;
import Presentacion.Operacion;
import Presentacion.Utilidades;
import Presentacion.ValidarTexbox;
import gui.GUILocal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Rodrigo
 */
public class PantallaTipoMaquinaABM extends javax.swing.JDialog {

    private GestorTipoMaquina gestor;
    private int operacion;
    private TipoMaquina maquina_actual=null;
   

    /** Creates new form PantallaMaquinaHerramientaTipoABM */
    public PantallaTipoMaquinaABM(GestorTipoMaquina g) {
        super((java.awt.Frame) null, true);
        initComponents();
        gestor = g;
        //GUILocal.establecerGUILocal(this);
        habilitarCampos(false);
        habilitarConfirmacion(false);
        habilitarBaja(false, null, null);
        cargarValidaciones();
        setLocationRelativeTo(null);
        lstDisponible.setModel(new DefaultListModel());            
        lstDisponible.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (lstDisponible.getSelectedIndex() >= 0) {
                    cargar((TipoMaquina) lstDisponible.getSelectedValue());
                } else {
                    limpiar();
                }
            }
        });
        cargarLista();
    }

    public void cargarValidaciones() {
        ValidarTexbox.validarLongitud(txtNombre, 50);
        ValidarTexbox.validarLongitud(txtDescripcion, 200);
        ValidarTexbox.validarLongitud(txtFechaBaja, 200);

        /************************Validacion de botones **********************************/

        btnAlta.setEnabled(false);
        btnModificar.setEnabled(false);
        btnBaja.setEnabled(false);
        lstDisponible.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                
                if (lstDisponible.getSelectedIndex() >= 0)
                {
                    TipoMaquina Seleccionado = (TipoMaquina) lstDisponible.getSelectedValue();
                    if (Seleccionado.getFecBaja() == null) {
                        btnBaja.setEnabled(true);
                        btnModificar.setEnabled(true);
                        btnAlta.setEnabled(false);
                    } else {
                        btnBaja.setEnabled(false);
                        btnModificar.setEnabled(false);
                        btnAlta.setEnabled(true);
                    }
                }
                else
                {
                      btnBaja.setEnabled(false);
                        btnModificar.setEnabled(false);
                        btnAlta.setEnabled(false);
                }
                
            }
        });
    }

    public void cargarLista() {
        DefaultListModel model = (DefaultListModel) lstDisponible.getModel();
        model.removeAllElements();
        for (TipoMaquina tmh : gestor.listarTipoMaquina()) {
            model.addElement(tmh);
        }
        lstDisponible.setModel(model);

    }

    public void limpiar() {
        txtNombre.setText("");
        txtDescripcion.setText("");
//        rdbHerramienta.setSelected(true);
        habilitarBaja(false, null, "");
    }

    public void cargar(TipoMaquina tmh) {
        txtNombre.setText(tmh.getNombre());
        txtDescripcion.setText(tmh.getDescripcion());
//        rdbHerramienta.setSelected(tmh.isEsHerramienta());
//        rdbMaquina.setSelected(!tmh.isEsHerramienta());
        txtFechaBaja.setText(Utilidades.parseFecha(tmh.getFecBaja()));
        txtMotivoBaja.setText(Utilidades.parseString(tmh.getMotivoBaja()));
    }

    public void habilitarBaja(boolean editable, Date fecha, String motivo) {
        txtMotivoBaja.setEnabled(editable);
        scrollBaja.setEnabled(editable);
        txtFechaBaja.setText(Utilidades.parseFecha(fecha));
        txtMotivoBaja.setText(Utilidades.parseString(motivo));
    }

    public void habilitarConfirmacion(boolean b) {
        btnSalir.setEnabled(!b);
        btnModificar.setEnabled(!b);
        btnNuevo.setEnabled(!b);
        btnBaja.setEnabled(!b);
        btnAlta.setEnabled(!b);
        lstDisponible.setEnabled(!b);

        btnAceptar.setEnabled(b);
        btnCancelar.setEnabled(b);
    }

    public void habilitarCampos(boolean b) {



        txtNombre.setEnabled(b);
        txtDescripcion.setEnabled(b);
//        rdbHerramienta.setEnabled(b);
//        rdbMaquina.setEnabled(b);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgTipo = new javax.swing.ButtonGroup();
        pnlDisponible = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstDisponible = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();
        btnAlta = new javax.swing.JButton();
        pnlCargo = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlBaja = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtFechaBaja = new javax.swing.JTextField();
        scrollBaja = new javax.swing.JScrollPane();
        txtMotivoBaja = new javax.swing.JTextArea();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Tipo Máquina");

        pnlDisponible.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Disponible", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lstDisponible.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstDisponibleMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstDisponible);

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

        btnBaja.setText("Eliminar");
        btnBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajaActionPerformed(evt);
            }
        });

        btnAlta.setText("Alta");
        btnAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnModificar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                    .addComponent(btnBaja, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                    .addComponent(btnAlta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBaja)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlta)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlDisponibleLayout = new javax.swing.GroupLayout(pnlDisponible);
        pnlDisponible.setLayout(pnlDisponibleLayout);
        pnlDisponibleLayout.setHorizontalGroup(
            pnlDisponibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDisponibleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlDisponibleLayout.setVerticalGroup(
            pnlDisponibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDisponibleLayout.createSequentialGroup()
                .addGroup(pnlDisponibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlCargo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo Máquina", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Nombre:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Descripción:");

        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtDescripcion);

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

        pnlBaja.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Eliminación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Fecha:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setText("Motivo:");

        txtFechaBaja.setEditable(false);

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
                    .addComponent(jLabel4)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollBaja))
                .addContainerGap())
        );
        pnlBajaLayout.setVerticalGroup(
            pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBajaLayout.createSequentialGroup()
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(scrollBaja, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlCargoLayout = new javax.swing.GroupLayout(pnlCargo);
        pnlCargo.setLayout(pnlCargoLayout);
        pnlCargoLayout.setHorizontalGroup(
            pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCargoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCargoLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
                    .addGroup(pnlCargoLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
                    .addComponent(pnlBaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCargoLayout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );
        pnlCargoLayout.setVerticalGroup(
            pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCargoLayout.createSequentialGroup()
                .addGroup(pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pnlBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap(17, Short.MAX_VALUE))
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
                        .addComponent(pnlCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlDisponible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlCargo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDisponible, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnSalir)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed
        // TODO add your handling code here:           
         if(lstDisponible.getSelectedIndex()==-1){
            Mensajes.mensajeErrorGenerico("Debe seleccionar un tipo de maquina que desea eliminar");
            return;
        }

        this.activarDisponible(false);
        maquina_actual=(TipoMaquina) lstDisponible.getSelectedValue();
        this.cargarDatos(maquina_actual);
        Format formato=new SimpleDateFormat("dd/MM/yyyy");
        String fecha=formato.format(new Date());
        this.txtFechaBaja.setText(fecha);
        this.activarBaja(true);
        this.activarTipoProducto(false);
        this.activarDisponible(false);
        this.activarBotones(false, false, false, true, true,false,false);
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
        this.activarTipoProducto(true);
        this.activarBotones(false, false, false, true, true,false,false);

        maquina_actual=(TipoMaquina) lstDisponible.getSelectedValue();
        this.cargarDatos(maquina_actual);
        this.operacion=Operacion.modificar;
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
         if(validar()){

        }
      if(operacion==Operacion.nuevo){
            TipoMaquina tipo=new TipoMaquina();
            tipo.setNombre(txtNombre.getText().toUpperCase());           
            tipo.setDescripcion(txtDescripcion.getText());

            gestor.guardar(tipo);
            Mensajes.mensajeInformacion("El tipo de producto "+tipo.getNombre()+"\n ha sido guardado exitosamente");
            this.cargarTipoProductos();
            cancelar();
            this.lstDisponible.setSelectedIndex(-1);
            return;
        }

        if(operacion==Operacion.modificar){
            maquina_actual.setNombre(txtNombre.getText().toUpperCase());
            maquina_actual.setDescripcion(txtDescripcion.getText());
            gestor.modificar(maquina_actual);
            Mensajes.mensajeInformacion("El tipo de producto "+maquina_actual.getNombre()+"\n ha sido modificado exitosamente");
              maquina_actual=null;
            cancelar();
            this.lstDisponible.setSelectedIndex(-1);
            return;
        }
         if(operacion==Operacion.baja){
             maquina_actual.setFecBaja(new Date());
            maquina_actual.setMotivoBaja(txtMotivoBaja.getText());
            gestor.modificar(maquina_actual);
            Mensajes.mensajeInformacion("El tipo de producto "+maquina_actual.getNombre()+"\n ha sido dado de baja exitosamente");

            maquina_actual=null;
            this.cancelar();
            this.lstDisponible.setSelectedIndex(-1);
            return;
        }
        if(operacion==Operacion.reactivar){
            maquina_actual.setFecBaja(null);
            maquina_actual.setMotivoBaja(null);
            gestor.modificar(maquina_actual);
            cancelar();
            this.lstDisponible.setSelectedIndex(-1);
            Mensajes.mensajeInformacion("El tipo de producto "+maquina_actual.getNombre()+"\n ha sido dado reactivado exitosamente");
        }
        this.cancelar();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
         this.maquina_actual = null;
        this.cancelar();
}//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        this.nuevo();
        
}//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltaActionPerformed
        // TODO add your handling code here:
        this.activarDisponible(false);
        this.maquina_actual=(TipoMaquina) lstDisponible.getSelectedValue();
        this.activarBaja(false);
        this.activarTipoProducto(false);
        this.activarBotones(false, false, false, true, true, false,false);
        this.txtFechaBaja.setText("");
        this.txtMotivoBaja.setText("");
        this.operacion=Operacion.reactivar;
    }//GEN-LAST:event_btnAltaActionPerformed

    private void lstDisponibleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstDisponibleMouseClicked
        // TODO add your handling code here:
         TipoMaquina temp= (TipoMaquina) lstDisponible.getSelectedValue();
        this.cargarDatos(temp);
        if(temp.getFecBaja()!=null){
            this.activarBaja(false);
            this.activarDisponible(true);
            this.activarTipoProducto(false);
            this.activarBotones(true, false, false, false, false, true,true);

        }
        else{
            this.activarBaja(false);
            this.activarDisponible(true);
            this.activarTipoProducto(false);
            this.activarBotones(true, true, true, false, false, false,true);
        }
    }//GEN-LAST:event_lstDisponibleMouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//            new GestorTipoMaquina().administar();
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgTipo;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAlta;
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lstDisponible;
    private javax.swing.JPanel pnlBaja;
    private javax.swing.JPanel pnlCargo;
    private javax.swing.JPanel pnlDisponible;
    private javax.swing.JScrollPane scrollBaja;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtFechaBaja;
    private javax.swing.JTextArea txtMotivoBaja;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

public void nuevo() {
        // TODO add your handling code here:

        this.activarDisponible(false);
        this.activarDisponible(false);
        this.activarBaja(false);
        this.activarTipoProducto(true);
        this.activarBotones(false, false, false, true, true,false,false);
        this.operacion = Operacion.nuevo;
        this.txtNombre.requestFocus();
    }
private void activarDisponible(boolean  flag)
    {
        this.lstDisponible.setEnabled(flag);
    }

private void activarTipoProducto(boolean flag){
        this.txtNombre.setEnabled(flag);
        this.txtDescripcion.setEnabled(flag);
    }
    private void activarBaja(boolean flag){
        this.txtFechaBaja.setEnabled(false);
        this.txtMotivoBaja.setEnabled(flag);
    }

    private void activarBotones(boolean nuevo, boolean modificar, boolean baja, boolean aceptar, boolean cancelar, boolean reactivar,boolean salir){
        this.btnNuevo.setEnabled(nuevo);
        this.btnModificar.setEnabled(modificar);
        this.btnBaja.setEnabled(baja);
        this.btnAceptar.setEnabled(aceptar);
        this.btnCancelar.setEnabled(cancelar);
        this.btnAlta.setEnabled(reactivar);
        this.btnSalir.setEnabled(salir);

    }

    private void cargarDatos(TipoMaquina tipo){
       
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
       this.activarTipoProducto(false);
       this.activarBaja(false);
       this.activarDisponible(true);
       this.activarBotones(true, false, false,  false, false,false,true);
       this.vaciar();
    }

    private void vaciar(){
        this.txtDescripcion.setText("");
        this.txtNombre.setText("");
        this.txtFechaBaja.setText("");
        this.txtMotivoBaja.setText("");
       this.lstDisponible.setSelectedIndex(-1);

    }

     private void cargarTipoProductos(){
        try {
            lstDisponible.removeAll();
            DefaultListModel modelo = new DefaultListModel();

            List<TipoMaquina> tipo = gestor.listarTipoMaquina();
            for(int i=0;i<tipo.size();i++){
                modelo.addElement(tipo.get(i));

            lstDisponible.setModel(modelo);
            lstDisponible.setSelectedIndex(-1);


            }
        } catch (Exception ex) {
            Logger.getLogger(PantallaABMProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      private boolean validar(){
        return true;
    }

}
