/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.Produccion.MaquinaHerramientaBD;
import BaseDeDatos.Produccion.*;

import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Produccion.PantallaMaquinaHerramientaABM;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public abstract class GestorMaquinaHerramienta {

    protected PantallaMaquinaHerramientaABM interfaz;
    protected MaquinaHerramientaParticular maquinaHerramienta;

    public abstract void iniciarCU();
    public abstract void ejecutarCU(MaquinaHerramientaParticular p) throws ExceptionGestor;

    public void finalizarCU()
    {
        interfaz.setVisible(false);
        interfaz.dispose();
    }

    public MaquinaHerramientaParticular getMaquinaHerramientaParticular() {
        return maquinaHerramienta;
    }

    public List<TipoMaquinaHerramienta> getTipoMaquinaHerramienta() {
        return MaquinaHerramientaBD.listarTipoMaquinaHerramienta();
    }

    public List<EstadoMaquina> getEstadoMaquina() {
        return MaquinaHerramientaBD.listarEstadoMaquina();
    }
}
