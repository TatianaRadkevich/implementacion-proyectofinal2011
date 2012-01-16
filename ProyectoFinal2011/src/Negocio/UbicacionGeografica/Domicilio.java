package Negocio.UbicacionGeografica;
// Generated 12/08/2011 13:27:23 by Hibernate Tools 3.2.1.GA


import Negocio.Administracion.Empleado;
import Negocio.Compras.Proveedor;
import Negocio.TipoDatoException;
import Negocio.Ventas.Cliente;
import java.util.HashSet;
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

/**
 * TDomicilios generated by hbm2java
 */
@Entity
@Table(name="T_DOMICILIOS"
    ,schema="dbo"
    ,catalog="Ramaty"
)
public class Domicilio  implements java.io.Serializable {

@Id
    @GeneratedValue
    @Column(name="ID_DOMICILIO", unique=true, nullable=false, precision=3, scale=0)
     private short idDomicilio;
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_LOCALIDAD")
     private Localidad TLocalidades;
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_BARRIO")
     private Barrio TBarrios;
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_PROVINCIA")
     private Provincia TProvincias;
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_PAIS"/*, nullable=false*/)
     private Pais TPaises;
    @Column(name = "CALLE", length = 20)
     private String calle;
    @Column(name = "DEPTO", precision = 3, scale = 0)
     private String depto;
    @Column(name = "NUMERO", precision = 5, scale = 0)
     private Integer numero;
    @Column(name = "PISO", precision = 2, scale = 0)
     private Short piso;
    

    public Domicilio() {
    }

	
    public Domicilio(short idDomicilio, Pais TPaises, String calle, Integer numero) {
        this.idDomicilio = idDomicilio;
        this.TPaises = TPaises;
        this.calle = calle;
        this.numero = numero;
    }
    public Domicilio(short idDomicilio, Localidad TLocalidades, Barrio TBarrios, Provincia TProvincias, Pais TPaises, String calle, String depto, Integer numero, Short piso) {
       this.idDomicilio = idDomicilio;
       this.TLocalidades = TLocalidades;
       this.TBarrios = TBarrios;
       this.TProvincias = TProvincias;
       this.TPaises = TPaises;
       this.calle = calle;
       this.depto = depto;
       this.numero = numero;
       this.piso = piso;
      
    }
   
     
    public short getIdDomicilio() {
        return this.idDomicilio;
    }
    
    public void setIdDomicilio(short idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public Localidad getTLocalidades() {
        return this.TLocalidades;
    }
    
    public void setTLocalidades(Localidad TLocalidades)  throws TipoDatoException{
        if(TLocalidades!=null)
           this.TLocalidades = TLocalidades;
        else
            throw new TipoDatoException("Debe seleccionar un tipo documento");


    }

    public Barrio getTBarrios() {
        return this.TBarrios;
    }
    
    public void setTBarrios(Barrio TBarrios) throws TipoDatoException{
         if(TBarrios!=null)
            this.TBarrios = TBarrios;
        else
            throw new TipoDatoException("Debe seleccionar un tipo documento");

    }

    public Provincia getTProvincias() {
        return this.TProvincias;
    }
    
    public void setTProvincias(Provincia TProvincias) throws TipoDatoException{
        if(TProvincias!=null)
            this.TProvincias = TProvincias;
        else
            throw new TipoDatoException("Debe seleccionar un tipo documento");

    }

    public Pais getTPaises() {
        return this.TPaises;
    }
    
    public void setTPaises(Pais TPaises) throws TipoDatoException{
         if(TPaises!=null)
            this.TPaises = TPaises;
        else
            throw new TipoDatoException("Debe seleccionar un tipo documento");
        
    }
    
    public String getCalle() {
        return this.calle;
    }
    
    public void setCalle(String calle) throws TipoDatoException{
        if(!calle.trim().isEmpty())
            this.calle = calle;
        else
            throw new TipoDatoException("Debe ingresar una calle.");
    }
    
    public String getDepto() {
        return this.depto;
    }
    
    public void setDepto(String depto) throws TipoDatoException{
        if(depto.length()<=3)
            this.depto = depto;
        else
            throw new TipoDatoException("Formato incorrecto. Como maximo deber tener 3 caracteres");
    }
    
    public Integer getNumero() {
        return this.numero;
    }
    
    public void setNumero(Integer numero) throws TipoDatoException {
             this.numero = numero;
    }
    
    public Short getPiso() {
        return this.piso;
    }
    
    public void setPiso(Short piso) throws TipoDatoException{
        if(piso<=200)
             this.piso = piso;
        else
            throw new TipoDatoException("Formato incorrecto. Numero de piso no valido");
    }
}


