/*
 * Issue #25 - Game Logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package model;

/**
 * Stores game rules.
 * @author Jimena Gdur.
 */
public abstract class Rules {
    /**
     * Stores all game rules
     */
    private String[] rules;
    
    /**
     * Creates a new Rules class.
    */
    public Rules(String fileName) {
        FileManager manager = new FileManager();
    }
}
