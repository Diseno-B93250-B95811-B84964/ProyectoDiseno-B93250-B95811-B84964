/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Jimena Gdur Vargas
 */
public abstract class PieceModel {
    protected int x;
    protected int y;
    
    public PieceModel() {
        x = 0;
        y = 0;
        pieceColor = Color.WHITE;
    }
    
    public PieceModel(Color pieceColor) {
        x = 0;
        y = 0;
        this.pieceColor = pieceColor;
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
}
