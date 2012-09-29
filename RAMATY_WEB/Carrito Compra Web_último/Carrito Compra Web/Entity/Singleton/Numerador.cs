using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Singleton
{
    public class Numerador
    {
        private static Numerador numerador;

        public static Numerador Instance
        {
            get { return getInstance(); }
        }

        private static Numerador getInstance()
        {
            if (numerador == null)
                numerador = new Numerador();
            return numerador;
        }

        private Int64 numero;
        public Int64 Numero
        {
            get { return obtenerNumero(); }
            set { numero = value; }
        }

        private long obtenerNumero()
        {
            this.numero++;
            return this.numero;
        }
    }
}
