using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Carrito_Compra_Web.Models
{
    public class ListaProducto
    {
        public List<Models.Entities.Producto> Productos
        {
            get;
            set;
        }

        public bool HayError
        {
            get;
            set;
        }

        public string DescripcionError
        {
            get;
            set;
        }

        public bool Logeado
        {
            get;
            set;
        }
    }
}