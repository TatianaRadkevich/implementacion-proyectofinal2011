package BaseDeDatos;
// Generated 09/08/2011 13:12:12 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * TDetallesOrdenCompra generated by hbm2java
 */
public class TDetallesOrdenCompra  implements java.io.Serializable {


     private int idDetalleOrdenCompra;
     private TOrdenesCompra TOrdenesCompra;
     private TMateriales TMateriales;
     private short cantidadPedida;
     private short cantidadRecibida;
     private Set TFaltanteses = new HashSet(0);

    public TDetallesOrdenCompra() {
    }

	
    public TDetallesOrdenCompra(int idDetalleOrdenCompra, TOrdenesCompra TOrdenesCompra, short cantidadPedida, short cantidadRecibida) {
        this.idDetalleOrdenCompra = idDetalleOrdenCompra;
        this.TOrdenesCompra = TOrdenesCompra;
        this.cantidadPedida = cantidadPedida;
        this.cantidadRecibida = cantidadRecibida;
    }
    public TDetallesOrdenCompra(int idDetalleOrdenCompra, TOrdenesCompra TOrdenesCompra, TMateriales TMateriales, short cantidadPedida, short cantidadRecibida, Set TFaltanteses) {
       this.idDetalleOrdenCompra = idDetalleOrdenCompra;
       this.TOrdenesCompra = TOrdenesCompra;
       this.TMateriales = TMateriales;
       this.cantidadPedida = cantidadPedida;
       this.cantidadRecibida = cantidadRecibida;
       this.TFaltanteses = TFaltanteses;
    }
   
    public int getIdDetalleOrdenCompra() {
        return this.idDetalleOrdenCompra;
    }
    
    public void setIdDetalleOrdenCompra(int idDetalleOrdenCompra) {
        this.idDetalleOrdenCompra = idDetalleOrdenCompra;
    }
    public TOrdenesCompra getTOrdenesCompra() {
        return this.TOrdenesCompra;
    }
    
    public void setTOrdenesCompra(TOrdenesCompra TOrdenesCompra) {
        this.TOrdenesCompra = TOrdenesCompra;
    }
    public TMateriales getTMateriales() {
        return this.TMateriales;
    }
    
    public void setTMateriales(TMateriales TMateriales) {
        this.TMateriales = TMateriales;
    }
    public short getCantidadPedida() {
        return this.cantidadPedida;
    }
    
    public void setCantidadPedida(short cantidadPedida) {
        this.cantidadPedida = cantidadPedida;
    }
    public short getCantidadRecibida() {
        return this.cantidadRecibida;
    }
    
    public void setCantidadRecibida(short cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }
    public Set getTFaltanteses() {
        return this.TFaltanteses;
    }
    
    public void setTFaltanteses(Set TFaltanteses) {
        this.TFaltanteses = TFaltanteses;
    }




}


