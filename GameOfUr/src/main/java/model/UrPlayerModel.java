/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Mauricio Palma
 */
public class UrPlayerModel extends PlayerModel{
    
    public final static int NUMBER_OF_PIECES = 7;
    ArrayList<UrPieceModel> playerPieces;
    /**
     * Default constructor that calls super constructor and sets attribute to a default value
     */
    public UrPlayerModel() {
        super();
        playerPieces = new ArrayList<UrPieceModel>();
        for(int pieces = 0; pieces < NUMBER_OF_PIECES; pieces++) {
            playerPieces.add(new UrPieceModel(this.playerColor));
        }
    }
    
    /**
     * Constructor method that sets the ID and color of a given Player
     * @param playerID The ID that will be assigned to the player
     * @param playerColor The color that will be assigned to the player
     */
    public UrPlayerModel (int playerID, Color playerColor) {
        super(playerID);
    }
    
    /**
    * {@inheritDoc}
    * Within UrPlayerModel, this method will add one point to the current score of the player
    */
    @Override
    public void addScoreToPlayer(){
        this.score++;
    }
}
