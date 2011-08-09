package BaseDeDatos;
// Generated 09/08/2011 13:12:12 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * TMaquinasHerramientaParticular generated by hbm2java
 */
public class TMaquinasHerramientaParticular  implements java.io.Serializable {


     private short idMaquinaHerramientaParticular;
     private TTmaquinaHerramienta TTmaquinaHerramienta;
     private TEmaquina TEmaquina;
     private int capacidadProductiva;
     private String caracteristicas;
     private String modelo;
     private String nombre;
     private String observaciones;
     private String codigo;
     private Set TDetallesPlans = new HashSet(0);

    public TMaquinasHerramientaParticular() {
    }

	
    public TMaquinasHerramientaParticular(short idMaquinaHerramientaParticular, TTmaquinaHerramienta TTmaquinaHerramienta, TEmaquina TEmaquina, int capacidadProductiva, String caracteristicas, String modelo, String nombre, String codigo) {
        this.idMaquinaHerramientaParticular = idMaquinaHerramientaParticular;
        this.TTmaquinaHerramienta = TTmaquinaHerramienta;
        this.TEmaquina = TEmaquina;
        this.capacidadProductiva = capacidadProductiva;
        this.caracteristicas = caracteristicas;
        this.modelo = modelo;
        this.nombre = nombre;
        this.codigo = codigo;
    }
    public TMaquinasHerramientaParticular(short idMaquinaHerramientaParticular, TTmaquinaHerramienta TTmaquinaHerramienta, TEmaquina TEmaquina, int capacidadProductiva, String caracteristicas, String modelo, String nombre, String observaciones, String codigo, Set TDetallesPlans) {
       this.idMaquinaHerramientaParticular = idMaquinaHerramientaParticular;
       this.TTmaquinaHerramienta = TTmaquinaHerramienta;
       this.TEmaquina = TEmaquina;
       this.capacidadProductiva = capacidadProductiva;
       this.caracteristicas = caracteristicas;
       this.modelo = modelo;
       this.nombre = nombre;
       this.observaciones = observaciones;
       this.codigo = codigo;
       this.TDetallesPlans = TDetallesPlans;
    }
   
    public short getIdMaquinaHerramientaParticular() {
        return this.idMaquinaHerramientaParticular;
    }
    
    public void setIdMaquinaHerramientaParticular(short idMaquinaHerramientaParticular) {
        this.idMaquinaHerramientaParticular = idMaquinaHerramientaParticular;
    }
    public TTmaquinaHerramienta getTTmaquinaHerramienta() {
        return this.TTmaquinaHerramienta;
    }
    
    public void setTTmaquinaHerramienta(TTmaquinaHerramienta TTmaquinaHerramienta) {
        this.TTmaquinaHerramienta = TTmaquinaHerramienta;
    }
    public TEmaquina getTEmaquina() {
        return this.TEmaquina;
    }
    
    public void setTEmaquina(TEmaquina TEmaquina) {
        this.TEmaquina = TEmaquina;
    }
    public int getCapacidadProductiva() {
        return this.capacidadProductiva;
    }
    
    public void setCapacidadProductiva(int capacidadProductiva) {
        this.capacidadProductiva = capacidadProductiva;
    }
    public String getCaracteristicas() {
        return this.caracteristicas;
    }
    
    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    public String getModelo() {
        return this.modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public String getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public Set getTDetallesPlans() {
        return this.TDetallesPlans;
    }
    
    public void setTDetallesPlans(Set TDetallesPlans) {
        this.TDetallesPlans = TDetallesPlans;
    }




}


