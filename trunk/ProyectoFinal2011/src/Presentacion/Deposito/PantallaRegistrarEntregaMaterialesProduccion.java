/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaRegistrarEntregaMaterialesProduccion.java
 *
 * Created on 23/05/2012, 18:56:07
 */

package Presentacion.Deposito;

import BaseDeDatos.Compras.MaterialBD;
import Negocio.Compras.Material;
import Negocio.Produccion.DetalleEtapaProduccion;
import Negocio.Produccion.DetallePlanProduccion;
import Negocio.Produccion.GestorOrdenTrabajo;
import Negocio.Produccion.OrdenTrabajo;
import Presentacion.Mensajes;
import Presentacion.Utilidades;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Sebastian
 */
public class PantallaRegistrarEntregaMaterialesProduccion extends javax.swing.JDialog {
    
    private GestorOrdenTrabajo gestor;
    private OrdenTrabajo ordenTrabajo;
    private Map<Material,Integer> mapa;
    
    private boolean stockInsuficiente = false;

    /** Creates new form PantallaRegistrarEntregaMaterialesProduccion */
    public PantallaRegistrarEntregaMaterialesProduccion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        gestor = new GestorOrdenTrabajo();
        initComponents();
        txtFechaActual.setText(Utilidades.parseFecha(Utilidades.getFechaActual()));
        setLocationRelativeTo(null);
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
        btnBuscar = new javax.swing.JButton();
        txtNroOrdenBuscar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtFechaActual = new javax.swing.JTextField();
        pnlOrden = new javax.swing.JPanel();
        pnlDetalle = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableMateriales = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNroOrden = new javax.swing.JTextField();
        txtEstado = new javax.swing.JTextField();
        txtFecGeneracion = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar Entrega Materiales a Producción");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Búsqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Nro. Orden Trabajo:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNroOrdenBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(txtNroOrdenBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnBuscar))
        );

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setText("Fecha de hoy:");

        txtFechaActual.setEditable(false);

        pnlOrden.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Orden de trabajo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        pnlDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Materiales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jTableMateriales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Descripción", "Cantidad Pedida", "Estado", "Cantidad Recibida"
            }
        ));
        jScrollPane2.setViewportView(jTableMateriales);

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Nro. Orden:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Estado:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Fecha Emisión:");

        txtNroOrden.setEditable(false);

        txtEstado.setEditable(false);

        txtFecGeneracion.setEditable(false);

        javax.swing.GroupLayout pnlOrdenLayout = new javax.swing.GroupLayout(pnlOrden);
        pnlOrden.setLayout(pnlOrdenLayout);
        pnlOrdenLayout.setHorizontalGroup(
            pnlOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOrdenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOrdenLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNroOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFecGeneracion, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlOrdenLayout.setVerticalGroup(
            pnlOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOrdenLayout.createSequentialGroup()
                .addGroup(pnlOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNroOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtFecGeneracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.setEnabled(false);
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
                    .addComponent(pnlOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFechaActual, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFechaActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        int nro = new Integer(txtNroOrdenBuscar.getText());
        ordenTrabajo = gestor.buscarOrdenTrabajo(nro);
        txtNroOrden.setText(ordenTrabajo.getIdOrdenTrabajo() + "");
        txtFecGeneracion.setText(Utilidades.parseFecha(ordenTrabajo.getFecEmision()));
        txtEstado.setText(ordenTrabajo.getTEordenTrabajo().getNombre());
        cargarMateriales();
}//GEN-LAST:event_btnBuscarActionPerformed

private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        for (Map.Entry<Material,Integer> entry : mapa.entrySet()) {
            entry.getKey().setStockActual((int)(entry.getKey().getStockActual() - entry.getValue()));
            MaterialBD.guardar(entry.getKey());
        }
        Mensajes.mensajeInformacion("La orden de trabajo a entrado a producción con éxito y se ha descontado el material del stock");
}//GEN-LAST:event_btnAceptarActionPerformed

private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
this.dispose();
}//GEN-LAST:event_btnCancelarActionPerformed

    private void cargarMateriales() {
        //Creo el mapa de materiales y sus cantidades necesarias
        mapa = new HashMap<Material, Integer>();
        List<DetallePlanProduccion> detallesPlan = ordenTrabajo.getTDetallesPlans();
        if (detallesPlan.isEmpty())
        {
            Mensajes.mensajeErrorGenerico("La orden de trabajo buscada no posee Detalles de Plan de Producción, intente con una Orden completa");
        }
        else
        {
            for (DetallePlanProduccion det : detallesPlan)
            {
                List<DetalleEtapaProduccion> detallesEstapa = det.getTEtapasProduccionEspecifica().getDetalleEtapaProduccion();
                for (DetalleEtapaProduccion detEtapa : detallesEstapa)
                {
                    if (mapa.containsKey(detEtapa.getTDetallesProducto().getTMateriales())) // Si el material ya existe voy sumando
                    {
                        mapa.put(detEtapa.getTDetallesProducto().getTMateriales(),mapa.get(detEtapa.getTDetallesProducto().getTMateriales()) + detEtapa.getCantidadNecesaria());
                    }
                    else // Si el material no existe lo inserto con el primer valor
                    {
                        mapa.put(detEtapa.getTDetallesProducto().getTMateriales(),detEtapa.getCantidadNecesaria());                    
                    }
                }
            }
            jTableMateriales.setModel(toTableModel(mapa));
            if (stockInsuficiente)
            {
                Mensajes.mensajeErrorGenerico("El stock es insuficiente para ejecutar la orden de trabajo, deberá efectuar una orden de compra para abastecer material");
                btnAceptar.setEnabled(false);
            }
            else
            {
                btnAceptar.setEnabled(true);            
            }
        }
    }
    
    public TableModel toTableModel(Map<Material,Integer> map) {
    DefaultTableModel model = new DefaultTableModel(
        new Object[] { "Nro Material", "Descripción", "Stock Actual", "Cantidad a Usar", "Diferencia" }, 0
    );
    
    boolean banderaStockInsuficiente = false;
    for (Map.Entry<Material,Integer> entry : map.entrySet()) {
        model.addRow(new Object[] { entry.getKey().getIdMaterial(),entry.getKey().getDescripcion(),entry.getKey().getStockActual(), entry.getValue(), entry.getKey().getStockActual() - entry.getValue() });
        if (entry.getKey().getStockActual() - entry.getValue() < 0)
        {
            banderaStockInsuficiente = true;
        }
    }
    stockInsuficiente = banderaStockInsuficiente;
    return model;
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableMateriales;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlOrden;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtFecGeneracion;
    private javax.swing.JTextField txtFechaActual;
    private javax.swing.JTextField txtNroOrden;
    private javax.swing.JTextField txtNroOrdenBuscar;
    // End of variables declaration//GEN-END:variables
}
