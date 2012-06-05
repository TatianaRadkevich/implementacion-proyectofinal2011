/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion.Compras;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author RUBEN
 */
public class FormatoTabla extends DefaultTableCellRenderer{
    @Override
    public Component getTableCellRendererComponent ( JTable table, Object value, boolean selected, boolean focused, int row, int column )
    {
        setEnabled(table == null || table.isEnabled());

        //setBackground(Color.cyan);//color de fondo
        //table.setForeground(Color.black);//color de texto


        //si la celda esta vacia se reemplaza por el texto "<vacio>" y se rellena la celda de color negro y fuente color blanco
        String var=String.valueOf(table.getValueAt(row,table.getColumnCount()-3));//stock actual
        String var1=String.valueOf(table.getValueAt(row,table.getColumnCount()-2));//stock minimo
        int num=Integer.parseInt((var));
        int num1=Integer.parseInt((var1));

        
        if(num <= num1){
            Color rojo = new Color(255,102,102);//rojo oscuro new Color(255,0,0); //rojo oscuro

            setBackground(rojo);
            //table.setForeground(Color.white);
        }
        else
        {
//            Color verde = new Color(204,255,204); // verde oscuro new Color(0,153,0);//verde oscuro
            setBackground(Color.WHITE);
        }
        

        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
    }

}
