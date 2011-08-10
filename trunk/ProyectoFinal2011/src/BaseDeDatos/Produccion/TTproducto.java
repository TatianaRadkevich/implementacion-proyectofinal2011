package BaseDeDatos.Produccion;
// Generated 09/08/2011 13:12:12 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * TTproducto generated by hbm2java
 */
public class TTproducto  implements java.io.Serializable {


     private byte idTproducto;
     private String nombre;
     private String descripcion;
     private Set TProductoses = new HashSet(0);

    public TTproducto() {
    }

	
    public TTproducto(byte idTproducto, String nombre) {
        this.idTproducto = idTproducto;
        this.nombre = nombre;
    }
    public TTproducto(byte idTproducto, String nombre, String descripcion, Set TProductoses) {
       this.idTproducto = idTproducto;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.TProductoses = TProductoses;
    }
   
    public byte getIdTproducto() {
        return this.idTproducto;
    }
    
    public void setIdTproducto(byte idTproducto) {
        this.idTproducto = idTproducto;
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
    public Set getTProductoses() {
        return this.TProductoses;
    }
    
    public void setTProductoses(Set TProductoses) {
        this.TProductoses = TProductoses;
    }




}

