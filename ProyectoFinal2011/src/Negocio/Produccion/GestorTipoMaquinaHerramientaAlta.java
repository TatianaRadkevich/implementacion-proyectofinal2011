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
 * @author Ivan
 */
public class GestorTipoMaquinaHerramientaAlta extends GestorTipoMaquinaHerramienta
{

    public GestorTipoMaquinaHerramientaAlta(PantallaMaquinaHerramientaTipoABM interfaz) {
        this.interfaz=interfaz;
        this.tipoMaquinaHerramienta=new TipoMaquinaHerramienta();
    }


    @Override
    public void iniciarCU() {       
        interfaz.limpiar();
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
        //mh.setEstadoMaquina(null);
        TipoMaquinaHerramientaBD.guardar(tmh);
        finalizarCU();
    }




  


}
