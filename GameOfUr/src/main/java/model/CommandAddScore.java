/*
 * Issue #26 - Game logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package model;

import java.util.ArrayList;

/**
 * Validates a player's score for Royal Game Of Ur.
 * @author Jimena Gdur.
 */
public class CommandAddScore implements CommandInterface {
    /**
     * Stores the player which score should be validated.
     */
    private ArrayList<Player> playerArray;
    
    private int currentPlayer;
    
    private Tile lastMovedTile;
    
    private int outOfBoundRow;
    
    private int outOfBoundColumn;
    
    /**
     * Validates player's score taking into consideration their last move.
     * @param playerId Indicates which player to validate.
     * @param lastMoveRow Row of tile in which player set their piece.
     * @param lastMoveCol Column of tile in which player set their piece.
     * @return whether a point was given to the player.
     */

    public CommandAddScore(ArrayList<Player> playerArray, int currentPlayer, Tile lastMovedTile, int outOfBoundRow, int outOfBoundColumn) {
        this.playerArray = playerArray;
        this.currentPlayer = currentPlayer;
        this.lastMovedTile = lastMovedTile;
        this.outOfBoundRow = outOfBoundRow;
        this.outOfBoundColumn = outOfBoundColumn;
    }

    private void updateCurrentPlayer(){
        currentPlayer++;
        currentPlayer = currentPlayer % playerArray.size();
    }
    
    /**
     * Executes an action.
     * Validates player's score taking into consideration their last move.
     * @return whether the operation was successful.
     */
    @Override
    public boolean execute() {
        boolean success = false;
        if (this.lastMovedTile.getRow() == this.outOfBoundRow &&
                this.lastMovedTile.getColumn() != outOfBoundColumn){
            playerArray.get(currentPlayer).modifyScore();
            success = true;
        }
        updateCurrentPlayer();
        return success;
    }

    @Override
    public boolean unExecute() {
        System.out.println("Unexecuting...");
        return false;
    }
}
