using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Entity.Persistente
{
    public class Pedido
    {
        #region atributos
        private long id;        
        private DateTime? fechaHoraRealEntrega;        
        private DateTime? fechaHoraGeneracion;        
        private DateTime? fechaSolicitada;        
        private int prioridad;        
        private string motivoBaja;        
        private DateTime? fechaClienteRecibio;      
        private DateTime? fechaEstimadaEntrega;
        private DateTime? fechaBaja;
        private Entity.ReadOnly.Cliente cliente;
        private Entity.ReadOnly.EstadoPedido estado;
        private IList<Entity.Persistente.DetallePedido> detallesPedido;
        private Int16 tipoPedido = 1;
        #endregion

        #region propiedades
        public virtual long Id
        {
            get { return id; }
            set { id = value; }
        }

        /// <summary>
        /// Fecha en la que se estima que se puede llegar a entregar.
        /// ¿Es la fecha propuesta por el vendedor?                 >> SI, CORREGIDO
        /// </summary>
        public virtual DateTime? FechaEstimadaEntrega
        {
            get { return fechaEstimadaEntrega; }
            set { fechaEstimadaEntrega = value; }
        }

        /// <summary>
        /// Fecha en la que se da de baja el pedido.
        /// Se guarda la fecha en la que el cliente cancela el pedido por algún motivo.
        /// </summary>
        public virtual DateTime? FechaBaja
        {
            get { return fechaBaja; }
            set { fechaBaja = value; }
        }

        /// <summary>
        /// Cliente que hace el pedido, se obtiene del Usuario.
        /// </summary>
        public virtual Entity.ReadOnly.Cliente Cliente
        {
            get { return cliente; }
            set { cliente = value; }
        }

        /// <summary>
        /// Estado del pedido.
        /// Patrón estado.
        /// </summary>
        public virtual Entity.ReadOnly.EstadoPedido Estado
        {
            get { return estado; }
            set { estado = value; }
        }

        /// <summary>
        /// Coleccion de detalles del pedido.
        /// </summary>
        public virtual IList<Entity.Persistente.DetallePedido> DetallesPedido
        {
            get { return detallesPedido; }
            set { detallesPedido = value; }
        }

        /// <summary>
        /// Fecha real en la que se entregó el pedido.
        /// </summary>
        public virtual DateTime? FechaHoraRealEntrega
        {
            get { return fechaHoraRealEntrega; }
            set { fechaHoraRealEntrega = value; }
        }

        /// <summary>
        /// Fecha en la que se generó el pedido.
        /// ¿No es lo mismo que la fecha del pedido?
        /// </summary>
        public virtual DateTime? FechaHoraGeneracion
        {
            get { return fechaHoraGeneracion; }
            set { fechaHoraGeneracion = value; }
        }

        /// <summary>
        /// Fecha solicitada por el cliente.
        /// </summary>
        public virtual DateTime? FechaSolicitada
        {
            get { return fechaSolicitada; }
            set { fechaSolicitada = value; }
        }

        /// <summary>
        /// Prioridad del pedido.
        /// </summary>
        public virtual int Prioridad
        {
            get { return prioridad; }
            set { prioridad = value; }
        }

        /// <summary>
        /// Motivo por el cual canceló el pedido.
        /// </summary>
        public virtual string MotivoBaja
        {
            get { return motivoBaja; }
            set { motivoBaja = value; }
        }

        /// <summary>
        /// WTF???                                          >> CORREGIDO
        /// </summary>
        public virtual DateTime? FechaClienteRecibio
        {
            get { return fechaClienteRecibio; }
            set { fechaClienteRecibio = value; }
        }

        /// <summary>
        /// Tipo de pedido.
        /// En nuestro caso es SIEMPRE = 1.
        /// MP del cliente
        /// </summary>
        public virtual Int16 TipoPedido
        {
            get { return tipoPedido; }
            set { tipoPedido = value; }
        }
        #endregion

        #region métodos
        public virtual double ObtenerTotal()
        {
            double total = 0;
            foreach (var item in detallesPedido)
            {
                total+=item.Cantidad*item.Precio;
            }
            return total;
        }

        public virtual string ObtenerTotalConFormato(string formato)
        {
            return this.ObtenerTotal().ToString(formato);
        }
        #endregion
    }
}
