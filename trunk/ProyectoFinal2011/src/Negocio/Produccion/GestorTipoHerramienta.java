/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Produccion;

import BaseDeDatos.Produccion.*;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Mensajes;
import Presentacion.Produccion.PantallaTipoHerramientaABM;
import Presentacion.Utilidades;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class GestorTipoHerramienta {

    /*************************************************************************/
    private interface gestor {

        public void iniciarCU();

        public void validar(TipoHerramienta tmh) throws ExceptionGestor;

        public void ejecutarCU(TipoHerramienta tmh) throws ExceptionGestor;
    }
    /*************************************************************************/




    private gestor comportamiento;
    protected PantallaTipoHerramientaABM interfaz;
    protected TipoHerramienta TipoHerramienta;

    public GestorTipoHerramienta()
    {
        this.interfaz=new PantallaTipoHerramientaABM(this);
    }

    public void administar()
    {
        interfaz.setVisible(true);
    }

    public void iniciarNuevo() {
        TipoHerramienta=new TipoHerramienta();
        comportamiento=nuevo;
        comportamiento.iniciarCU();
    }

    public void iniciarModificar(TipoHerramienta tmh) {
        TipoHerramienta=tmh;
        comportamiento=Modificar;
        comportamiento.iniciarCU();
    }

    public void iniciarBaja(TipoHerramienta tmh) {
        TipoHerramienta=tmh;
        comportamiento=Baja;
        comportamiento.iniciarCU();
    }

   public void iniciarAlta(TipoHerramienta tmh) {
        TipoHerramienta=tmh;
        comportamiento=Alta;
        comportamiento.iniciarCU();
    }

    public void ejecutarCU(TipoHerramienta tmh) throws ExceptionGestor {
        comportamiento.ejecutarCU(tmh);
    }

    public void validar(TipoHerramienta tmh) throws ExceptionGestor {
        comportamiento.validar(tmh);
    }

    public void finalizarCU() {
        interfaz.limpiar();
        interfaz.habilitarCampos(false);
        interfaz.habilitarConfirmacion(false);
    }

    public TipoHerramienta getTipoHerramienta() {
        return TipoHerramienta;
    }

    public List<TipoHerramienta> listarTipoHerramienta() {
        return HerramientaBD.listarTipoHerramienta();
    }




    private gestor nuevo = new gestor() {

        public void iniciarCU() {
                  interfaz.limpiar();
            interfaz.habilitarCampos(true);
        interfaz.habilitarConfirmacion(true);
        interfaz.habilitarBaja(false, null, "");
        }



        public void validar (TipoHerramienta tmh) throws ExceptionGestor {
                    String mensage="";

//        if(p.getCliente()==null)
//            mensage+="\n no se asigno un cliente al pedido";//

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);

        }
public void ejecutarCU(TipoHerramienta tmh) throws ExceptionGestor {
                  validar(tmh);
        //mh.setEstadoMaquina(null);
        TipoHerramientaBD.guardar(tmh);
        Mensajes.mensajeInformacion("El Tipo de Maquina \""+tmh.getNombre()+"\" ha sido guardado exitosamente.");
        finalizarCU();
        }


    };
    /*************************************************************************/
    private gestor Modificar = new gestor() {

        public void iniciarCU() {
                  if(TipoHerramienta==null)
            throw new RuntimeException("GestorTipoHerramientaModificar: Se debe definir el tipo maquina/herramienta a modificar");


        interfaz.cargar(TipoHerramienta);
                    interfaz.habilitarCampos(true);
        interfaz.habilitarConfirmacion(true);
        interfaz.habilitarBaja(false, null, "");
        }

        public void validar (TipoHerramienta tmh) throws ExceptionGestor {
                  String mensage="";

//        if(p.getCliente()==null)
//            mensage+="\n no se asigno un cliente al pedido";
//

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);
        }

        public void ejecutarCU(TipoHerramienta tmh) throws ExceptionGestor {
                 validar(tmh);
        TipoHerramientaBD.modificar(tmh);
        Mensajes.mensajeInformacion("El Tipo de Maquina \""+tmh.getNombre()+"\" ha sido modificado exitosamente.");
        finalizarCU();
        }
    };
    /*************************************************************************/
    private gestor Baja = new gestor() {

        public void iniciarCU() {
               if(TipoHerramienta==null)
            throw new RuntimeException("GestorTipoHerramientaBaja: Se debe definir el tipo maquina/herramienta a eliminar");


        interfaz.cargar(TipoHerramienta);
        interfaz.habilitarCampos(false);
        interfaz.habilitarConfirmacion(true);
        interfaz.habilitarBaja(true, Utilidades.getFechaActual(), "");
        }

        public void validar (TipoHerramienta tmh) throws ExceptionGestor {
                    String mensage="";

//        if(p.getCliente()==null)
//            mensage+="\n no se asigno un cliente al pedido";
//

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);
        }

        public void ejecutarCU(TipoHerramienta tmh) throws ExceptionGestor {
                 validar(tmh);
        tmh.setFecBaja(Utilidades.getFechaActual());
        TipoHerramientaBD.modificar(tmh);
        Mensajes.mensajeInformacion("El Tipo de Maquina \""+tmh.getNombre()+"\" ha sido eliminado exitosamente.");
        finalizarCU();
        }
    };

        private gestor Alta = new gestor() {

        public void iniciarCU() {
               if(TipoHerramienta==null&& TipoHerramienta.getFecBaja()==null)
            throw new RuntimeException("GestorTipoHerramientaBaja: Se debe definir el tipo maquina/herramienta eliminado para derle de alta");


        interfaz.cargar(TipoHerramienta);
        interfaz.habilitarCampos(false);
        interfaz.habilitarConfirmacion(true);
        interfaz.habilitarBaja(false, TipoHerramienta.getFecBaja(), TipoHerramienta.getMotivoBaja());
        }

        public void validar (TipoHerramienta tmh) throws ExceptionGestor {
                    String mensage="";

//        if(p.getCliente()==null)
//            mensage+="\n no se asigno un cliente al pedido";
//

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);
        }

        public void ejecutarCU(TipoHerramienta tmh) throws ExceptionGestor {
                 validar(tmh);
        tmh.setFecBaja(null);
        tmh.setMotivoBaja("");
        TipoHerramientaBD.modificar(tmh);
        finalizarCU();
        }
    };


public void guardar(TipoHerramienta tipoMaquina){
         TipoHerramientaBD.guardar(tipoMaquina);
    }


    public void modificar(TipoHerramienta tipoMaquina){
         TipoHerramientaBD.modificar(tipoMaquina);
    }


}
