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
    
    private boolean isVacant;
    private PieceModel piece;
    
    public TileModel(){
        row = -1;
        column = -1;
        isVacant = false;
        piece = null;
    }
    
    public TileModel(int row, int column){
        this.row = row;
        this.column = column;
        isVacant = false;
        piece = null;
    }
    
    public void setPiece(PieceModel newPiece){
        piece = newPiece;
    }
    
    public PieceModel getPiece(){
        return piece;
    }
    
    public boolean isVacant(){
        return isVacant;
    }
    
    
    public void toggleVacancy(){
        this.isVacant = !isVacant;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }
}