using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Carrito_Compra_Web.SessionController
{
    public class Session
    {
        private static System.Web.SessionState.HttpSessionState currentSession
        {
            get
            {
                if (HttpContext.Current.Session == null)
                    throw new Exception("Session is not available in the current context.");
                else
                    return HttpContext.Current.Session;
            }
        }

        public static Carrito_Compra_Web.Models.Login Login
        {
            get
            {
                return (Carrito_Compra_Web.Models.Login)currentSession["Login"];
            }
            set
            {
                if (value != null)
                    currentSession["Login"] = value;
                else
                    currentSession.Remove("Login");
            }
        }

        /// <summary>
        /// Para cargar un nuevo pedido del carrito de compra
        /// </summary>
        public static Carrito_Compra_Web.Models.CarritoCompra CarritoCompra
        {
            get
            {
                return (Carrito_Compra_Web.Models.CarritoCompra)currentSession["CarritoCompra"];
            }
            set
            {
                if (value != null)
                    currentSession["CarritoCompra"] = value;
                else
                    currentSession.Remove("CarritoCompra");
            }
        }
    }
}