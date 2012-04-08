/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Produccion;

import BaseDeDatos.Produccion.*;
import Negocio.Exceptiones.ExceptionGestor;
import Presentacion.Mensajes;
import Presentacion.Produccion.PantallaTipoMaquinaABM;
import Presentacion.Utilidades;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class GestorTipoMaquina {

    /*************************************************************************/
    private interface gestor {

        public void iniciarCU();

        public void validar(TipoMaquina tmh) throws ExceptionGestor;

        public void ejecutarCU(TipoMaquina tmh) throws ExceptionGestor;
    }
    /*************************************************************************/




    private gestor comportamiento;
    protected PantallaTipoMaquinaABM interfaz;
    protected TipoMaquina TipoMaquina;

    public GestorTipoMaquina() {
        this.interfaz=new PantallaTipoMaquinaABM(this);
    }

    public void administar() {
        interfaz.setVisible(true);
    }

    public void iniciarNuevo() {
        TipoMaquina=new TipoMaquina();
        comportamiento=nuevo;
        comportamiento.iniciarCU();
    }

    public void iniciarModificar(TipoMaquina tmh) {
        TipoMaquina=tmh;
        comportamiento=Modificar;
        comportamiento.iniciarCU();
    }

    public void iniciarBaja(TipoMaquina tmh) {
        TipoMaquina=tmh;
        comportamiento=Baja;
        comportamiento.iniciarCU();
    }

   public void iniciarAlta(TipoMaquina tmh) {
        TipoMaquina=tmh;
        comportamiento=Alta;
        comportamiento.iniciarCU();
    }

    public void ejecutarCU(TipoMaquina tmh) throws ExceptionGestor {
        comportamiento.ejecutarCU(tmh);
    }

    public void validar(TipoMaquina tmh) throws ExceptionGestor {
        comportamiento.validar(tmh);
    }

    public void finalizarCU() {
        interfaz.limpiar();
        interfaz.habilitarCampos(false);
        interfaz.habilitarConfirmacion(false);
    }

    public TipoMaquina getTipoMaquina() {
        return TipoMaquina;
    }

    public List<TipoMaquina> listarTipoMaquina() {
        return TipoMaquinaBD.listarTipoMaquina();
    }

    /*************************************************************************/

    private gestor nuevo = new gestor() {

        public void iniciarCU() {
                  interfaz.limpiar();
            interfaz.habilitarCampos(true);
        interfaz.habilitarConfirmacion(true);
        interfaz.habilitarBaja(false, null, "");
        }

        

        public void validar (TipoMaquina tmh) throws ExceptionGestor {
                    String mensage="";

//        if(p.getCliente()==null)
//            mensage+="\n no se asigno un cliente al pedido";//

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);

        }
public void ejecutarCU(TipoMaquina tmh) throws ExceptionGestor {
                  validar(tmh);
        //mh.setEstadoMaquina(null);
        TipoMaquinaBD.guardar(tmh);
        Mensajes.mensajeInformacion("El Tipo de Maquina \""+tmh.getNombre()+"\" ha sido guardado exitosamente.");
        finalizarCU();
        }

  
    };
    /*************************************************************************/
    private gestor Modificar = new gestor() {

        public void iniciarCU() {
                  if(TipoMaquina==null)
            throw new RuntimeException("GestorTipoMaquinaModificar: Se debe definir el tipo maquina/herramienta a modificar");


        interfaz.cargar(TipoMaquina);
                    interfaz.habilitarCampos(true);
        interfaz.habilitarConfirmacion(true);
        interfaz.habilitarBaja(false, null, "");
        }

        public void validar (TipoMaquina tmh) throws ExceptionGestor {
                  String mensage="";

//        if(p.getCliente()==null)
//            mensage+="\n no se asigno un cliente al pedido";
//

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);
        }

        public void ejecutarCU(TipoMaquina tmh) throws ExceptionGestor {
                 validar(tmh);
        TipoMaquinaBD.modificar(tmh);
        Mensajes.mensajeInformacion("El Tipo de Maquina \""+tmh.getNombre()+"\" ha sido modificado exitosamente.");
        finalizarCU();
        }
    };
    /*************************************************************************/
    private gestor Baja = new gestor() {

        public void iniciarCU() {
               if(TipoMaquina==null)
            throw new RuntimeException("GestorTipoMaquinaBaja: Se debe definir el tipo maquina/herramienta a eliminar");


        interfaz.cargar(TipoMaquina);
        interfaz.habilitarCampos(false);
        interfaz.habilitarConfirmacion(true);
        interfaz.habilitarBaja(true, Utilidades.getFechaActual(), "");
        }

        public void validar (TipoMaquina tmh) throws ExceptionGestor {
                    String mensage="";

//        if(p.getCliente()==null)
//            mensage+="\n no se asigno un cliente al pedido";
//

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);
        }

        public void ejecutarCU(TipoMaquina tmh) throws ExceptionGestor {
                 validar(tmh);
        tmh.setFecBaja(Utilidades.getFechaActual());
        TipoMaquinaBD.modificar(tmh);
        Mensajes.mensajeInformacion("El Tipo de Maquina \""+tmh.getNombre()+"\" ha sido eliminado exitosamente.");
        finalizarCU();
        }
    };

    private gestor Alta = new gestor() {

        public void iniciarCU() {
               if(TipoMaquina==null&& TipoMaquina.getFecBaja()==null)
            throw new RuntimeException("GestorTipoMaquinaBaja: Se debe definir el tipo maquina/herramienta eliminado para derle de alta");


        interfaz.cargar(TipoMaquina);
        interfaz.habilitarCampos(false);
        interfaz.habilitarConfirmacion(true);
        interfaz.habilitarBaja(false, TipoMaquina.getFecBaja(), TipoMaquina.getMotivoBaja());
        }

        public void validar (TipoMaquina tmh) throws ExceptionGestor {
                    String mensage="";

//        if(p.getCliente()==null)
//            mensage+="\n no se asigno un cliente al pedido";
//

        if(mensage.isEmpty()==false)
            throw new ExceptionGestor("Problemas:"+mensage);
        }

        public void ejecutarCU(TipoMaquina tmh) throws ExceptionGestor {
                 validar(tmh);
        tmh.setFecBaja(null);
        tmh.setMotivoBaja("");
        TipoMaquinaBD.modificar(tmh);
        finalizarCU();
        }
    };
}
