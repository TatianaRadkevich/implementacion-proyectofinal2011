/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaRegistrarPedido.java
 *
 * Created on 14/06/2011, 05:18:55
 */
package Presentacion.Ventas;

import BaseDeDatos.Administracion.EmpleadoBD;
import BaseDeDatos.Ventas.EstadoPedidoBD;
import Negocio.Exceptiones.*;
import Negocio.Produccion.*;
import Negocio.Ventas.*;
import Presentacion.ZLinkers.*;
import Presentacion.*;
import Presentacion.AutoFill.SelectionListener;
import Presentacion.ZLinkers.ZLFormatedTextField.Formato;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Ivan
 */
public class PantallaPedidoABM extends javax.swing.JDialog {

    /** Creates new form PantallaRegistrarPedido */
    private GestorPedido gestor;
    private TablaManager<DetallePedido> tmDetalle;
    private ZLObject<Pedido> zlo;
    private GestorPedido.GestorDetallePedido gesDet;
    private ZLObject<DetallePedido> zldp;
    private AutoFill<Cliente> auto;

    private PantallaPedidoABM(Window parent, final GestorPedido gestor) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.gestor = gestor;
        initComponents();

        auto = new AutoFill<Cliente>(txtBuscar) {

            @Override
            protected List<Cliente> getData(String texto) {
                return GestorPedido.buscarClientes(texto);
            }
        };
        auto.addSelectionListener(new SelectionListener<Cliente>() {

            public void objectSelected(Cliente object) {
                cargarCliente(object);
            }
        });



        zlo = new ZLObject<Pedido>(gestor.getPedido());
        zlo.add("generacion", false, new ZLFormatedTextField(txtFecha, Formato.DateTime));
        zlo.add("idpedido", false, new ZLTextField(txtNroPedido));
        zlo.add("estado", false, new ZLFormatedTextField(txtEstado, Formato.Default));
        zlo.add("cliente", new ZLAutoFill(auto));
        zlo.add("motivo", false, new ZLTextField(txtMotivoBaja));
        zlo.add("cliente", false, new ZLTextField(txtFechaBaja));

        //zlo.add("prioridad", new ZLComboBox(cmbPrioridad));
        
        cmbPrioridad.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try{
                    Utilidades.componenteCorrecto(cmbPrioridad);
                gestor.getPedido().setPrioridad((byte) cmbPrioridad.getSelectedIndex());
                }catch(NegocioException ne){Utilidades.componenteError(cmbPrioridad, ne.getMessage());}
            }
        });
        cmbPrioridad.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                try{
                    Utilidades.componenteCorrecto(cmbPrioridad);
                gestor.getPedido().setPrioridad((byte) cmbPrioridad.getSelectedIndex());
                }catch(NegocioException ne){Utilidades.componenteError(cmbPrioridad, ne.getMessage());}
            }
        });
        zlo.add("tipo", new ZLComboBox(cmbTipoPedido));
        zlo.add("necesidad", new ZLCalendar(dtcFechaNecesidad).setMaxDate(Utilidades.getFechaActual()));
        zlo.add("estimada", new ZLFormatedTextField(txtFechaEstimada, Formato.Date));

        zldp = new ZLObject<DetallePedido>(DetallePedido.class);
        zldp.add("producto", new ZLComboBox(cmbProducto));
        zldp.add("cantidad", new ZLTextField(txtCantidad));
        //Seteo de variables//

        tmDetalle = new TablaManager<DetallePedido>(tbDetalle) {

            @Override
            public Vector ObjetoFila(DetallePedido elemento) {
                Vector fila = new Vector();
                fila.add(elemento.getProducto().codigoMerge());
                fila.add(elemento.getProducto().getDescripcion());
                fila.add(elemento.getProducto().getPrecioUnitario());
                fila.add(elemento.getCantidad());
                fila.add(elemento.getProducto().getPrecioUnitario().floatValue() * elemento.getCantidad());
                return fila;
            }

            @Override
            public Vector getCabecera() {
                Vector cabecera = new Vector();
                cabecera.add("Codigo");
                cabecera.add("Descripcion");
                cabecera.add("Precio Unit.($)");
                cabecera.add("Cantidad (en unidades)");
                cabecera.add("Sub Total($)");
                return cabecera;
            }
        };
        tmDetalle.addModificationListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
                float total = 0;
                for (DetallePedido dp : tmDetalle.getDatos()) {
                    total += dp.getPrecio() * dp.getCantidad();
                }
                txtSubTotal.setText(total + "");
            }
        });


        Utilidades.comboCargar(cmbTipoProducto, gestor.getTipoProductos());
        Utilidades.comboCargar(cmbPrioridad, gestor.getPrioridades());
        Utilidades.comboCargar(cmbTipoPedido, gestor.getTipoPedidos());
        txtFechaEstimada.setValue(Utilidades.agregarTiempoFecha(Utilidades.getFechaActual(), 20, 0, 0));
        btnSalir.setVisible(false);
        zlo.load();
        cmbPrioridad.setSelectedIndex(gestor.getPedido().getPrioridad());
        tmDetalle.setDatos(gestor.getPedido().getDetallePedido());
    }

    public void cargarCliente(Cliente valor) {
        txtCUIT.setText("");
        txtRazonSocial.setText("");
        if (valor != null) {
            txtCUIT.setText(valor.getCuit());
            txtRazonSocial.setText(valor.getRazonSocial());
        }
    }

    public void cargarDetalle(DetallePedido dp) {

        txtCantidad.setText(dp.getCantidad() + "");
        cmbTipoProducto.setSelectedItem(dp.getProducto().getTTproducto());
        cmbProducto.setSelectedItem(dp.getProducto());
    }

    public void limpiarDetalle() {
        txtCantidad.setText("");
        cmbTipoProducto.setSelectedIndex(-1);
        cmbProducto.setSelectedIndex(-1);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        pnlPedido = new javax.swing.JPanel();
        cmbPrioridad = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbTipoPedido = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        labelFechaEstimada = new javax.swing.JLabel();
        pnlDetalle = new javax.swing.JPanel();
        btnDetalleAgregar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDetalle = new javax.swing.JTable();
        txtSubTotal = new javax.swing.JTextField();
        btnDetalleElminar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        pnlDetalleCarga = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtCantidad = new javax.swing.JTextField();
        cmbTipoProducto = new javax.swing.JComboBox();
        cmbProducto = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        dtcFechaNecesidad = new com.toedter.calendar.JDateChooser();
        pnlInfo = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtNroPedido = new javax.swing.JTextField();
        txtFecha = new javax.swing.JFormattedTextField();
        txtEstado = new javax.swing.JFormattedTextField();
        pnlCliente = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        txtRazonSocial = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtCUIT = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBusquedaAvanzada = new javax.swing.JButton();
        btnNuevoCliente = new javax.swing.JButton();
        txtFechaEstimada = new javax.swing.JFormattedTextField();
        pnlBaja = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtFechaBaja = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMotivoBaja = new javax.swing.JTextArea();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Pedido");

        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Aceptar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        pnlPedido.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Pedido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Prioridad:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("Tipo Pedido:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setText("Fecha Necesidad:");

        labelFechaEstimada.setFont(new java.awt.Font("Tahoma", 1, 11));
        labelFechaEstimada.setText("Fecha Estimada:");

        pnlDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        btnDetalleAgregar.setText("+");
        btnDetalleAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleAgregarActionPerformed(evt);
            }
        });

        tbDetalle.setModel(new javax.swing.table.DefaultTableModel());
        jScrollPane2.setViewportView(tbDetalle);

        txtSubTotal.setEditable(false);

        btnDetalleElminar.setText("-");
        btnDetalleElminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleElminarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Total ($):");

        pnlDetalleCarga.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setText("Tipo Producto:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setText("Producto:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel12.setText("Cantidad:");

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        cmbTipoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoProductoActionPerformed(evt);
            }
        });

        cmbProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProductoActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel15.setText("Precio unit.:");

        txtPrecio.setEditable(false);

        javax.swing.GroupLayout pnlDetalleCargaLayout = new javax.swing.GroupLayout(pnlDetalleCarga);
        pnlDetalleCarga.setLayout(pnlDetalleCargaLayout);
        pnlDetalleCargaLayout.setHorizontalGroup(
            pnlDetalleCargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleCargaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetalleCargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetalleCargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTipoProducto, javax.swing.GroupLayout.Alignment.TRAILING, 0, 250, Short.MAX_VALUE)
                    .addComponent(cmbProducto, javax.swing.GroupLayout.Alignment.TRAILING, 0, 250, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlDetalleCargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetalleCargaLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetalleCargaLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlDetalleCargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDetalleCargaLayout.setVerticalGroup(
            pnlDetalleCargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleCargaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetalleCargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDetalleCargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetalleLayout.createSequentialGroup()
                        .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlDetalleLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 371, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDetalleElminar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDetalleAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(pnlDetalleCarga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addComponent(pnlDetalleCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDetalleLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(pnlDetalleLayout.createSequentialGroup()
                        .addComponent(btnDetalleAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDetalleElminar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        dtcFechaNecesidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dtcFechaNecesidadMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Fecha-Hora Generación:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Estado:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel16.setText("Nro. Pedido:");

        txtNroPedido.setEditable(false);
        txtNroPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtFecha.setEditable(false);
        txtFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("d/MM/yy - H:mm"))));
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtEstado.setEditable(false);
        txtEstado.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout pnlInfoLayout = new javax.swing.GroupLayout(pnlInfo);
        pnlInfo.setLayout(pnlInfoLayout);
        pnlInfoLayout.setHorizontalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNroPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlInfoLayout.setVerticalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel16)
                .addComponent(txtNroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1)
                .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        txtRazonSocial.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("CUIT:"); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel13.setText("Razón Social:"); // NOI18N

        txtCUIT.setEditable(false);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel14.setText("Raxon Social:");

        btnBusquedaAvanzada.setText("Busqueda Avanzada");
        btnBusquedaAvanzada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusquedaAvanzadaActionPerformed(evt);
            }
        });

        btnNuevoCliente.setText("Nuevo Cliente");
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlClienteLayout = new javax.swing.GroupLayout(pnlCliente);
        pnlCliente.setLayout(pnlClienteLayout);
        pnlClienteLayout.setHorizontalGroup(
            pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                    .addGroup(pnlClienteLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCUIT, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRazonSocial, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
                    .addGroup(pnlClienteLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBusquedaAvanzada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNuevoCliente)))
                .addContainerGap())
        );
        pnlClienteLayout.setVerticalGroup(
            pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClienteLayout.createSequentialGroup()
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevoCliente)
                    .addComponent(btnBusquedaAvanzada))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCUIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtFechaEstimada.setEditable(false);

        javax.swing.GroupLayout pnlPedidoLayout = new javax.swing.GroupLayout(pnlPedido);
        pnlPedido.setLayout(pnlPedidoLayout);
        pnlPedidoLayout.setHorizontalGroup(
            pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlInfo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlPedidoLayout.createSequentialGroup()
                        .addGroup(pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPedidoLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(cmbPrioridad, 0, 247, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18))
                            .addGroup(pnlPedidoLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(cmbTipoPedido, 0, 247, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(labelFechaEstimada)
                                .addGap(23, 23, 23)))
                        .addGroup(pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtcFechaNecesidad, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(txtFechaEstimada, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlPedidoLayout.setVerticalGroup(
            pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPedidoLayout.createSequentialGroup()
                .addComponent(pnlInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(pnlCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(cmbPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addComponent(dtcFechaNecesidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(labelFechaEstimada)
                    .addComponent(txtFechaEstimada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlBaja.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cancelación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Fecha:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setText("Motivo:");

        txtFechaBaja.setEditable(false);

        txtMotivoBaja.setLineWrap(true);
        txtMotivoBaja.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txtMotivoBaja);

        javax.swing.GroupLayout pnlBajaLayout = new javax.swing.GroupLayout(pnlBaja);
        pnlBaja.setLayout(pnlBajaLayout);
        pnlBajaLayout.setHorizontalGroup(
            pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBajaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        pnlBajaLayout.setVerticalGroup(
            pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBajaLayout.createSequentialGroup()
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlBaja, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlPedido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSalir)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlBaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(btnSalir))
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            zlo.save();
            gestor.getPedido().setEmpleado(EmpleadoBD.listarEmpleado().get(0));
            gestor.setMotivoBaja(txtMotivoBaja.getText());
            gestor.aceptar();
            this.dispose();
        } catch (NegocioException ex) {
            Mensajes.mensajeErrorGenerico(ex.getMessage());
        }


        //JOptionPane.showMessageDialog(this, "El pedido Nro: XX ha sido registrado correctamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnDetalleAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleAgregarActionPerformed
        // TODO add your handling code here:
        try {
            limpiarDetalle();
            gesDet = gestor.nuevoDetallePedido();
            zldp.setObjeto(gesDet.getDetalle());
            zldp.load();
            pnlDetalleCarga.setVisible(true);
            btnDetalleAgregar.setEnabled(false);
            btnDetalleElminar.setEnabled(false);
        } catch (Exception e) {
            Mensajes.mensajeErrorGenerico(e.getMessage());
        }

    }//GEN-LAST:event_btnDetalleAgregarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void dtcFechaNecesidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtcFechaNecesidadMouseClicked
        // TODO add your handling code here:

        txtFechaEstimada.setText(Utilidades.parseFecha(dtcFechaNecesidad.getDate()));
    }//GEN-LAST:event_dtcFechaNecesidadMouseClicked

    private void btnDetalleElminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleElminarActionPerformed
        // TODO add your handling code here:

        try {
            limpiarDetalle();
            gestor.eliminarDetallePedido(tmDetalle.getSeletedObject());
            tmDetalle.setDatos(gestor.getPedido().getDetallePedido());
        } catch (Exception e) {
            Mensajes.mensajeErrorGenerico(e.getMessage());
        }


    }//GEN-LAST:event_btnDetalleElminarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:


        try {

            zldp.save();
            gesDet.aceptar();
            zldp.setObjeto(null);
            pnlDetalleCarga.setVisible(false);
            tmDetalle.setDatos(gestor.getPedido().getDetallePedido());
            btnCancelarActionPerformed(evt);

        } catch (NegocioException ex) {
            Mensajes.mensajeErrorGenerico(ex.getMessage());
        }

    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:

        limpiarDetalle();
        pnlDetalleCarga.setVisible(false);
           btnDetalleAgregar.setEnabled(true);
            btnDetalleElminar.setEnabled(true);

}//GEN-LAST:event_btnCancelarActionPerformed

    private void cmbTipoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoProductoActionPerformed
        // TODO add your handling code here:
        if (cmbTipoProducto.getSelectedItem() != null) {
            Utilidades.comboCargar(cmbProducto, gestor.getProductos((TipoProducto) cmbTipoProducto.getSelectedItem()));
        } else {
            cmbProducto.setSelectedItem(null);
            cmbProducto.removeAllItems();
        }

    }//GEN-LAST:event_cmbTipoProductoActionPerformed

    private void cmbProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProductoActionPerformed
        // TODO add your handling code here:

        if (cmbProducto.getSelectedItem() != null) {
            Producto prod = (Producto) cmbProducto.getSelectedItem();
            txtPrecio.setText(prod.getPrecioUnitario().toString());

            Utilidades.comboSeleccionarSinEvento(cmbTipoProducto, prod.getTTproducto());
        }
    }//GEN-LAST:event_cmbProductoActionPerformed

    private void btnBusquedaAvanzadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaAvanzadaActionPerformed
        // TODO add your handling code here:
        Cliente c = PantallaClienteConsultar.iniciarConsultarCliente(this);
        if (c != null) {
            auto.setValue(c);
        }

    }//GEN-LAST:event_btnBusquedaAvanzadaActionPerformed

    private void btnNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClienteActionPerformed
        // TODO add your handling code here:
        Cliente c = PantallaClienteABM.iniciarClienteAlta(this);
        if (c != null) {
            auto.setValue(c);
        }

    }//GEN-LAST:event_btnNuevoClienteActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                iniciarRegistrarPedido(null);
            }
        });
    }

    public static void iniciarRegistrarPedido(Window parent) {
        PantallaPedidoABM interfaz = new PantallaPedidoABM(parent, GestorPedido.getGestorPedidoRegistrar());
        interfaz.setTitle("Registrar Pedido");
        interfaz.pnlBaja.setVisible(false);
        interfaz.pnlInfo.setVisible(false);

        interfaz.pack();
        interfaz.pnlDetalleCarga.setVisible(false);
        interfaz.setVisible(true);

    }

    public static void iniciarModificarPedido(Window parent, Pedido p) {
        if (p == null) {
            throw new NegocioException("Elija el pedido que quiere modificar");
        }

        if (p.getEstadoPedido().equals(EstadoPedidoBD.getEstadoNoAutorizado()) == false) {
            if (p.getEstadoPedido().equals(EstadoPedidoBD.getEstadoAutorizadoPendiente()) == false) {
                if (p.getEstadoPedido().equals(EstadoPedidoBD.getEstadoPlanificado()) == false) {
                    throw new NegocioException(
                            "Solo los pedidos 'No Autorizados', 'Autorizados/Pendientes'"
                            + " o 'Planificados pueden ser modificados'");
                }
            }
        }

        PantallaPedidoABM interfaz = new PantallaPedidoABM(parent, GestorPedido.getGestorPedidoModificar(p));
        interfaz.setTitle("Modificar Pedido");

        Utilidades.habilitarPanel(interfaz.pnlCliente, false);
        interfaz.pnlBaja.setVisible(false);
        interfaz.pack();
        interfaz.pnlDetalleCarga.setVisible(false);
        interfaz.setVisible(true);
    }

    public static void iniciarCancelarPedido(Window parent, Pedido p) {
        if (p == null) {
            throw new NegocioException("Elija el pedido que quiere eliminar");
        }

        if (p.getEstadoPedido().equals(EstadoPedidoBD.getEstadoNoAutorizado()) == false) {
            if (p.getEstadoPedido().equals(EstadoPedidoBD.getEstadoAutorizadoPendiente()) == false) {
                if (p.getEstadoPedido().equals(EstadoPedidoBD.getEstadoPlanificado()) == false) {
                    throw new NegocioException(
                            "Solo los pedidos 'No Autorizados', 'Autorizados/Pendientes'"
                            + " o 'Planificados pueden ser cancelados'");
                }
            }
        }

        PantallaPedidoABM interfaz = new PantallaPedidoABM(parent, GestorPedido.getGestorPedidoCancelar(p));
        interfaz.setTitle("Cancelar Pedido");
        Utilidades.habilitarPanel(interfaz.pnlPedido, false);
        interfaz.pack();
        interfaz.pnlDetalleCarga.setVisible(false);
        interfaz.setVisible(true);
    }

    public static void iniciarVerPedido(Window parent, Pedido p) {
        GestorPedido gestVer=new GestorPedido(p) {
            @Override
            public void aceptar() {
            }
        };
        PantallaPedidoABM interfaz = new PantallaPedidoABM(parent, gestVer);
        interfaz.setTitle("Ver Pedido");

        Utilidades.habilitarPanel(interfaz.pnlPedido, false);
        Utilidades.habilitarPanel(interfaz.pnlBaja, false);
        if (p.getFecBaja() == null) {
            interfaz.pnlBaja.setVisible(false);
        }
        interfaz.btnSalir.setVisible(true);
        interfaz.btnAceptar.setVisible(false);
        interfaz.btnCancelar.setVisible(false);

        interfaz.pack();
        interfaz.pnlDetalleCarga.setVisible(false);
        interfaz.setVisible(true);
    }
    // <editor-fold defaultstate="collapsed" desc="variables">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBusquedaAvanzada;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDetalleAgregar;
    private javax.swing.JButton btnDetalleElminar;
    private javax.swing.JButton btnNuevoCliente;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cmbPrioridad;
    private javax.swing.JComboBox cmbProducto;
    private javax.swing.JComboBox cmbTipoPedido;
    private javax.swing.JComboBox cmbTipoProducto;
    private com.toedter.calendar.JDateChooser dtcFechaNecesidad;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelFechaEstimada;
    private javax.swing.JPanel pnlBaja;
    private javax.swing.JPanel pnlCliente;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlDetalleCarga;
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JPanel pnlPedido;
    private javax.swing.JTable tbDetalle;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCUIT;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JFormattedTextField txtEstado;
    private javax.swing.JFormattedTextField txtFecha;
    private javax.swing.JTextField txtFechaBaja;
    private javax.swing.JFormattedTextField txtFechaEstimada;
    private javax.swing.JTextArea txtMotivoBaja;
    private javax.swing.JTextField txtNroPedido;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtSubTotal;
    // End of variables declaration//GEN-END:variables
// </editor-fold>
}
