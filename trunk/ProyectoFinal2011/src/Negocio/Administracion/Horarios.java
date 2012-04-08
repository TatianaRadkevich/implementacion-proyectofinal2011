package Negocio.Administracion;
// Generated 21/10/2011 13:42:06 by Hibernate Tools 3.2.1.GA


import java.util.ArrayList;
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

/**
 * THorarios generated by hbm2java
 */
@Entity
@Table(name="T_HORARIOS"
    ,schema="dbo"
    ,catalog="Ramaty"
)
public class Horarios  implements java.io.Serializable {


     @Id
    @GeneratedValue
    @Column(name="ID_HORARIO", unique=true, nullable=false, precision=2, scale=0)
     private short idHorario;
     /*---------------------------------------------------------------------------------------------*/
      @Column(name="NOMBRE", nullable=false,length=50)
     private String nombre;
     /*---------------------------------------------------------------------------------------------*/
      @Column(name="DESCRIPCION", length=200)
     private String descripcion;
     /*---------------------------------------------------------------------------------------------*/
      @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="THorarios")
     private Set<AsignacionesDias> TAsignacionesDiases = new HashSet(0);
     /*---------------------------------------------------------------------------------------------*/
//     private Set TAsignacionesHorarios = new HashSet(0);
     /*---------------------------------------------------------------------------------------------*/

    public Horarios() {
    }

	
    public Horarios(short idHorario, String nombre) {
        this.idHorario = idHorario;
        this.nombre = nombre;
    }
    public Horarios(short idHorario, String nombre, String descripcion, Set TAsignacionesDiases, Set<AsignacionesDias> TAsignacionesHorarios) {
       this.idHorario = idHorario;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.TAsignacionesDiases = TAsignacionesDiases;
//       this.TAsignacionesHorarios = TAsignacionesHorarios;
    }
   
    
    public short getIdHorario() {
        return this.idHorario;
    }
    
    public void setIdHorario(short idHorario) {
        this.idHorario = idHorario;
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

    public List<AsignacionesDias> getTAsignacionesDiases() {
        return new ArrayList<AsignacionesDias>(this.TAsignacionesDiases);
    }

    public void setTAsignacionesDiases(List<AsignacionesDias> datos) {
        this.TAsignacionesDiases.clear();

        for(AsignacionesDias item:datos)
        {
            item.setTHorarios(this);
            this.TAsignacionesDiases.add(item);
        }       
    }


    public String toStringAsignacionDias() {
        String salida="";
        for(AsignacionesDias ad:TAsignacionesDiases)
            salida+="  "+ad.toString();
        return salida;
    }


    public String toString() {
                return this.getNombre();
    }




//@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="THorarios")
//    public Set getTAsignacionesHorarios() {
//        return this.TAsignacionesHorarios;
//    }
//
//    public void setTAsignacionesHorarios(Set TAsignacionesHorarios) {
//        this.TAsignacionesHorarios = TAsignacionesHorarios;
//    }


}


