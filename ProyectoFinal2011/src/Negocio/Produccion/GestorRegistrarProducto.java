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
        PantallaABMProducto producto=new PantallaABMProducto(parent, true, this);        
        producto.nuevo();
        parent.setTitle("Registrando Producto");
        producto.setVisible(true);
    }    

    @Override
    public Producto ejecutarOperacion(Producto producto) throws ExceptionGestor {
        return ProductoBD.guardar(producto);
    }

    @Override
    public String mensajeResultado(String nombreProducto) {
        return "El producto "+nombreProducto+ "\nha sido dado de registrado exitosamente";
    }

    @Override
    public void reiniciar(PantallaABMProducto aThis) {
        aThis.nuevo();
    }


}
