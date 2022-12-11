/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 * Manages the data from the game, for saving and uploading games.
 * @author Álvaro Miranda.
 */
abstract public class DataManager {
    /**
     * An instance of file manager to manage files.
     */
    protected FileManager mainManager;
    /**
     * Reference to the game board.
     */
    protected Board gameBoard;
    /**
     * Reference to the games players.
     */
    protected Player[] gamePlayers;
    /**
     * Method that compiles the management from the games attributes.
     */
    abstract public void execute();
    /**
     * Method to manage the access to the file that needs to be managed.
     */
    abstract protected void manageFile();
    /**
     * Method to group all of the boards attributes.
     */
    abstract protected void manageBoard();
    /**
     * Method to group all of the players attributes.
     */
    abstract protected void managePlayers();
}
