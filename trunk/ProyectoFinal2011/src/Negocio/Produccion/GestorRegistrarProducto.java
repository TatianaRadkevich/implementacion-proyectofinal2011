/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.Produccion.TProductos;
import BaseDeDatos.Produccion.TTproducto;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Produccion.PantallaABMProducto;
import java.awt.Dialog;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class GestorRegistrarProducto extends GestorProducto{


    public void nuevoProducto(Dialog parent){
        PantallaABMProducto producto=new PantallaABMProducto(parent, true, this);
        producto.nuevo();
        producto.setVisible(true);
    }

    @Override
    public List<TTproducto> traerTiposProductos() throws ExceptionGestor{        
          return TipoProducto.listarTiposProductos();
    }
    
    
    public TProductos ejecutarOperacion(TProductos producto) throws ExceptionGestor{
        return Producto.guardar(producto);
    }

}
