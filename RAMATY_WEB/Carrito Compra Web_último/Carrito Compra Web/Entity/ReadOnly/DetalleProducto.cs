using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Entity.ReadOnly
{
    public class DetalleProducto
    {
        #region atributos
        private Int64 id;        
        private int medida;
        #endregion

        #region propiedades
        /// <summary>
        /// Id.
        /// </summary>
        public virtual Int64 Id
        {
            get { return id; }
            set { id = value; }
        }

        /// <summary>
        /// Valor de la medida
        /// </summary>
        public virtual int Medida
        {
            get { return medida; }
            set { medida = value; }
        }
        #endregion
    }
}
