package BaseDeDatos;
// Generated 09/08/2011 13:12:12 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * TProvincias generated by hbm2java
 */
public class TProvincias  implements java.io.Serializable {


     private int idProvincia;
     private TPaises TPaises;
     private String nombre;
     private String descripcion;
     private Set TLocalidadeses = new HashSet(0);
     private Set TDomicilioses = new HashSet(0);

    public TProvincias() {
    }

	
    public TProvincias(int idProvincia, TPaises TPaises, String nombre) {
        this.idProvincia = idProvincia;
        this.TPaises = TPaises;
        this.nombre = nombre;
    }
    public TProvincias(int idProvincia, TPaises TPaises, String nombre, String descripcion, Set TLocalidadeses, Set TDomicilioses) {
       this.idProvincia = idProvincia;
       this.TPaises = TPaises;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.TLocalidadeses = TLocalidadeses;
       this.TDomicilioses = TDomicilioses;
    }
   
    public int getIdProvincia() {
        return this.idProvincia;
    }
    
    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }
    public TPaises getTPaises() {
        return this.TPaises;
    }
    
    public void setTPaises(TPaises TPaises) {
        this.TPaises = TPaises;
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
    public Set getTLocalidadeses() {
        return this.TLocalidadeses;
    }
    
    public void setTLocalidadeses(Set TLocalidadeses) {
        this.TLocalidadeses = TLocalidadeses;
    }
    public Set getTDomicilioses() {
        return this.TDomicilioses;
    }
    
    public void setTDomicilioses(Set TDomicilioses) {
        this.TDomicilioses = TDomicilioses;
    }




}


