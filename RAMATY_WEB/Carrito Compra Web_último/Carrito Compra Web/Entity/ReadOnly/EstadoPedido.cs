using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Entity.ReadOnly
{
    public class EstadoPedido
    {
        #region atributos
        private int id;
        private string nombre;
        private string descripcion;
        #endregion

        #region propiedades
        public virtual int Id
        {
            get { return id; }
            set { id = value; }
        }

        public virtual string Nombre
        {
            get { return nombre; }
            set { nombre = value; }
        }

        public virtual string Descripcion
        {
            get { return descripcion; }
            set { descripcion = value; }
        }
        #endregion
    }
}
