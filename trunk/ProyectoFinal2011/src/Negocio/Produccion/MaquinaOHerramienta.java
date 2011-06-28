/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

/**
 *
 * @author Heber Parrucci
 */
public class MaquinaOHerramienta {

private int codigo;
private int numeroSerie;
private String Nombre;
private String caracteristicas;
private float capacidadProductiva;
private String modelo;
private String observaciones;
private TipoMaquinaOHerramienta tipoMaqOHerr;
private String estado;

    public MaquinaOHerramienta(int codigo,int numeroSerie, String Nombre, String caracteristicas, float capacidadProductiva, String modelo, String observaciones, TipoMaquinaOHerramienta tipoMaqOHerr, String estado) {
        this.codigo = codigo;
        this.numeroSerie= numeroSerie;
        this.Nombre = Nombre;
        this.caracteristicas = caracteristicas;
        this.capacidadProductiva = capacidadProductiva;
        this.modelo = modelo;
        this.observaciones = observaciones;
        this.tipoMaqOHerr = tipoMaqOHerr;
        this.estado = estado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public float getCapacidadProductiva() {
        return capacidadProductiva;
    }

    public void setCapacidadProductiva(float capacidadProductiva) {
        this.capacidadProductiva = capacidadProductiva;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public TipoMaquinaOHerramienta getTipoMaqOHerr() {
        return tipoMaqOHerr;
    }

    public void setTipoMaqOHerr(TipoMaquinaOHerramienta tipoMaqOHerr) {
        this.tipoMaqOHerr = tipoMaqOHerr;
    }

    public int getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(int numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    @Override
    public String toString() {
        return "MaquinaOHerramienta{" + "codigo=" + codigo + "numeroSerie=" + numeroSerie + "Nombre=" + Nombre + "caracteristicas=" + caracteristicas + "capacidadProductiva=" + capacidadProductiva + "modelo=" + modelo + "observaciones=" + observaciones + "tipoMaqOHerr=" + tipoMaqOHerr + "estado=" + estado + '}';
    }




}
