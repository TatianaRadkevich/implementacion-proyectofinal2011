using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using NHibernate;
using NHibernate.Criterion;

namespace Carrito_Compra_Web.Controllers
{
    public class HomeController : Controller
    {
        //
        // GET: /Index/
        public ActionResult Index()
        {
            return View("Index");
        }

        [HttpPost]
        public ActionResult Login(string txt_user_name, string txt_password)
        {
            ViewBag.NombreUsuario = txt_user_name;
            
            try
            {
                NHibernate.Cfg.Configuration cfg = new NHibernate.Cfg.Configuration();
                cfg.Configure();
                NHibernate.ISessionFactory factory = cfg.BuildSessionFactory();
                using (ISession session = factory.OpenSession())
                {
                    ICriteria criterio = session.CreateCriteria<Entity.Persistente.Usuario>();

                    ICriterion eqUserName = Restrictions.Eq("NombreDeUsuario", txt_user_name);
                    criterio.Add(eqUserName);

                    Entity.Persistente.Usuario user = criterio.UniqueResult<Entity.Persistente.Usuario>();

                    if (user != null && user.IsPassword(txt_password))
                    {
                        ICriteria clienteCriteria = session.CreateCriteria<Entity.ReadOnly.Cliente>();
                        clienteCriteria.Add(Restrictions.Eq("Usuario", user));

                        Entity.ReadOnly.Cliente cliente = clienteCriteria.UniqueResult<Entity.ReadOnly.Cliente>();

                        Carrito_Compra_Web.SessionController.Session.Login = new Carrito_Compra_Web.Models.Login
                        {
                            Usuario = new Models.Entities.Usuario { Id = user.Id, NombreDeUsuario = user.NombreDeUsuario },
                            IsLogged = true,
                            ErrorMessage = "",
                            Cliente = new Models.Entities.Cliente { Apellido = cliente.Apellido, Nombre = cliente.Nombre, NumeroDocumento = cliente.NumeroDocumento }
                        };
                    }
                    else
                    {
                        Carrito_Compra_Web.SessionController.Session.Login = new Carrito_Compra_Web.Models.Login
                        {
                            Usuario = null,
                            IsLogged = false,
                            ErrorMessage = "Nombre de usuario o contraseña incorrecto"
                        };
                    }

                    session.Close();
                }
            }
            catch (Exception e)
            {
                Carrito_Compra_Web.SessionController.Session.Login = new Carrito_Compra_Web.Models.Login
                {
                    Usuario = null,
                    IsLogged = false,
                    ErrorMessage = ""
                };

                Exception exc = e;

                while (exc != null)
                {
                    Carrito_Compra_Web.SessionController.Session.Login.ErrorMessage += "\t\t - " + exc.Message + "\n";
                    exc = exc.InnerException;
                }
            }
          
            return View("Index");            
        }

        public ActionResult Logout()
        {
            Carrito_Compra_Web.SessionController.Session.Login = null;

            return View("Index");
        }
    }
}
