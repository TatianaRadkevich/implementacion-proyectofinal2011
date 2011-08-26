/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaABMProducto.java
 *
 * Created on 07/06/2011, 09:00:49
 */

package Presentacion.Produccion;
import Negocio.Exceptiones.ExceptionGestor;
import Negocio.Produccion.GestorProducto;
import Negocio.Produccion.GestorTipoProducto;
import Negocio.Produccion.Producto;
import Negocio.Produccion.TipoProducto;
import Presentacion.IniciadorDeVentanas;
import Presentacion.Mensajes;
import gui.GUILocal;
import java.awt.Dialog;
import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivan
 */
public class PantallaABMProducto extends javax.swing.JDialog {

    private GestorProducto gestor;
    private Producto producto=null;
    /** Creates new form PantallaABMProducto */
    public PantallaABMProducto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        GUILocal.establecerGUILocal(this);

        initComponents();
        IniciadorDeVentanas.iniciarVentana(this, this.getWidth(),this.getHeight());
    }

    public PantallaABMProducto(Dialog owner, boolean modal, GestorProducto gestor) {
        super(owner, modal);
        GUILocal.establecerGUILocal(this);
        initComponents();
        this.gestor=gestor;
        
       cargarTipoProductos();
       IniciadorDeVentanas.iniciarVentana(this, this.getWidth(),this.getHeight());
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
        jLabel1 = new javax.swing.JLabel();
        lbl_codigo = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaDescripcion = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbTipoProducto = new javax.swing.JComboBox();
        btnAgregarTipo = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlBaja = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtFechaBaja = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMotivoBaja = new javax.swing.JTextArea();
        btnAlta = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Producto"));

        jLabel1.setText("Nombre:");

        lbl_codigo.setText("Código:");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtCodigo.setText("Xxxxxx");

        jLabel2.setText("Descripción:");

        txtAreaDescripcion.setColumns(20);
        txtAreaDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtAreaDescripcion);

        jButton1.setText("Registrar estructura");

        jLabel3.setText("Precio:  $");

        jLabel4.setText("Tipo:");

        btnAgregarTipo.setText("Agregar");
        btnAgregarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTipoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel3))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lbl_codigo)
                                            .addComponent(jLabel1)))
                                    .addComponent(jLabel2))
                                .addGap(10, 10, 10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmbTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAgregarTipo)))))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_codigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(btnAgregarTipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");

        pnlBaja.setBorder(javax.swing.BorderFactory.createTitledBorder("Baja"));

        jLabel5.setText("Fecha:");

        jLabel6.setText("Motivo:");

        txtMotivoBaja.setColumns(20);
        txtMotivoBaja.setRows(5);
        jScrollPane3.setViewportView(txtMotivoBaja);

        btnAlta.setText("Alta");
        btnAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBajaLayout = new javax.swing.GroupLayout(pnlBaja);
        pnlBaja.setLayout(pnlBajaLayout);
        pnlBajaLayout.setHorizontalGroup(
            pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBajaLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBajaLayout.createSequentialGroup()
                        .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );
        pnlBajaLayout.setVerticalGroup(
            pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBajaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAlta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlBaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAceptar)
                        .addComponent(jButton3)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtNombreActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        if(validar()){            
            producto.setNombre(txtNombre.getText().toUpperCase());
            producto.setDescripcion(txtAreaDescripcion.getText());
            producto.setPrecioUnitario(new BigDecimal(txtPrecio.getText()));
            producto.setTTproducto((TipoProducto) cmbTipoProducto.getSelectedItem());

            if(txtFechaBaja.getText().compareTo("")!=0){
                producto.setFecBaja(new Date());
                producto.setMotivoBaja(txtMotivoBaja.getText());
            }

           
            try {
                gestor.ejecutarOperacion(producto);
                Mensajes.mensajeInformacion(gestor.mensajeResultado(producto.getNombre()));
                this.vaciar();
                gestor.reiniciar(this);

            } catch (ExceptionGestor ex) {
                Logger.getLogger(PantallaABMProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltaActionPerformed
        // TODO add your handling code here:

        this.producto.setMotivoBaja(null);
        this.producto.setFecBaja(null);
        this.txtFechaBaja.setText("");
        this.txtMotivoBaja.setText("");
        this.btnAlta.setEnabled(false);
}//GEN-LAST:event_btnAltaActionPerformed

    private void btnAgregarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTipoActionPerformed
        // TODO add your handling code here:       
        GestorTipoProducto.administarTipoProductoAgregar(this);
        
        this.cargarTipoProductos();
    }//GEN-LAST:event_btnAgregarTipoActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        pnlBaja.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    public boolean validar(){
        return true;
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PantallaABMProducto dialog = new PantallaABMProducto(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAgregarTipo;
    private javax.swing.JButton btnAlta;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox cmbTipoProducto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_codigo;
    private javax.swing.JPanel pnlBaja;
    private javax.swing.JTextArea txtAreaDescripcion;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtFechaBaja;
    private javax.swing.JTextArea txtMotivoBaja;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables


    private void cargarDatos(Producto prod){

        this.txtCodigo.setText(prod.codigoMerge());
        this.txtNombre.setText(prod.getNombre());
        this.txtPrecio.setText(prod.getPrecioUnitario()+"");
        this.txtAreaDescripcion.setText(prod.getDescripcion());

         if(prod.getFecBaja()==null)
            this.txtFechaBaja.setText("");
        else
        {
            Format formato=new SimpleDateFormat("dd/MM/yyyy");
            String fecha=formato.format(prod.getFecBaja());
            this.txtFechaBaja.setText(fecha);
        }

        if(prod.getMotivoBaja()==null)
            this.txtMotivoBaja.setText("");
        else
            this.txtMotivoBaja.setText(prod.getMotivoBaja());


         TipoProducto tipo=null;
        for(int i=0; i<cmbTipoProducto.getItemCount();i++){
            tipo=(TipoProducto) cmbTipoProducto.getItemAt(i);
            if(tipo.getNombre().compareTo(this.producto.getTTproducto().getNombre())==0){
                cmbTipoProducto.setSelectedIndex(i);
                break;
            }
        }
    }

     private void cargarTipoProductos(){
        try {
            cmbTipoProducto.removeAll();
            List<TipoProducto> tipo = gestor.traerTiposProductos();
            for(int i=0;i<tipo.size();i++){
                cmbTipoProducto.addItem(tipo.get(i));
            }
        } catch (ExceptionGestor ex) {
            Logger.getLogger(PantallaABMProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void nuevo(){
        this.txtCodigo.setVisible(false);
        this.lbl_codigo.setVisible(false);
        producto=new Producto();
        activarBaja(false);
        activarProducto(true);
        this.activarBotones(true, true, false);
        this.txtNombre.requestFocus();
    }

    public void modificar(Producto producto){
        this.producto=producto;  

        this.activarProducto(true);
        this.activarBaja(false);
        this.activarBotones(true, true, false);
        
       
        if(producto.getFecBaja()!=null){
            this.btnAlta.setEnabled(true);
         }
        
        this.txtNombre.requestFocus();
        this.cargarDatos(this.producto);
    }

    public void baja(Producto prod) {
        this.cargarDatos(prod);
        Format formato=new SimpleDateFormat("dd/MM/yyyy");
        String fecha=formato.format(new Date());
        this.txtFechaBaja.setText(fecha);
        this.producto=prod;
        this.activarProducto(false);
        this.activarBaja(true);
        this.activarBotones(true, true, false);
        this.txtMotivoBaja.requestFocus();
        }

    public void vaciar(){
        this.txtCodigo.setText("");
        this.txtNombre.setText("");
        this.txtAreaDescripcion.setText("");
        this.txtPrecio.setText("");
        this.txtFechaBaja.setText("");
        this.txtMotivoBaja.setText("");
        this.cmbTipoProducto.setSelectedIndex(1);
    }
    
    private void activarProducto(boolean flag){
        this.txtCodigo.setEnabled(flag);
        this.txtNombre.setEnabled(flag);
        this.txtAreaDescripcion.setEnabled(flag);
        this.txtPrecio.setEnabled(flag);
        
    }
    
    private void activarBaja(boolean flag){
        this.txtFechaBaja.setEnabled(false);
        this.txtMotivoBaja.setEnabled(flag);
    }

    private void activarBotones(boolean aceptar,boolean cancelar, boolean alta){
        this.btnAceptar.setEnabled(aceptar);
        this.btnCancelar.setEnabled(cancelar);
        this.btnAlta.setEnabled(alta);
    }
    
    
    
}
