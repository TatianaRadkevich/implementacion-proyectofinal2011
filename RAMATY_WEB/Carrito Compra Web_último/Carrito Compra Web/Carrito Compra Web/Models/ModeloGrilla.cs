using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Carrito_Compra_Web.Models
{
    public class ModeloGrilla
    {
        public IList<Row> rows
        {
            get;
            set;
        }
        public int total
        {
            get;
            set;
        }
        public int page
        {
            get;
            set;
        }
        public int records
        {
            get;
            set;
        }
    }

    public class Row
    {
        public string[] cell
        {
            get;
            set;
        }
        public int id
        {
            get;
            set;
        }
    }
}