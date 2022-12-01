/*
 * Game of Ur based on MARDA framework.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package controller;

import javax.swing.SwingUtilities;
import model.Tile;
import model.UrTile;

    /**
    * Main class that starts program.
    * @author Mauricio Palma, Alvaro Miranda, Jimena Gdur
    */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            System.out.println("Testing classes");
            Tile tile1 = new UrTile(2,3, false);
            System.out.println("tile: " + tile1.toString());
        });
    }
}
