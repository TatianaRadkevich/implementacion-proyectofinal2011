package Presentacion.Produccion;

import Negocio.Administracion.Cargo;
import Negocio.Administracion.GestorCargo;
import Negocio.Compras.GestorMaterialAlta;
import Negocio.Compras.Material;
import Negocio.Exceptiones.ExceptionGestor;
import Negocio.Produccion.DetalleEtapaProduccion;
import Negocio.Produccion.EtapaProduccion;
import Negocio.Produccion.EtapaProduccionEspecifica;
import Negocio.Produccion.GestorEstructura;
import Negocio.Produccion.GestorEtapaProduccion;
import Negocio.Produccion.GestorTipoMaquinaHerramienta;
import Negocio.Produccion.Producto;
import Negocio.Produccion.TipoMaquinaHerramienta;
import Negocio.Produccion.TipoProducto;
import Presentacion.IniciadorDeVentanas;
import Presentacion.Mensajes;
import Presentacion.TablaManager;
import Presentacion.Utilidades;
import Presentacion.ValidarTexbox;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Heber Parrucci
 */
public class PantallaEstructuraProductoABM extends javax.swing.JDialog {

    private boolean nuevo = false, modificar = false;
    private GestorEstructura gestor;
    private TablaManager<EtapaProduccionEspecifica> tmEtapaEspecifica;
    private TablaManager<DetalleEtapaProduccion> tmMateriales;
    private JComboBox cmbMaterial;

    public PantallaEstructuraProductoABM(GestorEstructura gestor) {
        super((JDialog) null, true);
        this.gestor = gestor;
        initComponents();
        IniciadorDeVentanas.iniciarVentana(this, this.getWidth(), this.getHeight());
        //Seteo de variables//
        cmbMaterial = new JComboBox();
        tmEtapaEspecifica = new TablaManager<EtapaProduccionEspecifica>(tbEtapas) {

            @Override
            public Vector ObjetoFila(EtapaProduccionEspecifica elemento) {
                Vector fila = new Vector();
                fila.add(elemento.getNumeroOrden());
                fila.add(elemento.getEtapaProduccion());
                fila.add(elemento.getCargo());

                if (elemento.getDuracion() != null && elemento.getDuracion() % 60 == 0) {
                    fila.add(elemento.getDuracion() / 60 + " min.");

                } else {
                    if(elemento.getDuracion() != null)
                        fila.add(elemento.getDuracion() + " seg.");
                    else
                        fila.add("");
                }


                fila.add(elemento.getDescripcion());
                return fila;
            }

            @Override
            public Vector getCabecera() {
                Vector cabecera = new Vector();
                cabecera.add("Nro Orden");
                cabecera.add("Tipo Etapa");
                cabecera.add("Cargo");
                cabecera.add("Duracion");
                cabecera.add("Descripcion");
                return cabecera;
            }
        };
        tmEtapaEspecifica.addSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                cargarEtapa(tmEtapaEspecifica.getSeletedObject());
            }
        });

        tmMateriales = new TablaManager<DetalleEtapaProduccion>(tbMaterial) {

            @Override
            public Vector ObjetoFila(DetalleEtapaProduccion elemento) {
                Vector fila = new Vector();
                fila.add(elemento.getMaterial());
                fila.add((elemento.getCantidadNecesaria() == null) ? "" : elemento.getCantidadNecesaria().intValueExact());
                return fila;
            }

            @Override
            public Vector getCabecera() {
                Vector cabecera = new Vector();
                cabecera.add("Material");
                cabecera.add("Cantidad");
                return cabecera;
            }

            @Override
            public boolean isCellEditable(int columna) {
                return true;
            }

            @Override
            public void fireTableCellUpdated(int row, int column) {
                Object value = tbMaterial.getValueAt(row, column);
                if (column == 0) {
                    tmMateriales.getDato(row).setMaterial((Material) value);
                }
                if (column == 1) {
                    tmMateriales.getDato(row).setCantidadNecesaria(new BigDecimal(value.toString()));
                }
            }
        };
        tbMaterial.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cmbMaterial));
        tbMaterial.getColumnModel().getColumn(0).setCellRenderer(new TableCellRenderer() {

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JComboBox cmb=new JComboBox();
                cmb.addItem(value);
                cmb.setEnabled(table.isEnabled());
                return cmb;
            }
        });
        JTextField txt = new JTextField();
        ValidarTexbox.validarInt(txt);
        ValidarTexbox.validarLongitud(txt, 3);
        tbMaterial.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(txt));
        tbMaterial.getColumnModel().getColumn(1).setCellRenderer(new TableCellRenderer() {

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JTextField txt=new JTextField();
                txt.setText(value.toString());
                txt.setEnabled(table.isEnabled());
                return txt;
            }
        });
        tbMaterial.setRowHeight((int) cmbMaterial.getPreferredSize().getHeight());
        cargarCombos();
        cargarValidaciones();

    }

    private void cargarValidaciones() {
        ValidarTexbox.validarInt(txtOrden);
        ValidarTexbox.validarLongitud(txtOrden, 2);
        ValidarTexbox.validarFloat(txtHorasHombre);
        ValidarTexbox.validarLongitud(txtHorasHombre, 6);
        ValidarTexbox.validarLongitud(txtDescripcion, 200);
//        ValidarTexbox.validarInt(txtRepeticiones);
//        ValidarTexbox.validarLongitud(txtRepeticiones, 6);


    }

    private void cargarCombos() {

        cmbCargo.setModel(new DefaultComboBoxModel(gestor.listarCargos().toArray()));
        cmbTipoEtapa.setModel(new DefaultComboBoxModel(gestor.listarEtapasGenericas().toArray()));
        cmbTipoProducto.setModel(new DefaultComboBoxModel(gestor.listarTipoProducto().toArray()));
        cmbTipoMaquina.setModel(new DefaultComboBoxModel(gestor.listarMaquinaHerramienta().toArray()));
        cmbMaterial.setModel(new DefaultComboBoxModel(gestor.listarMaterial().toArray()));
        cmbTipoProducto.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (cmbTipoProducto.getSelectedIndex() != -1) {
                    cmbProducto.setModel(new DefaultComboBoxModel(gestor.getProductos((TipoProducto) cmbTipoProducto.getSelectedItem()).toArray()));
                } else {
                    cmbProducto.setModel(new DefaultComboBoxModel(new Vector<Producto>()));
                }
                cmbProducto.setSelectedIndex(-1);

            }
        });
        cmbTipoProducto.setSelectedIndex(0);
    }

    public void habilitarSeleccionProducto(boolean valor) {
        pnlProducto.setEnabled(valor);
        cmbTipoProducto.setEnabled(valor);
        cmbProducto.setEnabled(valor);
    }

    public void habilitarSelecionEtapa(boolean valor) {
        tbEtapas.setEnabled(valor);

        btnNuevo.setEnabled(valor);
        btnModificar.setEnabled(valor);
        btnEliminar.setEnabled(valor);

        btnBajar.setEnabled(valor);
        btnSubir.setEnabled(valor);
    }

    public void habilitarCarga(boolean valor) {
        habilitarSeleccionProducto(!valor);
        habilitarSelecionEtapa(!valor);
        btnAceptar.setEnabled(!valor);
        btnCancelar.setEnabled(!valor);

//        pnlEtapaEspecifica.setEnabled(valor);
//        cmbCargo.setEnabled(valor);
//        cmbTipoEtapa.setEnabled(valor);
//        txtDescripcion.setEnabled(valor);
//        txtHorasHombre.setEnabled(valor);
//        txtOrden.setEnabled(valor);

        Utilidades.habilitarPanel(pnlEtapaEspecifica, valor);

//        //  cmbTipoMaquina.setEnabled(valor);
//        //  cmbMaterial.setEnabled(valor);
//        //   txtRepeticiones.setEditable(valor);
//
//        btnCargarCargo.setEnabled(valor);
//        btnCargarMaterial.setEnabled(valor);
//        btnCargarTipoEtapa.setEnabled(valor);
//        btnCargarTipoMaquina.setEnabled(valor);
//
////        tbDetalle.setEnabled(valor);
////        btnNuevoDetalleEtapa.setEnabled(valor);
////        btnEliminarDetalleEtapa.setEnabled(valor);
//
//        btnAceptarEtapa.setEnabled(valor);
//        btnCancelarEtapa.setEnabled(valor);
    }

    public void cargarProducto(final Producto prod) {


        cmbTipoProducto.setSelectedItem(prod.getTTproducto());
        cmbProducto.setSelectedItem(prod);

        EtapaProduccionEspecifica[] etapa = new EtapaProduccionEspecifica[prod.getEtapasProduccionEspecificas().size()];
        for (EtapaProduccionEspecifica epe : prod.getEtapasProduccionEspecificas()) {
            etapa[epe.getNumeroOrden() - 1] = epe;
        }

        for (EtapaProduccionEspecifica epe : etapa) {
            tmEtapaEspecifica.add(epe);
        }
        //ordeno las etapas por el numero





    }

    public void cargarEtapa(EtapaProduccionEspecifica ep) {
        if (ep == null) {
            limpiarEtapa();
            return;
        }



        txtDescripcion.setText(Utilidades.parseString(ep.getDescripcion()));


        if (ep.getHorasHombre() != null && ep.getHorasHombre().intValueExact() % 60 == 0) {
            txtHorasHombre.setText((int) (ep.getHorasHombre().intValueExact() / 60) + "");
            cmbTiempoHorasHom.setSelectedIndex(1);
        } else {
            txtHorasHombre.setText(Utilidades.parseString(ep.getHorasHombre().intValueExact()));
            cmbTiempoHorasHom.setSelectedIndex(0);
        }

        if (ep.getDuracion() != null && ep.getDuracion() % 60 == 0) {
            txtDuracion.setText(ep.getDuracion() / 60 + "");
            cmbTiempoDurEtapa.setSelectedIndex(1);
        } else {
            txtDuracion.setText(Utilidades.parseString(ep.getDuracion()));
            cmbTiempoDurEtapa.setSelectedIndex(0);
        }

        txtOrden.setText(Utilidades.parseString(ep.getNumeroOrden()));


        cmbCargo.setSelectedItem(ep.getCargo());
        cmbTipoEtapa.setSelectedItem(ep.getEtapaProduccion());


        List<DetalleEtapaProduccion> mteriales = new ArrayList<DetalleEtapaProduccion>();

        for (DetalleEtapaProduccion det : ep.getDetalleEtapaProduccion()) {
            if (det.getTipoMaquinaHerramienta() != null) {
                cmbTipoMaquina.setSelectedItem(det.getTipoMaquinaHerramienta());
            } else if (det.getMaterial() != null) {
                mteriales.add(det);
            }
        }

        tmMateriales.setDatos(mteriales);

        // cmbTipoMaquina.setSelectedItem(ep.getDetalleEtapaProduccion().get(0).getTipoMaquinaHerramienta());
        //cmbMaterial.setSelectedItem(ep.getDetalleEtapaProduccion().get(0).getMaterial());
        //txtRepeticiones.setText(ep.getDetalleEtapaProduccion().get(0).getCantidadRepeticiones() + "");

    }

    public void limpiarTodo() {
        cmbTipoProducto.setSelectedIndex(-1);
        tmEtapaEspecifica.setDatos(new ArrayList<EtapaProduccionEspecifica>());
        limpiarEtapa();
    }

    public void limpiarEtapa() {
        txtDescripcion.setText("");
        txtHorasHombre.setText("");
        txtOrden.setText("");
        cmbTiempoDurEtapa.setSelectedIndex(-1);
        cmbTiempoHorasHom.setSelectedIndex(-1);
        txtDuracion.setText("");
        cmbCargo.setSelectedIndex(-1);
        cmbTipoEtapa.setSelectedIndex(-1);
        cmbMaterial.setSelectedIndex(-1);
        tmMateriales.setDatos(new ArrayList<DetalleEtapaProduccion>());
        cmbTipoMaquina.setSelectedIndex(-1);

    }

    public void recalcularNroOrden() {
        for (byte i = 0; i < tmEtapaEspecifica.getSize(); i++) {
            tmEtapaEspecifica.getDato(i).setNumeroOrden((byte) (1 + i));
        }
        tmEtapaEspecifica.updateTabla();

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlEtapaEspecifica = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnCargarCargo = new javax.swing.JButton();
        cmbTipoMaquina = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        txtHorasHombre = new javax.swing.JTextField();
        txtDuracion = new javax.swing.JTextField();
        cmbTipoEtapa = new javax.swing.JComboBox();
        cmbTiempoDurEtapa = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        btnCargarTipoMaquina = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cmbCargo = new javax.swing.JComboBox();
        txtOrden = new javax.swing.JTextField();
        btnCargarTipoEtapa = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbTiempoHorasHom = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        btnEliminarMaterial = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbMaterial = new javax.swing.JTable();
        btnNuevoMaterial = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnCancelarEtapa = new javax.swing.JButton();
        btnAceptarEtapa = new javax.swing.JButton();
        pnlProducto = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbTipoProducto = new javax.swing.JComboBox();
        cmbProducto = new javax.swing.JComboBox();
        pnlEtapas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbEtapas = new javax.swing.JTable();
        btnSubir = new javax.swing.JButton();
        btnBajar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Estructura Producto");

        pnlEtapaEspecifica.setBorder(javax.swing.BorderFactory.createTitledBorder("Etapa especifica"));

        txtDescripcion.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        jScrollPane4.setViewportView(txtDescripcion);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel15.setText("Descripcion:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addContainerGap(205, Short.MAX_VALUE))
            .addComponent(jScrollPane4)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
        );

        btnCargarCargo.setText("+");
        btnCargarCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarCargoActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel21.setText("Duracion Etapa:");

        cmbTiempoDurEtapa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "seg.", "min." }));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel14.setText("Cargo:");

        btnCargarTipoMaquina.setText("+");
        btnCargarTipoMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarTipoMaquinaActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel13.setText("Horas Hombre:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel17.setText("Nro orden:");

        txtOrden.setEditable(false);

        btnCargarTipoEtapa.setText("+");
        btnCargarTipoEtapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarTipoEtapaActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel16.setText("Tipo Etapa:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Tipo maquina:");

        cmbTiempoHorasHom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "seg.", "min." }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16)
                    .addComponent(jLabel21)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtHorasHombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTiempoHorasHom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTiempoDurEtapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(cmbTipoEtapa, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnCargarTipoEtapa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cmbTipoMaquina, javax.swing.GroupLayout.Alignment.LEADING, 0, 146, Short.MAX_VALUE)
                                .addComponent(cmbCargo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnCargarTipoMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnCargarCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cmbTipoEtapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCargarTipoEtapa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(cmbTiempoDurEtapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cmbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCargarCargo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtHorasHombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTiempoHorasHom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbTipoMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCargarTipoMaquina)))
        );

        btnEliminarMaterial.setText("Eliminar");
        btnEliminarMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarMaterialActionPerformed(evt);
            }
        });

        tbMaterial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Material", "Cantidad"
            }
        ));
        jScrollPane2.setViewportView(tbMaterial);

        btnNuevoMaterial.setText("Nuevo");
        btnNuevoMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoMaterialActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Materiales necesarios:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnNuevoMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminarMaterial)))
                    .addComponent(jLabel3))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnNuevoMaterial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarMaterial)
                        .addContainerGap(113, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)))
        );

        btnCancelarEtapa.setText("Cancelar etapa");
        btnCancelarEtapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarEtapaActionPerformed(evt);
            }
        });

        btnAceptarEtapa.setText("Aceptar etapa");
        btnAceptarEtapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarEtapaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEtapaEspecificaLayout = new javax.swing.GroupLayout(pnlEtapaEspecifica);
        pnlEtapaEspecifica.setLayout(pnlEtapaEspecificaLayout);
        pnlEtapaEspecificaLayout.setHorizontalGroup(
            pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEtapaEspecificaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlEtapaEspecificaLayout.createSequentialGroup()
                        .addComponent(btnAceptarEtapa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarEtapa))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlEtapaEspecificaLayout.setVerticalGroup(
            pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEtapaEspecificaLayout.createSequentialGroup()
                .addGroup(pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEtapaEspecificaLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelarEtapa)
                            .addComponent(btnAceptarEtapa)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlProducto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Producto:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Tipo producto:");

        cmbTipoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoProductoActionPerformed(evt);
            }
        });

        cmbProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlProductoLayout = new javax.swing.GroupLayout(pnlProducto);
        pnlProducto.setLayout(pnlProductoLayout);
        pnlProductoLayout.setHorizontalGroup(
            pnlProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(308, 308, 308))
        );
        pnlProductoLayout.setVerticalGroup(
            pnlProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductoLayout.createSequentialGroup()
                .addGroup(pnlProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cmbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlEtapas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Etapas de produccion del producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbEtapas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbEtapas);

        btnSubir.setText("Subir");
        btnSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirActionPerformed(evt);
            }
        });

        btnBajar.setText("Bajar");
        btnBajar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnNuevo.setText("Insertar");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEtapasLayout = new javax.swing.GroupLayout(pnlEtapas);
        pnlEtapas.setLayout(pnlEtapasLayout);
        pnlEtapasLayout.setHorizontalGroup(
            pnlEtapasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEtapasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEtapasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBajar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSubir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlEtapasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlEtapasLayout.setVerticalGroup(
            pnlEtapasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEtapasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEtapasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addGroup(pnlEtapasLayout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addGap(6, 6, 6)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar))
                    .addGroup(pnlEtapasLayout.createSequentialGroup()
                        .addComponent(btnSubir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBajar)))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar))
                    .addComponent(pnlEtapas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 906, Short.MAX_VALUE)
                    .addComponent(pnlEtapaEspecifica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEtapas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEtapaEspecifica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap())
        );

        pnlEtapaEspecifica.getAccessibleContext().setAccessibleName("Etapa Seleccionada");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if (tmEtapaEspecifica.getSeletedObject() == null) {
            Mensajes.mensajeInformacion("Por favor antes seleccione una etapa para eliminarla");
            return;
        }

        if (Mensajes.mensajeConfirmacionGenerico("¿Realmente desea eliminar este elemento?")) {
            tmEtapaEspecifica.removeSelectedRow();
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBajarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajarActionPerformed
        // TODO add your handling code here:
        int index = tmEtapaEspecifica.getSelectedRow();
        if (index == -1) {
            return;
        }
        if (index == (tmEtapaEspecifica.getSize() - 1)) {
            return;
        }
        tmEtapaEspecifica.insert(index + 1, tmEtapaEspecifica.removeRow(index));
        recalcularNroOrden();
        tmEtapaEspecifica.setSelectedRow(index + 1);

    }//GEN-LAST:event_btnBajarActionPerformed

    private void cmbTipoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoProductoActionPerformed

    private void cmbProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProductoActionPerformed
        // TODO add your handling code here:

        if (cmbProducto.getSelectedIndex() != -1) {
            gestor.setProducto((Producto) cmbProducto.getSelectedItem());
        } else {
            tmEtapaEspecifica.setDatos(new ArrayList<EtapaProduccionEspecifica>());
            limpiarEtapa();
        }

    }//GEN-LAST:event_cmbProductoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        if (Mensajes.mensajeConfirmacionGenerico("¿Realmente desea salir?")) {
            gestor.finalizarCU();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try {
            // TODO add your handling code here:
            gestor.ejecutarCU((ArrayList<EtapaProduccionEspecifica>) tmEtapaEspecifica.getDatos());
            Mensajes.mensajeInformacion("Se ha guardado con exito");
            gestor.finalizarCU();
        } catch (ExceptionGestor ex) {
            Mensajes.mensajeErrorGenerico(ex.getMessage());
        }

    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        habilitarCarga(true);
        nuevo = true;
        limpiarEtapa();
        if (tbEtapas.getSelectedRow() != -1) {
            txtOrden.setText(tbEtapas.getSelectedRow() + 1 + "");
        } else {
            txtOrden.setText(tbEtapas.getRowCount() + 1 + "");
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarEtapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarEtapaActionPerformed
        // TODO add your handling code here:
        limpiarEtapa();
        habilitarCarga(false);
    }//GEN-LAST:event_btnCancelarEtapaActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        if (tmEtapaEspecifica.getSeletedObject() == null) {
            Mensajes.mensajeInformacion("Por favor antes seleccione una etapa para modificarla");
            return;
        }

        habilitarCarga(true);
        modificar = true;
        cargarEtapa(tmEtapaEspecifica.getSeletedObject());

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnAceptarEtapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarEtapaActionPerformed
        // TODO add your handling code here:
        EtapaProduccionEspecifica etapa;
        Integer duracion;

        if (nuevo) {
            etapa = new EtapaProduccionEspecifica();
        } else if (modificar) {
            etapa = tmEtapaEspecifica.getSeletedObject();
        } else {
            throw new RuntimeException("nuevo y modifcar = false");
        }


        etapa.setCargo((Cargo) cmbCargo.getSelectedItem());
        etapa.setDescripcion(txtDescripcion.getText());
        etapa.setEtapaProduccion((EtapaProduccion) cmbTipoEtapa.getSelectedItem());
        etapa.setNumeroOrden(Utilidades.parseByte(txtOrden.getText()));

        duracion = Utilidades.parseInteger(txtDuracion.getText());
        if (duracion != null) {
            if (cmbTiempoDurEtapa.getSelectedIndex() == 1) {
                duracion = duracion * 60;
            }
        }
        etapa.setDuracion(duracion);

        duracion = Utilidades.parseInteger(txtHorasHombre.getText());
        if (duracion != null) {
            if (cmbTiempoHorasHom.getSelectedIndex() == 1) {
                duracion = duracion * 60;
            }
        }
        etapa.setHorasHombre(new BigDecimal(duracion));


        etapa.setDetalleEtapaProduccion(tmMateriales.getDatos());
        etapa.addDetalleEtapaProduccion(
                new DetalleEtapaProduccion(
                (TipoMaquinaHerramienta) cmbTipoMaquina.getSelectedItem()));


        etapa.setProducto(gestor.getProducto());

        if (nuevo) {
            tmEtapaEspecifica.insert(etapa.getNumeroOrden() - 1, etapa);
            recalcularNroOrden();
        }

        tmEtapaEspecifica.updateTabla();
        modificar = false;
        nuevo = false;

        limpiarEtapa();
        habilitarCarga(false);


    }//GEN-LAST:event_btnAceptarEtapaActionPerformed

    private void btnCargarTipoEtapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarTipoEtapaActionPerformed
        // TODO add your handling code here:
        new GestorEtapaProduccion().administarTipoProducto(this);
        cmbTipoEtapa.setModel(new DefaultComboBoxModel(gestor.listarEtapasGenericas().toArray()));
        cmbTipoEtapa.setSelectedIndex(-1);

    }//GEN-LAST:event_btnCargarTipoEtapaActionPerformed

    private void btnCargarCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarCargoActionPerformed
        // TODO add your handling code here:
        new GestorCargo().administarCargo(this);
        cmbCargo.setModel(new DefaultComboBoxModel(gestor.listarCargos().toArray()));
        cmbCargo.setSelectedIndex(-1);
    }//GEN-LAST:event_btnCargarCargoActionPerformed

    private void btnNuevoMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoMaterialActionPerformed
        // TODO add your handling code here:
        tmMateriales.add(new DetalleEtapaProduccion());
    }//GEN-LAST:event_btnNuevoMaterialActionPerformed

    private void btnEliminarMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarMaterialActionPerformed
        // TODO add your handling code here:
        if (tmMateriales.getSeletedObject() != null) {
            tmMateriales.removeSelectedRow();
        }
    }//GEN-LAST:event_btnEliminarMaterialActionPerformed

    private void btnCargarTipoMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarTipoMaquinaActionPerformed
        // TODO add your handling code here:
        new GestorTipoMaquinaHerramienta().administar();
        cmbTipoMaquina.setModel(new DefaultComboBoxModel(gestor.listarMaquinaHerramienta().toArray()));
        cmbTipoMaquina.setSelectedIndex(-1);

    }//GEN-LAST:event_btnCargarTipoMaquinaActionPerformed

    private void btnSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirActionPerformed
        // TODO add your handling code here:
        int index = tmEtapaEspecifica.getSelectedRow();
        if (index == -1) {
            return;
        }
        if (index == 0) {
            return;
        }
        tmEtapaEspecifica.insert(index - 1, tmEtapaEspecifica.removeRow(index));
        recalcularNroOrden();
        tmEtapaEspecifica.setSelectedRow(index - 1);



    }//GEN-LAST:event_btnSubirActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new GestorEstructura().iniciarCU();
//                PantallaEstructuraProductoABM dialog = new PantallaEstructuraProductoABM(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAceptarEtapa;
    private javax.swing.JButton btnBajar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarEtapa;
    private javax.swing.JButton btnCargarCargo;
    private javax.swing.JButton btnCargarTipoEtapa;
    private javax.swing.JButton btnCargarTipoMaquina;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarMaterial;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevoMaterial;
    private javax.swing.JButton btnSubir;
    private javax.swing.JComboBox cmbCargo;
    private javax.swing.JComboBox cmbProducto;
    private javax.swing.JComboBox cmbTiempoDurEtapa;
    private javax.swing.JComboBox cmbTiempoHorasHom;
    private javax.swing.JComboBox cmbTipoEtapa;
    private javax.swing.JComboBox cmbTipoMaquina;
    private javax.swing.JComboBox cmbTipoProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel pnlEtapaEspecifica;
    private javax.swing.JPanel pnlEtapas;
    private javax.swing.JPanel pnlProducto;
    private javax.swing.JTable tbEtapas;
    private javax.swing.JTable tbMaterial;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtDuracion;
    private javax.swing.JTextField txtHorasHombre;
    private javax.swing.JTextField txtOrden;
    // End of variables declaration//GEN-END:variables
}
