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
public class UrPlayerModel extends PlayerModel{
    private Color playerColor;
    
    public UrPlayerModel() {
        super();
        this.playerColor = new Color(255,0,0); 
    }
    
    public UrPlayerModel (int playerID, Color playerColor) {
        super(playerID);
        this.playerColor = playerColor;
    }
    
}
