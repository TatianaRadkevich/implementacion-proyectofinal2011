/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;


import BaseDeDatos.Produccion.*;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.PantallaEliminar;
import Presentacion.Produccion.PantallaMaquinaHerramientaTipoABM;
import Presentacion.Utilidades;

/**
 *
 * @author Ivan
 */
public class GestorTipoMaquinaHerramientaModificar extends GestorTipoMaquinaHerramienta
{


   public GestorTipoMaquinaHerramientaModificar(PantallaMaquinaHerramientaTipoABM interfaz,TipoMaquinaHerramienta tipo)
    {
        this.interfaz=interfaz;
        this.tipoMaquinaHerramienta=tipo;
    }

    @Override
    public void iniciarCU() {
        if(tipoMaquinaHerramienta==null)
            throw new RuntimeException("GestorTipoMaquinaHerramientaModificar: Se debe definir el tipo maquina/herramienta a modificar");


        interfaz.cargar(tipoMaquinaHerramienta);
        interfaz.habilitarCarga(true);

    }

    @Override
    public void validar(TipoMaquinaHerramienta tmh) throws ExceptionGestor
    {
        String mensage="";

//        if(p.getCliente()==null)
//            mensage+="\n no se asigno un cliente al pedido";
//

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);

    }

    @Override
    public void ejecutarCU(TipoMaquinaHerramienta tmh) throws ExceptionGestor {
         validar(tmh);
        TipoMaquinaHerramientaBD.modificar(tmh);
        finalizarCU();
    }

  


}