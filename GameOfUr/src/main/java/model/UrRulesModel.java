/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Jimena Gdur
 */
public class UrRulesModel {
    private String rules[];
    
    public UrRulesModel(){
        rules = new String[11];
        rules[0] = "Game Rules";
        rules[1] = "1. Throw the dice to decide who plays first - highest score";
        rules[2] = "goes first, if it's a draw, throw again.";
        rules[3] = "2. Players take turns to throw three binary lots and move ";
        rules[4] = "one of their pieces.";
        rules[5] = "3. Only one piece may be moved per throw of the dice and ";
        rules[6] = "pieces must always move forward around the track.";
        rules[7] = "4. If a counter lands upon a square occupied by an opposing ";
        rules[8] = "counter, the counter landed upon is sent off the board and ";
        rules[9] = "must start again from the beginning";
        rules[10] = "5. This wil happen unless a player lands on a flower tile.";
    }
    
    public String[] getRules() {
        return rules;
    }
    
    public int getLength() {
        return rules.length;
    }
}
