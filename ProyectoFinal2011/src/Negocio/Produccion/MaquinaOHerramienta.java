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

private String codigo;
private String Nombre;
private String caracteristicas;
private float capacidadProductiva;
private String modelo;
private String observaciones;
private TipoMaquinaOHerramienta tipoMaqOHerr;
private EstadoMaquina estado;

    public MaquinaOHerramienta(String codigo, String Nombre, String caracteristicas, float capacidadProductiva, String modelo, String observaciones, TipoMaquinaOHerramienta tipoMaqOHerr, EstadoMaquina estado) {
        this.codigo = codigo;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public EstadoMaquina getEstado() {
        return estado;
    }

    public void setEstado(EstadoMaquina estado) {
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

    @Override
    public String toString() {
        return "MaquinaOHerramienta{" + "codigo=" + codigo + "Nombre=" + Nombre + "caracteristicas=" + caracteristicas + "capacidadProductiva=" + capacidadProductiva + "modelo=" + modelo + "observaciones=" + observaciones + "tipoMaqOHerr=" + tipoMaqOHerr.toString() + "estado=" + estado.toString() + '}';
    }



}
