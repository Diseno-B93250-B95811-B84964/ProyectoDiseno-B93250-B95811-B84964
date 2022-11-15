/*
 * User Story # 
 * Jimena Gdur Vargas B93250
 * Álvaro Miranda Villegas B84964
 * Ronald Palma Villegas B95811
 */

package model;

import java.awt.Color;

/**
 *
 * @author Mauricio Palma
 */
public class UrPlayerModel {
    /**
     * Attribute that holds player's color
     */
    protected Color playerColor;
    
    /**
     * Attribute that holds player's name
     */
    protected String playerName;
    
    /**
     * Attribute that holds player's score
     */
    protected int score;
    
    /**
     * Default constructor method that sets everything to default values
     */
    public UrPlayerModel() {
        this.playerColor = Color.WHITE;
        this.playerName = "Player";
        this.score = 0;
    }
    
    /**
     * Constructor method that sets the PlayerID to the given parameter
     * @param playerColor Player's Color
     */
    public UrPlayerModel (Color playerColor) {
        this.playerColor = playerColor;
        this.playerName = "Player";
        this.score = 0;
    }
    
    /**
     * Constructor method that sets the PlayerID to the given parameter
     * @param playerName Player's Name
     * @param playerScore Player's Score
     * @param playerColor Player's Color
     */
    public UrPlayerModel (Color playerColor, String playerName, int playerScore) {
        this.playerColor = playerColor;
        this.playerName = playerName;
        this.score = playerScore;
    }
    
    /**
     * Returns assigned color
     * @return The Color assigned
     */
    public Color getColor(){
        return this.playerColor;
    }
    
    /**
     * Assigns color to a player
     * @param playerColor Chosen color
     */
    public void setColor(Color playerColor){
        this.playerColor = playerColor;
    }
    
    /**
     * Returns assigned name
     * @return The String assigned
     */
    public String getPlayerName() {
        return this.playerName;
    }
    
    /**
     * Assigns name to a player
     * @param name Chosen name
     */
    public void setPlayerName(String name) {
        this.playerName = name;
    }
    
    /**
     *
     * @return An integer indicating the current score of the Player
     */
    public int getPlayerScore(){
        return this.score;
    }
    
    /**
     * Method that allows to increase score to the Player
     */
    public void addScoreToPlayer(){
        this.score++;
    }
}
