using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Entity.ReadOnly
{
   public class UnidadMedida
    {
        #region Id
        private int id;
        private string nombre;
        private string descripcion;
        #endregion

        #region propiedades

        /// <summary>
        /// Id de la Unidad de Medida
        /// </summary>
        public virtual int Id
        {
            get { return id; }
            set { id = value; }
        }

        /// <summary>
        /// Nombre de la Unidad de Medida
        /// </summary>
        public virtual string Nombre
        {
            get { return nombre; }
            set { nombre = value; }
        }

        /// <summary>
        /// Descripción de la Unidad de Medida
        /// </summary>
        public virtual string Descripcion
        {
            get { return descripcion; }
            set { descripcion = value; }
        }
        #endregion
    }
}
