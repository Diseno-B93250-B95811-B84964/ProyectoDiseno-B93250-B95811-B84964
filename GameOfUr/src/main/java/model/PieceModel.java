/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;

/**
 *
 * @author Jimena Gdur Vargas
 */
public class PieceModel {
    protected int x;
    protected int y;
    protected Color pieceColor;
    
    public PieceModel() {
        x = 0;
        y = 0;
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
}
