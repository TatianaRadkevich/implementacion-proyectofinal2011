package Negocio.Produccion;
// Generated 19/08/2011 17:02:19 by Hibernate Tools 3.2.1.GA

import java.math.BigDecimal;
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
 * TProductos generated by hbm2java
 */
@Entity
@Table(name = "T_PRODUCTOS", schema = "dbo", catalog = "Ramaty")
public class Producto implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_PRODUCTO", unique = true, nullable = false, precision = 5, scale = 0)
    private int idProducto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_UNIDAD_MEDIDA", nullable = false)
    private UnidadMedida TUnidadesMedida;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TPRODUCTO", nullable = false)
    private TipoProducto TTproducto;
    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;
    @Column(name = "PRECIO_UNITARIO", nullable = false, precision = 4)
    private BigDecimal precioUnitario;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FEC_BAJA", length = 23)
    private Date fecBaja;
    @Column(name = "MOTIVO_BAJA", length = 100)
    private String motivoBaja;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TProductos")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Set<EtapaProduccionEspecifica> EtapaProduccionEspecifica = new HashSet(0);
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TProductos")
    private Set<DetalleProducto> DetalleProducto = new HashSet(0);

    public Producto() {
    }

    public Producto(int idProducto, UnidadMedida TUnidadesMedida, TipoProducto TTproducto, String nombre, BigDecimal precioUnitario) {
        this.idProducto = idProducto;
        this.TUnidadesMedida = TUnidadesMedida;
        this.TTproducto = TTproducto;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
    }

    public Producto(int idProducto, UnidadMedida TUnidadesMedida, TipoProducto TTproducto, String descripcion, String nombre, BigDecimal precioUnitario, Date fecBaja, String motivoBaja, Set TEtapasProduccionEspecificas, Set TDetallesProductos) {
        this.idProducto = idProducto;
        this.TUnidadesMedida = TUnidadesMedida;
        this.TTproducto = TTproducto;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.fecBaja = fecBaja;
        this.motivoBaja = motivoBaja;
        this.EtapaProduccionEspecifica = TEtapasProduccionEspecificas;
        this.DetalleProducto = TDetallesProductos;
    }

    public int getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public TipoProducto getTTproducto() {
        return this.TTproducto;
    }

    public void setTTproducto(TipoProducto TTproducto) {
        this.TTproducto = TTproducto;
    }

    public UnidadMedida getTUnidadesMedida() {
        return this.TUnidadesMedida;
    }

    public void setTUnidadesMedida(UnidadMedida TUnidadesMedida) {
        this.TUnidadesMedida = TUnidadesMedida;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecioUnitario() {
        return this.precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Date getFecBaja() {
        return this.fecBaja;
    }

    public void setFecBaja(Date fecBaja) {
        this.fecBaja = fecBaja;
    }

    public String getMotivoBaja() {
        return this.motivoBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

    public Set<EtapaProduccionEspecifica> getTEtapasProduccionEspecificas() {
        return this.EtapaProduccionEspecifica;
    }

    public void setTEtapasProduccionEspecificas(Set<EtapaProduccionEspecifica> etapas) {
        if (EtapaProduccionEspecifica == etapas) {
            return;
        }
        EtapaProduccionEspecifica.clear();
        for (EtapaProduccionEspecifica et : etapas) {
            et.setProducto(this);
            this.EtapaProduccionEspecifica.add(et);
        }
    }

    public List<DetalleProducto> getTDetallesProductos() {
         return new ArrayList<DetalleProducto>(DetalleProducto);
    }

    public void setTDetallesProductos(List<DetalleProducto> detalle) {
        this.DetalleProducto.clear();
        for (DetalleProducto dt : detalle)
        {
            dt.setTProductos(this);
            this.DetalleProducto.add(dt);
        }
    }

    public String codigoMerge() {
        return this.getTTproducto().getCodigo() + "-" + this.getIdProducto();
    }

    public String toString() {
        return this.getNombre() + "(" + this.codigoMerge() + ")";
    }

    public Object getInfoColumna(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return this.codigoMerge();
            case 1:
                return this.getNombre();
            case 2:
                return this.getTTproducto().getNombre();

        }
        return null;
    }

    public boolean estaBaja() {
        if (fecBaja == null) {
            return false;
        }
        return true;

    }
}
