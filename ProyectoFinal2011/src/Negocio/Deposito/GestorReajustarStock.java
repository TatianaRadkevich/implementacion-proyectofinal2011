/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Deposito;

import BaseDeDatos.Compras.MaterialBD;
import BaseDeDatos.Deposito.ReajusteStockBD;
import Negocio.Compras.Material;
import java.util.List;
//import Presentacion.Deposito.ReajustarStock;

/**
 *
 * @author Sebastian
 */
public class GestorReajustarStock {

    //protected ReajustarStock interfaz;
    public Material buscarMaterial(String Cod) {
       return MaterialBD.getMaterial(Cod);
    }

    public void Ejecutar(ReajusteStock re)
    {               
        ReajusteStockBD.guardar(re);
        
    }

    public List<Material> listarMaterial()
    {
        return MaterialBD.getMateriales("", "", true, false);
    }
}
