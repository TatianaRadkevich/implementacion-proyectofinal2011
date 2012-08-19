/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReajustarStock.java
 *
 * Created on 02/01/2012, 11:57:18
 */

package Presentacion.Deposito;

import Negocio.Compras.GestorMaterial;
import Negocio.Compras.Material;
import Negocio.Deposito.GestorReajustarStock;
import Negocio.Deposito.ReajusteStock;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Mensajes;
import Presentacion.*;
import java.util.List;

/**
 *
 * @author Sebastian
 */
public class ReajustarStock extends javax.swing.JDialog {

    private GestorReajustarStock gestor;
    private ReajusteStock reajuste;
    private Material matActual;


    /** Creates new form ReajustarStock */
    public ReajustarStock(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        gestor=new GestorReajustarStock();

        inicializarComboMateriales();
        limpiarDatos();
    }

    private void inicializarComboMateriales()
    {
        cmbNombreMaterial.removeAllItems();
        List<Material> materiales = gestor.listarMaterial();
        for(int i = 0; i < materiales.size(); i++)
        {
            cmbNombreMaterial.addItem(materiales.get(i));
        }
    }

    public void cargarMaterial(Material obj)
    {
        for(int i = 0; i < cmbNombreMaterial.getItemCount(); i++)
        {
            if(((Material)cmbNombreMaterial.getItemAt(i)).getCodigo().equals(obj.getCodigo()))
            {
                cmbNombreMaterial.setSelectedIndex(i);
                break;
            }
        }
        txtCodigo.setText(obj.getCodigo());
        txtCant.setText(obj.getStockActual()+"");
    }
    
     private void limpiarDatos(){
         cmbNombreMaterial.setSelectedItem(null);
         txtCodigo.setText("");
         txtCant.setText("");
         txtCantDif.setText("");
         txtObs.setText("");
         txtCantReal.setText("");
     }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMaterial = new javax.swing.JPanel();
        cmbNombreMaterial = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextArea();
        txtCantDif = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnBuscarMat = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        txtCantReal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reajuste de Stock");
        setIconImage(null);

        pnlMaterial.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Material", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        cmbNombreMaterial.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbNombreMaterialItemStateChanged(evt);
            }
        });

        txtObs.setColumns(20);
        txtObs.setLineWrap(true);
        txtObs.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtObs);

        txtCantDif.setEditable(false);

        jLabel6.setText("Diferencia en Stock:");

        jLabel5.setText("Observaciones:");

        btnBuscarMat.setText("Buscar");
        btnBuscarMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMatActionPerformed(evt);
            }
        });

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

        txtCantReal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantRealKeyReleased(evt);
            }
        });

        jLabel4.setText("Cantidad Actual Existente:");

        txtCant.setEditable(false);

        jLabel3.setText("Cantidad:");

        jLabel2.setText("Nombre:");

        jLabel1.setText("Código:");

        jLabel7.setText("Búsqueda Material");

        jLabel8.setText("Reajuste Stock");

        javax.swing.GroupLayout pnlMaterialLayout = new javax.swing.GroupLayout(pnlMaterial);
        pnlMaterial.setLayout(pnlMaterialLayout);
        pnlMaterialLayout.setHorizontalGroup(
            pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMaterialLayout.createSequentialGroup()
                .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMaterialLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMaterialLayout.createSequentialGroup()
                                .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCant, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                    .addComponent(txtCantReal)
                                    .addComponent(txtCantDif))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE))
                            .addComponent(jLabel5)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMaterialLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAceptar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMaterialLayout.createSequentialGroup()
                                .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(pnlMaterialLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(91, 91, 91)
                                        .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cmbNombreMaterial, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(pnlMaterialLayout.createSequentialGroup()
                                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnBuscarMat, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(11, 11, 11))))
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addGap(32, 32, 32))
            .addGroup(pnlMaterialLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlMaterialLayout.setVerticalGroup(
            pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMaterialLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarMat))
                .addGap(18, 18, 18)
                .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbNombreMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCantReal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCantDif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCantRealKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantRealKeyReleased
        try{
        int cantidad = Integer.parseInt(txtCantReal.getText());
        if(matActual!=null)
            txtCantDif.setText(cantidad  - matActual.getStockActual()+"");
        }
        catch(Exception e){}        
    }//GEN-LAST:event_txtCantRealKeyReleased

  

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        reajuste = new ReajusteStock();
        reajuste.setMaterial(matActual);
        reajuste.setDiferencia(Short.parseShort(txtCantDif.getText()));
        reajuste.setCantidad(Short.parseShort(txtCantReal.getText()));
        reajuste.setObservaciones(txtObs.getText());
        reajuste.setFechaReajuste(Utilidades.getFechaActual());
       
        try
        {
            gestor.Ejecutar(reajuste);
            Mensajes.mensajeGuardoCorrectamente();
            this.dispose();
        }
        catch(Exception e)
        {
            Mensajes.mensajeErrorGuardar("No se pudo ejecutar el Reajuste");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

private void btnBuscarMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMatActionPerformed
    String codigoMaterial = txtCodigo.getText().trim();
    matActual= gestor.buscarMaterial(codigoMaterial);
    if (matActual == null)
    {
        Mensajes.mensajeErrorGenerico("El material con código " + codigoMaterial + " no ha sido encontrado");
        limpiarDatos();
    }
    else
    {
        limpiarDatos();
        cargarMaterial(matActual);
    }
}//GEN-LAST:event_btnBuscarMatActionPerformed

private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
     this.dispose();
}//GEN-LAST:event_btnCancelarActionPerformed

private void cmbNombreMaterialItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbNombreMaterialItemStateChanged
    if(evt.getItem() != null){
        matActual= (Material)evt.getItem();
        cargarMaterial(matActual);
    }
    else{
        limpiarDatos();
    }
}//GEN-LAST:event_cmbNombreMaterialItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscarMat;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox cmbNombreMaterial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlMaterial;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtCantDif;
    private javax.swing.JTextField txtCantReal;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextArea txtObs;
    // End of variables declaration//GEN-END:variables

}
