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

    private int x;

    private int y;
    
    private int initialRow;
    
    private final int initialCol;

   
    protected Color pieceColor;


    protected boolean isSafe;

    protected boolean isInPlay;
    

    public UrPieceModel(int column) {
        initialRow = 4;
        initialCol = column;
        
        x = initialRow;
        y = initialCol;
        pieceColor = Color.WHITE;
        isSafe = false;
        isInPlay = false;
    }
    
    public UrPieceModel(Color playerColor, int column) {
        initialRow = 4;
        initialCol = column;
       
        x = initialRow;
        y = initialCol;
        pieceColor = playerColor;
        isSafe = false;
        isInPlay = false;
    }
    
    public void setX(int xValue) {
        x = xValue;
    }
    

    public void setY(int yValue) {
        y = yValue;
    }
    

    public int getX() {
        return x;
    }
    
  
    public int getY() {
        return y;
    }
    

    public void setColor(Color pieceColor){
        this.pieceColor = pieceColor;
    }
    
   
    public Color getColor(){
        return pieceColor;
    }
    
    public boolean isInPlay(){
        return isInPlay;
    }
    
    public void setIsInPlay(boolean isInPlay){
        this.isInPlay = isInPlay;
    }
    
    public void resetPieceToOriginalPosition() {
        x = initialRow;
        y = initialCol;
    }
}
