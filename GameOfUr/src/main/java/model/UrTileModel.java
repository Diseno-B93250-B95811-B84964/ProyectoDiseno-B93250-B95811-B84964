/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mauricio Palma
 */
public class UrTileModel extends TileModel{
    private boolean isSafe;
    private UrPieceModel possiblePiece;
    
    
    public UrTileModel(){
        super();
        isSafe = false;
        possiblePiece = null;
    }
    
    public UrTileModel(int row, int column){
        super(row, column);
        isSafe = false;
    }
    
    public UrTileModel(int row, int column, UrPieceModel possiblePiece){
        super(row, column);
        isSafe = false;
        this.possiblePiece = possiblePiece;
    }
    
    public boolean isSafe(){
        return isSafe;
    }
    
    public void toggleSafeTile(){
        this.isSafe = !isSafe;
    }
    
    public void setPiece(UrPieceModel piece){
        this.possiblePiece = piece;
    }
    
    public UrPieceModel getPiece(){
        return this.possiblePiece;
    }
    
    public void setSafePiece(int x, int y, UrPieceModel piece){
        if(this.isSafe()){
            piece.setSafe();
        }
        piece.setX(x);
        piece.setY(y);
    }
}
