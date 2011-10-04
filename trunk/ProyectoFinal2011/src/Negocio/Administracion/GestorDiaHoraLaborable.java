/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Administracion;

import BaseDeDatos.Administracion.DiaHoraLaborableBD;

/**
 *
 * @author Ivan
 */
public class GestorDiaHoraLaborable {




    public DiaHoraLaborable guardar(DiaHoraLaborable diahora){
        return DiaHoraLaborableBD.guardar(diahora);
    }


    public DiaHoraLaborable modificar(DiaHoraLaborable diahora){
        return DiaHoraLaborableBD.modificar(diahora);
    }

}
