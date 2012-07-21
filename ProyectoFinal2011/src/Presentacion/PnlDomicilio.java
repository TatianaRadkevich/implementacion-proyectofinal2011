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
import Negocio.Exceptiones.NegocioException;
import Negocio.Exceptiones.TipoDatoException;
import Negocio.UbicacionGeografica.*;
import Negocio.UbicacionGeografica.Pais;
import Presentacion.ZLinkers.ZLComboBox;
import Presentacion.ZLinkers.ZLObject;
import Presentacion.ZLinkers.ZLTextField;
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
    private boolean inicializado = false;
    private ZLObject<Domicilio> link;

    public PnlDomicilio() {
        initComponents();

        cmbProvincia.setEnabled(false);
        cmbLocalidad.setEnabled(false);
        cmbBarrio.setEnabled(false);

        this.domicilio = new Domicilio();
        link = new ZLObject<Domicilio>(Domicilio.class, domicilio);
        link.add("pais", new ZLComboBox(cmbPais));
        link.add("provincia", new ZLComboBox(cmbProvincia));
        link.add("localidad", new ZLComboBox(cmbLocalidad));
        link.add("barrio", new ZLComboBox(cmbBarrio));
        link.add("calle", new ZLTextField(txtCalle));
        link.add("depto", new ZLTextField(txtDepto));
        link.add("numero", new ZLTextField(txtNumero));
        link.add("piso", new ZLTextField(txtPiso));

    }

    public void inicializar() {
        if (inicializado) {
            return;
        }
        Utilidades.comboCargar(cmbPais, gestorPais.listarPaises());
        setDefaul();
        inicializado = true;
    }

    private void setDefaul() {
        cmbPais.setSelectedItem(new Pais("Argentina"));
        cmbProvincia.setSelectedItem(new Provincia("Córdoba"));
        cmbLocalidad.setSelectedItem(new Localidad("capital"));
        txtCalle.setText("");
        txtDepto.setText("");
        txtNumero.setText("");
        txtPiso.setText("");

    }

    public Domicilio getDomicilio() throws NegocioException {

//            domicilio.setCalle(txtCalle.getText());
//            domicilio.setDepto(txtDepto.getText());
//            domicilio.setNumero(Utilidades.parseInteger(txtNumero.getText()));
//            domicilio.setPiso(Utilidades.parseShort(txtPiso.getText()));
//            domicilio.setTBarrios((Barrio) cmbBarrio.getSelectedItem());
//            domicilio.setTLocalidades((Localidad) cmbLocalidad.getSelectedItem());
//            domicilio.setTPaises((Pais) cmbPais.getSelectedItem());
//            domicilio.setTProvincias((Provincia) cmbProvincia.getSelectedItem());
        link.save();

//        } catch (TipoDatoException ex) {
//            return null;

        return domicilio;
    }

    public void setDomicilio(Domicilio dom) {
        if (dom == null) {
            setDefaul();
            return;
        }
        domicilio = dom;
        link.setObjeto(domicilio);
        link.load();
//        cmbProvincia.setEnabled(true);
//        cmbLocalidad.setEnabled(true);
//        cmbBarrio.setEnabled(true);
//
//        domicilio = dom;
//        cmbPais.setSelectedItem(dom.getTPaises());
//        cmbProvincia.setSelectedItem(dom.getTProvincias());
//        cmbLocalidad.setSelectedItem(dom.getTLocalidades());
//        cmbBarrio.setSelectedItem(dom.getTBarrios());
//        txtCalle.setText(dom.getCalle());
//        txtDepto.setText(dom.getDepto());
//        txtNumero.setText(Utilidades.parseString(dom.getNumero()));
//        txtPiso.setText(Utilidades.parseString(dom.getPiso()));
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
        btnAgregarPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPaisActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("Provincia:");

        btnAgregarLocalidad.setText("+");
        btnAgregarLocalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarLocalidadActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("País:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setText("Barrio:");

        btnAgregarProvincia.setText("+");
        btnAgregarProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProvinciaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setText("Localidad:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setText("Número:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setText("Calle:");

        cmbPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPaisActionPerformed(evt);
            }
        });

        cmbLocalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLocalidadActionPerformed(evt);
            }
        });

        btnAgregarBarrio.setText("+");
        btnAgregarBarrio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarBarrioActionPerformed(evt);
            }
        });

        cmbProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProvinciaActionPerformed(evt);
            }
        });

        cmbBarrio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBarrioActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel12.setText("Piso:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel14.setText("Depto:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbProvincia, 0, 142, Short.MAX_VALUE)
                            .addComponent(cmbPais, 0, 142, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAgregarProvincia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregarPais)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cmbBarrio, 0, 142, Short.MAX_VALUE)
                                    .addComponent(cmbLocalidad, 0, 142, Short.MAX_VALUE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnAgregarBarrio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAgregarLocalidad)))
                            .addComponent(txtCalle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtDepto, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPiso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarPais))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarProvincia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarLocalidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbBarrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarBarrio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="botones">
    private void btnAgregarPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPaisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarPaisActionPerformed

    private void btnAgregarProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProvinciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarProvinciaActionPerformed

    private void btnAgregarLocalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarLocalidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarLocalidadActionPerformed

    private void btnAgregarBarrioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarBarrioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarBarrioActionPerformed
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Combos">
    private void cmbPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPaisActionPerformed
        // TODO add your handling code here:
        Object o = cmbPais.getSelectedItem();
        if (o != null && o instanceof Pais) {
            Utilidades.comboCargar(cmbProvincia, GestorProvincia.listarProvincias((Pais) o));
            cmbProvincia.setEnabled(true && this.isEnabled());
            if (cmbProvincia.getItemCount() == 0) {
                cmbProvincia.addItem("<vacio>");
                cmbProvincia.setSelectedIndex(0);
                cmbProvincia.setEnabled(false);
            }
        } else {
            cmbProvincia.removeAllItems();
            cmbProvincia.addItem("Seleccione un pais");
            cmbProvincia.setSelectedIndex(0);
            cmbProvincia.setEnabled(false);
        }

    }//GEN-LAST:event_cmbPaisActionPerformed

    private void cmbProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProvinciaActionPerformed
        // TODO add your handling code here:
        Object o = cmbProvincia.getSelectedItem();
        if (o != null && o instanceof Provincia) {
            Utilidades.comboCargar(cmbLocalidad, GestorLocalidad.listarLocalidades((Provincia) o));
            cmbLocalidad.setEnabled(true && this.isEnabled());

            if (cmbLocalidad.getItemCount() == 0) {
                cmbLocalidad.addItem("<vacio>");
                cmbLocalidad.setSelectedIndex(0);
                cmbLocalidad.setEnabled(false);
            }

        } else {
            cmbLocalidad.removeAllItems();
            cmbLocalidad.addItem("Seleccione una provincia");
            cmbLocalidad.setSelectedIndex(0);
            cmbLocalidad.setEnabled(false);
        }
    }//GEN-LAST:event_cmbProvinciaActionPerformed

    private void cmbLocalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLocalidadActionPerformed
        // TODO add your handling code here:
        Object o = cmbLocalidad.getSelectedItem();
        if (o != null && o instanceof Localidad) {
            Utilidades.comboCargar(cmbBarrio, GestorBarrio.listarBarrios((Localidad) o));
            cmbBarrio.setEnabled(true && this.isEnabled());
            if (cmbBarrio.getItemCount() == 0) {
                cmbBarrio.addItem("<vacio>");
                cmbBarrio.setSelectedIndex(0);
                cmbBarrio.setEnabled(false);
            }
        } else {
            cmbBarrio.removeAllItems();
            cmbBarrio.addItem("Seleccione un barrio");
            cmbBarrio.setSelectedIndex(0);
            cmbBarrio.setEnabled(false);
        }
    }//GEN-LAST:event_cmbLocalidadActionPerformed

    private void cmbBarrioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBarrioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBarrioActionPerformed
    // </editor-fold>
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
}
