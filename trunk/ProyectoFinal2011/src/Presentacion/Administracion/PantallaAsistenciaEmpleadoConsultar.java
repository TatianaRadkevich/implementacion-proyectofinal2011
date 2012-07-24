/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConsultarAsistenciaEmpleado.java
 *
 * Created on 24/01/2012, 15:45:00
 */
package Presentacion.Administracion;

import BaseDeDatos.Administracion.AsistenciaEmpleadoBD;
import BaseDeDatos.Administracion.EmpleadoBD;
import Negocio.Administracion.AsignacionesDias;
import Negocio.Administracion.AsistenciaEmpleado;
import Negocio.Administracion.Empleado;
import Negocio.Administracion.Horarios;
import Presentacion.TablaManager;
import Presentacion.Utilidades;
import Presentacion.ValidarTexbox;
import com.toedter.calendar.JTextFieldDateEditor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Sebastian
 */
public class PantallaAsistenciaEmpleadoConsultar extends javax.swing.JDialog {

    private TablaManager<AsistenciaEmpleado> tmAsistencia;
    private TablaManager<Empleado> tmEmpleado;

    /** Creates new form ConsultarAsistenciaEmpleado */
    public PantallaAsistenciaEmpleadoConsultar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializarTablas();
        cargarValidaciones();
    }

     private void cargarValidaciones() {
        //**********************Validacion de textBox********************//
        ValidarTexbox.validarInt(txtLegajo);
        ValidarTexbox.validarLongitud(txtLegajo, 5);
        ValidarTexbox.validarLongitud(txtNombre,100);
        ValidarTexbox.validarLongitud(txtApellido,100);

        //**********************Validacion de Fecha********************//
        ((JTextFieldDateEditor) dtcFechaHasta.getDateEditor()).setEditable(false);
        ((JTextFieldDateEditor) dtcFechaDesde.getDateEditor()).setEditable(false);

        dtcFechaHasta.setMaxSelectableDate(Utilidades.getFechaActual());
        dtcFechaDesde.setMaxSelectableDate(Utilidades.getFechaActual());

        dtcFechaHasta.setDate(Utilidades.getFechaActual());
        dtcFechaDesde.setDate(Utilidades.agregarTiempoFecha(Utilidades.getFechaActual(), 0, -1, 0));

        //**********************Validacion de Fecha parte2********************//

        dtcFechaHasta.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtcFechaDesde.setMaxSelectableDate(dtcFechaHasta.getDate());
                cargarAsistenciaHorario();
            }
        });

        dtcFechaDesde.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtcFechaHasta.setMinSelectableDate(dtcFechaDesde.getDate());
                cargarAsistenciaHorario();
            }
        });

    }

    private void inicializarTablas() {
        tmAsistencia = new TablaManager<AsistenciaEmpleado>(tbAsistencia) {

            @Override
            public Vector ObjetoFila(AsistenciaEmpleado elemento) {
                Vector salida = new Vector();
                salida.add(Utilidades.parseFecha(elemento.getFecAsistencia()));
                salida.add(elemento.getHoraIngreso());
                salida.add(elemento.getHoraEgreso());
                return salida;
            }

            @Override
            public Vector getCabecera() {
                Vector salida = new Vector();
                salida.add("Fecha");
                salida.add("Hora Ingreso");
                salida.add("Hora Egreso");
                return salida;
            }
        };
        tmAsistencia.addSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                cargarObservaciones(tmAsistencia.getSeletedObject());
            }
        });

        tmEmpleado = new TablaManager<Empleado>(tbEmpledos) {

            @Override
            public Vector ObjetoFila(Empleado elemento) {
                Vector salida = new Vector();
                salida.add(elemento.getId());
                salida.add(elemento.getNombre());
                salida.add(elemento.getApellido());

                return salida;
            }

            @Override
            public Vector getCabecera() {
                Vector salida = new Vector();
                salida.add("Legajo");
                salida.add("Nombre");
                salida.add("Apellido");
                return salida;
            }
        };
        tmEmpleado.addSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                cargarAsistenciaHorario();
            }
        });       
    }

    private void cargarAsistenciaHorario() {
        limpiarAsistenciaHorario();

        if(tmEmpleado.getSeletedObject()==null)
            return;
        
        Empleado emp=tmEmpleado.getSeletedObject();
        Date desde=dtcFechaDesde.getDate();
        Date hasta=dtcFechaHasta.getDate();
        if (emp == null)
             return;

        List<AsistenciaEmpleado> resultado=AsistenciaEmpleadoBD.getAsistencia(emp.getId()+"", desde, hasta);
        tmAsistencia.setDatos(resultado); 
    }

    private void limpiarAsistenciaHorario() {
        tmAsistencia.limpiar();
        limpiarObservaciones();     
    }

    private void limpiarObservaciones() {
        txtObsEgreso.setText("");
        txtObsIngreso.setText("");
    }

    private void cargarObservaciones(AsistenciaEmpleado ae) {
        limpiarObservaciones();
        if (ae == null)            
            return;
        
        txtObsEgreso.setText(ae.getObservEgreso());
        txtObsIngreso.setText(ae.getObservIngreso());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBuscar = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtLegajo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbEmpledos = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        pnlClientes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAsistencia = new javax.swing.JTable();
        pnlObservaciones = new javax.swing.JPanel();
        scrlPnlEgreso = new javax.swing.JScrollPane();
        txtObsEgreso = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        scrlPnlIngreso = new javax.swing.JScrollPane();
        txtObsIngreso = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        dtcFechaHasta = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dtcFechaDesde = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consultar Asistencias de Empleados");

        pnlBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Empleados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Búsqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Legajo:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Apellido:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtLegajo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLegajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultados (seleccione el empleado que desea consultar)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbEmpledos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Legajo", "Nombre", "Apellido"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbEmpledos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlBuscarLayout = new javax.swing.GroupLayout(pnlBuscar);
        pnlBuscar.setLayout(pnlBuscarLayout);
        pnlBuscarLayout.setHorizontalGroup(
            pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBuscarLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlBuscarLayout.setVerticalGroup(
            pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBuscarLayout.createSequentialGroup()
                .addGroup(pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnModificar.setText("Salir");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        pnlClientes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Asistencias", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbAsistencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Hora Ingreso", "Hora Egreso"
            }
        ));
        jScrollPane1.setViewportView(tbAsistencia);

        pnlObservaciones.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Obsevaciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        txtObsEgreso.setColumns(20);
        txtObsEgreso.setEditable(false);
        txtObsEgreso.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtObsEgreso.setLineWrap(true);
        txtObsEgreso.setWrapStyleWord(true);
        scrlPnlEgreso.setViewportView(txtObsEgreso);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("Egreso:");

        txtObsIngreso.setColumns(20);
        txtObsIngreso.setEditable(false);
        txtObsIngreso.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtObsIngreso.setLineWrap(true);
        txtObsIngreso.setWrapStyleWord(true);
        scrlPnlIngreso.setViewportView(txtObsIngreso);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Ingreso:");

        javax.swing.GroupLayout pnlObservacionesLayout = new javax.swing.GroupLayout(pnlObservaciones);
        pnlObservaciones.setLayout(pnlObservacionesLayout);
        pnlObservacionesLayout.setHorizontalGroup(
            pnlObservacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlObservacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlObservacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrlPnlEgreso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(scrlPnlIngreso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        pnlObservacionesLayout.setVerticalGroup(
            pnlObservacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlObservacionesLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrlPnlIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrlPnlEgreso, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Fecha Hasta:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Fecha Desde:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(dtcFechaDesde, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtcFechaHasta, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(jLabel2)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(dtcFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jLabel5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(dtcFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout pnlClientesLayout = new javax.swing.GroupLayout(pnlClientes);
        pnlClientes.setLayout(pnlClientesLayout);
        pnlClientesLayout.setHorizontalGroup(
            pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(pnlObservaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlClientesLayout.setVerticalGroup(
            pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlObservaciones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlClientesLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlClientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnModificar)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("Consultar Asistencia Empleado");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:

        List<Empleado> resultado = EmpleadoBD.getEmpleados(
                txtLegajo.getText(),
                txtNombre.getText(),
                txtApellido.getText(),
                true, false);
        tmEmpleado.setDatos(resultado);

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        this.dispose();
}//GEN-LAST:event_btnModificarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                PantallaAsistenciaEmpleadoConsultar dialog = new PantallaAsistenciaEmpleadoConsultar(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnModificar;
    private com.toedter.calendar.JDateChooser dtcFechaDesde;
    private com.toedter.calendar.JDateChooser dtcFechaHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlBuscar;
    private javax.swing.JPanel pnlClientes;
    private javax.swing.JPanel pnlObservaciones;
    private javax.swing.JScrollPane scrlPnlEgreso;
    private javax.swing.JScrollPane scrlPnlIngreso;
    private javax.swing.JTable tbAsistencia;
    private javax.swing.JTable tbEmpledos;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtLegajo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextArea txtObsEgreso;
    private javax.swing.JTextArea txtObsIngreso;
    // End of variables declaration//GEN-END:variables
}
