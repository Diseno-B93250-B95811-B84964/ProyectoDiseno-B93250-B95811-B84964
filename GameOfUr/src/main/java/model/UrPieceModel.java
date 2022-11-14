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
 * @author Jimena Gdur
 */
public class UrPieceModel extends PieceModel {

    protected boolean isSafe;
    
    public UrPieceModel() {
        super();
        isSafe = false;
    }
    
    public UrPieceModel(Color playerColor) {
        super(playerColor);
        isSafe = false;
    }
    
    /**
     * Gets variable value isSafe
     * @return Returns a true or false value.
     */
    public boolean isSafe() {
        return isSafe;
    }
    
    /**
     * Sets variable isSafe as true.
     */
    public void setSafe() {
        isSafe = true;
    }
}
