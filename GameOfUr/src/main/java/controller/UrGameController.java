/*
 * Issue #27 - View Manager.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import model.Dice;
import model.FileManager;
import model.Piece;
import model.Player;
import model.Referee;
import model.Tile;
import model.UrPiece;
import model.UrPlayer;
import model.UrTile;

import view.UrLoadGame;
import view.UrMainGame;
import view.UrMainMenu;
import view.UrNewGame;
import view.ShowRules;

/**
 * Creates a general controller of the game to coordinate
 * Referee and view manager.
 * @author Mauricio Palma.
 */
public class UrGameController
{
    /**
    *  A Button that represents the new game button on GUI
    */
    private JButton startNewGameButton;
    /**
    *  A Button that represents the load game button on GUI
    */
    private JButton startLoadGameButton;
    /**
    *  A Button that represents the go back button on GUI, new game view
    */
    private JButton goBackToMainMenuFromNewGameButton;
    /**
    *  A Button that represents the continue button on GUI, new game view
    */
    private JButton goToMainGameFromNewGameButton;
    /**
    *  A Button that represents the go back button on GUI, load game view
    */
    private JButton goBackToMainMenuFromLoadGameButton;
    /**
    *  A Button that represents the continue button on GUI, load game view
    */
    private JButton goToMainGameFromLoadGameButton;
    /**
    *  A Button that represents the rules button on GUI, at main menu view
    */
    private JButton showRulesFromMainMenuButton;
    /**
    *  A Button that represents the rules button on GUI, at main game view
    */
    private JButton showRulesFromGameButton;
    /**
    *  A Button that represents the save and exit button on GUI
    */
    private JButton exitAndSaveButton;
    /**
    *  A Button that represents the throw dice button on GUI
    */
    private JButton throwDiceButton;
    /**
    * Object to call methods of the view manager and coordinate them with
    * the referee object
    */
    private ViewManager viewManager;
    /**
    * ArrayList used to store the information of every player playing the game
    */ 
    private ArrayList<Player> playerArray;

    /**
    * Integer that tracks which player is playing at a given time
    */
    private int currentPlayer;

    private Referee<UrPlayer, UrPiece, UrTile> referee;
    
    /**
     * Constructor method that uses templates to create a personalized viewManager.  
     */
    public UrGameController(){     
        this.viewManager = new ViewManager(UrMainGame::new, UrLoadGame::new, UrMainMenu::new, UrNewGame::new, ShowRules::new);
        this.playerArray = new ArrayList<>(); // Recibe, NO crea
        this.currentPlayer = 0;
        makeReferee();
        this.manageButtons();
    }
    
    private void makeReferee(){
        Player urPlayer = new UrPlayer();
        Piece urPiece = new UrPiece();
        Tile urTile = new UrTile();
        this.referee = new Referee(urPlayer, urPiece, urTile);

    }

    /**
     * Manages each button parameter of UrGameController to the actual GUI button
    */
    private void manageButtons(){
        startNewGameButton = viewManager.getStartNewGameButton();
        startLoadGameButton = viewManager.getStartLoadGameButton();
        goBackToMainMenuFromNewGameButton = viewManager.getGoBackButtonFromNewGame();
        goToMainGameFromNewGameButton = viewManager.getContinueButtonFromNewGame();
        goBackToMainMenuFromLoadGameButton = viewManager.getGoBackFromLoadGame();
        goToMainGameFromLoadGameButton = viewManager.getContinueButtonFromLoadGame();
        showRulesFromMainMenuButton = viewManager.getRulesButtonFromGame();
        showRulesFromGameButton = viewManager.getRulesButtonFromMainMenu();
        exitAndSaveButton = viewManager.getExitAndSave();
        throwDiceButton = viewManager.getThrowDiceButton();
        
        startNewGameButton.addActionListener(new buttonAction());
        startLoadGameButton.addActionListener(new buttonAction());
        goBackToMainMenuFromNewGameButton.addActionListener(new buttonAction());
        goToMainGameFromNewGameButton.addActionListener(new buttonAction());
        goBackToMainMenuFromLoadGameButton.addActionListener(new buttonAction());
        goToMainGameFromLoadGameButton.addActionListener(new buttonAction());
        showRulesFromMainMenuButton.addActionListener(new buttonAction());
        showRulesFromGameButton.addActionListener(new buttonAction());
        exitAndSaveButton.addActionListener(new buttonAction());
        throwDiceButton.addActionListener(new buttonAction());
    }
    
    /**
    * Inner Action Listener class to react accordingly user inputs on GUI
    */
    class buttonAction implements ActionListener{
        /**
        * Referee object used to coordinate them with the view manager object
        * Referee manipulates the board logic and updates the game status
        */
        refereeStub refereeStub;
        /**
        * Determines if the current player is the first player.
        */
        boolean firstPlayer;
        /**
        * Determines if a player has played its move, before moving on.
        */
        boolean playerPlayed;
        /**
        * Boolean to know if there has been a winner.
        */
        boolean winnerExists;
        
        boolean firstPlayerThrow;
        /**
        * Creates new action listener.
        */
        public buttonAction() {
            refereeStub = new refereeStub();
            firstPlayer  = true;
            playerPlayed = false;
            winnerExists = false;
            firstPlayerThrow = true;
            viewManager.setIfPieceMoved(true);
        }
        /**
        * Coordinates how to react when a new game is chosen.
        */
        private void manageContinueToNewGameButton(){
            viewManager.swapViewToNewGame();
        }
        /**
        * Coordinates how to react when a game is loading from a previous match.
        */
        private void manageContinueToLoadGameButton(){
            viewManager.swapViewToLoadGame();
        }
        /**
        * Coordinates what to do when a new game starts.
        */
        private void manageStartNewGame() {
            String playerData = viewManager.getPlayerData();
            if (playerData != null) {
                // Creates new player from data received
                String[] playerDataArray = playerData.split(",");
                Color playerColor = new Color(Integer.parseInt(playerDataArray[0]));
                String playerName = playerDataArray[1];
                referee.setPlayerInfo(playerName, playerColor, currentPlayer);
                currentPlayer += 1;
                int nextPlayerNumber = currentPlayer+1;
                
                if (firstPlayer) {
                    
                    firstPlayer = false;
                    viewManager.updateNewGameForNextPlayer(nextPlayerNumber);
                    viewManager.hideColors(playerColor);
                    viewManager.resetColorChosen();
                    viewManager.swapViewToNewGame();
                } else {
                    playerArray = referee.getPlayerArray();
                    viewManager.setPlayers(playerArray);
                    viewManager.swapViewToMainGame();
                    currentPlayer = currentPlayer % playerArray.size();
                }
                
            }
        }
        /**
        * Coordinates how to start a game that has been load from a former match.
        */
        private void manageStartLoadGame(){
            System.out.println("Referee stubs says: " + refereeStub.getMessage());
            String file = viewManager.getFileNameToLoadGame();
            if (file != null) {
                // playerArray = referee.getPlayers();
                // viewManager.setPlayers(playerArray);
                viewManager.swapViewToMainGame();
            }
        }
        /**
        * Coordinates how to go back to the main menu.
        */
        private void manageGoBackToMainMenu(){
            System.out.println("Referee stubs says: " + refereeStub.getMessage());
            viewManager.swapViewToMainMenu();
        }
        /**
        * Coordinates how to show games rules.
        */ 
        private void manageShowRules(){
            viewManager.showRules();
        }
        /**
        * Coordinates how to save current game state.
        */ 
        private void manageSaveAndExit(){
            // TODO implement this
            // serializer.saveState()
            System.exit(0);
        }
        /**
        * Coordinates how each player can interact when they can move a piece.
        */  
        private void managePlay(){
            boolean winner = checkIfWinner();
            if (!winner) {
                if (firstPlayerThrow) {
                    manageFirstPlayerPlay();
                }
                playerPlayed = viewManager.getIfPieceMoved();
                System.out.println("PlayerPlayed value within urGameController: " + playerPlayed);
                if (playerPlayed) {
                    int result = referee.throwDice()-1;
                    
                    viewManager.playMove(result, currentPlayer+1, playerArray.get(currentPlayer).getColor());
                    currentPlayer = currentPlayer % playerArray.size();
                    int row = viewManager.getClickedRow();
                    int column = viewManager.getClickedColum();

                    
                    System.out.println("ClickedRow is: " + Integer.toString(row));
                    System.out.println("ClickedColumn is: " + Integer.toString(column));
                    
                    referee.checkPlay(row, column);
                    Tile nextTile = referee.getNextTile();
                    System.out.println("NextTileRow: " + nextTile.getRow());
                    System.out.println("NextTileColumn: " + nextTile.getColumn());
                    
                    currentPlayer++;
                    currentPlayer = currentPlayer%playerArray.size();
                }
            }
        }
        
        public void manageFirstPlayerPlay(){
            firstPlayerThrow = false;
            int result = referee.throwDice()-1;                    
            viewManager.playMove(result, currentPlayer+1, playerArray.get(currentPlayer).getColor());
            currentPlayer = currentPlayer % playerArray.size();
            int row = viewManager.getClickedRow();
            int column = viewManager.getClickedColum();

            System.out.println("/************/");
            System.out.println("ClickedRow is: " + 4);
            System.out.println("ClickedColumn is: " + 0);
            System.out.println("/************/");
            
            referee.checkPlay(4, 0);
            Tile nextTile = referee.getNextTile();
            System.out.println("/************/");
            System.out.println("NextTileRow: " + nextTile.getRow());
            System.out.println("NextTileColumn: " + nextTile.getColumn());
            System.out.println("/************/");
            
            currentPlayer++;
            currentPlayer = currentPlayer%playerArray.size();
        }
        /**
        * Checks if a player won the match.
        * @return whether there is a winner.
        */  
        private boolean checkIfWinner(){
            //if (playerArray.get(currentPlayer).getScore() == 7) {
               // winnerExists = true;
            //}
            return winnerExists;
        }

        /**
        * Method that checks which button was clicked and acts accordingly
        */   
        @Override
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            
            if (source == startNewGameButton) {
                manageContinueToNewGameButton();
            } else if (source == startLoadGameButton) {
                manageContinueToLoadGameButton();
            } else if (source == goToMainGameFromNewGameButton) {
                manageStartNewGame();
            } else if (source == goToMainGameFromLoadGameButton){
                manageStartLoadGame();
            } else if (source == goBackToMainMenuFromLoadGameButton || source == goBackToMainMenuFromNewGameButton) {
                manageGoBackToMainMenu();
            } else if (source == showRulesFromMainMenuButton || source == showRulesFromGameButton) {
                manageShowRules();
            } else if (source == exitAndSaveButton) {
                manageSaveAndExit();
            } else if (source == throwDiceButton) {
                managePlay();
            }
        }
    }
}
