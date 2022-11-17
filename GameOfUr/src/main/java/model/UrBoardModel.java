/*
 * User Story # 
 * Jimena Gdur Vargas B93250
 * Álvaro Miranda Villegas B84964
 * Ronald Palma Villegas B95811
 */

package model;

import java.awt.Color;
import java.util.*;

/**
 *
 * @author Usuario1
 */
public class UrBoardModel {
    private final UrTileModel[][] urBoard;
    private HashMap<Color, ArrayList<UrTileModel>> playerPaths;
    private Color playerTurn;
    
    public final static int ROWS = 8;
    public final static int COLUMNS = 3;
    
    public UrBoardModel(Color playerOneColor, Color playerTwoColor){
        urBoard = new UrTileModel[ROWS][COLUMNS];
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
        
        playerPaths = new HashMap<>(2);
        playerPaths.put(playerOneColor, setPlayerPath(0)); //check for possible change to not use magic variables
        playerPaths.put(playerTwoColor, setPlayerPath(2)); //check for possible change to not use magic variables
    }
    
    private ArrayList<UrTileModel> setPlayerPath(int playerColumn) {
        ArrayList<UrTileModel> possiblePath =  new ArrayList<>();
        int middleColumn = 1;
        
        for(int playerRow = 3; playerRow > 0; playerRow--){
            possiblePath.add(urBoard[playerRow][playerColumn]);
        }
        
        for(int playerRow = 0; playerRow < ROWS; playerRow++){
            possiblePath.add(urBoard[playerRow][middleColumn]);
        }
        
        for(int playerRow = 7; playerRow >= 5; playerRow--){
            possiblePath.add(urBoard[playerRow][playerColumn]);
        }
        
        return possiblePath;
    }
    
    public void setPiece(int x, int y, UrPieceModel tile) {
        urBoard[x][y].setPiece(tile);
    }
    
    public UrTileModel getTile(int x, int y) {
        return urBoard[x][y];
    }
    
    public UrTileModel getPossibleTile(UrTileModel currentTile, int amountOfMoves, Color playerColor) {
        int tileLocation = 0;
        int possibleMoveIndex = 0;
        UrTileModel possibleTile = null;
        if(canMove(currentTile, amountOfMoves, playerColor)){
            tileLocation = calculateTileLocation(currentTile, playerColor);
            possibleMoveIndex = tileLocation + amountOfMoves;
            if (tileLocation < 10 || winCondition(possibleMoveIndex, playerColor)) {
                possibleTile = playerPaths.get(playerColor).get(possibleMoveIndex); 
            }
        }
        return possibleTile;
    }
    
    private boolean canMove(UrTileModel tile, int amountOfMoves, Color playerColor){
        return amountOfMoves != 0 && (tile.isVacant() || 
            (tile.getPiece().getColor() != playerColor && !tile.isSafe()));
    }
    
    private int calculateTileLocation(UrTileModel currentTile, Color playerColor){
        int tileLocation = 0;
        for(int playerPossibleTileIndex = 0; playerPossibleTileIndex < playerPaths.get(playerColor).size(); playerPossibleTileIndex++) {
            if (playerPaths.get(playerColor).get(playerPossibleTileIndex).getRow() == currentTile.getRow() && 
                playerPaths.get(playerColor).get(playerPossibleTileIndex).getColumn() == currentTile.getColumn()) {
                tileLocation = playerPossibleTileIndex;
            }
        }
        return tileLocation;
    }
    
    private boolean winCondition(int possibleMoveIndex, Color playerColor){
        boolean canWin = false;
        if (possibleMoveIndex < playerPaths.get(playerColor).size()) {
            if(possibleMoveIndex == 14){
                canWin = true;
            }
        }
        return canWin;
    }
    
    public void addScoreToPlayer(UrPlayerModel player){
        player.addScoreToPlayer();
    }
    
    public Color getPlayerTurn() {
        return playerTurn;
    }
    
    public void setPlayerTurn(Color playerColor) {
        playerTurn = playerColor;
    }
}