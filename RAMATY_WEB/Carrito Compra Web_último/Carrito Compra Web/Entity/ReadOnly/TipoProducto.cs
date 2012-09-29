using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Entity.ReadOnly
{
    public class TipoProducto
    {
        #region atributos
        private Int16 id;        
        private string nombre;
        private string descripcion;        
        #endregion

        #region propiedades
        public virtual string Nombre
        {
            set { nombre = value; }
            get { return nombre; }
        }
        public virtual Int16 Id
        {
            get { return id; }
            set { id = value; }
        }
        public virtual string Descripcion
        {
            get { return descripcion; }
            set { descripcion = value; }
        }
        #endregion        
    }
}
