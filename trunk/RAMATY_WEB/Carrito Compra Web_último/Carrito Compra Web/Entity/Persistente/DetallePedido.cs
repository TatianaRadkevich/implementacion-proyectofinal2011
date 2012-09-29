using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Entity.Persistente
{
    public class DetallePedido
    {
        #region atributos
        private long id;
        private Entity.ReadOnly.Producto producto;
        private int cantidad;
        private double precio;
        private Int16 estadoDetallePedido = 1;
        #endregion

        #region propiedades
        /// <summary>
        /// Id detalle de pedido.
        /// </summary>
        public virtual long Id
        {
            get { return id; }
            set { id = value; }
        }

        /// <summary>
        /// Producto.
        /// </summary>
        public virtual Entity.ReadOnly.Producto Producto
        {
            get { return producto; }
            set { producto = value; }
        }

        /// <summary>
        /// Cantidad.
        /// ¿La cantidad es un entero?      >> SI,   CORREGIDO
        /// </summary>
        public virtual int Cantidad
        {
            get { return cantidad; }
            set { cantidad = value; }
        }

        /// <summary>
        /// Precio.
        /// </summary>
        public virtual double Precio
        {
            get { return precio; }
            set { precio = value; }
        }

        /// <summary>
        /// Estado del detalle del pedido.
        /// Esta propiedad p/ nosotros es SIEMPRE = 1.
        /// </summary>
        public virtual Int16 EstadoDetallePedido
        {
            get { return estadoDetallePedido; }
            set { estadoDetallePedido = value; }
        }
        #endregion 

        #region métodos
        public virtual double obtenerTotal()
        {
            return (double)this.cantidad * this.precio;
        }
        #endregion
    }
}
