/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.Produccion.EstadoMaquinaBD;
import BaseDeDatos.Produccion.MaquinaBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Produccion.PantallaMaquinaABM;
import Presentacion.Utilidades;
import Presentacion.Ventas.PantallaPedidoABM;

/**
 *
 * @author Ivan
 */
public class GestorMaquinaAlta extends GestorMaquina
{

    @Override
    public void iniciarCU() {
        maquinaHerramienta=new MaquinaParticular();
        interfaz=new PantallaMaquinaABM(this);        
        interfaz.setVisible(true);
    }

    private void validar(MaquinaParticular mh) throws ExceptionGestor
    {
        String mensage="";
        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);

    }

    @Override
    public void ejecutarCU(MaquinaParticular mh) throws ExceptionGestor {
        mh.setTEmaquina(EstadoMaquinaBD.getEstadoDisponible());
        validar(mh);
        //mh.setEstadoMaquina(null);
        MaquinaBD.guardar(mh);
    }


  


}
