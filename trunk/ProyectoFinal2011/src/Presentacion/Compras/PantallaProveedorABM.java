/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PantallaProveedorABM.java
 *
 * Created on 19/09/2011, 16:39:22
 */

package Presentacion.Compras;

import Negocio.Compras.Proveedor;
import Negocio.Exceptiones.ExceptionGestor;
import Negocio.Compras.GestorProveedor;
import Negocio.Compras.GestorProveedorBaja;
import Negocio.Exceptiones.TipoDatoException;
import Negocio.Produccion.GestorBajaProducto;
import Negocio.Ventas.Cliente;
import Presentacion.Mensajes;
import Presentacion.Utilidades;
import Presentacion.ValidarTexbox;
import Presentacion.ZLinkers.ZLDomicilio;
import Presentacion.ZLinkers.ZLFormatedTextField;
import Presentacion.ZLinkers.ZLFormatedTextField.Formato;
import Presentacion.ZLinkers.ZLObject;
import Presentacion.ZLinkers.ZLTextField;
import java.awt.Window;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigo
 */
public class PantallaProveedorABM extends javax.swing.JDialog {

    private GestorProveedor gestor;
    private ZLObject<Proveedor> linker;
    /** Creates new form PantallaProveedorABM */
    private PantallaProveedorABM(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public PantallaProveedorABM(GestorProveedor g) {
        this(null, true);
        gestor = g;

        gestor.setInterfaz(this);
        /////////// Precargas Necesarias //////////
        habilitarBaja(false,false,null,"");
        cargarValidaciones();
        pnlDomicilio.inicializar();


//         Class p=Proveedor.class;
//        linker=new ZLinkerObject<Proveedor> (p,gestor.getProveedor());
//        linker.add(new ZLinkerTextField<Proveedor>(p, "razonSocial", txtRazonSocial));
//        linker.add(new ZLinkerTextField<Proveedor>(p, "cuit", txtCUIT));
//        linker.add(new ZLinkerTextField<Proveedor>(p, "correoElectronico", txtCorreoElectronico));
//        linker.add(new ZLinkerTextField<Proveedor>(p, "paginaWeb", txtPaginaWeb));
//        linker.add(new ZLinkerTextField<Proveedor>(p, "nombreResponsable", txtNombre));
//        linker.add(new ZLinkerTextField<Proveedor>(p, "apellidoResponsable", txtApellido));
//        linker.add(new ZLinkerTextField<Proveedor>(p, "telefono", txtTelefono));
//        linker.add(new ZLinkerDomicilio<Proveedor>(p, "domicilio",pnlDomicilio));
//        linker.add(new ZLinkerTextField<Proveedor>(p,"motivoBaja", txtMotivoBaja));

        linker=new ZLObject (Proveedor.class,gestor.getProveedor());
        linker.add("razonSocial", new ZLTextField(txtRazonSocial));
        linker.add("cuit", new ZLTextField(txtCUIT));
//        linker.add("cuit", new ZLFormatedTextField(txtCUIT,Formato.CUIT));
        linker.add("correoElectronico", new ZLTextField(txtCorreoElectronico));
        linker.add("paginaWeb", new ZLTextField(txtPaginaWeb));
        linker.add("nombreResponsable", new ZLTextField(txtNombre));
        linker.add("apellidoResponsable", new ZLTextField(txtApellido));
        linker.add("telefono", new ZLTextField(txtTelefono));
        linker.add("motivoBaja", new ZLTextField(txtMotivoBaja));
        linker.add("domicilio",new ZLDomicilio(pnlDomicilio));
    }

 

    public void cargarValidaciones() {
        ValidarTexbox.validarInt(txtCUIT);
        ValidarTexbox.validarInt(txtTelefono);
        ValidarTexbox.validarLongitud(txtCUIT, 11);
        ValidarTexbox.validarLongitud(txtNombre, 50);
        ValidarTexbox.validarLongitud(txtApellido, 200);
        ValidarTexbox.validarLongitud(txtCorreoElectronico, 50);
        ValidarTexbox.validarLongitud(txtRazonSocial, 50);
        ValidarTexbox.validarLongitud(txtPaginaWeb, 50);
    }

    public void cargar(Proveedor p) {
        txtApellido.setText(p.getApellidoResponsable());
        txtCUIT.setText(Utilidades.parseString(p.getCuit()));
        txtPaginaWeb.setText(p.getPaginaWeb());
        txtCorreoElectronico.setText(p.getCorreoElectronico());
        txtNombre.setText(p.getNombreResponsable());
        txtRazonSocial.setText(p.getRazonSocial());
        txtTelefono.setText(Utilidades.parseString(p.getTelefonoResponsable()));
        pnlDomicilio.setDomicilio(p.getDomicilio());
        if(p.getFecBaja()!=null)
        {
           // pnlBaja.setVisible(true);
            txtFechaBaja.setText(Utilidades.parseFecha(p.getFecBaja()));
            txtMotivoBaja.setText(Utilidades.parseString(p.getMotivoBaja()));
        }
    }

    public void habilitarCarga(boolean b) {
        txtApellido.setEditable(b);
        txtCUIT.setEditable(b);
        txtCorreoElectronico.setEditable(b);
        pnlDomicilio.habilitar(b);
        txtNombre.setEditable(b);
        txtTelefono.setEditable(b);        
        txtRazonSocial.setEditable(b);
        pnlDomicilio.setEnabled(b);
        txtPaginaWeb.setEditable(b);

    }

    public void habilitarBaja(boolean visible,boolean edittable, Date fecha,String motivo)
    {
       // pnlBaja.setVisible(visible);
        scrollBaja.setEnabled(edittable);
        txtMotivoBaja.setEnabled(edittable);
        txtMotivoBaja.setText(Utilidades.parseString(motivo));
        txtFechaBaja.setText(Utilidades.parseFecha(fecha));

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        pnlDomicilio = new Presentacion.PnlDomicilio();
        pnlCliente = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtCorreoElectronico = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtRazonSocial = new javax.swing.JTextField();
        txtCUIT = new javax.swing.JTextField();
        pnlResponsable = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPaginaWeb = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        pnlBaja = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtFechaBaja = new javax.swing.JTextField();
        scrollBaja = new javax.swing.JScrollPane();
        txtMotivoBaja = new javax.swing.JTextArea();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlDomicilio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pnlDomicilioFocusLost(evt);
            }
        });

        pnlCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("CUIT:");

        txtCorreoElectronico.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCorreoElectronicoFocusLost(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Razon social:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("E-Mail");

        txtRazonSocial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRazonSocialFocusLost(evt);
            }
        });

        txtCUIT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCUITFocusLost(evt);
            }
        });

        pnlResponsable.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Responsable", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreFocusLost(evt);
            }
        });

        txtApellido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtApellidoFocusLost(evt);
            }
        });

        txtTelefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTelefonoFocusLost(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Nombre:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("Apellido:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setText("Telefono:");

        javax.swing.GroupLayout pnlResponsableLayout = new javax.swing.GroupLayout(pnlResponsable);
        pnlResponsable.setLayout(pnlResponsableLayout);
        pnlResponsableLayout.setHorizontalGroup(
            pnlResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResponsableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTelefono)
                    .addComponent(txtApellido)
                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                .addContainerGap(220, Short.MAX_VALUE))
        );
        pnlResponsableLayout.setVerticalGroup(
            pnlResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResponsableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtPaginaWeb.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPaginaWebFocusLost(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Pagina web:");

        javax.swing.GroupLayout pnlClienteLayout = new javax.swing.GroupLayout(pnlCliente);
        pnlCliente.setLayout(pnlClienteLayout);
        pnlClienteLayout.setHorizontalGroup(
            pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlResponsable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlClienteLayout.createSequentialGroup()
                        .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPaginaWeb)
                            .addComponent(txtCorreoElectronico)
                            .addComponent(txtCUIT)
                            .addComponent(txtRazonSocial, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 220, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlClienteLayout.setVerticalGroup(
            pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClienteLayout.createSequentialGroup()
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCUIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCorreoElectronico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPaginaWeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(pnlResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlBaja.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Baja", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setText("Fecha:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setText("Motivo:");

        txtFechaBaja.setEditable(false);

        scrollBaja.setEnabled(false);

        txtMotivoBaja.setLineWrap(true);
        txtMotivoBaja.setWrapStyleWord(true);
        txtMotivoBaja.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMotivoBajaFocusLost(evt);
            }
        });
        scrollBaja.setViewportView(txtMotivoBaja);

        javax.swing.GroupLayout pnlBajaLayout = new javax.swing.GroupLayout(pnlBaja);
        pnlBaja.setLayout(pnlBajaLayout);
        pnlBajaLayout.setHorizontalGroup(
            pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBajaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollBaja))
                .addContainerGap())
        );
        pnlBajaLayout.setVerticalGroup(
            pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBajaLayout.createSequentialGroup()
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(scrollBaja, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlBaja, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(pnlCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(pnlDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(btnAceptar)
                            .addGap(18, 18, 18)
                            .addComponent(btnCancelar))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlDomicilio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        Proveedor pro = gestor.getProveedor();
        try {
            pro.setDomicilio(pnlDomicilio.getDomicilio());
        } catch (TipoDatoException ex) {

        }
         try {
            // TODO add your handling code here:
            pro.validarOk();
            
        } catch (TipoDatoException ex) {
            Mensajes.mensajeErrorGenerico(ex.getMessage());
      }
        try {
            gestor.ejecutarCU(pro);
            
            Mensajes.mensajeInformacion(gestor.mensajeResultado(pro.getRazonSocial()));
            gestor.finalizarCU();
        } catch (ExceptionGestor ex) {
            Mensajes.mensajeErrorGenerico(ex.getMessage());
        }
}                                          

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        gestor.finalizarCU();
}                                           

    private void txtRazonSocialFocusLost(java.awt.event.FocusEvent evt) {                                         
        // TODO add your handling code here:
//        try{
//
//            gestor.getProveedor().setRazonSocial(txtRazonSocial.getText());
//            Utilidades.componenteCorrecto(txtRazonSocial);
//        }catch(TipoDatoException ex){
//             txtRazonSocial.setToolTipText(ex.getMessage());
//             Utilidades.componenteError(txtRazonSocial);
//        }
    }                                        

    private void txtCUITFocusLost(java.awt.event.FocusEvent evt) {                                  
//         TODO add your handling code here:
        if(gestor.existeProveedor(txtCUIT.getText()))
        {
            Mensajes.mensajeInformacion("El proveedor con ese numero de cuit ya esta registrado");
            txtCUIT.setText("");
            
        }
    }                                 

    private void txtCorreoElectronicoFocusLost(java.awt.event.FocusEvent evt) {                                               
        // TODO add your handling code here:
//        try {
//            // TODO add your handling code here:
//            gestor.getProveedor(). setCorreoElectronico(txtCorreoElectronico.getText());
//            Utilidades.componenteCorrecto(txtCorreoElectronico);
//        } catch (TipoDatoException ex) {
//            txtCorreoElectronico.setToolTipText(ex.getMessage());
//            Utilidades.componenteError(txtCorreoElectronico);
//        }
    }                                              

    private void txtPaginaWebFocusLost(java.awt.event.FocusEvent evt) {                                       
        // TODO add your handling code here:
//        try {
//            gestor.getProveedor(). setPaginaWeb(txtPaginaWeb.getText());
//            Utilidades.componenteCorrecto(txtPaginaWeb);
//        } catch (TipoDatoException ex) {
//            txtPaginaWeb.setToolTipText(ex.getMessage());
//            Utilidades.componenteError(txtPaginaWeb);
//        }
    }                                      

    private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {                                    
        // TODO add your handling code here:
//        try {
//            gestor.getProveedor().setNombreResponsable(txtNombre.getText());
//            Utilidades.componenteCorrecto(txtNombre);
//        } catch (TipoDatoException ex) {
//            txtNombre.setToolTipText(ex.getMessage());
//            Utilidades.componenteError(txtNombre);
//        }
    }                                   

    private void txtApellidoFocusLost(java.awt.event.FocusEvent evt) {                                      
        // TODO add your handling code here:
//        try {
//            gestor.getProveedor().setApellidoResponsable(txtApellido.getText());
//            Utilidades.componenteCorrecto(txtApellido);
//        } catch (TipoDatoException ex) {
//            txtApellido.setToolTipText(ex.getMessage());
//            Utilidades.componenteError(txtApellido);
//        }
    }                                     

    private void txtTelefonoFocusLost(java.awt.event.FocusEvent evt) {                                      
        // TODO add your handling code here:
//         try{
//            long temp=Long.parseLong(txtTelefono.getText());
//            gestor.getProveedor().setTelefonoResponsable(txtTelefono.getText());
//            Utilidades.componenteCorrecto(txtTelefono);
//        }catch(TipoDatoException ex){
//             txtTelefono.setToolTipText(ex.getMessage());
//             Utilidades.componenteError(txtTelefono);
//        }catch(Exception e){
//             Utilidades.componenteError(txtTelefono);
//             txtTelefono.setToolTipText("Formato incorrecto. Debe ser numérico");
//        }
    }                                     

    private void pnlDomicilioFocusLost(java.awt.event.FocusEvent evt) {
        try {
            // TODO add your handling code here:
            gestor.getProveedor().setDomicilio(pnlDomicilio.getDomicilio());
        } catch (TipoDatoException ex) {

        }
    }

    private void txtMotivoBajaFocusLost(java.awt.event.FocusEvent evt) {                                        
        // TODO add your handling code here:
//        if(txtMotivoBaja.getText().compareTo("")!=0)
//            gestor.getProveedor().setMotivoBaja(txtMotivoBaja.getText());
    }                                       

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PantallaProveedorABM dialog = new PantallaProveedorABM(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel pnlBaja;
    private javax.swing.JPanel pnlCliente;
    private Presentacion.PnlDomicilio pnlDomicilio;
    private javax.swing.JPanel pnlResponsable;
    private javax.swing.JScrollPane scrollBaja;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCUIT;
    private javax.swing.JTextField txtCorreoElectronico;
    private javax.swing.JTextField txtFechaBaja;
    private javax.swing.JTextArea txtMotivoBaja;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPaginaWeb;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration                   

     public void iniciarBaja(){
         
        Utilidades.habilitarPanel(pnlCliente, false);
        Utilidades.habilitarPanel(pnlResponsable, false);
        Utilidades.habilitarPanel(pnlDomicilio, false);
        txtFechaBaja.setText(Utilidades.parseFecha(new Date()));
        txtMotivoBaja.setEnabled(true);
        cargar(gestor.getProveedor());

    }
     
      public static void iniciarCancelarEmpleado(Window parent, Proveedor e) {

        PantallaProveedorABM i = new PantallaProveedorABM(new GestorProveedorBaja(e));
        i.setTitle("Registar Baja Proveedor");
        i.iniciarBaja();
        i.pack();
        i.setVisible(true);
    }
}
