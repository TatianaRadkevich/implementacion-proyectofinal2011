package BaseDeDatos;
// Generated 09/08/2011 13:12:12 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * TTmaquinaHerramienta generated by hbm2java
 */
public class TTmaquinaHerramienta  implements java.io.Serializable {


     private byte idTmaquinaHerramienta;
     private TEtapasProduccionEspecifica TEtapasProduccionEspecifica;
     private String nombre;
     private boolean esHerramienta;
     private Set TMaquinasHerramientaParticulars = new HashSet(0);

    public TTmaquinaHerramienta() {
    }

	
    public TTmaquinaHerramienta(byte idTmaquinaHerramienta, TEtapasProduccionEspecifica TEtapasProduccionEspecifica, String nombre, boolean esHerramienta) {
        this.idTmaquinaHerramienta = idTmaquinaHerramienta;
        this.TEtapasProduccionEspecifica = TEtapasProduccionEspecifica;
        this.nombre = nombre;
        this.esHerramienta = esHerramienta;
    }
    public TTmaquinaHerramienta(byte idTmaquinaHerramienta, TEtapasProduccionEspecifica TEtapasProduccionEspecifica, String nombre, boolean esHerramienta, Set TMaquinasHerramientaParticulars) {
       this.idTmaquinaHerramienta = idTmaquinaHerramienta;
       this.TEtapasProduccionEspecifica = TEtapasProduccionEspecifica;
       this.nombre = nombre;
       this.esHerramienta = esHerramienta;
       this.TMaquinasHerramientaParticulars = TMaquinasHerramientaParticulars;
    }
   
    public byte getIdTmaquinaHerramienta() {
        return this.idTmaquinaHerramienta;
    }
    
    public void setIdTmaquinaHerramienta(byte idTmaquinaHerramienta) {
        this.idTmaquinaHerramienta = idTmaquinaHerramienta;
    }
    public TEtapasProduccionEspecifica getTEtapasProduccionEspecifica() {
        return this.TEtapasProduccionEspecifica;
    }
    
    public void setTEtapasProduccionEspecifica(TEtapasProduccionEspecifica TEtapasProduccionEspecifica) {
        this.TEtapasProduccionEspecifica = TEtapasProduccionEspecifica;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public boolean isEsHerramienta() {
        return this.esHerramienta;
    }
    
    public void setEsHerramienta(boolean esHerramienta) {
        this.esHerramienta = esHerramienta;
    }
    public Set getTMaquinasHerramientaParticulars() {
        return this.TMaquinasHerramientaParticulars;
    }
    
    public void setTMaquinasHerramientaParticulars(Set TMaquinasHerramientaParticulars) {
        this.TMaquinasHerramientaParticulars = TMaquinasHerramientaParticulars;
    }




}

