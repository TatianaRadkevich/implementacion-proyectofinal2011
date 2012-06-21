/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaRegistrarEntregaPedido.java
 *
 * Created on 11-oct-2011, 10:19:38
 */
package Presentacion.Deposito;

import BaseDeDatos.Deposito.AlmacenamientoProductoTerminado;
import BaseDeDatos.Deposito.AlmacenamientoProductoTerminadoBD;
import BaseDeDatos.Deposito.EAlmacenamientoProductoTerminado;
import BaseDeDatos.Ventas.EstadoPedidoBD;
import BaseDeDatos.Ventas.PedidoBD;
import Negocio.Exceptiones.NegocioException;
import Negocio.Ventas.Cliente;
import Negocio.Ventas.DetallePedido;
import Negocio.Ventas.EstadoPedido;
import Negocio.Ventas.Pedido;
import Presentacion.Mensajes;
import Presentacion.TablaManager;
import Presentacion.Ventas.PantallaClienteConsultar;
import java.util.List;
import java.util.Vector;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Heber Parrucci
 */
public class PantallaRegistrarEntregaPedido extends javax.swing.JDialog {
    //private PantallaClienteConsultar pantallaCliente;

    private TablaManager<Pedido> managerPedidos;
    private TablaManager<DetallePedido> managerDetalles;
    private Cliente cliente;
    private Cliente clienteSeleccionado;

    /** Creates new form PantallaRegistrarEntregaPedido */
    public PantallaRegistrarEntregaPedido(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //pantallaCliente  = new PantallaClienteConsultar(parent, true, true);
        //pantallaCliente.setVisible(true);

        while (true) {
            clienteSeleccionado = PantallaClienteConsultar.iniciarConsultarCliente(parent);
            if (clienteSeleccionado == null) {
                boolean respuesta = Mensajes.mensajeConfirmacionGenerico("Necesita elegir un cliente para poder proseguir.\n"
                        + "¿Desea volver a la pantalla de busqueda?");
                if (respuesta == false) {
                    this.setVisible(false);
                    this.dispose();
                    //return;//me voy a la mierda
                    throw new NegocioException("No se elgió ningun cliente");
                }
            } else if (PedidoBD.getPedidosAlamacenadoYTerminado(clienteSeleccionado).isEmpty()) {
                Mensajes.mensajeErrorGenerico("El cliente debe tener al menos un pedido que este 'Almacenado' y/o 'Terminado'");
            } else {//el cliente no es null y tiene pedidos
                break;//salgo del while
            }
        }

        managerPedidos = new TablaManager<Pedido>(jTablePedidos) {

            @Override
            public Vector getCabecera() {
                Vector cabcera = new Vector();
                cabcera.add("ID Pedido");//col 1
                cabcera.add("Estado Pedido");//col 2
                cabcera.add("Tipo Pedido");//col 3
                cabcera.add("Cantidad de Detalles");//col 3

                return cabcera;
            }

            @Override
            public Vector ObjetoFila(Pedido elemento) {
                Vector fila = new Vector();
                fila.add(elemento.getIdPedido());
                fila.add(elemento.getEstadoPedido());//col 3
                fila.add(elemento.getTipoPedido().getNombre());//col 4
                fila.add(elemento.getDetallePedido().size());//col 1
                return fila;
            }
        };
        jTablePedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        managerDetalles = new TablaManager<DetallePedido>(jTableDetalles) {

            @Override
            public Vector getCabecera() {
                Vector cabcera = new Vector();
                cabcera.add("Producto");//col 1
                cabcera.add("Precio");//col 2
                cabcera.add("Cantidad");//col 3
                cabcera.add("Estado");//col 3

                return cabcera;
            }

            @Override
            public Vector ObjetoFila(DetallePedido elemento) {
                Vector fila = new Vector();
                fila.add(elemento.getProducto().getNombre());
                fila.add("$ " + elemento.getPrecio());//col 3
                fila.add(elemento.getCantidad());//col 1
                fila.add(elemento.getEstadoDetallePedido().getNombre());//col 4
                return fila;
            }
        };

        managerPedidos.addSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                boolean var = false;
                Pedido p = managerPedidos.getSeletedObject();
                if (p != null) {
                    managerDetalles.setDatos(p.getDetallePedido());
                }
            }
        });
        cargarClienteYPedidos();
        AlmacenamientoProductoTerminado a = new AlmacenamientoProductoTerminado();
        EAlmacenamientoProductoTerminado estado = new EAlmacenamientoProductoTerminado();
        a.setEstado(estado);
        AlmacenamientoProductoTerminadoBD.listarAlmacenamientos();
    }

    private void cargarClienteYPedidos() {
        cliente = clienteSeleccionado;//pantallaCliente.getCliente(); el cliente ya esta cargado
        jTextFieldRazonSocial.setText(cliente.getRazonSocial());
        jTextFieldMail.setText(cliente.getCorreoElectronico());
        jTextFieldResponsable.setText(cliente.getApellidoResponsable() + ", " + cliente.getNombreResponsable());
        jTextFieldTelefono.setText(cliente.getTelefonoResponsable().toString());
        if (cliente.getDomicilio() != null)
            jTextFieldDomicilio.setText(cliente.getDomicilio().getCalle() + cliente.getDomicilio().getNumero());
        jTextFieldTipo.setText(cliente.getTipoCliente().getNombre());
        List<Pedido> pedidos = PedidoBD.getPedidosAlamacenadoYTerminado(cliente);
        managerPedidos.setDatos(pedidos);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldRazonSocial = new javax.swing.JTextField();
        jTextFieldMail = new javax.swing.JTextField();
        jTextFieldDomicilio = new javax.swing.JTextField();
        jTextFieldTipo = new javax.swing.JTextField();
        jTextFieldResponsable = new javax.swing.JTextField();
        jTextFieldTelefono = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePedidos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableDetalles = new javax.swing.JTable();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entregar Pedido a Cliente");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        jLabel1.setText("Razón Social:");

        jLabel2.setText("E-Mail:");

        jLabel3.setText("Domicilio:");

        jLabel4.setText("Tipo Cliente:");

        jLabel5.setText("Responsable:");

        jLabel6.setText("Teléfono Responsable:");

        jTextFieldRazonSocial.setEditable(false);

        jTextFieldMail.setEditable(false);

        jTextFieldDomicilio.setEditable(false);

        jTextFieldTipo.setEditable(false);

        jTextFieldResponsable.setEditable(false);

        jTextFieldTelefono.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldDomicilio, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldMail, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldRazonSocial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldTipo, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(jTextFieldResponsable)
                    .addComponent(jTextFieldTelefono))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos"));

        jTablePedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTablePedidos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
        );

        jButton1.setText("Entregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalles"));

        jTableDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTableDetalles);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Pedido p = managerPedidos.getSeletedObject();
        if (p != null) {
            boolean ret = Mensajes.mensajeConfirmacionGenerico("¿Está seguro que desea entregar el pedido " + p.getIdPedido() + "?");
            if (ret) {
                p.setEstadoPedido(EstadoPedidoBD.getEstadoRetirado());
                PedidoBD.guardar(p);
                Mensajes.mensajeInformacion("Se ha entregado con éxito el pedido " + p.getIdPedido());
                this.dispose();
            }
        } else {
            Mensajes.mensajeErrorGenerico("Seleccione un pedido correcto a entregar");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableDetalles;
    private javax.swing.JTable jTablePedidos;
    private javax.swing.JTextField jTextFieldDomicilio;
    private javax.swing.JTextField jTextFieldMail;
    private javax.swing.JTextField jTextFieldRazonSocial;
    private javax.swing.JTextField jTextFieldResponsable;
    private javax.swing.JTextField jTextFieldTelefono;
    private javax.swing.JTextField jTextFieldTipo;
    // End of variables declaration//GEN-END:variables
}
