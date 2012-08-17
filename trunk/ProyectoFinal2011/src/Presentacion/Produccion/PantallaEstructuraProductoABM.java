package Presentacion.Produccion;

import Negocio.Administracion.Cargo;
import Negocio.Administracion.GestorCargo;
import Negocio.Compras.Material;
import Negocio.Exceptiones.ExceptionGestor;
import Negocio.Produccion.*;
import Presentacion.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author Heber Parrucci
 */
public class PantallaEstructuraProductoABM extends javax.swing.JDialog {

    private boolean nuevo = false, modificar = false;
    private GestorEstructura gestor;
    private TablaManager<EtapaProduccionEspecifica> tmEtapaEspecifica;
    private TablaManager<DetalleEtapaProduccion> tmHerramientas;
    private TablaManager<DetalleEtapaProduccion> tmMateriales;
    private JComboBox cmbMaterial;
    private JComboBox cmbHerramienta;

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
                fila.add(elemento.getDuracion());
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

//        tmComposicion = new TablaManager<DetalleEtapaProduccion>(tbComposicion) {
//
//            @Override
//            public Vector ObjetoFila(DetalleEtapaProduccion elemento) {
//                Vector fila = new Vector();
//                fila.add(elemento.getEtapaProduccionEspecifica().getNumeroOrden());
//                fila.add(elemento.getTDetallesProducto().getTMateriales().getNombre());
//                fila.add(elemento.getCantidadNecesaria());
//                fila.add(elemento.getTDetallesProducto().getTMateriales().getUnidadMedida().getNombre());;
//                return fila;
//            }
//
//            @Override
//            public Vector getCabecera() {
//                Vector cabecera = new Vector();
//                cabecera.add("Nro Etapa");
//                cabecera.add("Material");
//                cabecera.add("Cantidad");
//                cabecera.add("Unidad");
//
//                return cabecera;
//            }
//        };

        iniciarTablaMaterial();
        iniciarTablaHerramientas();

        cargarCombos();
        cargarValidaciones();

    }

    private void iniciarTablaHerramientas() {
        tmHerramientas = new TablaManager<DetalleEtapaProduccion>(tbHerramientas) {

            @Override
            public Vector ObjetoFila(DetalleEtapaProduccion elemento) {
                Vector fila = new Vector();
                fila.add(elemento.getTipoMaquinaHerramienta());
                fila.add((elemento.getCantidadNecesaria() == null) ? "" : elemento.getCantidadNecesaria());
                return fila;
            }

            @Override
            public Vector getCabecera() {
                Vector cabecera = new Vector();
                cabecera.add("Herramienta");
                cabecera.add("Cantidad");
                return cabecera;
            }

            @Override
            public boolean isCellEditable(int columna) {
                return true;
            }

            @Override
            public void fireTableCellUpdated(int row, int column) {
                Object value = tbHerramientas.getValueAt(row, column);
                if (column == 0) {
                    tmHerramientas.getDato(row).setTipoMaquinaHerramienta((TipoHerramienta) value);
                }
                if (column == 1) {
                    tmHerramientas.getDato(row).setCantidadNecesaria(new Integer(value.toString()));
                }
            }
        };
        cmbHerramienta = new JComboBox();
        tbHerramientas.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cmbHerramienta));        
        JTextField txt = new JTextField();
        ValidarTexbox.validarInt(txt);
        ValidarTexbox.validarLongitud(txt, 3);
        tbHerramientas.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(txt));        
        tbHerramientas.setRowHeight((int) cmbHerramienta.getPreferredSize().getHeight());
    }

    private void iniciarTablaMaterial() {
        tmMateriales = new TablaManager<DetalleEtapaProduccion>(tbMaterial) {

            @Override
            public Vector ObjetoFila(DetalleEtapaProduccion elemento) {
                Vector fila = new Vector();
                if(elemento.getTDetallesProducto() == null)
                {
                    fila.add(null);
                    fila.add(1);
                    fila.add("");
                }
                else {
                    fila.add(elemento.getTDetallesProducto().getTMateriales());
                    fila.add((elemento.getCantidadNecesaria() == null) ? "" : elemento.getCantidadNecesaria());
                    fila.add((elemento.getTDetallesProducto().getTMateriales() == null) ?
                    "" : elemento.getTDetallesProducto().getTMateriales().getUnidadMedida());
                }

                return fila;
            }

            @Override
            public Vector getCabecera() {
                Vector cabecera = new Vector();
                cabecera.add("Material");
                cabecera.add("Cantidad");
                cabecera.add("Unidad");
                return cabecera;
            }

            @Override
            public boolean isCellEditable(int columna) {
                if(columna==2)
                    return false;
                return true;
            }

            @Override
            public void fireTableCellUpdated(int row, int column) {
                Object value = tbMaterial.getValueAt(row, column);
                if (column == 0) {
                    if(tmMateriales.getDato(row).getTDetallesProducto() == null)
                        tmMateriales.getDato(row).setTDetallesProducto(new DetalleProducto());
                    tmMateriales.getDato(row).getTDetallesProducto().setTMateriales((Material) value);
                    tmMateriales.getDato(row).getTDetallesProducto().setTProductos((Producto)cmbProducto.getSelectedItem());
                    tmMateriales.getDato(row).getTDetallesProducto().setLongitud((short)0);
                }
                if (column == 1) {
                    tmMateriales.getDato(row).setCantidadNecesaria(new Integer(value.toString()));
                }
            }
        };
        cmbMaterial = new JComboBox();
        cmbMaterial.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int index=tmMateriales.getSelectedRow();
                tmMateriales.updateTabla();
                tmMateriales.setSelectedRow(index);
            }
        });
        tbMaterial.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cmbMaterial));
        JTextField txt = new JTextField();
        ValidarTexbox.validarInt(txt);
        ValidarTexbox.validarLongitud(txt, 3);
        tbMaterial.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(txt));
        tbMaterial.setRowHeight((int) cmbMaterial.getPreferredSize().getHeight());


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
        cmbTipoMaquina.setModel(new DefaultComboBoxModel(gestor.listarTipoMaquinas().toArray()));
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
        cmbHerramienta.setModel(new DefaultComboBoxModel(gestor.listarTipoHerramientas().toArray()));
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

        Utilidades.habilitarPanel(pnlEtapaEspecifica, valor);

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

        actualizarContenido();



    }

    public void cargarEtapa(EtapaProduccionEspecifica ep) {
        if (ep == null) {
            limpiarEtapa();
            return;
        }

        txtDescripcion.setText(Utilidades.parseString(ep.getDescripcion()));
        txtHorasHombre.setText(ep.getHorasHombre().intValueExact()+"");
        txtDuracion.setText(ep.getDuracion() +"");

        txtOrden.setText(Utilidades.parseString(ep.getNumeroOrden()));


        cmbCargo.setSelectedItem(ep.getCargo());
        cmbTipoEtapa.setSelectedItem(ep.getEtapaProduccion());


        List<DetalleEtapaProduccion> materiales = new ArrayList<DetalleEtapaProduccion>();
        List<DetalleEtapaProduccion> herramientas = new ArrayList<DetalleEtapaProduccion>();

        for (DetalleEtapaProduccion det : ep.getDetalleEtapaProduccion()) {
            if (ep.getTTmaquina() != null) {
                cmbTipoMaquina.setSelectedItem(ep.getTTmaquina());
            }
            if (det.getTipoMaquinaHerramienta() != null) {
                herramientas.add(det);
            }
            if (det.getTDetallesProducto()!= null) {
                materiales.add(det);
            }
         
        }
        tmHerramientas.setDatos(herramientas);
        tmMateriales.setDatos(materiales);
      

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
//        cmbTiempoDurEtapa.setSelectedIndex(-1);
//        cmbTiempoHorasHom.setSelectedIndex(-1);
        txtDuracion.setText("");
        cmbCargo.setSelectedIndex(-1);
        cmbTipoEtapa.setSelectedIndex(-1);
        cmbMaterial.setSelectedIndex(-1);
        tmMateriales.setDatos(new ArrayList<DetalleEtapaProduccion>());
        tmHerramientas.setDatos(new ArrayList<DetalleEtapaProduccion>());
        cmbTipoMaquina.setSelectedIndex(-1);

    }

    public void recalcularNroOrden() {
        for (byte i = 0; i < tmEtapaEspecifica.getSize(); i++) {
            tmEtapaEspecifica.getDato(i).setNumeroOrden((byte) (1 + i));
        }
        tmEtapaEspecifica.updateTabla();

    }

    private void actualizarContenido() {

    }

     public void setVisibleBuscadorProducto(boolean b) {
        pnlProducto.setVisible(b);
        this.pack();
    }

    private boolean validarEtapa()
    {
        String mensaje="";

        Utilidades.componenteCorrecto(cmbTipoEtapa);
        Utilidades.componenteCorrecto(cmbCargo);
        Utilidades.componenteCorrecto(cmbTipoMaquina);
        Utilidades.componenteCorrecto(txtDuracion);
        Utilidades.componenteCorrecto(txtHorasHombre);

        if(cmbTipoEtapa.getSelectedIndex()==-1)
        {
            mensaje+="\nDebe seleccionar un tipo de etapa";
            Utilidades.componenteError(cmbTipoEtapa, "Debe seleccionar un tipo de etapa");
        }
        if(cmbCargo.getSelectedIndex()==-1)
        {
            mensaje+="\nDebe seleccionar un cargo correcto";
            Utilidades.componenteError(cmbCargo, "Debe seleccionar un cargo correcto");
        }
        if(cmbTipoMaquina.getSelectedIndex()==-1)
        {
            mensaje+="\nDebe seleccionar un tipo de maquina correcto";
            Utilidades.componenteError(cmbTipoMaquina, "Debe seleccionar un tipo de maquina correcto");
        }
        if(txtDuracion.getText().trim().isEmpty())
        {
            mensaje+="\nDebe ingresar una duración determinada";
            Utilidades.componenteError(txtDuracion, "Debe ingresar una duración determinada");
        }
        if(txtHorasHombre.getText().trim().isEmpty())
        {
            mensaje+="\nDebe ingresar las horas hombre requeridas";
            Utilidades.componenteError(txtHorasHombre, "Debe ingresar las horas hombre requeridas");
        }
        if(txtHorasHombre.getText().trim().isEmpty()==false)
            if(txtDuracion.getText().trim().isEmpty()==false)
            {
                BigDecimal duracionEtapa = Utilidades.parseBigDecimal(txtDuracion.getText());
                BigDecimal horasHombre = Utilidades.parseBigDecimal(txtHorasHombre.getText());
                if(horasHombre.doubleValue() > duracionEtapa.doubleValue())
                {
                    mensaje+="\nLas horas hombre no puede superar la duracion de la etapa";
                    Utilidades.componenteError(txtHorasHombre, "Las horas hombre no puede superar la duracion de la etapa");
                    Utilidades.componenteError(txtDuracion, "Las horas hombre no puede superar la duracion de la etapa");
                }
            }
        for(int i=0;i<tmHerramientas.getSize();i++)
        {
            DetalleEtapaProduccion dep=tmHerramientas.getDato(i);
            if(dep.getCantidadNecesaria()==null||dep.getCantidadNecesaria()<=0)
                mensaje+="\nHerramientas fila "+i+1+": La cantidad ingresada es invalida";

            if(dep.getTipoMaquinaHerramienta()==null)
                 mensaje+="\nHerramientas fila "+i+1+": No se eligió una herramienta correcta";
            else
            for(int x=0;x<tmHerramientas.getSize();x++)
            {
                if(x<i && dep.getTipoMaquinaHerramienta().equals(tmHerramientas.getDato(x)))
                {
                    mensaje+="\nHerramientas fila "+i+1+": Se ha elegido más de una vez la misma herramienta";
                    break;
                }
            }
        }


        for(int i=0;i<tmMateriales.getSize();i++)
        {
            DetalleEtapaProduccion dep=tmMateriales.getDato(i);
            if(dep.getCantidadNecesaria()==null||dep.getCantidadNecesaria()<=0)
                mensaje+="\nMaterial fila "+i+1+": La cantidad ingresada es invalida";

            if(dep.getTDetallesProducto()==null)
                 mensaje+="\nMaterial fila "+i+1+": No se eligió un Material correcto";
            else
            for(int x=0;x<tmMateriales.getSize();x++)
            {
                if(x<i && dep.getTDetallesProducto().getTMateriales().equals(tmMateriales.getDato(x)))
                {
                    mensaje+="\nMaterial fila "+i+1+": Se ha elegido más de una vez el mismo material";
                    break;
                }
            }
        }

        if(mensaje.isEmpty())
            return true;

        Mensajes.mensajeErrorGenerico(mensaje);
        return false;

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
        jLabel14 = new javax.swing.JLabel();
        btnCargarTipoMaquina = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cmbCargo = new javax.swing.JComboBox();
        txtOrden = new javax.swing.JTextField();
        btnCargarTipoEtapa = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnEliminarMaterial = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbMaterial = new javax.swing.JTable();
        btnNuevoMaterial = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnCancelarEtapa = new javax.swing.JButton();
        btnAceptarEtapa = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbHerramientas = new javax.swing.JTable();
        btnEliminarHerramienta = new javax.swing.JButton();
        btnNuevaHerramienta = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        pnlProducto = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbTipoProducto = new javax.swing.JComboBox();
        cmbProducto = new javax.swing.JComboBox();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlEtapas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbEtapas = new javax.swing.JTable();
        btnSubir = new javax.swing.JButton();
        btnBajar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Estructura de Producto");

        pnlEtapaEspecifica.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Etapa específica", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        txtDescripcion.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        jScrollPane4.setViewportView(txtDescripcion);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel15.setText("Descripción:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addContainerGap(38, Short.MAX_VALUE))
            .addComponent(jScrollPane4)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
        );

        btnCargarCargo.setText("+");
        btnCargarCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarCargoActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel21.setText("Duración Etapa:");

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
        jLabel17.setText("Nro de orden:");

        txtOrden.setEditable(false);

        btnCargarTipoEtapa.setText("+");
        btnCargarTipoEtapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarTipoEtapaActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel16.setText("Tipo etapa:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Tipo máquina:");

        jLabel5.setText("min.");

        jLabel7.setText("min.");

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
                        .addComponent(jLabel7))
                    .addComponent(txtOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbTipoEtapa, 0, 163, Short.MAX_VALUE)
                            .addComponent(cmbTipoMaquina, 0, 163, Short.MAX_VALUE)
                            .addComponent(cmbCargo, javax.swing.GroupLayout.Alignment.TRAILING, 0, 163, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCargarTipoMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCargarCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCargarTipoEtapa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
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
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cmbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCargarCargo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtHorasHombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbTipoMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCargarTipoMaquina))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnEliminarMaterial.setText("-");
        btnEliminarMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarMaterialActionPerformed(evt);
            }
        });

        tbMaterial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Material", "Cantidad", "Unidad"
            }
        ));
        jScrollPane2.setViewportView(tbMaterial);

        btnNuevoMaterial.setText("+");
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
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminarMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNuevoMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                        .addContainerGap())
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)))
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

        tbHerramientas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Herramienta", "Cantidad"
            }
        ));
        jScrollPane7.setViewportView(tbHerramientas);

        btnEliminarHerramienta.setText("-");
        btnEliminarHerramienta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarHerramientaActionPerformed(evt);
            }
        });

        btnNuevaHerramienta.setText("+");
        btnNuevaHerramienta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaHerramientaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Herramientas necesarias:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminarHerramienta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNuevaHerramienta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel4))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNuevaHerramienta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarHerramienta)
                        .addContainerGap())
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout pnlEtapaEspecificaLayout = new javax.swing.GroupLayout(pnlEtapaEspecifica);
        pnlEtapaEspecifica.setLayout(pnlEtapaEspecificaLayout);
        pnlEtapaEspecificaLayout.setHorizontalGroup(
            pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEtapaEspecificaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEtapaEspecificaLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEtapaEspecificaLayout.createSequentialGroup()
                        .addComponent(btnAceptarEtapa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarEtapa)))
                .addContainerGap())
        );
        pnlEtapaEspecificaLayout.setVerticalGroup(
            pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEtapaEspecificaLayout.createSequentialGroup()
                .addGroup(pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEtapaEspecificaLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEtapaEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarEtapa)
                    .addComponent(btnAceptarEtapa)))
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
                .addContainerGap(253, Short.MAX_VALUE))
        );
        pnlProductoLayout.setVerticalGroup(
            pnlProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6)
                .addComponent(cmbTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1)
                .addComponent(cmbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        pnlEtapas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Etapas de producción del producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbEtapas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbEtapas);

        btnSubir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/up_green.png"))); // NOI18N
        btnSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirActionPerformed(evt);
            }
        });

        btnBajar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/down_green.png"))); // NOI18N
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEtapasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEtapasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlEtapasLayout.createSequentialGroup()
                        .addGap(0, 425, Short.MAX_VALUE)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEtapasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSubir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBajar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlEtapasLayout.setVerticalGroup(
            pnlEtapasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEtapasLayout.createSequentialGroup()
                .addGroup(pnlEtapasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEtapasLayout.createSequentialGroup()
                        .addComponent(btnSubir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBajar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlEtapasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlEtapas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlEtapaEspecifica, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 575, Short.MAX_VALUE)
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEtapas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEtapaEspecifica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
            tmEtapaEspecifica.setDatos(null);
            limpiarEtapa();
            gestor.setProducto((Producto) cmbProducto.getSelectedItem());
        } else {
            tmEtapaEspecifica.setDatos(null);
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

        if(validarEtapa()==false)
            return;

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
        etapa.setNumeroOrden(Utilidades.parseByte(txtOrden.getText()));

        etapa.setDuracion(new Integer(txtDuracion.getText()));

        etapa.setHorasHombre(new BigDecimal(txtHorasHombre.getText()));


        etapa.limpiarDetalleEtapaProduccion();

        etapa.setTTmaquina((TipoMaquina) cmbTipoMaquina.getSelectedItem());

        for (DetalleEtapaProduccion dep : tmMateriales.getDatos()) {
            dep.setHorasMaquina(BigDecimal.ZERO);
            etapa.addDetalleEtapaProduccion(dep);
        }

        for (DetalleEtapaProduccion dep : tmHerramientas.getDatos()) {
            dep.setHorasMaquina(BigDecimal.ZERO);
            etapa.addDetalleEtapaProduccion(dep);
            
        }


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
        actualizarContenido();


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
        if(cmbMaterial.getItemCount()<=0)
        {
            Mensajes.mensajeErrorGenerico("No hay ningun material cargado en el sistema");
            return;
        }
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
        new GestorTipoMaquina().administar();
        cmbTipoMaquina.setModel(new DefaultComboBoxModel(gestor.listarTipoMaquinas().toArray()));
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

    private void btnEliminarHerramientaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarHerramientaActionPerformed
        // TODO add your handling code here:

        if (tmHerramientas.getSeletedObject() != null) {
            tmHerramientas.removeSelectedRow();
        }

    }//GEN-LAST:event_btnEliminarHerramientaActionPerformed

    private void btnNuevaHerramientaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaHerramientaActionPerformed
        // TODO add your handling code here:
        if(cmbHerramienta.getItemCount()<=0)
        {
            Mensajes.mensajeErrorGenerico("No hay ninguna herramienta cargada en el sistema");
            return;
        }
        tmHerramientas.add(new DetalleEtapaProduccion());

    }//GEN-LAST:event_btnNuevaHerramientaActionPerformed

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
    private javax.swing.JButton btnEliminarHerramienta;
    private javax.swing.JButton btnEliminarMaterial;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevaHerramienta;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevoMaterial;
    private javax.swing.JButton btnSubir;
    private javax.swing.JComboBox cmbCargo;
    private javax.swing.JComboBox cmbProducto;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JPanel pnlEtapaEspecifica;
    private javax.swing.JPanel pnlEtapas;
    private javax.swing.JPanel pnlProducto;
    private javax.swing.JTable tbEtapas;
    private javax.swing.JTable tbHerramientas;
    private javax.swing.JTable tbMaterial;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtDuracion;
    private javax.swing.JTextField txtHorasHombre;
    private javax.swing.JTextField txtOrden;
    // End of variables declaration//GEN-END:variables


}
