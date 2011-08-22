/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;


import BaseDeDatos.Produccion.*;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Produccion.PantallaMaquinaHerramientaTipoABM;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public abstract class GestorTipoMaquinaHerramienta {

    protected PantallaMaquinaHerramientaTipoABM interfaz;
    protected TipoMaquinaHerramienta tipoMaquinaHerramienta;

    public abstract void iniciarCU();
    public abstract void ejecutarCU(TipoMaquinaHerramienta p) throws ExceptionGestor;
    public abstract void validar(TipoMaquinaHerramienta p) throws ExceptionGestor;

    public void finalizarCU()
    {
        interfaz.limpiar();
        interfaz.habilitarCarga(false);
    }

    public TipoMaquinaHerramienta getTipoMaquinaHerramienta() {
        return tipoMaquinaHerramienta;
    }   

    public List<TipoMaquinaHerramienta> listarTipoMaquinaHerramienta() {
        return TipoMaquinaHerramientaBD.listarTipoMaquinaHerramienta();
    }

     

}
