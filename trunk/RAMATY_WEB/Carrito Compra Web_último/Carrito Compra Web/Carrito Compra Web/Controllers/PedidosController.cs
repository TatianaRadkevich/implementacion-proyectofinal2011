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
    public class PedidosController : Controller
    {
        public ActionResult NuevoPedido()
        {
            Carrito_Compra_Web.Models.ListaPedido model = new Models.ListaPedido();

            if (SessionController.Session.Login == null)
            {
                return RedirectToAction("Index", "Home");
            }

            return View("ConsultarPedidos", model);
        }

        [HttpPost]
        public JsonResult ListarPedidos(string sidx, string sord, int page, int rows)  //sidx = nombre atributo por el cual  ordena  //sord= asc o desc  //page= pagina q estoy solicitando //rows=cantidad de filas q stoy viendo en esa pagina
        {
            List<Carrito_Compra_Web.Models.Entities.Pedido> pedidos = new List<Models.Entities.Pedido>();
            int cantidadTotal = 0;
            int cantidadPagina = 0;

            if (SessionController.Session.Login != null && SessionController.Session.Login.IsLogged)
            {
                try
                {
                    Configuration cfg = new Configuration();
                    cfg.Configure();
                    ISessionFactory factory = cfg.BuildSessionFactory();
                    using (ISession session = factory.OpenSession())
                    {
                        /* Obtenemos el Usuario */
                        
                        
                        Entity.Persistente.Usuario usuario = session.Load<Entity.Persistente.Usuario>(SessionController.Session.Login.Usuario.Id);

                        /* Obtenemos el cliente para ese usuario */
                        ICriteria clienteCriteria = session.CreateCriteria<Entity.ReadOnly.Cliente>();
                        clienteCriteria.Add(Restrictions.Eq("Usuario", usuario));
                        Entity.ReadOnly.Cliente cliente = clienteCriteria.UniqueResult<Entity.ReadOnly.Cliente>();

                        /* Obtenemos la lista de pedidos para ese cliente */
                        ICriteria pedidoCriteria = session.CreateCriteria<Entity.Persistente.Pedido>("p");
                        pedidoCriteria.CreateCriteria("Estado", "e", NHibernate.SqlCommand.JoinType.InnerJoin);
                        pedidoCriteria.Add(Restrictions.IsNull("FechaBaja"));
                        pedidoCriteria.Add(Restrictions.Eq("Cliente", cliente));

                        //para hacer el virtual scroll.
                        cantidadTotal = cantidadPedidos(cliente, session);
                        
                        int inicio = ((page - 1) * rows);
                        int final = rows;
                        pedidoCriteria.SetFirstResult(inicio);
                        pedidoCriteria.SetMaxResults(final);
                        if (sord=="asc")
                        {
                            pedidoCriteria.AddOrder(Order.Asc(sidx));
                        }
                        else
                        {
                            pedidoCriteria.AddOrder(Order.Desc(sidx));
                        }

                        IList<Entity.Persistente.Pedido> listPedido = pedidoCriteria.List<Entity.Persistente.Pedido>();
                        cantidadPagina = listPedido.Count;
                        foreach (Entity.Persistente.Pedido p in listPedido)
                        {
                            pedidos.Add(new Carrito_Compra_Web.Models.Entities.Pedido
                            {
                                Id = p.Id,
                                FechaSolicitada = p.FechaSolicitada,
                                FechaEstimadaEntrega = p.FechaEstimadaEntrega,
                                TotalString = "$" + p.ObtenerTotalConFormato("#0.00"),
                                Estado = p.Estado.Nombre,
                            });
                        }

                        session.Close();
                    }
                }
                catch (Exception e)
                {

                    Exception exc = e;

                    string strError = "Se generó un error al obtener la lista de pedidos";

                    while (exc != null)
                    {
                        strError += exc.Message + "\n";
                        exc = exc.InnerException;
                    }
                }
            }
            
            var data = new
            {
                total = cantidadTotal/rows + 1,
                page = page,
                records = cantidadTotal,
                rows = from a in pedidos
                       select new
                       {
                           id = a.Id,
                           cell = new string[] 
                           {
                               a.Id.ToString(),
                               a.FechaSolicitada == null ? "" : ((DateTime)a.FechaSolicitada).ToString("dd/MM/yyyy"),
                               a.FechaEstimadaEntrega == null ? "" : ((DateTime)a.FechaEstimadaEntrega).ToString("dd/MM/yyyy"),
                               a.TotalString,
                               a.Estado
                           }
                       }
            };
            
            return Json(data);
        }

        private int cantidadPedidos(Entity.ReadOnly.Cliente cliente, ISession session)
        {
            int cant = 0;

            try
            {
                ICriteria pedidoCriteria = session.CreateCriteria<Entity.Persistente.Pedido>();
                pedidoCriteria.Add(Restrictions.IsNull("FechaBaja"));
                pedidoCriteria.Add(Restrictions.Eq("Cliente", cliente));
                pedidoCriteria.SetProjection(Projections.RowCount());
                cant = ((int)pedidoCriteria.UniqueResult());
            }
            catch (Exception e)
            {
                throw new Exception("Error al consultar la cantidad de pedidos", e);
            }

            return cant;
        }

        [HttpPost]
        public JsonResult CancelarPedido(long id_pedido)  //sidx = nombre atributo por el cual  ordena  //sord= asc o desc  //page= pagina q estoy solicitando //rows=cantidad de filas q stoy viendo en esa pagina
        {
            if (SessionController.Session.Login == null || !SessionController.Session.Login.IsLogged)
            {
                var data = new
                {
                    Success = false,
                    ErrorDesc = "Debe estar logueado para eliminar un pedido."
                };

                return Json(data);
            }

            try
            {
                Configuration cfg = new Configuration();
                cfg.Configure();
                ISessionFactory factory = cfg.BuildSessionFactory();
                using (ISession session = factory.OpenSession())
                {
                    /* Obtenemos el Pedido */
                    Entity.Persistente.Pedido pedido = session.Load<Entity.Persistente.Pedido>(id_pedido);

                    if (pedido == null)
                    {
                        var data = new
                        {
                            Success = false,
                            ErrorDesc = "El pedido no existe."
                        };

                        return Json(data);
                    }

                    if (pedido.Estado.Id != 1)
                    {
                        var data = new
                        {
                            Success = false,
                            ErrorDesc = "El pedido no puede cancelarse pues ya está planificado."
                        };

                        return Json(data);
                    }

                    ITransaction trans = session.BeginTransaction();

                    Entity.ReadOnly.EstadoPedido nuevoEstado = session.Load<Entity.ReadOnly.EstadoPedido>(3);
                    pedido.FechaBaja = DateTime.Today;
                    pedido.Estado = nuevoEstado;

                    session.Save(pedido);

                    trans.Commit();

                    session.Close();
                }
            }
            catch (Exception e)
            {

                Exception exc = e;

                string strError = "Se generó un error al cancelar pedido\n";

                while (exc != null)
                {
                    strError += exc.Message + "\n";
                    exc = exc.InnerException;
                }

                var data = new
                {
                    Success = false,
                    ErrorDesc = strError
                };

                return Json(data);
            }

            var ret = new
            {
                Success = true,
                ErrorDesc = ""
            };

            return Json(ret);
        }
    }
}
