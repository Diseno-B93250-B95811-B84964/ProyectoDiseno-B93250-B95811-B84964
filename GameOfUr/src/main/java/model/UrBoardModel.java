/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Usuario1
 */
public class UrBoardModel {
    private TileModel[][] urBoard;
    private Color playerOneColor;
    private final static int ROWS = 8;
    private final static int COLUMNS = 3;
    private final static int NON_OCCUPIED = 0;
    private final static int OCCUPIED_P1 = 1;
    private final static int OCCUPIED_P2 = 2;
    
    public UrBoardModel(Color playerOneColor){
        for(int rows = 0; rows < ROWS; rows++){
            for(int cols = 0; cols < COLUMNS; cols++){
                urBoard[rows][cols] = new TileModel(); 
            }
        }
        /*
        urBoard[0][0].isSafe();
        urBoard[0][2].isSafe();
        urBoard[3][1].isSafe();
        urBoard[6][0].isSafe();
        urBoard[6][2].isSafe();*/
        this.playerOneColor = playerOneColor;
    }
    /*
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
    }*/
}