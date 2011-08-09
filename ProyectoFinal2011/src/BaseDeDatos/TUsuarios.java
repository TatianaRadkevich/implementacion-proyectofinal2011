package BaseDeDatos;
// Generated 09/08/2011 13:12:12 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * TUsuarios generated by hbm2java
 */
public class TUsuarios  implements java.io.Serializable {


     private int idUsuario;
     private String nombreUsuario;
     private String contrasenia;
     private Set TEmpleadoses = new HashSet(0);
     private Set TClienteses = new HashSet(0);

    public TUsuarios() {
    }

	
    public TUsuarios(int idUsuario, String nombreUsuario, String contrasenia) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }
    public TUsuarios(int idUsuario, String nombreUsuario, String contrasenia, Set TEmpleadoses, Set TClienteses) {
       this.idUsuario = idUsuario;
       this.nombreUsuario = nombreUsuario;
       this.contrasenia = contrasenia;
       this.TEmpleadoses = TEmpleadoses;
       this.TClienteses = TClienteses;
    }
   
    public int getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getContrasenia() {
        return this.contrasenia;
    }
    
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public Set getTEmpleadoses() {
        return this.TEmpleadoses;
    }
    
    public void setTEmpleadoses(Set TEmpleadoses) {
        this.TEmpleadoses = TEmpleadoses;
    }
    public Set getTClienteses() {
        return this.TClienteses;
    }
    
    public void setTClienteses(Set TClienteses) {
        this.TClienteses = TClienteses;
    }




}

