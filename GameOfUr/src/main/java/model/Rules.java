/*
 * Issue #26 - Game Logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package model;

import java.util.ArrayList;

/**
 * Stores game rules.
 * @author Jimena Gdur.
 */
public final class Rules
{
    /**
     * Stores all game rules
     */
    private ArrayList<String> rules;
    
    /**
     * Creates a new Rules class.
    */
    public Rules() {
        readRules();
    }
    /**
     * Reads rules from 'gameRules' file and stores it in rules array.
    */
    private void readRules() {
        FileManager manager = new FileManager();
        manager.loadFile("gameRules.txt", "src/main/java/auxiliaryFiles/");
        rules = manager.getFileContents();
    }
    /**
     * Returns list of rules.
     * @return this.rules array.
     */
    public ArrayList<String> getRules() {
        return this.rules;
    }
}
