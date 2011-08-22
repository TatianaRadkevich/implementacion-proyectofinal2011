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
public class GestorTipoMaquinaHerramienta {

    protected PantallaMaquinaHerramientaTipoABM interfaz;
    protected TipoMaquinaHerramienta tipoMaquinaHerramienta;

    public void iniciarCU(){}
    public void ejecutarCU(TipoMaquinaHerramienta p) throws ExceptionGestor{}
    public void validar(TipoMaquinaHerramienta p) throws ExceptionGestor{}

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
