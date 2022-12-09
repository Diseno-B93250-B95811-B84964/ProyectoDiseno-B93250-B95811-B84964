/*
 * Issue #26 - Game Logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package model;

import java.awt.Color;
import java.util.function.Supplier;

/**
 * Creates a game board using a graph to store the information.
 * @author Jimena Gdur.
 */
public class UrPlayer <PieceType extends Piece> extends Player {
    /*
    /**
     * Creates a new Player with their color and name.
     * @param supplier Supplier class that contains instance of piece's child.
     * @param amountPieces Amount of pieces the player has.
     * @param chosenColor Color player chose on respective screen.
     * @param name Player's name.
    *//*
    public UrPlayer(Supplier<UrPiece> supplier, int amountPieces, Color chosenColor, String name) {
        super(supplier, amountPieces, chosenColor, name);
    }*/
    
    /*
    /**
     * Creates a new Player with an array of pieces.
     * @param supplier Supplier class that contains instance of piece's child.
     * @param amountPieces Amount of pieces the player has.
     *//*
    public UrPlayer(Supplier<UrPiece> supplier, int amountPieces) {
        super(supplier, amountPieces);
    }*/
    

    
    public UrPlayer(int amountPieces) {
        super(amountPieces, Color.WHITE, "", null);
    }
    
    public UrPlayer() {
        super(2, Color.WHITE, "", null);
        System.out.println("Empty UrPlayer controller greets u");
    }
    
    public UrPlayer(int amountPieces, Color color, String name) {
        super(amountPieces, color, name);
    }
    
    public UrPlayer(int amountPieces, Color color, String name, PieceType pieceType) {
        super(amountPieces, color, name, pieceType);
    }
    
    public UrPlayer(int amountPieces, Color color, String name, UrPiece pieceType) {
        super(amountPieces, color, name, pieceType);
    }
    
    /**
     * Modifies player's score.
     * Made for the Royal Game of Ur.
     */
    @Override
    public void modifyScore() {
        score++;
    }
    /**
     * Consults player for a piece.
     * Made for the Royal Game of Ur.
     * @return An available piece 
     */
    @Override
    public UrPiece getAvailablePiece() {
        UrPiece availablePiece = null;
        // TODO: research how to fix this issue
        for(var piece : pieces) {
            //if (piece.isInstanceOf(Piece));
            availablePiece = (UrPiece)piece;
            if (!availablePiece.isInPlay()) {
                break;
            } else {
                availablePiece = null;
            }
        }
        return availablePiece;
    }
}
