using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Carrito_Compra_Web.Models
{
    public class Campo
    {
        public string Value
        {
            get;
            set;
        }

        public bool IsError
        {
            get;
            set;
        }

        public string ErrorDesc
        {
            get;
            set;
        }

        public Campo()
        {
            this.Value = "";
            this.IsError = false;
            this.ErrorDesc = "";
        }
    }

    public class Productos
    {
        public Carrito_Compra_Web.Models.Entities.Producto Producto
        {
            get;
            set;
        }

        public Carrito_Compra_Web.Models.Entities.DetalleProducto DetalleProducto
        {
            get;
            set;
        }        
        public Campo NombreProductoBuscado
        {
            get;
            set;
        }
        public Campo FechaEntregaSolicitada
        {
            get;
            set;
        }
        public Campo FormaDePago
        {
            get;
            set;
        }
        public Campo DireccAEntregar
        {
            get;
            set;
        }
        public Campo TelAContactar
        {
            get;
            set;
        }

        public string ErrorGeneral
        {
            get;
            set;
        }

        public bool IsGeneralError
        {
            get;
            set;
        }

        public Productos()
        {
            Producto = null;
            DetalleProducto = null;
            NombreProductoBuscado = new Campo();
            FechaEntregaSolicitada = new Campo();
            FormaDePago = new Campo();
            DireccAEntregar = new Campo();
            TelAContactar = new Campo();
            ErrorGeneral = "";
            IsGeneralError = false;
        }


    }
}