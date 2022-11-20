/*
 * User Story # 
 * Jimena Gdur Vargas B93250
 * Álvaro Miranda Villegas B84964
 * Ronald Palma Villegas B95811
 */

package model;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Mauricio Palma
 */
public class UrPlayerModel {
    public final static int NUMBER_OF_PIECES = 7;
    private final ArrayList<UrPieceModel> playerPieces;

    /**
     * Attribute that holds player's color
     */
    private Color playerColor;
    
    /**
     * Attribute that holds player's name
     */
    private String playerName;
    
    /**
     * Attribute that holds player's score
     */
    private int score;
    
    /**
     * Default constructor method that sets everything to default values
     */
    public UrPlayerModel(int playerColumn) {
        this.playerColor = Color.WHITE;
        this.playerName = "Player";
        this.score = 0;
        
        playerPieces = new ArrayList<UrPieceModel>();
        for(int pieces = 0; pieces < NUMBER_OF_PIECES; pieces++) {
            playerPieces.add(new UrPieceModel(this.playerColor, playerColumn));
        }
    }
    
    /**
     * Constructor method that sets the PlayerID to the given parameter
     * @param playerColor Player's Color
     */
    public UrPlayerModel (Color playerColor, int playerColumn) {
        this.playerColor = playerColor;
        this.playerName = "Player";
        this.score = 0;
        
        playerPieces = new ArrayList<>();
        for(int pieces = 0; pieces < NUMBER_OF_PIECES; pieces++) {
            System.out.println("playerColor: " + playerColor + " ");
            UrPieceModel piece = new UrPieceModel(this.playerColor, playerColumn);
            playerPieces.add(piece);
            System.out.println("piece: " + piece.getColor());
        }
    }
    
    /**
     * Constructor method that sets the PlayerID to the given parameter
     * @param playerName Player's Name
     * @param playerScore Player's Score
     * @param playerColor Player's Color
     * @param playerColumn
     */
    public UrPlayerModel (Color playerColor, String playerName, int playerScore, int playerColumn) {
        this.playerColor = playerColor;
        this.playerName = playerName;
        this.score = playerScore;
        
        playerPieces = new ArrayList<>();
        for(int pieces = 0; pieces < NUMBER_OF_PIECES; pieces++) {
            playerPieces.add(new UrPieceModel(this.playerColor, playerColumn));
        }
    }
    
    public void changePlayerInfo(Color playerColor, String playerName, int playerScore) {
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
    
    public ArrayList<UrPieceModel> getPlayerPieces(){
        return playerPieces;
    }
    
    public UrPieceModel getPlayerPiece(int pieceId) {
        return playerPieces.get(pieceId);
    }
    
    public void removePiece(UrPieceModel piece) {
        for (UrPieceModel playerPiece : this.playerPieces) {
            if((piece.getX() == playerPiece.getX() && (piece.getY() == playerPiece.getY()))){
                playerPieces.remove(playerPiece);
                break;
            }
        }
    }
}
