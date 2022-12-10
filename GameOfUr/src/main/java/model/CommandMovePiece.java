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
        System.out.println("Current player executing..." + currentPlayer);
        boolean success = false;
        this.possibleTile.setRow(-1);
        this.possibleTile.setColumn(-1);
        this.possibleTile.removePiece();
        
        resetEatenValue();
                
        UrTile nextTile = null;
        System.out.println("MyColumn is: "+ clickedTile.getColumn());
        if (isPlayerColumn(clickedTile.getColumn())) {
            Piece currentPiece = getCurrentPiece();
            System.out.println("piece: " + currentPiece);
            if (currentPiece != null) {
                nextTile = (UrTile) getPossibleTile();
                if (nextTile != null) {
                    // Copies value from next tile to possibleTile
                    possibleTile.setRow(nextTile.getRow());
                    possibleTile.setColumn(nextTile.getColumn());
                    success = setPieceInTile(nextTile, currentPiece);
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
        if (this.clickedTile.isVacant() && this.clickedTile.getRow() == 4 && this.clickedTile.getColumn() != 1){
           currentPiece = this.playerArray.get(currentPlayer).getAvailablePiece();
        } else if (!this.clickedTile.isVacant()) {
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
        
        int diceResult = dice.getDiceResult();
        
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

    private boolean setPieceInTile(Tile realTile, Piece movedPiece) {
        boolean success = false;
        UrTile urTile = (UrTile) realTile;
        System.out.println("realTile: " + realTile);
        
        UrTile urClickedTile = (UrTile) board.getTile(clickedTile.getRow(), clickedTile.getColumn());
        
        UrPiece myUrPiece = (UrPiece) movedPiece;
        UrPiece yourUrPiece;
        System.out.println("Vamo a ver si es Vacant");
        System.out.println("!urTile.isSafe()" + !urTile.isSafe());
        
        if (realTile.isVacant()) {
            System.out.println("Si es Vacant");
            myUrPiece.setInPlay();
            realTile.setPiece(movedPiece);
            success = true;
        } else if (realTile.getPiece().getColor().getRGB() != movedPiece.getColor().getRGB()
            && !urTile.isSafe())
        {
            System.out.println("NO vacant");
            this.pieceEaten.setTrue();
            realTile.setPiece(movedPiece);
            myUrPiece.setInPlay();
            yourUrPiece = (UrPiece) realTile.getPiece();
            yourUrPiece.setNotInPlay();
            this.clickedTile.removePiece();
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
    
    @Override
    public boolean unExecute() {
        return false;    
    }
    
    private void resetEatenValue() {
        pieceEaten.setFalse();
    }
}
