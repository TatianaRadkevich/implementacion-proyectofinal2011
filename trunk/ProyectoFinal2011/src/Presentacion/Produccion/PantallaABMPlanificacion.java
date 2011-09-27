/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaABMPlanificacion.java
 *
 * Created on 11/09/2011, 18:10:55
 */
package Presentacion.Produccion;

import BaseDeDatos.Administracion.EmpleadoBD;
import BaseDeDatos.HibernateUtil;
import BaseDeDatos.Produccion.MaquinaHerramientaBD;
import BaseDeDatos.Ventas.PedidoBD;
import Negocio.Administracion.Empleado;
import Negocio.Produccion.DetalleEtapaProduccion;
import Negocio.Produccion.DetallePlanProduccion;
import Negocio.Produccion.EtapaProduccionEspecifica;
import Negocio.Produccion.MaquinaHerramientaParticular;
import Negocio.Produccion.PlanProduccion;
import Negocio.Produccion.TipoMaquinaHerramienta;
import Negocio.Ventas.DetallePedido;
import Negocio.Ventas.Pedido;
import Presentacion.Mensajes;
import Presentacion.TablaManager;
import Presentacion.Utilidades;
import Presentacion.ValidarTexbox;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Ivan
 */
public class PantallaABMPlanificacion extends javax.swing.JDialog {

    /** Creates new form PantallaABMPlanificacion */
    private Pedido pedido;
    private TablaManager<DetallePedido> tmDetallePedido;
    private TablaManager<EtapaProduccionEspecifica> tmEstructura;
    private TablaManager<DetallePlanProduccion> tmDetallePlanProduccion;
    private PlanProduccion plan;
    private boolean modificarDetPlan = false;

    private PantallaABMPlanificacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public PantallaABMPlanificacion(Pedido p) {
        this(null, true);
        pedido = p;
        cargarCombos();
        inicializarTablas();
        habilitarDatosPlanificacion(false);
        cargarDatosPedido(p);


        if (p.getPlanProduccion()!=null) {
            plan = p.getPlanProduccion();
            cargarDatosPlan(plan);
        } else {
            plan = new PlanProduccion(p);
        }

        ValidarTexbox.desabilitarEdicion(((DefaultEditor) fhInicioDetallePlan.getEditor()).getTextField());
        ValidarTexbox.desabilitarEdicion(((DefaultEditor) fhInicioPlan.getEditor()).getTextField());



    }

    private void inicializarTablas() {
        tmDetallePedido = new TablaManager<DetallePedido>(tbDetallePedido) {

            @Override
            public Vector ObjetoFila(DetallePedido elemento) {
                Vector fila = new Vector();
                fila.add(elemento.getProducto().getTTproducto().getNombre());
                fila.add(elemento.getProducto().getNombre());
                fila.add(elemento.getCantidad());

                return fila;
            }

            @Override
            public Vector getCabecera() {
                Vector cabecera = new Vector();
                cabecera.add("Tipo Producto");
                cabecera.add("Producto");
                cabecera.add("Cantidad");
                return cabecera;
            }
        };
        tmDetallePedido.addSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {

                if (tmDetallePedido.getSeletedObject() != null) {
                    cargarEstructura(tmDetallePedido.getSeletedObject());
                }

            }
        });

        /***************************************************************************/
        tmEstructura = new TablaManager<EtapaProduccionEspecifica>(tbEstructura) {

            @Override
            public Vector ObjetoFila(EtapaProduccionEspecifica elemento) {
                Vector fila = new Vector();
                fila.add(elemento.getNumeroOrden());
                fila.add(elemento.getEtapaProduccion().getNombre());
                fila.add(elemento.getDuracion());

                String tipoMaquina = "";
                for (DetalleEtapaProduccion dep : elemento.getDetalleEtapaProduccion()) {
                    if (dep.getTipoMaquinaHerramienta() != null) {
                        tipoMaquina = dep.getTipoMaquinaHerramienta().getNombre();
                    }
                }
                fila.add(tipoMaquina);

                fila.add(elemento.getCargo().getNombre());

                return fila;
            }

            @Override
            public Vector getCabecera() {
                Vector cabecera = new Vector();
                cabecera.add("Nro Oreden");
                cabecera.add("Etapa");
                cabecera.add("Duración");
                cabecera.add("Tipo maquina");
                cabecera.add("Cargo empleado");
                return cabecera;
            }
        };

        /******************************************************************************/
        tmDetallePlanProduccion = new TablaManager<DetallePlanProduccion>(tbEtapasPlanificadas) {

            @Override
            public Vector ObjetoFila(DetallePlanProduccion elemento) {
                Vector fila = new Vector();
                fila.add(elemento.getTEtapasProduccionEspecifica().getNumeroOrden());
                fila.add(elemento.getTEtapasProduccionEspecifica().getEtapaProduccion().getNombre());
                fila.add((elemento.getTMaquinasHerramientaParticular() == null) ? "" : elemento.getTMaquinasHerramientaParticular().getNombre());
                fila.add((elemento.getTEmpleados() == null) ? "" : elemento.getTEmpleados().getNombre());
                fila.add((elemento.getFecHoraPrevistaInicio() == null) ? "" : Utilidades.parseFecha(elemento.getFecHoraPrevistaInicio()));
                return fila;
            }

            @Override
            public Vector getCabecera() {
                Vector cabecera = new Vector();
                cabecera.add("Nro. Orden");
                cabecera.add("Etapa");
                cabecera.add("Maquina");
                cabecera.add("Empleado");
                cabecera.add("Fecha/Hora inicio");

                return cabecera;
            }
        };
        tmDetallePlanProduccion.addSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (tmDetallePlanProduccion.getSeletedObject() != null) {
                    cargarDatosEtapaPlanificacion(tmDetallePlanProduccion.getSeletedObject());
                    btnEliminar.setEnabled(true);
                    btnModificar.setEnabled(true);
                }
                else
                {
                    limpiarDatosEtapaPlanificacion();
                      btnEliminar.setEnabled(false);
                    btnModificar.setEnabled(false);
                }
            }
        });
    }

    private void cargarCombos() {
        cmbResponsable.removeAllItems();
        List<Empleado> emp = EmpleadoBD.listarEmpleado();
        for (int i = 0; i < emp.size(); i++) {
            cmbResponsable.addItem(emp.get(i));
        }

        cmbResponsable.repaint();
    }

    public void cargarDatosPedido(Pedido p) {
        lblRazonSocial.setText(p.getCliente().getRazonSocial());
        lblFechaNecesidad.setText(Utilidades.parseFecha(p.getFechaNecesidad()));
        lblFechaEntrega.setText(Utilidades.parseFecha(p.getFechaEstimadaEntrega()));
        lblTipoPedido.setText(p.getTipoPedido().getNombre());
        tmDetallePedido.setDatos(p.getDetallePedido());

    }

    public void cargarDatosPlan(PlanProduccion plan) {
        cmbResponsable.setSelectedItem(plan.getEmpleado());
        fhInicioPlan.setDate((plan.getFecHoraPrevistaInicio() == null) ? Utilidades.getFechaActual() : plan.getFecHoraPrevistaInicio());
    }

    private void cargarEstructura(DetallePedido dp) {

        List<EtapaProduccionEspecifica> etapas = dp.getProducto().getEtapasProduccionEspecificas();
        List<DetallePlanProduccion> etapasPlanificadas = plan.getDetallePlan(dp.getProducto());

        for (EtapaProduccionEspecifica epe : dp.getProducto().getEtapasProduccionEspecificas()) {
            for (DetallePlanProduccion dpp : etapasPlanificadas) {
                if (dpp.getTEtapasProduccionEspecifica().getId() == epe.getId()) {
                    etapas.remove(epe);
                }
            }
        }
        tmEstructura.setDatos(etapas);
        tmDetallePlanProduccion.setDatos(etapasPlanificadas);
    }

    private void habilitarDatosPlanificacion(boolean valor) {
        tbEtapasPlanificadas.setEnabled(!valor);      

        fhInicioDetallePlan.setEnabled(valor);
        cmbMaquina.setEnabled(valor);
        cmbOperario.setEnabled(valor);
        txtObservaciones.setEditable(valor);
        btnAceptar.setEnabled(valor); //cambió el nombre de componente
        btnCancelar.setEnabled(valor); //cambió el nombre de componente
    }

    public boolean validarDatosDetalle() {
        if (fhInicioDetallePlan.getDate() == null) {
            Mensajes.mensajeErrorGenerico("Debe ingresar la fecha de inicio del detalle de plan");
            fhInicioDetallePlan.requestFocus();
            return false;
        }

        if (cmbMaquina.getSelectedIndex() == -1) {
            Mensajes.mensajeErrorGenerico("Debe seleccionar una máquina");
            cmbMaquina.requestFocus();
            return false;
        }

        if (cmbOperario.getSelectedIndex() == -1) {
            Mensajes.mensajeErrorGenerico("Debe seleccionar un operario");
            cmbOperario.requestFocus();
            return false;
        }
        return true;
    }

    private void limpiarDatosEtapaPlanificacion() {
        fhInicioDetallePlan.setDate(Utilidades.getFechaActual());
        cmbMaquina.setSelectedIndex(-1);
        cmbOperario.setSelectedIndex(-1);
        txtObservaciones.setText("");
    }

    private void cargarDatosEtapaPlanificacion(DetallePlanProduccion detalle) {
        if (detalle == null) {
            limpiarDatosEtapaPlanificacion();
            return;
        }

        fhInicioDetallePlan.setDate((detalle.getFecHoraPrevistaInicio() == null) ? Utilidades.getFechaActual() : detalle.getFecHoraPrevistaInicio());
        TipoMaquinaHerramienta tipoMaq = null;
        for (DetalleEtapaProduccion det : detalle.getTEtapasProduccionEspecifica().getDetalleEtapaProduccion()) {
            if (det.getTipoMaquinaHerramienta() != null) {
                tipoMaq = det.getTipoMaquinaHerramienta();
            }
        }

        cmbMaquina.setModel(
                new DefaultComboBoxModel(
                MaquinaHerramientaBD.getMaquinasHerramientas(
                tipoMaq, true, false).toArray()));

        cmbMaquina.setSelectedItem(detalle.getTMaquinasHerramientaParticular());

        cmbOperario.setModel(
                new DefaultComboBoxModel(EmpleadoBD.getEmpleados(detalle.getTEtapasProduccionEspecifica().getCargo(), true, false).toArray()));

        //cmbOperario.setSelectedItem(detalle.getTEmpleados());
        txtObservaciones.setText(detalle.getObservaciones());
    }

    private void insertDetallePlan(DetallePlanProduccion det) {
        int i = 0;
        for (DetallePlanProduccion dep : tmDetallePlanProduccion.getDatos()) {
            if (dep.getTEtapasProduccionEspecifica().getNumeroOrden() >= det.getTEtapasProduccionEspecifica().getNumeroOrden()) {
                tmDetallePlanProduccion.insert(i, det);
                return;
            }
            i++;
        }
        tmDetallePlanProduccion.add(det);
    }

    private void insertEtapa(EtapaProduccionEspecifica epe) {
        int i = 0;
        for (EtapaProduccionEspecifica dep : tmEstructura.getDatos()) {
            if (dep.getNumeroOrden() >= epe.getNumeroOrden()) {
                tmEstructura.insert(i, epe);
                return;
            }
            i++;
        }
        tmEstructura.add(epe);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cmbResponsable = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        fhInicioPlan = new com.toedter.calendar.JSpinnerDateEditor();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblRazonSocial = new javax.swing.JLabel();
        lblFechaNecesidad = new javax.swing.JLabel();
        lblTipoPedido = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labefdsfsf = new javax.swing.JLabel();
        lblFechaEntrega = new javax.swing.JLabel();
        pnlDetallePlanificacion = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbEtapasPlanificadas = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        pnlDetallePlanificaion = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        fhInicioDetallePlan = new com.toedter.calendar.JSpinnerDateEditor();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cmbMaquina = new javax.swing.JComboBox();
        cmbOperario = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        pnlEstructura = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbEstructura = new javax.swing.JTable();
        btnPlanificar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDetallePedido = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Planificación de producción");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Planificacion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel15.setText("Responsable de Plan:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel16.setText("Fecha/hora calculada inicio Plan:");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pedido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Razón social cliente:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Tipo pedido:");

        lblRazonSocial.setText("[razon social]");

        lblFechaNecesidad.setText("[Fecha necesidad]");

        lblTipoPedido.setText("[tipo pedido]");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Fecha necesidad:");

        labefdsfsf.setFont(new java.awt.Font("Tahoma", 1, 11));
        labefdsfsf.setText("Fecha entrega material:");

        lblFechaEntrega.setText("[fecha entrga]");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labefdsfsf)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRazonSocial, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(lblTipoPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(lblFechaEntrega, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(lblFechaNecesidad, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblRazonSocial))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblFechaNecesidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblTipoPedido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labefdsfsf)
                    .addComponent(lblFechaEntrega)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fhInicioPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cmbResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(fhInicioPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlDetallePlanificacion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Etapas planificadas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

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

        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDetallePlanificacionLayout = new javax.swing.GroupLayout(pnlDetallePlanificacion);
        pnlDetallePlanificacion.setLayout(pnlDetallePlanificacionLayout);
        pnlDetallePlanificacionLayout.setHorizontalGroup(
            pnlDetallePlanificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetallePlanificacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlDetallePlanificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDetallePlanificacionLayout.setVerticalGroup(
            pnlDetallePlanificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetallePlanificacionLayout.createSequentialGroup()
                .addGroup(pnlDetallePlanificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDetallePlanificacionLayout.createSequentialGroup()
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                .addContainerGap())
        );

        jButton3.setText("Cancelar planificación");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Generar planificación");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        pnlDetallePlanificaion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Planificación de etapa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel14.setText("Fecha/hora inicio:");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setText("Máquina asignada:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setText("Empleado asignado:");

        txtObservaciones.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtObservaciones);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setText("Observaciones:");

        javax.swing.GroupLayout pnlDetallePlanificaionLayout = new javax.swing.GroupLayout(pnlDetallePlanificaion);
        pnlDetallePlanificaion.setLayout(pnlDetallePlanificaionLayout);
        pnlDetallePlanificaionLayout.setHorizontalGroup(
            pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetallePlanificaionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetallePlanificaionLayout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addGap(10, 10, 10)
                        .addComponent(btnCancelar))
                    .addGroup(pnlDetallePlanificaionLayout.createSequentialGroup()
                        .addGroup(pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(pnlDetallePlanificaionLayout.createSequentialGroup()
                                .addGroup(pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cmbOperario, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbMaquina, javax.swing.GroupLayout.Alignment.LEADING, 0, 187, Short.MAX_VALUE)
                                    .addComponent(fhInicioDetallePlan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        pnlDetallePlanificaionLayout.setVerticalGroup(
            pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetallePlanificaionLayout.createSequentialGroup()
                .addGroup(pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fhInicioDetallePlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(14, 14, 14)
                .addGroup(pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbOperario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar)))
        );

        pnlEstructura.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Etapas a planificar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbEstructura.setFont(new java.awt.Font("Tahoma", 1, 11));
        tbEstructura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro. Orden", "Etapa", "Duración", "Tipo maquina", "Cargo empleado"
            }
        ));
        tbEstructura.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbEstructura.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbEstructuraPropertyChange(evt);
            }
        });
        jScrollPane3.setViewportView(tbEstructura);

        btnPlanificar.setText("Planificar");
        btnPlanificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlanificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEstructuraLayout = new javax.swing.GroupLayout(pnlEstructura);
        pnlEstructura.setLayout(pnlEstructuraLayout);
        pnlEstructuraLayout.setHorizontalGroup(
            pnlEstructuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEstructuraLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPlanificar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlEstructuraLayout.setVerticalGroup(
            pnlEstructuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
            .addGroup(pnlEstructuraLayout.createSequentialGroup()
                .addComponent(btnPlanificar)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Producción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbDetallePedido.setFont(new java.awt.Font("Tahoma", 1, 11));
        tbDetallePedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo producto", "Producto", "Cantidad"
            }
        ));
        tbDetallePedido.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tbDetallePedido);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEstructura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlEstructura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlDetallePlanificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlDetallePlanificaion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDetallePlanificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDetallePlanificaion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbEstructuraPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbEstructuraPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tbEstructuraPropertyChange

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        //validarDatosDetalle();

        DetallePlanProduccion det = (modificarDetPlan)
                ? tmDetallePlanProduccion.getSeletedObject() : new DetallePlanProduccion();

        det.setFecHoraPrevistaInicio(fhInicioDetallePlan.getDate());
        det.setObservaciones(txtObservaciones.getText());
        det.setTEmpleados((Empleado) cmbOperario.getSelectedItem());
        det.setTMaquinasHerramientaParticular((MaquinaHerramientaParticular) cmbMaquina.getSelectedItem());

        if (modificarDetPlan == false) {
            det.setTEtapasProduccionEspecifica(tmEstructura.removeSelectedRow());
            insertDetallePlan(det);
            plan.addDetallePlan(det);

        }


        habilitarDatosPlanificacion(false);
        limpiarDatosEtapaPlanificacion();

}//GEN-LAST:event_btnAceptarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
//        PlanProduccion plan = new PlanProduccion();
//        plan.setDetallePlan(tmDetallePlanProduccion.getDatos());
//        plan.setPedido(pedido);
//        plan.setEmpleado((Empleado) cmbResponsable.getSelectedItem());
//        plan.setFecHoraPrevistaInicio(fhInicioPlan.getDate());
        HibernateUtil.guardarObjeto(plan);


        Mensajes.mensajeInformacion("Se ha generado el plan correctamente");
        this.dispose();

}//GEN-LAST:event_jButton4ActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        habilitarDatosPlanificacion(true);
        modificarDetPlan = true;

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:

        limpiarDatosEtapaPlanificacion();
        habilitarDatosPlanificacion(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if (tmDetallePlanProduccion.getSeletedObject() == null) {
            return;
        }
        if (Mensajes.mensajeConfirmacionGenerico("¿Realmente desea eliminar esta planificación?")) {
            DetallePlanProduccion dpp = tmDetallePlanProduccion.removeSelectedRow();
            plan.removeDetallePlan(dpp);
            EtapaProduccionEspecifica epe = dpp.getTEtapasProduccionEspecifica();
            epe.removeDetallePlanProduccion(dpp);
            insertEtapa(epe);
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnPlanificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlanificarActionPerformed
        // TODO add your handling code here:
        if (tmEstructura.getSeletedObject() == null) {
            return;
        }

        // limpiarDatosEtapaPlanificacion();
        cargarDatosEtapaPlanificacion(new DetallePlanProduccion(tmEstructura.getSeletedObject()));
        habilitarDatosPlanificacion(true);

        modificarDetPlan = false;

    }//GEN-LAST:event_btnPlanificarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                new PantallaABMPlanificacion(PedidoBD.getPedido(1)).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnPlanificar;
    private javax.swing.JComboBox cmbMaquina;
    private javax.swing.JComboBox cmbOperario;
    private javax.swing.JComboBox cmbResponsable;
    private com.toedter.calendar.JSpinnerDateEditor fhInicioDetallePlan;
    private com.toedter.calendar.JSpinnerDateEditor fhInicioPlan;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labefdsfsf;
    private javax.swing.JLabel lblFechaEntrega;
    private javax.swing.JLabel lblFechaNecesidad;
    private javax.swing.JLabel lblRazonSocial;
    private javax.swing.JLabel lblTipoPedido;
    private javax.swing.JPanel pnlDetallePlanificacion;
    private javax.swing.JPanel pnlDetallePlanificaion;
    private javax.swing.JPanel pnlEstructura;
    private javax.swing.JTable tbDetallePedido;
    private javax.swing.JTable tbEstructura;
    private javax.swing.JTable tbEtapasPlanificadas;
    private javax.swing.JTextArea txtObservaciones;
    // End of variables declaration//GEN-END:variables
}
