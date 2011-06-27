/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.UbicacionGeografica;

/**
 *
 * @author Rodrigo
 */
public class Domicilio {
    private Barrio barrio;
    private String calle;
    private Localidad localidad;
    private String depto;
    private int numero;
    private Pais pais;
    private int piso;
    private Provincia provincia;

    public Domicilio() {
    }

    public Domicilio(Barrio barrio, String calle, Localidad localidad, int numero, Pais pais, Provincia provincia) {
        this.barrio = barrio;
        this.calle = calle;
        this.localidad = localidad;
        this.numero = numero;
        this.pais = pais;
        this.provincia = provincia;
    }

    public Domicilio(Barrio barrio, String calle, Localidad localidad, String depto, int numero, Pais pais, int piso, Provincia provincia) {
        this.barrio = barrio;
        this.calle = calle;
        this.localidad = localidad;
        this.depto = depto;
        this.numero = numero;
        this.pais = pais;
        this.piso = piso;
        this.provincia = provincia;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getDepto() {
        return depto;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

}
