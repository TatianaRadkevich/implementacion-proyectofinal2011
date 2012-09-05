package Negocio.Produccion;
// Generated 18/03/2012 17:10:18 by Hibernate Tools 3.2.1.GA


import BaseDeDatos.Produccion.ProblemasMhpBD;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;

/**
 * TMaquinasParticular generated by hbm2java
 */
@Entity
@Table(name="T_MAQUINAS_PARTICULAR"
    ,schema="dbo"
    ,catalog="Ramaty"
)
public class MaquinaParticular  implements java.io.Serializable {


     @Id
    @GeneratedValue
    @Column(name="ID_MAQUINA_PARTICULAR", unique=true, nullable=false, precision=3, scale=0)
     private short idMaquinaParticular;
     //___________________________________________________________________________________//
     @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_TMAQUINA", nullable=false)
     private TipoMaquina TTmaquina;
     //___________________________________________________________________________________//
     @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_EMAQUINA", nullable=false)
     private EstadoMaquina TEmaquina;
     //___________________________________________________________________________________//
     @Column(name="CAPACIDAD_PRODUCTIVA", nullable=false, precision=5, scale=0)
     private float capacidadProductiva;
     //___________________________________________________________________________________//
     @Column(name="CARACTERISTICAS", nullable=false, length=200)
     private String caracteristicas;
     //___________________________________________________________________________________//
     @Column(name="MODELO", nullable=false, length=50)
     private String modelo;
     //___________________________________________________________________________________//
     @Column(name="NOMBRE", nullable=false, length=50)
     private String nombre;
     //___________________________________________________________________________________//
     @Column(name="OBSERVACIONES", length=200)
     private String observaciones;
     //___________________________________________________________________________________//

     @Column(name="CODIGO", nullable=false, length=2)
     private String codigo;
     //___________________________________________________________________________________//
     @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FEC_BAJA", length=23)
     private Date fecBaja;
     //___________________________________________________________________________________//
     @Column(name="MOTIVO_BAJA", length=100)
     private String motivoBaja;
    // ___________________________________________________________________________________//
     @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TMaquinasParticular")
     @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
     private Set<ProblemasMhp> TProblemasMhps = new HashSet<ProblemasMhp>(0);

       //___________________________________________________________________________________//
   //  private Set TMaqHerrPartXDetPlans = new HashSet(0);
     //___________________________________________________________________________________//

    public MaquinaParticular() {
    }

	
    public MaquinaParticular(short idMaquinaParticular, TipoMaquina TTmaquina, EstadoMaquina TEmaquina, float capacidadProductiva, String caracteristicas, String modelo, String nombre, String codigo) {
        this.idMaquinaParticular = idMaquinaParticular;
        this.TTmaquina = TTmaquina;
        this.TEmaquina = TEmaquina;
        this.capacidadProductiva = capacidadProductiva;
        this.caracteristicas = caracteristicas;
        this.modelo = modelo;
        this.nombre = nombre;
        this.codigo = codigo;
    }
    public MaquinaParticular(short idMaquinaParticular, TipoMaquina TTmaquina, EstadoMaquina TEmaquina, int capacidadProductiva, String caracteristicas, String modelo, String nombre, String observaciones, String codigo, Date fecBaja, String motivoBaja /*, Set TProblemasMhps, Set TMaqHerrPartXDetPlans*/) {
       this.idMaquinaParticular = idMaquinaParticular;
       this.TTmaquina = TTmaquina;
       this.TEmaquina = TEmaquina;
       this.capacidadProductiva = capacidadProductiva;
       this.caracteristicas = caracteristicas;
       this.modelo = modelo;
       this.nombre = nombre;
       this.observaciones = observaciones;
       this.codigo = codigo;
       this.fecBaja = fecBaja;
       this.motivoBaja = motivoBaja;
//       this.TProblemasMhps = TProblemasMhps;
       //this.TMaqHerrPartXDetPlans = TMaqHerrPartXDetPlans;
    }
   
    
    public short getIdMaquinaParticular() {
        return this.idMaquinaParticular;
    }
    
    public void setIdMaquinaParticular(short idMaquinaParticular) {
        this.idMaquinaParticular = idMaquinaParticular;
    }

    public TipoMaquina getTTmaquina() {
        return this.TTmaquina;
    }
    
    public void setTTmaquina(TipoMaquina TTmaquina) throws TipoDatoException{
        if(TTmaquina==null)
            throw new TipoDatoException("Debe seleccionar un tipo de maquina");
        this.TTmaquina = TTmaquina;
    }

    public EstadoMaquina getTEmaquina() {
        return this.TEmaquina;
    }
    
    public void setTEmaquina(EstadoMaquina TEmaquina) {
        this.TEmaquina = TEmaquina;
    }
    
    
    public float getCapacidadProductiva() {
        return this.capacidadProductiva;
    }
    
    public void setCapacidadProductiva(float capacidadProductiva) {
        this.capacidadProductiva = capacidadProductiva;
    }
    
    
    public String getCaracteristicas() {
        return this.caracteristicas;
    }
    
    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    
    
    public String getModelo() {
        return this.modelo;
    }
    
    public void setModelo(String modelo) throws TipoDatoException {
        if(modelo.trim().compareTo("")==0)
            throw new TipoDatoException("Debe ingresar un modelo");
        this.modelo = modelo;
    }
    
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) throws TipoDatoException {
        if(nombre.trim().compareTo("")==0)
            throw new TipoDatoException("Debe ingresar un nombre");
        this.nombre = nombre;
    }
    
    
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
    public String getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public List<ProblemasMhp> getTProblemasMhps() {
        return ProblemasMhpBD.listarProblemasMaquinas(this);
//        List<ProblemasMhp> pro= new ArrayList<ProblemasMhp>();
//        for(ProblemasMhp prob: this.TProblemasMhps ){
//            pro.add(prob);
//        }
       
    }

    public void setTProblemasMhps(Set TProblemasMhps) {
        this.TProblemasMhps = TProblemasMhps;
    }
//@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="TMaquinasParticular")
//    public Set getTMaqHerrPartXDetPlans() {
//        return this.TMaqHerrPartXDetPlans;
//    }
//
//    public void setTMaqHerrPartXDetPlans(Set TMaqHerrPartXDetPlans) {
//        this.TMaqHerrPartXDetPlans = TMaqHerrPartXDetPlans;
//    }
//


    public boolean validarOk() throws TipoDatoException{
        String mje="Algunos campos no han sido ingresado correctamente.";
        if(this.TTmaquina==null)
            throw new TipoDatoException(mje);
        if(this.modelo==null)
            throw new TipoDatoException(mje);
        if(this.nombre==null)
            throw new TipoDatoException(mje);
        return true;
    }

    @Override
    public String toString() {
       String salida="";
        salida+=this.TTmaquina.getNombre();
        salida+="-"+this.getModelo();
        salida+="[cod. "+this.getCodigo()+"]";
        return salida;
    }

    public boolean isOperativa(Date tiempo) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}


