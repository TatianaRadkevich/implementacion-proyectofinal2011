/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;


import BaseDeDatos.Produccion.HerramientaBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Produccion.PantallaHerramientaABM;

/**
 *
 * @author Ivan
 */
public class GestorHerramientaAlta extends GestorHerramienta
{

    @Override
    public void iniciarCU() {
        HerramientaHerramienta=new HerramientaParticular();
        interfaz=new PantallaHerramientaABM(this);
        interfaz.setVisible(true);
    }

    private void validar(HerramientaParticular mh) throws ExceptionGestor
    {
        String mensage="";
       
//        if(p.getCliente()==null)
//            mensage+="\n no se asigno un cliente al pedido";
//

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);

    }

    @Override
    public void ejecutarCU(HerramientaParticular mh) throws ExceptionGestor {
//        mh.setTEHerramienta(EstadoHerramientaBD.getEstadoDisponible());
        validar(mh);
        //mh.setEstadoHerramienta(null);
        HerramientaBD.guardar(mh);
    }


  


}
