/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.GestionUsuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

/**
 *
 * @author Administrador
 */

@Entity
@Table(name="T_ROLES_X_USUARIO"
    ,schema="dbo"
    ,catalog="Ramaty"
)

public class RolXUsuario implements java.io.Serializable {
    
    private int idRolXUsuario;
    private Rol TRol;
    
    public RolXUsuario(){
    }
    
    public RolXUsuario(int idRolXUsuario, Rol TRol){
        this.idRolXUsuario = idRolXUsuario;
        this.TRol = TRol;
    }

    @Id    
    @Column(name="ID_ROL_X_USUARIO", unique=true, nullable=false, precision=5, scale=0)
    public int getIdRolXUsuario() {
        return idRolXUsuario;
    }

    
    public void setIdRolXUsuario(int idRolXUsuario) {
        this.idRolXUsuario = idRolXUsuario;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_ROL", nullable=false)
    public Rol getTRol() {
        return TRol;
    }

    public void setTRol(Rol TRol) {
        this.TRol = TRol;
    }
       
    
}
