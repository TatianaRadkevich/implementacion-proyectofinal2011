/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaOrdenCompraABM.java
 *
 * Created on 19/09/2011, 20:00:25
 */
package Presentacion.Compras;

import BaseDeDatos.Compras.EstadoDetalleOrdenCompraBD;
import Negocio.Compras.DetalleOrdenCompra;
import Negocio.Compras.GestorOrdenCompra;
import Negocio.Compras.GestorOrdenCompraAlta;
import Negocio.Compras.Material;
import Negocio.Compras.OrdenCompra;
import Negocio.Compras.Proveedor;
import Negocio.Deposito.Faltante;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Mensajes;
import Presentacion.TablaManager;
import Presentacion.Utilidades;
import Presentacion.ValidarTexbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Rodrigo
 */
public class PantallaOrdenCompraABM extends javax.swing.JDialog {

    /** Creates new form PantallaOrdenCompraABM */
    private GestorOrdenCompra gestor;
    private TablaManager<DetalleOrdenCompra> tmOrdenCompra;
    private TablaManager<Material> tmStock;

    private PantallaOrdenCompraABM(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public PantallaOrdenCompraABM(GestorOrdenCompra gestor) {
        this(null, true);
        this.gestor = gestor;      

        //Seteo de variables//-      
        txtCodigo.setText((gestor.getCodigo() + 1) + "");
        txtFecha.setText(Utilidades.parseFecha(Utilidades.getFechaActual()));
        habilitarCarga(true);
        IniciarTablas();
        cargarCombos();
        cargarValidaciones();
        tmStock.setDatos(gestor.listarMateriales());
    }

    private void IniciarTablas() {

        tmStock=new TablaManager<Material>(tbStock) {

            @Override
            public Vector ObjetoFila(Material elemento) {

                    Vector salida=new Vector();
                    salida.add(elemento.getNombre());
                    salida.add(elemento.getStockActual());
                    salida.add(elemento.getStockReservado());
                    salida.add(elemento.getStockDisponible());
                    salida.add(elemento.getStockMinimo());
                    salida.add(elemento.getCantidadFaltante());
                    return salida;
            }

            @Override
            public Vector getCabecera() {
                Vector salida=new Vector();
                salida.add("Material");
                salida.add("Stock Actual");
                salida.add("Stock Reservado");
                salida.add("Stock Disponible");
                salida.add("Stock Minimo");
                salida.add("Stock Faltante");

                return salida;
            }
        };

        ////////////////// Detalle orden compra ///////////////////////////

        tmOrdenCompra = new TablaManager<DetalleOrdenCompra>(tbDetalle) {

            @Override
            public Vector ObjetoFila(DetalleOrdenCompra elemento) {
                ///////////////////////////////
                 Float precio=elemento.getMaterial().getPrecio((Proveedor) cmbProveedor.getSelectedItem());
                
                if(precio==null)
                    precio=0f;
                ////////////////////////////////
                Vector fila = new Vector();
                fila.add((elemento.getMaterial() == null) ? "" : elemento.getMaterial().getNombre());
                fila.add(precio);
                fila.add(elemento.getCantidadPedida());
                fila.add(elemento.getCantidadPedida() * precio);

                return fila;
            }

            @Override
            public Vector getCabecera() {
                Vector cabecera = new Vector();
                cabecera.add("Material");
                cabecera.add("Precio unitario");
                cabecera.add("Cantidad");
                cabecera.add("Sub total");
                return cabecera;
            }
        };

        tmOrdenCompra.addSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (tmOrdenCompra.getSeletedObject() != null) {
                    cargarDetalle(tmOrdenCompra.getSeletedObject());
                } else {
                    limpiarCargaDetalle();
                }
            }
        });

        tmOrdenCompra.addModificationListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
                recalcularTotal();
            }
        });

    }

    private void recalcularTotal() {

        float total = 0;
        for(int i=0;i<tbDetalle.getRowCount();i++)
            total+= Float.parseFloat(tbDetalle.getValueAt(i, 3).toString());//col 3 >> subtotal
        
        txtTotal.setText(total + "");
    }

    private void cargarValidaciones() {
        ValidarTexbox.validarInt(txtCantidad);
        ValidarTexbox.validarLongitud(txtCantidad, 3);
    }

    private void cargarCombos() {
        cmbProveedor.setModel(new DefaultComboBoxModel(gestor.getProveedores().toArray()));
        cmbProveedor.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (cmbProveedor.getSelectedIndex() != -1) {
                    Proveedor p = (Proveedor) cmbProveedor.getSelectedItem();
                    cmbMaterial.setModel(new DefaultComboBoxModel(p.getMateriales().toArray()));
                } else {
                    cmbMaterial.removeAllItems();
                }
                cmbMaterial.setSelectedIndex(-1);
            }
        });
        cmbProveedor.setSelectedIndex(-1);

    } 

    public void habilitarCarga(boolean valor) {

               Utilidades.habilitarPanel(pnlCompra, valor);
        Utilidades.habilitarPanel(pnlDetalleABM, !valor);
    }

    public void habilitarCargaDetalle(boolean valor) {

        Utilidades.habilitarPanel(pnlCompra, !valor);
        Utilidades.habilitarPanel(pnlDetalleABM, valor);
    }

    public void limpiarCargaDetalle() {
        txtCantidad.setText("");
        cmbMaterial.setSelectedIndex(-1);
    }

    public void cargarDetalle(DetalleOrdenCompra doc) {
        txtCantidad.setText(Utilidades.parseString(doc.getCantidadPedida()));
        cmbMaterial.setSelectedItem(doc.getMaterial());
    }

    public void cargar(OrdenCompra oc) {
        txtCodigo.setText(oc.getId()+"");
        txtFecha.setText(Utilidades.parseFecha(oc.getFecGeneracion()));
        tmOrdenCompra.setDatos(oc.getDetalle());
        cmbProveedor.setSelectedItem(oc.getProveedor());

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCompra = new javax.swing.JPanel();
        txtFecha = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        pnlDetalle = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDetalle = new javax.swing.JTable();
        btnEliminarDetalle = new javax.swing.JButton();
        btnNuevoDetalle = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        pnlDetalleABM = new javax.swing.JPanel();
        cmbMaterial = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnDetalleAceptar = new javax.swing.JButton();
        btnDetalleCancelar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cmbProveedor = new javax.swing.JComboBox();
        pnlFaltantes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbStock = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlCompra.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Orden de compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        txtFecha.setEditable(false);

        jLabel2.setText("Fecha generacion:");

        txtCodigo.setEditable(false);

        jLabel1.setText("Codigo:");

        pnlDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle orden de compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Material", "Precio unitario", "Cantidad", "Sub total"
            }
        ));
        jScrollPane2.setViewportView(tbDetalle);

        btnEliminarDetalle.setText("Eliminar");
        btnEliminarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarDetalleActionPerformed(evt);
            }
        });

        btnNuevoDetalle.setText("Nuevo");
        btnNuevoDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoDetalleActionPerformed(evt);
            }
        });

        jLabel4.setText("Total:");

        txtTotal.setEditable(false);

        pnlDetalleABM.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("Materiales:");

        jLabel6.setText("Cantidad:");

        btnDetalleAceptar.setText("Aceptar");
        btnDetalleAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleAceptarActionPerformed(evt);
            }
        });

        btnDetalleCancelar.setText("Cancelar");
        btnDetalleCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDetalleABMLayout = new javax.swing.GroupLayout(pnlDetalleABM);
        pnlDetalleABM.setLayout(pnlDetalleABMLayout);
        pnlDetalleABMLayout.setHorizontalGroup(
            pnlDetalleABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleABMLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(btnDetalleAceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDetalleCancelar)
                .addContainerGap())
        );
        pnlDetalleABMLayout.setVerticalGroup(
            pnlDetalleABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleABMLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetalleABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDetalleAceptar)
                    .addComponent(btnDetalleCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDetalleLayout.createSequentialGroup()
                        .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlDetalleLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 682, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnNuevoDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminarDetalle)))
                    .addComponent(pnlDetalleABM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetalleLayout.createSequentialGroup()
                .addComponent(pnlDetalleABM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDetalleLayout.createSequentialGroup()
                        .addComponent(btnNuevoDetalle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarDetalle))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetalleLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jLabel3.setText("Proveedores:");

        pnlFaltantes.setBorder(javax.swing.BorderFactory.createTitledBorder("Faltantes"));

        tbStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Material", "Stock Actual", "Stock Reservado", "Stock Disponible", "Stock Minimo", "Stock Faltante"
            }
        ));
        jScrollPane1.setViewportView(tbStock);

        jButton1.setText("agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFaltantesLayout = new javax.swing.GroupLayout(pnlFaltantes);
        pnlFaltantes.setLayout(pnlFaltantesLayout);
        pnlFaltantesLayout.setHorizontalGroup(
            pnlFaltantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFaltantesLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        pnlFaltantesLayout.setVerticalGroup(
            pnlFaltantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
            .addGroup(pnlFaltantesLayout.createSequentialGroup()
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlCompraLayout = new javax.swing.GroupLayout(pnlCompra);
        pnlCompra.setLayout(pnlCompraLayout);
        pnlCompraLayout.setHorizontalGroup(
            pnlCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCompraLayout.createSequentialGroup()
                .addGroup(pnlCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCompraLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlFaltantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlCompraLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlCompraLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 305, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlCompraLayout.setVerticalGroup(
            pnlCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCompraLayout.createSequentialGroup()
                .addGroup(pnlCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pnlFaltantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar))
                    .addComponent(pnlCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoDetalleActionPerformed
        // TODO add your handling code here:
        habilitarCargaDetalle(true);
    }//GEN-LAST:event_btnNuevoDetalleActionPerformed

    private void btnEliminarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarDetalleActionPerformed
        // TODO add your handling code here:
        if (tmOrdenCompra.getSeletedObject() != null) {
            tmOrdenCompra.removeSelectedRow();
        }
    }//GEN-LAST:event_btnEliminarDetalleActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        gestor.finalizarCU();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        OrdenCompra oc=gestor.getOrdenCompra();
        //oc.setId(Utilidades.parseInteger( txtCodigo.getText()));
        oc.setDetalle(tmOrdenCompra.getDatos());
        oc.setFecGeneracion(Utilidades.getFechaActual());
        oc.setProveedor((Proveedor) cmbProveedor.getSelectedItem());
        
        try {
            gestor.ejecutarCU(oc);
            gestor.finalizarCU();
        } catch (ExceptionGestor ex) {
            Mensajes.mensajeErrorGenerico(ex.getMessage());
        }
       
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnDetalleCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleCancelarActionPerformed
        // TODO add your handling code here:
        limpiarCargaDetalle();
        habilitarCargaDetalle(false);

    }//GEN-LAST:event_btnDetalleCancelarActionPerformed

    private void btnDetalleAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleAceptarActionPerformed
        // TODO add your handling code here:        
        DetalleOrdenCompra doc=new DetalleOrdenCompra();
        doc.setCantidadPedida(Utilidades.parseShort(txtCantidad.getText()));
        doc.setCantidadRecibida((short)0);
        doc.setMaterial((Material) cmbMaterial.getSelectedItem());
        doc.setPrecioUnitario(doc.getMaterial().getPrecio((Proveedor) cmbProveedor.getSelectedItem()));
        doc.setEstado(EstadoDetalleOrdenCompraBD.getEstadoPendiente());
        tmOrdenCompra.add(doc);
        limpiarCargaDetalle();
        habilitarCargaDetalle(false);
    }//GEN-LAST:event_btnDetalleAceptarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(tmStock.getSeletedObject()!= null)
        {
        Material mat=tmStock.getSeletedObject();
        tmOrdenCompra.add(new DetalleOrdenCompra(mat, mat.getCantidadFaltante().shortValue()));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new GestorOrdenCompraAlta().iniciarCU();
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDetalleAceptar;
    private javax.swing.JButton btnDetalleCancelar;
    private javax.swing.JButton btnEliminarDetalle;
    private javax.swing.JButton btnNuevoDetalle;
    private javax.swing.JComboBox cmbMaterial;
    private javax.swing.JComboBox cmbProveedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlCompra;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlDetalleABM;
    private javax.swing.JPanel pnlFaltantes;
    private javax.swing.JTable tbDetalle;
    private javax.swing.JTable tbStock;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
