using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Carrito_Compra_Web.Models
{
    public class Login
    {
        public Carrito_Compra_Web.Models.Entities.Usuario Usuario
        {
            get;
            set;
        }

        public Carrito_Compra_Web.Models.Entities.Cliente Cliente
        {
            get;
            set;
        }

        public bool IsLogged
        {
            get;
            set;
        }

        public string ErrorMessage
        {
            get;
            set;
        }

        public Login()
        {
            Usuario = null;
            IsLogged = false;
            ErrorMessage = "";
        }
    }
}