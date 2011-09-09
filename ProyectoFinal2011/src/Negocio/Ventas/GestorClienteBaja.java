/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Ventas;

import BaseDeDatos.Ventas.ClienteBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Utilidades;
import Presentacion.Ventas.PantallaClienteABM;

/**
 *
 * @author Rodrigo
 */
public class GestorClienteBaja extends GestorCliente{

public GestorClienteBaja(Cliente p)
    {
        cliente=p;
    }

    @Override
    public void iniciarCU() {
        if(cliente==null)
            throw new RuntimeException("GestorClienteEliminar: Se debe definir el cliente a eliminar");

        interfaz=new PantallaClienteABM(this);
        interfaz.cargar(cliente);
        interfaz.habilitarBaja(true, true, Utilidades.getFechaActual(), "");
        interfaz.habilitarCarga(false);
        interfaz.setTitle("Eliminar cliente");
        interfaz.setVisible(true);
    }

    private void validar(Cliente p) throws ExceptionGestor
    {
        String mensage="";


        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);
    }

    @Override
    public void ejecutarCU(Cliente p) throws ExceptionGestor {
        validar(p);
        ClienteBD.modificar(p);
    }






}
