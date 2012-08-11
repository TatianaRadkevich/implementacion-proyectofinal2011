/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Compras;

import BaseDeDatos.Compras.MaterialBD;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Compras.PantallaMaterialABM;

/**
 *
 * @author Rodrigo
 */
public class GestorMaterialAlta extends GestorMaterial{


  

    @Override
    public void iniciarCU() {
        material=new Material();
        interfaz=new PantallaMaterialABM(this);
        interfaz.setVisible(true);
      
    }

    @Override
    public void ejecutarCU(Material m) throws ExceptionGestor {
       // m.setStockReservado((short)0);
         validar(m);
        MaterialBD.guardar(m);
    }

    @Override
    public void validar(Material m) throws ExceptionGestor {
        String mensage="";


        if(MaterialBD.existeNombre(m.getNombre()))
            mensage+="\n El nombre \""+m.getNombre()+"\" ya existe, porfavor escriba otro nombre";
        if(m.getStockActual()==null)
            mensage+="\n El campo \"stock actual\" no puede dejarse vacio";
        if(m.getStockMinimo()==null)
            mensage+="\n El campo \"stock minimo\" no puede dejarse vacio";

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);
    }

}
