/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaCalendarioEmpleado.java
 *
 * Created on 02/10/2011, 21:22:42
 */
package Presentacion.Administracion;

import BaseDeDatos.Administracion.EmpleadoBD;
import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.AsignacionesDias;
import Negocio.Administracion.AsignacionesHorario;
import Negocio.Administracion.AsistenciaEmpleado;
import Negocio.Administracion.Dia;
import Negocio.Administracion.DiaHoraLaborable;
import Negocio.Administracion.Empleado;
//import Negocio.Administracion.GestorDiaHoraLaborable;
import Negocio.Administracion.Horarios;
import Negocio.Exceptiones.NegocioException;
import Presentacion.Mensajes;
import Presentacion.TablaManager;
import Presentacion.Utilidades;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.hibernate.Hibernate;

/**
 *
 * @author Ivan
 */
public class PantallaHorarioAsignarEmpleado extends javax.swing.JDialog {

    private Empleado empleadoSel;
    private AsignacionesHorario asignacionSel;
    private TablaManager<Empleado> tmEmpleados;
    private TablaManager<AsignacionesHorario> tmAsignaciones;
    private TablaManager<AsignacionesDias> tmHorario;

    private enum Estado {

        Nuevo, Modificar, Ninguno;
    };
    private Estado estadoActual;

//    private GestorDiaHoraLaborable gestor=new GestorDiaHoraLaborable();
    /** Creates new form PantallaCalendarioEmpleado */
    public PantallaHorarioAsignarEmpleado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        configurarControles();
        tmEmpleados.setDatos(EmpleadoBD.getEmpleadosVigentes());
        Utilidades.comboCargar(cmbHorario, Horarios.getAllHorarios());
        estadoActual = Estado.Ninguno;
        btnModificar.setVisible(false);

    }

    private void configurarControles() {
        tmEmpleados = new TablaManager<Empleado>(tbEmpleado) {

            @Override
            public Vector ObjetoFila(Empleado elemento) {
                Vector salida = new Vector();
                salida.add(elemento.getId());
                salida.add(elemento.getNombre());
                salida.add(elemento.getApellido());
                if (elemento.getAsignacionHorariaVigente() != null) {
                    salida.add(elemento.getAsignacionHorariaVigente().getHorario());
                } else {
                    salida.add("N/a");
                }
                return salida;
            }

            @Override
            public Vector getCabecera() {
                Vector salida = new Vector();
                salida.add("Legajo");
                salida.add("Nombre");
                salida.add("Apellido");
                salida.add("Horario Vig.");
                return salida;
            }
        };
        tmEmpleados.addSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                setAsignacionHorarioEmpleado(tmEmpleados.getSeletedObject());
            }
        });



        tmAsignaciones = new TablaManager<AsignacionesHorario>(tbAsignaciones) {

            @Override
            public Vector ObjetoFila(AsignacionesHorario elemento) {
                Vector salida = new Vector();
                salida.add(elemento.getHorario());
                salida.add(Utilidades.parseFecha(elemento.getFecDesde()));
                salida.add(Utilidades.parseFecha(elemento.getFecHasta()));
                return salida;
            }

            @Override
            public Vector getCabecera() {
                Vector salida = new Vector();
                salida.add("Horario");
                salida.add("Inicio Vig.");
                salida.add("Fin Vig.");
                return salida;
            }
        };

        tmAsignaciones.addSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                setAsignacionHorario(tmAsignaciones.getSeletedObject());
            }
        });

        tmHorario = new TablaManager<AsignacionesDias>(tbHorario) {

            @Override
            public Vector ObjetoFila(AsignacionesDias elemento) {
                Vector salida = new Vector();
                salida.add(elemento.getTDias());
                salida.add(Utilidades.parseHora(elemento.getHoraDesde()));
                salida.add(Utilidades.parseHora(elemento.getHoraHasta()));
                return salida;
            }

            @Override
            public Vector getCabecera() {
                Vector salida = new Vector();
                salida.add("Dia");
                salida.add("Desde");
                salida.add("Hasta");
                return salida;
            }
        };
    }

    private void setAsignacionHorario(AsignacionesHorario ah) {
        if (ah == null) {
            dtcFechaInicio.setDate(null);
            dtcFechaFin.setDate(null);
            cmbHorario.setSelectedItem(null);
            this.asignacionSel = null;
            //tmHorario.setDatos(new ArrayList<AsignacionesDias>());
            return;
        }
        dtcFechaInicio.setDate(ah.getFecDesde());
        dtcFechaFin.setDate(ah.getFecHasta());
        cmbHorario.setSelectedItem(ah.getHorario());
        this.asignacionSel = ah;
    }

    private void setAsignacionHorarioEmpleado(Empleado e) {
        if (e == null) {
            return;
        }
        tmAsignaciones.setDatos(e.getAsignacionHorariasProximas());
        empleadoSel = e;

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBuscarEmpleado = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbEmpleado = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        pnlAdministrarHorario = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAsignaciones = new javax.swing.JTable();
        pnlBotones = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        pnlAsignacionHorario = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        dtcFechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        dtcFechaFin = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbHorario = new javax.swing.JTable();
        cmbHorario = new javax.swing.JComboBox();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Asignar Horario a Empleado");

        pnlBuscarEmpleado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar Empleados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Empleados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Legajo", "Apellido", "Nombre", "Horario Vigente"
            }
        ));
        jScrollPane3.setViewportView(tbEmpleado);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlBuscarEmpleadoLayout = new javax.swing.GroupLayout(pnlBuscarEmpleado);
        pnlBuscarEmpleado.setLayout(pnlBuscarEmpleadoLayout);
        pnlBuscarEmpleadoLayout.setHorizontalGroup(
            pnlBuscarEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlBuscarEmpleadoLayout.setVerticalGroup(
            pnlBuscarEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Administrar asignacion de Horarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        pnlAdministrarHorario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Horaios Asignados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbAsignaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Horario", "Inicio Vig.", "Fin Vig."
            }
        ));
        jScrollPane1.setViewportView(tbAsignaciones);

        btnNuevo.setText("+");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnModificar.setText("*");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlAdministrarHorarioLayout = new javax.swing.GroupLayout(pnlAdministrarHorario);
        pnlAdministrarHorario.setLayout(pnlAdministrarHorarioLayout);
        pnlAdministrarHorarioLayout.setHorizontalGroup(
            pnlAdministrarHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAdministrarHorarioLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlAdministrarHorarioLayout.setVerticalGroup(
            pnlAdministrarHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
            .addGroup(pnlAdministrarHorarioLayout.createSequentialGroup()
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        pnlAsignacionHorario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Asignacion Horario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

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

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Horario:");

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vigencia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("Fin:");

        dtcFechaInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dtcFechaInicioMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Inico:");

        dtcFechaFin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dtcFechaFinMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtcFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtcFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6)
            .addComponent(dtcFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel7)
            .addComponent(dtcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        tbHorario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dia", "Desde", "Hasta"
            }
        ));
        jScrollPane2.setViewportView(tbHorario);

        cmbHorario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cmbHorarioMousePressed(evt);
            }
        });
        cmbHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHorarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAsignacionHorarioLayout = new javax.swing.GroupLayout(pnlAsignacionHorario);
        pnlAsignacionHorario.setLayout(pnlAsignacionHorarioLayout);
        pnlAsignacionHorarioLayout.setHorizontalGroup(
            pnlAsignacionHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAsignacionHorarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAsignacionHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlAsignacionHorarioLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAsignacionHorarioLayout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlAsignacionHorarioLayout.setVerticalGroup(
            pnlAsignacionHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAsignacionHorarioLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlAsignacionHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlAsignacionHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar)))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(pnlAdministrarHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAsignacionHorario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAdministrarHorario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlAsignacionHorario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBuscarEmpleado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBuscarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalir)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dtcFechaInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtcFechaInicioMouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_dtcFechaInicioMouseClicked

    private void dtcFechaFinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtcFechaFinMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dtcFechaFinMouseClicked

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        setAsignacionHorario(null);
        Utilidades.habilitarPanel(pnlAsignacionHorario, false);
      
        estadoActual = Estado.Ninguno;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:

        ArrayList<AsignacionesHorario> l = this.empleadoSel.getAsignacionHorariasProximas();
        Date f;
        if (l.isEmpty() == false) {
            f = Utilidades.agregarTiempoFecha(l.get(l.size() - 1).getFecHasta(), 1, 0, 0);
        } else {
            f = Utilidades.getFechaActual();
        }

        AsignacionesHorario ah = new AsignacionesHorario();
        ah.setFecDesde(f);

        setAsignacionHorario(ah);
        estadoActual = Estado.Nuevo;
        Utilidades.habilitarPanel(pnlAsignacionHorario, true);
        dtcFechaInicio.setEnabled(false);
        //Utilidades.habilitarPanel(pnlAdministrarHorario, false);

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:

        try {
            this.asignacionSel.setFecDesde(dtcFechaInicio.getDate());
            this.asignacionSel.setFecHasta(dtcFechaFin.getDate());
            this.asignacionSel.setTHorarios((Horarios) cmbHorario.getSelectedItem());

            if (estadoActual == Estado.Nuevo) {
                this.empleadoSel.addAsignacionHorario(this.asignacionSel);
            } else if (estadoActual == Estado.Modificar) {
                this.empleadoSel.updateAsignacionHorario(this.asignacionSel);
            } else {
                //nunca debe llegar
                throw new NegocioException("Error: estadi invalido");
            }

            HibernateUtil.modificarObjeto(this.empleadoSel);
            //////////////////////
            setAsignacionHorario(null);
            Utilidades.habilitarPanel(pnlAsignacionHorario, false);
            estadoActual = Estado.Ninguno;
            tmAsignaciones.setDatos(this.empleadoSel.getAsignacionHorariasProximas());
            
        } catch (NegocioException ne) {
            Mensajes.mensajeErrorGenerico(ne.getMessage());
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:

        AsignacionesHorario ah = tmAsignaciones.getSeletedObject();
        if (ah != null) {
            setAsignacionHorario(ah);
            estadoActual = Estado.Modificar;
            Utilidades.habilitarPanel(pnlAsignacionHorario, true);
            dtcFechaInicio.setEnabled(false);

        } else {
            Mensajes.mensajeErrorGenerico("Debe seleccionar el elemento que desea modificar");
        }

    }//GEN-LAST:event_btnModificarActionPerformed

    private void cmbHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHorarioActionPerformed
        // TODO add your handling code here:

        Horarios h=(Horarios) cmbHorario.getSelectedItem();
        if(h!=null)
        {
            tmHorario.setDatos(h.getTAsignacionesDiases());
        }    

    }//GEN-LAST:event_cmbHorarioActionPerformed

    private void cmbHorarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbHorarioMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbHorarioMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cmbHorario;
    private com.toedter.calendar.JDateChooser dtcFechaFin;
    private com.toedter.calendar.JDateChooser dtcFechaInicio;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnlAdministrarHorario;
    private javax.swing.JPanel pnlAsignacionHorario;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlBuscarEmpleado;
    private javax.swing.JTable tbAsignaciones;
    private javax.swing.JTable tbEmpleado;
    private javax.swing.JTable tbHorario;
    // End of variables declaration//GEN-END:variables
}
