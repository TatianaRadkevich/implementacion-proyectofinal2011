/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.ZLinkers;

import Negocio.Exceptiones.NegocioException;
import Negocio.Exceptiones.TipoDatoException;
import Presentacion.Utilidades;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigo
 */
public class ZLObject<C> {

    private C objeto;
    private ArrayList<ZLItem> detalle;
    private Class<C> clase;

    public ZLObject(Class<C> clase) {
        this.detalle = new ArrayList<ZLItem>();
        this.clase = clase;
    }

    public ZLObject(Class<C> clase, C obj) {
        this(clase);

        if (obj == null) {
            throw new TipoDatoException("ZLObject: No se acepta objetos nulos");
        }

        this.objeto = obj;
    }

    public void load() {
        for (ZLItem l : detalle) {
            try {
                l.load();
            } catch (Exception ex) {
                Logger.getLogger(ZLObject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void save() throws NegocioException {
        boolean err = false;
        for (ZLItem l : detalle) {
            try {
                l.save();

            } catch (NegocioException ne) {
                err = true;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                Logger.getLogger(ZLObject.class.getName()).log(Level.INFO, "message", e);
            }
        }

        if (err) {
            throw new NegocioException("Hay campos cargado incorrectamente, por favor verifiquelos.");
        }
    }

    public void add(String propRegex, ZLItem<C, Object> item) {

        item.setProp(new Propiedad(clase, propRegex));
        item.setZLinkerObject(this);
        detalle.add(item);
    }

    public void add(String propRegex, boolean soloLectura, ZLItem<C, Object> item) {

        item.setProp(new Propiedad(clase, propRegex));
        item.setZLinkerObject(this);
        detalle.add(item);
    }

    public C getObjeto() {
        return objeto;
    }

    public void setObjeto(C objeto) {
        if (objeto == null) {
            throw new TipoDatoException("ZLObject: No se acepta objetos nulos");
        }

        this.objeto = objeto;
    }
}
