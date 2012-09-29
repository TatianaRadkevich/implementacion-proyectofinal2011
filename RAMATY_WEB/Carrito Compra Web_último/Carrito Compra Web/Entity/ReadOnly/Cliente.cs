using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Entity.ReadOnly
{
    public class Cliente
    {
        #region atributos
        private int numeroCliente;
        private string nombre;
        private string apellido;
        private string numeroDocumento;
        private string email;
        private Int64 numeroTelefono;
        private DateTime? fechaBaja;
        private Entity.Persistente.Usuario usuario;        
        #endregion

        #region propiedades
        /// <summary>
        /// Nombres del cliente
        /// </summary>
        public virtual string Nombre
        {
            get{return nombre;}
            set { nombre = value; }
        }

        /// <summary>
        /// Apellido del cliente
        /// </summary>
        public virtual string Apellido
        {
            get { return apellido; }
            set { apellido = value; }
        }

        

        /// <summary>
        /// Numero de DNI/CUIT/LE/Etc...
        /// El valor no puede ser mayor a: 2147483648  >> CORREGIDO!!  
        /// Supongamos mi CUIT: 23297119079.           
        /// Esto genera un error...
        /// ¡ver!
        /// </summary>
        public virtual string NumeroDocumento
        {
            get { return numeroDocumento; }
            set { numeroDocumento = value; }
        }

        /// <summary>
        /// E-Mail del cliente.
        /// </summary>
        public virtual string Email
        {
            get { return email; }
            set { email = value; }
        }

        /// <summary>
        /// No puede ser mayor a 2147483648.
        /// Supongamos mi numéro de teléfono: 351156783108    >> CORREGIDO!!  
        /// Genera un error.
        /// ¡Ver!
        /// </summary>
        public virtual Int64 NumeroTelefono
        {
            get { return numeroTelefono; }
            set { numeroTelefono = value; }
        }

        /// <summary>
        /// Número del cliente: ¿Es como el Id?             >> CORREGIDO!!
        /// ¿Cliente tiene Id?
        /// ¿Es autoincremental?
        /// </summary>
        public virtual int NumeroCliente
        {
            get { return numeroCliente; }
            set { numeroCliente = value; }
        }

        /// <summary>
        /// Fecha en la que se dio de baja al cliente.
        /// </summary>
        public virtual DateTime? FechaBaja
        {
            get { return fechaBaja; }
            set { fechaBaja = value; }
        }

        /// <summary>
        /// usuario del cliente
        /// </summary>
        public virtual Entity.Persistente.Usuario Usuario
        {
            get { return usuario; }
            set { usuario = value; }
        }
        #endregion
    }
}
