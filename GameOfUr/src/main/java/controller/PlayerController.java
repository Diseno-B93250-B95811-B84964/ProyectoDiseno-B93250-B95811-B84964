/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.PlayerModel;


/**
 *
 * @author Mauricio Palma
 */
public abstract class PlayerController {
    protected PlayerModel playersArray[];
    
    public PlayerController(){
        playersArray = new PlayerModel[2];
    }
    
    public void setPlayers(PlayerModel firstPlayer, PlayerModel secondPlayer){ // make it abstract and remove attributes
        playersArray[0] = firstPlayer;
        playersArray[1] = secondPlayer;
    }
    
    public abstract void start();
      
}
