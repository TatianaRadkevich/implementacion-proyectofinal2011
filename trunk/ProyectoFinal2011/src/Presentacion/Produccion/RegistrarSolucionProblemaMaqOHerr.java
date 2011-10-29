/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegistrarSolucionProblemaMaqOHerr.java
 *
 * Created on 04/10/2011, 00:04:53
 */

package Presentacion.Produccion;

import BaseDeDatos.HibernateUtil;
import BaseDeDatos.Produccion.EstadoMaquinaBD;
import Presentacion.Utilidades;
import Negocio.Produccion.ProblemasMhp;
import BaseDeDatos.Produccion.ProblemasMhpBD;
import Negocio.Produccion.GestorProblemasMhp;
import Negocio.Produccion.MaquinaHerramientaParticular;
import Negocio.Produccion.TipoMaquinaHerramienta;
import Presentacion.IniciadorDeVentanas;
import Presentacion.Mensajes;
import Presentacion.TablaManager;
import com.toedter.calendar.JTextFieldDateEditor;
import gui.GUILocal;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
/**
 *
 * @author Heber Parrucci
 */
public class RegistrarSolucionProblemaMaqOHerr extends javax.swing.JDialog {

     private TablaManager<ProblemasMhp> tmProblemas;
     private GestorProblemasMhp gestor=new GestorProblemasMhp();
     private ProblemasMhp problema_actual= new ProblemasMhp();

    /** Creates new form RegistrarSolucionProblemaMaqOHerr */
    public RegistrarSolucionProblemaMaqOHerr(java.awt.Frame parent, boolean modal,int tipoMaq, int MaqHerrPart, boolean ban) {
        super(parent, modal);
        initComponents();
        HibernateUtil.getSessionFactory();
        inicializarTablas();
        if (!ban) rdbMaquina1.setSelected(true);
        cargarTiposMaqYHerr();
        if(tipoMaq!= -1)cmbTipoMaqHerr.setSelectedIndex(tipoMaq);
        
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
        lblMoHPart = new javax.swing.JLabel();
        cmbMaqHerrParticular = new javax.swing.JComboBox();
        cmbTipoMaqHerr = new javax.swing.JComboBox();
        lblTipoMoH = new javax.swing.JLabel();
        rdbHerramienta1 = new javax.swing.JRadioButton();
        rdbMaquina1 = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnRegistrarSolucion = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProbActuales = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Máquina/Herramienta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lblMoHPart.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblMoHPart.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMoHPart.setText("Herramienta particular:");

        cmbMaqHerrParticular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMaqHerrParticularActionPerformed(evt);
            }
        });

        cmbTipoMaqHerr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoMaqHerrActionPerformed(evt);
            }
        });

        lblTipoMoH.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblTipoMoH.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTipoMoH.setText("Tipo de Herramienta:");

        buttonGroup1.add(rdbHerramienta1);
        rdbHerramienta1.setFont(new java.awt.Font("Tahoma", 1, 11));
        rdbHerramienta1.setSelected(true);
        rdbHerramienta1.setText("Herramienta");
        rdbHerramienta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbHerramienta1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdbMaquina1);
        rdbMaquina1.setFont(new java.awt.Font("Tahoma", 1, 11));
        rdbMaquina1.setText("Máquina");
        rdbMaquina1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbMaquina1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Tipo:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTipoMoH)
                            .addComponent(lblMoHPart))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbMaqHerrParticular, 0, 195, Short.MAX_VALUE)
                            .addComponent(cmbTipoMaqHerr, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(rdbHerramienta1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdbMaquina1)))
                .addGap(500, 500, 500))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbHerramienta1)
                    .addComponent(rdbMaquina1)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoMoH)
                    .addComponent(cmbTipoMaqHerr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMoHPart)
                    .addComponent(cmbMaqHerrParticular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Problemas:");

        btnRegistrarSolucion.setText("Registrar Como Solucionado");
        btnRegistrarSolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarSolucionActionPerformed(evt);
            }
        });

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane2.setViewportView(txtObservaciones);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Observaciones:");

        tblProbActuales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProbActualesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblProbActuales);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRegistrarSolucion)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(btnRegistrarSolucion)
                .addGap(20, 20, 20))
        );

        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton3)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTipoMaqHerrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoMaqHerrActionPerformed
  if (cmbTipoMaqHerr.getSelectedIndex()!=-1)
            cargarMaqYHerrParticulares();
    }//GEN-LAST:event_cmbTipoMaqHerrActionPerformed

    private void btnRegistrarSolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarSolucionActionPerformed
        if(tmProblemas.getSelectedRow()!=-1){
        problema_actual = tmProblemas.getSeletedObject();
        problema_actual.setFecHoraRealSolucion(Utilidades.getFechaActual());

        if(txtObservaciones.getText().compareTo("")!=0)
        problema_actual.setObservacionesSolucion(txtObservaciones.getText());

        ProblemasMhpBD.modificar(problema_actual);
        Mensajes.mensajeInformacion("La solución ha sido registrada exitosamente");
        txtObservaciones.setText("");
        if (cmbMaqHerrParticular.getSelectedIndex()!=-1){
        tmProblemas.setDatos(ProblemasMhpBD.listarProblemasNoResueltos(((MaquinaHerramientaParticular)cmbMaqHerrParticular.getSelectedItem()).getId()));
        if (tmProblemas.getSize()==0){
        ((MaquinaHerramientaParticular)cmbMaqHerrParticular.getSelectedItem()).setEstadoMaquina(EstadoMaquinaBD.getEstadoDisponible());
        Mensajes.mensajeInformacion("La máquina está disponible");
        }
            }
        else
        {
        tmProblemas.limpiar();
        }
        }
        else
        {
            Mensajes.mensajeErrorGenerico("Debe seleccionar un problema");
        }

    }//GEN-LAST:event_btnRegistrarSolucionActionPerformed

    private void tblProbActualesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProbActualesMouseClicked
       if (tmProblemas.getSelectedRow()!=-1){
           txtObservaciones.setEnabled(true);
           txtObservaciones.setText("");
        }
    }//GEN-LAST:event_tblProbActualesMouseClicked

    private void cmbMaqHerrParticularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMaqHerrParticularActionPerformed
        if(cmbMaqHerrParticular.getSelectedIndex()!=-1)
        tmProblemas.setDatos(ProblemasMhpBD.listarProblemasNoResueltos(((MaquinaHerramientaParticular)cmbMaqHerrParticular.getSelectedItem()).getId()));
    }//GEN-LAST:event_cmbMaqHerrParticularActionPerformed

    private void rdbHerramienta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbHerramienta1ActionPerformed
        cargarTiposMaqYHerr();
        lblTipoMoH.setText("Tipo de Herramienta:");
        lblMoHPart.setText("Herramienta Particular:");
}//GEN-LAST:event_rdbHerramienta1ActionPerformed

    private void rdbMaquina1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbMaquina1ActionPerformed
        cargarTiposMaqYHerr();
        lblTipoMoH.setText("    Tipo de Máquina:");
        lblMoHPart.setText("    Máquina Particular:");
}//GEN-LAST:event_rdbMaquina1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RegistrarSolucionProblemaMaqOHerr dialog = new RegistrarSolucionProblemaMaqOHerr(new javax.swing.JFrame(), true,-1,-1,true);
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
    private javax.swing.JButton btnRegistrarSolucion;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbMaqHerrParticular;
    private javax.swing.JComboBox cmbTipoMaqHerr;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblMoHPart;
    private javax.swing.JLabel lblTipoMoH;
    private javax.swing.JRadioButton rdbHerramienta1;
    private javax.swing.JRadioButton rdbMaquina1;
    private javax.swing.JTable tblProbActuales;
    private javax.swing.JTextArea txtObservaciones;
    // End of variables declaration//GEN-END:variables

private void inicializarTablas() {

        tmProblemas = new TablaManager<ProblemasMhp>(tblProbActuales) {

            @Override
            public Vector getCabecera() {
                Vector cabecera = new Vector();

                cabecera.add("Código Máquina");
                cabecera.add("Nombre Máquina");
                cabecera.add("Descripción Problema");
                cabecera.add("Fecha Problema");
                return cabecera;

            }

          
            @Override
            public Vector ObjetoFila(ProblemasMhp elemento) {
                
                Vector fila = new Vector();

                fila.add(elemento.getTMaquinasHerramientaParticular().getId());
                fila.add(elemento.getTMaquinasHerramientaParticular().getNombre());
                fila.add(elemento.getDescripcion());
                fila.add(Utilidades.parseFecha(elemento.getFecHoraProblema()));

                return fila;
            }
        };
    }

    private void cargarMaqYHerrParticulares() {
        if (cmbTipoMaqHerr.getSelectedIndex()!=-1){
        cmbMaqHerrParticular.setModel(new DefaultComboBoxModel(gestor.getMaquinas((TipoMaquinaHerramienta) cmbTipoMaqHerr.getSelectedItem()).toArray()));
        if(cmbMaqHerrParticular.getSelectedIndex()!=-1){
        tmProblemas.setDatos(ProblemasMhpBD.listarProblemasNoResueltos(((MaquinaHerramientaParticular)cmbMaqHerrParticular.getSelectedItem()).getId()));
            }
        else
        {
        tmProblemas.limpiar();
        }

    }
    }
    

    private void cargarTiposMaqYHerr() {
        if (rdbMaquina1.isSelected())
         cmbTipoMaqHerr.setModel(new DefaultComboBoxModel(gestor.listarTipoMaq().toArray()));
         if(rdbHerramienta1.isSelected())
         cmbTipoMaqHerr.setModel(new DefaultComboBoxModel(gestor.listarTipoHerr().toArray()));
         if(cmbTipoMaqHerr.getSelectedIndex()!=-1)
         cargarMaqYHerrParticulares();
    }

    


}
