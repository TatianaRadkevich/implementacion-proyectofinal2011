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
import Negocio.Produccion.GestorTipoMaquinaHerramienta;
import Negocio.Produccion.GestorTipoMaquinaHerramientaAlta;
import Negocio.Produccion.GestorTipoMaquinaHerramientaBaja;
import Negocio.Produccion.GestorTipoMaquinaHerramientaModificar;
import Negocio.Produccion.TipoMaquinaHerramienta;
import Presentacion.Mensajes;
import Presentacion.ValidarTexbox;
import gui.GUILocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Rodrigo
 */
public class PantallaMaquinaHerramientaTipoABM extends javax.swing.JDialog {

    private GestorTipoMaquinaHerramienta gestor;

    /** Creates new form PantallaMaquinaHerramientaTipoABM */
    public PantallaMaquinaHerramientaTipoABM() {
        super((java.awt.Frame)null, true);
        initComponents();
        //GUILocal.establecerGUILocal(this);
        lstDisponible.setModel(new DefaultListModel());
        cargarValidaciones();
        gestor=new GestorTipoMaquinaHerramienta();
        lstDisponible.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (lstDisponible.getSelectedIndex() >= 0) 
                    cargar((TipoMaquinaHerramienta) lstDisponible.getSelectedValue());
                else
                    limpiar();
            }
        });
        cargarLista();
    }

    public void cargarValidaciones() {
        ValidarTexbox.validarLongitud(txtNombre, 50);
        ValidarTexbox.validarLongitud(txtDescripcion, 200);
        habilitarCarga(false);
        /************************Validacion de botones **********************************/
        btnBaja.setEnabled(false);
        btnModificar.setEnabled(false);
        lstDisponible.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                boolean var = false;
                if (lstDisponible.getSelectedIndex() >= 0) {
                    var = true;
                }

                btnBaja.setEnabled(var);
                btnModificar.setEnabled(var);
            }
        });
    }

    public void cargarLista()
    {
        DefaultListModel model=(DefaultListModel) lstDisponible.getModel();        
        model.removeAllElements();
        for(TipoMaquinaHerramienta tmh:gestor.listarTipoMaquinaHerramienta())
            model.addElement(tmh);
        lstDisponible.setModel(model);
        
    }

    public void limpiar() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        rdbHerramienta.setSelected(true);        
    }

    public void cargar(TipoMaquinaHerramienta tmh) {
        txtNombre.setText(tmh.getNombre());
        txtDescripcion.setText(tmh.getDescripcion());
        rdbHerramienta.setSelected(tmh.isEsHerramienta());
        rdbMaquina.setSelected(!tmh.isEsHerramienta());
    }

    public void habilitarCarga(boolean b) {

        btnSalir.setEnabled(!b);
        btnModificar.setEnabled(!b);
        btnNuevo.setEnabled(!b);
        btnBaja.setEnabled(!b);
        lstDisponible.setEnabled(!b);

        btnAceptar.setEnabled(b);
        btnCancelar.setEnabled(b);

        txtNombre.setEnabled(b);
        txtDescripcion.setEnabled(b);
        rdbHerramienta.setEnabled(b);
        rdbMaquina.setEnabled(b);
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
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();
        pnlCargo = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        rdbHerramienta = new javax.swing.JRadioButton();
        rdbMaquina = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Maquina/Herramienta");

        pnlDisponible.setBorder(javax.swing.BorderFactory.createTitledBorder("Disponible"));

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

        btnBaja.setText("Baja");
        btnBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDisponibleLayout = new javax.swing.GroupLayout(pnlDisponible);
        pnlDisponible.setLayout(pnlDisponibleLayout);
        pnlDisponibleLayout.setHorizontalGroup(
            pnlDisponibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDisponibleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(pnlDisponibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDisponibleLayout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDisponibleLayout.createSequentialGroup()
                        .addComponent(btnModificar)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDisponibleLayout.createSequentialGroup()
                        .addComponent(btnBaja)
                        .addGap(24, 24, 24))))
        );
        pnlDisponibleLayout.setVerticalGroup(
            pnlDisponibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDisponibleLayout.createSequentialGroup()
                .addComponent(btnNuevo)
                .addGap(18, 18, 18)
                .addComponent(btnModificar)
                .addGap(18, 18, 18)
                .addComponent(btnBaja)
                .addGap(222, 222, 222))
            .addGroup(pnlDisponibleLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlCargo.setBorder(javax.swing.BorderFactory.createTitledBorder("Mquina/Herramienta"));

        jLabel1.setText("Nombre:");

        jLabel2.setText("Descripción:");

        jLabel3.setText("Tipo:");

        btgTipo.add(rdbHerramienta);
        rdbHerramienta.setSelected(true);
        rdbHerramienta.setText("Herramienta");

        btgTipo.add(rdbMaquina);
        rdbMaquina.setText("Maquina");

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

        javax.swing.GroupLayout pnlCargoLayout = new javax.swing.GroupLayout(pnlCargo);
        pnlCargo.setLayout(pnlCargoLayout);
        pnlCargoLayout.setHorizontalGroup(
            pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCargoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCargoLayout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar))
                    .addGroup(pnlCargoLayout.createSequentialGroup()
                        .addGroup(pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(pnlCargoLayout.createSequentialGroup()
                                .addComponent(rdbHerramienta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdbMaquina))
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlCargoLayout.setVerticalGroup(
            pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCargoLayout.createSequentialGroup()
                .addGroup(pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rdbHerramienta)
                    .addComponent(rdbMaquina))
                .addGap(18, 18, 18)
                .addGroup(pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDisponible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCargo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed
        // TODO add your handling code here:

        gestor = new GestorTipoMaquinaHerramientaBaja(this, (TipoMaquinaHerramienta) lstDisponible.getSelectedValue());
        gestor.iniciarCU();

}//GEN-LAST:event_btnBajaActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
      gestor=new GestorTipoMaquinaHerramientaModificar(this,(TipoMaquinaHerramienta) lstDisponible.getSelectedValue());
        gestor.iniciarCU();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        TipoMaquinaHerramienta tipo = gestor.getTipoMaquinaHerramienta();

        tipo.setNombre(txtNombre.getText());
        tipo.setDescripcion(txtDescripcion.getText());
        tipo.setEsHerramienta(rdbHerramienta.isSelected());

        try {
            gestor.ejecutarCU(tipo);
            Mensajes.mensajeGuardoCorrectamente();
            cargarLista();
        } catch (ExceptionGestor ex) {
            Mensajes.mensajeErrorGenerico(ex.getMessage());
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        gestor.finalizarCU();
}//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        gestor = new GestorTipoMaquinaHerramientaAlta(this);
        gestor.iniciarCU();
}//GEN-LAST:event_btnNuevoActionPerformed

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
                PantallaMaquinaHerramientaTipoABM dialog = new PantallaMaquinaHerramientaTipoABM();
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
    private javax.swing.ButtonGroup btgTipo;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lstDisponible;
    private javax.swing.JPanel pnlCargo;
    private javax.swing.JPanel pnlDisponible;
    private javax.swing.JRadioButton rdbHerramienta;
    private javax.swing.JRadioButton rdbMaquina;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
