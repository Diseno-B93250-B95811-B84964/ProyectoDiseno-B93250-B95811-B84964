/*
 * Issue #25 - Graph logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */

package model;

/**
 * Represents a game tile that belongs to a game board.
 * @author Jimena Gdur.
 */
abstract public class Tile extends GameObject
{
    /**
    * A tile's row position.
    */
    protected int row;
    /**
    * A tile's column position.
    */
    protected int column;
    /**
    * A possible tile that can be placed on the tile.
    */
    protected Piece piece;
    /**
    * Determines if tile currently has a piece placed in it.
    */
    protected boolean isVacant;
    
    /**
     * Creates a new Tile located in -1,-1.
    */
    public Tile() {
        this.row = -1;
        this.column = -1;
        this.piece = null;
        this.isVacant = true;
    }
    /**
     * Creates a new Tile located in the given row and column.
     * @param tileRow The row in which the tile is located.
     * @param tileColumn The column in which the tile is located.
    */
    public Tile(int tileRow, int tileColumn) {
        this.row = tileRow;
        this.column = tileColumn;
        this.piece = null;
        this.isVacant = true;
    }
    /**
     * Gets the row in which the tile is located.
     * @return this tile's row
    */
    public int getRow() {
        return this.row;
    }
    /**
     * Gets the column in which the tile is located.
     * @return this tile's column
    */
    public int getColumn() {
        return this.column;
    }
    /**
     * Sets the row in which the tile is located.
     * @param givenRow Given row in which tile is placed
    */
    public void setRow(int givenRow) {
        this.row = givenRow;
    }
    /**
     * Sets the column in which the tile is located.
     * @param givenColumn Given column in which tile is placed
    */
    public void setColumn(int givenColumn) {
        this.column = givenColumn;
    }
    /**
     * Determines if the tile is currently occupied by a piece.
     * @return value of isVacant.
    */
    public boolean isVacant() {
        return this.isVacant;
    }
    /**
     * Sets given piece in current tile.
     * @param givenPiece The piece that is located in current tile.
    */
    public void setPiece(Piece givenPiece) {
        this.piece = givenPiece;
        this.isVacant = false;
    }
    /**
     * Gets the piece placed in current tile.
     * @return piece stored in tile.
    */
    public Piece getPiece() {
        return this.piece;
    }
    /**
     * Indicates piece has been moved from tile.
    */
    public void removePiece() {
        this.piece = null;
        this.isVacant = true;
    }
    /**
     * Converts Tile into a string.
     * @return a string representing a tile
     */
    @Override
    public String toString() {
        String string  = 
            "row: " + this.row + ", " + 
            "column: " + this.column + ", " +
            "isVacant: " + this.isVacant + ", " +
            "piece: " + this.piece; // By printing type piece it calls toString method
        return string;
    }
}
