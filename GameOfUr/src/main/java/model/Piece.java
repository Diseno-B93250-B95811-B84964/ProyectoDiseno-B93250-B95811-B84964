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
    
    /**
     * Creates a new Piece.
    */
    public Piece() {
        this.color = Color.WHITE;
    }
    /**
     * Creates a new Piece for a player.
     * @param selectedColor Player's selected color.
    */
    public Piece(Color selectedColor) {
        this.color = selectedColor;

    }
    /**
     * Gets piece's color.
     * @return the color of the current piece.
    */
    public Color getColor() {
        return this.color;
    }
    /**
     * Sets piece's color using player's color.
     * @param playerColor Player's color.
    */
    public void setColor(Color playerColor) {
        this.color = playerColor;
    }
    /**
     * Converts Piece into a string.
     * @return a string representing a piece
     */
    @Override
    public String toString() {
        String string =
            "pieceColor: " + this.color.getRGB();
        return string;
    }
}
