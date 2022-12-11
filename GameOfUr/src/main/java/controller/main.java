/*
 * MARDA implemmentation of Ur.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 * Executes GameController 
 * @author Mauricio Palma
 */
public class main
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            SwingUtilities.invokeAndWait(() -> {
                GameController gameController = new UrGameController();
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

}
