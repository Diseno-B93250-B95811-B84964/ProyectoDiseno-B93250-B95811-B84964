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
    
    private final int sharedColumn;
    
    private MutableBoolean pieceEaten;

    /**
     * Creates a command with given references.
     * @param board Game board with graph data.
     * @param playerArray Player array.
     * @param currentPlayer Index of player that starts the game.
     * @param dice Game dice with last stored value.
     * @param clickedTile Tile that user has clicked.
     * @param possibleTile Tile that will be assigned in class.
     * @param playerColumns
     * @param sharedColumn
     * @param pieceEaten
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
     
    @Override
    public boolean execute() {
         System.out.println(clickedTile.getRow() + ", " + clickedTile.getColumn());
        
        boolean success = false;
        resetEatenValue();
        
        System.out.println("dice: " + (dice.getDiceResult() - 1));
        
        UrTile nextTile = null;
        if (isPlayerColumn(clickedTile.getColumn())) {
            Piece currentPiece = getCurrentPiece();
            if (currentPiece != null) {
                nextTile = (UrTile) getPossibleTile();
                if (nextTile != null) {
                    // Copies value from next tile to possibleTile
                    possibleTile.setRow(nextTile.getRow());
                    possibleTile.setColumn(nextTile.getColumn());
                    setPieceInTile(nextTile, currentPiece);
                    success = true;
                }
            }
        }
        
        updateCurrentPlayer();
        return success;
    }
    
    private boolean isPlayerColumn(int col) {
        boolean isMine = false;
        if ( (col == playerColumns[currentPlayer]) || col == sharedColumn ) {
            isMine = true;
        }
        return isMine;
    }
    
    private Piece getCurrentPiece() {
        Piece currentPiece = null;
        
        if (this.clickedTile.isVacant()){
           currentPiece = this.playerArray.get(currentPlayer).getAvailablePiece();
        } else {
            currentPiece = this.clickedTile.getPiece();
        }
        
        return currentPiece;
    }
    
    
    private boolean isInRange(int row, int col) {
        boolean inRange = false;
        
        if ( row >= 0 && row < board.getAmountRows()
                && col >= 0 && col < board.getAmountColumns() ) {
            inRange = true;
        }
        
        return inRange;
    }
            
    private Tile getPossibleTile() {
        int currentRow = this.clickedTile.getRow();
        int currentCol = this.clickedTile.getColumn();
        
        int diceResult = dice.getDiceResult() - 1;
        
        Tile newTile = null;
        
        if (isInRange(currentRow, currentCol)
            && isPlayerColumn(currentCol) && diceResult != 0)
        {
            ArrayList<Tile> tileVertices = board.getVertices(currentRow, currentCol);

            while(diceResult > 0 && !tileVertices.isEmpty()) {
                for (Tile tile : tileVertices) {
                    currentCol = tile.getColumn();
                    currentRow = tile.getRow();
                    if (isPlayerColumn(currentCol)) {
                        newTile = tile;
                        diceResult--;
                    }
                }
                tileVertices = board.getVertices(currentRow, currentCol);
            }
        }
        
        return newTile;
    }

    private void setPieceInTile(Tile realTile, Piece movedPiece) {
        UrTile urTile = (UrTile) realTile;
        UrPiece myUrPiece = (UrPiece) movedPiece;
        UrPiece yourUrPiece;
        if (realTile.isVacant()) {
            myUrPiece.setInPlay();
            realTile.setPiece(movedPiece);
        } else if (realTile.getPiece().getColor() != movedPiece.getColor()
            && !urTile.isSafe())
        {
            this.pieceEaten.setTrue();
            realTile.setPiece(movedPiece);
            myUrPiece.setInPlay();
            yourUrPiece = (UrPiece) realTile.getPiece();
            yourUrPiece.setNotInPlay();
            this.clickedTile.removePiece();
        } 
    }
    
    /**
     * Changes current player to next one.
     */
    private void updateCurrentPlayer(){
        currentPlayer++;
        currentPlayer = currentPlayer % playerArray.size();
    }
    
    @Override
    public boolean unExecute() {
        return false;    
    }
    
    private void resetEatenValue() {
        pieceEaten.setFalse();
    }
}
