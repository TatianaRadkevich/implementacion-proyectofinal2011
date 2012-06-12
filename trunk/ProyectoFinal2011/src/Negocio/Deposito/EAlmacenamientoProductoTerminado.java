/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Deposito;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author fbobbio
 */
@Entity
public class EAlmacenamientoProductoTerminado implements Serializable {
        
    @Id
    @GeneratedValue
    @Column(name = "ID_EALMACENAMIENTO_PRODUCTO_TERMINADO", unique = true, nullable = false, precision = 8, scale = 0)
    private int idEstado;
    
    @Column(name = "NOMBRE", length = 50, nullable=false)
    private String nombre;
    
    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;    
}
