/*
 * Issue #25 - Game Logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * Creates a game board using a graph to store the information.
 * @author Jimena Gdur.
 * @param <PieceType> Piece's child class
 */
public abstract class Player<PieceType extends Piece> extends GameObject {
    /**
     * Player's chosen color.
     */
    protected Color playerColor;
    /**
     * Player's given name.
     */
    protected String playerName;
    /**
     * Player's current score.
     */
    protected int playerScore;
    /**
     * Player's inventory which contains InventoryItems.
     */
    //protected Inventory playerInventory;
    /**
     * Amount of pieces player has.
     */
    protected int piecesAmount;
    /**
     * Stores all of the player's pieces.
     */
    protected ArrayList<PieceType> playerPieces;
    
    /**
     * Creates a new Player with an array of pieces.
     * @param supplier Supplier class that contains instance of piece's child.
     * @param amountPieces Amount of pieces the player has.
     * @param chosenColor Color player chose on respective screen.
     * @param name Player's name.
    */
    public Player(Supplier<PieceType> supplier, int amountPieces, Color chosenColor, String name) {
        this.piecesAmount = amountPieces;
        this.playerColor = chosenColor;
        this.playerName = name;
        initializePiecesArray(supplier);
    }
    /**
     * Initializes player array that contains al their pieces.
     * @param supplier Supplier class that contains instance of piece's child.
    */
    private void initializePiecesArray(Supplier<PieceType> supplier){
        this.playerPieces = new ArrayList<>(this.piecesAmount);
        for (int pieceIndex = 0; pieceIndex < this.piecesAmount; pieceIndex++) {
            this.playerPieces.set(pieceIndex, supplier.get());
        }
    }
    /**
     * Returns player's color.
     * @return Player's color.
     */
    public Color getColor() {
        return this.playerColor;
    }
    /**
     * Returns player's name.
     * @return Player's name.
     */
    public String getPlayerName() {
        return this.playerName;
    }
    /**
     * Returns player's score.
     * @return Player's score.
     */
    public int getPlayerScore() {
        return this.playerScore;
    }
    /**
     * Modifies player's score.
     * This method is abstract.
     */
    public abstract void modifyPlayerScore();
    /**
     * Consults player for a piece.
     * This method is abstract.
     * @return
     */
    public abstract PieceType getAvailablePiece();
    @Override
    public String toString() {
        String string = "";
        return string;
    }
}
