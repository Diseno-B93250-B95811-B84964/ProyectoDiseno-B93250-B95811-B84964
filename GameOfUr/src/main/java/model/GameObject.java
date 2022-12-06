/*
 * Issue #25 - Graph logic.
 * 
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package model;

/**
 * Parent class that contains a toString method.
 * All game objects will inherit from this class.
 * @author Jimena Gdur
 */
abstract public class GameObject {
    /**
     * Converts specific class into a string.
     * @return a string representing the class
     */
    @Override
    abstract public String toString();
}
