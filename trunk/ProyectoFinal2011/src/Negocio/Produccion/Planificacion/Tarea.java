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

    private Empleado operario;
    private MaquinaParticular maquina;
//    private ArrayList<Recurso<HerramientaParticular, Integer>> herramientas;
//    private ArrayList<Recurso<Material, Integer>> materiales;
    private EtapaProduccionEspecifica etapa;
    private Pedido pedido;
    private Date fechaInicio,fechaFin;
    
    public void liberarRecusos()
    {
        
    }
}
