/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Ventas;

import BaseDeDatos.Ventas.TipoClienteBD;
import BaseDeDatos.Ventas.*;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Ventas.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Rodrigo
 */
public abstract class GestorCliente {


    protected PantallaClienteABM interfaz;
    protected Cliente cliente;

    public abstract void iniciarCU();
    public abstract void ejecutarCU(Cliente p) throws ExceptionGestor;

    public void finalizarCU()
    {
        interfaz.setVisible(false);
        interfaz.dispose();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<TipoCliente> getTipoClientes() {
        return TipoClienteBD.listarTiposClientes();
    }     

}
