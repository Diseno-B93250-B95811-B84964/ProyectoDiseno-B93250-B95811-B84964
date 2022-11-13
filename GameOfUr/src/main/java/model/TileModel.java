/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mauup
 */
public class TileModel {
    private int row;
    private int column;
    private boolean isSafe;
    private boolean isVacant;
    private PieceModel piece;
    
    public TileModel(){
        row = -1;
        column = -1;
        isSafe = false;
        isVacant = false;
        piece = null;
    }
    
    public void setPiece(PieceModel newPiece){
        piece = newPiece;
    }
    
    public PieceModel getPiece(){
        return piece;
    }
    
    public boolean isSafe(){
        return isSafe;
    }
    
    public boolean isVacant(){
        return isVacant;
    }
    
    public void toggleSafeTile(){
        this.isSafe = !isSafe;
    }
    
    public void toggleVacancy(){
        this.isVacant = !isVacant;
    }
}
