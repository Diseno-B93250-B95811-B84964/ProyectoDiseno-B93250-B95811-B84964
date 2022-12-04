/*
 * User Story # 
 * Jimena Gdur Vargas B93250
 * Álvaro Miranda Villegas B84964
 * Ronald Palma Villegas B95811
 */

package model;

/**
 *
 * @author Alvaro Miranda
 */
public class Dice {
    private int rollResult;
    
    public Dice()
    {
        rollResult = 0;
    }

    public void rollDice()
    {
        int min = 0;
        int max = 4;
        int range = max - min + 1;
        rollResult = (int)(Math.random() * range) + min;
    }
    
    public int getRollResult()
    {
        return rollResult;
    }
}