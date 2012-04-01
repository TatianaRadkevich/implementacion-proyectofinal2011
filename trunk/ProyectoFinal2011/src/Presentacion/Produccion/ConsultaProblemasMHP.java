/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConsultaProblemasMHP.java
 *
 * Created on 10-oct-2011, 20:56:42
 */

package Presentacion.Produccion;

import BaseDeDatos.HibernateUtil;
import Presentacion.Utilidades;
import Negocio.Produccion.ProblemasMhp;
import BaseDeDatos.Produccion.ProblemasMhpBD;
import Negocio.Produccion.GestorProblemasMhp;
import Negocio.Produccion.MaquinaParticular;
import Negocio.Produccion.TipoMaquina;
import Presentacion.IniciadorDeVentanas;
import Presentacion.Mensajes;
import Presentacion.TablaManager;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
/**
 *
 * @author Heber Parrucci
 */
public class ConsultaProblemasMHP extends javax.swing.JDialog {

     private TablaManager<ProblemasMhp> tmProblemas;
     private TablaManager<ProblemasMhp> tmProblemasResueltos;
     private GestorProblemasMhp gestor=new GestorProblemasMhp();
    /** Creates new form ConsultaProblemasMHP */
    public ConsultaProblemasMHP(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        HibernateUtil.getSessionFactory();
        inicializarTablas();
        inicializarTablasProblemasResueltos();
        cargarTiposMaqYHerr();
        cargarTodosProblemasHerramientas();
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
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHistorico = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dtcFechaGeneracionHasta = new com.toedter.calendar.JDateChooser();
        dtcFechaGeneracionDesde = new com.toedter.calendar.JDateChooser();
        cmbBuscar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProbActuales = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

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
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMoHPart)
                    .addComponent(lblTipoMoH))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbMaqHerrParticular, 0, 170, Short.MAX_VALUE)
                    .addComponent(cmbTipoMaqHerr, 0, 170, Short.MAX_VALUE))
                .addGap(970, 970, 970))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTipoMoH)
                    .addComponent(cmbTipoMaqHerr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMoHPart)
                    .addComponent(cmbMaqHerrParticular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Histórico de Problemas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jButton2.setText("Imprimir");

        tblHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblHistorico);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fechas Problema", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Desde:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Hasta:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(dtcFechaGeneracionDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(dtcFechaGeneracionHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(dtcFechaGeneracionDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(dtcFechaGeneracionHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        cmbBuscar.setText("Buscar");
        cmbBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(432, 432, 432)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbBuscar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1060, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBuscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jButton2)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Problemas Actuales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jButton1.setText("Imprimir");

        tblProbActuales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblProbActuales);

        jButton3.setText("Ir a Solución de Problemas");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1072, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCancelar.setText("Salir");
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(46, 46, 46))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(196, 196, 196)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(212, 212, 212))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTipoMaqHerrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoMaqHerrActionPerformed
        if (cmbTipoMaqHerr.getSelectedIndex()!=-1)
            cargarMaqYHerrParticulares();
    }//GEN-LAST:event_cmbTipoMaqHerrActionPerformed

    private void cmbMaqHerrParticularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMaqHerrParticularActionPerformed
        if(cmbMaqHerrParticular.getSelectedIndex()!=-1){
        tmProblemas.setDatos(ProblemasMhpBD.listarProblemasNoResueltos(((MaquinaParticular)cmbMaqHerrParticular.getSelectedItem()).getIdMaquinaParticular()));
        tmProblemasResueltos.setDatos(ProblemasMhpBD.listarProblemasResueltos(((MaquinaParticular)cmbMaqHerrParticular.getSelectedItem()).getIdMaquinaParticular()));
        }
    }//GEN-LAST:event_cmbMaqHerrParticularActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        RegistrarSolucionProblemaMaqOHerr pantalla=new RegistrarSolucionProblemaMaqOHerr(null, true,cmbTipoMaqHerr.getSelectedIndex(),cmbMaqHerrParticular.getSelectedIndex());
        pantalla.setVisible(true);
        if(cmbMaqHerrParticular.getSelectedIndex()!=-1){
        tmProblemas.setDatos(ProblemasMhpBD.listarProblemasNoResueltos(((MaquinaParticular)cmbMaqHerrParticular.getSelectedItem()).getIdMaquinaParticular()));
        tmProblemasResueltos.setDatos(ProblemasMhpBD.listarProblemasResueltos(((MaquinaParticular)cmbMaqHerrParticular.getSelectedItem()).getIdMaquinaParticular()));
        }
        else
        {

            
            
                cargarTodosProblemasMaquinas();
            
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
      dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cmbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBuscarActionPerformed

   if (validarFechas())
      cargarTodosProblemasMaquinas(dtcFechaGeneracionDesde.getDate(),dtcFechaGeneracionHasta.getDate());   
    }//GEN-LAST:event_cmbBuscarActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ConsultaProblemasMHP dialog = new ConsultaProblemasMHP(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cmbBuscar;
    private javax.swing.JComboBox cmbMaqHerrParticular;
    private javax.swing.JComboBox cmbTipoMaqHerr;
    private com.toedter.calendar.JDateChooser dtcFechaGeneracionDesde;
    private com.toedter.calendar.JDateChooser dtcFechaGeneracionHasta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblMoHPart;
    private javax.swing.JLabel lblTipoMoH;
    private javax.swing.JTable tblHistorico;
    private javax.swing.JTable tblProbActuales;
    // End of variables declaration//GEN-END:variables

      private void inicializarTablasProblemasResueltos() {
            tmProblemasResueltos = new TablaManager<ProblemasMhp>(tblHistorico) {

            @Override
            public Vector getCabecera() {
                Vector cabecera = new Vector();

                cabecera.add("Código Máquina");
                cabecera.add("Nombre Máquina");
                cabecera.add("Descripción Problema");
                cabecera.add("Observaciones Solución");
                cabecera.add("Fecha Problema");
                cabecera.add("Fecha Real Solución");
                return cabecera;

            }


            @Override
            public Vector ObjetoFila(ProblemasMhp elemento) {

                Vector fila = new Vector();

                fila.add(elemento.getTMaquinasParticular().getIdMaquinaParticular());
                fila.add(elemento.getTMaquinasParticular().getNombre());
                fila.add(elemento.getDescripcion());
                fila.add(elemento.getObservacionesSolucion());
                fila.add(Utilidades.parseFecha(elemento.getFecHoraProblema()));
                fila.add(Utilidades.parseFecha(elemento.getFecHoraRealSolucion()));

                return fila;
            }
        };
    }

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

                fila.add(elemento.getTMaquinasParticular().getIdMaquinaParticular());
                fila.add(elemento.getTMaquinasParticular().getNombre());
                fila.add(elemento.getDescripcion());
                fila.add(Utilidades.parseFecha(elemento.getFecHoraProblema()));
                

                return fila;
            }
        };
    }

    private void cargarTiposMaqYHerr() {
        
         cmbTipoMaqHerr.setModel(new DefaultComboBoxModel(gestor.listarTipoMaq().toArray()));
       
        
         cmbTipoMaqHerr.setSelectedIndex(-1);
         cmbMaqHerrParticular.setSelectedIndex(-1);
         
    }

    private void cargarMaqYHerrParticulares() {
        if (cmbTipoMaqHerr.getSelectedIndex()!=-1)
        cmbMaqHerrParticular.setModel(new DefaultComboBoxModel(gestor.getMaquinas((TipoMaquina) cmbTipoMaqHerr.getSelectedItem()).toArray()));
        if(cmbMaqHerrParticular.getSelectedIndex()!=-1){
        tmProblemas.setDatos(ProblemasMhpBD.listarProblemasNoResueltos(((MaquinaParticular)cmbMaqHerrParticular.getSelectedItem()).getIdMaquinaParticular()));
        tmProblemasResueltos.setDatos(ProblemasMhpBD.listarProblemasResueltos(((MaquinaParticular)cmbMaqHerrParticular.getSelectedItem()).getIdMaquinaParticular()));
        }
        else
        {
        tmProblemas.limpiar();
        tmProblemasResueltos.limpiar();
        }
    }

    private void cargarTodosProblemasHerramientas() {
        tmProblemas.setDatos(ProblemasMhpBD.listarProblemasNoResueltos(((MaquinaParticular)cmbMaqHerrParticular.getSelectedItem()).getIdMaquinaParticular()));
        tmProblemasResueltos.setDatos(ProblemasMhpBD.listarProblemasResueltos(((MaquinaParticular)cmbMaqHerrParticular.getSelectedItem()).getIdMaquinaParticular()));
    }

    private void cargarTodosProblemasHerramientas(Date fechaDesde, Date fechaHasta) {
        tmProblemasResueltos.setDatos(ProblemasMhpBD.listarProblemasNoResueltosConFecha(fechaDesde,fechaHasta));
    }

    private void cargarTodosProblemasMaquinas() {
        tmProblemas.setDatos(ProblemasMhpBD.listarProblemasNoResueltos(((MaquinaParticular)cmbMaqHerrParticular.getSelectedItem()).getIdMaquinaParticular()));
        tmProblemasResueltos.setDatos(ProblemasMhpBD.listarProblemasResueltos(((MaquinaParticular)cmbMaqHerrParticular.getSelectedItem()).getIdMaquinaParticular()));
    }

    private void cargarTodosProblemasMaquinas(Date fechaDesde, Date fechaHasta) {
        tmProblemasResueltos.setDatos(ProblemasMhpBD.listarProblemasNoResueltosConFecha(fechaDesde,fechaHasta));
    }

    private boolean validarFechas() {
        if (dtcFechaGeneracionDesde.getDate()==null){
            Mensajes.mensajeErrorGenerico("Debe seleccionar la fecha desde");
            return false;
        }
        if (dtcFechaGeneracionHasta.getDate()==null){
            Mensajes.mensajeErrorGenerico("Debe seleccionar la fecha hasta");
            return false;
        }
        return true;
    }
}
