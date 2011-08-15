/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion;

import java.util.Date;

/**
 *
 * @author Rodrigo
 */
public class Utilidades {

    public static String parseDate(Date fecha)
    {
        return String.format("%s/%s/%s", fecha.getDate(),fecha.getMonth(),fecha.getYear());
    }

}
