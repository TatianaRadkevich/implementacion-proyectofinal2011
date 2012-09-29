using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Carrito_Compra_Web.Models.Entities
{
    public class UnidadMedida
    {
        #region propiedades
        /// <summary>
        /// Id de la Unidad de Medida
        /// </summary>
        public int Id
        {
            get;
            set;
        }

        /// <summary>
        /// Nombre de la Unidad de Medida
        /// </summary>
        public string Nombre
        {
            get;
            set;
        }

        /// <summary>
        /// Descripción de la Unidad de Medida
        /// </summary>
        public string Descripcion
        {
            get;
            set;
        }
        #endregion
    }

    public class TipoProducto
    {
        #region propiedades
        public string Nombre
        {
            set;
            get;
        }

        public Int16 Id
        {
            get;
            set;
        }

        public string Descripcion
        {
            get;
            set;
        }
        #endregion        
    }

    public class DetalleProducto
    {
        #region propiedades
        /// <summary>
        /// Id.
        /// </summary>
        public Int64 Id
        {
            get;
            set;
        }

        /// <summary>
        /// Imágen del producto.
        /// Supongamos que el producto tiene 3 detalles.
        /// Ancho, Alto, Profundidad; 3 medidas diferentes, ¿esto supone 3 imágenes diferentes?   >>PENDIENTE DE RESPUESTA DE TANO
        /// </summary>
        public string ImagenProducto
        {
            get;
            set;
        }
               

        /// <summary>
        /// Valor de la medida
        /// </summary>
        public virtual int Medida
        {
            get;
            set;
        }
        #endregion
    }

    public class Producto
    {
        #region atributos
        /// <summary>
        /// Código único del producto.
        /// Representa el Id de producto.
        /// </summary>
        public int Codigo
        {
            get;
            set;
        }

        /// <summary>
        /// Nombre del producto.
        /// </summary>
        public string Nombre
        {
            get;
            set;
        }

        /// <summary>
        /// Descripción del producto.
        /// </summary>
        public string Descripcion
        {
            get;
            set;
        }

        /// <summary>
        /// Unidad de medida del producto.
        /// </summary>
        public Carrito_Compra_Web.Models.Entities.UnidadMedida UnidadDeMedida
        {
            get;
            set;
        }

        /// <summary>
        /// Tipo de producto.
        /// </summary>
        public Carrito_Compra_Web.Models.Entities.TipoProducto TipoProducto
        {
            get;
            set;
        }

        /// <summary>
        /// Detalles del producto.
        /// </summary>
        public List<Carrito_Compra_Web.Models.Entities.DetalleProducto> DetallesProducto
        {
            get;
            set;
        }

        public double PrecioUnitario
        {
            get;
            set;
        }

        public DateTime? FechaBaja
        {
            get;
            set;
        }
        #endregion
    }

    public class DetallePedido
    {
        #region atributos
        /// <summary>
        /// Id del detalle de pedido.
        /// </summary>
        public long Id
        {
            get;
            set;
        }

        /// <summary>
        /// Producto.
        /// </summary>
        public Carrito_Compra_Web.Models.Entities.Producto Producto
        {
            get;
            set;
        }

        /// <summary>
        /// Cantidad de productos.
        /// </summary>
        public int Cantidad
        {
            get;
            set;
        }

        /// <summary>
        /// Precio.
        /// </summary>
        public double Precio
        {
            get;
            set;
        }

        /// <summary>
        /// Estado del detalle del pedido.
        /// Esta propiedad p/ nosotros es SIEMPRE = 1.
        /// </summary>
        public Int16 EstadoDetallePedido
        {
            get;
            set;
        }

        /// <summary>
        /// Precio Total del pedido.
        /// </summary>
        public double Total
        {
            get;
            set;
        }

        #endregion
    }

    public class Usuario
    {
        #region propiedades
        /// <summary>
        /// Id del usuario.
        /// </summary>
        public int Id
        {
            get;
            set;
        }

        /// <summary>
        /// Nombre de usuario.
        /// </summary>
        public string NombreDeUsuario
        {
            get;
            set;
        }
        #endregion
    }

    public class Cliente
    {
        #region propiedades
        /// <summary>
        /// Nombres del cliente
        /// </summary>
        public string Nombre
        {
            get;
            set;
        }

        /// <summary>
        /// Apellido del cliente
        /// </summary>
        public string Apellido
        {
            get;
            set;
        }

        /// <summary>
        /// Numero de DNI/CUIT/LE/Etc...
        /// </summary>
        public string NumeroDocumento
        {
            get;
            set;
        }

        /// <summary>
        /// E-Mail del cliente.
        /// </summary>
        public string Email
        {
            get;
            set;
        }

        /// <summary>
        /// Número de teléfono del cliente
        /// </summary>
        public Int64 NumeroTelefono
        {
            get;
            set;
        }

        /// <summary>
        /// Número del cliente.
        /// </summary>
        public int NumeroCliente
        {
            get;
            set;
        }

        /// <summary>
        /// Fecha en la que se dio de baja al cliente.
        /// </summary>
        public DateTime? FechaBaja
        {
            get;
            set;
        }

        /// <summary>
        /// usuario del cliente
        /// </summary>
        public Carrito_Compra_Web.Models.Entities.Usuario Usuario
        {
            get;
            set;
        }
        #endregion
    }

    public class Pedido
    {
        #region propiedades
        public long Id
        {
            get;
            set;
        }

        /// <summary>
        /// Fecha en la que se estima que se puede llegar a entregar, propuesta por el vendedor.
        /// </summary>
        public DateTime? FechaEstimadaEntrega
        {
            get;
            set;
        }

        /// <summary>
        /// Fecha en la que se da de baja el pedido.
        /// Se guarda la fecha en la que el cliente cancela el pedido por algún motivo.
        /// </summary>
        public DateTime? FechaBaja
        {
            get;
            set;
        }

        /// <summary>
        /// Cliente que hace el pedido, se obtiene del Usuario.
        /// </summary>
        public Carrito_Compra_Web.Models.Entities.Cliente Cliente
        {
            get;
            set;
        }

        /// <summary>
        /// Estado del pedido.
        /// </summary>
        public string Estado
        {
            get;
            set;
        }

        /// <summary>
        /// Coleccion de detalles del pedido.
        /// </summary>
        public List<Carrito_Compra_Web.Models.Entities.DetallePedido> DetallesPedido
        {
            get;
            set;
        }

        /// <summary>
        /// Fecha real en la que se entregó el pedido.
        /// </summary>
        public DateTime? FechaHoraRealEntrega
        {
            get;
            set;
        }

        /// <summary>
        /// Fecha en la que se generó el pedido.
        /// ¿No es lo mismo que la fecha del pedido?
        /// </summary>
        public DateTime? FechaHoraGeneracion
        {
            get;
            set;
        }

        /// <summary>
        /// Fecha solicitada por el cliente.
        /// </summary>
        public DateTime? FechaSolicitada
        {
            get;
            set;
        }

        /// <summary>
        /// Prioridad del pedido.
        /// </summary>
        public int Prioridad
        {
            get;
            set;
        }

        /// <summary>
        /// Motivo por el cual canceló el pedido.
        /// </summary>
        public string MotivoBaja
        {
            get;
            set;
        }

        /// <summary>
        /// Fecha en la que el cliente recibió el pedido.
        /// </summary>
        public DateTime? FechaClienteRecibio
        {
            get;
            set;
        }

        /// <summary>
        /// Tipo de pedido.
        /// En nuestro caso es SIEMPRE = 1.
        /// MP del cliente
        /// </summary>
        public Int16 TipoPedido
        {
            get;
            set;
        }

        public string Total
        {
            get;
            set;
        }

        public string TotalString
        {
            get;
            set;
        }

        #endregion
    }

    public class Sesion
    {
        #region propiedades
        /// <summary>
        /// Id de la sesión.
        /// </summary>
        public int Id
        {
            get;
            set;
        }

        /// <summary>
        /// Fecha y hora en la que la sesión se inicio.
        /// </summary>
        public DateTime? FechaHoraInicio
        {
            get;
            set;
        }

        /// <summary>
        /// Fecha y hora en la que la sesión finalizo.
        /// </summary>
        public DateTime? FechaHoraFin
        {
            get;
            set;
        }

        /// <summary>
        /// Usuario que hizo uso de la sesión.
        /// </summary>
        public Carrito_Compra_Web.Models.Entities.Usuario Usuario
        {
            get;
            set;
        }
        #endregion
    }

    public class EstadoPedido
    {
        public int Id
        {
            get;
            set;
        }

        public string Nombre
        {
            get;
            set;
        }

        public string Descripcion
        {
            get;
            set;
        }
    }
}