/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Deposito;

import BaseDeDatos.Compras.MaterialBD;
import BaseDeDatos.Deposito.ReajusteStockBD;
import Negocio.Compras.Material;

/**
 *
 * @author Sebastian
 */
public class GestorReajustarStock {

    public Material buscarMaterial(String Cod) {
       return MaterialBD.getMaterial(Cod);
    }

    public void Ejecutar(ReajusteStock re)
    {       
        re.getMaterial().setStockActual((short)re.getCantidad());
        ReajusteStockBD.guardar(re);
    }

}
