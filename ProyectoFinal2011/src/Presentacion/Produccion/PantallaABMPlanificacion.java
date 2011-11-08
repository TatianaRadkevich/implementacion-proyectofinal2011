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
import BaseDeDatos.Produccion.EstadoDetallePlanBD;
import BaseDeDatos.Produccion.EstadoPlanBD;
import BaseDeDatos.Produccion.MaquinaHerramientaBD;
import BaseDeDatos.Ventas.EstadoPedidoBD;
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
import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Ivan
 */
public class PantallaABMPlanificacion extends javax.swing.JDialog {

    /** Creates new form PantallaABMPlanificacion */
    private Pedido pedido;
    private TablaManager<DetallePedido> tmDetallePedido;
    private TablaManager<EtapaProduccionEspecifica> tmEstructura;
    private TablaManager<MaquinaHerramientaParticular> tmMaquina;
    private TablaManager<Empleado> tmEmpleado;
    private TablaManager<DetalleEtapaProduccion> tmHerramientas;
    private PlanProduccion plan;

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


        if (p.getPlanProduccion() != null) {
            plan = p.getPlanProduccion();
            cargarDatosPlan(plan);
        } else {
            plan = new PlanProduccion(p);
        }

        // ValidarTexbox.desabilitarEdicion(((DefaultEditor) fhInicioDetallePlan.getEditor()).getTextField());
        ValidarTexbox.desabilitarEdicion(((DefaultEditor) fhInicioPlan.getEditor()).getTextField());
        fhInicioPlan.setDate(Utilidades.getFechaActual());



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
                    tmEstructura.setDatos(tmDetallePedido.getSeletedObject().getProducto().getEtapasProduccionEspecificas());
                }

            }
        });

        /***************************************************************************/
        tmEstructura = new TablaManager<EtapaProduccionEspecifica>(tbEstructura) {

            @Override
            public Vector ObjetoFila(EtapaProduccionEspecifica elemento) {
                Vector fila = new Vector();

                fila.add((plan.getDetallePlan(elemento) == null) ? "Sin Planificar" : "Planificado");
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
                cabecera.add("Estado");
                cabecera.add("Orden");
                cabecera.add("Etapa");
                cabecera.add("Duración");
                cabecera.add("Tipo maquina");
                cabecera.add("Cargo empleado");
                return cabecera;
            }
        };
        tmEstructura.addSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {

                if (tmEstructura.getSeletedObject() != null) {
                    cargarDatosPlanificacionEtapa(tmEstructura.getSeletedObject());
                }

            }
        });
        /******************************************************************************/
        tmMaquina = new TablaManager<MaquinaHerramientaParticular>(tbMaquinas) {

            @Override
            public Vector ObjetoFila(MaquinaHerramientaParticular elemento) {
                Vector salida = new Vector();
                salida.add(elemento.getCodigo());
                salida.add(elemento.getTipoMaquinaHerramienta().getNombre());
                salida.add(elemento.getNombre());
                salida.add(null);
                return salida;
            }

            @Override
            public Vector getCabecera() {
                Vector salida = new Vector();
                salida.add("Codigo");
                salida.add("Tipo");
                salida.add("Maquina");
                salida.add("");
                return salida;
            }
        };
        tbMaquinas.getColumnModel().getColumn(3).setCellRenderer(new TableCellRenderer() {

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JCheckBox chk = new JCheckBox();
                chk.setSelected(isSelected);
                return chk;
            }
        });
        /********************************************************************************/
        tmEmpleado = new TablaManager<Empleado>(tbEmpleado) {

            @Override
            public Vector ObjetoFila(Empleado elemento) {
                Vector salida = new Vector();
                salida.add(elemento.getIdEmpleado());
                salida.add(elemento.getApellido() + ", " + elemento.getNombre());
                salida.add(null);
                return salida;
            }

            @Override
            public Vector getCabecera() {
                Vector salida = new Vector();
                salida.add("Legajo");
                salida.add("Apellido/Nombre");
                salida.add("");
                return salida;
            }
        };
        tbEmpleado.getColumnModel().getColumn(2).setCellRenderer(new TableCellRenderer() {

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JCheckBox chk = new JCheckBox();
                chk.setSelected(isSelected);
                return chk;
            }
        });
        /********************************************************************************/
        tmHerramientas = new TablaManager<DetalleEtapaProduccion>(tbHerramienta) {

            @Override
            public Vector ObjetoFila(DetalleEtapaProduccion elemento) {
                Vector salida = new Vector();
                salida.add((elemento.getTipoMaquinaHerramienta() == null)
                        ? "" : elemento.getTipoMaquinaHerramienta().getNombre());
                salida.add(elemento.getCantidadNecesaria());
                return salida;
            }

            @Override
            public Vector getCabecera() {
                Vector salida = new Vector();
                salida.add("Herramientas");
                salida.add("Cantidad");
                return salida;
            }
        };

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

    private void habilitarDatosPlanificacion(boolean valor) {

        Utilidades.habilitarPanel(pnlDetallePlanificaion, valor);
        Utilidades.habilitarPanel(pnlContenedorAux, !valor);

        //fhInicioDetallePlan.setEnabled(valor);

        txtObservaciones.setEditable(valor);
        btnAceptar.setEnabled(valor); //cambió el nombre de componente
        btnCancelar.setEnabled(valor); //cambió el nombre de componente
    }

    public boolean validarDatosDetalle() {

        if (tmMaquina.getSelectedRow() == -1) {
            Mensajes.mensajeErrorGenerico("Debe seleccionar una máquina");
            tbMaquinas.requestFocus();
            return false;
        }

        if (tmEmpleado.getSelectedRow() == -1) {
            Mensajes.mensajeErrorGenerico("Debe seleccionar un operario");
            tbEmpleado.requestFocus();
            return false;
        }
        return true;
    }

    private void limpiarDatosEtapaPlanificacion() {
//        fhInicioDetallePlan.setDate(Utilidades.getFechaActual());
//        tbMaquinas.clearSelection();
//        tbEmpleado.clearSelection();
        txtTiempoInicio.setText("");
        txtTiempoFin.setText("");
        tmEmpleado.limpiar();
        tmMaquina.limpiar();
        tmHerramientas.limpiar();
        txtObservaciones.setText("");
    }

    private void cargarDatosPlanificacionEtapa(EtapaProduccionEspecifica epe) {
        limpiarDatosEtapaPlanificacion();
        TipoMaquinaHerramienta tipoMaq = null;
        List<DetalleEtapaProduccion> herramientas = new ArrayList<DetalleEtapaProduccion>();
        for (DetalleEtapaProduccion det : epe.getDetalleEtapaProduccion()) {
            if (det.getTipoMaquinaHerramienta() != null) {
                tipoMaq = det.getTipoMaquinaHerramienta();
            }
            if (det.getTipoMaquinaHerramienta() != null && det.getTipoMaquinaHerramienta().isEsHerramienta()) {
                herramientas.add(det);
            }
        }

        tmHerramientas.setDatos(herramientas);
        tmEmpleado.setDatos(EmpleadoBD.getEmpleados(epe.getCargo(), true, false));
        tmMaquina.setDatos(MaquinaHerramientaBD.getMaquinasHerramientas(tipoMaq, true, false));

        Date inicio = fhInicioPlan.getDate();
        if (epe.getNumeroOrden() == 1) {
            inicio = fhInicioPlan.getDate();
        } else {
            try{
            inicio=plan.getDetallePlan(tmEstructura.getDato(tmEstructura.getSelectedRow() - 1)).getFecHoraPrevistaFin();
            //inicio = plan.getDetallePlan(tmEstructura.getDato(epe.getNumeroOrden() - 1)).getFecHoraPrevistaFin();
            }catch(Exception e){inicio=Utilidades.getFechaActual();}
        }
        txtTiempoInicio.setText(Utilidades.parseFechaHora(inicio));
        int cantidadProductos=0;
        for(DetallePedido dp:tmDetallePedido.getDatos())
            if(dp.getProducto().getIdProducto()==epe.getProducto().getIdProducto())
                cantidadProductos=dp.getCantidad();
        txtTiempoFin.setText(Utilidades.parseFechaHora(Utilidades.agregarTiempoFecha(inicio, cantidadProductos*epe.getDuracion(), 0, 0, 0, 0)));


        if (plan.getDetallePlan(epe) == null) {
            return;
        }

        DetallePlanProduccion detalle = plan.getDetallePlan(epe);

//        fhInicioDetallePlan.setDate((detalle.getFecHoraPrevistaInicio() == null) ? Utilidades.getFechaActual() : detalle.getFecHoraPrevistaInicio());
        txtTiempoInicio.setText(Utilidades.parseFechaHora(detalle.getFecHoraPrevistaInicio()));
        txtTiempoFin.setText(Utilidades.parseFechaHora(detalle.getFecHoraPrevistaFin()));

//        tmMaquina.setDatos(MaquinaHerramientaBD.getMaquinasHerramientas(tipoMaq, true, false));
        tmMaquina.setSelectedRow(detalle.getTMaquinasHerramientaParticular());

//        tmEmpleado.setDatos(EmpleadoBD.getEmpleados(detalle.getTEtapasProduccionEspecifica().getCargo(), true, false));
        tmEmpleado.setSelectedRow(detalle.getTEmpleados());

        txtObservaciones.setText(detalle.getObservaciones());
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblRazonSocial = new javax.swing.JLabel();
        lblFechaNecesidad = new javax.swing.JLabel();
        lblTipoPedido = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labefdsfsf = new javax.swing.JLabel();
        lblFechaEntrega = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        cmbResponsable = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        fhInicioPlan = new com.toedter.calendar.JSpinnerDateEditor();
        jLabel16 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        pnlDetallePlanificaion = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        pnlEmpleado = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbEmpleado = new javax.swing.JTable();
        pnlMaquinas = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbMaquinas = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        txtTiempoFin = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbHerramienta = new javax.swing.JTable();
        txtTiempoInicio = new javax.swing.JTextField();
        pnlContenedorAux = new javax.swing.JPanel();
        pnlEstructura = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbEstructura = new javax.swing.JTable();
        btnPlanificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDetallePedido = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Planificación de producción");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Planificación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pedido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Razón social del cliente:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Tipo de pedido:");

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
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTipoPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                    .addComponent(lblRazonSocial))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labefdsfsf)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaNecesidad)
                    .addComponent(lblFechaEntrega))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblRazonSocial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoPedido)
                    .addComponent(jLabel4)))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labefdsfsf)
                    .addComponent(lblFechaEntrega)))
            .addComponent(lblFechaNecesidad)
        );

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel15.setText("Responsable del Plan:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel16.setText("Fecha/hora calculada inicio Plan:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fhInicioPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cmbResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(fhInicioPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        jLabel14.setText("Fecha/Hora inicio:");

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

        pnlEmpleado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Empleado Disponibles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Legajo", "Nombre/Apellido", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tbEmpleado);
        tbEmpleado.getColumnModel().getColumn(2).setHeaderValue("");

        javax.swing.GroupLayout pnlEmpleadoLayout = new javax.swing.GroupLayout(pnlEmpleado);
        pnlEmpleado.setLayout(pnlEmpleadoLayout);
        pnlEmpleadoLayout.setHorizontalGroup(
            pnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
        );
        pnlEmpleadoLayout.setVerticalGroup(
            pnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
        );

        pnlMaquinas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Máquinas Disponibles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbMaquinas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Tipo", "Maquina", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tbMaquinas);

        javax.swing.GroupLayout pnlMaquinasLayout = new javax.swing.GroupLayout(pnlMaquinas);
        pnlMaquinas.setLayout(pnlMaquinasLayout);
        pnlMaquinasLayout.setHorizontalGroup(
            pnlMaquinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
        );
        pnlMaquinasLayout.setVerticalGroup(
            pnlMaquinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setText("Observaciones:");

        txtObservaciones.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtObservaciones);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addContainerGap(117, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
        );

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel17.setText("Fecha/Hora fin:");

        txtTiempoFin.setEditable(false);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Herramientas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbHerramienta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Herramientas", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tbHerramienta);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
        );

        txtTiempoInicio.setEditable(false);

        javax.swing.GroupLayout pnlDetallePlanificaionLayout = new javax.swing.GroupLayout(pnlDetallePlanificaion);
        pnlDetallePlanificaion.setLayout(pnlDetallePlanificaionLayout);
        pnlDetallePlanificaionLayout.setHorizontalGroup(
            pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetallePlanificaionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDetallePlanificaionLayout.createSequentialGroup()
                        .addGroup(pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTiempoFin, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTiempoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pnlMaquinas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(pnlEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDetallePlanificaionLayout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addGap(10, 10, 10)
                        .addComponent(btnCancelar))))
        );
        pnlDetallePlanificaionLayout.setVerticalGroup(
            pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetallePlanificaionLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addGap(6, 6, 6))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetallePlanificaionLayout.createSequentialGroup()
                .addGroup(pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtTiempoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtTiempoFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(pnlEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlMaquinas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlEstructura.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Etapas a planificar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbEstructura.setFont(new java.awt.Font("Tahoma", 1, 11));
        tbEstructura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Planificado", "Nro. Orden", "Etapa", "Duración", "Tipo maquina", "Cargo empleado"
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

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEstructuraLayout = new javax.swing.GroupLayout(pnlEstructura);
        pnlEstructura.setLayout(pnlEstructuraLayout);
        pnlEstructuraLayout.setHorizontalGroup(
            pnlEstructuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEstructuraLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEstructuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPlanificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlEstructuraLayout.setVerticalGroup(
            pnlEstructuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEstructuraLayout.createSequentialGroup()
                .addComponent(btnPlanificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminar)
                .addGap(80, 80, 80))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlContenedorAuxLayout = new javax.swing.GroupLayout(pnlContenedorAux);
        pnlContenedorAux.setLayout(pnlContenedorAuxLayout);
        pnlContenedorAuxLayout.setHorizontalGroup(
            pnlContenedorAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorAuxLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEstructura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlContenedorAuxLayout.setVerticalGroup(
            pnlContenedorAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlEstructura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlDetallePlanificaion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlContenedorAux, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlContenedorAux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDetallePlanificaion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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



        if (plan.getDetallePlan(tmEstructura.getSeletedObject()) == null) {
            plan.addDetallePlan(new DetallePlanProduccion(tmEstructura.getSeletedObject()));
        }

        DetallePlanProduccion det = plan.getDetallePlan(tmEstructura.getSeletedObject());

//        det.setFecHoraPrevistaInicio(fhInicioDetallePlan.getDate());
        det.setFecHoraPrevistaInicio(new Date(txtTiempoInicio.getText()));
        det.setFecHoraPrevistaFin(new Date(txtTiempoFin.getText()));
//                Utilidades.agregarTiempoFecha(
//                fhInicioDetallePlan.getDate(), tmEstructura.getSeletedObject().getDuracion(), 0, 0, 0, 0));
        det.setObservaciones(txtObservaciones.getText());
        det.setTEmpleados(tmEmpleado.getSeletedObject());
        det.setTMaquinasHerramientaParticular(tmMaquina.getSeletedObject());
        det.setCantidad(tmDetallePedido.getSeletedObject().getCantidad());
        det.setTEdetallePlan(EstadoDetallePlanBD.getEstadoPendiente());

        habilitarDatosPlanificacion(false);
        limpiarDatosEtapaPlanificacion();
        tmEstructura.updateTabla();

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
        plan.generarFaltantes();
        if (plan.getFecGeneracion() == null) {
            plan.setFecGeneracion(Utilidades.getFechaActual());
        }
        plan.setFecUltimaModificacion(Utilidades.getFechaActual());
        if (cmbResponsable.getSelectedIndex() == -1) {
            Mensajes.mensajeErrorGenerico("Seleccione un responsable");
            return;
        }
        plan.setEmpleado((Empleado) cmbResponsable.getSelectedItem());

        Date fechaFin = null, fechaIni = null;
        for (DetallePlanProduccion dpp : plan.getDetallePlan()) {
            if (fechaFin == null || fechaFin.compareTo(dpp.getFecHoraPrevistaFin()) <= 0) {
                fechaFin = dpp.getFecHoraPrevistaFin();
            }

            if (fechaIni == null || fechaIni.compareTo(dpp.getFecHoraPrevistaInicio()) >= 0) {
                fechaIni = dpp.getFecHoraPrevistaInicio();
            }
        }
        plan.setFecHoraPrevistaFin(fechaFin);
        plan.setFecHoraPrevistaInicio(fechaIni);

        plan.getPedido().setEstadoPedido(EstadoPedidoBD.getEstadoPlanificado());
        plan.setEstado(EstadoPlanBD.getEstadoGenerado());
        HibernateUtil.guardarObjeto(plan);

        Mensajes.mensajeInformacion("Se ha generado el plan correctamente");
        this.dispose();

}//GEN-LAST:event_jButton4ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:

        limpiarDatosEtapaPlanificacion();
        habilitarDatosPlanificacion(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnPlanificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlanificarActionPerformed
        // TODO add your handling code here:
        if (tmEstructura.getSeletedObject() == null) {
            return;
        }

        if (tmEstructura.getSelectedRow() >= 1 && plan.getDetallePlan(tmEstructura.getDato(tmEstructura.getSelectedRow() - 1)) == null) {
            Mensajes.mensajeInformacion("Antes debe planificar la etapa anterior");
            return;
        }

        // limpiarDatosEtapaPlanificacion();
        cargarDatosPlanificacionEtapa(tmEstructura.getSeletedObject());
        habilitarDatosPlanificacion(true);



    }//GEN-LAST:event_btnPlanificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if (tmEstructura.getSeletedObject() == null) {
            return;
        }
        tmEstructura.getSeletedObject().limpiarDetalleEtapaProduccion();
        tmEstructura.updateTabla();

    }//GEN-LAST:event_btnEliminarActionPerformed

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
    private javax.swing.JButton btnPlanificar;
    private javax.swing.JComboBox cmbResponsable;
    private com.toedter.calendar.JSpinnerDateEditor fhInicioPlan;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel labefdsfsf;
    private javax.swing.JLabel lblFechaEntrega;
    private javax.swing.JLabel lblFechaNecesidad;
    private javax.swing.JLabel lblRazonSocial;
    private javax.swing.JLabel lblTipoPedido;
    private javax.swing.JPanel pnlContenedorAux;
    private javax.swing.JPanel pnlDetallePlanificaion;
    private javax.swing.JPanel pnlEmpleado;
    private javax.swing.JPanel pnlEstructura;
    private javax.swing.JPanel pnlMaquinas;
    private javax.swing.JTable tbDetallePedido;
    private javax.swing.JTable tbEmpleado;
    private javax.swing.JTable tbEstructura;
    private javax.swing.JTable tbHerramienta;
    private javax.swing.JTable tbMaquinas;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtTiempoFin;
    private javax.swing.JTextField txtTiempoInicio;
    // End of variables declaration//GEN-END:variables
}
