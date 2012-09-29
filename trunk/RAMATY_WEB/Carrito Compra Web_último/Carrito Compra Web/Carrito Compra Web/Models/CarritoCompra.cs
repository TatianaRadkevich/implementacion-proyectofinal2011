using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Carrito_Compra_Web.Models
{
    public class CarritoCompra
    {
        public List<Models.Entities.DetallePedido> DetallesPedido
        {
            get;
            set;
        }

        public double Total
        {
            get;
            set;
        }

        public string FechaRequerida
        {
            get;
            set;
        }
    }
}