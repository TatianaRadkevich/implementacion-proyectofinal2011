/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos.Administracion;

import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.TipoDocumento;
import Negocio.Produccion.GestorProducto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Heber Parrucci
 */
public class TipoDocumentoBD {

    public static List<TipoDocumento> listarTipoDocumento() throws ExceptionInInitializerError {

        List<TipoDocumento> result = new ArrayList<TipoDocumento>(0);

        try {
            result = HibernateUtil.ejecutarConsulta("from TipoDocumento");

        } catch (Exception ex) {
            Logger.getLogger(GestorProducto.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static TipoDocumento getTipoDocumentoPorNombre(String nombre) {
        nombre=nombre.trim().toLowerCase();
        String hql = String.format("FROM TipoDocumento WHERE LOWER(nombre) LIKE '%s'", nombre);
        List<TipoDocumento> l=HibernateUtil.ejecutarConsulta(hql);
        return (l.isEmpty())?null:l.get(0);
    }

    public TipoDocumentoBD() {
        super();
    }

    public static TipoDocumento guardar(TipoDocumento tipoDoc) {
        HibernateUtil.guardarObjeto(tipoDoc);
        return tipoDoc;
    }

    public static TipoDocumento modificar(TipoDocumento tipoDoc) {
        HibernateUtil.modificarObjeto(tipoDoc);
        return tipoDoc;
    }

    public static void eliminar(TipoDocumento tipoDoc) {
        HibernateUtil.EliminarObjeto(tipoDoc);
    }
}
