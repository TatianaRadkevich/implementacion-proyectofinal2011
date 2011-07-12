/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Ventas;
import Negocio.UbicacionGeografica.Domicilio;
import java.io.Serializable;

/**
 *
 * @author Ivan
 */
public class Cliente implements Serializable
{
    private String correoElectronico;
    private long cuil;
    private Domicilio domicilio;
    private Pedido[] pedidos;
    private String razonSocial;
    private Responsable[] responsables;
    private TipoCliente tipo;
    private ClienteWeb usuario;

    public Cliente() {
        razonSocial="";
        cuil=0;
    }

    public Cliente(long cuil, String razonSocial) {
        this.cuil = cuil;
        this.razonSocial = razonSocial;
    }

    public Cliente(String correoElectronico, int cuil, Domicilio domicilio, String razonSocial, TipoCliente tipo) {
        this.correoElectronico = correoElectronico;
        this.cuil = cuil;
        this.domicilio = domicilio;
        this.razonSocial = razonSocial;
        this.tipo = tipo;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public long getCuil() {
        return cuil;
    }

    public void setCuil(long cuil) {
        this.cuil = cuil;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Pedido[] getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedido[] pedidos) {
        this.pedidos = pedidos;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Responsable[] getResponsables() {
        return responsables;
    }

    public void setResponsables(Responsable[] responsables) {
        this.responsables = responsables;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }

    public ClienteWeb getUsuario() {
        return usuario;
    }

    public void setUsuario(ClienteWeb usuario) {
        this.usuario = usuario;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cliente)
        {
            Cliente aux=(Cliente) obj;
            if(razonSocial.compareToIgnoreCase(aux.getRazonSocial())==0)
                return true;

            if(cuil == aux.getCuil())
                return true;

        }

        return false;
    }

    @Override
    public String toString() {
        return razonSocial;
    }




}
