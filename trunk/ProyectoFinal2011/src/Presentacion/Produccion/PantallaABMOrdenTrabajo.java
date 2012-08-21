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

import BaseDeDatos.HibernateUtil;
import BaseDeDatos.Ventas.PedidoBD;
import Negocio.Administracion.Empleado;
import Negocio.Produccion.DetallePlanProduccion;
import Negocio.Produccion.GestorOrdenTrabajo;
import Negocio.Produccion.OrdenTrabajo;
import Negocio.Produccion.PlanProduccion;
import Negocio.Ventas.DetallePedido;
import Negocio.Ventas.Pedido;
import Presentacion.IniciadorDeVentanas;
import Presentacion.Mensajes;
import Presentacion.TablaManager;
import Presentacion.Utilidades;
import Presentacion.ValidarTexbox;
import com.toedter.calendar.JTextFieldDateEditor;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Rodrigo
 */
public class PantallaABMOrdenTrabajo extends javax.swing.JDialog {

    private TablaManager<Pedido> tmPedido;
    private TablaManager<DetallePlanProduccion> tmEtapas;
    private GestorOrdenTrabajo gestor;

    /** Creates new form PantallaConsultarPedido */
    public PantallaABMOrdenTrabajo(java.awt.Frame parent, boolean modal, GestorOrdenTrabajo orden) {
        super(parent, modal);

        initComponents();
        HibernateUtil.getSessionFactory();
        inicializarTablas();
        cargarValidaciones();
        IniciadorDeVentanas.iniciarVentana(this, this.getWidth(), this.getHeight());
        gestor=orden;
        txtFecha.setText(Utilidades.parseFechaHora(new Date()));

    }

    public PantallaABMOrdenTrabajo(JDialog parent, boolean modal, GestorOrdenTrabajo orden) {
        super(parent, modal);

        initComponents();
        chkMostrarVigentes.setVisible(false);
        HibernateUtil.getSessionFactory();
        inicializarTablas();
        cargarValidaciones();
        IniciadorDeVentanas.iniciarVentana(this, this.getWidth(), this.getHeight());
        gestor=orden;
        txtFecha.setText(Utilidades.parseFechaHora(new Date()));

    }
    private void inicializarTablas() {
        tmPedido = new TablaManager<Pedido>(tbPedidos) {

            @Override
            public Vector getCabecera() {
                Vector cabcera = new Vector();

                cabcera.add("Nro. pedido");//col 3
                cabcera.add("Fecha generación");//col 4
                cabcera.add("Razón social");//col 1
                cabcera.add("CUIT");//col 2
                cabcera.add("Estado");
                cabcera.add("Monto total");
                return cabcera;

            }

            @Override
            public Vector ObjetoFila(Pedido elemento) {
                Vector fila = new Vector();

                fila.add(elemento.getIdPedido());//col 3
                fila.add(Utilidades.parseFecha(elemento.getFechaGeneracion()));//col 4
                fila.add(elemento.getCliente().getRazonSocial());//col 1
                fila.add(elemento.getCliente().getCuit());//col 2
                fila.add(elemento.getEstadoPedido());
                //calculo monto total
                float montoTotal=0f;
                for(DetallePedido dp:elemento.getDetallePedido())
                    montoTotal+=dp.getCantidad()*dp.getPrecio();
                fila.add("$ "+montoTotal);
                return fila;
            }
        };
//        tmPedido.addSelectionListener(new ListSelectionListener() {
//
//            public void valueChanged(ListSelectionEvent e) {
//              if(tmPedido.getSeletedObject()!=null)
//                  tmDetalle.setDatos(tmPedido.getSeletedObject().getDetallePedido());
//              else
//                  tmDetalle.limpiar();
//            }
//        });
        ////////////////////////////////////////////////////////

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
                return cabcera;

            }

            @Override
            public Vector ObjetoFila(DetallePlanProduccion elemento) {
                Vector fila = new Vector();

                fila.add(elemento.getTEtapasProduccionEspecifica().getProducto().getNombre());
                fila.add(elemento.getTEtapasProduccionEspecifica().getNumeroOrden());
                fila.add(elemento.getTEtapasProduccionEspecifica().getEtapaProduccion().getNombre());
                fila.add(elemento.getTEmpleados().getApellido() +", "+ elemento.getTEmpleados().getNombre());
                fila.add(elemento.getMaquinaParticular().getNombre());
                fila.add(Utilidades.parseFechaHora(elemento.getFecHoraPrevistaInicio()));
               
                return fila;
            }
        };

    }

    private void cargarValidaciones() {
        //**********************Validacion de textBox********************//
        ValidarTexbox.validarInt(txtNroPedido);
        ValidarTexbox.validarLongitud(txtNroPedido, 8);
        ValidarTexbox.validarLong(txtCUIL);
        ValidarTexbox.validarLongitud(txtCUIL, 11);
        ValidarTexbox.validarLongitud(txtRazonSocial, 50);

        //**********************Validacion de Fecha********************//
        ((JTextFieldDateEditor) dtcFechaGeneracionHasta.getDateEditor()).setEditable(false);
        ((JTextFieldDateEditor) dtcFechaGeneracionDesde.getDateEditor()).setEditable(false);

        dtcFechaGeneracionHasta.setMaxSelectableDate(Utilidades.getFechaActual());
        dtcFechaGeneracionDesde.setMaxSelectableDate(Utilidades.getFechaActual());

        dtcFechaGeneracionHasta.setDate(Utilidades.getFechaActual());
        dtcFechaGeneracionDesde.setDate(Utilidades.agregarTiempoFecha(Utilidades.getFechaActual(), 0, -1, 0));

        //**********************Validacion de Fecha parte2********************//

        dtcFechaGeneracionHasta.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtcFechaGeneracionDesde.setMaxSelectableDate(dtcFechaGeneracionHasta.getDate());
            }
        });

        dtcFechaGeneracionDesde.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtcFechaGeneracionHasta.setMinSelectableDate(dtcFechaGeneracionDesde.getDate());
            }
        });

        /************************Validacion de botones **********************************/
        btnCancelar.setEnabled(true);
    
        tmPedido.addSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                boolean var = false;
                if (tmPedido.getSeletedObject() != null) {
                    if (tmPedido.getSeletedObject().getFecBaja() == null) {
                        var = true;
                    }
                }

                btnCancelar.setEnabled(var);
               
            }
        });
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
        chkMostrarVigentes = new javax.swing.JCheckBox();
        btnBuscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dtcFechaGeneracionDesde = new com.toedter.calendar.JDateChooser();
        dtcFechaGeneracionHasta = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtRazonSocial = new javax.swing.JTextField();
        txtCUIL = new javax.swing.JTextField();
        txtNroPedido = new javax.swing.JTextField();
        pnlPedidos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPedidos = new javax.swing.JTable();
        pnlDetalle = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbEtapasPlanificadas = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblNroPedido = new javax.swing.JLabel();
        lblFechaPlanificacion = new javax.swing.JLabel();
        lblFechaInicio = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacion = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Orden de Trabajo");

        pnlBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Búsqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        chkMostrarVigentes.setFont(new java.awt.Font("Tahoma", 1, 11));
        chkMostrarVigentes.setSelected(true);
        chkMostrarVigentes.setText("Mostrar Vigentes");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha Generación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Desde:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Hasta:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(12, 12, 12)
                        .addComponent(dtcFechaGeneracionHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(10, 10, 10)
                        .addComponent(dtcFechaGeneracionDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtcFechaGeneracionHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtcFechaGeneracionDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Razón Social:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Nro. Pedido:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("CUIT:");

        javax.swing.GroupLayout pnlBuscarLayout = new javax.swing.GroupLayout(pnlBuscar);
        pnlBuscar.setLayout(pnlBuscarLayout);
        pnlBuscarLayout.setHorizontalGroup(
            pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBuscarLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBuscarLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCUIL, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBuscarLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkMostrarVigentes)
                    .addComponent(btnBuscar))
                .addContainerGap(208, Short.MAX_VALUE))
        );
        pnlBuscarLayout.setVerticalGroup(
            pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBuscarLayout.createSequentialGroup()
                .addGroup(pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBuscarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCUIL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtNroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlBuscarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(chkMostrarVigentes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(btnBuscar))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlPedidos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pedidos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
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

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Fecha planificación:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("Número pedido:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setText("Fecha inicio:");

        lblNroPedido.setText("xxxxxxxx");

        lblFechaPlanificacion.setText("xxxxxxxx");

        lblFechaInicio.setText("xxxxxxxx");

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlDetalleLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFechaPlanificacion))
                    .addGroup(pnlDetalleLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNroPedido)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFechaInicio)
                .addGap(176, 176, 176))
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblNroPedido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(lblFechaPlanificacion)
                    .addComponent(lblFechaInicio))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Orden de trabajo"));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setText("Observación:");

        txtObservacion.setColumns(20);
        txtObservacion.setRows(5);
        jScrollPane2.setViewportView(txtObservacion);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setText("Fecha generación:");

        txtFecha.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(228, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        tmPedido.setDatos(
                PedidoBD.getPedidosPlanificados(
                txtRazonSocial.getText(),
                txtCUIL.getText(),
                txtNroPedido.getText(),
                dtcFechaGeneracionDesde.getDate(),
                dtcFechaGeneracionHasta.getDate(),
                chkMostrarVigentes.isSelected(),false));

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       this.dispose();

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        PlanProduccion plan= ((Pedido)tmPedido.getSeletedObject()).getPlanProduccion();       
        List<Empleado> empleados=plan.getEmpleadosInvolucrados();
        
        List<OrdenTrabajo> orden=new ArrayList<OrdenTrabajo>();
        
        OrdenTrabajo tempOrden=null;
        Date fecha=new Date();
        for(int i=0; i<empleados.size();i++){
            tempOrden=new OrdenTrabajo();
            tempOrden.setTEmpleados(empleados.get(i));
            tempOrden.setFecEmision(fecha);
            tempOrden.setTEordenTrabajo(gestor.estadoGenerado());
            tempOrden.setObservaciones(txtObservacion.getText());
            gestor.guardarOrdenTrabajo(tempOrden);
            
            
            List<DetallePlanProduccion> detalle=plan.getDetallePlan(empleados.get(i));
             tempOrden.setTDetallesPlans(new HashSet<DetallePlanProduccion>(detalle));
            orden.add(tempOrden);


//            for(int j=0; j<detalle.size();j++)
//            {
//                detalle.get(j).setTOrdenesTrabajo(tempOrden);
////                detalle.get(j).setTEdetallePlan(gestor.estadoDetalleEnEjecucion());
//                
//            }

           
            gestor.actualizarDetalle(detalle);

            gestor.modificarEstadoPedido(detalle.get(0).getPlanProduccion().getPedido());
            gestor.modificarEstadoPlan(detalle.get(0).getPlanProduccion());

        }

        if(orden.size()==1){
            Mensajes.mensajeInformacion("La orden de trabajo ha sido generado exitosamente "
                + "\n su numero de orden es: \n"
                + tempOrden.getIdOrdenTrabajo()+" Empleado: "+ tempOrden.getTEmpleados().getApellido() + ", "+ tempOrden.getTEmpleados().getNombre());
        }
         else{
            String mensaje="Las ordenes de trabajo han sido generadas exitosamente "
                + "\n los números de ordenes son: ";
            for(int i=0;i<orden.size();i++){
                mensaje+= "\n    " + orden.get(i).getIdOrdenTrabajo() + " Empleado: "+ orden.get(i).getTEmpleados().getApellido() + ", "+ orden.get(i).getTEmpleados().getNombre();
            }
            Mensajes.mensajeInformacion( mensaje);
            
         }
        this.dispose();



    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPedidosMouseClicked
        // TODO add your handling code here:
        Pedido ped=(Pedido)tmPedido.getSeletedObject();
        lblNroPedido.setText(ped.getIdPedido()+"");
        lblFechaInicio.setText(Utilidades.parseFecha(ped.getPlanProduccion().getFecHoraPrevistaInicio()));
        lblFechaPlanificacion.setText(Utilidades.parseFecha(ped.getPlanProduccion().getFecGeneracion()));
        tmEtapas.setDatos(ped.getPlanProduccion().getDetallePlan());
        tmEtapas.updateTabla();

    }//GEN-LAST:event_tbPedidosMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                PantallaABMOrdenTrabajo dialog = new PantallaABMOrdenTrabajo(new javax.swing.JFrame(), true, new GestorOrdenTrabajo());
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JCheckBox chkMostrarVigentes;
    private com.toedter.calendar.JDateChooser dtcFechaGeneracionDesde;
    private com.toedter.calendar.JDateChooser dtcFechaGeneracionHasta;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblFechaPlanificacion;
    private javax.swing.JLabel lblNroPedido;
    private javax.swing.JPanel pnlBuscar;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlPedidos;
    private javax.swing.JTable tbEtapasPlanificadas;
    private javax.swing.JTable tbPedidos;
    private javax.swing.JTextField txtCUIL;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNroPedido;
    private javax.swing.JTextArea txtObservacion;
    private javax.swing.JTextField txtRazonSocial;
    // End of variables declaration//GEN-END:variables
}
