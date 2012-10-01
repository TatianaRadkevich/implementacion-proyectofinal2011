/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Produccion.Planificacion.Recursos;

import java.util.ArrayList;

/**
 *
 * @author Dicsys
 */
public class Recursos {

    private ArrayList<OperarioPlan> operarios = new ArrayList<OperarioPlan>();
    private ArrayList<MaquinaPlan> maquinas = new ArrayList<MaquinaPlan>();
    private ArrayList<MaterialPlan> materiales = new ArrayList<MaterialPlan>();
    private ArrayList<HerramientaPlan> herramientas = new ArrayList<HerramientaPlan>();

    public void addOperario(OperarioPlan op) {
        operarios.add(op);
    }

    public void addMaquina(MaquinaPlan maq) {
        maquinas.add(maq);
    }

    public void addMaterial(MaterialPlan material) {
        materiales.add(material);
    }

    public void addHerramienta(HerramientaPlan herramienta) {
        herramientas.add(herramienta);
    }
}
