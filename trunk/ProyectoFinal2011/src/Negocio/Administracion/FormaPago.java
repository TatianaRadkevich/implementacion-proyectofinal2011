package Negocio.Administracion;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA

import BaseDeDatos.Administracion.FormaDePagoBD;
import BaseDeDatos.HibernateUtil;
import Negocio.Administracion.Cobro;
import Negocio.Exceptiones.TipoDatoException;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TFormasPago generated by hbm2java
 */
@Entity
@Table(name = "T_FORMAS_PAGO", schema = "dbo", catalog = "Ramaty")
public class FormaPago implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_FORMA_PAGO", unique = true, nullable = false, precision = 2, scale = 0)
    private short idFormaPago;
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;
    @Column(name = "DESCRIPCION", nullable = true, length = 200)
    private String descripcion;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FEC_BAJA", nullable = true, length = 23)
    private Date fecha;
    @Column(name = "MOTIVO_BAJA", nullable = true, length = 200)
    private String motivo;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TFormasPago")
    private Set<Cobro> TCobroses = new HashSet<Cobro>(0);

    public FormaPago() {
    }

    public FormaPago( String nombre) {        
        this.nombre = nombre;
    }

    public FormaPago(short idFormaPago, String nombre, String descripcion, Date fecha, String motivo, Set<Cobro> TCobroses) {
        this.idFormaPago = idFormaPago;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.motivo = motivo;
        this.TCobroses = TCobroses;
    }

    public short getIdFormaPago() {
        return this.idFormaPago;
    }

    public void setIdFormaPago(short idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) throws TipoDatoException {
        this.nombre = null;

        if (nombre.trim().isEmpty() || nombre.matches("[a-zA-Z ]*") == false) {
            throw new TipoDatoException("Formato incorrecto. Debe ser alfabético");
        }

        FormaPago fp = FormaDePagoBD.getFormaDePagoPorNombre(nombre);
        if (fp != null && fp.getIdFormaPago() != this.idFormaPago) {
            throw new TipoDatoException("Ya existe una forma de pago con ese nombre");
        }

        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) throws TipoDatoException {

        this.descripcion = descripcion;

    }

    public Set<Cobro> getTCobroses() {
        return this.TCobroses;
    }

    public void setTCobroses(Set<Cobro> TCobroses) {
        this.TCobroses = TCobroses;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setMotivo(String motivo) throws TipoDatoException {
        this.motivo = motivo;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public boolean ValidarOk() throws TipoDatoException {
        if (nombre == null) {
            throw new TipoDatoException("Algunos campos no han sido ingresado correctamente.");
        }
        return true;
    }

    public enum Tipo{Efectivo,Cheque;}

    public static FormaPago getFormaPago(Tipo t) {
        String HQL = String.format("FROM FormaPago as fp WHERE LOWER(ep.nombre) = LOWER('%s')", t.name());
        List<FormaPago> lst = HibernateUtil.ejecutarConsulta(HQL);
        if (lst.isEmpty()) {
            HibernateUtil.guardarObjeto(new FormaPago(t.name()));
            return getFormaPago(t);
        }
        return lst.get(0);
    }

    public static List<FormaPago> listarFormaPagos()
    {
        ArrayList<FormaPago> salida=new ArrayList<FormaPago>();
        for(Tipo t:Tipo.values())
        {
            salida.add(getFormaPago(t));
        }
        return salida;
    }

    @Override
    public boolean equals(Object obj) {
        try{
            FormaPago fp=(FormaPago) obj;
            if(fp.nombre.equals(this.nombre))
                return true;
        }catch(Exception e){}
        return false;
    }
}
