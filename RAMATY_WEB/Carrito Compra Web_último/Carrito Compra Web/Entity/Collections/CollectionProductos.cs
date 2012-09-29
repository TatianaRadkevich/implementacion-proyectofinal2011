using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Entity.ReadOnly;
using System.Data.SqlClient;

namespace Entity.Collections
{
    class CollectionProductos
    {
        private List<EntityReadOnlyProducto> listaProductos;

        private CollectionProductos()
        {
        }

        private List<EntityReadOnlyProducto> getListaProductos()
        {
            listaProductos = new List<EntityReadOnlyProducto>();
            EntityReadOnlyProducto producto;
            SqlCommand cmd = new SqlCommand();                          //no
            SqlDataReader dr=cmd.ExecuteReader();                       //modificar
            while (dr.Read())
            {
                producto = new EntityReadOnlyProducto();
                //producto.Codigo=Convert.ToInt32(dr["Id"]);            //modificar
                //producto.Nombre = dr["Nombre"].ToString();            //modificar
                //producto.descripcion = dr["Descripcion"].ToString();  //modificar
                //producto.tipoProducto= dr                             //modificar
                //producto.detalleProducto=dr                           //modificar
                listaProductos.Add(producto);
            }
            dr.Close();
            return listaProductos;
        }
    }
}
