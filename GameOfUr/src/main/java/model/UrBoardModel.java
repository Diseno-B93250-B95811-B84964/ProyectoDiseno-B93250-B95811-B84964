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
    
        
    public UrBoardModel(){
        urBoard = new UrTileModel[ROWS][COLUMNS];
        for(int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLUMNS; col++){
                urBoard[row][col] = new UrTileModel(row,col); 
            }
        }
        urBoard[0][0].setSafe();
        urBoard[0][2].setSafe();
        urBoard[3][1].setSafe();
        urBoard[6][0].setSafe();
        urBoard[6][2].setSafe();
    }
    
    public void createPlayerPaths(Color player1, Color player2) {
        playerPaths = new HashMap<>(2);
        playerPaths.put(player1, setPlayerPath(0));
        playerPaths.put(player2, setPlayerPath(2));
    }
    
    public UrBoardModel(Color playerOneColor, Color playerTwoColor){
        urBoard = new UrTileModel[ROWS][COLUMNS];
        for(int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLUMNS; col++){
                urBoard[row][col] = new UrTileModel(row,col); 
            }
        }
        urBoard[0][0].setSafe();
        urBoard[0][2].setSafe();
        urBoard[3][1].setSafe();
        urBoard[6][0].setSafe();
        urBoard[6][2].setSafe();
        
        playerPaths = new HashMap<>(2);
        playerPaths.put(playerOneColor, setPlayerPath(0)); //check for possible change to not use magic variables
        playerPaths.put(playerTwoColor, setPlayerPath(2)); //check for possible change to not use magic variables
    }
    
    private ArrayList<UrTileModel> setPlayerPath(int playerColumn) {
        ArrayList<UrTileModel> possiblePath =  new ArrayList<>();
        int middleColumn = 1;
        
        for(int playerRow = 4; playerRow >= 0; playerRow--){
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
    
    public void setPiece(int x, int y, UrPieceModel piece) {
        urBoard[x][y].setPiece(piece);
    }
    
    public UrTileModel getTile(int x, int y) {
        return urBoard[x][y];
    }
    
    public UrTileModel getPossibleTile(UrTileModel currentTile, int amountOfMoves, Color playerColor) {
        ArrayList<UrTileModel> playerPath = playerPaths.get(playerColor);   
        int possibleMoveIndex = 0;
        UrTileModel possibleTile = null;      
        int tileLocation = calculateTileLocation(currentTile, playerColor);  
        if (tileLocation != -1 && amountOfMoves != 0) {
            possibleMoveIndex = tileLocation + amountOfMoves;
            if(canMove(playerPath.get(possibleMoveIndex), playerColor)){
                if (tileLocation < 10 || canScore(possibleMoveIndex, playerColor)) {
                    possibleTile = playerPaths.get(playerColor).get(possibleMoveIndex);
                }
            }
        }
        return possibleTile;
    }

    private boolean canMove(UrTileModel possibleTile, Color playerColor){
        UrPieceModel pieceInTile = possibleTile.getPiece();
        
        return (possibleTile.isVacant() ||
            ((pieceInTile.getColor().getRGB() != playerColor.getRGB()) && !possibleTile.isSafe()));
    }

    private int calculateTileLocation(UrTileModel currentPiece, Color playerColor){
        int tileLocation = -1;
        int pieceX = currentPiece.getRow();
        int pieceY = currentPiece.getColumn();
        
        ArrayList<UrTileModel> curentPlayerPath = playerPaths.get(playerColor);
        int tileX = 0;
        int tileY = 0;
        
        for(int playerPossibleTileIndex = 0; playerPossibleTileIndex < curentPlayerPath.size(); playerPossibleTileIndex++) {
            tileX = curentPlayerPath.get(playerPossibleTileIndex).getRow();
            tileY = curentPlayerPath.get(playerPossibleTileIndex).getColumn();
            if (tileX == pieceX && tileY == pieceY) {
                tileLocation = playerPossibleTileIndex;
            }
        }
        return tileLocation;
    }

    private boolean canScore(int possibleMoveIndex, Color playerColor){
        boolean canScore = false;
        if (possibleMoveIndex < playerPaths.get(playerColor).size()) {
            if(possibleMoveIndex == 14){
                canScore = true;
            }
        }
        return canScore;
    }
    
    // Sets piece in tile and checks if tile is safe to reset old tile
    public boolean setPieceTile(UrPieceModel piece, UrTileModel possibleTile) {
        boolean eaten = false;
        // checks if possible tile has a piece inside
        if (possibleTile.getPiece() != null) {
            // resets other player's piece to original position
            possibleTile.getPiece().resetPieceToOriginalPosition();
            eaten = true;
        }
        possibleTile.setPiece(piece);
        return eaten;
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