/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Produccion;

import BaseDeDatos.Administracion.CargoBD;
import BaseDeDatos.Compras.MaterialBD;
import BaseDeDatos.Produccion.EtapaProduccionBD;
import BaseDeDatos.Produccion.ProductoBD;
import BaseDeDatos.Produccion.TipoMaquinaHerramientaBD;
import BaseDeDatos.Produccion.TipoProductoBD;
import Negocio.Administracion.Cargo;
import Negocio.Compras.Material;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Produccion.PantallaEstructuraProductoABM;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class GestorEstructura {

    protected PantallaEstructuraProductoABM interfaz;
    protected Producto producto;

    public GestorEstructura()
    {
        interfaz=new PantallaEstructuraProductoABM(this);
        producto=null;
    }



    public void iniciarCU() {

        producto = null;
        interfaz.habilitarCarga(false);
        interfaz.limpiarTodo();
        interfaz.habilitarSeleccionProducto(true);
        interfaz.setTitle("Registrar estructura");
        interfaz.setVisible(true);
    }

    public void iniciarCU(Producto prod) {
        if (producto != null) {
            if (producto.equals(prod)) {
                return;
            }
        }
        producto = prod;
        interfaz.habilitarCarga(false);
        interfaz.limpiarEtapa();
        interfaz.cargarProducto(producto);
        interfaz.habilitarSeleccionProducto(false);
        interfaz.setTitle("Registrar estructura");
        interfaz.setVisible(true);
    }



    public void ejecutarCU(ArrayList<EtapaProduccionEspecifica> e) throws ExceptionGestor {
        producto.setTEtapasProduccionEspecificas(new HashSet<EtapaProduccionEspecifica>(e));
        ProductoBD.guardar(producto);
    }

    public void finalizarCU() {
        interfaz.setVisible(false);
        interfaz.dispose();
    }

    public Producto getProducto() {
        return producto;
    }

    public List<TipoProducto> listarTipoProducto() {
        return TipoProductoBD.listarTiposProductosAlta();
    }

    public List<Producto> getProductos(TipoProducto tp) {
        if (tp == null) {
            throw new RuntimeException("GestorEstructura.getProductos\nTipo de producto null");
        }
        return BaseDeDatos.Produccion.ProductoBD.listarProductos(tp);
    }

    public List<Cargo> listarCargos() {
        return CargoBD.listarCargosAlta();
    }

    public List<EtapaProduccion> listarEtapasGenericas() {
        return EtapaProduccionBD.listarEtapaProduccionAlta();
    }

    public List<TipoMaquinaHerramienta> listarMaquinaHerramienta() {
        return TipoMaquinaHerramientaBD.listarTipoMaquinaHerramienta();
    }

    public List<Material> listarMaterial() {
        return MaterialBD.getMateriales("", "", true, false);
    }
}
