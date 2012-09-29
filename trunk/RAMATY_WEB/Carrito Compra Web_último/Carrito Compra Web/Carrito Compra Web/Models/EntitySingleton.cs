using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Carrito_Compra_Web.Models
{
    public class EntitySingleton : Controller
    {
        //
        // GET: /EntitySingleton/
        #region atributos
        static EntitySingleton singleton;
        private int numero;
        #endregion

        #region métodos
        static public EntitySingleton getInstance()
        {
            if(singleton == null)
            {
                singleton = new EntitySingleton();
            }
        return singleton;
        }
    
        public int getNumber()
        {        
            return numero;
        }
        public ActionResult Index()
        {
            return View();
        }
        #endregion

        //Singleton s = Singleton.getInstance();
        //s.getNumber();
    }
}
