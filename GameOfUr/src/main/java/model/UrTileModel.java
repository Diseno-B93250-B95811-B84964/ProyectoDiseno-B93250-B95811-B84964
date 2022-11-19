/*
 * User Story # 
 * Jimena Gdur Vargas B93250
 * Álvaro Miranda Villegas B84964
 * Ronald Palma Villegas B95811
 */

package model;

/**
 *
 * @author Alvaro Miranda
 */
public class UrTileModel {
    private int row;
    private int column;
    
    private boolean isVacant;
    private boolean isSafe;
    private UrPieceModel piece;
    
    public UrTileModel(){
        this.row = -1;
        this.column = -1;
        this.isVacant = true;
        this.isSafe = false;
        this.piece = null;
        
    }
    
    public UrTileModel(int row, int column){
        this.row = row;
        this.column = column;
        this.isVacant = true;
        this.isSafe = false;
        this.piece = null;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }
    
    public boolean isVacant(){
        return isVacant;
    }
    
    public void toggleVacancy(){
        this.isVacant = !isVacant;
    }
    
    public UrTileModel(int row, int column, UrPieceModel piece){
        isSafe = false;
        this.piece = piece;
    }
    
    public boolean isSafe(){
        return isSafe;
    }
    
    public void setSafe(){
        this.isSafe = true;
    }
    
    public void setPiece(UrPieceModel piece){
        this.piece = piece;
        isVacant = false;
        piece.setX(row);
        piece.setY(column);
        
        //System.out.println("piece in tile: " + piece.getColor());
    }
    
    public UrPieceModel getPiece(){
        return this.piece;
    }
    
    public void resetTile(){
        this.piece = null;
        toggleVacancy();
    }
}
