/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HorarioEmpleadoPresentacion.java
 *
 * Created on 24/01/2012, 14:51:34
 */

package Presentacion.Administracion;

import BaseDeDatos.Administracion.EmpleadoBD;
import BaseDeDatos.Administracion.HorarioBD;
import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.AsignacionesDias;
import Negocio.Administracion.Empleado;
import Negocio.Administracion.Horarios;
import Presentacion.TablaManager;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Gabriela
 */
public class HorarioEmpleadoPresentacion extends javax.swing.JFrame {
private TablaManager<Empleado> tablita;
private TablaManager<AsignacionesDias> tablita1;
    /** Creates new form HorarioEmpleadoPresentacion */
    public HorarioEmpleadoPresentacion() {




//        GUILocal.establecerGUILocal(this);
        initComponents();
        HibernateUtil.getSessionFactory();
        tablita = new TablaManager<Empleado>(tbEmpleado) {

            @Override
            public Vector getCabecera() {
                Vector cabcera = new Vector();
                cabcera.add("Legajo");//col 1
                cabcera.add("Nombre");//col 2
                cabcera.add("Apellido");//col 3
                
                return cabcera;


            }

            @Override
            public Vector ObjetoFila(Empleado elemento) {
                Vector fila = new Vector();
                fila.add(elemento.getId());
                fila.add(elemento.getNombre());//col 3
                fila.add(elemento.getApellido());//col 4

                return fila;
            }
        };
              tablita1 = new TablaManager<AsignacionesDias>(tbDias) {

            @Override
            public Vector getCabecera() {
                Vector cabcera = new Vector();
                cabcera.add("Día");//col 1
                cabcera.add("Hora desde");//col 2
                cabcera.add("Hora hasta");//col 3

                return cabcera;


            }

            @Override
            public Vector ObjetoFila(AsignacionesDias elemento) {
                Vector fila = new Vector();
                fila.add(elemento.getTDias());
                fila.add(elemento.getHoraDesde());//col 3
                fila.add(elemento.getHoraHasta());//col 4

                return fila;
            }
        };
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlEmpleados = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbEmpleado = new javax.swing.JTable();
        lblEmpleadosSHorario = new javax.swing.JLabel();
        lblEmpleadosCHorario = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        pnlHorarios = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        cnbHorario = new javax.swing.JComboBox();
        lblDias = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDias = new javax.swing.JTable();
        pnlFecha = new javax.swing.JPanel();
        lblFechaDesde = new javax.swing.JLabel();
        lblFechaHasta = new javax.swing.JLabel();
        txtFechaDesde = new javax.swing.JTextField();
        txtFechaHasta = new javax.swing.JTextField();
        btbAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlEmpleados.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleados"));

        tbEmpleado.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbEmpleado);

        lblEmpleadosSHorario.setText("Empleados sin horario vigente");

        lblEmpleadosCHorario.setText("Empleados con horario vigente");

        javax.swing.GroupLayout pnlEmpleadosLayout = new javax.swing.GroupLayout(pnlEmpleados);
        pnlEmpleados.setLayout(pnlEmpleadosLayout);
        pnlEmpleadosLayout.setHorizontalGroup(
            pnlEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmpleadosLayout.createSequentialGroup()
                .addGroup(pnlEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEmpleadosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEmpleadosLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(pnlEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEmpleadosSHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmpleadosCHorario))))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        pnlEmpleadosLayout.setVerticalGroup(
            pnlEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmpleadosLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblEmpleadosSHorario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEmpleadosCHorario)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        pnlHorarios.setBorder(javax.swing.BorderFactory.createTitledBorder("Horarios"));

        lblNombre.setText("Nombre:");

        cnbHorario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cnbHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnbHorarioActionPerformed(evt);
            }
        });

        lblDias.setText("Asignación días");

        tbDias.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbDias);

        javax.swing.GroupLayout pnlHorariosLayout = new javax.swing.GroupLayout(pnlHorarios);
        pnlHorarios.setLayout(pnlHorariosLayout);
        pnlHorariosLayout.setHorizontalGroup(
            pnlHorariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHorariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNombre)
                .addGap(18, 18, 18)
                .addComponent(cnbHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(340, Short.MAX_VALUE))
            .addGroup(pnlHorariosLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlHorariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDias))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        pnlHorariosLayout.setVerticalGroup(
            pnlHorariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHorariosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlHorariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(cnbHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblDias)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pnlFecha.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha vigencia"));

        lblFechaDesde.setText("Fecha desde:");

        lblFechaHasta.setText("Fecha hasta:");

        javax.swing.GroupLayout pnlFechaLayout = new javax.swing.GroupLayout(pnlFecha);
        pnlFecha.setLayout(pnlFechaLayout);
        pnlFechaLayout.setHorizontalGroup(
            pnlFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFechaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlFechaLayout.createSequentialGroup()
                        .addComponent(lblFechaDesde)
                        .addGap(18, 18, 18)
                        .addComponent(txtFechaDesde))
                    .addGroup(pnlFechaLayout.createSequentialGroup()
                        .addComponent(lblFechaHasta)
                        .addGap(18, 18, 18)
                        .addComponent(txtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(269, Short.MAX_VALUE))
        );
        pnlFechaLayout.setVerticalGroup(
            pnlFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFechaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaDesde)
                    .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaHasta)
                    .addComponent(txtFechaHasta))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        btbAceptar.setText("Aceptar");
        btbAceptar.setName("Aceptar"); // NOI18N
        btbAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");

        btnModificar.setText("Modificar");

        btnSalir.setText("Salir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlHorarios, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlEmpleados, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(68, 68, 68))
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(pnlFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(290, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(243, 243, 243)
                .addComponent(btbAceptar)
                .addGap(32, 32, 32)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(pnlEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlHorarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(pnlFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btbAceptar)
                            .addComponent(btnCancelar)
                            .addComponent(btnSalir)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(btnNuevo)
                        .addGap(27, 27, 27)
                        .addComponent(btnModificar)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
 this.lblEmpleadosCHorario.setVisible(false);
 this.lblEmpleadosSHorario.setVisible(true);
      //tablita.setDatos(EmpleadoBD.getEmpleadosSHorario());
      this.cargarHorarios();
      // TODO add your handling code here:
        
    }//GEN-LAST:event_btnNuevoActionPerformed


    private void cargarHorarios(){
            cnbHorario.removeAllItems();

            List<Horarios> tipo = HorarioBD.listarHorarios();
            for(int i=0;i<tipo.size();i++){
                cnbHorario.addItem(tipo.get(i));
            }
            cnbHorario.setSelectedIndex(-1);
            cnbHorario.repaint();
     }

    private void btbAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbAceptarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btbAceptarActionPerformed

    private void cnbHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnbHorarioActionPerformed
        // TODO add your handling code here:
        Horarios hora= (Horarios) cnbHorario.getSelectedItem();
        tablita1.setDatos(hora.getDiasAsignados());

    }//GEN-LAST:event_cnbHorarioActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HorarioEmpleadoPresentacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cnbHorario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDias;
    private javax.swing.JLabel lblEmpleadosCHorario;
    private javax.swing.JLabel lblEmpleadosSHorario;
    private javax.swing.JLabel lblFechaDesde;
    private javax.swing.JLabel lblFechaHasta;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JPanel pnlEmpleados;
    private javax.swing.JPanel pnlFecha;
    private javax.swing.JPanel pnlHorarios;
    private javax.swing.JTable tbDias;
    private javax.swing.JTable tbEmpleado;
    private javax.swing.JTextField txtFechaDesde;
    private javax.swing.JTextField txtFechaHasta;
    // End of variables declaration//GEN-END:variables

}
