package Presentacion.Produccion;

import BaseDeDatos.Compras.MaterialBD;
import BaseDeDatos.Produccion.TipoMaquinaHerramientaBD;
import Negocio.Administracion.Cargo;
import Negocio.Administracion.GestorCargo;
import Negocio.Compras.Material;
import Negocio.Exceptiones.ExceptionGestor;
import Negocio.Produccion.DetalleEtapaProduccion;
import Negocio.Produccion.EtapaProduccion;
import Negocio.Produccion.EtapaProduccionEspecifica;
import Negocio.Produccion.GestorEstructura;
import Negocio.Produccion.GestorEtapaProduccion;
import Negocio.Produccion.Producto;
import Negocio.Produccion.TipoMaquinaHerramienta;
import Negocio.Produccion.TipoProducto;
import Presentacion.IniciadorDeVentanas;
import Presentacion.Mensajes;
import Presentacion.TablaManager;
import Presentacion.Utilidades;
import Presentacion.ValidarTexbox;
import gui.GUILocal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Heber Parrucci
 */
public class PantallaEstructuraProductoABM extends javax.swing.JDialog {

    private boolean nuevo = false, modificar = false;
    private GestorEstructura gestor;
    private TablaManager<EtapaProduccionEspecifica> tmEtapaEspecifica;
    private TablaManager<DetalleEtapaProduccion> tmDetalleEtapa;

    public PantallaEstructuraProductoABM(GestorEstructura gestor) {
        super((JDialog) null, true);
        this.gestor = gestor;
        initComponents();
        IniciadorDeVentanas.iniciarVentana(this, this.getWidth(), this.getHeight());
        //Seteo de variables//
        tmEtapaEspecifica = new TablaManager<EtapaProduccionEspecifica>(tbEtapas) {

            @Override
            public Vector ObjetoFila(EtapaProduccionEspecifica elemento) {
                Vector fila = new Vector();
                fila.add(elemento.getNumeroOrden());
                fila.add(elemento.getEtapaProduccion());
                fila.add(elemento.getCargo());
                fila.add(elemento.getHorasHombre());
                fila.add(elemento.getDescripcion());
                return fila;
            }

            @Override
            public Vector getCabecera() {
                Vector cabecera = new Vector();
                cabecera.add("Nro Orden");
                cabecera.add("Tipo Etapa");
                cabecera.add("Cargo");
                cabecera.add("Horas Hombre");
                cabecera.add("Descripcion");
                return cabecera;
            }
        };
        tmEtapaEspecifica.addListenerModificaionSelecion(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                cargarEtapa(tmEtapaEspecifica.getSeletedObject());
            }
        });
       
        cargarCombos();
        cargarValidaciones();

    }

  

    private void cargarValidaciones() {
        ValidarTexbox.validarInt(txtOrden);
        ValidarTexbox.validarLongitud(txtOrden, 2);
        ValidarTexbox.validarInt(txtHorasHombre);
        ValidarTexbox.validarLongitud(txtHorasHombre, 6);
        ValidarTexbox.validarLongitud(txtDescripcion, 200);

    }

    private void cargarCombos() {
        cmbCargo.setModel(new DefaultComboBoxModel(gestor.listarCargos().toArray()));
        cmbTipoEtapa.setModel(new DefaultComboBoxModel(gestor.listarEtapasGenericas().toArray()));
        cmbTipoProducto.setModel(new DefaultComboBoxModel(gestor.listarTipoProducto().toArray()));
        cmbTipoProducto.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (cmbTipoProducto.getSelectedIndex() != -1) {
                    cmbProducto.setModel(new DefaultComboBoxModel(gestor.getProductos((TipoProducto) cmbTipoProducto.getSelectedItem()).toArray()));
                } else {
                    cmbProducto.setModel(new DefaultComboBoxModel(new Vector<Producto>()));
                }

            }
        });
        cmbTipoProducto.setSelectedIndex(0);
    }

    public void habilitarSeleccionProducto(boolean valor) {
        pnlProducto.setEnabled(valor);
        cmbTipoProducto.setEditable(valor);
        cmbProducto.setEnabled(valor);
    }

    public void habilitarCarga(boolean valor) {
        habilitarSeleccionProducto(!valor);
        tbEtapas.setEnabled(!valor);

        btnNuevo.setEnabled(!valor);
        btnModificar.setEnabled(!valor);
        btnEliminar.setEnabled(!valor);

        btnBajar.setEnabled(!valor);
        btnSubir.setEnabled(!valor);

        pnlEtapaEspecifica.setEnabled(valor);
        cmbCargo.setEnabled(valor);
        cmbTipoEtapa.setEnabled(valor);
        txtDescripcion.setEnabled(valor);
        txtHorasHombre.setEnabled(valor);
        txtOrden.setEnabled(valor);

//        tbDetalle.setEnabled(valor);
//        btnNuevoDetalleEtapa.setEnabled(valor);
//        btnEliminarDetalleEtapa.setEnabled(valor);

        btnAceptarEtapa.setEnabled(valor);
        btnCancelarEtapa.setEnabled(valor);
    }

    public void cargarProducto(final Producto prod) {

        new Thread(new Runnable() {

            public void run() {
                cmbTipoProducto.setSelectedItem(prod.getTTproducto());
                while (prod.equals(cmbProducto.getSelectedItem()) == false) {
                    try {
                        Thread.currentThread().sleep(500);
                        cmbProducto.setSelectedItem(prod);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }).start();

        tmEtapaEspecifica.setDatos(new ArrayList<EtapaProduccionEspecifica>(prod.getTEtapasProduccionEspecificas()));
    }

    public void cargarEtapa(EtapaProduccionEspecifica ep) {
        txtDescripcion.setText(Utilidades.parseString(ep.getDescripcion()));
        txtHorasHombre.setText(Utilidades.parseString(ep.getHorasHombre()));
        txtOrden.setText(Utilidades.parseString(ep.getNumeroOrden()));
        cmbCargo.setSelectedItem(ep.getCargo());
        cmbTipoEtapa.setSelectedItem(ep.getEtapaProduccion());
        tmDetalleEtapa.setDatos(ep.getDetalleEtapaProduccion());
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
        cmbCargo.setSelectedIndex(-1);
        cmbTipoEtapa.setSelectedIndex(-1);
        tmDetalleEtapa.setDatos(new ArrayList<DetalleEtapaProduccion>());
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
        jButton8 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        cmbTipoEtapa = new javax.swing.JComboBox();
        txtHorasHombre = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cmbCargo = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtOrden = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        btnAceptarEtapa = new javax.swing.JButton();
        btnCancelarEtapa = new javax.swing.JButton();
        cmbTipoMaquina = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        cmbMaterial = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtRepeticiones = new javax.swing.JTextField();
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

        pnlEtapaEspecifica.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton8.setText("*");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        txtDescripcion.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        jScrollPane4.setViewportView(txtDescripcion);

        jLabel13.setText("Horas Hombre:");

        jLabel14.setText("Cargo:");

        jLabel17.setText("Nro orden:");

        jLabel16.setText("Tipo Etapa:");

        jButton14.setText("*");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel15.setText("Descripcion:");

        btnAceptarEtapa.setText("Aceptar");
        btnAceptarEtapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarEtapaActionPerformed(evt);
            }
        });

        btnCancelarEtapa.setText("Cancelar");
        btnCancelarEtapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarEtapaActionPerformed(evt);
            }
        });

        jLabel18.setText("Tipo Maquina:");

        jLabel19.setText("Material:");

        jLabel20.setText("Repeticiones:");

        javax.swing.GroupLayout pnlEtapaEspecificaLayout = new javax.swing.GroupLayout(pnlEtapaEspecifica);
        pnlEtapaEspecifica.setLayout(pnlEtapaEspecificaLayout);
        pnlEtapaEspecificaLayout.setHorizontalGroup(
            pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEtapaEspecificaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEtapaEspecificaLayout.createSequentialGroup()
                        .addGroup(pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmbCargo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbTipoEtapa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtHorasHombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4))
                .addGap(77, 77, 77)
                .addGroup(pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlEtapaEspecificaLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbTipoMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEtapaEspecificaLayout.createSequentialGroup()
                        .addGroup(pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtRepeticiones)
                            .addComponent(cmbMaterial, 0, 164, Short.MAX_VALUE)))
                    .addGroup(pnlEtapaEspecificaLayout.createSequentialGroup()
                        .addComponent(btnAceptarEtapa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelarEtapa)))
                .addContainerGap())
        );
        pnlEtapaEspecificaLayout.setVerticalGroup(
            pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEtapaEspecificaLayout.createSequentialGroup()
                .addGroup(pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cmbTipoEtapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14)
                    .addComponent(jLabel18)
                    .addComponent(cmbTipoMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(cmbMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cmbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8)
                    .addComponent(jLabel20)
                    .addComponent(txtRepeticiones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtHorasHombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEtapaEspecificaLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelarEtapa)
                            .addComponent(btnAceptarEtapa)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)))
        );

        pnlProducto.setBorder(javax.swing.BorderFactory.createTitledBorder("Producto"));

        jLabel1.setText("Producto:");

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

        pnlEtapas.setBorder(javax.swing.BorderFactory.createTitledBorder("Etapas de produccion"));

        tbEtapas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbEtapas);

        btnSubir.setText("Subir");

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

        btnNuevo.setText("Nuevo");
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlEtapaEspecifica, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar))
                    .addComponent(pnlEtapas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEtapas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlEtapaEspecifica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
        if (Mensajes.mensajeConfirmacionGenerico("¿Realmente desea eliminar este elemento?")) {
            tmEtapaEspecifica.removeSelectedRow();
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBajarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBajarActionPerformed

    private void cmbTipoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoProductoActionPerformed

    private void cmbProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProductoActionPerformed
        // TODO add your handling code here:
        if (gestor.getProducto() != null) {
            if (Mensajes.mensajeConfirmacionGenerico(
                    "Si cambia el producto seleccionado la informacion que no se ha guardado sera eliminada,¿Desea comtinuar?")) {
                gestor.iniciarCU((Producto) cmbProducto.getSelectedItem());
            }
        }

    }//GEN-LAST:event_cmbProductoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        gestor.finalizarCU();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try {
            // TODO add your handling code here:
            gestor.ejecutarCU((ArrayList<EtapaProduccionEspecifica>) tmEtapaEspecifica.getDatos());
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
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarEtapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarEtapaActionPerformed
        // TODO add your handling code here:
        limpiarEtapa();
        habilitarCarga(false);
    }//GEN-LAST:event_btnCancelarEtapaActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        habilitarCarga(true);
        modificar = true;
        cargarEtapa(tmEtapaEspecifica.getSeletedObject());
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnAceptarEtapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarEtapaActionPerformed
        // TODO add your handling code here:
        EtapaProduccionEspecifica etapa;

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
        etapa.setHorasHombre(new BigDecimal(txtHorasHombre.getText()));
        etapa.setNumeroOrden(Utilidades.parseByte(txtOrden.getText()));
        etapa.setDetalleEtapaProduccion(tmDetalleEtapa.getDatos());
        etapa.setProducto(gestor.getProducto());

        if (nuevo) {
            tmEtapaEspecifica.add(etapa);
        }

        tmEtapaEspecifica.updateTabla();
        modificar = false;
        nuevo = false;

        limpiarEtapa();
        habilitarCarga(false);


    }//GEN-LAST:event_btnAceptarEtapaActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        new GestorEtapaProduccion().administarTipoProducto(this);
        cmbTipoEtapa.setModel(new DefaultComboBoxModel(gestor.listarEtapasGenericas().toArray()));

    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        new GestorCargo().administarCargo(this);
        cmbCargo.setModel(new DefaultComboBoxModel(gestor.listarCargos().toArray()));
    }//GEN-LAST:event_jButton8ActionPerformed

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
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSubir;
    private javax.swing.JComboBox cmbCargo;
    private javax.swing.JComboBox cmbMaterial;
    private javax.swing.JComboBox cmbProducto;
    private javax.swing.JComboBox cmbTipoEtapa;
    private javax.swing.JComboBox cmbTipoMaquina;
    private javax.swing.JComboBox cmbTipoProducto;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel pnlEtapaEspecifica;
    private javax.swing.JPanel pnlEtapas;
    private javax.swing.JPanel pnlProducto;
    private javax.swing.JTable tbEtapas;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtHorasHombre;
    private javax.swing.JTextField txtOrden;
    private javax.swing.JTextField txtRepeticiones;
    // End of variables declaration//GEN-END:variables
}
