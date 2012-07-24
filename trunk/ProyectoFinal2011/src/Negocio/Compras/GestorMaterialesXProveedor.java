/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Compras;

import BaseDeDatos.Compras.MaterialXProveedorBD;
import Negocio.Deposito.*;
import BaseDeDatos.Deposito.ReclamoBD;
import java.util.List;
/**
 *
 * @author Heber Parrucci
 */
public class GestorMaterialesXProveedor {

    public MaterialesXProveedor guardar(MaterialesXProveedor mxp){
         return MaterialXProveedorBD.guardar(mxp);
    }

    public MaterialesXProveedor modificar(MaterialesXProveedor mxp){
         return MaterialXProveedorBD.modificar(mxp);
    }
    

}
