/*
 * User Story # 
 * Jimena Gdur Vargas B93250
 * Álvaro Miranda Villegas B84964
 * Ronald Palma Villegas B95811
 */

package model;

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
    
    public String saveGameState() {
        String gameState = "";
        
        // Color playerColor, String playerName, int playerScore
        gameState += firstPlayer.getColor().getRGB() + "," + firstPlayer.getPlayerName() + "," + firstPlayer.getPlayerScore() + "\n";
        gameState += secondPlayer.getColor().getRGB() + "," + secondPlayer.getPlayerName() + "," + secondPlayer.getPlayerScore() + "\n";
        
        for(int row = 0; row < UrBoardModel.ROWS; row++){
            for(int col = 0; col < UrBoardModel.COLUMNS; col++){
                if( !gameBoard.getTile(row, col).isVacant()){
                    /*TODO change this to a Method. DO NOT call the attribute directly*/
                    if(firstPlayer.getColor() == gameBoard.getTile(row, col).getPiece().getColor()) {
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
        
        gameState += gameBoard.getPlayerTurn().getRGB();
        
        return gameState;
    }
}
