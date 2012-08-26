/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.GestionUsuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
 *
 * @author Administrador
 */
@Entity
@Table(name="T_ROLES_X_MENU"
    ,schema="dbo"
    ,catalog="Ramaty"
)

public class RolXMenu implements java.io.Serializable {
    
    private int idRolXMenu;
    private Menu TMenu;
    
    public RolXMenu(){
    }
    
    public RolXMenu(int idRolXMenu, Menu TMenu){
        this.idRolXMenu = idRolXMenu;
        this.TMenu = TMenu;
    }

    @Id    
    @Column(name="ID_ROL_X_MENU", unique=true, nullable=false, precision=5, scale=0)
    public int getIdRolXMenu() {
        return idRolXMenu;
    }

    public void setIdRolXMenu(int idRolXMenu) {
        this.idRolXMenu = idRolXMenu;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_MENU", nullable=false)
    public Menu getTMenu() {
        return TMenu;
    }

    public void setTMenu(Menu TMenu) {
        this.TMenu = TMenu;
    }
   
    
    
    
}
