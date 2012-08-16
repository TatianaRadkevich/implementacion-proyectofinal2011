/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.Produccion.*;

import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Produccion.PantallaHerramientaABM;

import java.util.List;

/**
 *
 * @author Rodrigo
 */
public abstract class GestorHerramienta {

    protected PantallaHerramientaABM interfaz;
    protected HerramientaParticular HerramientaHerramienta;

    public abstract void iniciarCU();
    public abstract void ejecutarCU(HerramientaParticular p) throws ExceptionGestor;

    public void finalizarCU()
    {
        interfaz.setVisible(false);
        interfaz.dispose();
    }

    public HerramientaParticular getHerramientaParticular() {
        return HerramientaHerramienta;
    }

    public List<TipoHerramienta> getTipoHerramientaHerramienta() {
        return HerramientaBD.listarTipoHerramienta();
    }

    public int getUltimoID() {

        return HerramientaBD.getUltimoID();

    }    
}
