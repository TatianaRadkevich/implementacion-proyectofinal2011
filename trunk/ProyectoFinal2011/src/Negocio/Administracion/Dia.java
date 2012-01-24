package Negocio.Administracion;
// Generated 21/10/2011 13:42:06 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TDias generated by hbm2java
 */
@Entity
@Table(name="T_DIAS"
    ,schema="dbo"
    ,catalog="Ramaty"
)
public class Dia  implements java.io.Serializable {


     @Id
    @GeneratedValue
    @Column(name="ID_DIA", unique=true, nullable=false, precision=2, scale=0)
     private short idDia;
     /*---------------------------------------------------------------------------------------------*/
     @Column(name="NOMBRE", nullable=false, length=20)
     private String nombre;
     /*---------------------------------------------------------------------------------------------*/
     

    public Dia() {
    }

    public Dia(String nombre) {
        this.nombre = nombre;
    }
	
    public Dia(short idDia, String nombre) {
        this.idDia = idDia;
        this.nombre = nombre;
    }
    public Dia(short idDia, String nombre, Set TAsignacionesDiases) {
       this.idDia = idDia;
       this.nombre = nombre;
     
    }
   
    
    public short getIdDia() {
        return this.idDia;
    }
    
    public void setIdDia(short idDia) {
        this.idDia = idDia;
    }
    
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }




}

