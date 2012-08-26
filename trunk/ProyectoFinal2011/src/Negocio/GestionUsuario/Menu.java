/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.GestionUsuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name="T_MENUS"
    ,schema="dbo"
    ,catalog="Ramaty"
)

public class Menu implements java.io.Serializable {
    
    private int idMenu;
    private String nombreMenu;
    
    public Menu(){
    }
    
    public Menu(int idMenu, String nombreMenu){
        this.idMenu = idMenu;
        this.nombreMenu = nombreMenu;
    }

    @Id    
    @Column(name="ID_MENU", unique=true, nullable=false, precision=5, scale=0)
    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    @Column(name="NOMBRE", nullable=false, length=50)
    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

}
