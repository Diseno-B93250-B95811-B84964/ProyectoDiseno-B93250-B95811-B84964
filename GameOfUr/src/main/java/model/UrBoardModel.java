/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;
import java.util.*;

/**
 *
 * @author Usuario1
 */
public class UrBoardModel {
    private UrTileModel[][] urBoard;
    private Color playerOneColor;
    private Color playerTwoColor;
    private final static int ROWS = 8;
    private final static int COLUMNS = 3;
    private final static int NON_OCCUPIED = 0;
    private final static int OCCUPIED_P1 = 1;
    private final static int OCCUPIED_P2 = 2;
    
    protected HashMap<Color, ArrayList<UrTileModel>> playerPaths;
    
    public UrBoardModel(Color playerOneColor){
        for(int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLUMNS; col++){
                urBoard[row][col] = new UrTileModel(row,col); 
            }
        }
        urBoard[0][0].isSafe();
        urBoard[0][2].isSafe();
        urBoard[3][1].isSafe();
        urBoard[6][0].isSafe();
        urBoard[6][2].isSafe();
        
        this.playerOneColor = playerOneColor;
        playerPaths = new HashMap<Color, ArrayList<UrTileModel>>(2);
        playerPaths.put(playerOneColor, setPlayerPath(0)); //check for possible change to not use magic variables
        playerPaths.put(playerTwoColor, setPlayerPath(2)); //check for possible change to not use magic variables
    }
    
    public ArrayList<Integer> indicateGameState() {
        ArrayList<Integer> gameState = new ArrayList<>();
        for(int rows = 0; rows < ROWS; rows++){
            for(int cols = 0; cols < COLUMNS; cols++){
                if( !urBoard[rows][cols].isVacant()){
                    if(playerOneColor == urBoard[rows][cols].getPiece().getColor()){
                        gameState.add(OCCUPIED_P1);
                    }else{
                        gameState.add(OCCUPIED_P2);
                    }
                }else{
                    gameState.add(NON_OCCUPIED);
                }
            }
        }
        return gameState;
    }
    
    public ArrayList<UrTileModel> setPlayerPath(int column) {
        ArrayList<UrTileModel> possiblePath =  new ArrayList<>();
        int middleColumn = 1;
        
        possiblePath.add(new UrTileModel(3, column));
        possiblePath.add(new UrTileModel(2, column));
        possiblePath.add(new UrTileModel(1, column));
        possiblePath.add(new UrTileModel(0, column));
        
        for(int playerRow = 0; playerRow < ROWS; playerRow++){
            possiblePath.add(new UrTileModel(playerRow, middleColumn));
        }
        
        possiblePath.add(new UrTileModel(7, column));
        possiblePath.add(new UrTileModel(6, column));
        possiblePath.add(new UrTileModel(5, column));
        
        return possiblePath;
    }
    
    public UrTileModel getPossibleTile(UrTileModel currentTile, int amountOfMoves, Color playerColor) {
        int tileLocation = 0;
        int possibleMoveIndex = 0;
        UrTileModel possibleTile = null;
        if(canMove(currentTile, amountOfMoves, playerColor)){
            tileLocation = calculateTileLocation(currentTile, playerColor);
            possibleMoveIndex = tileLocation + amountOfMoves;
            if (tileLocation < 10 || winCondition(possibleMoveIndex)) {
                possibleTile = playerPaths.get(playerColor).get(possibleMoveIndex); 
            }
        }
        return possibleTile;
    }
    
    public boolean canMove(UrTileModel tile, int amountOfMoves, Color playerColor){
        return amountOfMoves != 0 && (tile.isVacant() || 
            (tile.getPiece().getColor() != playerColor && !tile.isSafe()));
    }
    
    public int calculateTileLocation(UrTileModel currentTile, Color playerColor){
        int tileLocation = 0;
        for(int playerPossibleTileIndex = 0; playerPossibleTileIndex < playerPaths.get(playerColor).size(); playerPossibleTileIndex++) {
            if (playerPaths.get(playerColor).get(playerPossibleTileIndex).getRow() == currentTile.getRow() && 
                playerPaths.get(playerColor).get(playerPossibleTileIndex).getColumn() == currentTile.getColumn()) {
                tileLocation = playerPossibleTileIndex;
            }
        }
        return tileLocation;
    }
    
    public boolean winCondition(int possibleMoveIndex){
        boolean canWin = false;
        if (possibleMoveIndex < playerPaths.size()) {
            if(possibleMoveIndex == 14){
                canWin = true;
            }
        }
        return canWin;
    }
}