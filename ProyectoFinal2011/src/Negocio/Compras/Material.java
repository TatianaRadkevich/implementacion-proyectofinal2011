package Negocio.Compras;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA

import Negocio.Deposito.Faltante;
import Negocio.Produccion.UnidadMedida;
import Presentacion.Utilidades;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;

/**
 * TMateriales generated by hbm2java
 */
@Entity
@Table(name = "T_MATERIALES", schema = "dbo", catalog = "Ramaty")
public class Material implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_MATERIAL", unique = true, nullable = false, precision = 3, scale = 0)
    private Short idMaterial;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_UNIDAD_MEDIDA")//, nullable=false)
    private UnidadMedida TUnidadesMedida;
    @Column(name = "NOMBRE", nullable = true, length = 50)
    private String nombre;
    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;
    @Column(name = "DIAMETRO", precision = 6, scale = 0)
    private Integer diametro;
    @Column(name = "ES_MATERIA_PRIMA", nullable = true)
    private boolean esMateriaPrima;
    @Column(name = "LOGITUD", precision = 3, scale = 0)
    private Short logitud;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FEC_BAJA", length = 23)
    private Date fecBaja;
    @Column(name = "MOTIVO_BAJA", length = 200)
    private String motivoBaja;
    @Column(name = "STOCK_ACTUAL", nullable = true, precision = 3, scale = 0)
    private Short stockActual;
    @Column(name = "STOCK_MINIMO", nullable = true, precision = 3, scale = 0)
    private Short stockMinimo;
    @Column(name = "STOCK_RESERVADO", nullable = true, precision = 3, scale = 0)
    private Short stockReservado=new Short("0");
    @Column(name = "CODIGO", nullable = true, length = 6)
    private String codigo;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TMateriales")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Set<MaterialesXProveedor> TMaterialesXProveedors = new HashSet<MaterialesXProveedor>(0);
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TMateriales")
//    private Set<DetalleProducto> TDetallesProductos = new HashSet<DetalleProducto>(0);
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TMateriales")
//    private Set<DetalleEtapaProduccion> TDetallesEtapas = new HashSet<DetalleEtapaProduccion>(0);
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TMateriales")
    private Set<Faltante> TFaltanteses = new HashSet<Faltante>(0);
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TMateriales")
//    private Set<DetalleOrdenCompra> TDetallesOrdenCompras = new HashSet<DetalleOrdenCompra>(0);

    public Material() {
    }

    public Material(Short idMaterial, String nombre, boolean esMateriaPrima, Short stockActual, Short stockMinimo, Short stockReservado, String codigo) {
        this.idMaterial = idMaterial;
        this.nombre = nombre;
        this.esMateriaPrima = esMateriaPrima;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.stockReservado = stockReservado;
        this.codigo = codigo;
    }

    public Material(Short idMaterial, String nombre, String descripcion, Integer diametro, boolean esMateriaPrima, Short logitud, Date fecBaja, String motivoBaja, Short stockActual, Short stockMinimo, Short stockReservado, String codigo, Set<MaterialesXProveedor> TMaterialesXProveedors) {
        this.idMaterial = idMaterial;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.diametro = diametro;
        this.esMateriaPrima = esMateriaPrima;
        this.logitud = logitud;
        this.fecBaja = fecBaja;
        this.motivoBaja = motivoBaja;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.stockReservado = stockReservado;
        this.codigo = codigo;
        this.TMaterialesXProveedors = TMaterialesXProveedors;
    }

    public Short getIdMaterial() {
        return this.idMaterial;
    }

    public void setIdMaterial(Short idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDiametro() {
        return this.diametro;
    }

    public void setDiametro(Integer diametro) {
        this.diametro = diametro;
    }

    public boolean isMateriaPrima() {
        return this.esMateriaPrima;
    }

    public void setEsMateriaPrima(boolean esMateriaPrima) {
        this.esMateriaPrima = esMateriaPrima;
    }

    public Short getLogitud() {
        return this.logitud;
    }

    public void setLogitud(Short logitud) {
        this.logitud = logitud;
    }

    public Date getFechaBaja() {
        return this.fecBaja;
    }

    public void setFechaBaja(Date fecBaja) {
        this.fecBaja = fecBaja;
    }

    public String getMotivoBaja() {
        return this.motivoBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

    public Short getStockActual() {
        return this.stockActual;
    }

    public void setStockActual(Short stockActual) {
        this.stockActual = stockActual;
    }

    public Short getStockMinimo() {
        return this.stockMinimo;
    }

    public void setStockMinimo(Short stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

//    public Short getStockReservado() {
//        return this.stockReservado;
//    }
//
//    public void setStockReservado(Short stockReservado) {
//        this.stockReservado = stockReservado;
//    }
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Proveedor> getProveedores() {
        ArrayList<Proveedor> salida = new ArrayList<Proveedor>(TMaterialesXProveedors.size());
        for (MaterialesXProveedor mxp : this.TMaterialesXProveedors) {
            salida.add(mxp.getProveedor());
        }
        return salida;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        TMaterialesXProveedors.clear();
        for (Proveedor p : proveedores) {
            TMaterialesXProveedors.add(new MaterialesXProveedor(p, this));
        }
    }

    public UnidadMedida getUnidadMedida() {
        return this.TUnidadesMedida;
    }

    public void setUnidadMedida(UnidadMedida unidad) {
        this.TUnidadesMedida = unidad;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }

    public List<MaterialesXProveedor> getMaterialXProveedor() {
        return new ArrayList<MaterialesXProveedor>(this.TMaterialesXProveedors);
    }

    public Float getPrecio(Proveedor pro) {
        for (MaterialesXProveedor mp : this.TMaterialesXProveedors) {
            if (mp.getProveedor().equals(pro)) {
                return mp.getPrecio();
            }
        }
        return null;
    }

    public Integer getCantidadFaltante() {

        Integer cantReq = getCantidadRequerida();        
        Integer faltantes=0;
        
        if(cantReq>=getStockActual())
        {
            faltantes=cantReq-getStockActual();
            faltantes+=getStockMinimo();
        }
        else
        {
            if((getStockActual()-cantReq)<getStockMinimo())
                faltantes+=getStockMinimo()-(getStockActual()-cantReq);            
        }
        
        return faltantes;
        
      
    }
    
       public Integer getCantidadRequerida() {

        Integer faltantes = 0;

        for (Faltante f : this.getFaltantes()) {
            if (f.getFecNecesidad().compareTo(Utilidades.getFechaActual()) <= 0 && f.getDetalleOrdenCompra()==null) {
                faltantes += f.getCantidad().intValue();
            }
        }        

        return faltantes;
    }

    public Integer getStockReservado() {
        Integer cantReq = getCantidadRequerida();
        
        if(cantReq>=getStockActual())
            return getStockActual().intValue();
        else
            return cantReq;

    }

    public Integer getStockDisponible() {
        return this.getStockActual() - getStockReservado();
    }

    public List<Faltante> getFaltantes() {
        return new ArrayList<Faltante>(TFaltanteses);
    }

//    public void setFaltantes(List<Faltante> faltantes) {
//        TFaltanteses.clear();
//        for (Faltante f : faltantes) {
//            f.setMaterial(this);
//            this.TFaltanteses.add(f);
//        }
//    }
}
