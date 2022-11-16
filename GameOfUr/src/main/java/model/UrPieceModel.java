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
public class UrPieceModel {

    /**
     * Coordinate x
     */
    protected int x;

    /**
     * Coordinate y
     */
    protected int y;

    /**
     * Piece Color
     */
    protected Color pieceColor;

    /**
     * Determines if piece is safe
     */
    protected boolean isSafe;
    
    /**
     * Basic Constructor
     */
    public UrPieceModel() {
        x = 0;
        y = 0;
        pieceColor = Color.WHITE;
        isSafe = false;
    }
    
    /**
     * Constructor with a Color
     * @param playerColor Value to be assigned
     */
    public UrPieceModel(Color playerColor) {
        x = 0;
        y = 0;
        pieceColor = playerColor;
        isSafe = false;
    }
    
    /**
     * Sets variable x with given parameter
     * @param xValue Value to be assigned
     */
    public void setX(int xValue) {
        x = xValue;
    }
    
    /**
     * Sets variable pieceColor with given parameter
     * @param yValue Value to be assigned
     */
    public void setY(int yValue) {
        y = yValue;
    }
    
    /**
     * Gets variable value x
     * @return Returns an int value.
     */
    public int getX() {
        return x;
    }
    
    /**
     * Gets variable value y
     * @return Returns an int value.
     */
    public int getY() {
        return y;
    }
    
    /**
     * Sets variable pieceColor with given parameter
     * @param pieceColor Value to be assigned
     */
    public void setColor(Color pieceColor){
        this.pieceColor = pieceColor;
    }
    
    /**
     * Gets variable value pieceColor
     * @return Returns a Color value.
     */
    public Color getColor(){
        return pieceColor;
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
