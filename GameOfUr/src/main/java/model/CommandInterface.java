/*
 * Issue #26 - Game logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package model;

/**
 * Object whose role is to store and execute an action.
 * @author Jimena Gdur, Mauricio Palma.
 */
public interface CommandInterface
{
    /**
     * Executes an action, to follow the command pattern.
     * @return Whether the operation was successful.
     */
    public abstract boolean execute();
    /**
     * Reverts an execution, to follow the command pattern.
     * @return Whether the operation was successful.
     */
    public abstract boolean unExecute();
}
