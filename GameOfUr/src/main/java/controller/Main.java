/*
 * Game of Ur based on MARDA framework.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package controller;

import java.awt.Color;
import javax.swing.SwingUtilities;
import model.Board;

import model.Piece;
import model.Tile;
import model.UrPiece;
import model.UrTile;

/**
* Main class that starts program.
* @author Mauricio Palma, Alvaro Miranda, Jimena Gdur
*/
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Doesn't test other methods that can't be accesed through parent
            System.out.println("Testing classes");
            
            //Tile tile = new UrTile(2,3, false);
            //Piece piece = new UrPiece(Color.RED);
            //System.out.println("tile before: " + tile.toString());
            //tile.setPiece(piece);
            // como le digo al piece que esta en play?
            //System.out.println("tile after: " + tile.toString());
            //System.out.println("piece: " + piece.toString());
            
            System.out.println("board");
            Board board = new Board(UrTile::new, 6);
            // set tile x, y
            // set tile safe
            System.out.println(board);
            
            //System.out.println("board tiles: memory adresses");
            //for (int index = 0; index < 6; index++) {
            //    System.out.println("tile " + index + ": " + board.getTile(index).hashCode());
            //}

        });
    }
}
