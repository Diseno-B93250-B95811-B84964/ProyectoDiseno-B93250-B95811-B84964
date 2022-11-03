/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Jimena Gdur Vargas
 */
public class Piece {
    protected int x;
    protected int y;
    
    public Piece() {
        x = 0; // TODO: change to position in board
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
}
