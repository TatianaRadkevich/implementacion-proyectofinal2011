/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import de.javasoft.plaf.synthetica.SyntheticaBlueIceLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaGreenDreamLookAndFeel;
import java.awt.Frame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *
 * @author RUBEN
 */
public class GUILocal {

    
    public static void establecerGUILocal(Object frame)
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Object.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Object.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Object.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Object.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void establecerGUISyntheticaClassy(Object frame){
      try
    {

      UIManager.setLookAndFeel(new SyntheticaClassyLookAndFeel());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }


    }


    public static void establecerGUISyntheticaGreenDreamLookAndFeel(Object frame){
      try
    {

      UIManager.setLookAndFeel(new SyntheticaGreenDreamLookAndFeel());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }


    }

    public static void SyntheticaBlueIceLookAndFeel(Object frame){
      try
    {

      UIManager.setLookAndFeel(new SyntheticaBlueIceLookAndFeel());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }


    }


    
    
    
    

}
