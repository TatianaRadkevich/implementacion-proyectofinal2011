/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaCobroPedido.java
 *
 * Created on 16-jun-2012, 2:07:30
 */
package Presentacion.Administracion;

import BaseDeDatos.Administracion.CobroBD;
import BaseDeDatos.Administracion.FacturaBD;
import BaseDeDatos.Ventas.EstadoPedidoBD;
import BaseDeDatos.Ventas.PedidoBD;
import Negocio.Administracion.GestorCobroPedido;
import Negocio.Administracion.Cheque;
import Negocio.Administracion.Cobro;
import Negocio.Administracion.EstadoFactura;
import Negocio.Administracion.Factura;
import Negocio.Administracion.FormaPago;
import Negocio.Exceptiones.NegocioException;
import Negocio.Ventas.Pedido;
import Presentacion.Mensajes;
import Presentacion.TablaManager;
import Presentacion.TomaDeDecisiones.ReporteRecibo.ReporteRecibo;
import Presentacion.Utilidades;
import Presentacion.ZLinkers.*;
import Presentacion.ZLinkers.ZLFormatedTextField.Formato;
import java.awt.Window;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Rodrigo
 */
public class PantallaCobroPedido extends javax.swing.JDialog {

    /** Creates new form PantallaCobroPedido */
    private GestorCobroPedido gestor;
    private ZLObject<Factura> linkFactura;
    private TablaManager<Cobro> tmCobros;
    private ZLObject<Cobro> linkCobro;
    private ZLObject<Cheque> linkCheque;

    public PantallaCobroPedido(Window parent, GestorCobroPedido gestor) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.gestor = gestor;
         if (gestor.getFactura().getEstadoFactura().equals(FacturaBD.getEstadoFactura(FacturaBD.Estado.Cobrada)) == true
                || gestor.getFactura().getEstadoFactura().equals(FacturaBD.getEstadoFactura(FacturaBD.Estado.Anulada)) == true) {
            throw new NegocioException("No se encuentra ninguna factura pendiente de cobro");
        }
        initComponents();
        cofigurarControles();
        Utilidades.comboCargar(cmbFormaPagoCobro, gestor.getFormaPagos());
        this.cmbFormaPagoCobro.setSelectedIndex(0);
        setFactura(gestor.getFactura());
        this.cmbFormaPagoCobro.setSelectedIndex(0);
    }

    private void cofigurarControles() {
        linkFactura = new ZLObject<Factura>(Factura.class);
        linkFactura.add("cuit", false, new ZLTextField(txtCUIT));
        linkFactura.add("razonSocial", false, new ZLTextField(txtRazonSocial));
        linkFactura.add("numero", false, new ZLTextField(txtNroFactura));
        linkFactura.add("Generacion", false, new ZLFormatedTextField(txtFechaGeneracion, Formato.Date));
        linkFactura.add("domicilio", false, new ZLTextField(txtDomicilio));
        linkFactura.add("DescuentoMonto", false, new ZLTextField(txtDescuentoMonto));
        linkFactura.add("DescuentoPorcentaje", false, new ZLTextField(txtDescuentoPorcentaje));
        linkFactura.add("neto", false, new ZLTextField(txtTotalNeto));
        linkFactura.add("bruto", false, new ZLTextField(txtTotalBruto));
        
        this.tmCobros = new TablaManager<Cobro>(tbCobros) {

            @Override
            public Vector ObjetoFila(Cobro elemento) {
                Vector salida = new Vector();
                salida.add(elemento.getId());
                salida.add(elemento.getFechaCobro());
                salida.add(elemento.getFormaPago().getNombre());
                salida.add(elemento.getImporte());
                return salida;

            }

            @Override
            public Vector getCabecera() {
                Vector salida = new Vector();
                salida.add("Nro. Recibo");
                salida.add("Fecha");
                salida.add("Forma de Pago");
                salida.add("Importe ($)");
                return salida;
            }
        };

        linkCobro = new ZLObject<Cobro>(Cobro.class);
        linkCobro.add("lastId", false, new ZLTextField(txtNroCobro));
        linkCobro.add("fecha", false, new ZLCalendar(dtcFechaGeneracion));
        linkCobro.add("importe", new ZLTextField(txtMontoCobro));
        linkCobro.add("observacion", new ZLTextField(txtDescripcionCobro));
        linkCobro.add("formaPago", new ZLComboBox(cmbFormaPagoCobro));
        
        linkCheque = new ZLObject<Cheque>(Cheque.class);
        linkCheque.add("cuit", new ZLTextField(txtChequeCUIT));
        linkCheque.add("razonSocial", new ZLTextField(txtChequeRazonSocial));
        linkCheque.add("NroSucursal", new ZLTextField(txtChequeNroSucursal));
        linkCheque.add("Banco", new ZLTextField(txtChequeBanco));
        linkCheque.add("Emision", new ZLCalendar(dtcChequeFechaEmision));
        linkCheque.add("Vencimiento", new ZLCalendar(dtcChequeFechaVencimiento));
    }

    private void setFactura(Factura f) {
        linkFactura.setObjeto(f);
        linkFactura.load();
        tmCobros.setDatos(f.getCobros());
        setCobroFactura(new Cobro(f));
        txtSaldoActual.setText(f.getTotalCobrado() + "");
        txtSaldoRestante.setText(f.getTotalAdeudado() + "");
    }

    private void setCobroFactura(Cobro c) {
        linkCobro.setObjeto(c);
        linkCobro.load();
        txtNroCobro.setText(linkCobro.getObjeto().getLastId()+ 1 + "");
        dtcFechaGeneracion.setDate(Utilidades.getFechaActual());
    }

    private void setCheque(Cheque c) {
        if(c==null)
        {
            txtChequeCUIT.setText("");
            txtChequeRazonSocial.setText("");
            txtChequeBanco.setText("");
            txtChequeNroSucursal.setText("");
            dtcChequeFechaEmision.setDate(null);
            dtcChequeFechaVencimiento.setDate(null);
            return;
        }
        linkCheque.setObjeto(c);
        linkCobro.load();
        txtChequeCUIT.setText(gestor.getFactura().getCUITCliente());
        txtChequeRazonSocial.setText(gestor.getFactura().getRazonSocialCliente());
        dtcFechaGeneracion.setDate(Utilidades.getFechaActual());
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
        pnlFactura = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNroFactura = new javax.swing.JTextField();
        txtRazonSocial = new javax.swing.JTextField();
        txtDomicilio = new javax.swing.JTextField();
        txtCUIT = new javax.swing.JTextField();
        pnlResumen = new javax.swing.JPanel();
        pnlDescuento = new javax.swing.JPanel();
        txtDescuentoMonto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDescuentoPorcentaje = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        pnlTotal = new javax.swing.JPanel();
        txtTotalNeto = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTotalBruto = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtFechaGeneracion = new javax.swing.JFormattedTextField();
        pnlCobrosRegistrados = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCobros = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        txtSaldoActual = new javax.swing.JTextField();
        txtSaldoRestante = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        pnlCobro = new javax.swing.JPanel();
        pnlCheque = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtChequeNroSucursal = new javax.swing.JTextField();
        txtChequeBanco = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        pnlClienteCheque = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtChequeCUIT = new javax.swing.JTextField();
        txtChequeRazonSocial = new javax.swing.JTextField();
        dtcChequeFechaVencimiento = new com.toedter.calendar.JDateChooser();
        dtcChequeFechaEmision = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcionCobro = new javax.swing.JTextArea();
        cmbFormaPagoCobro = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtMontoCobro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNroCobro = new javax.swing.JTextField();
        dtcFechaGeneracion = new com.toedter.calendar.JDateChooser();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cobro de Factura");

        pnlFactura.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Factura (Vista previa)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Nro. Factura:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Fecha generación:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Razón Social:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("CUIT:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Domicilio:");

        txtNroFactura.setEditable(false);
        txtNroFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtRazonSocial.setEditable(false);
        txtRazonSocial.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtDomicilio.setEditable(false);

        txtCUIT.setEditable(false);
        txtCUIT.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        pnlDescuento.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descuento (-)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        txtDescuentoMonto.setEditable(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("%");

        txtDescuentoPorcentaje.setEditable(false);
        txtDescuentoPorcentaje.setToolTipText("Precionar Enter para que las modificaciones tomen efecto");

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

        pnlTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        txtTotalNeto.setEditable(false);
        txtTotalNeto.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setText("Neto:  $ ");

        txtTotalBruto.setEditable(false);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel24.setText("Bruto:  $ ");

        javax.swing.GroupLayout pnlTotalLayout = new javax.swing.GroupLayout(pnlTotal);
        pnlTotal.setLayout(pnlTotalLayout);
        pnlTotalLayout.setHorizontalGroup(
            pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalLayout.createSequentialGroup()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlTotalLayout.setVerticalGroup(
            pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel24)
                .addComponent(txtTotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel10)
                .addComponent(txtTotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlResumenLayout = new javax.swing.GroupLayout(pnlResumen);
        pnlResumen.setLayout(pnlResumenLayout);
        pnlResumenLayout.setHorizontalGroup(
            pnlResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResumenLayout.createSequentialGroup()
                .addComponent(pnlDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 351, Short.MAX_VALUE)
                .addComponent(pnlTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlResumenLayout.setVerticalGroup(
            pnlResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        txtFechaGeneracion.setEditable(false);
        txtFechaGeneracion.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout pnlFacturaLayout = new javax.swing.GroupLayout(pnlFactura);
        pnlFactura.setLayout(pnlFacturaLayout);
        pnlFacturaLayout.setHorizontalGroup(
            pnlFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFacturaLayout.createSequentialGroup()
                .addGroup(pnlFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFacturaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFacturaLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 440, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaGeneracion, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pnlResumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlFacturaLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCUIT, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlFacturaLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDomicilio, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlResumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlCobrosRegistrados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cobros anteriores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbCobros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbCobros);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel22.setText("Saldo actual:");

        txtSaldoActual.setEditable(false);

        txtSaldoRestante.setEditable(false);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel23.setText("Saldo restante:");

        javax.swing.GroupLayout pnlCobrosRegistradosLayout = new javax.swing.GroupLayout(pnlCobrosRegistrados);
        pnlCobrosRegistrados.setLayout(pnlCobrosRegistradosLayout);
        pnlCobrosRegistradosLayout.setHorizontalGroup(
            pnlCobrosRegistradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCobrosRegistradosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlCobrosRegistradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlCobrosRegistradosLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSaldoActual, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCobrosRegistradosLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSaldoRestante, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        pnlCobrosRegistradosLayout.setVerticalGroup(
            pnlCobrosRegistradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCobrosRegistradosLayout.createSequentialGroup()
                .addGroup(pnlCobrosRegistradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCobrosRegistradosLayout.createSequentialGroup()
                        .addGroup(pnlCobrosRegistradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSaldoActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlCobrosRegistradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSaldoRestante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlCobro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo Cobro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        pnlCheque.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Cheque", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel16.setText("Fecha Emision:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel17.setText("   Fecha Vencimiento:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel18.setText("Nro Sucursal:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel19.setText("Banco:");

        pnlClienteCheque.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel20.setText("CUIT:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel21.setText("Razon social:");

        txtChequeCUIT.setEditable(false);

        txtChequeRazonSocial.setEditable(false);

        javax.swing.GroupLayout pnlClienteChequeLayout = new javax.swing.GroupLayout(pnlClienteCheque);
        pnlClienteCheque.setLayout(pnlClienteChequeLayout);
        pnlClienteChequeLayout.setHorizontalGroup(
            pnlClienteChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClienteChequeLayout.createSequentialGroup()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtChequeRazonSocial, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtChequeCUIT, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlClienteChequeLayout.setVerticalGroup(
            pnlClienteChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClienteChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel21)
                .addComponent(txtChequeRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtChequeCUIT, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel20))
        );

        javax.swing.GroupLayout pnlChequeLayout = new javax.swing.GroupLayout(pnlCheque);
        pnlCheque.setLayout(pnlChequeLayout);
        pnlChequeLayout.setHorizontalGroup(
            pnlChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlClienteCheque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlChequeLayout.createSequentialGroup()
                .addGroup(pnlChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlChequeLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtcChequeFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dtcChequeFechaVencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                    .addGroup(pnlChequeLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtChequeBanco, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtChequeNroSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlChequeLayout.setVerticalGroup(
            pnlChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChequeLayout.createSequentialGroup()
                .addComponent(pnlClienteCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(dtcChequeFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(dtcChequeFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChequeBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChequeNroSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtDescripcionCobro.setLineWrap(true);
        txtDescripcionCobro.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtDescripcionCobro);

        cmbFormaPagoCobro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Efectivo", "Cheque", " " }));
        cmbFormaPagoCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFormaPagoCobroActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel14.setText("  Monto:  $");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel15.setText("Descripción:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setText("Fecha:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel13.setText("Forma pago:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Nro. Cobro:");

        txtNroCobro.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(jLabel15)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cmbFormaPagoCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMontoCobro, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtNroCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtcFechaGeneracion, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtNroCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addComponent(dtcFechaGeneracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMontoCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(cmbFormaPagoCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlCobroLayout = new javax.swing.GroupLayout(pnlCobro);
        pnlCobro.setLayout(pnlCobroLayout);
        pnlCobroLayout.setHorizontalGroup(
            pnlCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCobroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlCobroLayout.setVerticalGroup(
            pnlCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlCobroLayout.createSequentialGroup()
                .addComponent(pnlCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCobrosRegistrados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCobro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCobrosRegistrados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbFormaPagoCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFormaPagoCobroActionPerformed
        // TODO add your handling code here:
        if (cmbFormaPagoCobro.getSelectedItem() != null && cmbFormaPagoCobro.getSelectedItem() instanceof FormaPago) {
            FormaPago fp = (FormaPago) cmbFormaPagoCobro.getSelectedItem();
            if (!fp.equals(FormaPago.getFormaPago(FormaPago.Tipo.Efectivo))) {
                Utilidades.habilitarPanel(pnlCheque, true);
                setCheque(new Cheque());
            } else {
                Utilidades.habilitarPanel(pnlCheque, false);
                setCheque(null);
            }
        }

    }//GEN-LAST:event_cmbFormaPagoCobroActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        try{

            gestor.getFactura().getPedido().setEstadoPedido(EstadoPedidoBD.getEstadoPagado());
            
            linkCobro.getObjeto().setImporte(Utilidades.parseBigDecimal(txtMontoCobro.getText()));
            linkCobro.getObjeto().setgetEmpleado(gestor.getFactura().getEmpleado());
            linkCobro.getObjeto().setFechaCobro(dtcFechaGeneracion.getDate());
            linkCobro.getObjeto().setObservaciones(txtDescripcionCobro.getText());
            linkCobro.save();
            if(linkCobro.getObjeto().getFormaPago().equals(FormaPago.getFormaPago(FormaPago.Tipo.Cheque)))
            {
                linkCheque.getObjeto().setCliente(gestor.getFactura().getPedido().getCliente());
                linkCheque.getObjeto().setFechaEmision(dtcChequeFechaEmision.getDate());
                linkCheque.getObjeto().setFechaVencimiento(dtcChequeFechaVencimiento.getDate());
                linkCheque.getObjeto().setMonto(Utilidades.parseBigDecimal(txtMontoCobro.getText()));
                linkCheque.getObjeto().setNombreBanco(txtChequeBanco.getText());
                linkCheque.getObjeto().setNroSucursal(Utilidades.parseInteger(txtChequeNroSucursal.getText()));
                linkCheque.save();
                linkCheque.getObjeto().grabar();
                linkCobro.getObjeto().setCheque(linkCheque.getObjeto());
            }
            gestor.getFactura().addCobro(linkCobro.getObjeto());

            if(gestor.getFactura().getTotalCobrado() < gestor.getFactura().getTotalNeto())
            {
                gestor.getFactura().getPedido().setEstadoPedido(EstadoPedidoBD.getEstadoPagoParcialo());
                gestor.getFactura().setTEFactura(FacturaBD.getEstadoFactura(FacturaBD.Estado.ParcialmenteCobrada));
            }
            else
            {
                gestor.getFactura().getPedido().setEstadoPedido(EstadoPedidoBD.getEstadoPagado());
                gestor.getFactura().setTEFactura(FacturaBD.getEstadoFactura(FacturaBD.Estado.Cobrada));
                
            }

            gestor.getFactura().guardar();
            Mensajes.mensajeInformacion("El Cobro ha sido guardado con exito");
            this.dispose();
            ReporteRecibo reporteRecibo = new ReporteRecibo();
            BigDecimal id = new BigDecimal(linkCobro.getObjeto().getId());
            reporteRecibo.addParameter("id_recibo", id);
            reporteRecibo.runReporte();
        }catch(NegocioException ne){Mensajes.mensajeErrorGenerico(ne.getMessage());}

    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
            }
        });
    }

    public static void iniciarCobroPedido(Window parent, Factura f) {
        PantallaCobroPedido i = new PantallaCobroPedido(parent, new GestorCobroPedido(f));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox cmbFormaPagoCobro;
    private com.toedter.calendar.JDateChooser dtcChequeFechaEmision;
    private com.toedter.calendar.JDateChooser dtcChequeFechaVencimiento;
    private com.toedter.calendar.JDateChooser dtcFechaGeneracion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlCheque;
    private javax.swing.JPanel pnlClienteCheque;
    private javax.swing.JPanel pnlCobro;
    private javax.swing.JPanel pnlCobrosRegistrados;
    private javax.swing.JPanel pnlDescuento;
    private javax.swing.JPanel pnlFactura;
    private javax.swing.JPanel pnlResumen;
    private javax.swing.JPanel pnlTotal;
    private javax.swing.JTable tbCobros;
    private javax.swing.JTextField txtCUIT;
    private javax.swing.JTextField txtChequeBanco;
    private javax.swing.JTextField txtChequeCUIT;
    private javax.swing.JTextField txtChequeNroSucursal;
    private javax.swing.JTextField txtChequeRazonSocial;
    private javax.swing.JTextArea txtDescripcionCobro;
    private javax.swing.JTextField txtDescuentoMonto;
    private javax.swing.JTextField txtDescuentoPorcentaje;
    private javax.swing.JTextField txtDomicilio;
    private javax.swing.JFormattedTextField txtFechaGeneracion;
    private javax.swing.JTextField txtMontoCobro;
    private javax.swing.JTextField txtNroCobro;
    private javax.swing.JTextField txtNroFactura;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtSaldoActual;
    private javax.swing.JTextField txtSaldoRestante;
    private javax.swing.JTextField txtTotalBruto;
    private javax.swing.JTextField txtTotalNeto;
    // End of variables declaration//GEN-END:variables
}
