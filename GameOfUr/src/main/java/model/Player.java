/*
 * Issue #25 - Game Logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * Creates a game board using a graph to store the information.
 * @author Jimena Gdur.
 * @param <PieceType> Piece's child class
 */
public class Player<PieceType extends Piece> extends GameObject {
    /**
     * Stores all of the player's pieces.
     */
    protected ArrayList<PieceType> pieces;
    /**
     * Player's chosen color.
     */
    protected Color playerColor;
    
    /**
     * Creates a new Player with an array of pieces.
     * @param supplier Supplier class that contains instance of piece's child
    */
    Player(Supplier<PieceType> supplier, int amountPieces, Color chosenColor) {
        pieces = new ArrayList<>(amountPieces);
        for (int pieceIndex = 0; pieceIndex < amountPieces; pieceIndex++) {
            pieces.set(pieceIndex, supplier.get());
        }
    }
    @Override
    public String toString() {
        String string = "";
        return string;
    }
}
