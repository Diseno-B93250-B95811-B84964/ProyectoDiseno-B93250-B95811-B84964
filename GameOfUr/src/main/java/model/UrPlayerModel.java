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
    private ArrayList<UrPieceModel> playerPieces;

    private Color playerColor;
    private String playerName;
    private int score;
    
    public UrPlayerModel(int playerColumn) { // TODO delete this
        this.playerColor = Color.WHITE;
        this.playerName = "Player";
        this.score = 0;
        
        playerPieces = new ArrayList<UrPieceModel>();
        for(int pieces = 0; pieces < NUMBER_OF_PIECES; pieces++) {
            playerPieces.add(new UrPieceModel(this.playerColor, playerColumn));
        }
    }
    
    public UrPlayerModel (Color playerColor, int playerColumn) {
        this.playerColor = playerColor;
        this.playerName = "Player";
        this.score = 0;
        playerPieces = new ArrayList<UrPieceModel>();
        for(int pieces = 0; pieces < NUMBER_OF_PIECES; pieces++) {
            playerPieces.add(new UrPieceModel(this.playerColor, playerColumn));
        }
    }

    public UrPlayerModel (Color playerColor, String playerName, int playerScore, int playerColumn) {
        this.playerColor = playerColor;
        this.playerName = playerName;
        this.score = playerScore;
        playerPieces = new ArrayList<>();
        for(int pieces = 0; pieces < NUMBER_OF_PIECES; pieces++) {
            playerPieces.add(new UrPieceModel(this.playerColor, playerColumn));
        }
    }

    public Color getColor(){
        return this.playerColor;
    }
    

    public void setColor(Color playerColor){
        this.playerColor = playerColor;
    }
    

    public String getPlayerName() {
        return this.playerName;
    }
    

    public void setPlayerName(String name) {
        this.playerName = name;
    }
    

    public int getPlayerScore(){
        return this.score;
    }

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
