/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Administracion;

/**
 *
 * @author Ivan
 */
public class Dia {

    private String dia;

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Dia(String dia) {
        this.dia = dia;
    }

    public Dia() {
    }

    @Override
    public String toString() {
        return this.getDia();
    }



    
}
