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
import Negocio.Produccion.MaquinaParticular;
import Negocio.Produccion.TipoMaquina;
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
    public RegistrarSolucionProblemaMaqOHerr(java.awt.Frame parent, boolean modal,int tipoMaq, int MaqHerrPart) {
        super(parent, modal);
        initComponents();
        HibernateUtil.getSessionFactory();
        inicializarTablas();
        cargarTiposMaqYHerr();
        if(tipoMaq!= -1)cmbTipoMaqHerr.setSelectedIndex(tipoMaq);
        
        IniciadorDeVentanas.iniciarVentana(this, this.getWidth(), this.getHeight());
        this.cargarTabla();
    }

    public RegistrarSolucionProblemaMaqOHerr(java.awt.Frame parent, boolean modal){
        super(parent, modal);
        initComponents();
        HibernateUtil.getSessionFactory();
        inicializarTablas();
        cargarTiposMaqYHerr();


        IniciadorDeVentanas.iniciarVentana(this, this.getWidth(), this.getHeight());
        this.cargarTabla();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnRegistrarSolucion = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProbActuales = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtFechaActual = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Máquina", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lblMoHPart.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblMoHPart.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMoHPart.setText("Máquina particular:");

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
        lblTipoMoH.setText("Tipo de Máquina:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTipoMoH)
                    .addComponent(lblMoHPart))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbMaqHerrParticular, 0, 195, Short.MAX_VALUE)
                    .addComponent(cmbTipoMaqHerr, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(576, 576, 576))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoMoH)
                    .addComponent(cmbTipoMaqHerr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMoHPart)
                    .addComponent(cmbMaqHerrParticular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Problemas"));

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
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(685, Short.MAX_VALUE)
                .addComponent(btnRegistrarSolucion)
                .addGap(70, 70, 70))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(112, 112, 112)
                        .addComponent(btnRegistrarSolucion))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Fecha Actual:");

        txtFechaActual.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFechaActual, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton3)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarSolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarSolucionActionPerformed
        if(tmProblemas.getSelectedRow()!=-1){
        problema_actual = tmProblemas.getSeletedObject();
        problema_actual.setFecHoraRealSolucion(Utilidades.getFechaActual());

        if(txtObservaciones.getText().compareTo("")!=0)
        problema_actual.setObservacionesSolucion(txtObservaciones.getText());

        gestor.registrarSolucion(problema_actual);
        Mensajes.mensajeInformacion("La solución ha sido registrada exitosamente");
        txtObservaciones.setText("");

              if (cmbMaqHerrParticular.getSelectedIndex()!=-1){
            tmProblemas.setDatos(ProblemasMhpBD.listarProblemasNoResueltos(((MaquinaParticular)cmbMaqHerrParticular.getSelectedItem()).getIdMaquinaParticular()));
                if (tmProblemas.getSize()==0){
                ((MaquinaParticular)cmbMaqHerrParticular.getSelectedItem()).setTEmaquina(EstadoMaquinaBD.getEstadoDisponible());
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cmbTipoMaqHerrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoMaqHerrActionPerformed
        if (cmbTipoMaqHerr.getSelectedIndex()!=-1)
            cargarMaqYHerrParticulares();
}//GEN-LAST:event_cmbTipoMaqHerrActionPerformed

    private void cmbMaqHerrParticularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMaqHerrParticularActionPerformed
        if(cmbMaqHerrParticular.getSelectedIndex()!=-1)
            tmProblemas.setDatos(ProblemasMhpBD.listarProblemasNoResueltos(((MaquinaParticular)cmbMaqHerrParticular.getSelectedItem()).getIdMaquinaParticular()));
}//GEN-LAST:event_cmbMaqHerrParticularActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RegistrarSolucionProblemaMaqOHerr dialog = new RegistrarSolucionProblemaMaqOHerr(new javax.swing.JFrame(), true,-1,-1);
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblMoHPart;
    private javax.swing.JLabel lblTipoMoH;
    private javax.swing.JTable tblProbActuales;
    private javax.swing.JTextField txtFechaActual;
    private javax.swing.JTextArea txtObservaciones;
    // End of variables declaration//GEN-END:variables

private void inicializarTablas() {

        tmProblemas = new TablaManager<ProblemasMhp>(tblProbActuales) {

            @Override
            public Vector getCabecera() {
                Vector cabecera = new Vector();

                cabecera.add("Tipo problema");
                cabecera.add("Nombre");
                cabecera.add("Modelo");                
                cabecera.add("Descripción Problema");
                cabecera.add("Fecha Problema");
                return cabecera;

            }

          
            @Override
            public Vector ObjetoFila(ProblemasMhp elemento) {
                
                Vector fila = new Vector();

                fila.add(elemento.tipoProblema());
                fila.add(elemento.nombre());
                fila.add(elemento.modelo());
                fila.add(elemento.getDescripcion());
                fila.add(Utilidades.parseFecha(elemento.getFecHoraProblema()));

                return fila;
            }
        };
    }

    private void cargarMaqYHerrParticulares() {
//        if (cmbTipoMaqHerr.getSelectedIndex()!=-1){
//        cmbMaqHerrParticular.setModel(new DefaultComboBoxModel(gestor.getMaquinas((TipoMaquina) cmbTipoMaqHerr.getSelectedItem()).toArray()));
//        if(cmbMaqHerrParticular.getSelectedIndex()!=-1){
//        tmProblemas.setDatos(ProblemasMhpBD.listarProblemasNoResueltos(((MaquinaParticular)cmbMaqHerrParticular.getSelectedItem()).getIdMaquinaParticular()));
//            }
//        else
//        {
//        tmProblemas.limpiar();
//        }
//
//        }
    }
    

    private void cargarTiposMaqYHerr() {
       
         cmbTipoMaqHerr.setModel(new DefaultComboBoxModel(gestor.listarTipoMaq().toArray()));
        
         if(cmbTipoMaqHerr.getSelectedIndex()!=-1)
         cargarMaqYHerrParticulares();
    }

    

    public void cargarTabla()
    {
            tmProblemas.setDatos(ProblemasMhpBD.listarProblemasNoResueltos());
    }
}
