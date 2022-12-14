/*
 * Issue #26 - Game Logic.
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
 * Creates a game board using a graph to store the information.
 * @author Jimena Gdur.
 * @param <PieceType> Piece's child class.
 */
public abstract class Player
    <PieceType extends Piece> extends GameObject
{
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
    
   

    
    /**
     * Amount of pieces player has.
     */
    protected int piecesAmount;
    /**
     * Stores all of the player's pieces.
     */
    protected ArrayList<PieceType> pieces;
    /**
     * Generic data type that extends Piece model
     */
    protected PieceType pieceType;
    
    /**
    * Creates a new Player with their color and name.
    * @param amountPieces Amount of pieces the player has.
    * @param color Player's color
    * @param name Player's name.
    * @param pieceType The specific type of piece that will use player
   */
    public Player(int amountPieces, Color color, String name, PieceType pieceType) {
        
        this.piecesAmount = amountPieces;
        this.name = name;
        this.color = color;
        this.pieces = new ArrayList<>(this.piecesAmount);        
        this.pieceType = pieceType;
        initializePiecesArray();
    }

    public Player() {
        this.piecesAmount = 0;
        this.name = "";
        this.color = Color.WHITE;
        this.pieces = new ArrayList<>(this.piecesAmount);        
        this.pieceType = null;
        //initializePiecesArray();
    }

    /**
    * Initializes player array that contains all their pieces.
    */
    private void initializePiecesArray(){
        for (int pieceIndex = 0; pieceIndex < this.piecesAmount; pieceIndex++) {
            try {
                makeNewPiece(pieceIndex);
            } catch (IllegalAccessException | InstantiationException |
                    NoSuchMethodException | IllegalArgumentException | 
                    InvocationTargetException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Creates a new object of generic type PieceType and stores it into pieces arraylist.
     * @throws IllegalAccessException Exception that is thrown if object tries to access an invalidad memory reference
     * @throws InstantiationException Exception that is thrown if object cannot be instantiated
     * @throws NoSuchMethodException Exception that is thrown if method called does not exist
     * @throws IllegalArgumentException Exception that is thrown if arguments do not match requested method
     * @throws InvocationTargetException Exception that is thrown if target cannot be invoked
     */
    public void makeNewPiece(int index) throws IllegalAccessException,InstantiationException, 
            NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
        PieceType newPiece = (PieceType)pieceType.getClass()
                .getConstructor(Color.class, int.class)
                .newInstance(this.color,index);
        pieces.add(newPiece);
    }
    /**
     * Sets player's color with given Color.
     * @param chosenColor Player's color.
     */
    public void setColor(Color chosenColor) {
        this.color = chosenColor;
    }
    /**
     * Sets player's color with given Color.
     * @param chosenName Player's name.
     */
    public void setName(String chosenName) {
        this.name = chosenName;
    }
    /**
     * Sets player's score.
     * @param currentScore Player's score.
     */
    public void setScore(int currentScore) {
        this.score = currentScore;
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
     * Returns player's amount of pieces.
     * @return Player's amount of pieces.
     */
    public int getPiecesAmount() {
        return this.piecesAmount;
    }
    /**
     * Returns player's amount of pieces.
     * @return Player's amount of pieces.
     */
    public ArrayList<PieceType> getPiecesArray() {
        return this.pieces;
    }
    /**
     * Modifies player's score.
     * This method is abstract.
     */
    public abstract void modifyScore();
    /**
     * Consults player for a piece.
     * This method is abstract.
     * @return An available piece 
     */
    public abstract PieceType getAvailablePiece();
    /**
     * Converts Player into a string.
     * This method is abstract.
     * @return A string representing a player.
     */
    @Override
    public String toString() {
        String string =
            "color: " + color.getRGB() + "\n" +
            "name: " + name + "\n" +
            "score: " + score + "\n" +
            "pieces:\n";
        for (int pieceIndex = 0; pieceIndex < this.piecesAmount; pieceIndex++) {
            string += pieceIndex + ": " + this.pieces.get(pieceIndex) + "\n";
        }
        return string;
    }
    public abstract void setColorPiece(Color color);
}
