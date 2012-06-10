/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaOrdenCompraABM.java
 *
 * Created on 19/09/2011, 20:00:25
 */
package Presentacion.Compras;

import BaseDeDatos.Compras.EstadoDetalleOrdenCompraBD;
import Negocio.Compras.DetalleOrdenCompra;
import Negocio.Compras.GestorOrdenCompra;
import Negocio.Compras.GestorOrdenCompraAlta;
import Negocio.Compras.Material;
import Negocio.Compras.OrdenCompra;
import Negocio.Compras.Proveedor;
import Negocio.Deposito.Faltante;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Mensajes;
import Presentacion.TablaManager;
import Presentacion.Utilidades;
import Presentacion.ValidarTexbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Rodrigo
 */
public class PantallaOrdenCompraABM extends javax.swing.JDialog {

    /** Creates new form PantallaOrdenCompraABM */
    private GestorOrdenCompra gestor;
    private TablaManager<DetalleOrdenCompra> tmOrdenCompra;
    private TablaManager<Material> tmStock;
    private TablaManager<Faltante> tmFaltante;
    private Proveedor proveedor;

    private PantallaOrdenCompraABM(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public PantallaOrdenCompraABM(GestorOrdenCompra gestor) {
        this(null, true);
        this.gestor = gestor;

        //Seteo de variables//-      
        txtCodigo.setText((gestor.getCodigo() + 1) + "");
        txtFecha.setText(Utilidades.parseFecha(Utilidades.getFechaActual()));
        habilitarCarga(true);
        IniciarTablas();
        cargarCombos();
        cargarValidaciones();
        btnAgregarFaltante.setVisible(false);
        btnAgregarTodosFaltantes.setVisible(false);
       // pnlFlatante.setVisible(false);
        tmStock.setDatos(gestor.listarMateriales());
    }

    private void IniciarTablas() {

        tmFaltante = new TablaManager<Faltante>(tbFaltantes) {

            @Override
            public Vector ObjetoFila(Faltante elemento) {
                Vector salida = new Vector();
                salida.add(Utilidades.parseFecha(elemento.getFecGeneracion()));
                salida.add(Utilidades.parseFecha(elemento.getFecNecesidad()));
                salida.add(elemento.getCantidad());
//                salida.add((elemento.getDetalleOrdenCompra() == null) ? "NO" : "SI");

                return salida;
            }

            @Override
            public Vector getCabecera() {
                Vector salida = new Vector();
                salida.add("Fecha generacion");
                salida.add("Fecha necesidad");
                salida.add("Cantidad");
//                salida.add("Satisfecho");
                return salida;
            }
        };

        tbStock.setDefaultRenderer(Object.class, new FormatoTabla());
        tmStock = new TablaManager<Material>(tbStock) {

            @Override
            public Vector ObjetoFila(Material elemento) {

                Vector salida = new Vector();
                salida.add(elemento.getNombre());
                salida.add("Cada unidad hace referencia a " + elemento.getLogitud() + " " + elemento.getUnidadMedida().getNombre() + " de " + elemento.getNombre());
                salida.add(elemento.getStockActual());
                salida.add(elemento.getStockReservado());
                salida.add(elemento.getStockDisponible());
                salida.add(elemento.getStockMinimo());
                salida.add(elemento.getCantidadFaltante());
                return salida;
            }

            @Override
            public Vector getCabecera() {
                Vector salida = new Vector();
                salida.add("Material");
                salida.add("Desc.");
                salida.add("Stock Actual");
                salida.add("Stock Reservado");
                salida.add("Stock Disponible");
                salida.add("Stock Minimo");
                salida.add("Stock Faltante");

                return salida;
            }
        };

        tmStock.addSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                tmFaltante.limpiar();
                for (Faltante f : tmStock.getSeletedObject().getFaltantes()) {
                    if (f.getFecNecesidad().compareTo(Utilidades.getFechaActual()) <= 0) {
                        tmFaltante.add(f);
                    }
                }
            }
        });

        ////////////////// Detalle orden compra ///////////////////////////

        tmOrdenCompra = new TablaManager<DetalleOrdenCompra>(tbDetalle) {

            @Override
            public Vector ObjetoFila(DetalleOrdenCompra elemento) {
                ///////////////////////////////
                Float precio = elemento.getMaterial().getPrecio((Proveedor) cmbProveedor.getSelectedItem());

                if (precio == null) {
                    precio = 0f;
                }
                ////////////////////////////////
                Vector fila = new Vector();
                fila.add((elemento.getMaterial() == null) ? "" : elemento.getMaterial().getNombre());
                fila.add(precio);
                fila.add(elemento.getCantidadPedida());
                fila.add(elemento.getCantidadPedida() * precio);

                return fila;
            }

            @Override
            public Vector getCabecera() {
                Vector cabecera = new Vector();
                cabecera.add("Material");
                cabecera.add("Precio unitario");
                cabecera.add("Cantidad");
                cabecera.add("Sub total");
                return cabecera;
            }
        };

        tmOrdenCompra.addSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (tmOrdenCompra.getSeletedObject() != null) {
                    cargarDetalle(tmOrdenCompra.getSeletedObject());
                } else {
                    limpiarCargaDetalle();
                }
            }
        });

        tmOrdenCompra.addModificationListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
                recalcularTotal();
            }
        });

    }

    public void setNro(int id)
    {
        txtCodigo.setText(""+id);
    }

    private void recalcularTotal() {

        float total = 0;
        for (int i = 0; i < tbDetalle.getRowCount(); i++) {
            total += Float.parseFloat(tbDetalle.getValueAt(i, 3).toString());//col 3 >> subtotal
        }
        txtTotal.setText(total + "");
    }

    private void cargarValidaciones() {
        ValidarTexbox.validarInt(txtUnidades);
        ValidarTexbox.validarLongitud(txtUnidades, 3);
    }

    private void cargarCombos() {
        cmbProveedor.setModel(new DefaultComboBoxModel(gestor.getProveedores().toArray()));
        cmbProveedor.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (tmOrdenCompra.getSize() > 0) {
                    if (Mensajes.mensajeConfirmacionGenerico("¡Si prosige se eliminaran todos los itams del detalle de la orden de compra!"
                            + "\n¿Realmente desea cambiar de proveedor?") == false) {
                        cmbProveedor.setSelectedItem(proveedor);
                        return;
                    }
                }
                tmOrdenCompra.limpiar();
                proveedor = (Proveedor) cmbProveedor.getSelectedItem();
                if (proveedor != null) {
                    cmbMaterial.setModel(new DefaultComboBoxModel(proveedor.getMateriales().toArray()));
                    cmbMaterial.setSelectedIndex(-1);
                }
            }
        });
        cmbProveedor.setSelectedIndex(-1);
        cmbMaterial.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (cmbMaterial.getSelectedIndex() == -1) {
                    lblUnidad.setText("");
                    txtCantidad.setText("");
                    txtUnidades.setEditable(false);
                    return;
                }
                txtUnidades.setEditable(true);
                Material m = (Material) cmbMaterial.getSelectedItem();
                lblUnidad.setText(m.getUnidadMedida().getNombre());
                calcularCantidades();
            }
        });

    }

    public void habilitarCarga(boolean valor) {

        Utilidades.habilitarPanel(pnlCompra, valor);
        Utilidades.habilitarPanel(pnlDetalleABM, !valor);
    }

    public void habilitarCargaDetalle(boolean valor) {

        Utilidades.habilitarPanel(pnlCompra, !valor);
        Utilidades.habilitarPanel(pnlDetalleABM, valor);
    }

    public void limpiarCargaDetalle() {
        txtUnidades.setText("");
        cmbMaterial.setSelectedIndex(-1);
        txtCantidad.setText("");
        lblUnidad.setText("");
    }

    private void updateTablaStock() {
        int selRow = tmStock.getSelectedRow();
        tmStock.updateTabla();
        tmStock.setSelectedRow(selRow);

        selRow = tmFaltante.getSelectedRow();
        tmFaltante.updateTabla();
        tmFaltante.setSelectedRow(selRow);
    }

    public void cargarDetalle(DetalleOrdenCompra doc) {
        txtUnidades.setText(Utilidades.parseString(doc.getCantidadPedida()));
        cmbMaterial.setSelectedItem(doc.getMaterial());
    }

    private void calcularCantidades() {
        try {
            int unid = Integer.parseInt(txtUnidades.getText());
            Material m = (Material) cmbMaterial.getSelectedItem();
            int cant = unid * m.getLogitud();
            txtCantidad.setText(cant + "");
        } catch (Exception e) {
        }
    }

    public void cargar(OrdenCompra oc) {
        txtCodigo.setText(oc.getId() + "");
        txtFecha.setText(Utilidades.parseFecha(oc.getFecGeneracion()));
        cmbProveedor.setSelectedItem(oc.getProveedor());
        tmOrdenCompra.setDatos(oc.getDetalle());

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCompra = new javax.swing.JPanel();
        txtFecha = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        pnlDetalle = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDetalle = new javax.swing.JTable();
        btnEliminarDetalle = new javax.swing.JButton();
        btnNuevoDetalle = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        pnlDetalleABM = new javax.swing.JPanel();
        cmbMaterial = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txtUnidades = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnDetalleAceptar = new javax.swing.JButton();
        btnDetalleCancelar = new javax.swing.JButton();
        lblUnidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbProveedor = new javax.swing.JComboBox();
        pnlStock = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbStock = new javax.swing.JTable();
        pnlFlatante = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbFaltantes = new javax.swing.JTable();
        btnAgregarFaltante = new javax.swing.JButton();
        btnAgregarTodosFaltantes = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlCompra.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Orden de compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        txtFecha.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Fecha generación:");

        txtCodigo.setEditable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Nro Orden:");

        pnlDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle orden de compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Material", "Precio unitario", "Cantidad", "Sub total"
            }
        ));
        jScrollPane2.setViewportView(tbDetalle);

        btnEliminarDetalle.setText("Eliminar");
        btnEliminarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarDetalleActionPerformed(evt);
            }
        });

        btnNuevoDetalle.setText("Nuevo");
        btnNuevoDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoDetalleActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Total: $");

        txtTotal.setEditable(false);

        pnlDetalleABM.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cmbMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMaterialActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Material:");

        txtUnidades.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUnidadesKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Unidades:");

        btnDetalleAceptar.setText("Aceptar");
        btnDetalleAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleAceptarActionPerformed(evt);
            }
        });

        btnDetalleCancelar.setText("Cancelar");
        btnDetalleCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleCancelarActionPerformed(evt);
            }
        });

        lblUnidad.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblUnidad.setText(" ");

        txtCantidad.setEditable(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("Cantidad");

        javax.swing.GroupLayout pnlDetalleABMLayout = new javax.swing.GroupLayout(pnlDetalleABM);
        pnlDetalleABM.setLayout(pnlDetalleABMLayout);
        pnlDetalleABMLayout.setHorizontalGroup(
            pnlDetalleABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleABMLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUnidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addComponent(btnDetalleAceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDetalleCancelar)
                .addContainerGap())
        );
        pnlDetalleABMLayout.setVerticalGroup(
            pnlDetalleABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleABMLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetalleABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(btnDetalleAceptar)
                    .addComponent(btnDetalleCancelar)
                    .addComponent(jLabel7)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUnidad))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlDetalleABM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDetalleLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 646, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnNuevoDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarDetalle))
                .addContainerGap())
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetalleLayout.createSequentialGroup()
                .addComponent(pnlDetalleABM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDetalleLayout.createSequentialGroup()
                        .addComponent(btnNuevoDetalle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarDetalle))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetalleLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Proveedores:");

        cmbProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProveedorActionPerformed(evt);
            }
        });

        pnlStock.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Stock", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Material", "Stock Actua", "Stock Reservado", "Stock Disponible", "Stock Minimo", "Stock Faltante"
            }
        ));
        jScrollPane1.setViewportView(tbStock);

        pnlFlatante.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Faltantes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbFaltantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha Generacion", "Fecha Necesidad", "Cantidad"
            }
        ));
        jScrollPane3.setViewportView(tbFaltantes);

        btnAgregarFaltante.setText("Agregar");
        btnAgregarFaltante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarFaltanteActionPerformed(evt);
            }
        });

        btnAgregarTodosFaltantes.setText("<html><center>Agregar<br>Todos</center> <html>");
        btnAgregarTodosFaltantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTodosFaltantesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFlatanteLayout = new javax.swing.GroupLayout(pnlFlatante);
        pnlFlatante.setLayout(pnlFlatanteLayout);
        pnlFlatanteLayout.setHorizontalGroup(
            pnlFlatanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFlatanteLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlFlatanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarTodosFaltantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarFaltante)))
        );
        pnlFlatanteLayout.setVerticalGroup(
            pnlFlatanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFlatanteLayout.createSequentialGroup()
                .addComponent(btnAgregarFaltante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAgregarTodosFaltantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlStockLayout = new javax.swing.GroupLayout(pnlStock);
        pnlStock.setLayout(pnlStockLayout);
        pnlStockLayout.setHorizontalGroup(
            pnlStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStockLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlFlatante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlStockLayout.setVerticalGroup(
            pnlStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFlatante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlCompraLayout = new javax.swing.GroupLayout(pnlCompra);
        pnlCompra.setLayout(pnlCompraLayout);
        pnlCompraLayout.setHorizontalGroup(
            pnlCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCompraLayout.createSequentialGroup()
                .addGroup(pnlCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCompraLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlCompraLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlCompraLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlCompraLayout.setVerticalGroup(
            pnlCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCompraLayout.createSequentialGroup()
                .addGroup(pnlCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pnlStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
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
                        .addComponent(btnAceptar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar))
                    .addComponent(pnlCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoDetalleActionPerformed
        // TODO add your handling code here:
        if (proveedor == null) {
            Mensajes.mensajeErrorGenerico("Antes debe seleccionar un proveedor");
            return;
        }
        if (proveedor.getMateriales().isEmpty()) {
            Mensajes.mensajeErrorGenerico("El proveedor  seleccionado no tiene asignado ningún material");
            return;
        }
        habilitarCargaDetalle(true);
    }//GEN-LAST:event_btnNuevoDetalleActionPerformed

    private void btnEliminarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarDetalleActionPerformed
        // TODO add your handling code here:
        if (tmOrdenCompra.getSeletedObject() != null) {
            tmOrdenCompra.removeSelectedRow().removeFaltantes();
            updateTablaStock();
        }
    }//GEN-LAST:event_btnEliminarDetalleActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        gestor.finalizarCU();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        OrdenCompra oc = gestor.getOrdenCompra();
        //oc.setId(Utilidades.parseInteger( txtCodigo.getText()));
        oc.setDetalle(tmOrdenCompra.getDatos());
        oc.setFecGeneracion(Utilidades.getFechaActual());
        oc.setProveedor((Proveedor) cmbProveedor.getSelectedItem());

        try {
            gestor.ejecutarCU(oc);
            gestor.finalizarCU();
        } catch (ExceptionGestor ex) {
            Mensajes.mensajeErrorGenerico(ex.getMessage());
        }

    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnDetalleCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleCancelarActionPerformed
        // TODO add your handling code here:
        limpiarCargaDetalle();
        habilitarCargaDetalle(false);

    }//GEN-LAST:event_btnDetalleCancelarActionPerformed

    private void btnDetalleAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleAceptarActionPerformed
        // TODO add your handling code here:

        if (cmbMaterial.getSelectedIndex() == -1) {
            Mensajes.mensajeErrorGenerico("Debe seleccionar un material");
            return;
        }

        try {
            new Short(txtUnidades.getText());
        } catch (Exception e) {
            Mensajes.mensajeErrorGenerico("La cantidad ingresada es incorrecta");
            return;
        }

        DetalleOrdenCompra doc = new DetalleOrdenCompra();
        doc.setCantidadPedida(Utilidades.parseShort(txtUnidades.getText()));        
        doc.setMaterial((Material) cmbMaterial.getSelectedItem());
        doc.setPrecioUnitario(doc.getMaterial().getPrecio((Proveedor) cmbProveedor.getSelectedItem()));
        doc.setEstado(EstadoDetalleOrdenCompraBD.getEstadoPendiente());
        tmOrdenCompra.add(doc);

        int auxCant = doc.getCantidadPedida();
        for (Faltante f : doc.getMaterial().getFaltantes()) {
            if (f.getCantidad() <= auxCant) {
                f.setDetalleOrdenCompra(doc);
                auxCant = auxCant - f.getCantidad();
            }
        }

        limpiarCargaDetalle();
        habilitarCargaDetalle(false);
    }//GEN-LAST:event_btnDetalleAceptarActionPerformed

    private void btnAgregarFaltanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarFaltanteActionPerformed
        // TODO add your handling code here:
//        if (tmStock.getSeletedObject() != null) {
//            Material mat = tmStock.getSeletedObject();
//            tmOrdenCompra.add(new DetalleOrdenCompra(mat, mat.getCantidadFaltante().shortValue()));
//        }

        if (tmFaltante.getSeletedObject() != null) {
            Faltante f = tmFaltante.getSeletedObject();
            f.setDetalleOrdenCompra(new DetalleOrdenCompra(f.getMaterial(), f.getCantidad().shortValue()));
            tmOrdenCompra.add(f.getDetalleOrdenCompra());
            updateTablaStock();
        }

    }//GEN-LAST:event_btnAgregarFaltanteActionPerformed

    private void btnAgregarTodosFaltantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTodosFaltantesActionPerformed
        // TODO add your handling code here:
        if (tmStock.getSeletedObject() != null) {
            for (Faltante f : tmStock.getSeletedObject().getFaltantes()) {
                f.setDetalleOrdenCompra(new DetalleOrdenCompra(f.getMaterial(), f.getCantidad().shortValue()));
                tmOrdenCompra.add(f.getDetalleOrdenCompra());
            }
            updateTablaStock();
        }

    }//GEN-LAST:event_btnAgregarTodosFaltantesActionPerformed

    private void txtUnidadesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnidadesKeyReleased
        // TODO add your handling code here:
        calcularCantidades();
    }//GEN-LAST:event_txtUnidadesKeyReleased

    private void cmbProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbProveedorActionPerformed

    private void cmbMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMaterialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMaterialActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new GestorOrdenCompraAlta().iniciarCU();
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAgregarFaltante;
    private javax.swing.JButton btnAgregarTodosFaltantes;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDetalleAceptar;
    private javax.swing.JButton btnDetalleCancelar;
    private javax.swing.JButton btnEliminarDetalle;
    private javax.swing.JButton btnNuevoDetalle;
    private javax.swing.JComboBox cmbMaterial;
    private javax.swing.JComboBox cmbProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblUnidad;
    private javax.swing.JPanel pnlCompra;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlDetalleABM;
    private javax.swing.JPanel pnlFlatante;
    private javax.swing.JPanel pnlStock;
    private javax.swing.JTable tbDetalle;
    private javax.swing.JTable tbFaltantes;
    private javax.swing.JTable tbStock;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtUnidades;
    // End of variables declaration//GEN-END:variables
}
