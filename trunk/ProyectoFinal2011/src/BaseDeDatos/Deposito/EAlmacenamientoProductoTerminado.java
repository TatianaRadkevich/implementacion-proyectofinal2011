/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos.Deposito;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author fbobbio
 */
@Entity
@Table(name = "T_EALMACENAMIENTO_PRODUCTO_TERMINADO", schema = "dbo", catalog = "Ramaty")
public class EAlmacenamientoProductoTerminado implements Serializable {
        
    @Id
    @GeneratedValue
    @Column(name = "ID_EALMACENAMIENTO_PRODUCTO_TERMINADO", unique = true, nullable = false, precision = 8, scale = 0)
    private int idEstado;
    
    @Column(name = "NOMBRE", length = 50, nullable=false)
    private String nombre;
    
    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
