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
import Negocio.Ventas.*;
import Negocio.Ventas.Pedido;
import Presentacion.Mensajes;
import Presentacion.TablaManager;
import Presentacion.Utilidades;
import Presentacion.ValidarTexbox;
import com.toedter.calendar.JTextFieldDateEditor;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Ivan
 */
public class PantallaPedidoABM extends javax.swing.JDialog {

    /** Creates new form PantallaRegistrarPedido */
    private GestorPedido gestor;
    private TablaManager<DetallePedido> tablaDetalle;

    public PantallaPedidoABM(GestorPedido gestor) {
        super((JDialog) null, true);
        this.gestor = gestor;
        initComponents();
        //Seteo de variables//
        tablaDetalle = new TablaManager<DetallePedido>(tbDetalle) {

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
        tablaDetalle.addModificationListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
                float total = 0;
                for (DetallePedido dp : tablaDetalle.getDatos()) {
                    total += dp.getPrecio() * dp.getCantidad();
                }
                txtSubTotal.setText(total + "");
            }
        });
        cargarCombos();
        cargarValidaciones();
        pnlInfo.setVisible(false);
        habilitarBaja(false, false, null, "");
    }

    private void cargarValidaciones() {
        ((JTextFieldDateEditor) dtcFechaNecesidad.getDateEditor()).setEditable(false);
        dtcFechaNecesidad.setMinSelectableDate(Utilidades.getFechaActual());
        ValidarTexbox.validarLongitud(txtMotivoBaja, 200);
    }

    private void cargarCombos() {
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
        dtcFechaNecesidad.setDate(pedido.getFechaSolicitada());
        tablaDetalle.setDatos(pedido.getDetallePedido());
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
        PaneDetalle = new javax.swing.JPanel();
        txtSubTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnDetalleElminar = new javax.swing.JButton();
        btnDetalleAgregar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDetalle = new javax.swing.JTable();
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

        pnlPedido.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Pedido"));

        cmbPrioridad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPrioridadActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Prioridad:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Tipo Pedido:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Fecha Necesidad:");

        labelFechaEstimada.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelFechaEstimada.setText("Fecha Estimada:");

        pnlDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));

        PaneDetalle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtSubTotal.setEditable(false);

        jLabel5.setText("Total ($):");

        btnDetalleElminar.setText("-");
        btnDetalleElminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleElminarActionPerformed(evt);
            }
        });

        btnDetalleAgregar.setText("+");
        btnDetalleAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleAgregarActionPerformed(evt);
            }
        });

        tbDetalle.setModel(new javax.swing.table.DefaultTableModel());
        jScrollPane2.setViewportView(tbDetalle);

        javax.swing.GroupLayout PaneDetalleLayout = new javax.swing.GroupLayout(PaneDetalle);
        PaneDetalle.setLayout(PaneDetalleLayout);
        PaneDetalleLayout.setHorizontalGroup(
            PaneDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PaneDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneDetalleLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PaneDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDetalleElminar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDetalleAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        PaneDetalleLayout.setVerticalGroup(
            PaneDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PaneDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PaneDetalleLayout.createSequentialGroup()
                        .addComponent(btnDetalleAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDetalleElminar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PaneDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PaneDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PaneDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        dtcFechaNecesidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dtcFechaNecesidadMouseClicked(evt);
            }
        });

        txtFechaEstimada.setEditable(false);

        txtFecha.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Fecha/Hora Generación:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
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
                .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlInfoLayout.setVerticalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(4, Short.MAX_VALUE))
        );

        pnlCliente.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        javax.swing.GroupLayout pnlPedidoLayout = new javax.swing.GroupLayout(pnlPedido);
        pnlPedido.setLayout(pnlPedidoLayout);
        pnlPedidoLayout.setHorizontalGroup(
            pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlPedidoLayout.createSequentialGroup()
                        .addGroup(pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbPrioridad, 0, 198, Short.MAX_VALUE)
                            .addComponent(cmbTipoPedido, 0, 198, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlPedidoLayout.createSequentialGroup()
                                .addComponent(labelFechaEstimada)
                                .addGap(22, 22, 22))
                            .addGroup(pnlPedidoLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)))
                        .addGroup(pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFechaEstimada, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                            .addComponent(dtcFechaNecesidad, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)))
                    .addComponent(pnlCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlPedidoLayout.setVerticalGroup(
            pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPedidoLayout.createSequentialGroup()
                .addComponent(pnlInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(cmbPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addComponent(dtcFechaNecesidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(pnlPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(labelFechaEstimada)
                    .addComponent(txtFechaEstimada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlBaja.setBorder(javax.swing.BorderFactory.createTitledBorder("Cancelación"));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Fecha:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
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
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
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
        pedido.setDetallePedido(tablaDetalle.getDatos());
        pedido.setFechaSolicitada(dtcFechaNecesidad.getDate());
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

        PantallaPedidoDetalleABM panDet = new PantallaPedidoDetalleABM(null, true, gestor);
        panDet.setVisible(true);
        if (panDet.isOk()) {
            DetallePedido nuevoDetalle = panDet.getDetalle();
            for (DetallePedido dp : tablaDetalle.getDatos()) {
                if (dp.getProducto().getNombre().equals(nuevoDetalle.getProducto().getNombre())) {
                    String mensage =
                            "¡Ya existe un detalle que contiene el producto " + dp.getProducto().getNombre() + "!\n"
                            + "¿Desea agregar la cantidad ingresada al detalle existente?";
                    if (Mensajes.mensajeConfirmacionGenerico(mensage)) {
                        dp.setCantidad(dp.getCantidad() + nuevoDetalle.getCantidad());
                        tablaDetalle.updateTabla();
                    }
                    return;
                }
            }
            tablaDetalle.add(nuevoDetalle);
        }


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
            tablaDetalle.removeSelectedRow();
        }

    }//GEN-LAST:event_btnDetalleElminarActionPerformed

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
    private javax.swing.JPanel PaneDetalle;
    private javax.swing.JButton btnDetalleAgregar;
    private javax.swing.JButton btnDetalleElminar;
    private javax.swing.JComboBox cmbPrioridad;
    private javax.swing.JComboBox cmbTipoPedido;
    private com.toedter.calendar.JDateChooser dtcFechaNecesidad;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JPanel pnlPedido;
    private javax.swing.JTable tbDetalle;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtFechaBaja;
    private javax.swing.JTextField txtFechaEstimada;
    private javax.swing.JTextArea txtMotivoBaja;
    private javax.swing.JTextField txtSubTotal;
    // End of variables declaration//GEN-END:variables
}
