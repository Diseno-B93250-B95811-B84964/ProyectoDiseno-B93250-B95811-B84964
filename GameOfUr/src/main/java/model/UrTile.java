/*
 * Issue #25 - Game Logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */

package model;

/**
 * Made for the Royal Game of Ur.
 * @author Jimena Gdur.
 */
public class UrTile extends Tile
{
    /**
    * A boolean value that determines if tile is safe.
    */
    protected boolean isSafe;
    
    /**
     * Creates a new Tile located in the given row and column.
     * @param tileRow The row in which the tile is located.
     * @param tileColumn The column in which the tile is located.
     * @param isTileSafe If current tile is safe.
    */
    public UrTile(int tileRow, int tileColumn, boolean isTileSafe) {
        super(tileRow, tileColumn);
        this.isSafe = isTileSafe;
    }
    /**
     * Determines if current tile is safe.
     * @return value of isSafe.
    */
    public boolean isSafe() {
        return this.isSafe;
    }
    
    public String toString() {
        String string  = this.row + ", " + this.getColumn()+ ", " + this.isSafe();
        return string;
    }
}
