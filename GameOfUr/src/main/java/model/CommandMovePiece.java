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
            //System.out.println("Current piece: " + currentPiece.getColor());
            if (currentPiece != null) {
                nextTile = (UrTile) getPossibleTile();
                if (nextTile != null) {
                    // Copies value from next tile to possibleTile
                    possibleTile.setRow(nextTile.getRow());
                    possibleTile.setColumn(nextTile.getColumn());
                    possibleTile.setPiece(currentPiece);
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
        if (clickedTile.isVacant() && clickedTile.getRow() == 4 && clickedTile.getColumn() != 1){
           currentPiece = this.playerArray.get(currentPlayer).getAvailablePiece();
        } else if (!clickedTile.isVacant()) {
            currentPiece = clickedTile.getPiece();
            System.out.println("CurrentPiece after getting it from board: " + currentPiece);

        }
        
        System.out.println("Inside getCurrentPiece: Current player is: " + currentPlayer);
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
        int currentRow = clickedTile.getRow();
        int currentCol = clickedTile.getColumn();
        
        int diceResult = dice.getDiceResult();
        
        Tile newTile = null;
        
        if (isInRange(currentRow, currentCol)
            && isPlayerColumn(currentCol) && diceResult != 0)
        {
            ArrayList<Tile> tileVertices = board.getVertices(currentRow, currentCol);
            while(diceResult > 0 && !tileVertices.isEmpty()) {
                System.out.println("TileVertices: " + tileVertices);
                
                for (Tile tile : tileVertices) {
                    currentCol = tile.getColumn();
                    currentRow = tile.getRow();
                    System.out.println("Tilevertices size is: " + tileVertices.size());
                    if (tileVertices.size() > 1) {
                        if (currentPlayer == 0) {
                            currentCol = 0;
                            currentRow = 7;
                            newTile = tile;
                        } else {
                            currentCol = 2;
                            currentRow = 7;
                            newTile = tile; 
                       }
                    } else {
                        newTile = tile; 
                    }

                    if (isPlayerColumn(currentCol)) {

                        System.out.println("Current col of for is: ["+currentCol+"]");
                        System.out.println("Current row of for is: ["+currentRow+"]");
                        
                        System.out.println("Printing new tile within commandMove" + newTile);
                        
                    }
                }
                diceResult--;
                tileVertices = board.getVertices(currentRow, currentCol);
                System.out.println("DiceResult after each WHILE is: " + diceResult);
                System.out.println("!TileVertices.IsEmpty after each While" + !tileVertices.isEmpty());
            }
        }
        
        return newTile;
    }

    private boolean setPieceInTile(Tile nexTile, Piece movedPiece) {
        boolean success = false;
        UrTile urNextTile = (UrTile) nexTile;
        System.out.println("realTile: " + urNextTile);
        //System.out.println("realTile: " + nexTile.getPiece().getColor());
                
        UrPiece myUrPiece = (UrPiece) movedPiece;
        UrPiece yourUrPiece;
        System.out.println("Vamo a ver si es Vacant");
        System.out.println("!urNextTile.isSafe()" + !urNextTile.isSafe());
        
        if (urNextTile.getPiece() != null) {
            System.out.println("NextTile color: " + urNextTile.getPiece().getColor());
            System.out.println("Moved piece color: " + myUrPiece.getColor());
            System.out.println("Current piece index of urNextTile is: " + urNextTile.getPiece().pieceIndex);
            System.out.println("Current piece index of movedPiece is: " + myUrPiece.pieceIndex);
            boolean a = urNextTile.getPiece().getColor().getRGB() != myUrPiece.getColor().getRGB();
            System.out.println("Printing weird !=" + a);
        }

        Tile tile = this.board.getTile(this.clickedTile.getRow(), this.clickedTile.getColumn());
        tile.removePiece();
        clickedTile.removePiece();
        System.out.println("ClickedTile piece after removing" + this.clickedTile);
        System.out.println("tile piece after removing" + tile);

        if (urNextTile.isVacant()) {
            System.out.println("Si es Vacant");
            myUrPiece.setInPlay();
            urNextTile.setPiece(myUrPiece);
            success = true;
        } else if (urNextTile.getPiece().getColor().getRGB() != myUrPiece.getColor().getRGB()
            && !urNextTile.isSafe())
        {
            System.out.println("NO vacant");
            this.pieceEaten.setTrue();
            urNextTile.setPiece(myUrPiece);
            myUrPiece.setInPlay();
            yourUrPiece = (UrPiece) urNextTile.getPiece();
            yourUrPiece.setNotInPlay();
            
            success = true;
        } 
        System.out.println("Printing movedPiece" + myUrPiece);
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
