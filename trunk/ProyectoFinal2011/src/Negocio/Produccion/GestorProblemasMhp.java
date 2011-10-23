/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.Produccion.ProblemasMhpBD;
import BaseDeDatos.Produccion.TipoMaquinaHerramientaBD;
import java.util.List;

/**
 *
 * @author Heber Parrucci
 */
public class GestorProblemasMhp {


    public List<TipoMaquinaHerramienta> listarTipoMaqYHerr() {
        return TipoMaquinaHerramientaBD.listarTipoMaquinaHerramienta();
    }

    public List<TipoMaquinaHerramienta> listarTipoMaq() {
        return TipoMaquinaHerramientaBD.listarTipoMaquina();
    }

    public List<TipoMaquinaHerramienta> listarTipoHerr() {
        return TipoMaquinaHerramientaBD.listarTipoHerramienta();
    }

    public List<MaquinaHerramientaParticular> getMaquinas(TipoMaquinaHerramienta tmh) {
        if (tmh == null) {
            throw new RuntimeException("GestorEstructura.getProductos\nTipo de producto null");
        }
        return BaseDeDatos.Produccion.MaquinaHerramientaBD.getMaquinasHerramientas(tmh, true, false);
    }

    public ProblemasMhp guardar(ProblemasMhp pro){
        return ProblemasMhpBD.guardar(pro);
    }


    public ProblemasMhp modificar(ProblemasMhp pro){
        return ProblemasMhpBD.modificar(pro);
    }

}
