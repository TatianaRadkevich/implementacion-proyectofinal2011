using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Entity.Persistente
{
    public class Sesion
    {
        #region atributos
        private int id;
        private DateTime? fechaHoraInicio;
        private DateTime? fechaHoraFin;
        private Entity.Persistente.Usuario usuario;
        #endregion

        #region propiedades
        /// <summary>
        /// Id de la sesión.
        /// </summary>
        public virtual int Id
        {
            get { return id; }
            set { id = value; }
        }

        /// <summary>
        /// Fecha y hora en la que la sesión se inicio.
        /// </summary>
        public virtual DateTime? FechaHoraInicio
        {
            get { return fechaHoraInicio; }
            set { fechaHoraInicio = value; }
        }

        /// <summary>
        /// Fecha y hora en la que la sesión finalizo.
        /// </summary>
        public virtual DateTime? FechaHoraFin
        {
            get { return fechaHoraFin; }
            set { fechaHoraFin = value; }
        }

        /// <summary>
        /// Usuario que hizo uso de la sesión.
        /// </summary>
        public virtual Entity.Persistente.Usuario Usuario
        {
            get { return usuario; }
            set { usuario = value; }
        }
        #endregion
    }
}
