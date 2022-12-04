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
    
    private ViewManager viewManager;
    private ArrayList<Player> playerArray;
    
    public gameControllerStub(){
        try {
            SwingUtilities.invokeAndWait(() -> {
                viewManager = new ViewManager();
                playerArray = new ArrayList<>();
                manageButtons();
                
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
        
        startNewGameButton.addActionListener(new buttonAction());
        startLoadGameButton.addActionListener(new buttonAction());
        goBackToMainMenuFromNewGameButton.addActionListener(new buttonAction());
        goToMainGameFromNewGameButton.addActionListener(new buttonAction());
        goBackToMainMenuFromLoadGameButton.addActionListener(new buttonAction());
        goToMainGameFromLoadGameButton.addActionListener(new buttonAction());
        showRulesFromMainMenuButton.addActionListener(new buttonAction());
        showRulesFromGameButton.addActionListener(new buttonAction());
        exitAndSaveButton.addActionListener(new buttonAction());
    }
    
    class buttonAction implements ActionListener{
        refereeStub refereeStub = new refereeStub();
        boolean firstPlayer;
        
        public buttonAction() {
            firstPlayer  = true;
        }
        
        private void manageContinueToNewGameButton(){
            System.out.println("Referee stubs says: " + refereeStub.getMessage());
            viewManager.swapViewToNewGame();
        }
        
        private void manageContinueToLoadGameButton(){
            System.out.println("Referee stubs says: " + refereeStub.getMessage());
            viewManager.swapViewToLoadGame();
        }
        
        private void manageStartNewGame(){
            Player player = viewManager.getPlayerData();
            if (firstPlayer) {
                System.out.println("Referee stubs says: " + refereeStub.getMessage());                
                if (player != null) {
                    playerArray.add(player);
                    firstPlayer = false;
                    viewManager.updateNewGameForNextPlayer();
                    viewManager.hideColors(player.getColor());
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
            }
        }
    }
}
