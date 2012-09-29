using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Entity.ReadOnly
{
    public class Producto
    {
        #region atributos
        private int codigo;
        private string nombre;
        private string descripcion;
        private Entity.ReadOnly.UnidadMedida unidadDeMedida;
        private Entity.ReadOnly.TipoProducto tipoProducto;
        private IList<Entity.ReadOnly.DetalleProducto> detallesProducto;
        private double precioUnitario;
        private DateTime? fechaBaja;
        #endregion

        #region propiedades
        /// <summary>
        /// Código del producto.
        /// ¿Es el Id?, ¿Es auto-incremental?       >> SI, CORREGIDO
        /// </summary>
        public virtual int Codigo
        {
            get { return codigo; }
            set { codigo = value; }
        }

        /// <summary>
        /// Nombre del producto.
        /// </summary>
        public virtual string Nombre
        {
            get { return nombre; }
            set { nombre = value; }
        }

        /// <summary>
        /// Descripción del producto.
        /// </summary>
        public virtual string Descripcion
        {
            get { return descripcion; }
            set { descripcion = value; }
        }

        /// <summary>
        /// Unidad de medida del producto.
        /// </summary>
        public virtual Entity.ReadOnly.UnidadMedida UnidadDeMedida
        {
            get { return unidadDeMedida; }
            set { unidadDeMedida = value; }
        }

        /// <summary>
        /// Tipo de producto.
        /// </summary>
        public virtual Entity.ReadOnly.TipoProducto TipoProducto
        {
            get { return tipoProducto; }
            set { tipoProducto = value; }
        }

        /// <summary>
        /// Detalles del producto.
        /// </summary>
        public virtual IList<Entity.ReadOnly.DetalleProducto> DetallesProducto
        {
            get { return detallesProducto; }
            set { detallesProducto = value; }
        }

        public virtual double PrecioUnitario
        {
            get { return precioUnitario; }
            set { precioUnitario = value; }
        }

        public virtual DateTime? FechaBaja
        {
            get { return fechaBaja; }
            set { fechaBaja = value; }
        }
        #endregion
    }
}
