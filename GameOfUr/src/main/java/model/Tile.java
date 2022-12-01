/*
 * Issue #25 - Game Logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */

package model;

/**
 * Represents a game tile that belongs to a game board.
 * @author Jimena Gdur.
 */
abstract public class Tile
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
    * A boolean value that determines if tile currently has a piece placed in it.
    */
    protected boolean isVacant;
    
    /**
     * Creates a new Tile located in the given row and column.
     * @param tileRow The row in which the tile is located.
     * @param tileColumn The column in which the tile is located.
    */
    protected Tile(int tileRow, int tileColumn) {
        this.row = tileRow;
        this.column = tileColumn;
        this.piece = null;
        this.isVacant = true;
    }
    /**
     * Gets the row in which the tile is located.
     * @return this tile's row
    */
    protected int getRow() {
        return this.row;
    }
    /**
     * Gets the column in which the tile is located.
     * @return this tile's column
    */
    protected int getColumn() {
        return this.column;
    }
    /**
     * Determines if the tile is currently occupied by a piece.
     * @return value of isVacant.
    */
    protected boolean isVacant() {
        return this.isVacant;
    }
    /**
     * Sets given piece in current tile.
     * @param givenPiece The piece that is located in current tile.
    */
    protected void setPiece(Piece givenPiece) {
        this.piece = givenPiece;
        this.isVacant = false;
    }
    /**
     * Gets the piece placed in current tile.
     * @return piece stored in tile.
    */
    protected Piece getPiece() {
        return this.piece;
    }
    /**
     * Indicates piece has been moved from tile.
    */
    protected void removePiece() {
        this.piece = null;
        this.isVacant = true;
    }
}
