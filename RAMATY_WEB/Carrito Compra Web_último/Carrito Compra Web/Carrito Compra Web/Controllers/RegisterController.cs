using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using NHibernate.Cfg;
using NHibernate;
using NHibernate.Criterion;

namespace Carrito_Compra_Web.Controllers
{
    public class RegisterController : Controller
    {
        public ActionResult NuevoRegistro()
        {
            Carrito_Compra_Web.Models.Register model = new Models.Register();

            return View("Register", model);
        }

        public ActionResult EditRegistro()
        {
            Carrito_Compra_Web.Models.Register model = new Models.Register();

            if (SessionController.Session.Login == null)
            {
                return RedirectToAction("Index", "Home");
            }

            model.NombreUsuario.Value = Carrito_Compra_Web.SessionController.Session.Login.Usuario.NombreDeUsuario;
            return View("EditUssPassw", model);
        }

        [HttpPost]
        public ActionResult Registrar(string nro_cliente, string nro_documento, string user_name, string user_password, string confirmacion_password)
        {
            bool someError = false;

            Carrito_Compra_Web.Models.Register model = new Models.Register();
            
            model.AcceptTerms = true;
            //model.Password.Value = ""; //<== Ponemos el Pass??.
            //model.Password.Value = user_password;
            model.NumeroCliente.Value = nro_cliente;
            model.Dni.Value = nro_documento;
            model.NombreUsuario.Value = user_name;
            
            try
            {
                if (user_password != confirmacion_password)
                {
                    someError = true;
                    model.Password.IsError = true;
                    model.Password.ErrorDesc = "Las contraseñas no coinciden";
                }

                if (nro_cliente == null || nro_cliente == "")
                {
                    someError = true;
                    model.NumeroCliente.IsError = true;
                    model.NumeroCliente.ErrorDesc = "El numero de cliente es un campo obligatorio";      
                }

                if (nro_documento == null || nro_documento == "")
                {
                    someError = true;
                    model.Dni.IsError = true;
                    model.Dni.ErrorDesc = "El numero de dni/cuit es un campo obligatorio";               
                }

                if (user_name == null || user_name == "")
                {
                    someError = true;
                    model.NombreUsuario.IsError = true;
                    model.NombreUsuario.ErrorDesc = "El nombre de usuario es un campo obligatorio";      
                }

                Configuration cfg = new Configuration();
                cfg.Configure();
                ISessionFactory factory = cfg.BuildSessionFactory();
                using (ISession session = factory.OpenSession())
                {
                    /* PRIMERO HAY QUE VALIDAR QUE EL NOMBRE DE USUARIO NO EXISTA!!!! */
                    ICriteria userNameCriteria = session.CreateCriteria<Entity.Persistente.Usuario>();
                    userNameCriteria.Add(Restrictions.Eq("NombreDeUsuario", user_name));
                    IList<Entity.Persistente.Usuario> usuarios = userNameCriteria.List<Entity.Persistente.Usuario>();
                    if (usuarios.Count > 0)
                    {
                        someError = true;
                        model.NombreUsuario.IsError = true;
                        model.NombreUsuario.ErrorDesc = "El nombre de usuario ya existe";
                    }

                    /* VALIDAR QUE EL CLIENTE EXISTA == TIENE QUE COINCIDIR SI O SI EL DNI Y EL NRO DE CLIENTE */
                    int idCliente = 0;
                    int.TryParse(nro_cliente, out idCliente);
                    
                    ICriteria clienteCriteria = session.CreateCriteria<Entity.ReadOnly.Cliente>();
                    clienteCriteria.Add(Restrictions.Eq("NumeroCliente", idCliente));
                    clienteCriteria.Add(Restrictions.Eq("NumeroDocumento", nro_documento));

                    Entity.ReadOnly.Cliente cliente = clienteCriteria.UniqueResult<Entity.ReadOnly.Cliente>();

                    if (cliente == null)
                    {
                        /* EL CLIENTE NO EXISTE */
                        someError = true;

                        model.NumeroCliente.IsError = true;
                        model.NumeroCliente.ErrorDesc = "El numero de cliente o el DNI son incorrectos";
                        model.NumeroCliente.Value = nro_cliente;

                        model.Dni.IsError = true;
                        model.Dni.ErrorDesc = "El numero de cliente o el DNI son incorrectos";
                        model.Dni.Value = nro_documento;
                    }
                    else if (cliente.Usuario != null)
                    {
                        /* EL CLIENTE SI EXISTE PERO YA TIENE USUARIO */
                        someError = true;

                        model.ErrorGeneral = "El cliente ya tiene un usuario";
                        model.IsGeneralError = true;
                    }
                    else if(!someError)
                    { 
                        /* Hasta ahora venimos bien:
                         * Completo los campos obligatorios, no hubo errores de validación, puedo guardar */
                        Entity.Persistente.Usuario user = new Entity.Persistente.Usuario
                        {
                            NombreDeUsuario = user_name,
                            Password = ""
                        };

                        user.SetPassword(user_password);

                        cliente.Usuario = user;

                        ITransaction trans = session.BeginTransaction();
                        session.Save(user);
                        session.Save(cliente);
                        trans.Commit();

                        Carrito_Compra_Web.SessionController.Session.Login = new Models.Login
                        {
                            ErrorMessage = "",
                            IsLogged = true,
                            Usuario = new Models.Entities.Usuario { Id = user.Id, NombreDeUsuario = user.NombreDeUsuario }
                        };

                        session.Close();

                        return RedirectToAction("Index", "Home");
                    }

                    session.Close();
                }
            }
            catch (Exception e)
            {
                model.IsGeneralError = true;

                Exception exc = e;

                while (exc != null)
                {
                    model.ErrorGeneral += "\t\t - " + exc.Message + "\n";
                    exc = exc.InnerException;
                }
            }

            return View("Register", model);
        }

        [HttpPost]
        public ActionResult CambiarDatosRegistro(string user_name, string user_old_password, string user_new_password, string confirmacion_new_password)
        {
            bool someError = false;
            Carrito_Compra_Web.Models.Register model = new Models.Register();                        
            model.NombreUsuario.Value = user_name;
            //model.Password.Value = user_new_password;
            try
            {
                if (user_new_password != confirmacion_new_password)
                {
                    someError = true;
                    model.Password.IsError = true;
                    model.Password.ErrorDesc = "Las contraseñas no coinciden";
                }

                 Configuration cfg = new Configuration();
                cfg.Configure();
                ISessionFactory factory = cfg.BuildSessionFactory();
                using (ISession session = factory.OpenSession())
                {
                    /* PRIMERO VALIDAMOS QUE LA PASSWORD ANTIGUA (Ó LLAMESE ACTUAL) SEA CORRECTA!!!! */

                    ICriteria userNameCriteria = session.CreateCriteria<Entity.Persistente.Usuario>();
                    userNameCriteria.Add(Restrictions.Eq("NombreDeUsuario", Carrito_Compra_Web.SessionController.Session.Login.Usuario.NombreDeUsuario));
                    Entity.Persistente.Usuario usuario = userNameCriteria.UniqueResult<Entity.Persistente.Usuario>();
                    if (usuario !=null)
                    {
                        if (!usuario.IsPassword(user_old_password))
                        {
                            someError = true;
                            model.OldPassword.IsError = true;
                            model.OldPassword.ErrorDesc = "La contraseña ingresada no es correcta";
                        }
                    }

                    if (!someError)
                    {
                        /* Todo ok: Completo los campos obligatorios, no hubo errores de validación, puedo guardar */
                        Entity.Persistente.Usuario u = session.Load<Entity.Persistente.Usuario>(Carrito_Compra_Web.SessionController.Session.Login.Usuario.Id);
                        u.NombreDeUsuario = user_name;
                        u.SetPassword(user_new_password);

                        ITransaction trans = session.BeginTransaction();
                        session.Save(u);
                        trans.Commit();

                        session.Close();

                        Carrito_Compra_Web.SessionController.Session.Login.Usuario.NombreDeUsuario = u.NombreDeUsuario;

                        return RedirectToAction("Index", "Home");
                    }
                    session.Close();
                }


                return View("EditUssPassw", model);
            }
            catch (Exception e)
            {
                model.IsGeneralError = true;

                Exception exc = e;

                while (exc != null)
                {
                    model.ErrorGeneral += "\t\t - " + exc.Message + "\n";
                    exc = exc.InnerException;
                }
            }
            return View("EditUssPassw", model);
        }
    }
}
