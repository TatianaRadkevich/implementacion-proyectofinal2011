package Presentacion;


import java.awt.*;


/**  
 * Clase genérica para iniciar parámetros de ventanas.
 *
 * @author Christian G. Adam
 * @version Marzo de 2008
 */

public class IniciadorDeVentanas
{
    /**
     * Método estático para setear parámetros de las ventanas y ubicarlas al centro de
     * la pantalla.
     *
     * @param ventana ventana a setear
     * @param anchoComponente ancho de la ventana
     * @param altoComponente alto de la ventana
     */
    public static void iniciarVentana(Window ventana, int anchoComponente , int altoComponente)
    {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit. getScreenSize();
        int alto = dim.height;
        int ancho = dim.width;
        ventana.setSize(anchoComponente, altoComponente);
        
        ventana.setLocation((ancho/2 - anchoComponente/2),(alto/2 - altoComponente/2));
        
//        ventana.setLocation((ancho/2-anchoComponente/2),(alto/2-altoComponente/2));
    }
}