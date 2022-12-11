/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author Mauricio Palma
 */
public class main {

    
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
