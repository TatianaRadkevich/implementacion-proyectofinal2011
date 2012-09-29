using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Entity.Persistente
{
    public class Usuario
    {
        #region atributos
        private int id;        
        private string nombreDeUsuario;
        private string password;
        //private Entity.ReadOnly.Cliente cliente;
        #endregion

        #region propiedades
        /// <summary>
        /// Id del usuario.
        /// </summary>
        public virtual int Id
        {
            get { return id; }
            set { id = value; }
        }

        /// <summary>
        /// Nombre de usuario.
        /// </summary>
        public virtual string NombreDeUsuario
        {
            get { return nombreDeUsuario; }
            set { nombreDeUsuario = value; }
        }  

        /// <summary>
        /// Contraseña encriptada.
        /// NO USAR NUNCA ESTA PROPERTY.
        /// Usar los metodos SetPassword y IsPassword.
        /// </summary>
        public virtual string Password
        {
            get { return password; }
            set { password = value; }
        }        
        #endregion

        #region métodos

        /// <summary>
        /// Método para encriptar un string. En este caso se usa para el Pass.
        /// </summary>
        /// <param name="password">Contraseña a encriptar</param>
        /// <returns>Cadena encriptada</returns>
        private static string encrypt(string password)
        {
            System.Security.Cryptography.HashAlgorithm hashValue = new System.Security.Cryptography.SHA1CryptoServiceProvider();
            byte[] bytes = System.Text.Encoding.UTF8.GetBytes(password);
            byte[] byteHash = hashValue.ComputeHash(bytes);

            hashValue.Clear();

            return (Convert.ToBase64String(byteHash));
        }

        /// <summary>
        /// Setea la password del usuario encriptandolo previamente.
        /// </summary>
        /// <param name="password">Contraseña SIN encriptar</param>
        public virtual void SetPassword(string password)
        {
            this.password = Usuario.encrypt(password);
        }

        /// <summary>
        /// Pregunta si la contraseña sin encriptar es igual a la contraseña encriptada del usuario.
        /// </summary>
        /// <param name="password">Contraseña SIN encriptar</param>
        /// <returns>true: son iguales; false: son diferentes</returns>
        public virtual bool IsPassword(string password)
        {
            string passEncriptado = Usuario.encrypt(password);
            if (this.password == passEncriptado)
            {
                return true;
            }

            return false;
        }
        #endregion
    }
}
