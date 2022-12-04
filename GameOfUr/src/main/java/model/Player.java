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
public class Player {
    private Color color;
    private String name;
    
    public Player(){
        this.color = Color.WHITE;
        this.name = "";
    }

    public Player(Color color, String name) {
        this.color = color;
        this.name = name;
    }
    
    public Color getColor(){
        return this.color;
    }
    
    public String getName(){
        return this.name;
    }
}
