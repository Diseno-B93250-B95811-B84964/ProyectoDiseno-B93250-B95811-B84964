/*
 * Issue #25 - Graph logic.
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
    * Determines if tile is safe.
    */
    protected boolean isSafe;
    
    /**
     * Creates a new Tile located in -1, -1.
    */
    public UrTile() {
        super();
        this.isSafe = false;
    }
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
    /**
     * Sets isSafe value as true.
    */
    public void setAsSafe() {
        this.isSafe = true;
    }
    /**
     * Sets tile isSafe value depending on the parameter.
     * @param isSafe indicates if the tile is safe.
    */
    public void setIsSafe(boolean isSafe) {
        this.isSafe = isSafe;
    }
    /**
     * Converts Tile into a string.
     * Made for the Royal Game of Ur.
     * @return a string representing a tile
     */
    @Override
    public String toString() {
        String string  = 
            super.toString() + ", " +
            "isSafe: " + this.isSafe;
        return string;
    }
}
