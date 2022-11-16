/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.UrPlayerModel;


/**
 *
 * @author Mauricio Palma
 */
public abstract class PlayerController {

    /**
     * Array of UrPlayerModels to keep track of every player playing a game
     */
    protected UrPlayerModel playersArray[];
    
    /**
     * Default constructor that creates two players
     */
    public PlayerController(){
        playersArray = new UrPlayerModel[2];
    }
    
    /**
    * Constructor that creates N players 
    * @param numberOfPlayers It indicates the number of players that will be playing the game
    */
    
    public PlayerController(int numberOfPlayers){
        playersArray = new UrPlayerModel[numberOfPlayers];
    }
    
    /**
     * Method that sets the two players existing in the array to the given parameters
     * @param firstPlayer The first player that will be playing the game
     * @param secondPlayer The second player that will be playing the game
     */
    public void setPlayers(UrPlayerModel firstPlayer, UrPlayerModel secondPlayer){ // make it abstract and remove attributes
        playersArray[0] = firstPlayer;
        playersArray[1] = secondPlayer;
    }
    
    /**
     * Template method that allows the controller to start operating
     */
    public abstract void start();
      
}
