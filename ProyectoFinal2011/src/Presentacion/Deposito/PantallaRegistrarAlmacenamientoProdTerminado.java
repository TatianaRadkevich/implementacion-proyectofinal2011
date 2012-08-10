/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaRegistrarAlmacenamientoProdTerminado.java
 *
 * Created on 27/06/2011, 15:24:35
 */
package Presentacion.Deposito;

import BaseDeDatos.Deposito.AlmacenamientoProductoTerminado;
import BaseDeDatos.Deposito.AlmacenamientoProductoTerminadoBD;
import BaseDeDatos.Deposito.EAlmacenamientoProductoTerminado;
import BaseDeDatos.Deposito.EAlmacenamientoProductoTerminadoBD;
import BaseDeDatos.HibernateUtil;
import BaseDeDatos.Ventas.PedidoBD;
import Negocio.Ventas.DetallePedido;
import Negocio.Ventas.Pedido;
import Presentacion.*;
import com.toedter.calendar.JTextFieldDateEditor;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Rodrigo
 */
public class PantallaRegistrarAlmacenamientoProdTerminado extends javax.swing.JDialog {

    private TablaManager<Pedido> tmPedido;
    private TablaManager<DetallePedido> tmDetalle;

    /** Creates new form PantallaConsultarPedido */
    public PantallaRegistrarAlmacenamientoProdTerminado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
        HibernateUtil.getSessionFactory();
        inicializarTablas();
        cargarValidaciones();
        cargarComboEstados();
        IniciadorDeVentanas.iniciarVentana(this, this.getWidth(), this.getHeight());
    }

    private void inicializarTablas() {
        tmPedido = new TablaManager<Pedido>(tbPedidos) {

            @Override
            public Vector getCabecera() {
                Vector cabcera = new Vector();

                cabcera.add("Nro. pedido");//col 3
                cabcera.add("Fecha generación");//col 4
                cabcera.add("Razón social");//col 1
                cabcera.add("CUIT");//col 2
                cabcera.add("Estado");
                cabcera.add("Monto total");
                return cabcera;

            }

            @Override
            public Vector ObjetoFila(Pedido elemento) {
                Vector fila = new Vector();

                fila.add(elemento.getIdPedido());//col 3
                fila.add(Utilidades.parseFecha(elemento.getFechaGeneracion()));//col 4
                fila.add(elemento.getCliente().getRazonSocial());//col 1
                fila.add(elemento.getCliente().getCuit());//col 2
                fila.add(elemento.getEstadoPedido());
                //calculo monto total
                float montoTotal=0f;
                for(DetallePedido dp:elemento.getDetallePedido())
                    montoTotal+=dp.getCantidad()*dp.getPrecio();
                fila.add("$ "+montoTotal);
                return fila;
            }
        };
        tmPedido.addSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
              if(tmPedido.getSeletedObject()!=null)
              {
                  tmDetalle.setDatos(tmPedido.getSeletedObject().getDetallePedido());
              }
              else
              {
                  tmDetalle.limpiar();
              }
            }
        });
        ////////////////////////////////////////////////////////
        tmDetalle = new TablaManager<DetallePedido>(tbDetalle) {

            @Override
            public Vector ObjetoFila(DetallePedido elemento) {
                Vector salida = new Vector(6);
                salida.add(elemento.getProducto().getNombre());
                salida.add(elemento.getProducto().getDescripcion());
                salida.add("$ "+elemento.getPrecio());
                salida.add(elemento.getEstadoDetallePedido().getNombre());
                salida.add("$ "+elemento.getCantidad() * elemento.getPrecio());
                salida.add(elemento.getCantidad());
                salida.add(getCantidadAlmacenada(elemento));
                salida.add(getEstadoAlmacenado(elemento));
                return salida;
            }

            @Override
            public Vector getCabecera() {
                Vector cabcera = new Vector();
                cabcera.add("Producto");//col 3
                cabcera.add("Descripción");//col 4
                cabcera.add("Precio unit.");//col 1
                cabcera.add("Estado");
                cabcera.add("Sub total");
                cabcera.add("Cantidad Total");//col 2
                cabcera.add("Cantidad Almacenada");//col 2
                cabcera.add("Estado Almacenado");//col 2
                return cabcera;
            }

            private Object getCantidadAlmacenada(DetallePedido elemento) {
                Double ret;
                double r = 0;
                for (AlmacenamientoProductoTerminado alm : elemento.getAlmacenado())
                {
                    r+=alm.getCantidad();
                }
                ret = new Double(r);
                return ret;
            }

            private Object getEstadoAlmacenado(DetallePedido elemento) {
                if (elemento.getAlmacenado().isEmpty())
                {
                    return "Sin almacenamiento";
                }
                else
                {
                   
                    int ret = 0;
                    AlmacenamientoProductoTerminado obj = null;
                    for (int i = 0; i < elemento.getAlmacenado().size(); i++ )
                    {
                        if(((AlmacenamientoProductoTerminado)elemento.getAlmacenado().toArray()[i]).getIdAlmacenamiento() > ret)
                        {
                            ret = ((AlmacenamientoProductoTerminado)elemento.getAlmacenado().toArray()[i]).getIdAlmacenamiento();
                            obj =((AlmacenamientoProductoTerminado)elemento.getAlmacenado().toArray()[i]);
                        }
                    }
//                    int last = elemento.getAlmacenado().size() - 1;
//                    return ((AlmacenamientoProductoTerminado)elemento.getAlmacenado().toArray()[last]).getEstado();
                    return obj.getEstado();
                }
            }
            
        };
        
        
        tmDetalle.addSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                limpiarAlmacenamiento();
                if (tmDetalle.getSeletedObject() != null) {
                    tomarValores(tmDetalle.getSeletedObject());
                    jButtonGuardarAlmac.setEnabled(true);
                }
                else
                    jButtonGuardarAlmac.setEnabled(false);

            }
        });
    }

    private void tomarValores(DetallePedido seletedObject) {
        Set<AlmacenamientoProductoTerminado> almacenado = seletedObject.getAlmacenado();
        if (almacenado != null && !almacenado.isEmpty())
        {
            jComboBoxEstado.setSelectedItem(((AlmacenamientoProductoTerminado)almacenado.toArray()[almacenado.size() - 1]).getEstado());
            jTextFieldCantidad.setText(getCantAlmacenada(seletedObject)+"");
        } 
    }
    
    private Vector cargarComboEstados() {
        List<EAlmacenamientoProductoTerminado> estados = EAlmacenamientoProductoTerminadoBD.listarAlmacenamientos();
        Vector items = new Vector();
        items.add("Seleccionar...");
        items.addAll(estados);        
        return items;
    }

    private void limpiarAlmacenamiento() {
        jComboBoxEstado.setSelectedIndex(-1);
        jTextFieldCantidad.setText("");
    }

    @SuppressWarnings("static-access")
    private void cargarAlmacenamiento(DetallePedido seletedObject) {
//        EAlmacenamientoProductoTerminado estado = (EAlmacenamientoProductoTerminado)jComboBoxEstado.getSelectedItem();
        EAlmacenamientoProductoTerminado estado = new EAlmacenamientoProductoTerminado();

//        if (estado == null)
//        {
//            Mensajes.mensajeErrorGenerico("Debe seleccionar un estado de producto válido");
//            return;
//        }
        int cantidad;
        try
        {
            cantidad = Integer.parseInt(jTextFieldCantidad.getText().trim());
            int cantAlm = getCantAlmacenada(seletedObject);
           /* if (cantidad + cantAlm > seletedObject.getCantidad())
            {
                Mensajes.mensajeErrorGenerico("Existe una cantidad almacenada de " + getCantAlmacenada(seletedObject) + " y no puede superar el total de " + seletedObject.getCantidad() + " producida");
                return;
            }*/
            if(cantidad + cantAlm > seletedObject.getCantidad())
            {
                estado = new EAlmacenamientoProductoTerminadoBD().devolverEstadoAlmacenamiento(4).get(0);
            }
            else
            {
                if(cantidad + cantAlm == seletedObject.getCantidad())
                {
                    estado = new EAlmacenamientoProductoTerminadoBD().devolverEstadoAlmacenamiento(2).get(0);
                }
                else
                {
                    estado = new EAlmacenamientoProductoTerminadoBD().devolverEstadoAlmacenamiento(3).get(0);
                }
            }
        }
        catch (Exception e)
        {
            Mensajes.mensajeErrorGenerico("Ingrese una cantidad válida");
            return;
        }
        
        AlmacenamientoProductoTerminado alm = new AlmacenamientoProductoTerminado();
        alm.setEstado(estado);
        alm.setCantidad(cantidad);
        alm.setFechaAlmacenamiento(new Date());
        alm.setTDetallesPedido(seletedObject);
        seletedObject.getAlmacenado().add(alm);
        jButtonAceptarActionPerformed(null);
    }
    
    private int getCantAlmacenada(DetallePedido seletedObject) {
        int ret = 0;
        for (AlmacenamientoProductoTerminado a : seletedObject.getAlmacenado())
        {
            ret+= a.getCantidad();
        }
        return ret;
    }

        
    private void cargarValidaciones() {
        //**********************Validacion de textBox********************//
        ValidarTexbox.validarInt(txtNroPedido);
        ValidarTexbox.validarLongitud(txtNroPedido, 8);

        //**********************Validacion de Fecha********************//
        ((JTextFieldDateEditor) dtcFechaGeneracionHasta.getDateEditor()).setEditable(false);
        ((JTextFieldDateEditor) dtcFechaGeneracionDesde.getDateEditor()).setEditable(false);

        dtcFechaGeneracionHasta.setMaxSelectableDate(Utilidades.getFechaActual());
        dtcFechaGeneracionDesde.setMaxSelectableDate(Utilidades.getFechaActual());

        dtcFechaGeneracionHasta.setDate(Utilidades.getFechaActual());

        //**********************Validacion de Fecha parte2********************//

        dtcFechaGeneracionHasta.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtcFechaGeneracionDesde.setMaxSelectableDate(dtcFechaGeneracionHasta.getDate());
            }
        });

        dtcFechaGeneracionDesde.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtcFechaGeneracionHasta.setMinSelectableDate(dtcFechaGeneracionDesde.getDate());
            }
        });

        /************************Validacion de botones **********************************/
        tmPedido.addSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                boolean var = false;
                if (tmPedido.getSeletedObject() != null) {
                    if (tmPedido.getSeletedObject().getFecBaja() == null) {
                        var = true;
                    }
                }
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBuscar = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dtcFechaGeneracionDesde = new com.toedter.calendar.JDateChooser();
        dtcFechaGeneracionHasta = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txtNroPedido = new javax.swing.JTextField();
        btnTodos = new javax.swing.JButton();
        pnlPedidos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPedidos = new javax.swing.JTable();
        pnlDetalle = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDetalle = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxEstado = new javax.swing.JComboBox(cargarComboEstados());
        jTextFieldCantidad = new javax.swing.JTextField();
        jButtonGuardarAlmac = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jButtonAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar Almacenamiento Producto Terminado");

        pnlBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Búsqueda", 0, 0, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha Generación", 0, 0, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Desde:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Hasta:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(12, 12, 12)
                        .addComponent(dtcFechaGeneracionHasta, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(10, 10, 10)
                        .addComponent(dtcFechaGeneracionDesde, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(dtcFechaGeneracionHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(dtcFechaGeneracionDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Nro. Pedido:");

        btnTodos.setText("Mostrar Todos");
        btnTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBuscarLayout = new javax.swing.GroupLayout(pnlBuscar);
        pnlBuscar.setLayout(pnlBuscarLayout);
        pnlBuscarLayout.setHorizontalGroup(
            pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBuscarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(47, 47, 47)
                .addComponent(txtNroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlBuscarLayout.setVerticalGroup(
            pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBuscarLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(pnlBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlBuscarLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTodos)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlPedidos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pedidos", 0, 0, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbPedidos);

        pnlDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle pedido seleccionado", 0, 0, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tbDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Descripción", "Precio unit. ($)", "Cantidad", "Estado", "Sub total"
            }
        ));
        jScrollPane2.setViewportView(tbDetalle);

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Almacenamiento", 0, 0, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel6.setText("Estado:");

        jLabel7.setText("Cantidad:");

        jButtonGuardarAlmac.setText("Guardar");
        jButtonGuardarAlmac.setEnabled(false);
        jButtonGuardarAlmac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarAlmacActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxEstado, 0, 98, Short.MAX_VALUE)
                            .addComponent(jTextFieldCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 78, Short.MAX_VALUE)
                        .addComponent(jButtonGuardarAlmac)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonGuardarAlmac)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlPedidosLayout = new javax.swing.GroupLayout(pnlPedidos);
        pnlPedidos.setLayout(pnlPedidosLayout);
        pnlPedidosLayout.setHorizontalGroup(
            pnlPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPedidosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
                    .addGroup(pnlPedidosLayout.createSequentialGroup()
                        .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlPedidosLayout.setVerticalGroup(
            pnlPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPedidosLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnSalir.setText("Cancelar");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 578, Short.MAX_VALUE)
                        .addComponent(jButtonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(jButtonAceptar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        List<Pedido> pedidos = PedidoBD.getPedidos(
                "",
                "",
                txtNroPedido.getText(),
                dtcFechaGeneracionDesde.getDate(),
                dtcFechaGeneracionHasta.getDate(),
                true,
                false);
        tmPedido.setDatos(pedidos);

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();        
    }//GEN-LAST:event_btnSalirActionPerformed

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
        if (!tmPedido.getDatos().isEmpty())
        {
                for (Pedido p : tmPedido.getDatos())
            {
                PedidoBD.guardar(p);
            }
            Mensajes.mensajeInformacion("Se han guardado correctamente los almacenamientos");
        }
    }//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jButtonGuardarAlmacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarAlmacActionPerformed
        cargarAlmacenamiento(tmDetalle.getSeletedObject());
        tmDetalle.setDatos(tmDetalle.getDatos());
        jComboBoxEstado.setSelectedIndex(-1);
        jTextFieldCantidad.setText("");
        tmDetalle.setSelectedRow(-1);
    }//GEN-LAST:event_jButtonGuardarAlmacActionPerformed

    private void btnTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodosActionPerformed
        // TODO add your handling code here:
        List<Pedido> pedidos = PedidoBD.getPedidosTerminadoProduccion();
        tmPedido.setDatos(pedidos);
    }//GEN-LAST:event_btnTodosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnTodos;
    private com.toedter.calendar.JDateChooser dtcFechaGeneracionDesde;
    private com.toedter.calendar.JDateChooser dtcFechaGeneracionHasta;
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonGuardarAlmac;
    private javax.swing.JComboBox jComboBoxEstado;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextFieldCantidad;
    private javax.swing.JPanel pnlBuscar;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlPedidos;
    private javax.swing.JTable tbDetalle;
    private javax.swing.JTable tbPedidos;
    private javax.swing.JTextField txtNroPedido;
    // End of variables declaration//GEN-END:variables

}
