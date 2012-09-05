/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import BaseDeDatos.Produccion.EstadoMaquinaBD;
import BaseDeDatos.Produccion.HerramientaBD;
import BaseDeDatos.Produccion.MaquinaBD;
import BaseDeDatos.Produccion.ProblemasMhpBD;
import BaseDeDatos.Produccion.TipoHerramientaBD;
import BaseDeDatos.Produccion.TipoMaquinaBD;
import Presentacion.Produccion.ProblemaHerramientaParticular;
import Presentacion.Produccion.ProblemaMaquinaParticular;
import java.util.ArrayList;
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
    
    public List<MaquinaParticular> getMaquinasSinProblemas(TipoMaquina tm) {
        if (tm == null) {
            throw new RuntimeException("GestorEstructura.getProductos\nTipo de producto null");
        }
        List<MaquinaParticular> maquina=new ArrayList<MaquinaParticular>();
        boolean ban=true;
        List<MaquinaParticular> maquinaTemp=BaseDeDatos.Produccion.MaquinaBD.getMaquinas(tm, true, false);
        for(MaquinaParticular maq : maquinaTemp)
        {
            List<ProblemasMhp> pro=maq.getTProblemasMhps();
            if(pro.isEmpty() || pro==null)
                maquina.add(maq);
            else
            {
                for(ProblemasMhp p : pro)
                {
                    if(p.getFecHoraRealSolucion() == null)
                    {
                        ban=false;
                        break;
                    }
                    
                }
                if(ban){
                        maquina.add(maq);
                    }
            }
            
        }         
        return maquina;
    }

    public List<HerramientaParticular> getHerramientas(TipoHerramienta tm) {
        if (tm == null) {
            throw new RuntimeException("GestorEstructura.getProductos\nTipo de herramienta null");
        }
        return BaseDeDatos.Produccion.HerramientaBD.getHerramientaParticular(tm, true, false);
    }

    public List<HerramientaParticular> getHerramientasSinProblemas(TipoHerramienta th){

        if (th == null) {
            throw new RuntimeException("GestorEstructura.getProductos\nTipo de herramienta null");
        }
        List<HerramientaParticular> herramienta=new ArrayList<HerramientaParticular>();
        List<HerramientaParticular> herramientaTemp=BaseDeDatos.Produccion.HerramientaBD.getHerramientaParticular(th, true, false);



        for(HerramientaParticular her : herramientaTemp)
        {
            if(her.getCantidad()!=0)
                herramienta.add(her);
        }
        return herramienta;
    }

    public ProblemasMhp guardar(ProblemasMhp pro){

        if(pro.getTHerramientasParticular()!=null)
        {
            HerramientaParticular temp=pro.getTHerramientasParticular();
            if(temp.getCantidad()==1)
            {
                pro.getTHerramientasParticular().setCantidad(pro.getTHerramientasParticular().getCantidad()-1);

            }
            HerramientaBD.modificar(pro.getTHerramientasParticular());
        }
        else
        {
            MaquinaBD.modificar(pro.getTMaquinasParticular());
        }        
        
        return ProblemasMhpBD.guardar(pro);
    }


    public ProblemasMhp modificar(ProblemasMhp pro){
        return ProblemasMhpBD.modificar(pro);
    }


    public ProblemasMhp registrarSolucion(ProblemasMhp pro){
        if(pro.getTMaquinasParticular() != null)
        {
            pro.getTMaquinasParticular().setTEmaquina(EstadoMaquinaBD.getEstadoDisponible());
            MaquinaBD.modificar(pro.getTMaquinasParticular());
        }
        else{
            pro.getTHerramientasParticular().setCantidad(pro.getTHerramientasParticular().getCantidad()+1);
            HerramientaBD.modificar(pro.getTHerramientasParticular());
        }

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
