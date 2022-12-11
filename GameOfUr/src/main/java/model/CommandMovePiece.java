/*
 * Issue #26 - Game logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package model;

import java.util.ArrayList;
import org.apache.commons.lang3.mutable.MutableBoolean;

/**
 * Sets piece in calculated if clicked tile is a valid move.
 * @author Jimena Gdur, Mauricio Palma.
 */
public class CommandMovePiece implements CommandInterface {
    /**
     * Stores reference game board.
     */
    private final Board board;
    /**
     * Stores reference game players.
     */
    private final ArrayList<Player> playerArray;
    /**
     * Indicates current player.
     */
    private int currentPlayer;
    /**
     * Stores reference to game dice.
     */
    private final Dice dice;
    /**
     * Stores reference to last moved tile.
     */
    private final Tile clickedTile;
    /**
     * Stores reference to next tile.
     */
    private Tile possibleTile;
    /**
     * Stores the column numbers associated with each player.
     */
    private final int[] playerColumns;
    /**
     * Stores the players shared column.
     */
    private final int sharedColumn;
    /**
     * Stores if a piece has been eaten during the game.
     */
    private MutableBoolean pieceEaten;

    /**
     * Creates a command with given references.
     * @param board Game board with graph data.
     * @param playerArray Player array.
     * @param currentPlayer Index of player that starts the game.
     * @param dice Game dice with last stored value.
     * @param clickedTile Tile that user has clicked.
     * @param possibleTile Tile that will be assigned in class.
     * @param pieceEaten Value that stores if a piece has been eaten during the game.
     */
    public CommandMovePiece(Board board, ArrayList<Player> playerArray,
        int currentPlayer, Dice dice, Tile clickedTile, Tile possibleTile, MutableBoolean pieceEaten)
    {
        this.board = board;
        this.playerArray = playerArray;
        this.currentPlayer = currentPlayer;
        this.dice = dice;
        this.clickedTile = clickedTile;
        this.possibleTile = possibleTile;
        this.playerColumns = new int[2];
        playerColumns[0] = 0;
        playerColumns[1] = 2;
        this.sharedColumn = 1;
        this.pieceEaten = pieceEaten;
    }
    
    /**
     * Executes the action of eating a piece.
     * @return Indicates if the action of eating a piece has been successful.
     */
    @Override
    public boolean execute() {        
        boolean success = false;
        this.possibleTile.setRow(-1);
        this.possibleTile.setColumn(-1);
        this.possibleTile.removePiece();
        
        resetEatenValue();
                
        UrTile nextTile = null;
        System.out.println("MyColumn is: "+ clickedTile.getColumn());
        if (isPlayerColumn(clickedTile.getColumn())) {
            Piece currentPiece = getCurrentPiece();
            if (currentPiece != null) {
                nextTile = (UrTile) getPossibleTile();
                if (nextTile != null) {
                    // Copies value from next tile to possibleTile
                    possibleTile.setRow(nextTile.getRow());
                    possibleTile.setColumn(nextTile.getColumn());
                    possibleTile.setPiece(currentPiece);
                    success = setPieceInTile(nextTile, currentPiece);
                    if (success) {
                        Tile tile = this.board.getTile(this.clickedTile.getRow(), this.clickedTile.getColumn());
                        tile.removePiece();
                        clickedTile.removePiece();
                    }
                }
            }
        }
        
        updateCurrentPlayer();
        return success;
    }
    /**
     * Indicates if a current column is of a certain player.
     * @param col The selected column.
     * @return Indicates if the column is of the player or not.
     */
    private boolean isPlayerColumn(int col) {
        boolean isMine = false;
        if ( (col == playerColumns[currentPlayer]) || col == sharedColumn ) {
            isMine = true;
        }
        return isMine;
    }
    /**
     * Gets a piece to be moved, if the piece is already on the board, or has to
     * be taken from the players array of pieces.
     * @return The current piece.
     */
    private Piece getCurrentPiece() {
        Piece currentPiece = null;
        if (clickedTile.isVacant() && clickedTile.getRow() == 4 && clickedTile.getColumn() != 1){
           currentPiece = this.playerArray.get(currentPlayer).getAvailablePiece();
        } else if (!clickedTile.isVacant()) {
            currentPiece = clickedTile.getPiece();
        }
        return currentPiece;
    }
    
    /**
     * Indicates if a pair of coordinates are contained within the board.
     * @param row The selected row.
     * @param col The selected column.
     * @return If the coordinates are on the board or not.
     */
    private boolean isInRange(int row, int col) {
        boolean inRange = false;
        
        if ( row >= 0 && row < board.getAmountRows()
                && col >= 0 && col < board.getAmountColumns() ) {
            inRange = true;
        }
        
        return inRange;
    }
    /**
     * Get the possible tile for a move.
     * @return The selected tile.
     */ 
    private Tile getPossibleTile() {
        int currentRow = clickedTile.getRow();
        int currentCol = clickedTile.getColumn();
        
        int diceResult = dice.getDiceResult();
        
        Tile newTile = null;
        
        if (isInRange(currentRow, currentCol)
            && isPlayerColumn(currentCol) && diceResult != 0)
        {
            ArrayList<Tile> tileVertices = board.getVertices(currentRow, currentCol);
            while(diceResult > 0 && !tileVertices.isEmpty()) {
                
                    Tile tile = tileVertices.get(0);
                    currentCol = tile.getColumn();
                    currentRow = tile.getRow();
                    if (tileVertices.size() > 1) {
                        if (currentPlayer == 0) {
                            currentCol = 0;
                            currentRow = 7;

                            newTile = tileVertices.get(0);
                        } else {
                            currentCol = 2;
                            currentRow = 7;
                            newTile = tileVertices.get(1);
                       }
                    } else {
                        newTile = tile; 
                    }
                diceResult--;
                tileVertices = board.getVertices(currentRow, currentCol);

            }
        }
        
        return newTile;
    }
    /**
     * Indicates if a pair of coordinates are contained within the board.
     * @param realTile Selected tile from the board.
     * @param movedPiece Piece that's being moved.
     * @return If the coordinates are on the board or not.
     */
    private boolean setPieceInTile(Tile nexTile, Piece movedPiece) {
        boolean success = false;
        UrTile urNextTile = (UrTile) nexTile;

        UrPiece myUrPiece = (UrPiece) movedPiece;
        UrPiece yourUrPiece;


        if (urNextTile.isVacant()) {
            myUrPiece.setInPlay();
            urNextTile.setPiece(myUrPiece);
            success = true;
        } else if (urNextTile.getPiece().getColor().getRGB() != myUrPiece.getColor().getRGB()
            && !urNextTile.isSafe())
        {
            this.pieceEaten.setTrue();
            urNextTile.setPiece(myUrPiece);
            myUrPiece.setInPlay();
            yourUrPiece = (UrPiece) urNextTile.getPiece();
            yourUrPiece.setNotInPlay();

            success = true;
        } 
        return success;
    }
    
    /**
     * Changes current player to next one.
     */
    private void updateCurrentPlayer(){
        currentPlayer++;
        currentPlayer = currentPlayer % playerArray.size();
    }
    /**
     * Reverts an action.
     * @return whether the operation was successful.
     */
    @Override
    public boolean unExecute() {
        return false;    
    }
    /**
     * Resets the eaten value.
     */
    private void resetEatenValue() {
        pieceEaten.setFalse();
    }
}
