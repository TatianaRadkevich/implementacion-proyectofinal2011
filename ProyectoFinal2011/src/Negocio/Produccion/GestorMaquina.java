/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.Produccion.MaquinaBD;
import BaseDeDatos.Produccion.*;

import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Produccion.PantallaMaquinaABM;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public abstract class GestorMaquina {

    protected PantallaMaquinaABM interfaz;
    protected MaquinaParticular maquinaHerramienta;

    public abstract void iniciarCU();
    public abstract void ejecutarCU(MaquinaParticular p) throws ExceptionGestor;

    public void finalizarCU()
    {
        interfaz.setVisible(false);
        interfaz.dispose();
    }

    public MaquinaParticular getMaquinaParticular() {
        return maquinaHerramienta;
    }

    public List<TipoMaquina> getTipoMaquina() {
        return MaquinaBD.listarTipoMaquina();
    }

    public int getUltimoID() {

        return MaquinaBD.getUltimoID();

    }

    public List<EstadoMaquina> getEstadoMaquina() {
        return MaquinaBD.listarEstadoMaquina();
    }
}
