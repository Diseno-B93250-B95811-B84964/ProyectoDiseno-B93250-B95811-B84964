/*
 * Issue #25 - Graph logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package model;

/**
 * Object whose role is to store and execute an action.
 * @author Jimena Gdur.
 */
public interface CommandInterface
{
    /**
     * Executes an action.
     * @return whether the operation was successful.
     */
    public abstract boolean execute();
    public abstract boolean unExecute();
}
