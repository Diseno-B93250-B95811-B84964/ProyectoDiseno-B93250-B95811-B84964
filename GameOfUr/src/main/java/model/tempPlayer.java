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
public class tempPlayer {
    private Color playerColor;
    private String playerName;
   
    public tempPlayer() {
        this.playerColor = Color.WHITE;
        this.playerName = "";
    }
    
    public tempPlayer(Color playerColor, String playerName) {
        this.playerColor = playerColor;
        this.playerName = playerName;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }


            
            
}
