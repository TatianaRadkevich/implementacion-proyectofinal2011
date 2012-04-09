/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Administracion;

import BaseDeDatos.Administracion.EmpleadoBD;
import BaseDeDatos.Administracion.EstadoEmpleadoBD;
import BaseDeDatos.HibernateUtil;
import BaseDeDatos.UbicacionGeografica.BarrioBD;
import BaseDeDatos.UbicacionGeografica.LocalidadBD;
import BaseDeDatos.UbicacionGeografica.PaisBD;
import BaseDeDatos.UbicacionGeografica.ProvinciaBD;
import Negocio.Exceptiones.ExceptionGestor;
import Negocio.UbicacionGeografica.Barrio;
import Negocio.UbicacionGeografica.Localidad;
import Negocio.UbicacionGeografica.Pais;
import Negocio.UbicacionGeografica.Provincia;
import java.util.Iterator;
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

    public static  Empleado traerEmpleado(int legajo){
       return (Empleado) HibernateUtil.getObjeto(Empleado.class, legajo);
    }

    public boolean existeEmpleado(TipoDocumento tipodocumento, int numeroDoc) {
        return EmpleadoBD.existeEmpleado(tipodocumento.getIdTdocumento(), numeroDoc);
    }

    public List<Provincia> listarProvincias(Pais pais) {
        return  ProvinciaBD.listarProvincias(pais);
    }

    public List<Localidad> listarLocalidades(Provincia provincia) {
        return LocalidadBD.listarLocalidades(provincia);
    }

    public List<Barrio> listarBarrios(Localidad localidad) {
        return BarrioBD.listarBarrios(localidad);
    }

    public EstadosEmpleado getEstadoDisponible() {
        return EstadoEmpleadoBD.traerEstadoDisponible();
    }

}
