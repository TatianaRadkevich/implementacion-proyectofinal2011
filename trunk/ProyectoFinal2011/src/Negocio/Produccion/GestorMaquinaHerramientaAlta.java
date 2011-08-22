/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.Produccion.MaquinaHerramientaBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Produccion.PantallaMaquinaHerramientaABM;
import Presentacion.Utilidades;
import Presentacion.Ventas.PantallaPedidoABM;

/**
 *
 * @author Ivan
 */
public class GestorMaquinaHerramientaAlta extends GestorMaquinaHerramienta
{

    @Override
    public void iniciarCU() {
        interfaz=new PantallaMaquinaHerramientaABM(this);
        maquinaHerramienta=new MaquinaHerramientaParticular();
        interfaz.setVisible(true);
    }

    private void validar(MaquinaHerramientaParticular mh) throws ExceptionGestor
    {
        String mensage="";
       
//        if(p.getCliente()==null)
//            mensage+="\n no se asigno un cliente al pedido";
//

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);

    }

    @Override
    public void ejecutarCU(MaquinaHerramientaParticular mh) throws ExceptionGestor {
        validar(mh);
        //mh.setEstadoMaquina(null);
        MaquinaHerramientaBD.guardar(mh);
    }


  


}
