/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Jimena Gdur Vargas
 */
public class UrDeserializerModel {
    private UrPlayerModel[] playerArray;
    private UrBoardModel board;
    
    public final static int NON_OCCUPIED = 0;
    public final static int OCCUPIED_P1 = 1;
    public final static int OCCUPIED_P2 = 2;
    
    public UrDeserializerModel(UrPlayerModel[] playerArray, UrBoardModel board) {
        this.playerArray = playerArray;
        this.board = board;
    }
    
    public void loadGameState(ArrayList<String[]> fileContents) {
        int fileContentsIndex = 0;
        setPlayerInfo(0, fileContents.get(fileContentsIndex));
        
        fileContentsIndex++;
        setPlayerInfo(1, fileContents.get(fileContentsIndex));
        
        fileContentsIndex++;
        board.createPlayerPaths(playerArray[0].getColor(), playerArray[1].getColor());
        fileContentsIndex = createBoard(fileContents, fileContentsIndex);
        
        board.setPlayerTurn(new Color(Integer.parseInt(fileContents.get(fileContentsIndex)[0])));  
    }
    
    private void setPlayerInfo(int playerId, String[] playerInfo) {
        Color playerColor = new Color(Integer.parseInt(playerInfo[0]));
        int playerScore = Integer.parseInt(playerInfo[2]);
        /// Color playerColor, String playerName, int playerScore
        playerArray[playerId].changePlayerInfo(playerColor, playerInfo[1], playerScore);
    }
    
    private int createBoard(ArrayList<String[]> fileContents, int fileContentsIndex) {
        int pieceIndexPlayer1 = 0; // contains player 1's piece array index
        int pieceIndexPlayer2 = 0; // contains player 2's piece array index
        UrPieceModel piece; // player's piece to be set in board
        int value = 0; // contains char as numeric value
        
        for(int row = 0; row < UrBoardModel.ROWS; row++) {
            for(int col = 0; col < UrBoardModel.COLUMNS; col++) {
                String cellValue = fileContents.get(fileContentsIndex)[col];
                try {
                    value = Integer.parseInt(cellValue);
                    if (value == OCCUPIED_P1) {
                        piece = playerArray[0].getPlayerPiece(pieceIndexPlayer1);
                        board.setPiece(row, col, piece);
                        pieceIndexPlayer1++;
                    } else if (value == OCCUPIED_P2) {
                        piece = playerArray[1].getPlayerPiece(pieceIndexPlayer2);
                        board.setPiece(row, col, piece);
                        pieceIndexPlayer2++;
                    }
                } catch(NumberFormatException nfe) { }
            }
            fileContentsIndex++;
        }
        return fileContentsIndex;
    }
}
