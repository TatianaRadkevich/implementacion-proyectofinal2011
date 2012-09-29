using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Carrito_Compra_Web.Models
{
    public class Field
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

        public Field()
        {
            this.Value = "";
            this.IsError = false;
            this.ErrorDesc = "";
        }
    }

    public class Register
    {
        public Field NumeroCliente
        {
            get;
            set;
        }

        public Field Dni
        {
            get;
            set;
        }

        public Field NombreUsuario
        {
            get;
            set;
        }

        public Field Password
        {
            get;
            set;
        }

        public Field OldPassword
        {
            get;
            set;
        }

        public bool AcceptTerms
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

        public Register()
        {
            this.Dni = new Field();
            this.NombreUsuario = new Field();
            this.NumeroCliente = new Field();
            this.OldPassword = new Field();
            this.Password = new Field();
            this.AcceptTerms = false;
            this.ErrorGeneral = "";
            this.IsGeneralError = false;
        }
    }
}