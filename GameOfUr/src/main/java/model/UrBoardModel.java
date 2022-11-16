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
    private UrTileModel[][] urBoard;
    //private PlayerModel playerOne;
    //private PlayerModel playerTwo;
    private Color playerOneColor;
    private Color playerTwoColor;
    private final static int ROWS = 8;
    private final static int COLUMNS = 3;
    private final static int NON_OCCUPIED = 0;
    private final static int OCCUPIED_P1 = 1;
    private final static int OCCUPIED_P2 = 2;
    public int playerTurn;

    protected HashMap<Color, ArrayList<UrTileModel>> playerPaths;
    
    public UrBoardModel(Color playerOneColor, Color playerTwoColor){
        //this.playerOne = new UrPlayerModel(1, playerOneColor);
        //this.playerTwo = new UrPlayerModel(2, playerTwoColor);
        this.playerOneColor = playerOneColor;
        this.playerTwoColor = playerTwoColor;
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
        
        playerPaths = new HashMap<Color, ArrayList<UrTileModel>>(2);
        playerPaths.put(playerOneColor, setPlayerPath(0)); //check for possible change to not use magic variables
        playerPaths.put(playerTwoColor, setPlayerPath(2)); //check for possible change to not use magic variables
    }
    
    private ArrayList<UrTileModel> setPlayerPath(int column) {
        ArrayList<UrTileModel> possiblePath =  new ArrayList<>();
        int middleColumn = 1;
        
        possiblePath.add(urBoard[3][column]);
        possiblePath.add(urBoard[2][column]);
        possiblePath.add(urBoard[1][column]);
        possiblePath.add(urBoard[0][column]);
        
        for(int playerRow = 0; playerRow < ROWS; playerRow++){
            possiblePath.add(urBoard[playerRow][middleColumn]);
        }
        
        possiblePath.add(urBoard[7][column]);
        possiblePath.add(urBoard[6][column]);
        possiblePath.add(urBoard[5][column]);
        
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

    public UrTileModel getTile(int x, int y) {
        return urBoard[x][y];
    }
}