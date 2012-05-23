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

import Negocio.Compras.Material;
import Negocio.Deposito.GestorReajustarStock;
import Negocio.Deposito.ReajusteStock;
import Presentacion.Mensajes;
import Presentacion.Utilidades;

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
    }

    public void cargarMaterial(Material obj)
    {
        txtCodigo.setText(obj.getCodigo());
        txtNombre.setText(obj.getNombre());
        txtCant.setText(obj.getStockActual()+"");
    }
    
     public void limpiarDatos(){
         txtNombre.setText("");
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

        txtCodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCantReal = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnBuscarMat = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCantDif = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reajuste de Stock");
        setIconImage(null);

        jLabel1.setText("Código:");

        jLabel2.setText("Nombre:");

        txtNombre.setEditable(false);
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        jLabel3.setText("Cantidad:");

        txtCant.setEditable(false);

        jLabel4.setText("Cantidad Actual Existente:");

        txtCantReal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantRealKeyReleased(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");

        btnBuscarMat.setText("Buscar Material");
        btnBuscarMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMatActionPerformed(evt);
            }
        });

        jLabel5.setText("Observaciones:");

        jLabel6.setText("Diferencia en Stock:");

        txtCantDif.setEditable(false);

        txtObs.setColumns(20);
        txtObs.setLineWrap(true);
        txtObs.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtObs);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtCantDif, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCant, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCantReal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnBuscarMat)))
                                        .addGap(20, 20, 20))))
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(btnAceptar)
                        .addGap(36, 36, 36)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarMat)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txtCantReal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(txtCantDif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

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
            Mensajes.mensajeErrorGuardar("No se pudo guardar el Reajuste");
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscarMat;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtCantDif;
    private javax.swing.JTextField txtCantReal;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextArea txtObs;
    // End of variables declaration//GEN-END:variables

}
