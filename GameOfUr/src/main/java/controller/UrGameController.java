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
import java.util.ArrayList;
import model.Board;

import model.JSONDeserializer;
import model.JSONSerializer;
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
public class UrGameController extends GameController
{
    
    /**
     * Constructor method that uses templates to create a personalized viewManager.  
     */
    public UrGameController(){     
        this.viewManager = new ViewManager(UrMainGame::new, UrLoadGame::new, UrMainMenu::new, UrNewGame::new, ShowRules::new);
        this.playerArray = new ArrayList<>();
        this.currentPlayer = 0;
        makeReferee();
        makeSafeTiles();
        makeSerializers();
        this.manageButtons();
    }
    
    /**
    * Makes seriales
    */
    public void makeSerializers(){
        ArrayList<Player> myPlayerArray = referee.getPlayerArray();
        Board myBoard = referee.getBoard();
        Player[] myPlayerAsArray = myPlayerArray.toArray(new Player[2]);
        JSONserializer = new JSONSerializer(myBoard,myPlayerAsArray);
        JSONdeserializer = new JSONDeserializer(myBoard,myPlayerAsArray);
    }
    
    /**
     * Constructor for the referee using the references from the game elements.  
     */    
    private void makeReferee(){
        Player urPlayer = new UrPlayer();
        Piece urPiece = new UrPiece(0);
        Tile urTile = new UrTile();
        this.referee = new Referee(urPlayer, urPiece, urTile);

    }
    /**
     * Initialize safe tiles.  
     */
    private void makeSafeTiles(){
        UrTile safeTile1 = (UrTile) referee.getTile(0, 0);
        safeTile1.setAsSafe();
        UrTile safeTile2 = (UrTile) referee.getTile(0, 2);
        safeTile2.setAsSafe();
        UrTile safeTile3 = (UrTile) referee.getTile(6, 0);
        safeTile3.setAsSafe();
        UrTile safeTile4 = (UrTile) referee.getTile(6, 2);
        safeTile4.setAsSafe();
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
    * Inner Action Listener class to react accordingly user inputs on GUI.
    */
    class buttonAction implements ActionListener{
   
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
        
        boolean selectedPiece;
        /**
        * Creates new action listener.
        */
        public buttonAction() {
            firstPlayer  = true;
            playerPlayed = false;
            winnerExists = false;
            selectedPiece = false;
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
            String file = viewManager.getFileNameToLoadGame();
            ArrayList<Player> myPlayerArray = referee.getPlayerArray();
            Player[] myPlayerAsArray = myPlayerArray.toArray(new Player[2]);
            if (file != null) {
                JSONdeserializer.execute();
                playerArray = referee.getPlayerArray();
                viewManager.setPlayers(playerArray);
                searchForPieces(myPlayerAsArray);
                viewManager.loadFormerGame(myPlayerAsArray);
                viewManager.swapViewToMainGame();
                currentPlayer = currentPlayer % playerArray.size();
                
            }
        }
        /**
        * Coordinates how to go back to the main menu.
        */
        private void manageGoBackToMainMenu(){
            viewManager.swapViewToMainMenu();
        }
        /**
        * Coordinates how to show games rules.
        */ 
        private void manageShowRules(){
            ArrayList<String> rules = referee.getGameRules();
            viewManager.showRules(rules);
        }
        /**
        * Coordinates how to save current game state.
        */ 
        private void manageSaveAndExit(){
            JSONserializer.execute();
            System.exit(0);
        }
        /**
        * Coordinates how each player can interact when they can move a piece.
        */  
        private void managePlay(){
            boolean winner = checkIfWinner();
            if (!winner) {
                if (!selectedPiece) {
                    manageThrowDice();
                } else{
                    manageSelectedTile();
                }
            }
        }
        /**
        * Manages the throw of the dice in the game flow.
        */  
        public void manageThrowDice(){
            int result = referee.throwDice();
            viewManager.playMove(result, currentPlayer+1, playerArray.get(currentPlayer).getColor());
            if (result > 0) {
                selectedPiece = true;
                viewManager.changeThrowDiceButtonText("CONTINUE");
            } else {
                updateCurrentPlayer();
            }
        }
        /**
        * Manages the changes made when a tile is selected to make a move.
        */  
        public void manageSelectedTile(){
            selectedPiece = false;
            playerPlayed = viewManager.getIfPieceMoved();
            if (playerPlayed) {
                int formerRow = viewManager.getClickedRow();
                int formerColumn = viewManager.getClickedColum();
                
                boolean canMove = referee.checkPlay(formerRow, formerColumn);
                if (canMove) {       
                    Tile nextTile = referee.getNextTile();
                    viewManager.setNextTilePosition(nextTile.getRow(), nextTile.getColumn());
                    updateGUI(formerRow, formerColumn);  
                    
                    if(referee.getPieceEaten()) {
                        updateCurrentPlayer();
                        viewManager.desactivatePiece(playerArray.get(currentPlayer).getColor());
                        updateCurrentPlayer();
                    }
                    
                    if (referee.getIfScored()) {
                        viewManager.addScoreToPlayer(playerArray.get(currentPlayer).getColor());
                    }
                    
                    if (referee.getIsWinner()) {
                        viewManager.declareWinner(currentPlayer+1);
                    }
                } else {
                    viewManager.resetBackground(formerRow, formerColumn);
                }

                updateCurrentPlayer();
                viewManager.changeThrowDiceButtonText("THROW DICE");
            }
        }
        /**
        * Changes turns between players.
        */  
        private void updateCurrentPlayer(){
            currentPlayer++;
            currentPlayer = currentPlayer%playerArray.size();
        }
        /**
        * Updates the users view.
        */  
        private void updateGUI(int formerX, int formerY){
            int nextRow = viewManager.getNextRowPosition();
            int nextColumn = viewManager.getNextColumnPosition();
            viewManager.movePiece(formerX, formerY, nextRow, nextColumn);
        }
        
        /**
        * Checks if a player won the match.
        * @return whether there is a winner.
        */  
        private boolean checkIfWinner(){
            if (referee.getIsWinner()) {
               winnerExists = true;
            }
            return winnerExists;
        }

        private void searchForPieces(Player[] myPlayerAsArray){
            Board board = referee.getBoard();
            UrTile tile = null;
            int playerIndex = -1;
            int totalSize = board.getAmountRows() * board.getAmountColumns();
            for (int index = 0; index < totalSize; index++) {
                tile = (UrTile)board.getTile(index);
                System.out.println("Index of urGameController" + index);
                if (tile.getPiece() != null) {
                    playerIndex = viewManager.checkColorMatchForFormerGame(tile.getPiece().getColor(), myPlayerAsArray);
                    viewManager.desactivatePiecesForAFormerMatch(myPlayerAsArray,playerIndex, tile); 
                }
            }
        }
        
        /**
        * Method that checks which button was clicked and acts accordingly.
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
