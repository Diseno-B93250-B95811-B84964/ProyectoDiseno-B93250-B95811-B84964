/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.lang.Math;
/**
 *
 * @author Usuario1
 */
public abstract class Dice {
    
    protected int numberOfSides;
    protected int rollResult;
    
    public abstract void rollDice();
    public abstract int getRollResult();
}
