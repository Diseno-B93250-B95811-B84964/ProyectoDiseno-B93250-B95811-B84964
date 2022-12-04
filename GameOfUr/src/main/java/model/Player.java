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
    protected Color color;
    /**
     * Player's given name.
     */
    protected String name;
    /**
     * Player's current score.
     */
    protected int score;
    /**
     * Player's inventory which contains InventoryItems.
     */
    //protected Inventory inventory;
    /**
     * Amount of pieces player has.
     */
    protected int piecesAmount;
    /**
     * Stores all of the player's pieces.
     */
    protected ArrayList<PieceType> pieces;
    
    /**
     * Creates a new Player with an array of pieces.
     * @param supplier Supplier class that contains instance of piece's child.
     * @param amountPieces Amount of pieces the player has.
     * @param chosenColor Color player chose on respective screen.
     * @param name Player's name.
    */
    public Player(Supplier<PieceType> supplier, int amountPieces, Color chosenColor, String name) {
        this.piecesAmount = amountPieces;
        this.color = chosenColor;
        this.name = name;
        initializePiecesArray(supplier);
    }
    /**
     * Initializes player array that contains al their pieces.
     * @param supplier Supplier class that contains instance of piece's child.
    */
    private void initializePiecesArray(Supplier<PieceType> supplier){
        this.pieces = new ArrayList<>(this.piecesAmount);
        for (int pieceIndex = 0; pieceIndex < this.piecesAmount; pieceIndex++) {
            this.pieces.set(pieceIndex, supplier.get());
        }
    }
    /**
     * Returns player's color.
     * @return Player's color.
     */
    public Color getColor() {
        return this.color;
    }
    /**
     * Returns player's name.
     * @return Player's name.
     */
    public String getName() {
        return this.name;
    }
    /**
     * Returns player's score.
     * @return Player's score.
     */
    public int getScore() {
        return this.score;
    }
    /**
     * Modifies player's score.
     * This method is abstract.
     */
    public abstract void modifyScore();
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
