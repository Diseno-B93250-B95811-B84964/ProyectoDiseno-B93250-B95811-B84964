/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import model.Dice;
import model.Player;
import model.UrPiece;
import model.UrPlayer;

import view.UrLoadGame;
import view.UrMainGame;
import view.UrMainMenu;
import view.UrNewGame;
import view.ShowRules;
/**
 *
 * @author Mauricio Palma
 */

/**
 * Creates a general controller of the game to coordinate
 * Referee and view manager.
 */

public class UrGameController {
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
    * Object that is used to manipulate the dice accordingly in each game
    */
    private Dice dice;
    
    /**
    * Integer that tracks which player is playing at a given time
    */
    private int currentPlayer;
    
    /**
     * Constructor method that uses templates to create a personalized viewManager.  
     */
    public UrGameController(){
        float[] probabilities = { 20, 20, 20, 20, 20 };
        try {
            SwingUtilities.invokeAndWait(() -> {
                this.viewManager = new ViewManager(UrMainGame::new, UrLoadGame::new, UrMainMenu::new, UrNewGame::new, ShowRules::new);
                this.playerArray = new ArrayList<>();
                this.dice = new Dice(5, probabilities);
                this.manageButtons();
                this.currentPlayer = 1;
                
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(mainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Method that manages each button parameter of UrGameController to the actual GUI button
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
        * Boolean to know if the current player is the first player
        */
        boolean firstPlayer;
        /**
        * Boolean to know if a player played its move before moving on
        */
        boolean playerPlayed;
        /**
        * Boolean to know if there has been a winner
        */
        boolean winnerExists;
        
        /**
        * Constructor method 
        */
        public buttonAction() {
            refereeStub = new refereeStub();
            firstPlayer  = true;
            playerPlayed = true;
            winnerExists = false;
            viewManager.setIfPieceMoved(true);
        }
        
        /**
        * Method that coordinates how to react when a new game is chosen
        */
        private void manageContinueToNewGameButton(){
            viewManager.swapViewToNewGame();
        }
        
        /**
        * Method that coordinates how to react when a game is loading from a previous match
        */
        private void manageContinueToLoadGameButton(){
            viewManager.swapViewToLoadGame();
        }
        
        // TODO: reaed file with game data
        
        
        /**
        * Method that coordinates what to do when a new game starts
        */
        private void manageStartNewGame(){
            String playerData = viewManager.getPlayerData();
            if (playerData != null) {
                // Creates new player from data received
                String[] playerDataArray = playerData.split(",");
                Color playerColor = new Color(Integer.parseInt(playerDataArray[0]));
                String playerName = playerDataArray[1];
                // 7 NEEDS TO BE CHANGED TO # OF PIECES FROM FILE.
                Player newPlayer = new UrPlayer(UrPiece::new, 7, playerColor, playerName);
           
                System.out.println("Referee stubs says: " + refereeStub.getMessage());
                int nextPlayerNumber = currentPlayer+1;
                
                if (firstPlayer) {
                    playerArray.add(newPlayer);
                    firstPlayer = false;
                    viewManager.updateNewGameForNextPlayer(nextPlayerNumber);
                    viewManager.hideColors(newPlayer.getColor());
                    viewManager.resetColorChosen();
                    viewManager.swapViewToNewGame();
                } else {
                    playerArray.add(newPlayer);
                    viewManager.setPlayers(playerArray);
                    viewManager.swapViewToMainGame();
                }
            }
        }
        
        /**
        * Method that coordinates how to start a game that has been load from a former match
        */
        private void manageStartLoadGame(){
            System.out.println("Referee stubs says: " + refereeStub.getMessage());
            File file = viewManager.getFileNameToLoadGame();
            if (file != null) {
                // playerArray = referee.getPlayers();
                // viewManager.setPlayers(playerArray);
                viewManager.swapViewToMainGame();
            }
        }
        
        /**
        * Method that coordinates how to go back to the main menu
        */
        private void manageGoBackToMainMenu(){
            System.out.println("Referee stubs says: " + refereeStub.getMessage());
            viewManager.swapViewToMainMenu();
        }
        
        /**
        * Method that coordinates how to show games rules
        */ 
        private void manageShowRules(){
            viewManager.showRules();
        }
        /**
        * Method that coordinates how to save current game state 
        */ 
        private void manageSaveAndExit(){
            // TODO implement this
            // serializer.saveState()
            System.exit(0);
        }
        /**
        * Method that coordinates how each player can interact when they can move a piece
        */  
        private void managePlay(){
            boolean winner = checkIfWinner();
            if (!winner) {
                playerPlayed = viewManager.getIfPieceMoved();
                if (playerPlayed) {
                    System.out.println("Inside!");
                    int result = throwDice();     
                    currentPlayer++;
                    viewManager.playMove(result, currentPlayer, playerArray.get(currentPlayer-1).getColor());
                    System.out.println("Current player is: " + currentPlayer);
                    currentPlayer = currentPlayer % playerArray.size();
                    System.out.println("Priting selected tile...: ");
                    int row = viewManager.getClickedRow();
                    int column = viewManager.getClickedColum();
                    System.out.println("Row is: " + Integer.toString(row));
                    System.out.println("Column is: " + Integer.toHexString(column));
                }
            }
        }
        /**
        * Method that checks if a player won the match
        * @return whether there is a winner.
        */  
        private boolean checkIfWinner(){
            //if (playerArray.get(currentPlayer).getScore() == 7) {
               // winnerExists = true;
            //}
            return winnerExists;
        }
        /**
        * Method that throws a random dice and shows up its result
        */    
        private int throwDice(){
            int diceResult = 0;
            diceResult = dice.throwDice() - 1;
            return diceResult;
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
