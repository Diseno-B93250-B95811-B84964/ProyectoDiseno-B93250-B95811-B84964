/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author mauup
 */
public class CommandValidateWinner implements CommandInterface {

    private ArrayList<Player> playerArray;
    private int currentPlayer;

    public CommandValidateWinner(ArrayList<Player> playerArray, int currentPlayer) {
        this.playerArray = playerArray;
        this.currentPlayer = currentPlayer;
    }
    
    /*TODO delete this method*/
    public int getPlayerArrayLength(){
        return playerArray.size();
    }
    
    private void updateCurrentPlayer(){
        currentPlayer++;
        currentPlayer = currentPlayer % playerArray.size();
    }
    
    @Override
    public boolean execute() {
        System.out.println("Current length: " + getPlayerArrayLength());
        System.out.println("Current player value: " + currentPlayer);
        
        System.out.println("Name of the current player: " + playerArray.get(currentPlayer).getName());
        System.out.println("Current player score: " + playerArray.get(currentPlayer).getScore());
        System.out.println("/***********************************/");
        boolean checkIfWinner=playerArray.get(currentPlayer).score >= 7;
        updateCurrentPlayer();
        return checkIfWinner;
    }

    @Override
    public boolean unExecute() {
        return false;
    }
    
}
