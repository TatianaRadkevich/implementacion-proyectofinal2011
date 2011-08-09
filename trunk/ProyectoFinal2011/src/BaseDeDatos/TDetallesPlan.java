package BaseDeDatos;
// Generated 09/08/2011 13:12:12 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TDetallesPlan generated by hbm2java
 */
public class TDetallesPlan  implements java.io.Serializable {


     private int idDetallePlan;
     private TEtapasProduccionEspecifica TEtapasProduccionEspecifica;
     private TEmpleados TEmpleados;
     private TPlanesProduccion TPlanesProduccion;
     private TMaquinasHerramientaParticular TMaquinasHerramientaParticular;
     private int cantidad;
     private Date fecHoraPrevistaFin;
     private Date fecHoraPrevistaInicio;
     private Date fecHoraRealInicio;
     private Date fecHoraRealFin;
     private Set TFaltanteses = new HashSet(0);

    public TDetallesPlan() {
    }

	
    public TDetallesPlan(int idDetallePlan, TEtapasProduccionEspecifica TEtapasProduccionEspecifica, TEmpleados TEmpleados, TPlanesProduccion TPlanesProduccion, TMaquinasHerramientaParticular TMaquinasHerramientaParticular, int cantidad, Date fecHoraPrevistaFin, Date fecHoraPrevistaInicio, Date fecHoraRealInicio, Date fecHoraRealFin) {
        this.idDetallePlan = idDetallePlan;
        this.TEtapasProduccionEspecifica = TEtapasProduccionEspecifica;
        this.TEmpleados = TEmpleados;
        this.TPlanesProduccion = TPlanesProduccion;
        this.TMaquinasHerramientaParticular = TMaquinasHerramientaParticular;
        this.cantidad = cantidad;
        this.fecHoraPrevistaFin = fecHoraPrevistaFin;
        this.fecHoraPrevistaInicio = fecHoraPrevistaInicio;
        this.fecHoraRealInicio = fecHoraRealInicio;
        this.fecHoraRealFin = fecHoraRealFin;
    }
    public TDetallesPlan(int idDetallePlan, TEtapasProduccionEspecifica TEtapasProduccionEspecifica, TEmpleados TEmpleados, TPlanesProduccion TPlanesProduccion, TMaquinasHerramientaParticular TMaquinasHerramientaParticular, int cantidad, Date fecHoraPrevistaFin, Date fecHoraPrevistaInicio, Date fecHoraRealInicio, Date fecHoraRealFin, Set TFaltanteses) {
       this.idDetallePlan = idDetallePlan;
       this.TEtapasProduccionEspecifica = TEtapasProduccionEspecifica;
       this.TEmpleados = TEmpleados;
       this.TPlanesProduccion = TPlanesProduccion;
       this.TMaquinasHerramientaParticular = TMaquinasHerramientaParticular;
       this.cantidad = cantidad;
       this.fecHoraPrevistaFin = fecHoraPrevistaFin;
       this.fecHoraPrevistaInicio = fecHoraPrevistaInicio;
       this.fecHoraRealInicio = fecHoraRealInicio;
       this.fecHoraRealFin = fecHoraRealFin;
       this.TFaltanteses = TFaltanteses;
    }
   
    public int getIdDetallePlan() {
        return this.idDetallePlan;
    }
    
    public void setIdDetallePlan(int idDetallePlan) {
        this.idDetallePlan = idDetallePlan;
    }
    public TEtapasProduccionEspecifica getTEtapasProduccionEspecifica() {
        return this.TEtapasProduccionEspecifica;
    }
    
    public void setTEtapasProduccionEspecifica(TEtapasProduccionEspecifica TEtapasProduccionEspecifica) {
        this.TEtapasProduccionEspecifica = TEtapasProduccionEspecifica;
    }
    public TEmpleados getTEmpleados() {
        return this.TEmpleados;
    }
    
    public void setTEmpleados(TEmpleados TEmpleados) {
        this.TEmpleados = TEmpleados;
    }
    public TPlanesProduccion getTPlanesProduccion() {
        return this.TPlanesProduccion;
    }
    
    public void setTPlanesProduccion(TPlanesProduccion TPlanesProduccion) {
        this.TPlanesProduccion = TPlanesProduccion;
    }
    public TMaquinasHerramientaParticular getTMaquinasHerramientaParticular() {
        return this.TMaquinasHerramientaParticular;
    }
    
    public void setTMaquinasHerramientaParticular(TMaquinasHerramientaParticular TMaquinasHerramientaParticular) {
        this.TMaquinasHerramientaParticular = TMaquinasHerramientaParticular;
    }
    public int getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public Date getFecHoraPrevistaFin() {
        return this.fecHoraPrevistaFin;
    }
    
    public void setFecHoraPrevistaFin(Date fecHoraPrevistaFin) {
        this.fecHoraPrevistaFin = fecHoraPrevistaFin;
    }
    public Date getFecHoraPrevistaInicio() {
        return this.fecHoraPrevistaInicio;
    }
    
    public void setFecHoraPrevistaInicio(Date fecHoraPrevistaInicio) {
        this.fecHoraPrevistaInicio = fecHoraPrevistaInicio;
    }
    public Date getFecHoraRealInicio() {
        return this.fecHoraRealInicio;
    }
    
    public void setFecHoraRealInicio(Date fecHoraRealInicio) {
        this.fecHoraRealInicio = fecHoraRealInicio;
    }
    public Date getFecHoraRealFin() {
        return this.fecHoraRealFin;
    }
    
    public void setFecHoraRealFin(Date fecHoraRealFin) {
        this.fecHoraRealFin = fecHoraRealFin;
    }
    public Set getTFaltanteses() {
        return this.TFaltanteses;
    }
    
    public void setTFaltanteses(Set TFaltanteses) {
        this.TFaltanteses = TFaltanteses;
    }




}


