/*
 * Issue #26 - Game Logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package model;

import java.awt.Color;

/**
 * Creates a game board using a graph to store the information.
 * @author Jimena Gdur.
 * @param <PieceType> Piece's child class.
 */
public class UrPlayer <PieceType extends Piece> extends Player {
    /**
     * Creates a new Player with their color and name.
     * @param amountPieces Amount of pieces the player has.
     * @param color Player's color.
     * @param name Player's name.
     * @param pieceType The specific type of piece that will use player.
    */
    public UrPlayer(int amountPieces, Color color, String name, UrPiece pieceType) {
        super(amountPieces, color, name, pieceType);
    }
    
    public UrPlayer() {
        super();
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
        for(var piece : pieces) {
            availablePiece = (UrPiece)piece;
            if (!availablePiece.isInPlay()) {
                break;
            } 
        }
        return availablePiece;
    }

    @Override
    public void setColorPiece(Color color) {
        UrPiece tempPiece = null;
        for(var piece : pieces) {
            tempPiece = (UrPiece)piece;
            tempPiece.setColor(color);
        }
    }
}
