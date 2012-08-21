/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaPlanProduccionNuevo.java
 *
 * Created on 17-ago-2012, 23:45:18
 */
package Presentacion.Produccion;

import BaseDeDatos.Administracion.EmpleadoBD;
import BaseDeDatos.HibernateUtil;
import BaseDeDatos.Produccion.EstadoDetallePlanBD;
import BaseDeDatos.Produccion.EstadoPlanBD;
import BaseDeDatos.Produccion.MaquinaBD;
import BaseDeDatos.Ventas.EstadoDetallePedidoBD;
import BaseDeDatos.Ventas.EstadoPedidoBD;
import BaseDeDatos.Ventas.PedidoBD;
import Negocio.Administracion.Empleado;
import Negocio.Compras.Material;
import Negocio.Produccion.DetalleEtapaProduccion;
import Negocio.Produccion.DetallePlanProduccion;
import Negocio.Produccion.EstadoDetallePlan;
import Negocio.Produccion.EtapaProduccionEspecifica;
import Negocio.Produccion.MaqHerrPartXDetPlan;
import Negocio.Produccion.PlanProduccion;
import Negocio.Produccion.PlanProduccionId;
import Negocio.Ventas.DetallePedido;
import Negocio.Ventas.Pedido;
import Presentacion.Mensajes;
import Presentacion.TablaManager;
import Presentacion.Utilidades;
import java.awt.CardLayout;
import java.awt.Dialog;
import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JFrame;
import org.hibernate.Hibernate;

/**
 *
 * @author Rodrigo
 */
public class PantallaPlanProduccionNuevo extends javax.swing.JDialog {

    public static void generarNuevoPlan(Window parent) {
        PantallaPlanProduccionNuevo interfaz = new PantallaPlanProduccionNuevo(parent);
        interfaz.pnlPedidoBuscar.setVisible(true);
        interfaz.pnlPedidoInfo.setVisible(false);
        interfaz.btnPedCancelar.setEnabled(false);
        Utilidades.habilitarPanel(interfaz.pnlPlanPrincipal, false);
        interfaz.setVisible(true);
    }

    static void ModificarPlan(Window parent, PlanProduccion plan) {
        PantallaPlanProduccionNuevo interfaz = new PantallaPlanProduccionNuevo(parent);
        interfaz.pnlPedidoBuscar.setVisible(false);
        interfaz.pnlPedidoInfo.setVisible(true);
        interfaz.btnPedBuscar.setVisible(false);
        Utilidades.habilitarPanel(interfaz.pnlPlanPrincipal, true);
        interfaz.dtcPlanFecInicioPrevista.setEnabled(false);
        interfaz.setPlanProduccion(plan);
        interfaz.setVisible(true);
    }
    /** Creates new form PantallaPlanProduccionNuevo */
    private TablaManager<Pedido> tmPedido;
    private TablaManager<DetallePedido> tmDetallePedido;
    private TablaManager<MaterialInfo> tmMaterial;
    private TablaManager<DetallePlanProduccion> tmDetallePlan;
    private Pedido pedidoActual;
    private PlanProduccion planActual;

    private PantallaPlanProduccionNuevo(java.awt.Window parent) {
        super(parent, ModalityType.APPLICATION_MODAL);
        initComponents();
        iniciarConfiguracion();
        this.addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });

        Utilidades.comboCargar(cmbPedFiltroPrioridad, new String[]{"Baja", "Media", "Alta", "Muy Alta", "Todos"});
        cmbPedFiltroPrioridad.setSelectedIndex(4);
        cmbPrioridad.setSelectedIndex(-1);
        btnPedBuscar.setEnabled(true);

        Utilidades.habilitarPanel(pnlPlanDatosGeneral, false);

        btnBuscarActionPerformed(null);
    }

    private void filtrarTablaActividades(Date d) {
        if (planActual == null) {
            return;


        }
        String fechaFiltro = Utilidades.parseFecha(d);
        List<DetallePlanProduccion> l = planActual.getDetallePlan();
        for (int i = 0; i < l.size(); i++) {
            String fecha = Utilidades.parseFecha(l.get(i).getFecHoraPrevistaInicio());
            if (fechaFiltro.equals(fecha) == false) {
                l.remove(i);
                i--;
            }
        }

        tmDetallePlan.setDatos(l);
    }

    private void iniciarConfiguracion() {

        calFechaProduccion.addPropertyChangeListener("date", new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                filtrarTablaActividades((Date) evt.getNewValue());
            }
        });

        tmPedido = new TablaManager<Pedido>(tbPedido) {

            @Override
            public Vector ObjetoFila(Pedido elemento) {
                Vector salida = new Vector();
                salida.add(elemento.getIdPedido());
                salida.add(Utilidades.parseFecha(elemento.getFechaGeneracion()));
                salida.add(elemento.getCliente().getRazonSocial());
                salida.add(elemento.getCliente().getCuit());
                salida.add(elemento.getPrioridadTexto());
                salida.add(Utilidades.parseFecha(elemento.getFechaNecesidad()));
                return salida;
            }

            @Override
            public Vector getCabecera() {
                Vector salida = new Vector();
                salida.add("Nro");
                salida.add("Fecha Generacion");
                salida.add("Razon Social");
                salida.add("CUIT");
                salida.add("Prioridad");
                salida.add("Fecha Necesidad");
                return salida;
            }
        };

        tmDetallePedido = new TablaManager<DetallePedido>(tbDetalle) {

            @Override
            public Vector ObjetoFila(DetallePedido elemento) {
                Vector salida = new Vector();
                salida.add(elemento.getProducto().toString());
                salida.add(elemento.getCantidad());
                salida.add(elemento.getPrecio());
                salida.add(elemento.getSubTotal());

                return salida;
            }

            @Override
            public Vector getCabecera() {
                Vector salida = new Vector();
                salida.add("Producto");
                salida.add("Cantidad");
                salida.add("Precio");
                salida.add("Sub. Total");
                return salida;
            }
        };

        tmMaterial = new TablaManager<MaterialInfo>(tbMateriales) {

            @Override
            public Vector ObjetoFila(MaterialInfo elemento) {
                Vector salida = new Vector();
                salida.add(elemento.getMaterial().getNombre());
                salida.add(elemento.getCantidadNecesaria());
                salida.add(elemento.getMaterial().getStockActual());
                return salida;
            }

            @Override
            public Vector getCabecera() {
                Vector salida = new Vector();
                salida.add("Material");
                salida.add("Cantidad Necesaria");
                salida.add("Stock Actual");
                return salida;
            }
        };

        tmDetallePlan = new TablaManager<DetallePlanProduccion>(tbActividades) {

            @Override
            public Vector ObjetoFila(DetallePlanProduccion elemento) {
                Vector salida = new Vector();

                salida.add(Utilidades.parseFecha(elemento.getFecHoraPrevistaInicio()));
                salida.add(Utilidades.parseHora(elemento.getFecHoraPrevistaInicio()));
                salida.add(Utilidades.parseHora(elemento.getFecHoraPrevistaFin()));
                salida.add(elemento.getTEmpleados().getApellidoNombre());
                salida.add(elemento.getTEtapasProduccionEspecifica().getProducto());
                salida.add(elemento.getTEtapasProduccionEspecifica().getEtapaProduccion().getNombre());
                salida.add(elemento.getMaquinaParticular());

                return salida;
            }

            @Override
            public Vector getCabecera() {
                Vector salida = new Vector();
                salida.add("Fecha");
                salida.add("Hora Inicio");
                salida.add("Hora Fin");
                salida.add("Operario");
                salida.add("Producto");
                salida.add("Actividad");
                salida.add("Maquina");

                return salida;
            }
        };


    }

    private void cargarDatosPedidos(Pedido p) {
        txtPedCliCUIT.setText(p.getCliente().getCuit());
        txtPedCliRazonSocial.setText(p.getCliente().getRazonSocial());
        txtPedEmpApellidoNombre.setText(p.getEmpleado().getApellidoNombre());
        txtPedEmpLegajo.setText(p.getEmpleado().getId() + "");
        txtPedFechaGeneracion.setText(Utilidades.parseFecha(p.getFechaGeneracion()));
        txtPedFechaNecesidad.setText(Utilidades.parseFecha(p.getFechaNecesidad()));
        //txtPedFechaRecep.setText(Utilidades.parseFecha(p.getf()));
        txtPedNro.setText(p.getIdPedido() + "");
        txtPedPrioridad.setText(p.getPrioridadTexto());
        txtPedTipo.setText(p.getTipoPedido().getNombre());
        txtPedTotal.setText(p.getMontoTotal().toPlainString());
        tmDetallePedido.setDatos(p.getDetallePedido());
    }

    private void cargarDatosPlan(PlanProduccion plan) {
        String aux = "";
        txtPlanFecFinPrevista.setText(Utilidades.parseFecha(plan.getFecHoraPrevistaFin()));
        dtcPlanFecInicioPrevista.setDate(plan.getFecHoraPrevistaInicio());

        aux = (plan.getFecHoraRealFin() == null) ? "----" : Utilidades.parseFecha(plan.getFecHoraRealFin());
        txtPlanFecFinReal.setText(aux);

        cmbPrioridad.setSelectedIndex(plan.getPedido().getPrioridad());
        aux = (plan.getFecHoraRealInicio() == null) ? "----" : Utilidades.parseFecha(plan.getFecHoraRealInicio());
        txtPlanFecInicioReal.setText(aux);
        tmDetallePlan.setDatos(plan.getDetallePlan());
        calFechaProduccion.setSelectableDateRange(plan.getFecHoraPrevistaInicio(), plan.getFecHoraPrevistaFin());
//        calFechaProduccion.setMinSelectableDate(plan.getFecHoraPrevistaInicio());
//        calFechaProduccion.setMaxSelectableDate(plan.getFecHoraPrevistaFin());
        calFechaProduccion.repaint();
        txtObservaciones.setText(plan.getObservaciones());
    }

    private PlanProduccion generarPlan(Pedido p) {
        PlanProduccion plan = new PlanProduccion();
        plan.setId(new PlanProduccionId(1, p.getIdPedido()));
        plan.setFecGeneracion(Utilidades.getFechaActual());
        plan.setEmpleado(EmpleadoBD.getEmpleadosVigentes().get(0));
        plan.setEstado(EstadoPlanBD.getEstadoGenerado());
        plan.setFecHoraPrevistaInicio(Utilidades.agregarTiempoFecha(Utilidades.getFechaActual(), 1, 0, 0));
        plan.setPedido(p);
        p.setEstadoPedido(EstadoPedidoBD.getEstadoPlanificado());
        Date fecha = plan.getFecHoraPrevistaInicio();
        List<Empleado> operarios = EmpleadoBD.getEmpleadosVigentes();

        int empIndex = 0;
        for (DetallePedido dp : p.getDetallePedido()) {
            for (EtapaProduccionEspecifica e : dp.getProducto().getEtapasProduccionEspecificas()) {
                int duracion = 0;
                DetallePlanProduccion detPlan = new DetallePlanProduccion();
                plan.addDetallePlan(detPlan);
                detPlan.setPlanProduccion(plan);
                detPlan.setTDetallesPedido(dp);
                detPlan.setTEdetallePlan(EstadoDetallePlanBD.getEstadoPendiente());
                detPlan.setTEtapasProduccionEspecifica(e);

                if (empIndex >= operarios.size()) {
                    empIndex = 0;
                }

                detPlan.setTEmpleados(EmpleadoBD.getEmpleadosVigentes().get(empIndex));
                empIndex++;

                detPlan.setCantidad(dp.getCantidad());
                detPlan.setFecHoraPrevistaInicio(fecha);

                duracion = e.getDuracion() * dp.getCantidad();//minutos
                fecha = Utilidades.agregarTiempoFecha(fecha, duracion, 0, 0, 0, 0);

                detPlan.setFecHoraPrevistaFin(fecha);
                detPlan.setMaquinaParticular(MaquinaBD.getMaquinas(e.getTTmaquina(), true, false).get(0));
                detPlan.setTDetallesPedido(dp);
            }
        }

        plan.setFecHoraPrevistaFin(fecha);
        return plan;
    }

    private void setPlanProduccion(PlanProduccion plan) {
        this.planActual = plan;
        this.pedidoActual = plan.getPedido();
        cargarDatosPedidos(pedidoActual);
        cargarDatosPlan(planActual);
    }

    private void setPedido(Pedido p) {
        this.pedidoActual = p;
        cargarDatosPedidos(p);
        this.planActual = generarPlan(p);
        cargarDatosPlan(planActual);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPedido = new javax.swing.JPanel();
        pnlPedidoBuscar = new javax.swing.JPanel();
        scrlTablaPedido = new javax.swing.JScrollPane();
        tbPedido = new javax.swing.JTable();
        pnlPedFiltro = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtPedFiltroNro = new javax.swing.JTextField();
        txtPedFiltroRazonSocial = new javax.swing.JTextField();
        txtPedFiltroCUIT = new javax.swing.JTextField();
        cmbPedFiltroPrioridad = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnPedCancelar = new javax.swing.JButton();
        btnPedidoAceptar = new javax.swing.JButton();
        pnlPedidoInfo = new javax.swing.JPanel();
        pnlPedDetalle = new javax.swing.JPanel();
        scrlTablaDetalle = new javax.swing.JScrollPane();
        tbDetalle = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtPedTotal = new javax.swing.JTextField();
        pnlPedInfoGeneral = new javax.swing.JPanel();
        pnlPedCliente = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtPedCliCUIT = new javax.swing.JTextField();
        txtPedCliRazonSocial = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtPedNro = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPedFechaRecep = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPedFechaNecesidad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPedFechaGeneracion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPedPrioridad = new javax.swing.JTextField();
        txtPedTipo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        pnlPedEmpleado = new javax.swing.JPanel();
        txtPedEmpApellidoNombre = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtPedEmpLegajo = new javax.swing.JTextField();
        btnPedBuscar = new javax.swing.JButton();
        pnlPlanPrincipal = new javax.swing.JPanel();
        pnlPlanDetalle = new javax.swing.JPanel();
        pnlActividades = new javax.swing.JPanel();
        scrlTablaActividades = new javax.swing.JScrollPane();
        tbActividades = new javax.swing.JTable();
        pnlFiltro = new javax.swing.JPanel();
        calFechaProduccion = new com.toedter.calendar.JCalendar();
        pnlPlanDatosGeneral = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        dtcPlanFecInicioPrevista = new com.toedter.calendar.JDateChooser();
        txtPlanFecFinPrevista = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cmbPrioridad = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        txtPlanFecFinReal = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtPlanFecInicioReal = new javax.swing.JTextField();
        pnlMateriales = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMateriales = new javax.swing.JTable();
        btnPlanAceptar = new javax.swing.JButton();
        btnPlanCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Generar Plan de Produccion");

        pnlPedido.setLayout(new java.awt.CardLayout());

        pnlPedidoBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos no planificados"));

        tbPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro", "Fecha Generacion", "Razon Social", "CUIT", "Tipo", "Prioridad", "Fecha Necesidad"
            }
        ));
        scrlTablaPedido.setViewportView(tbPedido);

        pnlPedFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));

        jLabel14.setText("Prioridad:");

        cmbPedFiltroPrioridad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Nro:");

        jLabel13.setText("CUIT:");

        jLabel12.setText("Razon Social:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPedFiltroLayout = new javax.swing.GroupLayout(pnlPedFiltro);
        pnlPedFiltro.setLayout(pnlPedFiltroLayout);
        pnlPedFiltroLayout.setHorizontalGroup(
            pnlPedFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPedFiltroNro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPedFiltroRazonSocial, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPedFiltroCUIT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbPedFiltroPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar)
                .addContainerGap())
        );
        pnlPedFiltroLayout.setVerticalGroup(
            pnlPedFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel11)
                .addComponent(txtPedFiltroNro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel12)
                .addComponent(txtPedFiltroRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel13)
                .addComponent(txtPedFiltroCUIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel14)
                .addComponent(cmbPedFiltroPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnBuscar))
        );

        btnPedCancelar.setText("Cancelar");
        btnPedCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedCancelarActionPerformed(evt);
            }
        });

        btnPedidoAceptar.setText("Aceptar");
        btnPedidoAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedidoAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPedidoAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
            .addComponent(btnPedCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnPedidoAceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPedCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlPedidoBuscarLayout = new javax.swing.GroupLayout(pnlPedidoBuscar);
        pnlPedidoBuscar.setLayout(pnlPedidoBuscarLayout);
        pnlPedidoBuscarLayout.setHorizontalGroup(
            pnlPedidoBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedidoBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPedidoBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPedidoBuscarLayout.createSequentialGroup()
                        .addComponent(scrlTablaPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlPedFiltro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlPedidoBuscarLayout.setVerticalGroup(
            pnlPedidoBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedidoBuscarLayout.createSequentialGroup()
                .addComponent(pnlPedFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPedidoBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrlTablaPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlPedido.add(pnlPedidoBuscar, "PedidoBuscar");

        pnlPedidoInfo.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedido seleccionado"));

        pnlPedDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));

        tbDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Cantidad", "Precio", "Sub. Total"
            }
        ));
        scrlTablaDetalle.setViewportView(tbDetalle);

        jLabel7.setText("Total:");

        txtPedTotal.setEditable(false);

        javax.swing.GroupLayout pnlPedDetalleLayout = new javax.swing.GroupLayout(pnlPedDetalle);
        pnlPedDetalle.setLayout(pnlPedDetalleLayout);
        pnlPedDetalleLayout.setHorizontalGroup(
            pnlPedDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedDetalleLayout.createSequentialGroup()
                .addContainerGap(290, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPedTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(scrlTablaDetalle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
        );
        pnlPedDetalleLayout.setVerticalGroup(
            pnlPedDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPedDetalleLayout.createSequentialGroup()
                .addComponent(scrlTablaDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPedDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtPedTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnlPedCliente.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        jLabel2.setText("CUIT:");

        txtPedCliCUIT.setEditable(false);

        txtPedCliRazonSocial.setEditable(false);

        jLabel1.setText("Razon Social:");

        javax.swing.GroupLayout pnlPedClienteLayout = new javax.swing.GroupLayout(pnlPedCliente);
        pnlPedCliente.setLayout(pnlPedClienteLayout);
        pnlPedClienteLayout.setHorizontalGroup(
            pnlPedClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedClienteLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPedCliCUIT, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPedCliRazonSocial, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))
        );
        pnlPedClienteLayout.setVerticalGroup(
            pnlPedClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(txtPedCliCUIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1)
                .addComponent(txtPedCliRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtPedNro.setEditable(false);

        jLabel9.setText("Nro:");

        jLabel8.setText("Fecha Recepcion de MP:");

        txtPedFechaRecep.setEditable(false);

        jLabel4.setText("Fecha Necesidad:");

        txtPedFechaNecesidad.setEditable(false);

        jLabel5.setText("Prioridad:");

        txtPedFechaGeneracion.setEditable(false);

        jLabel3.setText("Fecha Generacion:");

        txtPedPrioridad.setEditable(false);

        txtPedTipo.setEditable(false);

        jLabel6.setText("Tipo Pedido");

        pnlPedEmpleado.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleado"));

        txtPedEmpApellidoNombre.setEditable(false);

        jLabel10.setText("Legajo:");

        jLabel22.setText("Apellido, Nombre:");

        txtPedEmpLegajo.setEditable(false);

        javax.swing.GroupLayout pnlPedEmpleadoLayout = new javax.swing.GroupLayout(pnlPedEmpleado);
        pnlPedEmpleado.setLayout(pnlPedEmpleadoLayout);
        pnlPedEmpleadoLayout.setHorizontalGroup(
            pnlPedEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedEmpleadoLayout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPedEmpLegajo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPedEmpApellidoNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
        );
        pnlPedEmpleadoLayout.setVerticalGroup(
            pnlPedEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel10)
                .addComponent(txtPedEmpLegajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel22)
                .addComponent(txtPedEmpApellidoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnPedBuscar.setText("<html><center>Buscar<br>de nuevo</center></html>");
        btnPedBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPedInfoGeneralLayout = new javax.swing.GroupLayout(pnlPedInfoGeneral);
        pnlPedInfoGeneral.setLayout(pnlPedInfoGeneralLayout);
        pnlPedInfoGeneralLayout.setHorizontalGroup(
            pnlPedInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPedInfoGeneralLayout.createSequentialGroup()
                .addGroup(pnlPedInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlPedInfoGeneralLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnPedBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPedInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlPedInfoGeneralLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPedNro, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPedFechaGeneracion, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(pnlPedEmpleado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlPedInfoGeneralLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(pnlPedInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPedInfoGeneralLayout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPedFechaNecesidad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPedPrioridad))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPedInfoGeneralLayout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPedTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPedFechaRecep, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(pnlPedCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlPedInfoGeneralLayout.setVerticalGroup(
            pnlPedInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedInfoGeneralLayout.createSequentialGroup()
                .addGroup(pnlPedInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtPedNro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtPedFechaGeneracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPedEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPedCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPedInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlPedInfoGeneralLayout.createSequentialGroup()
                        .addGroup(pnlPedInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPedFechaNecesidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtPedPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlPedInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtPedTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtPedFechaRecep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnPedBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout pnlPedidoInfoLayout = new javax.swing.GroupLayout(pnlPedidoInfo);
        pnlPedidoInfo.setLayout(pnlPedidoInfoLayout);
        pnlPedidoInfoLayout.setHorizontalGroup(
            pnlPedidoInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedidoInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPedInfoGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(pnlPedDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPedidoInfoLayout.setVerticalGroup(
            pnlPedidoInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPedInfoGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnlPedidoInfoLayout.createSequentialGroup()
                .addComponent(pnlPedDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlPedido.add(pnlPedidoInfo, "PedidoInfo");

        pnlPlanPrincipal.setBorder(javax.swing.BorderFactory.createTitledBorder("Plan de Produccion"));

        pnlPlanDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle del Pan de Produccion"));

        pnlActividades.setBorder(javax.swing.BorderFactory.createTitledBorder("Actividades"));

        tbActividades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Operario", "Actividad", "Hora Inicio", "Hora Fin", "Maquina", "Materiales Usadas", "Heramioentas Usadas"
            }
        ));
        scrlTablaActividades.setViewportView(tbActividades);

        javax.swing.GroupLayout pnlActividadesLayout = new javax.swing.GroupLayout(pnlActividades);
        pnlActividades.setLayout(pnlActividadesLayout);
        pnlActividadesLayout.setHorizontalGroup(
            pnlActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlActividadesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrlTablaActividades, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlActividadesLayout.setVerticalGroup(
            pnlActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrlTablaActividades, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
        );

        pnlFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro"));

        calFechaProduccion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        calFechaProduccion.setWeekOfYearVisible(false);

        javax.swing.GroupLayout pnlFiltroLayout = new javax.swing.GroupLayout(pnlFiltro);
        pnlFiltro.setLayout(pnlFiltroLayout);
        pnlFiltroLayout.setHorizontalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(calFechaProduccion, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
        );
        pnlFiltroLayout.setVerticalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(calFechaProduccion, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlPlanDetalleLayout = new javax.swing.GroupLayout(pnlPlanDetalle);
        pnlPlanDetalle.setLayout(pnlPlanDetalleLayout);
        pnlPlanDetalleLayout.setHorizontalGroup(
            pnlPlanDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPlanDetalleLayout.createSequentialGroup()
                .addComponent(pnlFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlActividades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPlanDetalleLayout.setVerticalGroup(
            pnlPlanDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlActividades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlPlanDatosGeneral.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Generales"));
        pnlPlanDatosGeneral.setMinimumSize(new java.awt.Dimension(600, 0));

        txtObservaciones.setBackground(new java.awt.Color(255, 255, 255));
        txtObservaciones.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setWrapStyleWord(true);
        txtObservaciones.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtObservacionesFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(txtObservaciones);

        jLabel19.setText("Observaciones:");

        dtcPlanFecInicioPrevista.setDateFormatString("dd/MM/yyyy");

        txtPlanFecFinPrevista.setEditable(false);

        jLabel21.setText("Prioridad:");

        jLabel16.setText(" Fin Prevista:");

        jLabel15.setText("Inicio Prevista:");

        cmbPrioridad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baja", "Media", "Alta", "Muy Alta", " " }));
        cmbPrioridad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPrioridadActionPerformed(evt);
            }
        });

        txtPlanFecFinReal.setEditable(false);

        jLabel17.setText("Inicio Real:");

        jLabel18.setText("Fin Real");

        txtPlanFecInicioReal.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPlanFecFinReal, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                    .addComponent(txtPlanFecInicioReal, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtPlanFecInicioReal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtPlanFecFinReal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtcPlanFecInicioPrevista, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPlanFecFinPrevista, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                            .addComponent(cmbPrioridad, 0, 114, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(dtcPlanFecInicioPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtPlanFecFinPrevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(cmbPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlPlanDatosGeneralLayout = new javax.swing.GroupLayout(pnlPlanDatosGeneral);
        pnlPlanDatosGeneral.setLayout(pnlPlanDatosGeneralLayout);
        pnlPlanDatosGeneralLayout.setHorizontalGroup(
            pnlPlanDatosGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPlanDatosGeneralLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPlanDatosGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPlanDatosGeneralLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addContainerGap(138, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)))
        );
        pnlPlanDatosGeneralLayout.setVerticalGroup(
            pnlPlanDatosGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPlanDatosGeneralLayout.createSequentialGroup()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlMateriales.setBorder(javax.swing.BorderFactory.createTitledBorder("Materiales"));
        pnlMateriales.setPreferredSize(new java.awt.Dimension(200, 129));

        tbMateriales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Material", "Cantidad Necesaria", "Cantidad Actual"
            }
        ));
        jScrollPane1.setViewportView(tbMateriales);

        javax.swing.GroupLayout pnlMaterialesLayout = new javax.swing.GroupLayout(pnlMateriales);
        pnlMateriales.setLayout(pnlMaterialesLayout);
        pnlMaterialesLayout.setHorizontalGroup(
            pnlMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMaterialesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlMaterialesLayout.setVerticalGroup(
            pnlMaterialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlPlanPrincipalLayout = new javax.swing.GroupLayout(pnlPlanPrincipal);
        pnlPlanPrincipal.setLayout(pnlPlanPrincipalLayout);
        pnlPlanPrincipalLayout.setHorizontalGroup(
            pnlPlanPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPlanPrincipalLayout.createSequentialGroup()
                .addComponent(pnlPlanDatosGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(pnlMateriales, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
            .addComponent(pnlPlanDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlPlanPrincipalLayout.setVerticalGroup(
            pnlPlanPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPlanPrincipalLayout.createSequentialGroup()
                .addGroup(pnlPlanPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMateriales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlPlanDatosGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPlanDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnPlanAceptar.setText("Aceptar");
        btnPlanAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlanAceptarActionPerformed(evt);
            }
        });

        btnPlanCancelar.setText("Cancelar");
        btnPlanCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlanCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
                    .addComponent(pnlPlanPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnPlanAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPlanCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPlanPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPlanCancelar)
                    .addComponent(btnPlanAceptar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        Integer prioridad = null;
        if (cmbPedFiltroPrioridad.getSelectedIndex() != 4) {
            prioridad = (Integer) cmbPedFiltroPrioridad.getSelectedIndex();
        }
        List<Pedido> l = PedidoBD.getPedidos(
                txtPedFiltroNro.getText(),
                txtPedFiltroCUIT.getText(),
                txtPedFiltroRazonSocial.getText(),
                prioridad,
                EstadoPedidoBD.getEstadoAutorizadoPendiente());
        tmPedido.setDatos(l);



    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnPedidoAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedidoAceptarActionPerformed
        // TODO add your handling code here:
        if (tmPedido.getSeletedObject() == null) {
            Mensajes.mensajeErrorGenerico("Debe seleccionar un pedido");
            return;
        }

        pnlPedidoBuscar.setVisible(false);
        pnlPedidoInfo.setVisible(true);
        btnPedCancelar.setEnabled(true);
        Utilidades.habilitarPanel(pnlPlanPrincipal, true);
        dtcPlanFecInicioPrevista.setEnabled(false);

        setPedido(tmPedido.getSeletedObject());

    }//GEN-LAST:event_btnPedidoAceptarActionPerformed

    private void btnPedCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedCancelarActionPerformed
        // TODO add your handling code here:
        pnlPedidoBuscar.setVisible(false);
        pnlPedidoInfo.setVisible(true);
        Utilidades.habilitarPanel(pnlPlanPrincipal, true);

    }//GEN-LAST:event_btnPedCancelarActionPerformed

    private void btnPedBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedBuscarActionPerformed
        // TODO add your handling code here:
        pnlPedidoBuscar.setVisible(true);
        pnlPedidoInfo.setVisible(false);
        Utilidades.habilitarPanel(pnlPlanPrincipal, false);
    }//GEN-LAST:event_btnPedBuscarActionPerformed

    private void txtObservacionesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtObservacionesFocusLost
        // TODO add your handling code here:
        if (planActual != null) {
            planActual.setObservaciones(txtObservaciones.getText());
        }

    }//GEN-LAST:event_txtObservacionesFocusLost

    private void btnPlanCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlanCancelarActionPerformed
        // TODO add your handling code here:
        HibernateUtil.getSession().clear();
        this.dispose();
    }//GEN-LAST:event_btnPlanCancelarActionPerformed

    private void btnPlanAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlanAceptarActionPerformed
        // TODO add your handling code here:
        HibernateUtil.guardarModificarObjeto(planActual);
        Mensajes.mensajeInformacion("Se ha guardado corectamente");
        this.dispose();

    }//GEN-LAST:event_btnPlanAceptarActionPerformed

    private void cmbPrioridadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPrioridadActionPerformed
        // TODO add your handling code here:
        if(cmbPrioridad.getSelectedIndex()!=-1)
        {
            pedidoActual.setPrioridad((byte) cmbPrioridad.getSelectedIndex());
            txtPedPrioridad.setText(pedidoActual.getPrioridadTexto());
        }
    }//GEN-LAST:event_cmbPrioridadActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                PantallaPlanProduccionNuevo.generarNuevoPlan(null);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnPedBuscar;
    private javax.swing.JButton btnPedCancelar;
    private javax.swing.JButton btnPedidoAceptar;
    private javax.swing.JButton btnPlanAceptar;
    private javax.swing.JButton btnPlanCancelar;
    private com.toedter.calendar.JCalendar calFechaProduccion;
    private javax.swing.JComboBox cmbPedFiltroPrioridad;
    private javax.swing.JComboBox cmbPrioridad;
    private com.toedter.calendar.JDateChooser dtcPlanFecInicioPrevista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlActividades;
    private javax.swing.JPanel pnlFiltro;
    private javax.swing.JPanel pnlMateriales;
    private javax.swing.JPanel pnlPedCliente;
    private javax.swing.JPanel pnlPedDetalle;
    private javax.swing.JPanel pnlPedEmpleado;
    private javax.swing.JPanel pnlPedFiltro;
    private javax.swing.JPanel pnlPedInfoGeneral;
    private javax.swing.JPanel pnlPedido;
    private javax.swing.JPanel pnlPedidoBuscar;
    private javax.swing.JPanel pnlPedidoInfo;
    private javax.swing.JPanel pnlPlanDatosGeneral;
    private javax.swing.JPanel pnlPlanDetalle;
    private javax.swing.JPanel pnlPlanPrincipal;
    private javax.swing.JScrollPane scrlTablaActividades;
    private javax.swing.JScrollPane scrlTablaDetalle;
    private javax.swing.JScrollPane scrlTablaPedido;
    private javax.swing.JTable tbActividades;
    private javax.swing.JTable tbDetalle;
    private javax.swing.JTable tbMateriales;
    private javax.swing.JTable tbPedido;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtPedCliCUIT;
    private javax.swing.JTextField txtPedCliRazonSocial;
    private javax.swing.JTextField txtPedEmpApellidoNombre;
    private javax.swing.JTextField txtPedEmpLegajo;
    private javax.swing.JTextField txtPedFechaGeneracion;
    private javax.swing.JTextField txtPedFechaNecesidad;
    private javax.swing.JTextField txtPedFechaRecep;
    private javax.swing.JTextField txtPedFiltroCUIT;
    private javax.swing.JTextField txtPedFiltroNro;
    private javax.swing.JTextField txtPedFiltroRazonSocial;
    private javax.swing.JTextField txtPedNro;
    private javax.swing.JTextField txtPedPrioridad;
    private javax.swing.JTextField txtPedTipo;
    private javax.swing.JTextField txtPedTotal;
    private javax.swing.JTextField txtPlanFecFinPrevista;
    private javax.swing.JTextField txtPlanFecFinReal;
    private javax.swing.JTextField txtPlanFecInicioReal;
    // End of variables declaration//GEN-END:variables

    private class MaterialInfo {

        private Material material;
        private int cantidad;

        public int getCantidadNecesaria() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public Material getMaterial() {
            return material;
        }

        public void setMaterial(Material material) {
            this.material = material;
        }
    }
}
