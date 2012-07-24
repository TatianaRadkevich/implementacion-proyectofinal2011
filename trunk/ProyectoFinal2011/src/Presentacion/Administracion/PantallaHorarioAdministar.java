/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaMaquinaHerramientaTipoABM.java
 *
 * Created on 22/08/2011, 13:09:19
 */
package Presentacion.Administracion;

import Negocio.Administracion.AsignacionesDias;
import Negocio.Administracion.Dia;
import Negocio.Administracion.GestorHorario;
import Negocio.Administracion.Horarios;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Mensajes;
import Presentacion.TablaManager;
import Presentacion.Utilidades;
import Presentacion.ValidarTexbox;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Rodrigo
 */
public class PantallaHorarioAdministar extends javax.swing.JDialog {

    private GestorHorario gestor;
    private TablaManager<AsignacionesDias> tmAsignacion;
    private boolean banderaNuevo=true;

    /** Creates new form PantallaMaquinaHerramientaTipoABM */
    public PantallaHorarioAdministar(GestorHorario g) {
        super((java.awt.Frame) null, true);
        initComponents();
        gestor = g;
        inicializarTabla();
        //GUILocal.establecerGUILocal(this);

        cmbDia.setModel(new DefaultComboBoxModel(gestor.listarDias().toArray()));
        habilitarCampos(false);
        cargarValidaciones();
        inicializarLista();
        cargarLista();
        limpiar();
    }

    private void inicializarTabla()
    {
            tmAsignacion = new TablaManager<AsignacionesDias>(tbDetalle) {

            @Override
            public Vector ObjetoFila(AsignacionesDias elemento) {
                Vector salida = new Vector();
                salida.add(elemento.getTDias().getNombre());
                salida.add(Utilidades.parseHora(elemento.getHoraDesde()));
                salida.add(Utilidades.parseHora(elemento.getHoraHasta()));
                return salida;
            }

            @Override
            public Vector getCabecera() {
                Vector salida = new Vector();
                salida.add("Día");
                salida.add("Desde");
                salida.add("Hasta");
                return salida;
            }
        };
        tmAsignacion.addSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                cargarDetalle(tmAsignacion.getSeletedObject());
            }
        });
    }

    public void inicializarLista() {
        lstDisponible.setModel(new DefaultListModel());
        lstDisponible.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {

                    public void valueChanged(ListSelectionEvent e) {
                        if (lstDisponible.getSelectedIndex() >= 0) {
                            cargar((Horarios) lstDisponible.getSelectedValue());
                        } else {
                            limpiar();
                        }
                    }
                });
    }

    public void cargarValidaciones() {
        ValidarTexbox.validarLongitud(txtNombre, 50);
        ValidarTexbox.validarLongitud(txtDescripcion, 200);

        /************************Validacion de botones **********************************/
        btnNuevo.setEnabled(true);
        btnModificar.setEnabled(false);
        btnBaja.setEnabled(false);
        lstDisponible.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {

                    public void valueChanged(ListSelectionEvent e) {
                        if (lstDisponible.getSelectedIndex() >= 0) {
                            btnBaja.setEnabled(true);
                            btnModificar.setEnabled(true);
                        } else {
                            btnBaja.setEnabled(false);
                            btnModificar.setEnabled(false);
                        }
                    }
                });
    }

    public void cargarLista() {
        DefaultListModel model = (DefaultListModel) lstDisponible.getModel();
        model.removeAllElements();
        for (Horarios tmh : gestor.listarHorarios()) {
            model.addElement(tmh);
        }
        lstDisponible.setModel(model);
    }

    public void limpiar() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        limpiarDetalle();
        tmAsignacion.limpiar();
    }

    public void limpiarDetalle() {
        cmbDia.setSelectedIndex(-1);
        txtDesde.setText("");
        txtHasta.setText("");
    }

    public void cargar(Horarios tmh) {
        txtNombre.setText(tmh.getNombre());
        txtDescripcion.setText(tmh.getDescripcion());
        tmAsignacion.setDatos(tmh.getTAsignacionesDiases());
    }

    public void cargarDetalle(AsignacionesDias as) {
        if(as==null)
        {
            limpiarDetalle();
            return;
        }
        cmbDia.setSelectedItem(as.getTDias());
        txtDesde.setValue(as.getHoraDesde());
        txtHasta.setValue(as.getHoraHasta());

    }

    public void habilitarCampos(boolean b) {
        btnSalir.setEnabled(!b);
        btnModificar.setEnabled(!b);
        btnNuevo.setEnabled(!b);
        btnBaja.setEnabled(!b);
        //btnAlta.setEnabled(!b);
        lstDisponible.setEnabled(!b);

        txtNombre.setEnabled(b);
        txtDescripcion.setEnabled(b);
        Utilidades.habilitarPanel(pnlDetalle, b);
        Utilidades.habilitarPanel(pnlDetalleCarga, false);

        btnAceptar.setEnabled(b);
        btnCancelar.setEnabled(b);
    }

    public void habilitarDetalle(boolean b) {

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgTipo = new javax.swing.ButtonGroup();
        pnlDisponible = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstDisponible = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();
        pnlCargo = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlDetalle = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbDetalle = new javax.swing.JTable();
        pnlDetalleCarga = new javax.swing.JPanel();
        cmbDia = new javax.swing.JComboBox();
        btnAceptarDetalle = new javax.swing.JButton();
        btnCancelarDetalle = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDesde = new javax.swing.JFormattedTextField();
        txtHasta = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        btnNuevoDetalle = new javax.swing.JButton();
        btnModificarDetalle = new javax.swing.JButton();
        btnEliminarDetalle = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Horario");

        pnlDisponible.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Disponibles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jScrollPane2.setViewportView(lstDisponible);

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnBaja.setText("Eliminar");
        btnBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
            .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnBaja, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBaja)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlDisponibleLayout = new javax.swing.GroupLayout(pnlDisponible);
        pnlDisponible.setLayout(pnlDisponibleLayout);
        pnlDisponibleLayout.setHorizontalGroup(
            pnlDisponibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDisponibleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlDisponibleLayout.setVerticalGroup(
            pnlDisponibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDisponibleLayout.createSequentialGroup()
                .addGroup(pnlDisponibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlCargo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Horario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Nombre:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Descripción:");

        txtDescripcion.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtDescripcion);

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

        pnlDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Día", "Desde", "Hasta"
            }
        ));
        jScrollPane3.setViewportView(tbDetalle);

        pnlDetalleCarga.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cmbDia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" }));

        btnAceptarDetalle.setText("Aceptar");
        btnAceptarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarDetalleActionPerformed(evt);
            }
        });

        btnCancelarDetalle.setText("Cancelar");
        btnCancelarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarDetalleActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Hasta:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Desde:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Día:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("hs.");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("hs.    ");

        txtDesde.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm"))));

        txtHasta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm"))));

        javax.swing.GroupLayout pnlDetalleCargaLayout = new javax.swing.GroupLayout(pnlDetalleCarga);
        pnlDetalleCarga.setLayout(pnlDetalleCargaLayout);
        pnlDetalleCargaLayout.setHorizontalGroup(
            pnlDetalleCargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetalleCargaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbDia, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptarDetalle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarDetalle)
                .addContainerGap())
        );
        pnlDetalleCargaLayout.setVerticalGroup(
            pnlDetalleCargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleCargaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetalleCargaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptarDetalle)
                    .addComponent(btnCancelarDetalle)
                    .addComponent(jLabel5)
                    .addComponent(cmbDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnNuevoDetalle.setText("Nuevo");
        btnNuevoDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoDetalleActionPerformed(evt);
            }
        });

        btnModificarDetalle.setText("Modificar");
        btnModificarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarDetalleActionPerformed(evt);
            }
        });

        btnEliminarDetalle.setText("Eliminar");
        btnEliminarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarDetalleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnModificarDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevoDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevoDetalle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificarDetalle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarDetalle)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDetalleCarga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetalleLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addComponent(pnlDetalleCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlCargoLayout = new javax.swing.GroupLayout(pnlCargo);
        pnlCargo.setLayout(pnlCargoLayout);
        pnlCargoLayout.setHorizontalGroup(
            pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCargoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlCargoLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
                    .addGroup(pnlCargoLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCargoLayout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );
        pnlCargoLayout.setVerticalGroup(
            pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCargoLayout.createSequentialGroup()
                .addGroup(pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSalir)
                    .addComponent(pnlDisponible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlCargo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDisponible, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnSalir)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed
        // TODO add your handling code here:  
        gestor.iniciarBaja((Horarios) lstDisponible.getSelectedValue());

}//GEN-LAST:event_btnBajaActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        gestor.iniciarModificar((Horarios) lstDisponible.getSelectedValue());
         Utilidades.habilitarPanel(pnlCargo, true);
        Utilidades.habilitarPanel(pnlDetalleCarga, false);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        Horarios elemento = gestor.getElemento();
        elemento.setNombre(txtNombre.getText());
        elemento.setDescripcion(txtDescripcion.getText());
        elemento.setTAsignacionesDiases(tmAsignacion.getDatos());
        
        try {
            gestor.ejecutarCU(elemento);
            cargarLista();
        } catch (ExceptionGestor ex) {
            Mensajes.mensajeErrorGenerico(ex.getMessage());
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        gestor.finalizarCU();
}//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        gestor.iniciarNuevo();
        Utilidades.habilitarPanel(pnlCargo, true);
        Utilidades.habilitarPanel(pnlDetalleCarga, false);

}//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCancelarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarDetalleActionPerformed
        // TODO add your handling code here:
        limpiarDetalle();
        Utilidades.habilitarPanel(pnlDetalle, true);
        Utilidades.habilitarPanel(pnlDetalleCarga, false);

    }//GEN-LAST:event_btnCancelarDetalleActionPerformed

    private void btnNuevoDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoDetalleActionPerformed
        // TODO add your handling code here:
        Utilidades.habilitarPanel(pnlDetalle, false);
        Utilidades.habilitarPanel(pnlDetalleCarga, true);
        limpiarDetalle();
        banderaNuevo=true;
    }//GEN-LAST:event_btnNuevoDetalleActionPerformed

    private void btnModificarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarDetalleActionPerformed
        // TODO add your handling code here:
        Utilidades.habilitarPanel(pnlDetalle, false);
        Utilidades.habilitarPanel(pnlDetalleCarga, true);
        banderaNuevo=false;

    }//GEN-LAST:event_btnModificarDetalleActionPerformed

    private void btnEliminarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarDetalleActionPerformed
        // TODO add your handling code here:
        if(Mensajes.mensajeConfirmacionGenerico("¿Desea eliminar el elemento seleccionado?"))
            tmAsignacion.removeSelectedRow();

    }//GEN-LAST:event_btnEliminarDetalleActionPerformed

    private void btnAceptarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarDetalleActionPerformed
        // TODO add your handling code here:

        AsignacionesDias ad;
        
        if(banderaNuevo)
            ad=new AsignacionesDias();
        else
            ad=tmAsignacion.getSeletedObject();

        ad.setTDias((Dia) cmbDia.getSelectedItem());
        ad.setHoraDesde((Date) txtDesde.getValue());
        ad.setHoraHasta((Date) txtHasta.getValue());

        if(banderaNuevo)
            tmAsignacion.add(ad);
        else
            tmAsignacion.updateTabla();
        
        limpiarDetalle();
        Utilidades.habilitarPanel(pnlDetalle, true);
        Utilidades.habilitarPanel(pnlDetalleCarga, false);

    }//GEN-LAST:event_btnAceptarDetalleActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new GestorHorario().administar();
               // new HorarioAdministracionPantalla(new GestorHorarios()).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgTipo;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAceptarDetalle;
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarDetalle;
    private javax.swing.JButton btnEliminarDetalle;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnModificarDetalle;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevoDetalle;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cmbDia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList lstDisponible;
    private javax.swing.JPanel pnlCargo;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlDetalleCarga;
    private javax.swing.JPanel pnlDisponible;
    private javax.swing.JTable tbDetalle;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JFormattedTextField txtDesde;
    private javax.swing.JFormattedTextField txtHasta;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
