/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import javax.swing.JButton;
import model.DataManager;
import model.Piece;
import model.Player;
import model.Referee;
import model.Tile;

/**
 *
 * @author Mauricio Palma
 */
public class GameController {
        /**
    *  A Button that represents the new game button on GUI.
    */
    protected JButton startNewGameButton;
    /**
    *  A Button that represents the load game button on GUI.
    */
    protected JButton startLoadGameButton;
    /**
    *  A Button that represents the go back button on GUI, new game view.
    */
    protected JButton goBackToMainMenuFromNewGameButton;
    /**
    *  A Button that represents the continue button on GUI, new game view.
    */
    protected JButton goToMainGameFromNewGameButton;
    /**
    *  A Button that represents the go back button on GUI, load game view.
    */
    protected JButton goBackToMainMenuFromLoadGameButton;
    /**
    *  A Button that represents the continue button on GUI, load game view.
    */
    protected JButton goToMainGameFromLoadGameButton;
    /**
    *  A Button that represents the rules button on GUI, at main menu view.
    */
    protected JButton showRulesFromMainMenuButton;
    /**
    *  A Button that represents the rules button on GUI, at main game view.
    */
    protected JButton showRulesFromGameButton;
    /**
    *  A Button that represents the save and exit button on GUI.
    */
    protected JButton exitAndSaveButton;
    /**
    *  A Button that represents the throw dice button on GUI.
    */
    protected JButton throwDiceButton;
    /**
    * Object to call methods of the view manager and coordinate them with.
    * the referee object
    */
    protected ViewManager viewManager;
    /**
    * ArrayList used to store the information of every player playing the game.
    */ 
    protected ArrayList<Player> playerArray;

    /**
    * Integer that tracks which player is playing at a given time.
    */
    protected int currentPlayer;
    /**
    * Referee to manage the game.
    */
    protected Referee referee;
    /**
    * Serializer attribute
    */
    protected DataManager JSONdeserializer;
    /**
    * Deserializer attribute
    */
    protected DataManager JSONserializer;
    
    public GameController() {
        this.playerArray = new ArrayList<>();
        this.currentPlayer = 0; 
    }
    
    /**
     * Constructor for the referee using the references from the game elements.  
     */    
    protected void makeReferee(Player player, Piece piece, Tile tile) {
        this.referee = new Referee(player, piece, tile);

    }
}
