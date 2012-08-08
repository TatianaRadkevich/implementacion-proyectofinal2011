/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Rodrigo
 */
@Embeddable
public class PlanProduccionId implements java.io.Serializable{

        private int version;
        private int idPedido;

        public PlanProduccionId() {
        }

        public PlanProduccionId(int version, int idPedido) {
            this.version = version;
            this.idPedido = idPedido;
        }

        @Column(name = "VERSION", nullable = false)
        public int getVersion() {
            return this.version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        @Column(name = "ID_PEDIDO", nullable = false, precision = 8, scale = 0)
        public int getIdPedido() {
            return this.idPedido;
        }

        public void setIdPedido(int idPedido) {
            this.idPedido = idPedido;
        }

        public boolean equals(Object other) {
            if ((this == other)) {
                return true;
            }
            if ((other == null)) {
                return false;
            }
            if (!(other instanceof PlanProduccionId)) {
                return false;
            }
            PlanProduccionId castOther = (PlanProduccionId) other;

            return (this.getVersion() == castOther.getVersion())
                    && (this.getIdPedido() == castOther.getIdPedido());
        }

        public int hashCode() {
            int result = 17;

            result = 37 * result + this.getVersion();
            result = 37 * result + this.getIdPedido();
            return result;
        }
    }