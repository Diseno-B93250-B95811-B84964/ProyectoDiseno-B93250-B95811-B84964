/*
 * Issue #25 - Graph logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */

package model;

import java.awt.Color;

/**
 * Made for the Royal Game of Ur.
 * @author Jimena Gdur.
 */
public class UrPiece extends Piece {
    /**
    * Determines whether piece is currently in play.
    */
    private boolean isInPlay;
    
    /**
     * Creates a new Piece.
    */
    public UrPiece() {
        super();
        this.isInPlay = false;
    }
    /**
     * Creates a new Piece for a player.
     * @param selectedColor Player's selected color.
    */
    public UrPiece(Color selectedColor) {
        super(selectedColor);
        this.isInPlay = false;
    }
    /**
     * Determines if the piece is currently in play.
     * @return value of isInPlay.
    */
    public boolean isInPlay() {
        return this.isInPlay;
    }
    /**
     * Indicates piece is currently in play.
    */
    public void setInPlay() {
        this.isInPlay = true;
    }
    /**
     * Indicates piece is currently not in play.
    */
    public void setNotInPlay() {
        this.isInPlay = false;
    }
    
    public void setIsInPlay(boolean isInPlay){
        this.isInPlay = isInPlay;
    }
    /**
     * Converts Piece into a string.
     * Made for the Royal Game of Ur.
     * @return a string representing a piece
     */
    @Override
    public String toString() {
        String string =
            super.toString() + ", " + 
            "isInPlay: " + this.isInPlay;
        return string;
    }
}
