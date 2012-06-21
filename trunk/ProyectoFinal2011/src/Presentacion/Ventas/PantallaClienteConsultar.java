/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaClienteConsultar.java
 *
 * Created on 09/09/2011, 10:39:03
 */
package Presentacion.Ventas;

import BaseDeDatos.HibernateUtil;
import BaseDeDatos.Ventas.ClienteBD;
import BaseDeDatos.Ventas.EstadoPedidoBD;
import BaseDeDatos.Ventas.PedidoBD;
import Negocio.Exceptiones.ExceptionGestor;
import Negocio.Exceptiones.NegocioException;
import Negocio.Ventas.Cliente;
import Negocio.Ventas.GestorClienteConsultar;
import Negocio.Ventas.Pedido;
import Negocio.Ventas.Pedido;
import Presentacion.IniciadorDeVentanas;
import Presentacion.Mensajes;
import Presentacion.TablaManager;
import Presentacion.ValidarTexbox;
import Presentacion.ZLinkers.ZLinkerCheckBox;
import Presentacion.ZLinkers.ZLinkerCheckList;
import Presentacion.ZLinkers.ZLinkerObject;
import Presentacion.ZLinkers.ZLinkerTextField;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Rodrigo
 */
public class PantallaClienteConsultar extends javax.swing.JDialog {

    private TablaManager<Cliente> tablita;
    private GestorClienteConsultar gestor;
    private ZLinkerObject<GestorClienteConsultar> link;

    /** Creates new form PantallaClienteConsultar */
    private PantallaClienteConsultar(java.awt.Window parent, boolean modal, GestorClienteConsultar g) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.gestor = g;
        initComponents();
        IniciadorDeVentanas.iniciarVentana(this, this.getWidth(), this.getHeight());
        HibernateUtil.getSessionFactory();

        tablita = new TablaManager<Cliente>(tbClientes) {

            @Override
            public Vector getCabecera() {
                Vector cabcera = new Vector();
                cabcera.add("CUIT");//col 1
                cabcera.add("Razón Social");//col 2
                cabcera.add("Mail");//col 3
                cabcera.add("Responsable");//col 3
                cabcera.add("Teléfono Responsable");//col 4

                return cabcera;
            }

            @Override
            public Vector ObjetoFila(Cliente elemento) {
                Vector fila = new Vector();
                fila.add(elemento.getCuit());
                fila.add(elemento.getRazonSocial());//col 3
                fila.add(elemento.getCorreoElectronico());//col 4

                fila.add(elemento.getApellidoResponsable() + ", "
                        + elemento.getNombreResponsable());//col 1
                fila.add(elemento.getTelefonoResponsable());//col 2
                return fila;
            }
        };
        tablita.setDatos(this.gestor.getResultado());
        tablita.addSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                gestor.setClienteSelecionado(tablita.getSeletedObject());
            }
        });
        cargarValidaciones();

        Class c = GestorClienteConsultar.class;
        link = new ZLinkerObject<GestorClienteConsultar>(c, gestor);
        
        link.add("cuit",new ZLinkerTextField(txtCUIT));
        link.add("razonSocial",new ZLinkerTextField(txtRazonSocial));
        link.add("apellidoResponsable",new ZLinkerTextField(txtApellido));
        link.add("ombreResponsable",new ZLinkerTextField(txtNombre));
        link.add("cancelados",new ZLinkerCheckBox(chkMostrarCancelados));
        link.add("vigentes",new ZLinkerCheckBox(chkMostrarVigentes));
        link.load();
        //        GUILocal.establecerGUILocal(this);
    }  



    private void cargarValidaciones() {
        //**********************Validacion de textBox********************//
//        ValidarTexbox.validarLongitud(txtCUIT, 20);
//        ValidarTexbox.validarLongitud(txtRazonSocial, 50);
//        ValidarTexbox.validarLongitud(txtNombre, 50);
//        ValidarTexbox.validarLongitud(txtApellido, 50);
        /************************Validacion de botones **********************************/
        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnVer.setEnabled(false);
        tablita.addSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                boolean var = false;
                if (tablita.getSeletedObject() != null) {
                    if (tablita.getSeletedObject().getFechaBaja() == null) {
                        var = true;
                    }
                }

                btnEliminar.setEnabled(var);
                btnModificar.setEnabled(var);
                btnVer.setEnabled(var);
            }
        });
    }

    private void buscarClientes() {
        gestor.buscar();
        tablita.setDatos(gestor.getResultado());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBuscar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtRazonSocial = new javax.swing.JTextField();
        txtCUIT = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        chkMostrarVigentes = new javax.swing.JCheckBox();
        btnBuscar = new javax.swing.JButton();
        chkMostrarCancelados = new javax.swing.JCheckBox();
        pnlClientes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbClientes = new javax.swing.JTable();
        pnlBotones = new javax.swing.JPanel();
        btnModificar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consultar Clientes");

        pnlBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Búsqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Razón Social:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("CUIT:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Nombre del Responsble:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Apellido del Responsable:");

        chkMostrarVigentes.setFont(new java.awt.Font("Tahoma", 1, 11));
        chkMostrarVigentes.setSelected(true);
        chkMostrarVigentes.setText("Mostrar Vigentes");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        chkMostrarCancelados.setFont(new java.awt.Font("Tahoma", 1, 11));
        chkMostrarCancelados.setText("Mostrar Cancelados");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addComponent(btnBuscar))
            .addComponent(chkMostrarCancelados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(chkMostrarVigentes, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(chkMostrarVigentes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkMostrarCancelados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(btnBuscar))
        );

        javax.swing.GroupLayout pnlBuscarLayout = new javax.swing.GroupLayout(pnlBuscar);
        pnlBuscar.setLayout(pnlBuscarLayout);
        pnlBuscarLayout.setHorizontalGroup(
            pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBuscarLayout.createSequentialGroup()
                .addGroup(pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombre)
                    .addComponent(txtApellido)
                    .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCUIT, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlBuscarLayout.setVerticalGroup(
            pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBuscarLayout.createSequentialGroup()
                .addGroup(pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBuscarLayout.createSequentialGroup()
                        .addGroup(pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtCUIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlClientes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbClientes);

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEliminar.setText("Cancelar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnVer.setText("Ver");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
            .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnVer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
            .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlClientesLayout = new javax.swing.GroupLayout(pnlClientes);
        pnlClientes.setLayout(pnlClientesLayout);
        pnlClientesLayout.setHorizontalGroup(
            pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlClientesLayout.setVerticalGroup(
            pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClientesLayout.createSequentialGroup()
                .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
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

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlClientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                    .addComponent(pnlBuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:        
        this.buscarClientes();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {

            Cliente c = tablita.getSeletedObject();
            if (c == null) {
                throw new NegocioException("No se ha elejido un clienter");
            }

            if (c.getFechaBaja() != null) {
                throw new NegocioException("El cliente seleccionado está eliminado, no se puede modificar");
            }

            PantallaClienteABM.iniciarClienteModificar(this, tablita.getSeletedObject());
            this.buscarClientes();
        } catch (NegocioException ex) {
            Mensajes.mensajeErrorGenerico(ex.getMessage());
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        PantallaClienteABM.iniciarClienteAlta(this);
        tbClientes.clearSelection();
        this.buscarClientes();
}//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            Cliente c = tablita.getSeletedObject();
            if (c == null) {
                throw new NegocioException("No se ha elejido un clienter");
            }

            if (c.getFechaBaja() != null) {
                throw new NegocioException("El cliente seleccionado ya está eliminado");
            }


            Set<Pedido> pedidos = c.getPedidos();
            for (Pedido ped : pedidos) {
                if (ped.getEstadoPedido().equals(EstadoPedidoBD.getEstadoPagado()) == false) {
                    if (ped.getEstadoPedido().equals(EstadoPedidoBD.getEstadoCancelado()) == false) {
                        throw new NegocioException(
                                "No se puede eliminar el cliente selecsionado aun tiene pedidos vigentes sin finalizar");
                    }
                }
            }
            PantallaClienteABM.iniciarClienteBaja(this, tablita.getSeletedObject());
            this.buscarClientes();
        } catch (Exception ex) {
            Mensajes.mensajeErrorGenerico(ex.getMessage());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
}//GEN-LAST:event_btnSalirActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        gestor.setClienteSelecionado(tablita.getSeletedObject());
        this.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        // TODO add your handling code here:
        try {
            Cliente c = tablita.getSeletedObject();
            if (c == null) {
                throw new NegocioException("No se ha elejido un clienter");
            }

            PantallaClienteABM.iniciarClienteVer(this, tablita.getSeletedObject());
            this.buscarClientes();
        } catch (Exception ex) {
            Mensajes.mensajeErrorGenerico(ex.getMessage());
        }
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        gestor.setClienteSelecionado(null);
        this.dispose();        
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                iniciarAdministrarCliente(null);
            }
        });
    }

    public static void iniciarAdministrarCliente(Window parent) {
        PantallaClienteConsultar interfaz= new PantallaClienteConsultar(parent, true, new GestorClienteConsultar());
        
        interfaz.setTitle("Consultar Cliente");
        interfaz.btnAceptar.setVisible(false);
        interfaz.btnCancelar.setVisible(false);
        interfaz.btnSalir.setVisible(true);
        interfaz.setVisible(true);
    }

    public static Cliente iniciarConsultarCliente(Window parent) {
        GestorClienteConsultar gestor = new GestorClienteConsultar();
        PantallaClienteConsultar interfaz =new PantallaClienteConsultar(parent, true, gestor);
        
        interfaz.setTitle("Consultar Cliente");

        interfaz.pnlBotones.setVisible(false);
        interfaz.btnSalir.setVisible(false);

        interfaz.btnAceptar.setVisible(true);
        interfaz.btnCancelar.setVisible(true);

        interfaz.setVisible(true);
        return gestor.getClienteSelecionado();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVer;
    private javax.swing.JCheckBox chkMostrarCancelados;
    private javax.swing.JCheckBox chkMostrarVigentes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlBuscar;
    private javax.swing.JPanel pnlClientes;
    private javax.swing.JTable tbClientes;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCUIT;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRazonSocial;
    // End of variables declaration//GEN-END:variables
}
