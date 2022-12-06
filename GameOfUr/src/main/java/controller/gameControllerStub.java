/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

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

/**
 *
 * @author Mauricio Palma
 */

public class gameControllerStub {
    private JButton startNewGameButton;
    private JButton startLoadGameButton;
    private JButton goBackToMainMenuFromNewGameButton;
    private JButton goToMainGameFromNewGameButton;
    private JButton goBackToMainMenuFromLoadGameButton;
    private JButton goToMainGameFromLoadGameButton;
    private JButton showRulesFromMainMenuButton;
    private JButton showRulesFromGameButton;
    private JButton exitAndSaveButton;
    private JButton throwDiceButton;
    
    private ViewManager viewManager;
    private ArrayList<Player> playerArray;
    private Dice dice;
    
    private int currentPlayer;
    
    public gameControllerStub(){
        try {
            SwingUtilities.invokeAndWait(() -> {
                this.viewManager = new ViewManager();
                this.playerArray = new ArrayList<>();
                this.dice = new Dice();
                this.manageButtons();
                this.currentPlayer = 1;
                
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(mainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
    
    class buttonAction implements ActionListener{
        refereeStub refereeStub = new refereeStub();
        boolean firstPlayer;
        boolean playerPlayed;
        
        public buttonAction() {
            firstPlayer  = true;
            playerPlayed = true;
            viewManager.setIfPieceMoved(true);
        }
        
        private void manageContinueToNewGameButton(){
            viewManager.swapViewToNewGame();
        }
        
        private void manageContinueToLoadGameButton(){
            viewManager.swapViewToLoadGame();
        }
        
        private void manageStartNewGame(){
            Player player = viewManager.getPlayerData();
            int nextPlayerNumber = currentPlayer+1;
            if (firstPlayer) {
                System.out.println("Referee stubs says: " + refereeStub.getMessage());                
                if (player != null) {
                    playerArray.add(player);
                    firstPlayer = false;
                    viewManager.updateNewGameForNextPlayer(nextPlayerNumber);
                    viewManager.hideColors(player.getColor());
                    viewManager.resetColorChosen();
                    viewManager.swapViewToNewGame();
                }
            } else {
                if (player != null) {
                    playerArray.add(player);
                    viewManager.setPlayers(playerArray);
                    viewManager.swapViewToMainGame();
                }
            }
        }
        
        private void manageStartLoadGame(){
            System.out.println("Referee stubs says: " + refereeStub.getMessage());
            File file = viewManager.getFileNameToLoadGame();
            if (file != null) {
                // playerArray = referee.getPlayers();
                // viewManager.setPlayers(playerArray);
                viewManager.swapViewToMainGame();
            }
        }
        
        private void manageGoBackToMainMenu(){
            System.out.println("Referee stubs says: " + refereeStub.getMessage());
            viewManager.swapViewToMainMenu();
        }
        
        private void manageShowRules(){
            viewManager.showRules();
        }
        
        private void manageSaveAndExit(){
            // TODO implement this
            // serializer.saveState()
            System.exit(0);
        }
        
        private void managePlay(){
            playerPlayed = viewManager.getIfPieceMoved();
            if (playerPlayed) {
                System.out.println("Inside!");
                int result = throwDice();     
                currentPlayer++;
                viewManager.playMove(result, currentPlayer);
                System.out.println("Current player is: " + currentPlayer);
                currentPlayer = currentPlayer % playerArray.size();
            }
        }
        
        private int throwDice(){
            int diceResult = 0;
            dice.rollDice();
            diceResult = dice.getRollResult();
            return diceResult;
        }
        
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
