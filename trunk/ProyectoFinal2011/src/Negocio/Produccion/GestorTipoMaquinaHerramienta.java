/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Produccion;

import BaseDeDatos.Produccion.*;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Mensajes;
import Presentacion.Produccion.PantallaMaquinaHerramientaTipoABM;
import Presentacion.Utilidades;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class GestorTipoMaquinaHerramienta {
 
    private gestor comportamiento;
    protected PantallaMaquinaHerramientaTipoABM interfaz;
    protected TipoMaquinaHerramienta tipoMaquinaHerramienta;

    public GestorTipoMaquinaHerramienta()
    {
        this.interfaz=new PantallaMaquinaHerramientaTipoABM(this);
    }

    public void administar()
    {
        interfaz.setVisible(true);
    }

    public void iniciarNuevo() {
        tipoMaquinaHerramienta=new TipoMaquinaHerramienta();
        comportamiento=nuevo;
        comportamiento.iniciarCU();
    }

    public void iniciarModificar(TipoMaquinaHerramienta tmh) {
        tipoMaquinaHerramienta=tmh;
        comportamiento=Modificar;
        comportamiento.iniciarCU();
    }

    public void iniciarBaja(TipoMaquinaHerramienta tmh) {
        tipoMaquinaHerramienta=tmh;
        comportamiento=Baja;
        comportamiento.iniciarCU();
    }

   public void iniciarAlta(TipoMaquinaHerramienta tmh) {
        tipoMaquinaHerramienta=tmh;
        comportamiento=Alta;
        comportamiento.iniciarCU();
    }

    public void ejecutarCU(TipoMaquinaHerramienta tmh) throws ExceptionGestor {
        comportamiento.ejecutarCU(tmh);
    }

    public void validar(TipoMaquinaHerramienta tmh) throws ExceptionGestor {
        comportamiento.validar(tmh);
    }

    public void finalizarCU() {
        interfaz.limpiar();
        interfaz.habilitarCampos(false);
        interfaz.habilitarConfirmacion(false);
    }

    public TipoMaquinaHerramienta getTipoMaquinaHerramienta() {
        return tipoMaquinaHerramienta;
    }

    public List<TipoMaquinaHerramienta> listarTipoMaquinaHerramienta() {
        return TipoMaquinaHerramientaBD.listarTipoMaquinaHerramienta();
    }



    /*************************************************************************/
    private interface gestor {

        public void iniciarCU();

        public void validar(TipoMaquinaHerramienta tmh) throws ExceptionGestor;

        public void ejecutarCU(TipoMaquinaHerramienta tmh) throws ExceptionGestor;

    }
    /*************************************************************************/
    private gestor nuevo = new gestor() {

        public void iniciarCU() {
                  interfaz.limpiar();
            interfaz.habilitarCampos(true);
        interfaz.habilitarConfirmacion(true);
        interfaz.habilitarBaja(false, null, "");
        }

        

        public void validar (TipoMaquinaHerramienta tmh) throws ExceptionGestor {
                    String mensage="";

//        if(p.getCliente()==null)
//            mensage+="\n no se asigno un cliente al pedido";//

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);

        }
public void ejecutarCU(TipoMaquinaHerramienta tmh) throws ExceptionGestor {
                  validar(tmh);
        //mh.setEstadoMaquina(null);
        TipoMaquinaHerramientaBD.guardar(tmh);
        Mensajes.mensajeInformacion("El Tipo de Maquina \""+tmh.getNombre()+"\" ha sido guardado exitosamente.");
        finalizarCU();
        }

  
    };
    /*************************************************************************/
    private gestor Modificar = new gestor() {

        public void iniciarCU() {
                  if(tipoMaquinaHerramienta==null)
            throw new RuntimeException("GestorTipoMaquinaHerramientaModificar: Se debe definir el tipo maquina/herramienta a modificar");


        interfaz.cargar(tipoMaquinaHerramienta);
                    interfaz.habilitarCampos(true);
        interfaz.habilitarConfirmacion(true);
        interfaz.habilitarBaja(false, null, "");
        }

        public void validar (TipoMaquinaHerramienta tmh) throws ExceptionGestor {
                  String mensage="";

//        if(p.getCliente()==null)
//            mensage+="\n no se asigno un cliente al pedido";
//

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);
        }

        public void ejecutarCU(TipoMaquinaHerramienta tmh) throws ExceptionGestor {
                 validar(tmh);
        TipoMaquinaHerramientaBD.modificar(tmh);
        Mensajes.mensajeInformacion("El Tipo de Maquina \""+tmh.getNombre()+"\" ha sido modificado exitosamente.");
        finalizarCU();
        }
    };
    /*************************************************************************/
    private gestor Baja = new gestor() {

        public void iniciarCU() {
               if(tipoMaquinaHerramienta==null)
            throw new RuntimeException("GestorTipoMaquinaHerramientaBaja: Se debe definir el tipo maquina/herramienta a eliminar");


        interfaz.cargar(tipoMaquinaHerramienta);
        interfaz.habilitarCampos(false);
        interfaz.habilitarConfirmacion(true);
        interfaz.habilitarBaja(true, Utilidades.getFechaActual(), "");
        }

        public void validar (TipoMaquinaHerramienta tmh) throws ExceptionGestor {
                    String mensage="";

//        if(p.getCliente()==null)
//            mensage+="\n no se asigno un cliente al pedido";
//

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);
        }

        public void ejecutarCU(TipoMaquinaHerramienta tmh) throws ExceptionGestor {
                 validar(tmh);
        tmh.setFechaBaja(Utilidades.getFechaActual());
        TipoMaquinaHerramientaBD.modificar(tmh);
        Mensajes.mensajeInformacion("El Tipo de Maquina \""+tmh.getNombre()+"\" ha sido eliminado exitosamente.");
        finalizarCU();
        }
    };

        private gestor Alta = new gestor() {

        public void iniciarCU() {
               if(tipoMaquinaHerramienta==null&& tipoMaquinaHerramienta.getFechaBaja()==null)
            throw new RuntimeException("GestorTipoMaquinaHerramientaBaja: Se debe definir el tipo maquina/herramienta eliminado para derle de alta");


        interfaz.cargar(tipoMaquinaHerramienta);
        interfaz.habilitarCampos(false);
        interfaz.habilitarConfirmacion(true);
        interfaz.habilitarBaja(false, tipoMaquinaHerramienta.getFechaBaja(), tipoMaquinaHerramienta.getMotivoBaja());
        }

        public void validar (TipoMaquinaHerramienta tmh) throws ExceptionGestor {
                    String mensage="";

//        if(p.getCliente()==null)
//            mensage+="\n no se asigno un cliente al pedido";
//

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);
        }

        public void ejecutarCU(TipoMaquinaHerramienta tmh) throws ExceptionGestor {
                 validar(tmh);
        tmh.setFechaBaja(null);
        tmh.setMotivoBaja("");
        TipoMaquinaHerramientaBD.modificar(tmh);
        finalizarCU();
        }
    };
}
