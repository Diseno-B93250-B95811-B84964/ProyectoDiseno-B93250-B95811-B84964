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
import model.UrPiece;
import model.UrPlayer;

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
    * Object that is used to manipulate the dice accordingly in each game
    */
    private Dice dice;
    /**
    * Integer that tracks which player is playing at a given time
    */
    private int currentPlayer;
    /**
    * An array with the probabilities of each dice side.
    */
    private int[] diceProbabilities;
    /**
    * The amount of players in game.
    */
    private int playerAmount;  // TODO: USAR ESTOS AL CREAR REFEREE
    /**
    * The amount of pieces each player has.
    */
    private int pieceAmount;
    /**
    * The amount of rows in game board.
    */
    private int rowAmount;
    /**
    * The amount of columns in game board.
    */
    private int colAmount;
    /**
    * A matrix that contains the adjacents of each tile.
    */
    private ArrayList<ArrayList<Boolean>> adjacentMatrix;
    /**
    * A reference to an object that reads and manages files.
    */
    private FileManager fileManager;
    
    /**
     * Constructor method that uses templates to create a personalized viewManager.  
     */
    public UrGameController(){
        readGameData();
        try {
            SwingUtilities.invokeAndWait(() -> {
                this.viewManager = new ViewManager(UrMainGame::new, UrLoadGame::new, UrMainMenu::new, UrNewGame::new, ShowRules::new);
                this.playerArray = new ArrayList<>();
                this.dice = new Dice(diceProbabilities.length, diceProbabilities);
                this.manageButtons();
                this.currentPlayer = 1;
                
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(mainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Reads game data from file.  
     */
    private void readGameData() {
        // Reads from file
        fileManager = new FileManager();
        fileManager.loadFile("gameData.csv", "src/main/java/auxiliaryFiles/");
        ArrayList<String> stringArray = fileManager.getFileContents();
        
        setDiceProbabilities(stringArray.get(0));
        setPlayerAmount(stringArray.get(1));
        setBoardDimensions(stringArray.get(2));
        
        ArrayList<String> adjacentsArray = new ArrayList(stringArray.subList(3, stringArray.size()));
        setBoardAdjacentMatrix(adjacentsArray);
    }
    /**
     * Sets dice probabilities extracting them from a string.
     * @param row A file line.
     */
    private void setDiceProbabilities(String row) {
        String[] diceData = row.split(",");
        diceProbabilities = new int[diceData.length];
        for (int diceSide = 0; diceSide < diceData.length; diceSide++) {
            diceProbabilities[diceSide] = Integer.parseInt(diceData[diceSide]);
        }
    }
    /**
     * Sets amount of players and the amount of pieces each player has, extracting them from a string.
     * @param row A file line.
     */
    private void setPlayerAmount(String row) {
        String[] playerData = row.split(",");
        playerAmount = Integer.parseInt(playerData[0]);
        pieceAmount = Integer.parseInt(playerData[1]);
    }
    /**
     * Sets board dimensions, extracting them from a string.
     * @param row A file line.
     */
    private void setBoardDimensions(String row) {
        String[] boardData = row.split(",");
        rowAmount = Integer.parseInt(boardData[0]);
        colAmount = Integer.parseInt(boardData[1]);
    }
    /**
     * Sets the graph's adjacent matrix, extracting them from an array of strings.
     * @param adjacents An array of strings, each with a true or false value specifying if there is an adjacent there.
     */
    private void setBoardAdjacentMatrix(ArrayList<String> adjacents) {
        // Converts from ArrayList<String> to ArrayList<ArrayList<Boolean>>
        ArrayList<ArrayList<String>> stringMatrix = fileManager.splitArray(adjacents, ",");
        adjacentMatrix = fileManager.convertFromStringToBoolean(stringMatrix);
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
        
        /**
        * Creates new action listener.
        */
        public buttonAction() {
            refereeStub = new refereeStub();
            firstPlayer  = true;
            playerPlayed = true;
            winnerExists = false;
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
        private void manageStartNewGame(){
            String playerData = viewManager.getPlayerData();
            Piece urPiece = new UrPiece();
            if (playerData != null) {
                // Creates new player from data received
                String[] playerDataArray = playerData.split(",");
                Color playerColor = new Color(Integer.parseInt(playerDataArray[0]));
                String playerName = playerDataArray[1];
                //public UrPlayer(int amountPieces, Color color, String name, UrPiece pieceType);
                Player newPlayer = new UrPlayer(pieceAmount, playerColor, playerName, (UrPiece) urPiece); // TODO make a General Game Controller with templates
           
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
        * Method that throws a random dice and shows up its result.
        * @return dice result.
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
