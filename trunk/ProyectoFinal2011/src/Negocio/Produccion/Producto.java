/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import java.util.ArrayList;

/**
 *
 * @author Rodrigo
 */
public class Producto {
    private int codigo;
    private String descripcion;
    private ArrayList<DetalleProducto> detalleProducto;
    private ArrayList<EtapaProduccion> etapasProduccion;
    private String nombre;
    private TipoProducto tipo;
    private float precioUnit;

    public Producto() {
        detalleProducto=new ArrayList<DetalleProducto>();
        etapasProduccion=new ArrayList<EtapaProduccion>();
    }

    public Producto(int codigo, String descripcion, String nombre,float precioUnit, TipoProducto tipo) {
        this();
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precioUnit=precioUnit;
    }

    public float getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(float precioUnit) {
        this.precioUnit = precioUnit;
    }



    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<DetalleProducto> getDetalleProducto() {
        return detalleProducto;
    }

    public void setDetalleProducto(ArrayList<DetalleProducto> detalleProducto) {
        this.detalleProducto = detalleProducto;
    }

    public ArrayList<EtapaProduccion> getEtapasProduccion() {
        return etapasProduccion;
    }

    public void setEtapasProduccion(ArrayList<EtapaProduccion> etapasProduccion) {
        this.etapasProduccion = etapasProduccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Producto)
        {
            Producto aux=(Producto) obj;
            if(codigo == aux.getCodigo())
                return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return codigo+"|"+nombre;
    }
    


}
