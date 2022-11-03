/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;

/**
 *
 * @author mauup
 */
public class PlayerModel {
    private int playerID;
    private Color playerColor;
    
    public PlayerModel() {
        this.playerID = -1;
        this.playerColor = new Color(255,0,0);
    }
    
    public PlayerModel (int playerID, Color playerColor) {
        this.playerID = playerID;
        this.playerColor = playerColor;
    }
    
    public void setPlayerID(int id) {
        this.playerID = id;
    }
    
    public int getID() {
        return this.playerID;
    }
}
