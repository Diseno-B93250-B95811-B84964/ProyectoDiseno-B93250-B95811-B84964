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
public final class Dice {
    /**
    * A helper list that contains the weighted sum of each side.
    */
    private float[] weightedSumList;
    /**
    * The amount of sides in current dice.
    */
    private final int sideAmount;
    
    /**
     * Creates a dice with given sides, each with it's own weight.
     * @param sides Amount of sides in dice.
     * @param probabilities An array with the probability of each side.
     */
    public Dice(int sides, float[] probabilities) {
        sideAmount = sides;
        createHelperList(probabilities);
    }
    /**
     * Creates a helper list with the weighted sum of each side.
     * @param probabilities An array with the probability of each side.
     */
    private void createHelperList(float[] probabilities) {
        weightedSumList = new float[sideAmount];
        weightedSumList[0] = probabilities[0];
        for (int sideIndex = 1; sideIndex < sideAmount; sideIndex++) {
            weightedSumList[sideIndex] =
                weightedSumList[sideIndex - 1] + probabilities[sideIndex];
        }
    }
    /**
     * Throws the dice with given probabilities.
     * Based on http://censore.blogspot.com/2014/08/simulate-roll-of-bias-or-weighted-dice.HTML
     * @return random number.
     */
    public int throwDice() {
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(100);
        
        int side = sideAmount;
        // If X <= a[0] choose 1 as outcome
        if(randomNumber <= weightedSumList[0]) {
            side = 1;
        }
        // If a[0] < X <= a[n-1] choose [2 ... n-1] as outcome
        else if (randomNumber <= weightedSumList[sideAmount - 1]) {
            for(int sideIndex = 1; sideIndex <= sideAmount - 2; sideIndex++) {
                if (randomNumber > weightedSumList[sideIndex - 1] && randomNumber <= weightedSumList[sideIndex]) {
                    side = sideIndex + 1;
                }
            }
        }
        // If X > a[n-1] choose n as outcome
        return side;
    }
}
