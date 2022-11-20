/*
 * User Story # 
 * Jimena Gdur Vargas B93250
 * Álvaro Miranda Villegas B84964
 * Ronald Palma Villegas B95811
 */

package model;

import java.util.ArrayList;

/**
 *
 * @author Jimena Gdur
 */
public class UrSerializerModel {
    public UrBoardModel gameBoard; // make gets?
    public UrPlayerModel firstPlayer;
    public UrPlayerModel secondPlayer;
    
    public final static int NON_OCCUPIED = 0;
    public final static int OCCUPIED_P1 = 1;
    public final static int OCCUPIED_P2 = 2;
    
    public UrSerializerModel(UrBoardModel gameBoard, UrPlayerModel player1, UrPlayerModel player2) {
        this.gameBoard = gameBoard;
        this.firstPlayer = player1;
        this.secondPlayer = player2;
    }
    
    public ArrayList<String[]> saveGameState() {
        ArrayList<String[]> gameState = new ArrayList<>();
        
        int playerRGB = 0;
        int playerScore = 0;
        
        playerRGB = firstPlayer.getColor().getRGB();
        playerScore = firstPlayer.getPlayerScore();
        String[] player1 = {Integer.toString(playerRGB), firstPlayer.getPlayerName(), Integer.toString(playerScore)};
        gameState.add(player1);
        
        playerRGB = secondPlayer.getColor().getRGB();
        playerScore = secondPlayer.getPlayerScore();
        String[] player2 = {Integer.toString(playerRGB), secondPlayer.getPlayerName(), Integer.toString(playerScore)};
        gameState.add(player2);

        for(int row = 0; row < UrBoardModel.ROWS; row++){
            String[] boardRow = new String[UrBoardModel.COLUMNS];
            for(int col = 0; col < UrBoardModel.COLUMNS; col++){
                if( !gameBoard.getTile(row, col).isVacant()){
                    if(gameBoard.getTile(row, col).getPiece() != null) {
                        if(firstPlayer.getColor() == gameBoard.getTile(row, col).getPiece().getColor()) {
                            boardRow[col] = Integer.toString(OCCUPIED_P1);
                        } else {
                            boardRow[col] = Integer.toString(OCCUPIED_P2);
                        }
                    }
                } else{
                    boardRow[col] = Integer.toString(NON_OCCUPIED);
                }
            }
            gameState.add(boardRow);
        }
        
        int currentPlayerRGB = gameBoard.getPlayerTurn().getRGB();
        String[] currentPlayerRGBString = { Integer.toString(currentPlayerRGB) };
        gameState.add(currentPlayerRGBString);
        
        return gameState;
    }
}
