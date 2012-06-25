/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Ventas;

import BaseDeDatos.Produccion.*;
import BaseDeDatos.Ventas.*;
import Negocio.Exceptiones.ExceptionGestor;
import Negocio.Exceptiones.NegocioException;
import Negocio.Produccion.Producto;
import Negocio.Produccion.TipoProducto;
import Presentacion.Mensajes;
import Presentacion.Ventas.PantallaPedidoABM;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public abstract class GestorPedido {

    public static GestorPedido getGestorPedidoRegistrar() throws NegocioException {
        return new GestorPedido(new Pedido()) {

            @Override
            public void aceptar() throws NegocioException {
                this.pedido.registrar();
                Mensajes.mensajeInformacion("El Pedido Nro. '" + this.pedido.getIdPedido() + "' ha sido registrado exitosamente.");
            }
        };
    }

    public static GestorPedido getGestorPedidoModificar() throws NegocioException {

        return new GestorPedido(new Pedido()) {

            @Override
            public void aceptar() throws NegocioException {
                this.pedido.registrar();
                Mensajes.mensajeInformacion("El Pedido Nro. '" + this.pedido.getIdPedido() + "' ha sido modificado exitosamente.");
            }
        };
    }

    public static GestorPedido getGestorPedidoCancelar() throws NegocioException {
        return new GestorPedido(new Pedido()) {

            @Override
            public void aceptar() throws NegocioException {
                this.pedido.cancelar(motivoBaja);
                Mensajes.mensajeInformacion("El Pedido Nro. '" + this.pedido.getIdPedido() + "' ha sido eliminado exitosamente.");
            }
        };
    }
    protected Pedido pedido;
    protected String motivoBaja;

    public GestorPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public String getMotivoBaja() {
        return motivoBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

    public List<TipoProducto> getTipoProductos() {
        return TipoProductoBD.listarTiposProductosAlta();
    }

    public List<TipoPedido> getTipoPedidos() {
        return TipoPedidoBD.getTipoPedidos();
    }

    public List<Producto> getProductos(TipoProducto tp) {
        if (tp == null) {
            return new ArrayList();
        }
        return ProductoBD.listarProductos(tp);
    }

    public static List getPrioridades() {
        ArrayList salida = new ArrayList();
        salida.add("Muy Alta");
        salida.add("Alta");
        salida.add("Media");
        salida.add("Baja");
        return salida;
    }

    public abstract void aceptar();

    public void cancelar() {
    }

    public GestorDetallePedido nuevoDetallePedido() {
        return new GestorDetallePedido(new DetallePedido(), this.pedido) {

            @Override
            public void aceptar() throws NegocioException {
                for (DetallePedido dp : pedido.getDetallePedido()) {
                    if (dp.getProducto().getNombre().equals(detalle.getProducto().getNombre())) {
                        String mensage =
                                "¡Ya existe un detalle que contiene el producto " + dp.getProducto().getNombre() + "!\n"
                                + "¿Desea agregar la cantidad ingresada al detalle existente?";
                        if (Mensajes.mensajeConfirmacionGenerico(mensage)) {
                            dp.setCantidad(dp.getCantidad() + detalle.getCantidad());
                        }

                    }
                }
                this.ped.addDetallePedido(this.detalle);
            }
        };
    }

    public GestorDetallePedido modificarDetallePedido(final DetallePedido dp) {
        return new GestorDetallePedido(new DetallePedido(dp), this.pedido) {

            @Override
            public void aceptar() throws NegocioException {
                dp.setCantidad(this.detalle.getCantidad());
            }
        };
    }

    public void eliminarDetallePedido(DetallePedido dp) throws NegocioException {
        this.pedido.removeDetallePedido(dp);
    }

    public static List<Cliente> buscarClientes(String razonSocial) {
        return ClienteBD.getClientes("", razonSocial, "", "", true, false);
    }

    public abstract class GestorDetallePedido {

        protected DetallePedido detalle;
        protected Pedido ped;

        public GestorDetallePedido(DetallePedido dp, Pedido p) {
            this.detalle = dp;
            this.ped = p;
        }

        public DetallePedido getDetalle() {
            return detalle;
        }

        public abstract void aceptar() throws NegocioException;
    }
}
