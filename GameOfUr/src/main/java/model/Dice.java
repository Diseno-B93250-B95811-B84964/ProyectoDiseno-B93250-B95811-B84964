/*
 * Issue #26 - Game Logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */

package model;

import java.util.Random;

/**
 * Simulate the roll of a loaded die.
 * @author Jimena Gdur.
 */
public final class Dice
{
    /**
    * A helper list that contains the weighted sum of each side.
    */
    private int[] weightedSumList;
    /**
    * The amount of sides in current dice.
    */
    private final int sideAmount;
    /**
    * The value of the side of the dice.
    */
    private int side;
    
    /**
     * Creates a dice with given sides, each with it's own weight.
     * @param sides Amount of sides in dice.
     * @param probabilities An array with the probability of each side.
     */
    public Dice(int sides, int[] probabilities) {
        this.sideAmount = sides;
        this.createHelperList(probabilities);
    }
    /**
     * Creates a helper list with the weighted sum of each side.
     * @param probabilities An array with the probability of each side.
     */
    private void createHelperList(int[] probabilities) {
        weightedSumList = new int[sideAmount];
        weightedSumList[0] = probabilities[0];
        for (int sideIndex = 1; sideIndex < sideAmount; sideIndex++) {
            weightedSumList[sideIndex] =
                weightedSumList[sideIndex - 1] + probabilities[sideIndex];
        }
    }
    /**
     * Throws the dice with given probabilities.
     * Adapted from http://censore.blogspot.com/2014/08/simulate-roll-of-bias-or-weighted-dice.HTML
     * @return Random number.
     */
    public int throwDice() {
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(100);

        this.side = sideAmount;
        // If X <= a[0] choose 1 as outcome
        if(randomNumber <= weightedSumList[0]) {
            this.side = 1;
        }
        // If a[0] < X <= a[n-1] choose [2 ... n-1] as outcome
        else if (randomNumber <= weightedSumList[sideAmount - 1]) {
            for(int sideIndex = 1; sideIndex <= sideAmount - 2; sideIndex++) {
                if (randomNumber > weightedSumList[sideIndex - 1] && randomNumber <= weightedSumList[sideIndex]) {
                    this.side = sideIndex + 1;
                }
            }
        }
        // If X > a[n-1] choose n as outcome
        return this.side;
    }
    /**
     * Gets the result returned from the dice.
     * @return Result of the dice.
     */
    public int getDiceResult(){
        return this.side;
    }
}