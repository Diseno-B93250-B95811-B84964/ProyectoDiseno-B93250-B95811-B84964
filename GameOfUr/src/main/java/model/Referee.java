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
import org.apache.commons.lang3.mutable.MutableBoolean;

/**
 * Game referee, manages game moves and game score.
 * @author Jimena Gdur.
 * @param <PlayerType>
 * @param <PieceType>
 * @param <TileType>
 */
public class Referee <
    PlayerType extends Player
    , PieceType extends Piece
    , TileType extends Tile>
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
    
    protected TileType tileType;
    
    protected Tile clickedTile;
    
    protected Tile nextTile;
    
    protected Dice gameDice;
    
    protected MutableBoolean pieceEaten;
    
    protected boolean playerScored;
    protected boolean isWinner;
    
    CommandInterface commandAddScore;
    CommandInterface commandValidateWinner;
    CommandInterface commandMovePiece;
    
    /**
     * Creates referee class.
     * @param rows Amount of rows in game board.
     * @param cols  Amount of columns in game board.
     * @param players Amount of players in game.
     * @param pieces Amount of pieces for each player.
     * @param tiles Amount of tiles in game board.
     * @param boolMatrix A matrix that allows the referee to determine possible routes.
     * @param playerType The specific type of player that will be playing
     * @param pieceType
     * @param tileType
     */
    public Referee(int rows, int cols, int players, int pieces, int tiles,
        ArrayList<ArrayList<Boolean>> boolMatrix, PlayerType playerType,
        PieceType pieceType, TileType tileType, int[] diceProbabilities)
    {
        this.amountRows = rows;
        this.amountCols = cols;
        this.playerAmount = players;
        this.pieceAmount = pieces;
        this.tileAmount = tiles;
        this.playerType = playerType;
        this.pieceType = pieceType;
        this.tileType = tileType;
        
        this.pieceEaten = new MutableBoolean(false);
        this.clickedTile = new Tile();
        this.nextTile = new Tile();
        this.gameRules = new Rules();
        this.gameDice = new Dice(diceProbabilities.length, diceProbabilities);
        
        try {
            createPlayers();
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Referee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        createBoard(boolMatrix);
        
        initializeCommands();
    }
    
    private void initializeCommands() {
        // commandAddScore
        commandAddScore = new CommandAddScore(this.playerArray, 0, this.nextTile);
        
        // commandValidateWinner
        commandValidateWinner = new CommandValidateWinner(this.playerArray, 0, this.pieceAmount);
        
        // commandMovePiece        
        commandMovePiece = new CommandMovePiece(this.gameBoard, this.playerArray, 0,
            this.gameDice, this.clickedTile, this.nextTile, this.pieceEaten);
    }
    
    /*
    /**
    * Creates players and stores them in playerArray.
    */
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
        String string = this.gameBoard.toString();
        string += this.playerArray.get(0).toString();
        return string;
    }
    
    /**
    * Creates players and stores them in playerArray.Abstract class that allows Referee child to manage implementation.
    * @return Returns a object of the specific player given through templates
    * @throws IllegalAccessException Exception that is thrown if object tries to access an invalid memory reference
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
                        .newInstance(7, Color.BLACK, "Migulito", this.pieceType); // TODO change magic values
        return newPlayer;
    }
    
    /**
     * Creates players and stores them in playerArray.
     * @param boolMatrix Matrix of booleans to reflect adjacent vertices
     */
    protected void createBoard(ArrayList<ArrayList<Boolean>> boolMatrix) {
        gameBoard = new Board(tileAmount, amountRows, amountCols, this.tileType);
        gameBoard.setAdjacentMatrix(boolMatrix);
    }    
    
    /**
     * Sets player information with given arrays.
     * @param playerNames All player names.
     * @param playerColors All player colors.
     * @return whether the operation was successful.
     */ 
    public boolean setPlayerInfo(ArrayList<String> playerNames, ArrayList<Color> playerColors) {
        boolean success = false;
        if (playerAmount == playerNames.size() && playerNames.size() == playerColors.size()) {
            for(int playerIndex = 0; playerIndex < playerAmount; playerIndex++) {
                playerArray.get(playerIndex).setColor(playerColors.get(playerIndex));
                playerArray.get(playerIndex).setName(playerNames.get(playerIndex));
            }
        }
        return success;
    }
    
    private void copyClickedTile(int x, int y) {
        Tile boardTile = this.gameBoard.getTile(x, y);
        
        this.clickedTile.setColumn(y);
        this.clickedTile.setRow(x);
        if (boardTile.getPiece() != null) {
            this.clickedTile.setPiece(boardTile.getPiece());
        }
    }
    
    /**
     * 
     */
    public boolean checkPlay(int x, int y) {
        boolean success = false;
        this.playerScored = false;
        this.isWinner = false;
        
        System.out.println("In check play");
        
        // Moves piece to tile if possible
        copyClickedTile(x, y);
        this.clickedTile = this.gameBoard.getTile(x, y);
        System.out.println("clickedTile: " + clickedTile.getRow() + ", " + clickedTile.getColumn());
        success = this.commandMovePiece.execute();
        
        System.out.println("After moving piece: " + this.nextTile);
        
        // possible tile vacio -1, -1
        // movePiece = possible tile lleno
        
        
        if (success == true) {
            // Validates if point can be given to player
            this.playerScored = this.commandAddScore.execute();

            // Checks for winner
            this.isWinner = this.commandValidateWinner.execute();
        }
        
        return success;
    }

    /**
     * Gets game rules from class Rules.
     * @return a list of rules.
     */
    
    public ArrayList<String> getGameRules() {
        return this.gameRules.getRules();
    }
    
    public boolean getPieceEaten() {
        return this.pieceEaten.booleanValue();
    }
    
    public boolean getIfScored() {
        return this.playerScored;
    }
    
    public boolean getIsWinner() {
        return this.isWinner;
    }
    
    public Tile getNextTile() {
        return this.nextTile;
    }
}
