package BaseDeDatos.Ventas;
// Generated 09/08/2011 13:12:12 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * TTcliente generated by hbm2java
 */
public class TTcliente  implements java.io.Serializable {


     private byte idTcliente;
     private String nombre;
     private String descripcion;
     private Set TClienteses = new HashSet(0);

    public TTcliente() {
    }

	
    public TTcliente(byte idTcliente, String nombre) {
        this.idTcliente = idTcliente;
        this.nombre = nombre;
    }
    public TTcliente(byte idTcliente, String nombre, String descripcion, Set TClienteses) {
       this.idTcliente = idTcliente;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.TClienteses = TClienteses;
    }
   
    public byte getIdTcliente() {
        return this.idTcliente;
    }
    
    public void setIdTcliente(byte idTcliente) {
        this.idTcliente = idTcliente;
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
    public Set getTClienteses() {
        return this.TClienteses;
    }
    
    public void setTClienteses(Set TClienteses) {
        this.TClienteses = TClienteses;
    }




}

