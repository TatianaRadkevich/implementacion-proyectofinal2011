/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.Produccion.HerramientaBD;
import BaseDeDatos.Produccion.ProblemasMhpBD;
import BaseDeDatos.Produccion.TipoHerramientaBD;
import BaseDeDatos.Produccion.TipoMaquinaBD;
import Presentacion.Produccion.ProblemaHerramientaParticular;
import Presentacion.Produccion.ProblemaMaquinaParticular;
import java.util.List;

/**
 *
 * @author Heber Parrucci
 */
public class GestorProblemasMhp {


    public List<TipoMaquina> listarTipoMaq() {
        return TipoMaquinaBD.listarTipoMaquina();
    }
    public List<TipoHerramienta> listarTipoHerramienta() {
        return TipoHerramientaBD.listarTipoHerramienta();
    }



    public List<MaquinaParticular> getMaquinas(TipoMaquina tm) {
        if (tm == null) {
            throw new RuntimeException("GestorEstructura.getProductos\nTipo de producto null");
        }
        return BaseDeDatos.Produccion.MaquinaBD.getMaquinas(tm, true, false);
    }

    public List<HerramientaParticular> getHerramientas(TipoHerramienta tm) {
        if (tm == null) {
            throw new RuntimeException("GestorEstructura.getProductos\nTipo de producto null");
        }
        return BaseDeDatos.Produccion.HerramientaBD.getHerramientaParticular(tm, true, false);
    }

    public ProblemasMhp guardar(ProblemasMhp pro){
        HerramientaParticular temp=pro.getTHerramientasParticular();
        if(temp.getCantidad()==1)
        {
            pro.getTHerramientasParticular().setCantidad(pro.getTHerramientasParticular().getCantidad()-1);
            
        }
        
        HerramientaBD.modificar(pro.getTHerramientasParticular());
        return ProblemasMhpBD.guardar(pro);
    }


    public ProblemasMhp modificar(ProblemasMhp pro){
        return ProblemasMhpBD.modificar(pro);
    }


    public static void iniciarUC(MaquinaParticular maquina)
    {
        ProblemaMaquinaParticular ventana= new ProblemaMaquinaParticular(null, true);
        ventana.cargarMaquina(maquina);
        ventana.setVisible(true);
    }

    public static void iniciarUCHerramienta(HerramientaParticular herramienta)
    {
        ProblemaHerramientaParticular ventana= new ProblemaHerramientaParticular(null, true);
        ventana.cargarHerramienta(herramienta);
        ventana.setVisible(true);
    }
}
