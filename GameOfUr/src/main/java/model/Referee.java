/*
 * Issue #26 - Game Logic.
 * 
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package model;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Game referee, manages game moves and game score.
 * @author Jimena Gdur.
 */
public abstract class Referee
{
    /**
     * Amount of rows in board.
     */
    protected final int amountRows;
    /**
     * Amount of columns in board.
     */
    protected final int amountCols;
    /**
     * Amount of tiles in board.
     */
    protected final int tileAmount;
    /**
     * Stores game board.
     */
    protected Board gameBoard;
    /**
     * Amount of players in game.
     */
    protected final int playerAmount;
    /**
     * Amount of pieces a player has.
     */
    protected final int pieceAmount;
    /**
     * An array with all game players.
     */
    protected ArrayList<Player> playerArray;
    /**
     * An object that stores game rules.
     */
    protected final Rules gameRules;
    
    /**
     * Creates referee class.
     * @param rows Amount of rows in game board.
     * @param cols  Amount of columns in game board.
     * @param players Amount of players in game.
     * @param pieces Amount of pieces for each player.
     * @param tiles Amount of tiles in game board.
     * @param boolMatrix A matrix that allows the referee to determine possible routes.
     */
    public Referee(int rows, int cols, int players, int pieces, int tiles, ArrayList<ArrayList<Boolean>> boolMatrix)
    {
        amountRows = rows;
        amountCols = cols;
        playerAmount = players;
        pieceAmount = pieces;
        tileAmount = tiles;
        
        gameRules = new Rules();
        
        createPlayers();
        createBoard(boolMatrix);
    }
    /**
     * Creates players and stores them in playerArray.
     * Abstract class that allows Referee child to manage implementation.
     */
    protected abstract void createPlayers();
    /**
     * Creates players and stores them in playerArray.
     * @param boolMatrix 
     */
    protected abstract void createBoard(ArrayList<ArrayList<Boolean>> boolMatrix);    
    /**
     * Gets game rules from class Rules.
     * @return a list of rules.
     */
    public ArrayList<String> getGameRules() {
        return this.gameRules.getRules();
    }
    /**
     *
     * @param x Row in which tile is located.
     * @param y Column in which tile is located.
     * @param tileJumps Amount of jumps the tile has to make.
     * @return
     */
    /*private ArrayList<Integer> getTilesAdjacents(int x, int y, int tileJumps) {
        ArrayList<Integer> adjacents = new ArrayList<>();
        boolean foundAdjacent = false;
        
        if (x >= 0 && x < amountRows && y >= 0 && y < amountColumns) {
            int currentVertexIndex = getVertexIndexThroughXYCoordinates(x, y);
            //System.out.println("x: " + x + ", y: " + y);
            System.out.println("currentVertexIndex: " + currentVertexIndex);
            System.out.println("tileJumps: " + tileJumps);

            while(tileJumps > 1 && currentVertexIndex < verticesAmount) {
                foundAdjacent = false;
                for(int columnIndex = 0; columnIndex < vertices.size(); columnIndex++) {
                    if(foundAdjacent != true && graphAdjacentMatrix.get(currentVertexIndex).get(columnIndex) == true) {
                        currentVertexIndex = columnIndex;
                        foundAdjacent = true;
                        System.out.println("currentVertexIndex: " + currentVertexIndex);
                    }
                }
                tileJumps--;
            }
            System.out.println("Final currentVertexIndex: " + currentVertexIndex);
            for(int columnIndex = 0; columnIndex < vertices.size(); columnIndex++) {
                if(graphAdjacentMatrix.get(currentVertexIndex).get(columnIndex) == true) {
                    adjacents.add(columnIndex);
                }
            }
        }
        return adjacents;
    }*/
}
