/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Exceptiones;

/**
 *
 * @author Rodrigo
 */
public class TipoDatoMaxLengthException extends TipoDatoException {

    private int MaxLength;

    public TipoDatoMaxLengthException(String mensaje,int maxLen )
    {
        super(mensaje);
        this.MaxLength=maxLen;
    }

    public int getMaxLength() {
        return MaxLength;
    }

    public void setMaxLength(int MaxLength) {
        this.MaxLength = MaxLength;
    }

}
