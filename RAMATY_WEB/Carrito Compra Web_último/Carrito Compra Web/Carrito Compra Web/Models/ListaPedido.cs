using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Carrito_Compra_Web.Models
{
    public class ListaPedido
    {
        public List<Models.Entities.Pedido> Pedidos
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

        public ListaPedido()
        {
            Pedidos = null;
            HayError = false;
            DescripcionError = "";
        }
    }
}