/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion.Planificacion.Recursos;

import Negocio.Produccion.MaquinaParticular;

/**
 *
 * @author Dicsys
 */
public class MaquinaPlan {

    private MaquinaParticular mp;
    private boolean operativa;//que no esta rota
    private boolean ocupada;

    public MaquinaPlan(MaquinaParticular maquina,boolean operativa)
    {
        this.mp=maquina;
        this.operativa=operativa;
        this.ocupada=false;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public boolean isOperativa() {
        return operativa;
    }

    public void setOperativa(boolean operativa) {
        this.operativa = operativa;
    }



}
