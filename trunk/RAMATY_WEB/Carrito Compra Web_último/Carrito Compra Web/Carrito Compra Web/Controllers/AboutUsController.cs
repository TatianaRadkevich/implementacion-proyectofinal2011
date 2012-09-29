using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Carrito_Compra_Web.Controllers
{
    public class AboutUsController : Controller
    {
        public ActionResult Index()
        {
            return View("AboutUs");
        }

    }
}
