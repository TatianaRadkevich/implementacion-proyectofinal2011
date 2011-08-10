package BaseDeDatos.Administracion;
// Generated 09/08/2011 13:12:12 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * TSexos generated by hbm2java
 */
public class TSexos  implements java.io.Serializable {


     private byte idSexo;
     private String nombre;
     private String descripcion;
     private Set TEmpleadoses = new HashSet(0);

    public TSexos() {
    }

	
    public TSexos(byte idSexo, String nombre) {
        this.idSexo = idSexo;
        this.nombre = nombre;
    }
    public TSexos(byte idSexo, String nombre, String descripcion, Set TEmpleadoses) {
       this.idSexo = idSexo;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.TEmpleadoses = TEmpleadoses;
    }
   
    public byte getIdSexo() {
        return this.idSexo;
    }
    
    public void setIdSexo(byte idSexo) {
        this.idSexo = idSexo;
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
    public Set getTEmpleadoses() {
        return this.TEmpleadoses;
    }
    
    public void setTEmpleadoses(Set TEmpleadoses) {
        this.TEmpleadoses = TEmpleadoses;
    }




}

