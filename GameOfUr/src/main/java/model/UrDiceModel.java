/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
/**
 *
 * @author Alvaro Miranda
 */
public class UrDiceModel extends DiceModel {
    
    private int urNumberOfSides;
    
    public UrDiceModel()
    {
        urNumberOfSides = 5;
        this.numberOfSides = urNumberOfSides;
        rollResult = 0;
    }
    
    @Override
    public void rollDice()
    {
        int min = 0;
        int max = 4;
        int range = max - min + 1;
        rollResult = (int)(Math.random() * range) + min;
    }
    
    @Override
    public int getRollResult()
    {
        return rollResult;
    }
}