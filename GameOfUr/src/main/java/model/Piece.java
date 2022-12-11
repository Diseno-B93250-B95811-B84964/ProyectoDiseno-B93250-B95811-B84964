/*
 * Issue #25 - Graph logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */

package model;

import java.awt.Color;

/**
 * Represents a game piece that can be placed in a tile and belongs to a player.
 * @author Jimena Gdur.
 */
abstract public class Piece extends GameObject
{
    /**
    * Allows the referee to determine to which player this piece belongs to.
    */
    protected Color color;
    
    public int pieceIndex; // TODO delete this
    
    /**
     * Creates a new Piece.
    */
    public Piece(int index) {
        this.color = Color.WHITE;
        pieceIndex = index;
    }
    /**
     * Creates a new Piece for a player.
     * @param selectedColor Player's selected color.
    */
    public Piece(Color selectedColor, int index) {
        this.color = selectedColor;
        pieceIndex = index;

    }
    /**
     * Sets piece's color using player's color.
     * @param playerColor Player's color.
    */
    public void setColor(Color playerColor) {
        this.color = playerColor;
    }
    /**
     * Gets piece's color.
     * @return The color of the current piece.
    */
    public Color getColor() {
        return this.color;
    }
    /**
     * Converts Piece into a string.
     * @return A string representing a piece
     */
    @Override
    public String toString() {
        String string =
            "pieceColor: " + this.color;
        return string;
    }
}
