/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaABMMaquinaOHerramienta.java
 *
 * Created on 20/06/2011, 22:56:49
 */

package Presentacion.Produccion;


import BaseDeDatos.Compras.MaterialBD;
import Negocio.Compras.Material;
import Negocio.Exceptiones.ExceptionGestor;
import Negocio.Produccion.*;
import Presentacion.Mensajes;
import Presentacion.TablaManager;
import Presentacion.Utilidades;
import Presentacion.ValidarTexbox;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Heber Parrucci
 */
public class PantallaMaquinaHerramientaABM extends javax.swing.JDialog {

    /** Creates new form PantallaABMMaquinaOHerramienta */
    private GestorMaquinaHerramienta gestor;
    private TablaManager<CapacidadProductiva> tmCapacidad;
    private PantallaMaquinaHerramientaABM(java.awt.Frame parent, boolean modal) {
        super(parent, modal);        
        initComponents();
        pnlBaja.setVisible(false);
    }

    public PantallaMaquinaHerramientaABM(GestorMaquinaHerramienta g)
    {
        this(null,true);
        gestor=g;

        /////////// Precargas Necesarias //////////
        cargarValidaciones();
        recargarComboTipoMaquina();
        configurarTablaCapacidad();
    }

    private void configurarTablaCapacidad() {

        tmCapacidad = new TablaManager<CapacidadProductiva>(tbCapacidadProductiva) {

            @Override
            public Vector ObjetoFila(CapacidadProductiva elemento) {
                Vector fila = new Vector();                
                fila.add(elemento.getMaterial());
                fila.add(elemento.getCapacidad());
                return fila;
            }

            @Override
            public boolean isCellEditable(int columna) {
                return true;
            }

            @Override
            public Vector getCabecera() {
                Vector cabecera = new Vector();                
                cabecera.add("Material");
                cabecera.add("Capaciad");
                return cabecera;
            }

            @Override
            public void fireTableCellUpdated(int row, int column) {
                CapacidadProductiva det = tmCapacidad.getDato(row);
                Object value = tbCapacidadProductiva.getModel().getValueAt(row, column);
                if (value == null) {
                    return;
                }
                switch (column) {
                    case (0):
                        det.setMaterial((Material) value);
                        break;
                    case (1):
                        det.setCapacidad((Integer) value);
                        break;
                   
                }
            }
        };

        tbCapacidadProductiva.getColumnModel()
                .getColumn(0)
                .setCellEditor(new DefaultCellEditor(new JComboBox(MaterialBD.getMateriales("", "", true, false).toArray())));

    }

    private void recargarComboTipoMaquina()
    {
        cmbTipoMaquinaHerramienta.setModel(new DefaultComboBoxModel(gestor.getTipoMaquinaHerramienta().toArray()));
    }

    public void cargarValidaciones()
    {
//        ValidarTexbox.validarInt(txtCapacidadProductiva);
//        ValidarTexbox.validarLongitud(txtCapacidadProductiva,5);
        ValidarTexbox.validarLongitud(txtCaracteristicas,200);
        ValidarTexbox.validarLongitud(txtModelo,50);
        ValidarTexbox.validarLongitud(txtNombre,50);
        ValidarTexbox.validarLongitud(txtObservaciones,200);
        ValidarTexbox.validarLongitud(txtCodigo,2);

    }

    public void habilitarBaja(boolean visible,boolean edittable, Date fecha,String motivo)
    {
        pnlBaja.setVisible(visible);
        txtMotivoBaja.setEditable(edittable);
        txtMotivoBaja.setText(Utilidades.parseString(motivo));
        txtFechaBaja.setText(Utilidades.parseFecha(fecha));

    }
    public void cargar(MaquinaHerramientaParticular m) {

        //txtCapacidadProductiva.setText(Utilidades.parseString(m.getCapacidadProductiva()));
        tmCapacidad.setDatos(m.getCapacidadProductiva());
        txtCaracteristicas.setText(Utilidades.parseString(m.getCaracteristicas()));
        txtCodigo.setText(Utilidades.parseString(m.getCodigo()));
        txtModelo.setText(Utilidades.parseString(m.getModelo()));
        txtNombre.setText(Utilidades.parseString(m.getNombre()));
        txtObservaciones.setText(Utilidades.parseString(m.getObservaciones()));
        cmbTipoMaquinaHerramienta.setSelectedItem(m.getTipoMaquinaHerramienta());
             if(m.getFechaBaja()!=null)
        {
            pnlBaja.setVisible(true);
            txtFechaBaja.setText(Utilidades.parseFecha(m.getFechaBaja()));
            txtMotivoBaja.setText(Utilidades.parseString(m.getMotivoBaja()));
        }
    }

    public void habilitarTodo(boolean b) {
        tbCapacidadProductiva.setEnabled(b);
        txtCaracteristicas.setEditable(b);
        txtCodigo.setEditable(b);
        txtModelo.setEditable(b);
        txtNombre.setEditable(b);
        txtObservaciones.setEditable(b);
        cmbTipoMaquinaHerramienta.setEditable(b);
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
        jLabel5 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        cmbTipoMaquinaHerramienta = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCaracteristicas = new javax.swing.JTextArea();
        btnAgregarTipo = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlBaja = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtFechaBaja = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMotivoBaja = new javax.swing.JTextArea();
        pnlCapacidad = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbCapacidadProductiva = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nueva Máquina o Herramienta ");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Maquina/Herramienta"));

        jLabel5.setText("Características:");

        jLabel7.setText("Observaciones:");

        jLabel1.setText("Tipo:");

        jLabel4.setText("Modelo:");

        txtObservaciones.setLineWrap(true);
        txtObservaciones.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtObservaciones);

        cmbTipoMaquinaHerramienta.setName(""); // NOI18N

        jLabel2.setText("Codigo:");

        jLabel3.setText("Nombre:");

        txtCaracteristicas.setLineWrap(true);
        txtCaracteristicas.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtCaracteristicas);

        btnAgregarTipo.setText("...");
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
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbTipoMaquinaHerramienta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnAgregarTipo))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtModelo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbTipoMaquinaHerramienta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarTipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                .addContainerGap())
        );

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

        pnlBaja.setBorder(javax.swing.BorderFactory.createTitledBorder("Cancelación"));

        jLabel8.setText("Fecha:");

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
                    .addComponent(jLabel8)
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
                    .addComponent(jLabel8)
                    .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlCapacidad.setBorder(javax.swing.BorderFactory.createTitledBorder("Capacidad Productiva"));

        tbCapacidadProductiva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tbCapacidadProductiva);

        javax.swing.GroupLayout pnlCapacidadLayout = new javax.swing.GroupLayout(pnlCapacidad);
        pnlCapacidad.setLayout(pnlCapacidadLayout);
        pnlCapacidadLayout.setHorizontalGroup(
            pnlCapacidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCapacidadLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlCapacidadLayout.setVerticalGroup(
            pnlCapacidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapacidadLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBaja, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlCapacidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCapacidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(pnlBaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed

        MaquinaHerramientaParticular maqHer = gestor.getMaquinaHerramientaParticular();
        maqHer.setCapacidadProductiva(tmCapacidad.getDatos());
        maqHer.setCaracteristicas(txtCaracteristicas.getText());
        maqHer.setCodigo(txtCodigo.getText());
        maqHer.setModelo(txtModelo.getText());
        maqHer.setNombre(txtNombre.getText());
        maqHer.setObservaciones(txtObservaciones.getText());
        maqHer.setTipoMaquinaHerramienta((TipoMaquinaHerramienta) cmbTipoMaquinaHerramienta.getSelectedItem());
        maqHer.setMotivoBaja(Utilidades.parseString(txtMotivoBaja.getText()));
        try {
            gestor.ejecutarCU(maqHer);
            this.setVisible(false);
        } catch (ExceptionGestor ex) {
            Mensajes.mensajeErrorGenerico(ex.getMessage());
        }

    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        gestor.finalizarCU();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAgregarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTipoActionPerformed
        // TODO add your handling code here:
        new PantallaMaquinaHerramientaTipoABM().setVisible(true);
        recargarComboTipoMaquina();
    }//GEN-LAST:event_btnAgregarTipoActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PantallaMaquinaHerramientaABM dialog = new PantallaMaquinaHerramientaABM(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox cmbTipoMaquinaHerramienta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel pnlBaja;
    private javax.swing.JPanel pnlCapacidad;
    private javax.swing.JTable tbCapacidadProductiva;
    private javax.swing.JTextArea txtCaracteristicas;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtFechaBaja;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextArea txtMotivoBaja;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextArea txtObservaciones;
    // End of variables declaration//GEN-END:variables

 


}
