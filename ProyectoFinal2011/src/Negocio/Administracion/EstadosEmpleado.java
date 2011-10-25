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
 * TEstadosEmpleado generated by hbm2java
 */
@Entity
@Table(name="T_ESTADOS_EMPLEADO"
    ,schema="dbo"
    ,catalog="Ramaty"
)
public class EstadosEmpleado  implements java.io.Serializable {


    @Id
    @GeneratedValue
    @Column(name="ID_ESTADO_EMPLEADO", unique=true, nullable=false, precision=2, scale=0)
     private int idEstadoEmpleado;
     /*---------------------------------------------------------------------------------------------*/
     @Column(name="NOMBRE", nullable=false, length=50)
     private String nombre;
     /*---------------------------------------------------------------------------------------------*/
     @Column(name="DESCRIPCION", length=200)
     private String descripcion;
     /*---------------------------------------------------------------------------------------------*/
   
     
    public EstadosEmpleado() {
    }

	
    public EstadosEmpleado(int idEstadoEmpleado, String nombre) {
        this.idEstadoEmpleado = idEstadoEmpleado;
        this.nombre = nombre;
    }
    public EstadosEmpleado(int idEstadoEmpleado, String nombre, String descripcion) {
       this.idEstadoEmpleado = idEstadoEmpleado;
       this.nombre = nombre;
       this.descripcion = descripcion;
      
    }
   
     
    public int getIdEstadoEmpleado() {
        return this.idEstadoEmpleado;
    }
    
    public void setIdEstadoEmpleado(int idEstadoEmpleado) {
        this.idEstadoEmpleado = idEstadoEmpleado;
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
   



}


