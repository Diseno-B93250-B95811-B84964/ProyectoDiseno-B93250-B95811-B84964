/*
 * User Story # 
 * Jimena Gdur Vargas B93250
 * Álvaro Miranda Villegas B84964
 * Ronald Palma Villegas B95811
 */

package model;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Jimena Gdur
 */
public class UrSerializerConstructor {
    public UrBoardModel gameBoard; // make gets?
    public UrPlayerModel firstPlayer;
    public UrPlayerModel secondPlayer;
    
    private final static int NON_OCCUPIED = 0;
    private final static int OCCUPIED_P1 = 1;
    private final static int OCCUPIED_P2 = 2;
    
    public UrSerializerConstructor(UrBoardModel gameBoard, UrPlayerModel player1, UrPlayerModel player2) {
        this.gameBoard = gameBoard;
        this.firstPlayer = player1;
        this.secondPlayer = player2;
    }
    
    public String saveGameState() {
        String gameState = "";
        
        // Color playerColor, String playerName, int playerScore
        gameState += firstPlayer.getColor() + "," + firstPlayer.getPlayerName() + "," + firstPlayer.getPlayerScore() + "\n";
        gameState += secondPlayer.getColor() + "," + secondPlayer.getPlayerName() + "," + secondPlayer.getPlayerScore() + "\n";
        
        for(int row = 0; row < UrBoardModel.ROWS; row++){
            for(int col = 0; col < UrBoardModel.COLUMNS; col++){
                if( !gameBoard.getTile(row, col).isVacant()){
                    if(gameBoard.playerOneColor == gameBoard.getTile(row, col).getPiece().getColor()) {
                        gameState += OCCUPIED_P1;
                    } else {
                        gameState += OCCUPIED_P2;
                    }
                } else{
                    gameState += NON_OCCUPIED;
                }
                if (col != UrBoardModel.COLUMNS - 1) {
                    gameState += ",";
                }
            }
            gameState += "\n";
        }
        
        gameState += gameBoard.playerTurn;
        
        return gameState;
    }
    
    private UrPlayerModel createPlayer(String[] player) {
        Color playerColor = new Color(Integer.parseInt(player[0]));
        int playerScore = Integer.parseInt(player[2]);
        // Color playerColor, String playerName, int playerScore
        return new UrPlayerModel(playerColor, player[1], playerScore);
    }
    
    public void loadGameState(ArrayList<String> fileContents) {
        // read player colors and score
        int fileContentsIndex = 0;
        firstPlayer = createPlayer(fileContents.get(fileContentsIndex).split("[,]", 0));
        
        fileContentsIndex++;
        secondPlayer = createPlayer(fileContents.get(fileContentsIndex).split("[,]", 0));
        
        fileContentsIndex++;
        gameBoard = new UrBoardModel(firstPlayer.getColor(), secondPlayer.getColor());
        
        for(int row = 0; row < UrBoardModel.ROWS; row++){
            fileContentsIndex++;
            for(int col = 0; col < UrBoardModel.COLUMNS; col++){
                char character = fileContents.get(row).charAt(col);
                if(character != ',') {
                    UrPieceModel piece = null;
                    if (character == OCCUPIED_P1) {
                        piece = new UrPieceModel(firstPlayer.getColor());
                        
                    } else if (character == OCCUPIED_P2) {
                        piece = new UrPieceModel(secondPlayer.getColor());
                    }
                    gameBoard.setPiece(row, col, piece);
                }
            }
        }
        
        fileContentsIndex++;
        gameBoard.playerTurn = Integer.parseInt(fileContents.get(fileContentsIndex));
    }

    public UrBoardModel getGameBoard() {
        return gameBoard;
    }
    
    public UrPlayerModel[] getGamePlayers() {
        UrPlayerModel[] playerArray = new UrPlayerModel[2];
        playerArray[0] = firstPlayer;
        playerArray[1] = secondPlayer;
   
        return playerArray;
    }
}
