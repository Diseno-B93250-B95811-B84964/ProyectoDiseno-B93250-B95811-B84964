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
    protected int playerID;
    protected int score;
    
    public PlayerModel() {
        this.playerID = -1;
        this.score = 0;
    }
    
    public PlayerModel (int playerID) {
        this.playerID = playerID;
        this.score = 0;
    }
    
    public void setPlayerID(int id) {
        this.playerID = id;
    }
    
    public int getplayerID() {
        return this.playerID;
    }
    
    public void addScoreToPlayer(){
        this.score++;
    }
    
    public int getPlayerScore(){
        return this.score;
    }
}
