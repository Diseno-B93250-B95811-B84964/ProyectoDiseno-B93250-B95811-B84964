/*
 * Issue #26 - Game Logic.
 * 
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package model;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Game referee, manages game moves and game score.
 * @author Jimena Gdur.
 * @param <PlayerType>
 * @param <PieceType>
 */
public abstract class Referee <PlayerType extends Player, PieceType extends Piece>
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
    
    protected PlayerType playerType;
    
    protected PieceType pieceType;
    
    /**
     * Creates referee class.
     * @param rows Amount of rows in game board.
     * @param cols  Amount of columns in game board.
     * @param players Amount of players in game.
     * @param pieces Amount of pieces for each player.
     * @param tiles Amount of tiles in game board.
     * @param boolMatrix A matrix that allows the referee to determine possible routes.
     * @param playerType The specific type of player that will be playing
     */
    public Referee(int rows, int cols, int players, int pieces, int tiles, ArrayList<ArrayList<Boolean>> boolMatrix, PlayerType playerType)
    {
        this.amountRows = rows;
        this.amountCols = cols;
        this.playerAmount = players;
        this.pieceAmount = pieces;
        this.tileAmount = tiles;
        this.playerType = playerType;
        
        gameRules = new Rules();
        
        try {
            createPlayers();
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Referee.class.getName()).log(Level.SEVERE, null, ex);
        }
        createBoard(boolMatrix);
    }
    
    /*TODO delete this constructor*/
    public Referee(int players, int pieces, PlayerType playerType, PieceType pieceType)
    {
        this.playerAmount = players;
        this.pieceAmount = pieces;
        this.playerType = playerType;
        this.pieceType = pieceType;
        
        try {
            createPlayers();
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Referee.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.amountRows = 0;
        this.amountCols = 0;
        this.tileAmount = 0;
        this.gameRules = null;
    }
    
    

    protected void createPlayers() {
        playerArray = new ArrayList<>(playerAmount);
        for (int playerIndex = 0; playerIndex < playerAmount; playerIndex++) {
            try {
                playerArray.add(makeNewPlayers());
            } catch (IllegalAccessException | InstantiationException | 
                    NoSuchMethodException | IllegalArgumentException | 
                    InvocationTargetException ex) {
                Logger.getLogger(Referee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /*TODO Delete this method*/
    public String getPlayerString(){
        return this.playerArray.get(0).toString();
    }
    
    /**
    * Creates players and stores them in playerArray.Abstract class that allows Referee child to manage implementation.
    * @return Returns a object of the specific player given through templates
    * @throws IllegalAccessException Exception that is thrown if object tries to access an invalidad memory reference
    * @throws InstantiationException Exception that is thrown if object cannot be instantiated
    * @throws NoSuchMethodException Exception that is thrown if method called does not exist
    * @throws IllegalArgumentException Exception that is thrown if arguments do not match requested method
    * @throws InvocationTargetException Exception that is thrown if target cannot be invoked
    */
    protected PlayerType makeNewPlayers() throws IllegalAccessException,InstantiationException,
            NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
        PlayerType newPlayer = (PlayerType)
                playerType.getClass()
                        .getConstructor(int.class, Color.class, String.class, this.pieceType.getClass())
                        .newInstance(7, Color.WHITE, "Migulito", this.pieceType); // TODO change magic values
        return newPlayer;
    }
    
    /**
     * Creates players and stores them in playerArray.
     * @param boolMatrix 
     */
    protected abstract void createBoard(ArrayList<ArrayList<Boolean>> boolMatrix);    
    /**
     * Validates player's intended move, and moves piece there if it is valid.
     * @param playerId Indicates which player to validate.
     * @param x The row of the tile the player selected.
     * @param y The column of the tile the player selected.
     * @param moves Amount of moves the dice selected.
     * @return whether the move is valid.
     */
    protected abstract boolean validateMove(int playerId, int x, int y, int moves); 
    /**
     * Validates player's score taking into consideration their last move.
     * @param playerId Indicates which player to validate.
     * @param lastMoveRow Row of tile in which player set their piece.
     * @param lastMoveCol Column of tile in which player set their piece.
     * @return whether a point was given to the player.
     */
    protected abstract boolean validateScore(int playerId, int lastMoveRow, int lastMoveCol);
    /**
     * Validates the status of all players and determines if someone has won.
     * @return the id of the player that has won the game.
     */
    protected abstract int validateScore(); // TODO check if it's what was intended
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
    private ArrayList<Integer> getTilesAdjacents(int x, int y, int tileJumps) {
        ArrayList<Integer> adjacents = new ArrayList<>();
        
        /*if (x >= 0 && x < amountRows && y >= 0 && y < amountCols) {
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
        }*/
        return adjacents;
    }
}
