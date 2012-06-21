/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Exceptiones;

/**
 *
 * @author Ivan
 */
public class TipoDatoFormatNumberException extends NegocioException {

    private int maxDigitos=0,maxDigitosDecimales=0;

    public TipoDatoFormatNumberException(String message,int enteros,int decimales) {
        super(message);
        this.maxDigitos=enteros;
        this.maxDigitosDecimales=decimales;
    }

    public int getMaxDigitosDecimales() {
        return maxDigitosDecimales;
    }

    public void setMaxDigitosDecimales(int maxDigitosDecimales) {
        this.maxDigitosDecimales = maxDigitosDecimales;
    }

    public int getMaxDigitos() {
        return maxDigitos;
    }

    public void setMaxDigitos(int maxDigitosEntoros) {
        this.maxDigitos = maxDigitosEntoros;
    }


}
