/*
 * User Story # 8
 * Jimena Gdur Vargas B93250
 * Álvaro Miranda Villegas B84964
 * Ronald Palma Villegas B95811
 */

package model;

import java.awt.Color;

/**
 *
 * @author Jimena Gdur Vargas
 */
public class UrPiece extends Piece {

    /**
     *
     */
    protected Color color;

    /**
     *
     */
    protected boolean isSafe;
    
    /**
     *
     */
    public UrPiece() {
        super();
        color = Color.RED;
        isSafe = false;
    }
    
    /**
     *
     * @param chosenColor
     */
    public void setColor(Color chosenColor) {
        color = chosenColor;
    }
    
    /**
     *
     * @return
     */
    public Color getColor() {
        return color;
    }
    
    /**
     *
     * @return
     */
    public boolean isSafe() {
        return isSafe;
    }
    
    /**
     *
     */
    public void setSafe() {
        isSafe = true;
    }
}
