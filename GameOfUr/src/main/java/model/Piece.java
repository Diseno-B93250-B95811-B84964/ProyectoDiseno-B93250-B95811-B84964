/*
 * Issue #25 - Game Logic.
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
    protected Color pieceColor;
    
    /**
     * Creates a new Piece for a player.
     * @param selectedColor Player's selected color.
    */
    public Piece(Color selectedColor) {
        this.pieceColor = selectedColor;
    }
    /**
     * Gets piece's color.
     * @return the color of the current piece.
    */
    public Color getColor() {
        return this.pieceColor;
    }
}
