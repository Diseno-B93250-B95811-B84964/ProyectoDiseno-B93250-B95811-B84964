/*
 * Issue #26 - Game logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package model;

import java.util.ArrayList;

/**
 * Validates a player's score taking into consideration their last move.
 * Made for the Royal Game Of Ur.
 * @author Jimena Gdur, Mauricio Palma.
 */
public class CommandAddScore implements CommandInterface {
    /**
     * Stores the players which score will be validated.
     */
    private final ArrayList<Player> playerArray;
    /**
     * Stores the index of the current player.
     */
    private int currentPlayer;
    /**
     * Stores last moved tile.
     */
    private final Tile lastMovedTile;
    /**
     * Stores a row that represents that a piece is out of the game.
     */
    private final int outOfBoundRow;
    /**
     * Stores a column that represents that a piece is out of the game.
     */
    private final int outOfBoundColumn;
    
    /**
     * Creates new command with references to changing values.
     * @param playerArray Player array received from referee.
     * @param currentPlayer Index of current player.
     * @param lastMovedTile Tile received from referee, contains last moved tile.
     * @param outOfBoundRow Specific row that is out of bounds for Ur.
     * @param outOfBoundColumn Specific column that is out of bounds for Ur.
     */
    public CommandAddScore(ArrayList<Player> playerArray, int currentPlayer, Tile lastMovedTile) {
        this.playerArray = playerArray;
        this.currentPlayer = currentPlayer;
        this.lastMovedTile = lastMovedTile;
        this.outOfBoundRow = 5;
        this.outOfBoundColumn = 1;
    }
    /**
     * Updates current player index.
     */
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
            System.out.println("VOY A BORRAR REMOVE PIECE x" + lastMovedTile.getRow() + " y " + lastMovedTile.getColumn());
            this.lastMovedTile.removePiece();
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
