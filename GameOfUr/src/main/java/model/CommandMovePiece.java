/*
 * Issue #26 - Game logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package model;

import java.util.ArrayList;

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
    
    private boolean pieceEaten;

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
     */
    public CommandMovePiece(Board board, ArrayList<Player> playerArray,
        int currentPlayer, Dice dice, Tile clickedTile, Tile possibleTile,
        int[] playerColumns, int sharedColumn, boolean pieceEaten)
    {
        this.board = board;
        this.playerArray = playerArray;
        this.currentPlayer = currentPlayer;
        this.dice = dice;
        this.clickedTile = clickedTile;
        this.possibleTile = possibleTile;
        this.playerColumns = playerColumns;
        this.sharedColumn = sharedColumn;
        this.pieceEaten = pieceEaten;
    }
     
    @Override
    public boolean execute() {
        boolean success = false;
        
        if (isPlayerColumn(clickedTile.getColumn())) {
            System.out.println("Is player column");
            Piece currentPiece = getCurrentPiece();
            if (currentPiece != null) {
                System.out.println("Current piece: " + currentPiece);
                possibleTile = getPossibleTile();
                if (possibleTile != null) {
                    System.out.println("Possible tile is: " + possibleTile);
                    setPieceInTile(currentPiece);
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
        
        int diceResult = 3;
        
        int index = 1; // TODO : DELETE, USED FOR PRINT
        
        Tile newTile = null;
        
        if (isInRange(currentRow, currentCol)
            && isPlayerColumn(currentCol) && diceResult != 0)
        {
            System.out.println("Is in range");
            ArrayList<Tile> tileVertices = board.getVertices(currentRow, currentCol);
            System.out.println("tileVertices: " + tileVertices + "\n");
            

            while(diceResult > 0 && !tileVertices.isEmpty()) {
                System.out.println("tileVertices" + index++ + ": " + tileVertices);
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

    private void setPieceInTile(Piece movedPiece) {
        UrTile urTile = (UrTile) possibleTile;
        UrPiece myUrPiece = (UrPiece) movedPiece;
        UrPiece yourUrPiece;
        if (possibleTile.isVacant()) {
            possibleTile.setPiece(movedPiece);
        } else if (possibleTile.getPiece().getColor() != movedPiece.getColor()
            && !urTile.isSafe())
        {
            pieceEaten = true;
            System.out.println("pieceEaten: " + pieceEaten);
            possibleTile.setPiece(movedPiece);
            myUrPiece.setInPlay();
            yourUrPiece = (UrPiece) possibleTile.getPiece();
            yourUrPiece.setNotInPlay();
        } else {
            System.out.println("ES MIA");
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
    
}
