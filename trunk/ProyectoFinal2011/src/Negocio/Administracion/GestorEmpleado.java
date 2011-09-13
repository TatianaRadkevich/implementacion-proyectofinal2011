/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Administracion;

import BaseDeDatos.Administracion.EmpleadoBD;
import BaseDeDatos.UbicacionGeografica.PaisBD;
import Negocio.Exceptiones.ExceptionGestor;
import Negocio.UbicacionGeografica.Pais;
import java.util.List;

/**
 *
 * @author Ivan
 */
public abstract class GestorEmpleado {



    public abstract Empleado ejecutarOperacion(Empleado Empleado) throws ExceptionGestor;

    public static List<Empleado> listarProductos(){
        return EmpleadoBD.listarEmpleado();
    }
    public static List<Cargo> listarCargos(){
        return GestorCargo.listarCargosAlta();
    }
    public List<TipoDocumento> listarTipoDocumento(){
        return GestorTipoDocumento.listarTipoDocumentos();
    }

    public List<Sexo> listarSexo(){
        return GestorSexo.listarSexos();
    }

    public abstract String mensajeResultado(String nombreProducto);

    public List<Pais> listarPais(){
        return PaisBD.listarPaises();
    }

}
