/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.ZLinkers;

import Negocio.NegocioException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigo
 */
public class ZLinkerObject<T> {

    private T objeto;
    private ArrayList<ZLinkerItem<T>> detalle;
    private Class<T> clase;

    public ZLinkerObject(Class<T> clase) {
        this.detalle = new ArrayList<ZLinkerItem<T>>();
        this.clase=clase;
    }

    public ZLinkerObject(Class<T> clase, T obj) {
        this(clase);
        this.objeto = obj;
    }

    public void load() {
        for (ZLinkerItem<T> l : detalle) {
            try {
                l.load();
            } catch (Exception ex) {
                Logger.getLogger(ZLinkerObject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void save() throws NegocioException {
        boolean err=false;
        for (ZLinkerItem<T> l : detalle) {
            try {
                l.save();

            }catch(NegocioException ne){
                err=true;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                Logger.getLogger(ZLinkerObject.class.getName()).log(Level.INFO, "message", e);
                //Logger.getLogger(ZLinkerObject.class.getName()).log(Level.INFO, "message", e);
            }
        }

        if(err)
            throw new NegocioException("Hay campos cargado incorrectamente, por favor verifiquelos.");
    }

    public void add(ZLinkerItem<T> item) {
        //item.setClass(clase);
        item.setLinkObj(this);
        detalle.add(item);
    }

    public T getObjeto() {
        return objeto;
    }

    public void setObjeto(T objeto) {
        this.objeto = objeto;
    }
}
