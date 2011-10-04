/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Administracion;

import BaseDeDatos.Administracion.DiaHoraLaborableBD;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivan
 */
public class GestorDiaHoraLaborable {



    public List<Dia> getDias(){
        List<Dia> dias=new ArrayList<Dia>();
        dias.add(new Dia("Lunes"));
        dias.add(new Dia("Martes"));
        dias.add(new Dia("Miércoles"));
        dias.add(new Dia("Jueves"));
        dias.add(new Dia("Viernes"));
        dias.add(new Dia("Sábado"));
        dias.add(new Dia("Domingo"));

        return dias;
    }

    public DiaHoraLaborable guardar(DiaHoraLaborable diahora){
        return DiaHoraLaborableBD.guardar(diahora);
    }


    public DiaHoraLaborable modificar(DiaHoraLaborable diahora){
        return DiaHoraLaborableBD.modificar(diahora);
    }

}
