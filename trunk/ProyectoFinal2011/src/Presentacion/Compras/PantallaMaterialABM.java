/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaMaterialABM.java
 *
 * Created on 20/08/2011, 15:28:13
 */
package Presentacion.Compras;

import BaseDeDatos.Compras.MaterialBD;
import Negocio.Compras.GestorMaterial;
import Negocio.Compras.GestorMaterialAlta;
import Negocio.Compras.GestorMaterialesXProveedor;
import Negocio.Compras.Material;
import Negocio.Compras.MaterialesXProveedor;
import Negocio.Compras.Proveedor;
import Negocio.Exceptiones.ExceptionGestor;
import Negocio.Exceptiones.NegocioException;
import Negocio.Produccion.GestorUnidadMedida;
import Negocio.Produccion.UnidadMedida;
import Negocio.Ventas.Pedido;
import Presentacion.Mensajes;
import Presentacion.TablaManager;
import Presentacion.Utilidades;
import Presentacion.ValidarTexbox;
import Presentacion.ZLinkers.ZLObject;
import Presentacion.ZLinkers.ZLTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Rodrigo
 */
public class PantallaMaterialABM extends javax.swing.JDialog {

    private GestorMaterial gestor;
    private GestorMaterialesXProveedor gestorMaterialXProveedor = new GestorMaterialesXProveedor();
    private TablaManager<MaterialesXProveedor> tmMaterialXProveedor;
    private ZLObject<Material> zlo;
    /** Creates new form PantallaMaterialABM */
    private PantallaMaterialABM(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarValidaciones();
        pnlDetalle.setVisible(true);
        
        pnlBaja.setVisible(false);        
    }

    public PantallaMaterialABM(GestorMaterial gm) {
        this(null, true);
        gestor = gm;
       // lstProveedores.setData(gestor.getProveedores());
        cmbUnidadMedida.setModel(new DefaultComboBoxModel(gestor.getUnidadMedida().toArray()));
        cargarCombos();
        inicializarTablas();
        generarCodigo();

        zlo = new ZLObject<Material>(gestor.getMaterial());
        zlo.add("nombre", true, new ZLTextField(txtNombre));
        zlo.add("codigo", true, new ZLTextField(txtCodigo));
        zlo.add("descripcion", true, new ZLTextField(txtDescripcion));
        zlo.add("diametro", true, new ZLTextField(txtDiametro));
        zlo.add("stockactual", true, new ZLTextField(txtStockActual));
        zlo.add("stockminimo", true, new ZLTextField(txtStockMinimo));
        zlo.add("longi", true, new ZLTextField(txtLongitud));
        
        habilitarCargaDetalle(false);
        if (cmbUnidadMedida.getItemCount()>0) cmbUnidadMedida.setSelectedIndex(0);
    }

    private void generarCodigo()
    {
        String codigo="";
        codigo+=(rdbInsumo.isSelected())?"I":"";
        codigo+=(rdbMateriaPrima.isSelected())?"MP":"";
        try{
        codigo+=txtNombre.getText().subSequence(0, 1).toString().toUpperCase();
        }catch(Exception e){}
        codigo+=gestor.getUltimoID()+1;
        txtCodigo.setText(codigo);
    }

    public void cargar(Material m)
    {
        txtCodigo.setText(m.getCodigo());
        cmbUnidadMedida.setSelectedItem(m.getUnidadMedida());
        txtDescripcion.setText(m.getDescripcion());
        txtDiametro.setText(Utilidades.parseString(m.getDiametro()));
        txtLongitud.setText(Utilidades.parseString(m.getLongitud()));
        txtNombre.setText(m.getNombre());
        txtStockActual.setText(Utilidades.parseString(m.getStockActual()));
        txtStockMinimo.setText(Utilidades.parseString(m.getStockMinimo()));
        rdbMateriaPrima.setSelected(m.isMateriaPrima());
        rdbInsumo.setSelected(!m.isMateriaPrima());
       // lstProveedores.setSelectedItems(m.getProveedores());

        if(m.getFechaBaja()!=null)
        {
            pnlBaja.setVisible(true);
            txtFechaBaja.setText(Utilidades.parseFecha(m.getFechaBaja()));
            txtMotivoBaja.setText(Utilidades.parseString(m.getMotivoBaja()));
        }

    }

    private void cargarValidaciones() {
        ValidarTexbox.validarLongitud(txtCodigo, 2);
        ValidarTexbox.validarLongitud(txtNombre, 50);
        ValidarTexbox.validarInt(txtStockActual);
        ValidarTexbox.validarLongitud(txtStockActual, 3);//ummmmmmmmmmm....
        ValidarTexbox.validarInt(txtStockMinimo);
        ValidarTexbox.validarLongitud(txtStockMinimo, 3);//ummmmmmmmmmm....
        ValidarTexbox.validarInt(txtDiametro);
        ValidarTexbox.validarLongitud(txtDiametro, 6);//ummmmmmmmmmm....
        ValidarTexbox.validarInt(txtLongitud);
        ValidarTexbox.validarLongitud(txtLongitud, 3);//ummmmmmmmm....
        ValidarTexbox.validarLongitud(txtDescripcion, 200);
    }

    public void habilitarBaja(boolean visible,boolean edittable, Date fecha,String motivo)
    {
        pnlBaja.setVisible(visible);
        txtMotivoBaja.setEditable(edittable);
        txtMotivoBaja.setText(Utilidades.parseString(motivo));
        txtFechaBaja.setText(Utilidades.parseFecha(fecha));

    }

    public void habilitarTodo(boolean b) {
        Utilidades.habilitarPanel(pnlMaterial, b);
    }
 

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgTipoMaterial = new javax.swing.ButtonGroup();
        pnlMaterial = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        rdbInsumo = new javax.swing.JRadioButton();
        rdbMateriaPrima = new javax.swing.JRadioButton();
        txtNombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        txtStockMinimo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtStockActual = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtLongitud = new javax.swing.JTextField();
        txtDiametro = new javax.swing.JTextField();
        cmbUnidadMedida = new javax.swing.JComboBox();
        btnAgregarUnidad = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlBaja = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtFechaBaja = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtMotivoBaja = new javax.swing.JTextArea();
        pnlProveedores = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDetalle1 = new javax.swing.JTable();
        btnEliminarDetalle1 = new javax.swing.JButton();
        btnNuevoDetalle1 = new javax.swing.JButton();
        pnlDetalle = new javax.swing.JPanel();
        txtPresentacion = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        lblUnidad1 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cmbProveedor = new javax.swing.JComboBox();
        lblUnidadaPresentacion1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtPrecioUnitario = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        lblUnidadMedida = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlMaterial.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Material", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Descripcion:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("Stock actual:");

        txtCodigo.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Nombre:");

        btgTipoMaterial.add(rdbInsumo);
        rdbInsumo.setFont(new java.awt.Font("Tahoma", 1, 11));
        rdbInsumo.setText("Insumo");
        rdbInsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbInsumoActionPerformed(evt);
            }
        });

        btgTipoMaterial.add(rdbMateriaPrima);
        rdbMateriaPrima.setFont(new java.awt.Font("Tahoma", 1, 11));
        rdbMateriaPrima.setSelected(true);
        rdbMateriaPrima.setText("Materia Prima");
        rdbMateriaPrima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbMateriaPrimaActionPerformed(evt);
            }
        });

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtDescripcion);

        txtStockMinimo.setText("0");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Codigo:");

        txtStockActual.setText("0");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setText("Stock minimo:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Tipo material:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Longitud:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setText("Diametro:");

        cmbUnidadMedida.setName(""); // NOI18N
        cmbUnidadMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUnidadMedidaActionPerformed(evt);
            }
        });

        btnAgregarUnidad.setText("+");
        btnAgregarUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUnidadActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setText("Unidad medida:");

        javax.swing.GroupLayout pnlMaterialLayout = new javax.swing.GroupLayout(pnlMaterial);
        pnlMaterial.setLayout(pnlMaterialLayout);
        pnlMaterialLayout.setHorizontalGroup(
            pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMaterialLayout.createSequentialGroup()
                .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMaterialLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMaterialLayout.createSequentialGroup()
                                .addComponent(rdbMateriaPrima)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdbInsumo))
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(246, 246, 246))
                    .addGroup(pnlMaterialLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10)
                            .addComponent(jLabel2))
                        .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMaterialLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlMaterialLayout.createSequentialGroup()
                                        .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtLongitud)
                                            .addComponent(txtStockActual, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtDiametro)
                                            .addComponent(txtStockMinimo, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                                        .addGap(182, 182, 182))
                                    .addComponent(jScrollPane1)))
                            .addGroup(pnlMaterialLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlMaterialLayout.createSequentialGroup()
                                        .addComponent(cmbUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAgregarUnidad)))))))
                .addContainerGap())
        );
        pnlMaterialLayout.setVerticalGroup(
            pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMaterialLayout.createSequentialGroup()
                .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(rdbMateriaPrima)
                    .addComponent(rdbInsumo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarUnidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStockActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiametro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(pnlMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                .addGap(50, 50, 50))
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

        pnlBaja.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cancelación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel12.setText("Fecha:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel13.setText("Motivo:");

        txtFechaBaja.setEditable(false);

        txtMotivoBaja.setLineWrap(true);
        txtMotivoBaja.setWrapStyleWord(true);
        jScrollPane4.setViewportView(txtMotivoBaja);

        javax.swing.GroupLayout pnlBajaLayout = new javax.swing.GroupLayout(pnlBaja);
        pnlBaja.setLayout(pnlBajaLayout);
        pnlBajaLayout.setHorizontalGroup(
            pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBajaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        pnlBajaLayout.setVerticalGroup(
            pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBajaLayout.createSequentialGroup()
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlProveedores.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Proveedores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbDetalle1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Proveedor", "Presentación"
            }
        ));
        jScrollPane2.setViewportView(tbDetalle1);

        btnEliminarDetalle1.setText("Eliminar");
        btnEliminarDetalle1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarDetalle1MouseClicked(evt);
            }
        });
        btnEliminarDetalle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarDetalle1ActionPerformed(evt);
            }
        });

        btnNuevoDetalle1.setText("Nuevo");
        btnNuevoDetalle1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNuevoDetalle1MouseClicked(evt);
            }
        });
        btnNuevoDetalle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoDetalle1ActionPerformed(evt);
            }
        });

        pnlDetalle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtPresentacion.setEnabled(false);
        txtPresentacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPresentacionKeyReleased(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel18.setText("Presentación:");

        lblUnidad1.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblUnidad1.setText(" ");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel20.setText("Proveedor:");

        cmbProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProveedorActionPerformed(evt);
            }
        });

        lblUnidadaPresentacion1.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblUnidadaPresentacion1.setText(" ");

        jButton1.setText("Aceptar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtPrecioUnitario.setEnabled(false);
        txtPrecioUnitario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioUnitarioKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel19.setText("Precio:");

        lblUnidadMedida.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblUnidadMedida.setText("unidad");

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUnidad1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPresentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblUnidadMedida)
                .addGap(81, 81, 81)
                .addComponent(lblUnidadaPresentacion1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(20, 20, 20))
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUnidad1)
                    .addComponent(lblUnidadaPresentacion1)
                    .addComponent(jLabel20)
                    .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(txtPresentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(txtPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUnidadMedida)
                    .addComponent(jLabel19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlProveedoresLayout = new javax.swing.GroupLayout(pnlProveedores);
        pnlProveedores.setLayout(pnlProveedoresLayout);
        pnlProveedoresLayout.setHorizontalGroup(
            pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProveedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnNuevoDetalle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarDetalle1))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        pnlProveedoresLayout.setVerticalGroup(
            pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProveedoresLayout.createSequentialGroup()
                .addComponent(pnlDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProveedoresLayout.createSequentialGroup()
                        .addComponent(btnNuevoDetalle1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarDetalle1))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlBaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnAceptar)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelar))
                            .addComponent(pnlMaterial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(pnlProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
      try {
        if (txtNombre.getText().isEmpty()) {
            throw new NegocioException("Debe ingresar el nombre");
        } else if(txtStockActual.getText().isEmpty())
        {throw new NegocioException("Debe ingresar el stock actual");}
        else if(txtStockMinimo.getText().isEmpty())
        {throw new NegocioException("Debe ingresar el el stock mínimo");}

        Material material = gestor.getMaterial();
        generarCodigo();
        material.setCodigo(txtCodigo.getText());
        material.setNombre(txtNombre.getText());
        material.setDescripcion(txtDescripcion.getText());
        material.setDiametro(Utilidades.parseInteger(txtDiametro.getText()));
        material.setLongitud(Utilidades.parseInteger(txtLongitud.getText()));
        material.setStockActual(Utilidades.parseInteger(txtStockActual.getText()));
        material.setStockMinimo(Utilidades.parseInteger(txtStockMinimo.getText()));
        material.setEsMateriaPrima(rdbMateriaPrima.isSelected());
       // material.setProveedores(lstProveedores.getSelectedItems());
        material.setMotivoBaja(Utilidades.parseString(txtMotivoBaja.getText()));
        material.setUnidadMedida((UnidadMedida) cmbUnidadMedida.getSelectedItem());
        

            gestor.ejecutarCU(material);

            for (MaterialesXProveedor mxp : tmMaterialXProveedor.getDatos()) {
                mxp.setMaterial(material);
                 gestorMaterialXProveedor.guardar(mxp);
            }

            this.setVisible(false);
            Mensajes.mensajeInformacion("El material ha sido guardado correctamente");
        } catch (ExceptionGestor ex) {
            Mensajes.mensajeErrorGenerico(ex.getMessage());
        }catch (NegocioException ex) {
            Mensajes.mensajeErrorGenerico(ex.getMessage());
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        gestor.finalizarCU();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAgregarUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUnidadActionPerformed
        // TODO add your handling code here:
        new GestorUnidadMedida().administrarUnidadMedida(this);
        cmbUnidadMedida.setModel(new DefaultComboBoxModel(gestor.getUnidadMedida().toArray()));
        cmbUnidadMedida.setSelectedIndex(-1);
    }//GEN-LAST:event_btnAgregarUnidadActionPerformed

    private void rdbMateriaPrimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbMateriaPrimaActionPerformed
        // TODO add your handling code here:
        generarCodigo();
    }//GEN-LAST:event_rdbMateriaPrimaActionPerformed

    private void rdbInsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbInsumoActionPerformed
        // TODO add your handling code here:
        generarCodigo();
    }//GEN-LAST:event_rdbInsumoActionPerformed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        // TODO add your handling code here:
        generarCodigo();
    }//GEN-LAST:event_txtNombreKeyReleased

    private void btnEliminarDetalle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarDetalle1ActionPerformed
    if(tmMaterialXProveedor.getSelectedRow()!=-1){
        tmMaterialXProveedor.removeSelectedRow();
        tmMaterialXProveedor.updateTabla();
        }
}//GEN-LAST:event_btnEliminarDetalle1ActionPerformed

    private void btnNuevoDetalle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoDetalle1ActionPerformed
  habilitarCargaDetalle(true);
  txtPresentacion.requestFocus();

}//GEN-LAST:event_btnNuevoDetalle1ActionPerformed

    private void txtPresentacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPresentacionKeyReleased

}//GEN-LAST:event_txtPresentacionKeyReleased

    private void cmbProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProveedorActionPerformed


    }//GEN-LAST:event_cmbProveedorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            if(cmbProveedor.getSelectedIndex() == -1)
        {
            Mensajes.mensajeErrorGenerico("Debe seleccionar un proveedor");
            return;
        }

        try {
            new Short(txtPresentacion.getText());
        } catch (Exception e) {
            Mensajes.mensajeErrorGenerico("La presentación ingresada es incorrecta, debe ser un valor positivo");
            return;
            }

        MaterialesXProveedor mxp = new MaterialesXProveedor();
      //  MaterialesXProveedor temp= (MaterialesXProveedor) cmbPresentacion.getSelectedItem();
        mxp.setPresentacion(Utilidades.parseLong(txtPresentacion.getText()));
        mxp.setPrecio(Float.parseFloat(txtPrecioUnitario.getText()));
        Proveedor proveedor = (Proveedor)cmbProveedor.getSelectedItem();
        Material m = new Material();
      //  m.setCodigo(MaterialBD.getUltimoID()+1 + "");

        mxp.setProveedor(proveedor);

      //  mxp.setMaterial(m);
        tmMaterialXProveedor.add(mxp);

        limpiarCargaDetalle();
        habilitarCargaDetalle(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
      

    }//GEN-LAST:event_jButton1MouseClicked

        public void habilitarCargaDetalle(boolean valor) {
        Utilidades.habilitarPanel(pnlDetalle, valor);
    }
    private void txtPrecioUnitarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioUnitarioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioUnitarioKeyReleased

    private void btnNuevoDetalle1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoDetalle1MouseClicked
       habilitarCargaDetalle(true);
    }//GEN-LAST:event_btnNuevoDetalle1MouseClicked

    private void btnEliminarDetalle1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarDetalle1MouseClicked
      if(tmMaterialXProveedor.getSelectedRow()!=-1){
        tmMaterialXProveedor.removeSelectedRow();
        tmMaterialXProveedor.updateTabla();
        }
    }//GEN-LAST:event_btnEliminarDetalle1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        limpiarCargaDetalle();
        habilitarCargaDetalle(false);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        habilitarCargaDetalle(false);
        limpiarCargaDetalle();
    }//GEN-LAST:event_jButton2MouseClicked

    private void cmbUnidadMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUnidadMedidaActionPerformed
        if (cmbUnidadMedida.getSelectedIndex()!=-1){
            lblUnidadMedida.setText(cmbUnidadMedida.getSelectedItem().toString());
            tmMaterialXProveedor.updateTabla();
        }
    }//GEN-LAST:event_cmbUnidadMedidaActionPerformed

    private void cargarCombos() {
        cmbProveedor.setModel(new DefaultComboBoxModel(gestor.getProveedores().toArray()));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                new GestorMaterialAlta().iniciarCU();

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgTipoMaterial;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAgregarUnidad;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarDetalle1;
    private javax.swing.JButton btnNuevoDetalle1;
    private javax.swing.JComboBox cmbProveedor;
    private javax.swing.JComboBox cmbUnidadMedida;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblUnidad1;
    private javax.swing.JLabel lblUnidadMedida;
    private javax.swing.JLabel lblUnidadaPresentacion1;
    private javax.swing.JPanel pnlBaja;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlMaterial;
    private javax.swing.JPanel pnlProveedores;
    private javax.swing.JRadioButton rdbInsumo;
    private javax.swing.JRadioButton rdbMateriaPrima;
    private javax.swing.JTable tbDetalle1;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtDiametro;
    private javax.swing.JTextField txtFechaBaja;
    private javax.swing.JTextField txtLongitud;
    private javax.swing.JTextArea txtMotivoBaja;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecioUnitario;
    private javax.swing.JTextField txtPresentacion;
    private javax.swing.JTextField txtStockActual;
    private javax.swing.JTextField txtStockMinimo;
    // End of variables declaration//GEN-END:variables

    private void limpiarCargaDetalle() {
        txtPresentacion.setText("");
        txtPrecioUnitario.setText("");
    }

    private void inicializarTablas() {
          tmMaterialXProveedor = new TablaManager<MaterialesXProveedor>(tbDetalle1) {

            @Override
            public Vector ObjetoFila(MaterialesXProveedor elemento) {
                ///////////////////////////////
                ////////////////////////////////
                Vector fila = new Vector();
                fila.add(elemento.getProveedor().getRazonSocial());
                fila.add(elemento.getPresentacion());
                fila.add(cmbUnidadMedida.getSelectedItem().toString());
                fila.add(elemento.getPrecio());
                return fila;
            }

            @Override
            public Vector getCabecera() {
                Vector cabecera = new Vector();
                cabecera.add("Proveedor");
                cabecera.add("Presentación");
                cabecera.add("Unidad de medida");
                cabecera.add("Precio");
                return cabecera;
            }
        };

    }


}
