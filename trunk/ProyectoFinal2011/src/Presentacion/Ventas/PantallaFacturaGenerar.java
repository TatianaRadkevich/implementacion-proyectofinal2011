/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaFacturaGenerar.java
 *
 * Created on 14-jun-2012, 21:07:15
 */
package Presentacion.Ventas;

import BaseDeDatos.Administracion.EmpleadoBD;
import BaseDeDatos.Ventas.EstadoPedidoBD;
import BaseDeDatos.Ventas.PedidoBD;
import Negocio.Administracion.DetalleFactura;
import Negocio.Administracion.Factura;
import Negocio.Exceptiones.NegocioException;
import Negocio.Ventas.DetallePedido;
import Negocio.Ventas.Pedido;
import Presentacion.Mensajes;
import Presentacion.TablaManager;
import Presentacion.Utilidades;
import Presentacion.ValidarTexbox;
import java.awt.Window;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Rodrigo
 */
public class PantallaFacturaGenerar extends javax.swing.JDialog {

    public static void iniciarGeneracionFactura(Window parent) throws NegocioException {

//        if(PedidoBD.getPedidos(EstadoPedidoBD.getEstadoRetirado()).isEmpty())
//            throw new NegocioException("No hay pedidos para facturar");
        PantallaFacturaGenerar interfaz = new PantallaFacturaGenerar(parent);
        interfaz.setTitle("Generar Factura");
        interfaz.pnlAnular.setVisible(false);
        interfaz.limpiarCampos();
        interfaz.setVisible(true);
    }

    public static void iniciarVerFactura(Window parent, Factura factura) {
        PantallaFacturaGenerar interfaz = new PantallaFacturaGenerar(parent);
        interfaz.setTitle("Ver Factura");
        interfaz.pnlPedidos.setVisible(false);
        interfaz.btnAceptar.setVisible(false);
        interfaz.btnCancelar.setText("Salir");
        Utilidades.habilitarPanel(interfaz.pnlFactura, false);
        interfaz.limpiarCampos();
        interfaz.setFactura(factura);
        interfaz.pnlAnular.setVisible(factura.getFechaGeneracion() != null);
        interfaz.pack();
        interfaz.setVisible(true);
    }

    static void iniciarAnularFactura(Window parent, Factura factura) {
        PantallaFacturaGenerar interfaz = new PantallaFacturaGenerar(parent);
        interfaz.setTitle("Anular Factura");
        interfaz.pnlPedidos.setVisible(false);
        Utilidades.habilitarPanel(interfaz.pnlFactura, false);
        interfaz.limpiarCampos();
        interfaz.setFactura(factura);
        interfaz.btnAceptar.setVisible(false);
        interfaz.btnAnular.setVisible(true);
        interfaz.pack();
        interfaz.setVisible(true);
    }
    /** Creates new form PantallaFacturaGenerar */
    private TablaManager<Pedido> tmPedido;
    private TablaManager<DetalleFactura> tmDetFac;
    private Factura facturaSelec;

    private PantallaFacturaGenerar(Window parent) {
        super(parent, ModalityType.APPLICATION_MODAL);
        initComponents();
        Utilidades.iniciarVentana(this);

        inicializarTablas();
        //tmPedido.setDatos(PedidoBD.getPedidos(EstadoPedidoBD.getEstadoRetirado()));
        tmPedido.setDatos(PedidoBD.getPedidos(EstadoPedidoBD.getEstadoAutorizadoPendiente()));
        ValidarTexbox.validarNumero(txtRecargoPorcentaje, 5, 2, 100f, 0f);
        ValidarTexbox.validarNumero(txtDescuentoPorcentaje, 5, 2, 100f, 0f);
        ValidarTexbox.validarLongitud(txtMotivoBaja, 100);
        btnAnular.setVisible(false);

    }

    private void inicializarTablas() {
        this.tmPedido = new TablaManager<Pedido>(this.tbPedidos) {

            @Override
            public Vector ObjetoFila(Pedido elemento) {
                Vector salida = new Vector();
                salida.add(elemento.getCliente().getRazonSocial());
                salida.add(elemento.getCliente().getCuit());
                salida.add(elemento.getIdPedido());
                salida.add(Utilidades.parseFecha(elemento.getFechaGeneracion()));
                salida.add(Utilidades.parseFecha(elemento.getFechaRealEntrega()));
                return salida;
            }

            @Override
            public Vector getCabecera() {
                Vector salida = new Vector();
                salida.add("Razón social");
                salida.add("CUIT");
                salida.add("Nro. Pedido");
                salida.add("Fecha Generacion");
                salida.add("Fecha Entrega");
                return salida;
            }
        };
        this.tmPedido.addSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent lse) {
                setPedido(tmPedido.getSeletedObject());
            }
        });
        this.tmDetFac = new TablaManager<DetalleFactura>(this.tbDetalleFactura) {

            @Override
            public Vector ObjetoFila(DetalleFactura elemento) {
                Vector salida = new Vector(6);
                salida.add(elemento.getDetallePedido().getProducto().getNombre());
                salida.add(elemento.getDetallePedido().getProducto().getDescripcion());
                salida.add("$ " + elemento.getPrecio());
                salida.add(elemento.getCantidad());
                salida.add("$ " + elemento.getCantidad() * elemento.getPrecio());
                return salida;
            }

            @Override
            public Vector getCabecera() {
                Vector cabcera = new Vector();
                cabcera.add("Producto");//col 3
                cabcera.add("Descripción");//col 4
                cabcera.add("Precio unit.");//col 1
                cabcera.add("Cantidad");//col 2
                cabcera.add("Sub total");
                return cabcera;
            }
        };
    }

    private void limpiarCampos() {
        txtCUIT.setText("");
        txtDescuentoMonto.setText("0");
        txtDescuentoPorcentaje.setText("0");
        txtDomicilio.setText("");
        txtFechaGeneracion.setText("");
        txtNroFactura.setText("");
        txtRazonSocial.setText("");
        txtRecargoMonto.setText("0");
        txtRecargoPorcentaje.setText("0");
        txtSubTotal.setText("0");
        txtTotal.setText("0");
        tmDetFac.limpiar();

    }

    private void setFactura(Factura f) {
        this.setPedido(f.getPedido());
        if (f.getFechaBaja() != null) {
            txtMotivoBaja.setText(f.getMotivoBaja());
            txtFechaBaja.setText(Utilidades.parseFecha(f.getFechaBaja()));
        }
        facturaSelec = f;
    }

    private void setPedido(Pedido p) {
        float subTotal = 0, recPor = 0, descPor = 0;

        limpiarCampos();        
        btnAceptar.setEnabled(false);
        if (p == null) {
            return;
        }

        btnAceptar.setEnabled(true);


        ArrayList<DetalleFactura> detFac = new ArrayList<DetalleFactura>();
        for (DetallePedido detPed : p.getDetallePedido()) {
            detFac.add(new DetalleFactura(detPed));
            subTotal += detPed.getSubTotal();
        }
        tmDetFac.setDatos(detFac);
        txtCUIT.setText(p.getCliente().getRazonSocial());
        txtDomicilio.setText(p.getCliente().getDomicilio().toString());
        txtFechaGeneracion.setText(Utilidades.parseFecha(p.getFechaGeneracion()));
        txtNroFactura.setText(Factura.getUltimoNro() + 1 + "");
        txtRazonSocial.setText(p.getCliente().getRazonSocial());
        txtSubTotal.setText("" + subTotal);
        actualizarTotal();
    }

    private void actualizarTotal() {
        float subTotal = getSubTotal();
        float recPor = 0, descPor = 0;
        float recMonto = 0, descMonto = 0;

        try {
            txtRecargoMonto.setText("0");
            recPor = new Float(txtRecargoPorcentaje.getText());
            recMonto = subTotal * recPor / 100;
            txtRecargoMonto.setText("" + recMonto);
        } catch (Exception e) {
        }
        try {
            txtDescuentoMonto.setText("0");
            descPor = new Float(txtDescuentoPorcentaje.getText());
            descMonto = subTotal * descPor / 100;
            txtDescuentoMonto.setText("" + descMonto);
        } catch (Exception e) {
        }

        txtTotal.setText("" + (subTotal - descMonto + recMonto));
    }

    private float getSubTotal() {
        try {
            return new Float(txtSubTotal.getText());
        } catch (Exception e) {
        }
        return 0;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPedidos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPedidos = new javax.swing.JTable();
        pnlFactura = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNroFactura = new javax.swing.JTextField();
        txtRazonSocial = new javax.swing.JTextField();
        txtDomicilio = new javax.swing.JTextField();
        txtFechaGeneracion = new javax.swing.JTextField();
        txtCUIT = new javax.swing.JTextField();
        pnlDetFactura = new javax.swing.JPanel();
        txtSubTotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDetalleFactura = new javax.swing.JTable();
        pnlResumen = new javax.swing.JPanel();
        pnlDescuento = new javax.swing.JPanel();
        txtDescuentoMonto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDescuentoPorcentaje = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        pnlRecargo = new javax.swing.JPanel();
        txtRecargoMonto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtRecargoPorcentaje = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        pnlTotal = new javax.swing.JPanel();
        txtTotal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlAnular = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMotivoBaja = new javax.swing.JTextArea();
        txtFechaBaja = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnAnular = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Generar Factura");

        pnlPedidos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pedidos entregados (Seleccione el pedido que de sea facturar)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Razón social", "CUIT", "Nro. Pedido", "F. Generacion", "F. Entrega"
            }
        ));
        jScrollPane1.setViewportView(tbPedidos);

        javax.swing.GroupLayout pnlPedidosLayout = new javax.swing.GroupLayout(pnlPedidos);
        pnlPedidos.setLayout(pnlPedidosLayout);
        pnlPedidosLayout.setHorizontalGroup(
            pnlPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
        );
        pnlPedidosLayout.setVerticalGroup(
            pnlPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );

        pnlFactura.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Factura (Vista previa)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Nro. Factura:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Fecha generacion:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Razón Social:");

        jLabel4.setText("CUIT:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Domicilio:");

        txtNroFactura.setEditable(false);
        txtNroFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNroFactura.setText("XXXXXXXX");

        txtRazonSocial.setEditable(false);
        txtRazonSocial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRazonSocial.setText("XXXXXXXXXXXXXXXXX");

        txtDomicilio.setEditable(false);

        txtFechaGeneracion.setEditable(false);
        txtFechaGeneracion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaGeneracion.setText("XX/XX/XXXX");

        txtCUIT.setEditable(false);
        txtCUIT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCUIT.setText("XX-XXXXXXXX-X");

        pnlDetFactura.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle Fatura", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        txtSubTotal.setEditable(false);
        txtSubTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSubTotal.setText("XXXXX");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Sub-Total:  $");

        tbDetalleFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbDetalleFactura);

        javax.swing.GroupLayout pnlDetFacturaLayout = new javax.swing.GroupLayout(pnlDetFactura);
        pnlDetFactura.setLayout(pnlDetFacturaLayout);
        pnlDetFacturaLayout.setHorizontalGroup(
            pnlDetFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetFacturaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlDetFacturaLayout.setVerticalGroup(
            pnlDetFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetFacturaLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)))
        );

        pnlDescuento.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descuento (-)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        txtDescuentoMonto.setEditable(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("%");

        txtDescuentoPorcentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescuentoPorcentajeKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setText("  $");

        javax.swing.GroupLayout pnlDescuentoLayout = new javax.swing.GroupLayout(pnlDescuento);
        pnlDescuento.setLayout(pnlDescuentoLayout);
        pnlDescuentoLayout.setHorizontalGroup(
            pnlDescuentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDescuentoLayout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescuentoPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescuentoMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlDescuentoLayout.setVerticalGroup(
            pnlDescuentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDescuentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtDescuentoPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel7)
                .addComponent(txtDescuentoMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel11))
        );

        pnlRecargo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Recargo (+)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        txtRecargoMonto.setEditable(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setText("%");

        txtRecargoPorcentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRecargoPorcentajeKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel12.setText("  $");

        javax.swing.GroupLayout pnlRecargoLayout = new javax.swing.GroupLayout(pnlRecargo);
        pnlRecargo.setLayout(pnlRecargoLayout);
        pnlRecargoLayout.setHorizontalGroup(
            pnlRecargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRecargoLayout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRecargoPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRecargoMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlRecargoLayout.setVerticalGroup(
            pnlRecargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRecargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtRecargoPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel8)
                .addComponent(txtRecargoMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel12))
        );

        pnlTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        txtTotal.setEditable(false);
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.setText("XXXXX");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setText("$");

        javax.swing.GroupLayout pnlTotalLayout = new javax.swing.GroupLayout(pnlTotal);
        pnlTotal.setLayout(pnlTotalLayout);
        pnlTotalLayout.setHorizontalGroup(
            pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalLayout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
        );
        pnlTotalLayout.setVerticalGroup(
            pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel10))
        );

        javax.swing.GroupLayout pnlResumenLayout = new javax.swing.GroupLayout(pnlResumen);
        pnlResumen.setLayout(pnlResumenLayout);
        pnlResumenLayout.setHorizontalGroup(
            pnlResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResumenLayout.createSequentialGroup()
                .addComponent(pnlDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRecargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(pnlTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlResumenLayout.setVerticalGroup(
            pnlResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlRecargo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlFacturaLayout = new javax.swing.GroupLayout(pnlFactura);
        pnlFactura.setLayout(pnlFacturaLayout);
        pnlFacturaLayout.setHorizontalGroup(
            pnlFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFacturaLayout.createSequentialGroup()
                .addGroup(pnlFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFacturaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFacturaLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaGeneracion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pnlResumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlFacturaLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCUIT, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pnlDetFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFacturaLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDomicilio, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlFacturaLayout.setVerticalGroup(
            pnlFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFacturaLayout.createSequentialGroup()
                .addGroup(pnlFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaGeneracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtCUIT, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDetFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlResumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnAceptar.setText("Generar");
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

        pnlAnular.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Anulacion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        pnlAnular.setDoubleBuffered(true);

        txtMotivoBaja.setLineWrap(true);
        txtMotivoBaja.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txtMotivoBaja);

        txtFechaBaja.setEditable(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setText("Fecha:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel13.setText("Motivo:");

        javax.swing.GroupLayout pnlAnularLayout = new javax.swing.GroupLayout(pnlAnular);
        pnlAnular.setLayout(pnlAnularLayout);
        pnlAnularLayout.setHorizontalGroup(
            pnlAnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 588, Short.MAX_VALUE)
            .addGroup(pnlAnularLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(pnlAnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlAnularLayout.setVerticalGroup(
            pnlAnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
            .addGroup(pnlAnularLayout.createSequentialGroup()
                .addGroup(pnlAnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlAnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnAnular.setText("Anular");
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlAnular, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlPedidos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAnular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAceptar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAnular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar)
                    .addComponent(btnAnular))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
//        try {
            Factura f = new Factura();
            f.setPedido(tmPedido.getSeletedObject());
            f.setDetalleFactura(tmDetFac.getDatos());
            try {
                f.setEmpleado(EmpleadoBD.listarEmpleado().get(0));
            } catch (IndexOutOfBoundsException e) {
                Mensajes.mensajeErrorGenerico("No se ha cargado ningun empleado");
                return;
            }
            f.setDescuentoPorcentaje(new BigDecimal(txtDescuentoMonto.getText()));
            f.setRecargoPorcentaje(new BigDecimal(txtRecargoMonto.getText()));
            f.generar();
//        } catch (Exception e) {
//            Mensajes.mensajeErrorGenerico("Datos incorrectos");
//        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtRecargoPorcentajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRecargoPorcentajeKeyReleased
        // TODO add your handling code here:
        actualizarTotal();
    }//GEN-LAST:event_txtRecargoPorcentajeKeyReleased

    private void txtDescuentoPorcentajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoPorcentajeKeyReleased
        // TODO add your handling code here:
        actualizarTotal();
    }//GEN-LAST:event_txtDescuentoPorcentajeKeyReleased

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        // TODO add your handling code here:
        try {
            facturaSelec.anular(txtMotivoBaja.getText());
        } catch (Exception e) {
            Mensajes.mensajeErrorGenerico("Datos incorrectos");
        }
    }//GEN-LAST:event_btnAnularActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                iniciarGeneracionFactura(null);
            }
        });
    }
    // <editor-fold defaultstate="collapsed" desc="Variables del Editor">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnlAnular;
    private javax.swing.JPanel pnlDescuento;
    private javax.swing.JPanel pnlDetFactura;
    private javax.swing.JPanel pnlFactura;
    private javax.swing.JPanel pnlPedidos;
    private javax.swing.JPanel pnlRecargo;
    private javax.swing.JPanel pnlResumen;
    private javax.swing.JPanel pnlTotal;
    private javax.swing.JTable tbDetalleFactura;
    private javax.swing.JTable tbPedidos;
    private javax.swing.JTextField txtCUIT;
    private javax.swing.JTextField txtDescuentoMonto;
    private javax.swing.JTextField txtDescuentoPorcentaje;
    private javax.swing.JTextField txtDomicilio;
    private javax.swing.JTextField txtFechaBaja;
    private javax.swing.JTextField txtFechaGeneracion;
    private javax.swing.JTextArea txtMotivoBaja;
    private javax.swing.JTextField txtNroFactura;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtRecargoMonto;
    private javax.swing.JTextField txtRecargoPorcentaje;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
// </editor-fold>
}
