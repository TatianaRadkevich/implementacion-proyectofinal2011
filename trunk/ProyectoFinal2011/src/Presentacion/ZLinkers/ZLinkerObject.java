/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.ZLinkers;


import Negocio.Exceptiones.NegocioException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigo
 */
public class ZLinkerObject<C> {

    private C objeto;
    private ArrayList<ZLinkerItem> detalle;
    private Class<C> clase;

    public ZLinkerObject(Class<C> clase) {
        this.detalle = new ArrayList<ZLinkerItem>();
        this.clase=clase;
    }

    public ZLinkerObject(Class<C> clase, C obj) {
        this(clase);
        this.objeto = obj;
    }

    public void load() {
        for (ZLinkerItem l : detalle) {
            try {
                l.load();
            } catch (Exception ex) {
                Logger.getLogger(ZLinkerObject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void save() throws NegocioException {
        boolean err=false;
        for (ZLinkerItem l : detalle) {
            try {
                l.save();

            }catch(NegocioException ne){
                err=true;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                Logger.getLogger(ZLinkerObject.class.getName()).log(Level.INFO, "message", e);               
            }
        }

        if(err)
            throw new NegocioException("Hay campos cargado incorrectamente, por favor verifiquelos.");
    }

    public void add(String propRegex, ZLinkerItem<C,Object> item) {

        item.setProp(new Propiedad(clase, propRegex));
        item.setZLinkerObject(this);
        detalle.add(item);
    }
    public void add(String propRegex,boolean  soloLectura ,ZLinkerItem<C,Object> item) {

        item.setProp(new Propiedad(clase, propRegex));
        item.setZLinkerObject(this);
        detalle.add(item);
    }

    public C getObjeto() {
        return objeto;
    }

    public void setObjeto(C objeto) {
        this.objeto = objeto;
    }
}
