/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Produccion.Planificacion;

import Negocio.Administracion.Empleado;
import Negocio.Compras.Material;
import Negocio.Produccion.EtapaProduccionEspecifica;
import Negocio.Produccion.HerramientaParticular;
import Negocio.Produccion.MaquinaParticular;
import Negocio.Produccion.Planificacion.Recursos.*;
import Negocio.Ventas.Pedido;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Dicsys
 */
public class Tarea {

    private OperarioPlan operario;
    private MaquinaPlan maquina;
   // private ArrayList<Detalle<HerramientaParticular, Integer>> herramientas;
    private ArrayList<Detalle<MaterialPlan, Integer>> materiales=new ArrayList<Detalle<MaterialPlan, Integer>>();
    private EtapaProduccionEspecifica etapa;
    private Pedido pedido;
    private Date fechaInicio, fechaFin;

    public Tarea() {
      
    }

    public Tarea(OperarioPlan operario, MaquinaPlan maquina, EtapaProduccionEspecifica etapa, Pedido pedido, Date fechaInicio, Date fechaFin) {
        this.operario = operario;
        this.maquina = maquina;
        this.etapa = etapa;
        this.pedido = pedido;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public void liberarRecusos() {
        operario.setOcupado(false);
        maquina.setOcupada(false);
    }

    public EtapaProduccionEspecifica getEtapa() {
        return etapa;
    }

    public void setEtapa(EtapaProduccionEspecifica etapa) {
        this.etapa = etapa;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public MaquinaPlan getMaquina() {
        return maquina;
    }

    public void setMaquina(MaquinaPlan maquina) {
        this.maquina = maquina;
    }

    public OperarioPlan getOperario() {
        return operario;
    }

    public void setOperario(OperarioPlan operario) {
        this.operario = operario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void addMaterial(MaterialPlan mat,int cantidad)
    {
        materiales.add(new Detalle<MaterialPlan, Integer>(mat, cantidad));

    }
}
