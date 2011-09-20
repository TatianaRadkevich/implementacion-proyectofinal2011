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
import Negocio.Produccion.DetallePlanProduccion;
import Negocio.Produccion.EtapaProduccionEspecifica;
import Negocio.Produccion.MaquinaHerramientaParticular;
import Negocio.Produccion.PlanProduccion;
import Negocio.Produccion.Producto;
import Negocio.Ventas.DetallePedido;
import Negocio.Ventas.Pedido;
import Presentacion.JCheckList;
import Presentacion.Mensajes;
import Presentacion.TablaManager;
import Presentacion.Utilidades;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Ivan
 */
public class PantallaABMPlanificacion1 extends javax.swing.JDialog {

    /** Creates new form PantallaABMPlanificacion */
    private Pedido pedido;
    private TablaManager<DetallePedido> tmDetallePedido;
    private TablaManager<EtapaProduccionEspecifica> tmEstructura;
    private TablaManager<DetallePlanProduccion> tmDetallePlanProduccion;

    private PantallaABMPlanificacion1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public PantallaABMPlanificacion1(Pedido p) {
        this(null, true);
        pedido = p;
        cargarCombos();
        inicializarTablas();
        habilitarDatosPlanificacion(false);
        cargarDatosPedido(p);
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
        tmDetallePedido.addListenerModificaionSelecion(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {

                if (tmDetallePedido.getSeletedObject() != null) {
                    cargarEstructura(tmDetallePedido.getSeletedObject().getProducto());
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
                fila.add(elemento.getDetalleEtapaProduccion().get(0).getTipoMaquinaHerramienta().getNombre());
                fila.add(elemento.getCargo().getNombre());
                fila.add(elemento.getHorasHombre());
                return fila;
            }

            @Override
            public Vector getCabecera() {
                Vector cabecera = new Vector();
                cabecera.add("Nro Oreden");
                cabecera.add("Etapa");
                cabecera.add("Tipo maquina");
                cabecera.add("Cargo empleado");
                cabecera.add("Horas hombre");
                return cabecera;
            }
        };

        /******************************************************************************/
        tmDetallePlanProduccion = new TablaManager<DetallePlanProduccion>(tbDetallePlan) {

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
        tmDetallePlanProduccion.addListenerModificaionSelecion(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                cargarDatosEtapaPlanificacion(tmDetallePlanProduccion.getSeletedObject());
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
        lblFechaNecesidad.setText(Utilidades.parseFecha(p.getFechaSolicitada()));
        lblFechaEntrega.setText(Utilidades.parseFecha(p.getFechaEstimadaEntrega()));
        lblTipoPedido.setText(p.getTipoPedido().getNombre());
        tmDetallePedido.setDatos(p.getDetallePedido());
        if(p.getPlanesProduccion().iterator().hasNext())
        {
            PlanProduccion plan=p.getPlanesProduccion().iterator().next();
            cmbResponsable.setSelectedItem(plan.getTEmpleados());
            fhInicioPlan.setDate((plan.getFecHoraPrevistaInicio()==null)?Utilidades.getFechaActual():plan.getFecHoraPrevistaInicio());
        }
        
    }

    private void cargarEstructura(Producto p) {
        tmEstructura.setDatos(new ArrayList(p.getTEtapasProduccionEspecificas()));


        ArrayList<DetallePlanProduccion> aux = new ArrayList<DetallePlanProduccion>(p.getTEtapasProduccionEspecificas().size());
        for (int i = 0; i < p.getTEtapasProduccionEspecificas().size(); i++) {
            if(tmEstructura.getDato(i).getDetallePlanProduccion().isEmpty())
                aux.add(new DetallePlanProduccion(tmEstructura.getDato(i)));
            else
            {
                aux.add(tmEstructura.getDato(i).getDetallePlanProduccion().get(0));
            }

        }

        tmDetallePlanProduccion.setDatos(aux);

    }

    private void habilitarDatosPlanificacion(boolean valor) {
        tbDetallePlan.setEnabled(!valor);
        btnModificar.setEnabled(!valor); //cambió el nombre de componente

        fhInicioDetallePlan.setEnabled(valor);
       // cmbMaquina.setEnabled(valor);
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

//        if (cmbMaquina.getSelectedIndex() == -1) {
//            Mensajes.mensajeErrorGenerico("Debe seleccionar una máquina");
//            cmbMaquina.requestFocus();
//            return false;
//        }

        if (cmbOperario.getSelectedIndex() == -1) {
            Mensajes.mensajeErrorGenerico("Debe seleccionar un operario");
            cmbOperario.requestFocus();
            return false;
        }
        return true;
    }

    private void limpiarDatosEtapaPlanificacion()
    {
        fhInicioDetallePlan.setDate(Utilidades.getFechaActual());
        //cmbMaquina.setSelectedIndex(-1);
        cmbOperario.setSelectedIndex(-1);
        txtObservaciones.setText("");
    }

    private void cargarDatosEtapaPlanificacion(DetallePlanProduccion detalle) {
        if(detalle==null)
        {
            limpiarDatosEtapaPlanificacion();
            return;
        }

        fhInicioDetallePlan.setDate((detalle.getFecHoraPrevistaInicio()==null)?Utilidades.getFechaActual():detalle.getFecHoraPrevistaInicio());
//        cmbMaquina.setModel(
//                new DefaultComboBoxModel(
//                MaquinaHerramientaBD.getMaquinasHerramientas(
//                detalle.getTEtapasProduccionEspecifica()
//                .getDetalleEtapaProduccion().get(0)
//                .getTipoMaquinaHerramienta(), true, false).toArray()));
//        cmbMaquina.setSelectedItem(detalle.getTMaquinasHerramientaParticular());

        cmbOperario.setModel(
                new DefaultComboBoxModel(EmpleadoBD.getEmpleados(detalle.getTEtapasProduccionEspecifica().getCargo(), true, false).toArray()));
        
        cmbOperario.setSelectedItem(detalle.getTEmpleados());
        txtObservaciones.setText(detalle.getObservaciones());
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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDetallePedido = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        pnlDetallePlanificaion = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        fhInicioDetallePlan = new com.toedter.calendar.JSpinnerDateEditor();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cmbOperario = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        pnlDetallePlanificacion = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbDetallePlan = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        pnlEstructura = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbEstructura = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

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

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle Pedido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbDetallePedido.setFont(new java.awt.Font("Tahoma", 1, 11));
        tbDetallePedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Producto", "Cantidad", "Tipo producto"
            }
        ));
        tbDetallePedido.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbDetallePedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDetallePedidoMouseClicked(evt);
            }
        });
        tbDetallePedido.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tbDetallePedidoComponentShown(evt);
            }
        });
        tbDetallePedido.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tbDetallePedidoComponentAdded(evt);
            }
        });
        tbDetallePedido.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbDetallePedidoPropertyChange(evt);
            }
        });
        tbDetallePedido.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tbDetallePedidoAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane2.setViewportView(tbDetallePedido);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addContainerGap())
        );

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
                    .addComponent(lblFechaNecesidad, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                    .addComponent(lblRazonSocial, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                    .addComponent(lblTipoPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                    .addComponent(lblFechaEntrega, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fhInicioPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(166, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(fhInicioPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cmbResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        pnlDetallePlanificaion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle Planificación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

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

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setText("Empleado:");

        txtObservaciones.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtObservaciones);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setText("Observaciones:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 451, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 282, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab1", jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 451, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 282, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab2", jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 451, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 282, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab3", jPanel7);

        javax.swing.GroupLayout pnlDetallePlanificaionLayout = new javax.swing.GroupLayout(pnlDetallePlanificaion);
        pnlDetallePlanificaion.setLayout(pnlDetallePlanificaionLayout);
        pnlDetallePlanificaionLayout.setHorizontalGroup(
            pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetallePlanificaionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDetallePlanificaionLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(10, 10, 10)
                        .addComponent(fhInicioDetallePlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDetallePlanificaionLayout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addGap(10, 10, 10)
                        .addComponent(btnCancelar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDetallePlanificaionLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1))
                    .addGroup(pnlDetallePlanificaionLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(cmbOperario, 0, 205, Short.MAX_VALUE)))
                .addGap(151, 151, 151)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlDetallePlanificaionLayout.setVerticalGroup(
            pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetallePlanificaionLayout.createSequentialGroup()
                .addGroup(pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDetallePlanificaionLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel14))
                    .addComponent(fhInicioDetallePlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDetallePlanificaionLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel10))
                    .addComponent(cmbOperario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                .addGroup(pnlDetallePlanificaionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addGap(41, 41, 41))
            .addGroup(pnlDetallePlanificaionLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlDetallePlanificacion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle del Plan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbDetallePlan.setFont(new java.awt.Font("Tahoma", 1, 11));
        tbDetallePlan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nro. Orden", "Etapa", "Empleado", "Fecha hora inicio"
            }
        ));
        tbDetallePlan.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbDetallePlan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDetallePlanMouseClicked(evt);
            }
        });
        tbDetallePlan.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tbDetallePlanComponentShown(evt);
            }
        });
        tbDetallePlan.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tbDetallePlanComponentAdded(evt);
            }
        });
        tbDetallePlan.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbDetallePlanPropertyChange(evt);
            }
        });
        tbDetallePlan.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tbDetallePlanAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane4.setViewportView(tbDetallePlan);

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDetallePlanificacionLayout = new javax.swing.GroupLayout(pnlDetallePlanificacion);
        pnlDetallePlanificacion.setLayout(pnlDetallePlanificacionLayout);
        pnlDetallePlanificacionLayout.setHorizontalGroup(
            pnlDetallePlanificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetallePlanificacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnModificar)
                .addContainerGap())
        );
        pnlDetallePlanificacionLayout.setVerticalGroup(
            pnlDetallePlanificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetallePlanificacionLayout.createSequentialGroup()
                .addGroup(pnlDetallePlanificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModificar)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlEstructura.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estructura", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbEstructura.setFont(new java.awt.Font("Tahoma", 1, 11));
        tbEstructura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nro. Orden", "Etapa", "Cargo empleado", "Horas hombre"
            }
        ));
        tbEstructura.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbEstructura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbEstructuraMouseClicked(evt);
            }
        });
        tbEstructura.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tbEstructuraComponentShown(evt);
            }
        });
        tbEstructura.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tbEstructuraComponentAdded(evt);
            }
        });
        tbEstructura.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbEstructuraPropertyChange(evt);
            }
        });
        tbEstructura.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tbEstructuraAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane3.setViewportView(tbEstructura);

        javax.swing.GroupLayout pnlEstructuraLayout = new javax.swing.GroupLayout(pnlEstructura);
        pnlEstructura.setLayout(pnlEstructuraLayout);
        pnlEstructuraLayout.setHorizontalGroup(
            pnlEstructuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEstructuraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlEstructuraLayout.setVerticalGroup(
            pnlEstructuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEstructuraLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText(">>>>");

        jButton2.setText("<<<<");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEstructura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(pnlDetallePlanificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlDetallePlanificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlEstructura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(17, 17, 17)
                        .addComponent(jButton3)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(17, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlDetallePlanificaion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDetallePlanificaion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(jButton3))
                .addGap(55, 55, 55))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbDetallePedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDetallePedidoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDetallePedidoMouseClicked

    private void tbDetallePedidoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tbDetallePedidoComponentShown
}//GEN-LAST:event_tbDetallePedidoComponentShown

    private void tbDetallePedidoComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tbDetallePedidoComponentAdded
}//GEN-LAST:event_tbDetallePedidoComponentAdded

    private void tbDetallePedidoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbDetallePedidoPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDetallePedidoPropertyChange

    private void tbDetallePedidoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tbDetallePedidoAncestorAdded
}//GEN-LAST:event_tbDetallePedidoAncestorAdded

    private void tbEstructuraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEstructuraMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbEstructuraMouseClicked

    private void tbEstructuraComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tbEstructuraComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_tbEstructuraComponentShown

    private void tbEstructuraComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tbEstructuraComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tbEstructuraComponentAdded

    private void tbEstructuraPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbEstructuraPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tbEstructuraPropertyChange

    private void tbEstructuraAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tbEstructuraAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tbEstructuraAncestorAdded

    private void tbDetallePlanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDetallePlanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDetallePlanMouseClicked

    private void tbDetallePlanComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tbDetallePlanComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDetallePlanComponentShown

    private void tbDetallePlanComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tbDetallePlanComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDetallePlanComponentAdded

    private void tbDetallePlanPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbDetallePlanPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDetallePlanPropertyChange

    private void tbDetallePlanAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tbDetallePlanAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDetallePlanAncestorAdded

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        //validarDatosDetalle();

        DetallePlanProduccion det=tmDetallePlanProduccion.getSeletedObject();
        det.setFecHoraPrevistaInicio(fhInicioDetallePlan.getDate());
        det.setObservaciones(txtObservaciones.getText());
        det.setTEmpleados((Empleado) cmbOperario.getSelectedItem());
      //  det.setTMaquinasHerramientaParticular((MaquinaHerramientaParticular) cmbMaquina.getSelectedItem());
        habilitarDatosPlanificacion(false);
        limpiarDatosEtapaPlanificacion();
        tmDetallePlanProduccion.updateTabla();

}//GEN-LAST:event_btnAceptarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        PlanProduccion plan=new PlanProduccion();
        plan.setTDetallesPlans(tmDetallePlanProduccion.getDatos());
        plan.setTPedidos(pedido);
        plan.setTEmpleados((Empleado) cmbResponsable.getSelectedItem());
        plan.setFecHoraPrevistaInicio(fhInicioPlan.getDate());
        HibernateUtil.guardarObjeto(plan);
        Mensajes.mensajeInformacion("Se ha generado el plan correctamente");
        this.dispose();

}//GEN-LAST:event_jButton4ActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        habilitarDatosPlanificacion(true);

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        habilitarDatosPlanificacion(false);
        limpiarDatosEtapaPlanificacion();
    }//GEN-LAST:event_btnCancelarActionPerformed

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
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox cmbOperario;
    private javax.swing.JComboBox cmbResponsable;
    private com.toedter.calendar.JSpinnerDateEditor fhInicioDetallePlan;
    private com.toedter.calendar.JSpinnerDateEditor fhInicioPlan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labefdsfsf;
    private javax.swing.JLabel lblFechaEntrega;
    private javax.swing.JLabel lblFechaNecesidad;
    private javax.swing.JLabel lblRazonSocial;
    private javax.swing.JLabel lblTipoPedido;
    private javax.swing.JPanel pnlDetallePlanificacion;
    private javax.swing.JPanel pnlDetallePlanificaion;
    private javax.swing.JPanel pnlEstructura;
    private javax.swing.JTable tbDetallePedido;
    private javax.swing.JTable tbDetallePlan;
    private javax.swing.JTable tbEstructura;
    private javax.swing.JTextArea txtObservaciones;
    // End of variables declaration//GEN-END:variables
}
