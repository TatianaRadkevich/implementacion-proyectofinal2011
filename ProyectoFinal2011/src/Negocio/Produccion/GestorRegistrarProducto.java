/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;
import BaseDeDatos.Produccion.ProductoBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Produccion.PantallaABMProducto;
import java.awt.Dialog;

/**
 *
 * @author Ivan
 */
public class GestorRegistrarProducto extends GestorProducto{


    public void nuevoProducto(Dialog parent){
        PantallaABMProducto producto=new PantallaABMProducto(parent, true, this,"Registrar Producto");
        producto.nuevo();        
        producto.setVisible(true);
        
    }

    public GestorRegistrarProducto() {
    }


    
    @Override
    public Producto ejecutarOperacion(Producto producto) throws ExceptionGestor {
        return ProductoBD.guardar(producto);
    }

    @Override
    public String mensajeResultado(Producto productos) {
        return "El producto "+productos.getNombre()+ " ha sido dado de registrado exitosamente \nsu codigo es: "+productos.codigoMerge();
    }

    @Override
    public void reiniciar(PantallaABMProducto aThis) {
        aThis.nuevo();
    }


}
