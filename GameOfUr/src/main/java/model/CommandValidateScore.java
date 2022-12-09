/*
 * Issue #26 - Game logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package model;

/**
 * Validates a player's score for Royal Game Of Ur.
 * @author Jimena Gdur.
 */
public class CommandValidateScore implements CommandInterface {
    /**
     * Stores the player which score should be validated.
     */
    private Player currentPlayer;
    /**
     * Initializes ValidateScore class, allowing Action to have parameters.
     * @param player Indicates which player to validate.
     * @param lastMoveRow Row of tile in which player set their piece.
     * @param lastMoveCol Column of tile in which player set their piece.
     */
    public CommandValidateScore(Player player, int lastMoveRow, int lastMoveCol) {
        
    }
    /**
     * Executes an action.
     * Validates player's score taking into consideration their last move.
     * @return whether the operation was successful.
     */
    @Override
    public boolean execute() {
        boolean success = false;
        
        
        
        return success;
    }

    @Override
    public boolean unExecute() {
        System.out.println("Unexecuting...");
        return true;
    }
}
