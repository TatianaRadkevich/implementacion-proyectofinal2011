/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.GestionUsuario;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name="T_ROLES"
    ,schema="dbo"
    ,catalog="Ramaty"
)

public class Rol implements java.io.Serializable {
    
    private int idRol;
    private String nombreRol;
    private Set<RolXMenu> TRolXMenu = new HashSet<RolXMenu>(0);
    
    public Rol(){}

    public Rol(int idRol, String nombreRol){
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }

    @Id    
    @Column(name="ID_ROL", unique=true, nullable=false, precision=5, scale=0)
    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    @Column(name="NOMBRE", nullable=false, length=50)
    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="ID_ROL", nullable=false)
    public Set<RolXMenu> getTRolXMenu() {
        return TRolXMenu;
    }

    public void setTRolXMenu(Set<RolXMenu> TRolXMenu) {
        this.TRolXMenu = TRolXMenu;
    }
    
}
