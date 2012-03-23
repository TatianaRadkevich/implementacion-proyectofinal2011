/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.Produccion.ProblemasMhpBD;
import BaseDeDatos.Produccion.TipoMaquinaBD;
import java.util.List;

/**
 *
 * @author Heber Parrucci
 */
public class GestorProblemasMhp {


    public List<TipoMaquina> listarTipoMaq() {
        return TipoMaquinaBD.listarTipoMaquina();
    }



    public List<MaquinaParticular> getMaquinas(TipoMaquina tm) {
        if (tm == null) {
            throw new RuntimeException("GestorEstructura.getProductos\nTipo de producto null");
        }
        return BaseDeDatos.Produccion.MaquinaBD.getMaquinas(tm, true, false);
    }

    public ProblemasMhp guardar(ProblemasMhp pro){
        return ProblemasMhpBD.guardar(pro);
    }


    public ProblemasMhp modificar(ProblemasMhp pro){
        return ProblemasMhpBD.modificar(pro);
    }

}
