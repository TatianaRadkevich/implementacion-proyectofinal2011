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
    public class ProductosController : Controller
    {
       
        public ActionResult ListarProducto()
        {
            return View("Productos");
        }

        [HttpPost]
        public JsonResult ListarProductos(int inicio, int fin, string filtro, string columna_orden)
        {
            List<Carrito_Compra_Web.Models.Entities.Producto> productos = new List<Models.Entities.Producto>();
            bool logueado = false;
            if (SessionController.Session.Login != null)
            {
                logueado = SessionController.Session.Login.IsLogged;
            }

            try
            {
                Configuration cfg = new Configuration();
                cfg.Configure();
                ISessionFactory factory = cfg.BuildSessionFactory();
                using (ISession session = factory.OpenSession())
                {
                    ICriteria productoCriteria = session.CreateCriteria<Entity.ReadOnly.Producto>();
                    productoCriteria.Add(Restrictions.Like("Nombre", "%" + filtro + "%"));
                    productoCriteria.SetFirstResult(inicio);
                    productoCriteria.SetMaxResults(fin);
                    productoCriteria.AddOrder(Order.Desc(columna_orden));
                    productoCriteria.Add(Restrictions.IsNull("FechaBaja"));

                    IList<Entity.ReadOnly.Producto> listProducto = productoCriteria.List<Entity.ReadOnly.Producto>();

                    
                    foreach (Entity.ReadOnly.Producto p in listProducto)
                    {
                        productos.Add(new Carrito_Compra_Web.Models.Entities.Producto
                        {
                            Codigo = p.Codigo,
                            Descripcion = p.Descripcion,
                            FechaBaja = p.FechaBaja,
                            Nombre = p.Nombre,
                            PrecioUnitario = p.PrecioUnitario,
                            TipoProducto = new Models.Entities.TipoProducto
                            {
                                Descripcion = p.TipoProducto.Descripcion,
                                Id = p.TipoProducto.Id,
                                Nombre = p.TipoProducto.Nombre
                            },
                            UnidadDeMedida = new Models.Entities.UnidadMedida
                            {
                                Descripcion = p.UnidadDeMedida.Descripcion,
                                Id = p.UnidadDeMedida.Id,
                                Nombre = p.UnidadDeMedida.Nombre
                            }
                        });

                    }

                    session.Close();
                }
            }
            catch (Exception e)
            {

                Exception exc = e;

                string strError="";
                
                while (exc != null)
                {
                    strError += exc.Message + "\n";
                    exc = exc.InnerException;
                }

                return Json(new {
                    DescripcionError = strError,
                    HayError = true
                });

            }
            
            return Json(new Carrito_Compra_Web.Models.ListaProducto{DescripcionError = "",
                                                                    HayError = false,
                                                                    Logeado = logueado,
                                                                    Productos = productos
                                                                    });
        }

        [HttpPost]
        public int CantidadProductos(string filtro)
        {
            int cant = 0;

            try
            {
                Configuration cfg = new Configuration();
                cfg.Configure();
                ISessionFactory factory = cfg.BuildSessionFactory();
                using (ISession session = factory.OpenSession())
                {
                    ICriteria productoCriteria = session.CreateCriteria<Entity.ReadOnly.Producto>();
                    productoCriteria.Add(Restrictions.Like("Nombre", "%" + filtro + "%"));
                    productoCriteria.Add(Restrictions.IsNull("FechaBaja"));

                    productoCriteria.SetProjection(Projections.RowCount());

                    cant = ((int)productoCriteria.UniqueResult());
                }
            }
            catch (Exception e)
            {
                return 0;
            }

            return cant;
        }

        [HttpPost]
        public JsonResult addProducto(string codigo, int cantidad)
        {
            if (SessionController.Session.Login == null || !SessionController.Session.Login.IsLogged)
            {
                return Json(new {
                    DescripcionError = "Debe estar logueado para realizar esta operación.",
                    HayError = true
                }); 
            }

            try
            {
                Configuration cfg = new Configuration();
                cfg.Configure();
                ISessionFactory factory = cfg.BuildSessionFactory();
                using (ISession session = factory.OpenSession())
                {

                    if (SessionController.Session.CarritoCompra == null)
                    {
                        SessionController.Session.CarritoCompra = new Models.CarritoCompra();
                    }

                    if (SessionController.Session.CarritoCompra.DetallesPedido == null)
                    {
                        SessionController.Session.CarritoCompra.DetallesPedido = new List<Models.Entities.DetallePedido>();
                    }

                    int idProducto = 0;
                    int.TryParse(codigo, out idProducto);

                    //Si existe el articulo se suma al ya ingresado anteriormente para no hacer una nueva busqueda
                    foreach (Models.Entities.DetallePedido p in SessionController.Session.CarritoCompra.DetallesPedido)
                    {
                        if (p.Producto.Codigo == idProducto)
                        {
                            p.Cantidad += cantidad;
                            return Json(new {
                                DescripcionError = "",
                                HayError = false
                            });
                        }
                    }

                    Entity.ReadOnly.Producto prod = session.Load<Entity.ReadOnly.Producto>(idProducto);
                    if (prod == null)
                    {
                        return Json(new {
                            DescripcionError = "El producto ha sido eliminado.",
                            HayError = true
                        });
                    }

                    SessionController.Session.CarritoCompra.DetallesPedido.Add( new Models.Entities.DetallePedido {
                                                                                    Producto = new Models.Entities.Producto
                                                                                    {
                                                                                        Codigo = prod.Codigo,
                                                                                        Descripcion = prod.Descripcion,
                                                                                        Nombre = prod.Nombre,
                                                                                        PrecioUnitario = prod.PrecioUnitario
                                                                                    },
                                                                                    Cantidad = cantidad,
                                                                                    Precio = prod.PrecioUnitario
                                                                                });
                }
            }
            catch (Exception e)
            {
                Exception exc = e;
                string strError = "";

                while (exc != null)
                {
                    strError += exc.Message + "\n";
                    exc = exc.InnerException;
                }


                return Json(new {
                    DescripcionError = strError,
                    HayError = true
                });
            }

            return Json(new {
                DescripcionError = "",
                HayError = false
            });
        }

        [HttpPost]
        public JsonResult MostrarCarrito()
        {
            if (SessionController.Session.Login == null || !SessionController.Session.Login.IsLogged)
            {
                return Json(new
                {
                    DescripcionError = "Debe estar logueado para realizar esta operación.",
                    HayError = true
                });
            }

            try
            {
                if (SessionController.Session.CarritoCompra == null || SessionController.Session.CarritoCompra.DetallesPedido == null || SessionController.Session.CarritoCompra.DetallesPedido.Count == 0)
                {
                    return Json(new {
                        DescripcionError = "No existen productos en el carrito de compra",
                        HayError = true
                    });
                }

                SessionController.Session.CarritoCompra.Total = 0;

                //Si existe el articulo se suma al ya ingresado anteriormente para no hacer una nueva busqueda
                foreach (Models.Entities.DetallePedido p in SessionController.Session.CarritoCompra.DetallesPedido)
                {
                    p.Total = p.Cantidad * p.Precio;
                    SessionController.Session.CarritoCompra.Total += p.Total;
                }

                if (SessionController.Session.CarritoCompra.FechaRequerida == "" || SessionController.Session.CarritoCompra.FechaRequerida == null)
                {
                    SessionController.Session.CarritoCompra.FechaRequerida = DateTime.Today.ToString("dd/MM/yyyy");
                }
            }
            catch (Exception e)
            {

                Exception exc = e;

                string strError = "";

                while (exc != null)
                {
                    strError += exc.Message + "\n";
                    exc = exc.InnerException;
                }

                return Json(new {
                    DescripcionError = strError,
                    HayError = true
                });
            }

            return Json(SessionController.Session.CarritoCompra);
        }

        [HttpPost]
        public JsonResult quitarProducto(string codigo)
        {
            if (SessionController.Session.Login == null || !SessionController.Session.Login.IsLogged)
            {
                return Json(new {
                    DescripcionError = "Debe estar logueado para realizar esta operación",
                    HayError = true
                });
            }

            try
            {
                int idProducto = 0;
                int.TryParse(codigo, out idProducto);

                //Si existe el articulo se suma al ya ingresado anteriormente para no hacer una nueva busqueda
                foreach (Models.Entities.DetallePedido p in SessionController.Session.CarritoCompra.DetallesPedido)
                {
                    if (p.Producto.Codigo == idProducto)
                    {
                        SessionController.Session.CarritoCompra.DetallesPedido.Remove(p);
                        return Json(new {
                            DescripcionError = "",
                            HayError = false
                        });
                    }
                }
            }
            catch (Exception e)
            {

                Exception exc = e;

                string strError = "";

                while (exc != null)
                {
                    strError += exc.Message + "\n";
                    exc = exc.InnerException;
                }

                return Json(new {
                    DescripcionError = strError,
                    HayError = true
                });
            }

            return Json(new {
                DescripcionError = "",
                HayError = false
            });
        }

        [HttpPost]
        public JsonResult limpiarCarro()
        {
            if (SessionController.Session.Login == null || !SessionController.Session.Login.IsLogged)
            {
                return Json(new
                {
                    DescripcionError = "Debe estar logueado para realizar esta operación",
                    HayError = true
                });
            }

            try
            {
                SessionController.Session.CarritoCompra = null;
                
                return Json(new
                {
                    DescripcionError = "",
                    HayError = false
                });

            }
            catch (Exception e)
            {

                Exception exc = e;

                string strError = "";

                while (exc != null)
                {
                    strError += exc.Message + "\n";
                    exc = exc.InnerException;
                }


                return Json(new {
                    DescripcionError = strError,
                    HayError = true
                });
            }
        }

        [HttpPost]
        public JsonResult GrabarPedido(string fechaReq)
        {
            if (SessionController.Session.Login == null || !SessionController.Session.Login.IsLogged)
            {
                return Json(new
                {
                    DescripcionError = "Debe estar logueado para realizar esta operación.",
                    HayError = true
                });
            }

            if (SessionController.Session.CarritoCompra == null || SessionController.Session.CarritoCompra.DetallesPedido.Count == 0)
            {
                return Json(new
                {
                    DescripcionError = "Debe haber cargado por lo menos un producto al carrito de compra para realizar esta operación.",
                    HayError = true
                });
            }

            try
            {
                Configuration cfg = new Configuration();
                cfg.Configure();
                ISessionFactory factory = cfg.BuildSessionFactory();
                using (ISession session = factory.OpenSession())
                {
                    //Obtenemos el estado de pedido.
                    Entity.ReadOnly.EstadoPedido estPed = session.Load<Entity.ReadOnly.EstadoPedido>(1);

                    //Obtenemos el usuario.
                    Entity.Persistente.Usuario usuario = session.Load<Entity.Persistente.Usuario>(SessionController.Session.Login.Usuario.Id);
                    
                    //Obtenemos el cliente para ese usuario
                    ICriteria clienteCriteria = session.CreateCriteria<Entity.ReadOnly.Cliente>();
                    clienteCriteria.Add(Restrictions.Eq("Usuario", usuario));
                    Entity.ReadOnly.Cliente cliente = clienteCriteria.UniqueResult<Entity.ReadOnly.Cliente>();

                    Entity.Persistente.Pedido pedido = new Entity.Persistente.Pedido
                    {
                        FechaHoraGeneracion = DateTime.Today,
                        FechaSolicitada = Convert.ToDateTime(fechaReq),
                        Prioridad = 2,  //nosotros no manejamos esto en el Dominio
                        Estado = estPed,
                        TipoPedido = 1,
                        Cliente = cliente
                    };

                    pedido.DetallesPedido = new List<Entity.Persistente.DetallePedido>();

                    foreach (Models.Entities.DetallePedido dp in SessionController.Session.CarritoCompra.DetallesPedido)
                    {
                        Entity.ReadOnly.Producto prod = session.Load<Entity.ReadOnly.Producto>(dp.Producto.Codigo);
                        
                        Entity.Persistente.DetallePedido detPed = new Entity.Persistente.DetallePedido
                        {
                            Cantidad=dp.Cantidad,
                            Precio=dp.Precio,
                            Producto=prod,
                            Id = 0
                        };

                        pedido.DetallesPedido.Add(detPed);
                    }

                    ITransaction trans = session.BeginTransaction();
                    session.Save(pedido);
                    trans.Commit();

                    SessionController.Session.CarritoCompra = null;
                }
            }
            catch (Exception e)
            {
                Exception exc = e;
                string strError = "";

                while (exc != null)
                {
                    strError += exc.Message + "\n";
                    exc = exc.InnerException;
                }


                return Json(new
                {
                    DescripcionError = strError,
                    HayError = true
                });
            }

            return Json(new
            {
                DescripcionError = "",
                HayError = false
            });
        }

        [HttpPost]
        public JsonResult ActualizarFechaRequerida(string fecha_requerida)
        {
            if (SessionController.Session.Login == null || !SessionController.Session.Login.IsLogged)
            {
                return Json(new
                {
                    DescripcionError = "Debe estar logueado para realizar esta operación",
                    HayError = true
                });
            }
            
            DateTime fecha = DateTime.Today;

            DateTime.TryParse(fecha_requerida, out fecha);

            SessionController.Session.CarritoCompra.FechaRequerida = fecha.ToString("dd/MM/yyyy");

            return Json(new
            {
                DescripcionError = "",
                HayError = false
            });
        }

    }
}
