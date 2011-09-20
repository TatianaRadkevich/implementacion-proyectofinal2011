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

import Negocio.Compras.DetalleOrdenCompra;
import Negocio.Compras.GestorOrdenCompra;
import Negocio.Compras.Material;
import Negocio.Compras.OrdenCompra;
import Negocio.Compras.Proveedor;
import Negocio.Deposito.Faltante;
import Presentacion.TablaManager;
import Presentacion.Utilidades;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.rmi.CORBA.Util;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodrigo
 */
public class PantallaOrdenCompraABM extends javax.swing.JDialog {

    /** Creates new form PantallaOrdenCompraABM */
    private GestorOrdenCompra gestor;
    private JComboBox cmbMaterial;
    private TablaManager<Faltante> tmFaltantes;
    private TablaManager<DetalleOrdenCompra> tmOrdenCompra;

    public PantallaOrdenCompraABM(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public PantallaOrdenCompraABM(GestorOrdenCompra gestor) {
        this(null, true);
        this.gestor = gestor;

        //Seteo de variables//-
        cmbMaterial = new JComboBox();
        txtCodigo.setText((gestor.getCodigo() + 1) + "");
        txtFecha.setText(Utilidades.parseFecha(Utilidades.getFechaActual()));
        IniciarTablas();
        cargarCombos();
        cargarValidaciones();
        tmFaltantes.setDatos(gestor.getFaltantes());

    }

    private void IniciarTablas() {

        tmFaltantes = new TablaManager<Faltante>(tbFaltantes) {

            @Override
            public Vector ObjetoFila(Faltante elemento) {
                Vector fila = new Vector();
                fila.add(elemento.getMaterial().getNombre());
                fila.add(elemento.getCantidad());
                return fila;
            }

            @Override
            public Vector getCabecera() {
                Vector cabecera = new Vector();
                cabecera.add("Material");
                cabecera.add("Cantidad");
                return cabecera;
            }
        };

        tmOrdenCompra = new TablaManager<DetalleOrdenCompra>(tbDetalle) {

            @Override
            public Vector ObjetoFila(DetalleOrdenCompra elemento) {
                Vector fila = new Vector();
                fila.add((elemento.getMaterial() == null) ? "" : elemento.getMaterial().getNombre());
                fila.add(elemento.getCantidadPedida());
                return fila;
            }

            @Override
            public Vector getCabecera() {
                Vector cabecera = new Vector();
                cabecera.add("Material");
                cabecera.add("Cantidad");
                return cabecera;
            }

            @Override
            public boolean isCellEditable(int columna) {
                return true;
            }

            @Override
            public void fireTableCellUpdated(int row, int column) {
                Object value = tbDetalle.getModel().getValueAt(row, column);
                if (column == 0) {
                    tmOrdenCompra.getDato(row).setMaterial((Material) value);
                    actlizarComboProveedor();
                }
                if (column == 1) {
                    tmOrdenCompra.getDato(row).setCantidadPedida(new Short(value.toString()));
                }
            }
        };
        tbDetalle.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cmbMaterial));
        tbDetalle.setRowHeight((int) cmbMaterial.getPreferredSize().getHeight());



        tmOrdenCompra.addListenerModificacionTabla(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
                actlizarComboProveedor();
            }
        });
    }

    private void cargarValidaciones() {
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

    public void actlizarComboProveedor() {

        List<Material> materiales=new ArrayList<Material>();
        for(DetalleOrdenCompra doc:tmOrdenCompra.getDatos())
            if(doc.getMaterial()!=null)
                materiales.add(doc.getMaterial());

        List<Proveedor> pro = gestor.getProveedores();
        for(int i=0;i<pro.size();i++)
        {
            Proveedor p=pro.get(i);
            if(p.getMateriales().containsAll(materiales)==false)
            {
                pro.remove(p);
                i--;
            }            
        }



        Object o = cmbProveedor.getSelectedItem();
        cmbProveedor.setModel(new DefaultComboBoxModel(pro.toArray()));
        cmbProveedor.setSelectedItem(o);
    }

    public void habilitarCarga(boolean valor) {

        Utilidades.habilitarPanel(pnlCompra, valor);
    }

    public void cargar(OrdenCompra oc) {
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
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        pnlFaltantes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbFaltantes = new javax.swing.JTable();
        btnFaltanteDetalle = new javax.swing.JButton();
        pnlDetalle = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDetalle = new javax.swing.JTable();
        btnEliminarDetalle = new javax.swing.JButton();
        btnNuevoDetalle = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cmbProveedor = new javax.swing.JComboBox();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlCompra.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Orden de compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        txtFecha.setEditable(false);

        jLabel2.setText("Fecha:");

        txtCodigo.setEditable(false);

        jLabel1.setText("Codigo:");

        pnlFaltantes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Faltantes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbFaltantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Material", "Cantidad"
            }
        ));
        jScrollPane1.setViewportView(tbFaltantes);

        btnFaltanteDetalle.setText(">>");

        javax.swing.GroupLayout pnlFaltantesLayout = new javax.swing.GroupLayout(pnlFaltantes);
        pnlFaltantes.setLayout(pnlFaltantesLayout);
        pnlFaltantesLayout.setHorizontalGroup(
            pnlFaltantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFaltantesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFaltanteDetalle))
        );
        pnlFaltantesLayout.setVerticalGroup(
            pnlFaltantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFaltantesLayout.createSequentialGroup()
                .addGroup(pnlFaltantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFaltanteDetalle)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Material", "Cantidad"
            }
        ));
        jScrollPane2.setViewportView(tbDetalle);

        btnEliminarDetalle.setText("-");
        btnEliminarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarDetalleActionPerformed(evt);
            }
        });

        btnNuevoDetalle.setText("+");
        btnNuevoDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoDetalleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnEliminarDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevoDetalle)))
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDetalleLayout.createSequentialGroup()
                        .addComponent(btnNuevoDetalle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarDetalle))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel3.setText("Proveedor:");

        javax.swing.GroupLayout pnlCompraLayout = new javax.swing.GroupLayout(pnlCompra);
        pnlCompra.setLayout(pnlCompraLayout);
        pnlCompraLayout.setHorizontalGroup(
            pnlCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCompraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCompraLayout.createSequentialGroup()
                        .addComponent(pnlFaltantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addComponent(pnlDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCompraLayout.createSequentialGroup()
                        .addGroup(pnlCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCompraLayout.createSequentialGroup()
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 462, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlCompraLayout.setVerticalGroup(
            pnlCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCompraLayout.createSequentialGroup()
                .addGroup(pnlCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlFaltantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
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
                .addComponent(pnlCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
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
        tmOrdenCompra.add(new DetalleOrdenCompra());
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
        gestor.finalizarCU();
    }//GEN-LAST:event_btnAceptarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                PantallaOrdenCompraABM dialog = new PantallaOrdenCompraABM(new GestorOrdenCompra());
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
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarDetalle;
    private javax.swing.JButton btnFaltanteDetalle;
    private javax.swing.JButton btnNuevoDetalle;
    private javax.swing.JComboBox cmbProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel pnlCompra;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlFaltantes;
    private javax.swing.JTable tbDetalle;
    private javax.swing.JTable tbFaltantes;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtFecha;
    // End of variables declaration//GEN-END:variables
}
