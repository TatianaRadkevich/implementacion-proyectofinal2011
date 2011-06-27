/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Ventas;

/**
 *
 * @author Rodrigo
 */
class ClienteWeb {
    private String usuario;
    private String contraseñaa;

    public ClienteWeb(String usuario, String contraseñaa) {
        this.usuario = usuario;
        this.contraseñaa = contraseñaa;
    }

    public String getContraseñaa() {
        return contraseñaa;
    }

    public void setContraseñaa(String contraseñaa) {
        this.contraseñaa = contraseñaa;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
