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

import BaseDeDatos.Ventas.PedidoBD;
import Negocio.Exceptiones.ExceptionGestor;
import Negocio.Produccion.Producto;
import Negocio.Produccion.TipoProducto;
import Negocio.Ventas.*;
import Negocio.Ventas.Pedido;
import Presentacion.Mensajes;
import Presentacion.TablaManager;
import Presentacion.Utilidades;
import Presentacion.ValidarTexbox;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

    public PantallaPedidoABM(GestorPedido gestor) {
        super((JDialog) null, true);
        this.gestor = gestor;
        initComponents();
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
        tmDetalle.addSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (tmDetalle.getSeletedObject() != null) {
                    cargarDetalle(tmDetalle.getSeletedObject());
                } else {
                    limpiarDetalle();
                }
            }
        });

        habilitarCargaDetalle(false);
        cargarCombos();
        cargarValidaciones();
        txtFechaEstimada.setText(Utilidades.parseFecha(Utilidades.agregarTiempoFecha(Utilidades.getFechaActual(), 20, 0, 0)));
        pnlInfo.setVisible(false);
        habilitarBaja(false, false, null, "");
    }

    private void cargarValidaciones() {
        ((JTextFieldDateEditor) dtcFechaNecesidad.getDateEditor()).setEditable(false);
        dtcFechaNecesidad.setMinSelectableDate(Utilidades.getFechaActual());
        ValidarTexbox.validarLongitud(txtMotivoBaja, 200);
    }

    private void cargarCombos() {
        cmbTipoProducto.setModel(new DefaultComboBoxModel(gestor.getTipoProductos().toArray()));
        cmbTipoProducto.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                TipoProducto tp = (TipoProducto) cmbTipoProducto.getSelectedItem();
                cmbProducto.setModel(new DefaultComboBoxModel(gestor.getProductos(tp).toArray()));
            }
        });

        cmbPrioridad.setModel(new DefaultComboBoxModel(gestor.getPrioridades().toArray()));
        cmbTipoPedido.setModel(new DefaultComboBoxModel(gestor.getTipoPedidos().toArray()));
    }

    public void habilitarCarga(boolean valor) {
        pnlCliente.setEnabled(valor);

        btnDetalleAgregar.setEnabled(valor);
        btnDetalleElminar.setEnabled(valor);
        tbDetalle.setEnabled(valor);

        cmbPrioridad.setEnabled(valor);
        cmbTipoPedido.setEnabled(valor);
        dtcFechaNecesidad.setEnabled(valor);
    }

    public void habilitarCargaDetalle(boolean valor) {
        Utilidades.habilitarPanel(pnlDetalle,!valor);
        Utilidades.habilitarPanel(pnlDetalleCarga, valor);
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

    public void habilitarBaja(boolean visible, boolean edittable, Date fecha, String motivo) {
        pnlBaja.setVisible(visible);
        txtMotivoBaja.setEditable(edittable);
        txtMotivoBaja.setText(Utilidades.parseString(motivo));
        txtFechaBaja.setText(Utilidades.parseFecha(fecha));

    }

    public void cargar(Pedido pedido) {
        pnlInfo.setVisible(true);
        txtFecha.setText(Utilidades.parseFechaHora(pedido.getFechaGeneracion()));
        txtEstado.setText(pedido.getEstadoPedido().getNombre());
        pnlCliente.setCliente(pedido.getCliente());
        txtFechaEstimada.setText(Utilidades.parseFecha(pedido.getFechaEstimadaEntrega()));
        dtcFechaNecesidad.setDate(pedido.getFechaNecesidad());
        tmDetalle.setDatos(pedido.getDetallePedido());
        cmbPrioridad.setSelectedIndex(pedido.getPrioridad());
        cmbTipoPedido.setSelectedItem(pedido.getTipoPedido());
        if (pedido.getFecBaja() != null) {
            pnlBaja.setVisible(true);
            txtFechaBaja.setText(Utilidades.parseFecha(pedido.getFecBaja()));
            txtMotivoBaja.setText(Utilidades.parseString(pedido.getMotivoBaja()));
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
        dtcFechaNecesidad = new com.toedter.calendar.JDateChooser();
        txtFechaEstimada = new javax.swing.JTextField();
        pnlInfo = new javax.swing.JPanel();
        txtFecha = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        pnlCliente = new Presentacion.BuscadorCliente();
        pnlBaja = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtFechaBaja = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMotivoBaja = new javax.swing.JTextArea();

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

        cmbPrioridad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPrioridadActionPerformed(evt);
            }
        });

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

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Total ($):");

        pnlDetalleCarga.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Tipo Producto:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Producto:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
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

        javax.swing.GroupLayout pnlDetalleCargaLayout = new javax.swing.GroupLayout(pnlDetalleCarga);
        pnlDetalleCarga.setLayout(pnlDetalleCargaLayout);
        pnlDetalleCargaLayout.setHorizontalGroup(
            pnlDetalleCargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleCargaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbTipoProducto, 0, 69, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbProducto, 0, 68, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar)
                .addContainerGap())
        );
        pnlDetalleCargaLayout.setVerticalGroup(
            pnlDetalleCargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleCargaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetalleCargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(cmbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 398, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE))
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
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
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

        txtFechaEstimada.setEditable(false);

        txtFecha.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Fecha/Hora Generación:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Estado:");

        txtEstado.setEditable(false);

        javax.swing.GroupLayout pnlInfoLayout = new javax.swing.GroupLayout(pnlInfo);
        pnlInfo.setLayout(pnlInfoLayout);
        pnlInfoLayout.setHorizontalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlInfoLayout.setVerticalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3)
                .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1))
        );

        pnlCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        javax.swing.GroupLayout pnlPedidoLayout = new javax.swing.GroupLayout(pnlPedido);
        pnlPedido.setLayout(pnlPedidoLayout);
        pnlPedidoLayout.setHorizontalGroup(
            pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                    .addComponent(pnlInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlPedidoLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(cmbPrioridad, 0, 263, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(dtcFechaNecesidad, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                    .addGroup(pnlPedidoLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(cmbTipoPedido, 0, 263, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(labelFechaEstimada)
                        .addGap(22, 22, 22)
                        .addComponent(txtFechaEstimada, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlPedidoLayout.setVerticalGroup(
            pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPedidoLayout.createSequentialGroup()
                .addComponent(pnlInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
                .addContainerGap())
        );

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
                    .addComponent(jButton4)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbPrioridadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPrioridadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPrioridadActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Pedido pedido = gestor.getPedido();
        pedido.setCliente(pnlCliente.getCliente());
        pedido.setDetallePedido(tmDetalle.getDatos());
        pedido.setFechaNecesidad(dtcFechaNecesidad.getDate());
        pedido.setFechaEstimadaEntrega(dtcFechaNecesidad.getDate());
        pedido.setFechaGeneracion(Utilidades.getFechaActual());
        pedido.setPrioridad((byte) cmbPrioridad.getSelectedIndex());
        pedido.setTipoPedido((TipoPedido) cmbTipoPedido.getSelectedItem());
        pedido.setMotivoBaja(Utilidades.parseString(txtMotivoBaja.getText()));

        try {
            gestor.ejecutarCU(pedido);
            gestor.finalizarCU();

        } catch (ExceptionGestor ex) {
            Mensajes.mensajeErrorGenerico(ex.getMessage());
        }


        //JOptionPane.showMessageDialog(this, "El pedido Nro: XX ha sido registrado correctamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnDetalleAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleAgregarActionPerformed
        // TODO add your handling code here:

        limpiarDetalle();
        habilitarCargaDetalle(true);




    }//GEN-LAST:event_btnDetalleAgregarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        gestor.finalizarCU();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void dtcFechaNecesidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtcFechaNecesidadMouseClicked
        // TODO add your handling code here:

        txtFechaEstimada.setText(Utilidades.parseFecha(dtcFechaNecesidad.getDate()));
    }//GEN-LAST:event_dtcFechaNecesidadMouseClicked

    private void btnDetalleElminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleElminarActionPerformed
        // TODO add your handling code here:
        if (tbDetalle.getSelectedRow() != -1) {
            tmDetalle.removeSelectedRow();
            limpiarDetalle();
             txtFechaEstimada.setText(Utilidades.parseFecha(Utilidades.agregarTiempoFecha(Utilidades.getFechaActual(), 20+3*tmDetalle.getSize(), 0, 0)));
        }

    }//GEN-LAST:event_btnDetalleElminarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:

        if (cmbProducto.getSelectedIndex() == -1) {
            Mensajes.mensajeErrorGenerico("Debe seleccionar un producto");
            return;
        }

        try {
            new Short(txtCantidad.getText());
        } catch (Exception e) {
            Mensajes.mensajeErrorGenerico("La cantidad ingresada es incorrecta");
            return;
        }

        DetallePedido nuevoDetalle = new DetallePedido();
        Producto prod = (Producto) cmbProducto.getSelectedItem();
        nuevoDetalle.setCantidad(Integer.parseInt(txtCantidad.getText()));
        nuevoDetalle.setProducto(prod);
        nuevoDetalle.setPrecio(prod.getPrecioUnitario().floatValue());


        for (DetallePedido dp : tmDetalle.getDatos()) {
            if (dp.getProducto().getNombre().equals(nuevoDetalle.getProducto().getNombre())) {
                String mensage =
                        "¡Ya existe un detalle que contiene el producto " + dp.getProducto().getNombre() + "!\n"
                        + "¿Desea agregar la cantidad ingresada al detalle existente?";
                if (Mensajes.mensajeConfirmacionGenerico(mensage)) {
                    dp.setCantidad(dp.getCantidad() + nuevoDetalle.getCantidad());
                    tmDetalle.updateTabla();
                     limpiarDetalle();
                    habilitarCargaDetalle(false);
                }
                return;
            }
        }

        
        tmDetalle.add(nuevoDetalle);
        limpiarDetalle();
        habilitarCargaDetalle(false);
         txtFechaEstimada.setText(Utilidades.parseFecha(Utilidades.agregarTiempoFecha(Utilidades.getFechaActual(), 20+3*tmDetalle.getSize(), 0, 0)));
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:

        limpiarDetalle();
        habilitarCargaDetalle(false);

}//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new GestorPedidoAlta().iniciarCU();
                //new GestorPedidoModificar(PedidoBD.getPedido(9)).iniciar();
                //new GestorPedidoEliminar(PedidoBD.getPedido(6)).iniciar();
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDetalleAgregar;
    private javax.swing.JButton btnDetalleElminar;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelFechaEstimada;
    private javax.swing.JPanel pnlBaja;
    private Presentacion.BuscadorCliente pnlCliente;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlDetalleCarga;
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JPanel pnlPedido;
    private javax.swing.JTable tbDetalle;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtFechaBaja;
    private javax.swing.JTextField txtFechaEstimada;
    private javax.swing.JTextArea txtMotivoBaja;
    private javax.swing.JTextField txtSubTotal;
    // End of variables declaration//GEN-END:variables
}
