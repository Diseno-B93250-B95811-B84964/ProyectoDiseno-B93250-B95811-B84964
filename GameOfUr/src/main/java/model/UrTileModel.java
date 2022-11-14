/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mauup
 */
public class UrTileModel extends TileModel{
    private boolean isSafe;
    
    
    public UrTileModel(){
        super();
        isSafe = false;
    }
    
    public UrTileModel(int row, int column){
        super(row, column);
        isSafe = false;
    }
    
    public boolean isSafe(){
        return isSafe;
    }
    
    public void toggleSafeTile(){
        this.isSafe = !isSafe;
    }
}
