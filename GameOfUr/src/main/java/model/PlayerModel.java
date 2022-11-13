/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;

/**
 *
 * @author Mauricio Palma
 */
public abstract class PlayerModel {

    /**
     * Attribute that holds player's ID
     */
    protected int playerID;

    /**
     * Attribute that holds player's score
     */
    protected int score;
    
    protected Color playerColor;
    
    /**
     * Default constructor method that sets everything to default values
     */
    public PlayerModel() {
        this.playerID = -1;
        this.score = 0;
        this.playerColor = Color.WHITE;
    }
    
    /**
     * Constructor method that sets the PlayerID to the given parameter
     * @param playerID
     */
    public PlayerModel (int playerID) {
        this.playerID = playerID;
        this.score = 0;
    }
    
    /**
     * Method that allows to set the ID of a given player to the given parameter
     * @param id The ID that will be assigned to the Player
     */
    public void setPlayerID(int id) {
        this.playerID = id;
    }
    
    /**
     *
     * @return An integer indicating the ID of the Player
     */
    public int getplayerID() {
        return this.playerID;
    }
    
    /**
     * Method that allows to increase score to the Player
     */
    public abstract void addScoreToPlayer();
    
    /**
     *
     * @return An integer indicating the current score of the Player
     */
    public int getPlayerScore(){
        return this.score;
    }
    
    public void setColor(Color playerColor){
        this.setColor(playerColor);
    }
    
    public Color getColor(){
        return playerColor;
    }
}
