/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaConsultarPedido.java
 *
 * Created on 27/06/2011, 15:24:35
 */
package Presentacion.Produccion;

import BaseDeDatos.Administracion.AsistenciaEmpleadoBD;
import BaseDeDatos.HibernateUtil;
import BaseDeDatos.Produccion.DetallePlanProduccionBD;
import BaseDeDatos.Produccion.EstadoDetallePlanBD;
import BaseDeDatos.Produccion.EstadoOrdenTrabajoBD;
import BaseDeDatos.Produccion.EstadoPlanBD;
import BaseDeDatos.Produccion.OrdenTrabajoBD;
import BaseDeDatos.Produccion.PlanProduccionBD;
import BaseDeDatos.Ventas.EstadoDetallePedidoBD;
import BaseDeDatos.Ventas.EstadoPedidoBD;
import Negocio.Administracion.Empleado;
import Negocio.Produccion.DetallePlanProduccion;
import Negocio.Produccion.GestorOrdenTrabajo;
import Negocio.Produccion.OrdenTrabajo;
import Negocio.Produccion.PlanProduccion;
import Presentacion.IniciadorDeVentanas;
import Presentacion.Mensajes;
import Presentacion.TablaManager;
import Presentacion.TomaDeDecisiones.ReporteOrdenTrabajo.ReporteOrdenTrabajo;
import Presentacion.Utilidades;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Rodrigo
 */
public class PantallaABMOrdenTrabajo extends javax.swing.JDialog {

    private TablaManager<PlanProduccion> tmPedido;
    private TablaManager<DetallePlanProduccion> tmEtapas;
    private GestorOrdenTrabajo gestor;

    /** Creates new form PantallaConsultarPedido */
    public PantallaABMOrdenTrabajo(java.awt.Frame parent, boolean modal, GestorOrdenTrabajo orden) throws ExceptionInInitializerError, ParseException {
        super(parent, modal);

        initComponents();
        HibernateUtil.getSessionFactory();
        inicializarTablas();
        IniciadorDeVentanas.iniciarVentana(this, this.getWidth(), this.getHeight());
        gestor = orden;
    }

    public PantallaABMOrdenTrabajo(JDialog parent, boolean modal, GestorOrdenTrabajo orden) throws ExceptionInInitializerError, ParseException {
        super(parent, modal);

        initComponents();
        HibernateUtil.getSessionFactory();
        inicializarTablas();
        IniciadorDeVentanas.iniciarVentana(this, this.getWidth(), this.getHeight());
        gestor = orden;
    }

    private void inicializarTablas() throws ExceptionInInitializerError, ParseException {
        tmPedido = new TablaManager<PlanProduccion>(tbPedidos) {

            @Override
            public Vector getCabecera() {
                Vector cabcera = new Vector();
                cabcera.add("Nro. plan");
                cabcera.add("Fecha/Hora inicio");
                cabcera.add("Fecha/Hora fin");
                return cabcera;
            }

            @Override
            public Vector ObjetoFila(PlanProduccion elemento) {
                Vector fila = new Vector();
                fila.add(elemento.getVersion() + " - " + elemento.getPedido().getIdPedido());
                fila.add(Utilidades.parseFechaHora(elemento.getFecHoraPrevistaInicio()));
                fila.add(Utilidades.parseFechaHora(elemento.getFecHoraPrevistaFin()));
                return fila;
            }
        };

        tmPedido.addSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                actualizarDetalles();
            }
        });

        tmEtapas = new TablaManager<DetallePlanProduccion>(tbEtapasPlanificadas) {

            @Override
            public Vector getCabecera() {
                Vector cabcera = new Vector();

                cabcera.add("Producto");
                cabcera.add("Nro. orden");
                cabcera.add("Etapa");
                cabcera.add("Empleado");
                cabcera.add("Máquina");
                cabcera.add("Fecha/Hora inicio");
                cabcera.add("Cantidad");
                return cabcera;
            }

            @Override
            public Vector ObjetoFila(DetallePlanProduccion elemento) {
                Vector fila = new Vector();
                fila.add(elemento.getEtapaProduccionEspecifica().getProducto().getNombre());
                fila.add(elemento.getEtapaProduccionEspecifica().getNumeroOrden());
                fila.add(elemento.getEtapaProduccionEspecifica().getEtapaProduccion().getNombre());
                fila.add(elemento.getEmpleado().getApellido() + ", " + elemento.getEmpleado().getNombre());
                fila.add(elemento.getMaquinaParticular().getNombre());
                fila.add(Utilidades.parseFechaHora(elemento.getFecHoraPrevistaInicio()));
                fila.add(elemento.getCantidad());
                return fila;
            }
        };

        this.actualizarPlanes();
    }

    public void actualizarPlanes() {
        List<PlanProduccion> planes = new ArrayList<PlanProduccion>();
        try {
            planes = PlanProduccionBD.listarPlanesPendientesActual();
        } catch (ExceptionInInitializerError ex) {
            Logger.getLogger(PantallaABMOrdenTrabajo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PantallaABMOrdenTrabajo.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.tmPedido.setDatos(planes);
    }

    public void actualizarDetalles() {
        if (tmPedido.getSeletedObject() != null) {
            List<DetallePlanProduccion> detalles = new ArrayList<DetallePlanProduccion>();
            for (DetallePlanProduccion dp : tmPedido.getSeletedObject().getDetallePlan()) {
                if (dp.getTEdetallePlan().getIdEdetallePlan() == 5 && dp.getTOrdenesTrabajo() == null) {
                    detalles.add(dp);
                }
            }
            tmEtapas.setDatos(detalles);
        } else {
            tmEtapas.limpiar();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPedidos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPedidos = new javax.swing.JTable();
        pnlDetalle = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbEtapasPlanificadas = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Orden de Trabajo");
        setModalExclusionType(null);
        setModalityType(null);

        pnlPedidos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Planes de producción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPedidosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPedidos);

        javax.swing.GroupLayout pnlPedidosLayout = new javax.swing.GroupLayout(pnlPedidos);
        pnlPedidos.setLayout(pnlPedidosLayout);
        pnlPedidosLayout.setHorizontalGroup(
            pnlPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedidosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlPedidosLayout.setVerticalGroup(
            pnlPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedidosLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle de planificación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbEtapasPlanificadas.setFont(new java.awt.Font("Tahoma", 1, 11));
        tbEtapasPlanificadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nro. Orden", "Etapa", "Maquina", "Empleado", "Fecha hora inicio"
            }
        ));
        tbEtapasPlanificadas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(tbEtapasPlanificadas);

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setText("Generar Orden Trabajo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPedidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        DetallePlanProduccion detallePlan = tmEtapas.getSeletedObject();
        if (detallePlan == null) {
            Mensajes.mensajeErrorGenerico("Debe seleccionar un detalle de plan");
            return;
        }

        if (detallePlan.getMaquinaParticular().getFecBaja() != null) {
            Mensajes.mensajeErrorGenerico("La máquina especificada en el detalle no se encuentra disponible, se colocará en estado 'Replanificar'");
            detallePlan.setTEdetallePlan(EstadoDetallePlanBD.getEstadoReplanificar());
            DetallePlanProduccionBD.guardar(detallePlan);
            this.actualizarDetalles();
            if (tmEtapas.getDatos().size() == 0) {
                this.actualizarPlanes();
            }

            return;
        }

        if (Mensajes.mensajeConfirmacionGenerico("¿Realmente desea generar esta orden de trabajo?") == false) {
            return;
        }

        Empleado empleado = detallePlan.getEmpleado();
        if (!AsistenciaEmpleadoBD.estaPresente(empleado)) {
            DialogoModificarEmpleado d = new DialogoModificarEmpleado(null, true);
            Utilidades.iniciarVentana(d);
            empleado = d.ejecutar();
        }


        if (empleado == null) {
            return;
        }

        Date fecha = Calendar.getInstance().getTime();

        detallePlan.setTEmpleados(empleado);

        OrdenTrabajo ot = new OrdenTrabajo();
        ot.setFecEmision(fecha);
        ot.setTEmpleados(empleado);
        ot.setTEordenTrabajo(EstadoOrdenTrabajoBD.traerEstadoGenerado());
        ot.addDetallePlan(detallePlan);
        ot = OrdenTrabajoBD.guardar(ot);

        detallePlan.setTOrdenesTrabajo(ot);
        detallePlan.setTEdetallePlan(EstadoDetallePlanBD.getEstadoPendiente());
        detallePlan.getTDetallesPedido().setEstadoDetallePedido(EstadoDetallePedidoBD.getEstadoProduccion());
        detallePlan.setCantidadProducida(0);
        DetallePlanProduccionBD.guardar(detallePlan);

        PlanProduccion plan = tmPedido.getSeletedObject();
        plan.setEstado(EstadoPlanBD.getEstadoIniciado());
        plan.getPedido().setEstadoPedido(EstadoPedidoBD.getEstadoProduccion());
        PlanProduccionBD.guardar(plan);

        Mensajes.mensajeInformacion("La orden de trabajo se generó exitosamente.\nNúmero de orden: " + ot.getIdOrdenTrabajo() + "\nEncargado: " + ot.getTEmpleados().getApellidoNombre());
        ReporteOrdenTrabajo reporteOT = new ReporteOrdenTrabajo();
        BigDecimal id = new BigDecimal(ot.getIdOrdenTrabajo());
        reporteOT.addParameter("id_orden_trabajo", id);
        reporteOT.runReporte();

        this.actualizarDetalles();
        if (tmEtapas.getDatos().isEmpty()) {
            this.actualizarPlanes();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPedidosMouseClicked
        // TODO add your handling code here:
        this.actualizarDetalles();
    }//GEN-LAST:event_tbPedidosMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                PantallaABMOrdenTrabajo dialog = null;
                try {
                    dialog = new PantallaABMOrdenTrabajo(new javax.swing.JFrame(), true, new GestorOrdenTrabajo());
                } catch (ExceptionInInitializerError ex) {
                    Logger.getLogger(PantallaABMOrdenTrabajo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(PantallaABMOrdenTrabajo.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlPedidos;
    private javax.swing.JTable tbEtapasPlanificadas;
    private javax.swing.JTable tbPedidos;
    // End of variables declaration//GEN-END:variables
}
