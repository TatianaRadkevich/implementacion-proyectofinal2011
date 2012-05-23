/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Domicilio.java
 *
 * Created on 19/09/2011, 13:58:34
 */
package Presentacion;

import BaseDeDatos.UbicacionGeografica.PaisBD;
import Negocio.Administracion.GestorEmpleado;
import Negocio.TipoDatoException;
import Negocio.UbicacionGeografica.*;
import Negocio.UbicacionGeografica.Pais;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Rodrigo
 */
public class PnlDomicilio extends javax.swing.JPanel {

    /** Creates new form Domicilio */
    private Domicilio domicilio;
    private boolean inicializado=false;


    public PnlDomicilio() {
        initComponents(); 
        cargarValidaciones();
        domicilio = new Domicilio();
    }

    public void inicializar()
    {
        if(inicializado)
            return;
        cargarCombos();
        setDefaul();
        inicializado=true;
    }

    private void cargarValidaciones() {
        ValidarTexbox.validarShort(txtPiso);
        ValidarTexbox.validarLongitud(txtPiso, 2);

        ValidarTexbox.validarInt(txtNumero);
        ValidarTexbox.validarLongitud(txtPiso, 5);

        ValidarTexbox.validarLongitud(txtCalle, 20);
        ValidarTexbox.validarLongitud(txtDepto, 3);
    }

    private void cargarCombos() {
        cmbPais.setModel(new DefaultComboBoxModel(PaisBD.listarPaises().toArray()));
        cmbPais.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (cmbPais.getSelectedIndex() != -1) {
                    Pais p = (Pais) cmbPais.getSelectedItem();
                    cmbProvincia.setModel(new DefaultComboBoxModel(p.getTProvinciases().toArray()));
                } else {
                    cmbProvincia.removeAllItems();
                }
                cmbProvincia.setSelectedIndex(-1);
            }
        });

        cmbProvincia.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (cmbProvincia.getSelectedIndex() != -1) {
                    Provincia p = (Provincia) cmbProvincia.getSelectedItem();
                    cmbLocalidad.setModel(new DefaultComboBoxModel(p.getTLocalidadeses().toArray()));
                } else {
                    cmbLocalidad.removeAllItems();
                }
                cmbLocalidad.setSelectedIndex(-1);
            }
        });

        cmbLocalidad.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (cmbLocalidad.getSelectedIndex() != -1) {
                    Localidad b = (Localidad) cmbLocalidad.getSelectedItem();
                    cmbBarrio.setModel(new DefaultComboBoxModel(b.getTBarrioses().toArray()));
                } else {
                    cmbBarrio.removeAllItems();
                }
                cmbBarrio.setSelectedIndex(-1);
            }
        });
        cmbPais.setSelectedIndex(-1);
    }

    private void setDefaul() {
        cmbPais.setSelectedItem(new Pais("Argentina"));
        cmbProvincia.setSelectedItem(new Provincia("Córdoba"));
        cmbLocalidad.setSelectedItem(new Localidad("Córdoba"));
        txtCalle.setText("");
        txtDepto.setText("");
        txtNumero.setText("");
        txtPiso.setText("");
       
    }

    public Domicilio getDomicilio() {
        try{
        domicilio.setCalle(txtCalle.getText());
        domicilio.setDepto(txtDepto.getText());
        domicilio.setNumero(Utilidades.parseInteger(txtNumero.getText()));
        domicilio.setPiso(Utilidades.parseShort(txtPiso.getText()));
        domicilio.setTBarrios((Barrio) cmbBarrio.getSelectedItem());
        domicilio.setTLocalidades((Localidad) cmbLocalidad.getSelectedItem());
        domicilio.setTPaises((Pais) cmbPais.getSelectedItem());
        domicilio.setTProvincias((Provincia) cmbProvincia.getSelectedItem());

        } catch (TipoDatoException ex) {
            return null;
        }
        return domicilio;
    }

    public void setDomicilio(Domicilio dom) {
        if(dom==null)
        {
            setDefaul();
            return;
        }
        cmbProvincia.setEnabled(true);
        cmbLocalidad.setEnabled(true);
        cmbBarrio.setEnabled(true);

        domicilio = dom;
        cmbPais.setSelectedItem(dom.getTPaises());
        cmbProvincia.setSelectedItem(dom.getTProvincias());
        cmbLocalidad.setSelectedItem(dom.getTLocalidades());
        cmbBarrio.setSelectedItem(dom.getTBarrios());
        txtCalle.setText(dom.getCalle());
        txtDepto.setText(dom.getDepto());
        txtNumero.setText(Utilidades.parseString(dom.getNumero()));
        txtPiso.setText(Utilidades.parseString(dom.getPiso()));
    }

    public void habilitar(boolean enabled) {
       
        Utilidades.habilitarPanel(this, enabled);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAgregarPais = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnAgregarLocalidad = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtCalle = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnAgregarProvincia = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cmbPais = new javax.swing.JComboBox();
        cmbLocalidad = new javax.swing.JComboBox();
        btnAgregarBarrio = new javax.swing.JButton();
        cmbProvincia = new javax.swing.JComboBox();
        cmbBarrio = new javax.swing.JComboBox();
        txtPiso = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtDepto = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Domicilio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        btnAgregarPais.setText("+");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("Provincia:");

        btnAgregarLocalidad.setText("+");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("País:");

        txtCalle.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCalleFocusLost(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setText("Barrio:");

        btnAgregarProvincia.setText("+");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setText("Localidad:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setText("Número:");

        txtNumero.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumeroFocusLost(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setText("Calle:");

        cmbPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPaisActionPerformed(evt);
            }
        });
        cmbPais.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbPaisFocusLost(evt);
            }
        });

        cmbLocalidad.setEnabled(false);
        cmbLocalidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbLocalidadFocusLost(evt);
            }
        });

        btnAgregarBarrio.setText("+");

        cmbProvincia.setEnabled(false);
        cmbProvincia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbProvinciaFocusLost(evt);
            }
        });

        cmbBarrio.setEnabled(false);

        txtPiso.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPisoFocusLost(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel12.setText("Piso:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel14.setText("Depto:");

        txtDepto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDeptoFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbBarrio, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbPais, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDepto, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregarPais)
                            .addComponent(btnAgregarProvincia)
                            .addComponent(btnAgregarLocalidad)
                            .addComponent(btnAgregarBarrio)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumero, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                            .addComponent(txtPiso, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(btnAgregarPais))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(btnAgregarProvincia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(btnAgregarLocalidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbBarrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(btnAgregarBarrio))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtDepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(67, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPaisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPaisActionPerformed

    private void cmbPaisFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbPaisFocusLost
        // TODO add your handling code here:
       


        try{
            domicilio.setTPaises((Pais)cmbPais.getSelectedItem());
            Utilidades.componenteCorrecto(cmbPais);
        }catch(TipoDatoException ex){
            cmbPais.setToolTipText(ex.getMessage());
            Utilidades.componenteError(cmbPais);
        }

         if(cmbPais.getSelectedItem()!=null){
        this.cargarComboProvicias((Pais)cmbPais.getSelectedItem());
        this.cmbProvincia.setEnabled(true);
        }
        else
            this.cmbProvincia.setEnabled(false);
    }//GEN-LAST:event_cmbPaisFocusLost

    private void cmbProvinciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbProvinciaFocusLost
        // TODO add your handling code here:
        try{
            domicilio.setTProvincias((Provincia)cmbProvincia.getSelectedItem());
            Utilidades.componenteCorrecto(cmbProvincia);
        }catch(TipoDatoException ex){
            cmbProvincia.setToolTipText(ex.getMessage());
            Utilidades.componenteError(cmbProvincia);
        }

        if(cmbProvincia.getSelectedItem()!=null){
            this.cmbLocalidad.setEnabled(true);
            this.cargarComboLocalidades((Provincia)cmbProvincia.getSelectedItem());
        }
        else
            this.cmbLocalidad.setEnabled(false);
        this.repaint();


    }//GEN-LAST:event_cmbProvinciaFocusLost

    private void cmbLocalidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbLocalidadFocusLost
        // TODO add your handling code here:
        try{
            domicilio.setTLocalidades((Localidad)cmbLocalidad.getSelectedItem());
            Utilidades.componenteCorrecto(cmbLocalidad);
        }catch(TipoDatoException ex){
            cmbLocalidad.setToolTipText(ex.getMessage());
            Utilidades.componenteError(cmbLocalidad);
        }

         if(cmbLocalidad.getSelectedItem()!=null){
            this.cmbBarrio.setEnabled(true);
            this.cargarComboBarrio((Localidad)cmbLocalidad.getSelectedItem());

        }
        else
            this.cmbBarrio.setEnabled(false);

        this.repaint();
    }//GEN-LAST:event_cmbLocalidadFocusLost

    private void txtCalleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCalleFocusLost
        // TODO add your handling code here:
        try{
            domicilio.setCalle(txtCalle.getText());
            Utilidades.componenteCorrecto(txtCalle);
        }catch(TipoDatoException ex){
             txtCalle.setToolTipText(ex.getMessage());
             Utilidades.componenteError(txtCalle);
        }
    }//GEN-LAST:event_txtCalleFocusLost

    private void txtNumeroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroFocusLost
        // TODO add your handling code here:
        try{
            int temp=Integer.parseInt(txtNumero.getText());
            domicilio.setNumero(temp);
            Utilidades.componenteCorrecto(txtNumero);
        }catch(TipoDatoException ex){
             txtNumero.setToolTipText(ex.getMessage());
             Utilidades.componenteError(txtNumero);
        }catch(Exception e){
             Utilidades.componenteError(txtNumero);
             txtNumero.setToolTipText("Formato incorrecto. Debe ser numérico");
        }
    }//GEN-LAST:event_txtNumeroFocusLost

    private void txtDeptoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDeptoFocusLost
        // TODO add your handling code here:
        try{
           domicilio.setDepto(txtDepto.getText());
            Utilidades.componenteCorrecto(txtDepto);
        }catch(TipoDatoException ex){
             txtDepto.setToolTipText(ex.getMessage());
             Utilidades.componenteError(txtDepto);
        }
    }//GEN-LAST:event_txtDeptoFocusLost

    private void txtPisoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPisoFocusLost
        // TODO add your handling code here:
        try{
            if(txtPiso.getText().trim().length()!=0){
                short temp=Short.parseShort(txtPiso.getText());
                domicilio.setPiso(temp);
            }
            else
                domicilio.setPiso(null);
            Utilidades.componenteCorrecto(txtPiso);
        }catch(TipoDatoException ex){
             txtPiso.setToolTipText(ex.getMessage());
             Utilidades.componenteError(txtPiso);
        }catch(Exception e){
             Utilidades.componenteError(txtPiso);
             txtPiso.setToolTipText("Formato incorrecto. Debe ser numérico");
        }
    }//GEN-LAST:event_txtPisoFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarBarrio;
    private javax.swing.JButton btnAgregarLocalidad;
    private javax.swing.JButton btnAgregarPais;
    private javax.swing.JButton btnAgregarProvincia;
    private javax.swing.JComboBox cmbBarrio;
    private javax.swing.JComboBox cmbLocalidad;
    private javax.swing.JComboBox cmbPais;
    private javax.swing.JComboBox cmbProvincia;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtDepto;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtPiso;
    // End of variables declaration//GEN-END:variables

private void cargarComboProvicias(Pais pais){
         List<Provincia> provincias= GestorProvincia.listarProvincias(pais);
         cmbProvincia.removeAllItems();


         for(int i=0;i<provincias.size();i++){
             cmbProvincia.addItem(provincias.get(i));
         }
         cmbProvincia.setSelectedIndex(-1);

            cmbLocalidad.removeAllItems();
             cmbLocalidad.repaint();
             cmbBarrio.removeAllItems();
             cmbBarrio.repaint();

        cmbProvincia.repaint();
    }


private void cargarComboLocalidades(Provincia provincia){
        List<Localidad> localidad= GestorLocalidad.listarLocalidades(provincia);
         cmbLocalidad.removeAllItems();

         for(int i=0;i<localidad.size();i++){
             cmbLocalidad.addItem(localidad.get(i));
         }             cmbBarrio.removeAllItems();
             cmbBarrio.repaint();

         this.cmbLocalidad.setSelectedIndex(-1);
         this.cmbLocalidad.repaint();
    }

private void cargarComboBarrio(Localidad localidad){

         List<Barrio> barrio=GestorBarrio.listarBarrios(localidad);
         cmbBarrio.removeAllItems();

         for(int i=0;i<barrio.size();i++){
             cmbBarrio.addItem(barrio.get(i));
         }
         this.cmbBarrio.setSelectedIndex(-1);
         this.cmbBarrio.repaint();

    }

}
