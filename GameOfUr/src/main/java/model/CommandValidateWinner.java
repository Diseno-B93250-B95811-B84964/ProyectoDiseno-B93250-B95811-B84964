/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 * Validates the winner.
 * @author Mauricio Palma.
 */
public class CommandValidateWinner implements CommandInterface {

    private ArrayList<Player> playerArray;
    private int currentPlayer;
    private int maxScore;

    /**
    * Validates the status of all players and determines if someone has won.
    * @return the id of the player that has won the game.
    */
    public CommandValidateWinner(ArrayList<Player> playerArray, int currentPlayer, int maxScore) {
        this.playerArray = playerArray;
        this.currentPlayer = currentPlayer;
        this.maxScore = maxScore;
    }
    
    /*TODO delete this method*/
    public int getPlayerArrayLength(){
        return playerArray.size();
    }
    
    private void updateCurrentPlayer(){
        currentPlayer++;
        currentPlayer = currentPlayer % playerArray.size();
    }
    /**
     * Executes an action, to follow the command pattern.
     * @return Whether the operation was successful.
     */
    @Override
    public boolean execute() {
        boolean checkIfWinner = this.playerArray.get(currentPlayer).score >= this.maxScore;
        updateCurrentPlayer();
        return checkIfWinner;
    }
    
    /**
     * Reverts an execution, to follow the command pattern.
     * @return Whether the operation was successful.
     */
    @Override
    public boolean unExecute() {
        return false;
    }
    
}
