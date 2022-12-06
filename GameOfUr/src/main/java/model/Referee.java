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
import java.util.function.Supplier;

/**
 * Game referee, manages game moves and game score.
 * @author Jimena Gdur.
 * @param <PlayerType> Player's child class.
 * @param <TileType> Tile's child class.
 */
public abstract class Referee<PlayerType extends Player, TileType extends Tile> {
    /**
     * Amount of rows in board.
     */
    private final int amountRows;
    /**
     * Amount of columns in board.
     */
    private final int amountCols;
    /**
     * Stores game board.
     */
    private Board gameBoard;
    /**
     * Amount of players in game.
     */
    private int playerAmount;
    /**
     * An array with all game players.
     */
    private ArrayList<PlayerType> playerArray;
    /**
     * An object that stores game rules.
     */
    private Rules gameRules;
    
    /**
     * Creates referee class.
     * @param rows Amount of rows in game board.
     * @param cols  Amount of columns in game board.
     * @param players Amount of players in game.
     * @param supplier Supplier class that contains instance of player's child.
     */
    public Referee(int rows, int cols, int players, Supplier<PlayerType> playerSupplier, Supplier<TileType> tileSupplier) {
        amountRows = rows;
        amountCols = cols;
        playerAmount = players;
        
        gameRules = new Rules();
        
        //createPlayers(supplier);
        createBoard();
    }
    /**
     * Creates players and stores them in playerArray.
     * @param supplier Supplier class that contains instance of player's child.
     */
    private void createPlayers(Supplier<PlayerType> supplier) {
        playerArray = new ArrayList<>(playerAmount);
        for (int playerIndex = 0; playerIndex < playerAmount; playerIndex++) {
            playerArray.add(playerIndex, supplier.get());
        }
    }
    /**
     * Creates players and stores them in playerArray.
     * @param supplier Supplier class that contains instance of player's child.
     */
    private void createBoard() {
        //gameBoard = new Board(TileType::new, );
        for (int playerIndex = 0; playerIndex < playerAmount; playerIndex++) {
            //playerArray.add(playerIndex, supplier.get());
        }
    }
    /**
     *
     * @param playerNames
     * @param playerColors
     */
    public void settingPlayerInfo(ArrayList<String> playerNames, ArrayList<Color> playerColors) {
        
    }
    
    /**
     * Gets game rules from class Rules.
     * @return a list of rules.
     */
    public ArrayList<String> getGameRules() {
        return this.gameRules.getRules();
    }
}
