/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Principal.java
 *
 * Created on 17/08/2011, 17:53:23
 */
package Presentacion;

import Negocio.Administracion.GestorHorario;
import Negocio.Deposito.GestorRecepcionMaterial;
import Negocio.Produccion.GestorEstructura;
import Negocio.Produccion.GestorOrdenTrabajo;
import Negocio.Produccion.GestorAvanceProduccion;
import Negocio.Produccion.GestorTipoHerramienta;
import Negocio.Produccion.GestorTipoMaquina;
import Negocio.UbicacionGeografica.gestorPais;
import Presentacion.Administracion.*;
import Presentacion.Compras.PantallaMaterialConsultar;
import Presentacion.Compras.PantallaOrdenCompraConsultar;
import Presentacion.Compras.PantallaProveedorConsultar;
import Presentacion.Deposito.*;
import Presentacion.Produccion.PantallaABMTipoProducto;
import Presentacion.Produccion.PantallaPlanConsultar;
import Presentacion.Produccion.PantallaConsultarProducto;
import Presentacion.Produccion.ProblemaMaquinaHerramientaParticular;
import Presentacion.Produccion.ConsultaProblemasMHP;
import Presentacion.Produccion.PantallaABMEtapaProduccion;
import Presentacion.Produccion.PantallaHerramientaConsultar;
import Presentacion.Produccion.PantallaMaquinaConsultar;
import Presentacion.Produccion.PantallaPlanProduccionNuevo;
import Presentacion.Produccion.PantallaPlanProduccionPrincipal;
import Presentacion.Produccion.PantallaTipoHerramientaABM;
import Presentacion.Produccion.PantallaTipoMaquinaABM;
import Presentacion.UbicacionGeografica.PantallaABMPais;
import Presentacion.Ventas.PantallaClienteConsultar;
import Presentacion.Ventas.PantallaFacturaConsultar;

import BaseDeDatos.Usuario.UsuarioBD;
import Negocio.Administracion.Cargo;
import Negocio.Administracion.Empleado;
import Negocio.GestionUsuario.*;


import Presentacion.Ventas.PantallaPedidoConsultar;
import gui.GUILocal;
import java.awt.Graphics;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author Ivan
 */
public class Principal extends javax.swing.JFrame {

    /** Creates new form Principal */
    ImageIcon imagen;

    public Principal() {
        GUILocal.SyntheticaBlueIceLookAndFeel(this);
        this.setContentPane(new Fondo("fondo.png"));
        initComponents();
     
        ocultarMenu();
        
        this.setExtendedState(this.getExtendedState() | MAXIMIZED_BOTH);
    }

    private void visible(boolean valor) {
             
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSession = new javax.swing.JPanel();
        btnIniciarSession = new javax.swing.JButton();
        lblMensaje = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        txtUsuario = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblMensajeInicio = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuSalir = new javax.swing.JMenu();
        mnuIniciarSesion = new javax.swing.JMenuItem();
        mnuExit = new javax.swing.JMenuItem();
        mnuAdministracion = new javax.swing.JMenu();
        mnuCargo = new javax.swing.JMenuItem();
        mnuEmpleado = new javax.swing.JMenuItem();
        mnuTipoDocumento = new javax.swing.JMenuItem();
        mnuFormaPago = new javax.swing.JMenuItem();
        mnuRegistrarAsistencia = new javax.swing.JMenuItem();
        mnuHorarios = new javax.swing.JMenuItem();
        mnuConsultarAsistencia = new javax.swing.JMenuItem();
        mnuAsignarHorario = new javax.swing.JMenuItem();
        mnuCompras = new javax.swing.JMenu();
        mnuMaterial = new javax.swing.JMenuItem();
        mnuProveedor = new javax.swing.JMenuItem();
        mnuOrdenCompra = new javax.swing.JMenuItem();
        mnuProduccion = new javax.swing.JMenu();
        mnuTipoProducto = new javax.swing.JMenuItem();
        mnuProducto = new javax.swing.JMenuItem();
        mnuMaquina = new javax.swing.JMenuItem();
        mnuEstructuraProducto = new javax.swing.JMenuItem();
        mnuPlanificacionViejo = new javax.swing.JMenuItem();
        mnuOrdenTrabajo = new javax.swing.JMenuItem();
        mnuAvanceProduccion = new javax.swing.JMenuItem();
        mnuProblemaMaquina = new javax.swing.JMenuItem();
        mnuConsultarProblemas = new javax.swing.JMenuItem();
        mnuTipoMaquina = new javax.swing.JMenuItem();
        mnuTipoHerramienta = new javax.swing.JMenuItem();
        mnuEtapaProduccion = new javax.swing.JMenuItem();
        mnuHerramienta = new javax.swing.JMenuItem();
        mnuPlanificacionNuevo = new javax.swing.JMenuItem();
        mnuVentas = new javax.swing.JMenu();
        mnuCliente = new javax.swing.JMenuItem();
        mnuPedido = new javax.swing.JMenuItem();
        mnuFactura = new javax.swing.JMenuItem();
        mnuDeposito = new javax.swing.JMenu();
        mnuReajustarStock = new javax.swing.JMenuItem();
        mnuRecepcionMateriales = new javax.swing.JMenuItem();
        mnuEntregarPedido = new javax.swing.JMenuItem();
        mnuEntregaMaterialesProduccion = new javax.swing.JMenuItem();
        mnuAlmacenamientoProductoTerminado = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú Principal");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        getContentPane().setLayout(null);

        pnlSession.setLayout(null);

        btnIniciarSession.setText("Iniciar Sesión");
        btnIniciarSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSessionActionPerformed(evt);
            }
        });
        pnlSession.add(btnIniciarSession);
        btnIniciarSession.setBounds(210, 120, 95, 23);

        lblMensaje.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblMensaje.setForeground(new java.awt.Color(255, 0, 0));
        lblMensaje.setText("jLabel1");
        pnlSession.add(lblMensaje);
        lblMensaje.setBounds(60, 150, 320, 14);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        pnlSession.add(btnSalir);
        btnSalir.setBounds(310, 120, 53, 23);
        pnlSession.add(txtUsuario);
        txtUsuario.setBounds(150, 40, 220, 20);
        pnlSession.add(txtPassword);
        txtPassword.setBounds(150, 70, 220, 20);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuario:");
        pnlSession.add(jLabel1);
        jLabel1.setBounds(90, 40, 60, 14);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Contraseña:");
        pnlSession.add(jLabel2);
        jLabel2.setBounds(70, 70, 80, 14);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Inicio de sesion.png"))); // NOI18N
        pnlSession.add(jLabel3);
        jLabel3.setBounds(-10, 0, 410, 172);

        getContentPane().add(pnlSession);
        pnlSession.setBounds(250, 250, 390, 170);

        lblMensajeInicio.setFont(new java.awt.Font("Tahoma", 1, 14));
        getContentPane().add(lblMensajeInicio);
        lblMensajeInicio.setBounds(10, 10, 870, 30);

        mnuSalir.setText("Sesión");

        mnuIniciarSesion.setText("Iniciar Sesión");
        mnuIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuIniciarSesionActionPerformed(evt);
            }
        });
        mnuSalir.add(mnuIniciarSesion);

        mnuExit.setText("Cerrar Sesión");
        mnuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuExitActionPerformed(evt);
            }
        });
        mnuSalir.add(mnuExit);

        jMenuBar1.add(mnuSalir);

        mnuAdministracion.setText("Administración");

        mnuCargo.setText("Cargo");
        mnuCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCargoActionPerformed(evt);
            }
        });
        mnuAdministracion.add(mnuCargo);

        mnuEmpleado.setText("Empleado");
        mnuEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEmpleadoActionPerformed(evt);
            }
        });
        mnuAdministracion.add(mnuEmpleado);

        mnuTipoDocumento.setText("Tipo de Documento");
        mnuTipoDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTipoDocumentoActionPerformed(evt);
            }
        });
        mnuAdministracion.add(mnuTipoDocumento);

        mnuFormaPago.setText("Forma de Pago");
        mnuFormaPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFormaPagoActionPerformed(evt);
            }
        });
        mnuAdministracion.add(mnuFormaPago);

        mnuRegistrarAsistencia.setText("Registrar Asistencia");
        mnuRegistrarAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRegistrarAsistenciaActionPerformed(evt);
            }
        });
        mnuAdministracion.add(mnuRegistrarAsistencia);

        mnuHorarios.setText("Horarios");
        mnuHorarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuHorariosActionPerformed(evt);
            }
        });
        mnuAdministracion.add(mnuHorarios);

        mnuConsultarAsistencia.setText("Consultar Asistencia");
        mnuConsultarAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuConsultarAsistenciaActionPerformed(evt);
            }
        });
        mnuAdministracion.add(mnuConsultarAsistencia);

        mnuAsignarHorario.setText("Asignar Horario");
        mnuAsignarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAsignarHorarioActionPerformed(evt);
            }
        });
        mnuAdministracion.add(mnuAsignarHorario);

        jMenuBar1.add(mnuAdministracion);

        mnuCompras.setText("Compras");

        mnuMaterial.setText("Material");
        mnuMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMaterialActionPerformed(evt);
            }
        });
        mnuCompras.add(mnuMaterial);

        mnuProveedor.setText("Proveedor");
        mnuProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuProveedorActionPerformed(evt);
            }
        });
        mnuCompras.add(mnuProveedor);

        mnuOrdenCompra.setText("Orden de Compra");
        mnuOrdenCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuOrdenCompraActionPerformed(evt);
            }
        });
        mnuCompras.add(mnuOrdenCompra);

        jMenuBar1.add(mnuCompras);

        mnuProduccion.setText("Producción");

        mnuTipoProducto.setText("Tipo de Producto");
        mnuTipoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTipoProductoActionPerformed(evt);
            }
        });
        mnuProduccion.add(mnuTipoProducto);

        mnuProducto.setText("Producto");
        mnuProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuProductoActionPerformed(evt);
            }
        });
        mnuProduccion.add(mnuProducto);

        mnuMaquina.setText("Máquina");
        mnuMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMaquinaActionPerformed(evt);
            }
        });
        mnuProduccion.add(mnuMaquina);

        mnuEstructuraProducto.setText("Estructura de Producto");
        mnuEstructuraProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEstructuraProductoActionPerformed(evt);
            }
        });
        mnuProduccion.add(mnuEstructuraProducto);

        mnuPlanificacionViejo.setText("Planificación Viejo");
        mnuPlanificacionViejo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPlanificacionViejoActionPerformed(evt);
            }
        });
        mnuProduccion.add(mnuPlanificacionViejo);

        mnuOrdenTrabajo.setText("Orden de Trabajo");
        mnuOrdenTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuOrdenTrabajoActionPerformed(evt);
            }
        });
        mnuProduccion.add(mnuOrdenTrabajo);

        mnuAvanceProduccion.setText("Avance de Producción");
        mnuAvanceProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAvanceProduccionActionPerformed(evt);
            }
        });
        mnuProduccion.add(mnuAvanceProduccion);

        mnuProblemaMaquina.setText("Problemas Maquinas");
        mnuProblemaMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuProblemaMaquinaActionPerformed(evt);
            }
        });
        mnuProduccion.add(mnuProblemaMaquina);

        mnuConsultarProblemas.setText("Consultar Problemas");
        mnuConsultarProblemas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuConsultarProblemasActionPerformed(evt);
            }
        });
        mnuProduccion.add(mnuConsultarProblemas);

        mnuTipoMaquina.setText("Tipo de Maquina");
        mnuTipoMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTipoMaquinaActionPerformed(evt);
            }
        });
        mnuProduccion.add(mnuTipoMaquina);

        mnuTipoHerramienta.setText("Tipo de Herramienta");
        mnuTipoHerramienta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTipoHerramientaActionPerformed(evt);
            }
        });
        mnuProduccion.add(mnuTipoHerramienta);

        mnuEtapaProduccion.setText("Etapa de Producción");
        mnuEtapaProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEtapaProduccionActionPerformed(evt);
            }
        });
        mnuProduccion.add(mnuEtapaProduccion);

        mnuHerramienta.setText("Herramienta");
        mnuHerramienta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuHerramientaActionPerformed(evt);
            }
        });
        mnuProduccion.add(mnuHerramienta);

        mnuPlanificacionNuevo.setText("Planificación Nuevo");
        mnuPlanificacionNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPlanificacionNuevoActionPerformed(evt);
            }
        });
        mnuProduccion.add(mnuPlanificacionNuevo);

        jMenuBar1.add(mnuProduccion);

        mnuVentas.setText("Ventas");

        mnuCliente.setText("Cliente");
        mnuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuClienteActionPerformed(evt);
            }
        });
        mnuVentas.add(mnuCliente);

        mnuPedido.setText("Pedido");
        mnuPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPedidoActionPerformed(evt);
            }
        });
        mnuVentas.add(mnuPedido);

        mnuFactura.setText("Factura");
        mnuFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFacturaActionPerformed(evt);
            }
        });
        mnuVentas.add(mnuFactura);

        jMenuBar1.add(mnuVentas);

        mnuDeposito.setText("Depósito");

        mnuReajustarStock.setText("Reajustar Stock");
        mnuReajustarStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuReajustarStockActionPerformed(evt);
            }
        });
        mnuDeposito.add(mnuReajustarStock);

        mnuRecepcionMateriales.setText("Recepción de Materiales");
        mnuRecepcionMateriales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRecepcionMaterialesActionPerformed(evt);
            }
        });
        mnuDeposito.add(mnuRecepcionMateriales);

        mnuEntregarPedido.setText("Entregar Pedido");
        mnuEntregarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEntregarPedidoActionPerformed(evt);
            }
        });
        mnuDeposito.add(mnuEntregarPedido);

        mnuEntregaMaterialesProduccion.setText("Entrega Materiales Producción");
        mnuEntregaMaterialesProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEntregaMaterialesProduccionActionPerformed(evt);
            }
        });
        mnuDeposito.add(mnuEntregaMaterialesProduccion);

        mnuAlmacenamientoProductoTerminado.setText("Almacenamiento Producto Terminado");
        mnuAlmacenamientoProductoTerminado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAlmacenamientoProductoTerminadoActionPerformed(evt);
            }
        });
        mnuDeposito.add(mnuAlmacenamientoProductoTerminado);

        jMenuBar1.add(mnuDeposito);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuTipoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTipoProductoActionPerformed
        // TODO add your handling code here:
        PantallaABMTipoProducto tipo = new PantallaABMTipoProducto(this, true);
        tipo.setVisible(true);
    }//GEN-LAST:event_mnuTipoProductoActionPerformed

    private void mnuCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCargoActionPerformed
        // TODO add your handling code here:
        PantallaABMCargo cargo = new PantallaABMCargo(this, true);
        cargo.setVisible(true);
    }//GEN-LAST:event_mnuCargoActionPerformed

    private void mnuEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEmpleadoActionPerformed
        // TODO add your handling code here:
        PantallaConsultarEmpleado empleado = new PantallaConsultarEmpleado(this, true);
        empleado.setVisible(true);
    }//GEN-LAST:event_mnuEmpleadoActionPerformed

    private void mnuTipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTipoDocumentoActionPerformed
        // TODO add your handling code here:
        PantallaABMTipoDocumento documento = new PantallaABMTipoDocumento(this, true);
        documento.setVisible(true);
    }//GEN-LAST:event_mnuTipoDocumentoActionPerformed

    private void mnuFormaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFormaPagoActionPerformed
        // TODO add your handling code here:
        new PantallaFormaDePago(this, true).setVisible(true);
    }//GEN-LAST:event_mnuFormaPagoActionPerformed

    private void mnuRegistrarAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRegistrarAsistenciaActionPerformed
        // TODO add your handling code here:
        PantallaAsistenciaEmpleadoRegistrar.iniciarRegistroAsistencia();
    }//GEN-LAST:event_mnuRegistrarAsistenciaActionPerformed

    private void mnuHorariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuHorariosActionPerformed
        // TODO add your handling code here:
        new PantallaHorarioAdministar(new GestorHorario()).setVisible(true);
    }//GEN-LAST:event_mnuHorariosActionPerformed

    private void mnuConsultarAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuConsultarAsistenciaActionPerformed
        // TODO add your handling code here:
        new PantallaAsistenciaEmpleadoConsultar(this,true).setVisible(true);
    }//GEN-LAST:event_mnuConsultarAsistenciaActionPerformed

    private void mnuAsignarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAsignarHorarioActionPerformed
        // TODO add your handling code here:
        new PantallaHorarioAsignarEmpleado(this,true).setVisible(true);
    }//GEN-LAST:event_mnuAsignarHorarioActionPerformed

    private void mnuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuClienteActionPerformed
        // TODO add your handling code here:
        PantallaClienteConsultar.iniciarAdministrarCliente(this);
    }//GEN-LAST:event_mnuClienteActionPerformed

    private void mnuPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPedidoActionPerformed
        // TODO add your handling code here:
        PantallaPedidoConsultar.iniciarAdministracionPedidos(this);
    }//GEN-LAST:event_mnuPedidoActionPerformed

    private void mnuFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFacturaActionPerformed
        // TODO add your handling code here:
        new PantallaFacturaConsultar(this,true).setVisible(true);
    }//GEN-LAST:event_mnuFacturaActionPerformed

    private void mnuMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMaterialActionPerformed
        // TODO add your handling code here:
        PantallaMaterialConsultar pantalla = new PantallaMaterialConsultar(this, true);
        pantalla.setVisible(true);
    }//GEN-LAST:event_mnuMaterialActionPerformed

    private void mnuProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuProveedorActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                PantallaProveedorConsultar dialog = new PantallaProveedorConsultar(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }//GEN-LAST:event_mnuProveedorActionPerformed

    private void mnuOrdenCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuOrdenCompraActionPerformed
        // TODO add your handling code here:
        new PantallaOrdenCompraConsultar(new javax.swing.JFrame(), true).setVisible(true);
    }//GEN-LAST:event_mnuOrdenCompraActionPerformed

    private void mnuProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuProductoActionPerformed
        // TODO add your handling code here:
        PantallaConsultarProducto producto = new PantallaConsultarProducto(this, true);
        producto.setVisible(true);
    }//GEN-LAST:event_mnuProductoActionPerformed

    private void mnuMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMaquinaActionPerformed
        // TODO add your handling code here:
        PantallaMaquinaConsultar pantalla = new PantallaMaquinaConsultar(this, true);
        pantalla.setVisible(true);
    }//GEN-LAST:event_mnuMaquinaActionPerformed

    private void mnuEstructuraProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEstructuraProductoActionPerformed
        // TODO add your handling code here:
        new GestorEstructura().iniciarCU();
    }//GEN-LAST:event_mnuEstructuraProductoActionPerformed

    private void mnuPlanificacionViejoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPlanificacionViejoActionPerformed
        // TODO add your handling code here:
        PantallaPlanConsultar pantalla = new PantallaPlanConsultar(this, true);
        pantalla.setVisible(true);
    }//GEN-LAST:event_mnuPlanificacionViejoActionPerformed

    private void mnuOrdenTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuOrdenTrabajoActionPerformed
        // TODO add your handling code here:
        new GestorOrdenTrabajo();
    }//GEN-LAST:event_mnuOrdenTrabajoActionPerformed

    private void mnuAvanceProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAvanceProduccionActionPerformed
        // TODO add your handling code here:
        new GestorAvanceProduccion().iniciarCU(null);
    }//GEN-LAST:event_mnuAvanceProduccionActionPerformed

    private void mnuProblemaMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuProblemaMaquinaActionPerformed
        // TODO add your handling code here:
        new ProblemaMaquinaHerramientaParticular(this, true).setVisible(true);
    }//GEN-LAST:event_mnuProblemaMaquinaActionPerformed

    private void mnuConsultarProblemasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuConsultarProblemasActionPerformed
        // TODO add your handling code here:
        new ConsultaProblemasMHP(this, true).setVisible(true);
    }//GEN-LAST:event_mnuConsultarProblemasActionPerformed

    private void mnuTipoMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTipoMaquinaActionPerformed
        // TODO add your handling code here:
        new PantallaTipoMaquinaABM(new GestorTipoMaquina()).setVisible(true);
    }//GEN-LAST:event_mnuTipoMaquinaActionPerformed

    private void mnuTipoHerramientaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTipoHerramientaActionPerformed
        // TODO add your handling code here:
        new PantallaTipoHerramientaABM(new GestorTipoHerramienta()).setVisible(true);
    }//GEN-LAST:event_mnuTipoHerramientaActionPerformed

    private void mnuEtapaProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEtapaProduccionActionPerformed
        // TODO add your handling code here:
        new PantallaABMEtapaProduccion(this, true).setVisible(true);
    }//GEN-LAST:event_mnuEtapaProduccionActionPerformed

    private void mnuHerramientaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuHerramientaActionPerformed
        // TODO add your handling code here:
        PantallaHerramientaConsultar pantalla = new PantallaHerramientaConsultar(this, true);
        pantalla.setVisible(true);
    }//GEN-LAST:event_mnuHerramientaActionPerformed

    private void mnuPlanificacionNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPlanificacionNuevoActionPerformed
        // TODO add your handling code here:
        new PantallaPlanProduccionPrincipal(this,true).setVisible(true);
    }//GEN-LAST:event_mnuPlanificacionNuevoActionPerformed

    private void mnuReajustarStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuReajustarStockActionPerformed
        // TODO add your handling code here:
        new ReajustarStock(this, true).setVisible(true);
    }//GEN-LAST:event_mnuReajustarStockActionPerformed

    private void mnuRecepcionMaterialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRecepcionMaterialesActionPerformed
        // TODO add your handling code here:
        new GestorRecepcionMaterial();
    }//GEN-LAST:event_mnuRecepcionMaterialesActionPerformed

    private void mnuEntregarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEntregarPedidoActionPerformed
        // TODO add your handling code here:
        new PantallaRegistrarEntregaPedido(this, true).setVisible(true);
    }//GEN-LAST:event_mnuEntregarPedidoActionPerformed

    private void mnuEntregaMaterialesProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEntregaMaterialesProduccionActionPerformed
        // TODO add your handling code here:
        new PantallaRegistrarEntregaMaterialesProduccion(this, true).setVisible(true);
    }//GEN-LAST:event_mnuEntregaMaterialesProduccionActionPerformed

    private void mnuAlmacenamientoProductoTerminadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAlmacenamientoProductoTerminadoActionPerformed
        // TODO add your handling code here:
        new PantallaRegistrarAlmacenamientoProdTerminado(this, true).setVisible(true);
    }//GEN-LAST:event_mnuAlmacenamientoProductoTerminadoActionPerformed

    private void mnuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuExitActionPerformed
        // TODO add your handling code here:
        pnlSession.setVisible(true);
        ocultarMenu();
    }//GEN-LAST:event_mnuExitActionPerformed

    private void btnIniciarSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSessionActionPerformed
        // TODO add your handling code here:
        Usuario uss;
        
        uss = UsuarioBD.getUsuario(txtUsuario.getText(), txtPassword.getText());

        ocultarMenu();
        
        if(uss == null)
        {
            lblMensaje.setText("El Usuario y/o Contraseña son Incorrectos");
            lblMensaje.setVisible(true);
        }
        else
        {
         
            if(UsuarioBD.getEmpleado(uss) == null)
            {
                Mensajes.mensajeErrorGenerico("El Usuario no esta Asociado a un Empleado");
                return;
            }
            
            pnlSession.setVisible(false);
            txtPassword.setText("");
            txtUsuario.setText("");
            
            Empleado emp = UsuarioBD.getEmpleado(uss);
            String strCargo = "";
            
            if(emp.getCargos().isEmpty())
            {
                strCargo = "Sin Cargo";
            }
            else
            {
                strCargo = ((Cargo) emp.getCargos().toArray()[0]).getNombre() ;
            }
            
            
            lblMensajeInicio.setText("Bienvenido: " + emp.getNumeroDocumento() + " - " + emp.getApellidoNombre() + " - " + strCargo);

            for(RolXUsuario rolxuss : uss.getTRolXUsuario()){
                Rol rol = rolxuss.getTRol();
                for(RolXMenu rolxmenu : rol.getTRolXMenu())
                {
                    
                    switch(rolxmenu.getTMenu().getIdMenu())
                    {
                        case 1:
                            mnuAdministracion.setVisible(true);
                            break;
                       case 2:
                            mnuCompras.setVisible(true);
                            break;
                       case 3:
                            mnuProduccion.setVisible(true);
                            break;
                       case 4:
                            mnuVentas.setVisible(true);
                            break;
                       case 5:
                            mnuDeposito.setVisible(true);
                            break;
                          
                    }
                }
            }
            

        }
   
    }//GEN-LAST:event_btnIniciarSessionActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        if (Mensajes.mensajeConfirmacionGenerico("¿Realmente desea salir?")) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void mnuIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuIniciarSesionActionPerformed
        // TODO add your handling code here:
        ocultarMenu();
    }//GEN-LAST:event_mnuIniciarSesionActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:

        pnlSession.setLocation((this.getWidth() - pnlSession.getWidth())/2, (this.getHeight() - pnlSession.getHeight())/2);
                
    }//GEN-LAST:event_formComponentResized

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                String[] li2 = {"Licensee=Jose Rolando Mamani", "LicenseRegistrationNumber=NCJM100903", "Product=SyntheticaAddons", "LicenseType=Non Commercial", "ExpireDate=--.--.----", "MaxVersion=1.999.999"};
UIManager.put("SyntheticaAddons.license.info", li2);
UIManager.put("SyntheticaAddons.license.key", "706FC55F-821A72A1-F46D7D49-1076473F-C9336002");
                new Principal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarSession;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblMensajeInicio;
    private javax.swing.JMenu mnuAdministracion;
    private javax.swing.JMenuItem mnuAlmacenamientoProductoTerminado;
    private javax.swing.JMenuItem mnuAsignarHorario;
    private javax.swing.JMenuItem mnuAvanceProduccion;
    private javax.swing.JMenuItem mnuCargo;
    private javax.swing.JMenuItem mnuCliente;
    private javax.swing.JMenu mnuCompras;
    private javax.swing.JMenuItem mnuConsultarAsistencia;
    private javax.swing.JMenuItem mnuConsultarProblemas;
    private javax.swing.JMenu mnuDeposito;
    private javax.swing.JMenuItem mnuEmpleado;
    private javax.swing.JMenuItem mnuEntregaMaterialesProduccion;
    private javax.swing.JMenuItem mnuEntregarPedido;
    private javax.swing.JMenuItem mnuEstructuraProducto;
    private javax.swing.JMenuItem mnuEtapaProduccion;
    private javax.swing.JMenuItem mnuExit;
    private javax.swing.JMenuItem mnuFactura;
    private javax.swing.JMenuItem mnuFormaPago;
    private javax.swing.JMenuItem mnuHerramienta;
    private javax.swing.JMenuItem mnuHorarios;
    private javax.swing.JMenuItem mnuIniciarSesion;
    private javax.swing.JMenuItem mnuMaquina;
    private javax.swing.JMenuItem mnuMaterial;
    private javax.swing.JMenuItem mnuOrdenCompra;
    private javax.swing.JMenuItem mnuOrdenTrabajo;
    private javax.swing.JMenuItem mnuPedido;
    private javax.swing.JMenuItem mnuPlanificacionNuevo;
    private javax.swing.JMenuItem mnuPlanificacionViejo;
    private javax.swing.JMenuItem mnuProblemaMaquina;
    private javax.swing.JMenu mnuProduccion;
    private javax.swing.JMenuItem mnuProducto;
    private javax.swing.JMenuItem mnuProveedor;
    private javax.swing.JMenuItem mnuReajustarStock;
    private javax.swing.JMenuItem mnuRecepcionMateriales;
    private javax.swing.JMenuItem mnuRegistrarAsistencia;
    private javax.swing.JMenu mnuSalir;
    private javax.swing.JMenuItem mnuTipoDocumento;
    private javax.swing.JMenuItem mnuTipoHerramienta;
    private javax.swing.JMenuItem mnuTipoMaquina;
    private javax.swing.JMenuItem mnuTipoProducto;
    private javax.swing.JMenu mnuVentas;
    private javax.swing.JPanel pnlSession;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

    private void ocultarMenu() {
        mnuAdministracion.setVisible(false);
        mnuCompras.setVisible(false);
        mnuProduccion.setVisible(false);
        mnuVentas.setVisible(false);
        mnuDeposito.setVisible(false);
        lblMensaje.setVisible(false);
        lblMensajeInicio.setText("");
    }
}
