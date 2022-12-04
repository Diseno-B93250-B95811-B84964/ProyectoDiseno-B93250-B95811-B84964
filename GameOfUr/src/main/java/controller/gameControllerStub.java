/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

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
    
    private ViewManager viewManager;
    
    public gameControllerStub(){
        try {
            SwingUtilities.invokeAndWait(() -> {
                viewManager = new ViewManager();
                viewManager.manageCardLayout();
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
        
        startNewGameButton.addActionListener(new buttonAction());
        startLoadGameButton.addActionListener(new buttonAction());
        goBackToMainMenuFromNewGameButton.addActionListener(new buttonAction());
        goToMainGameFromNewGameButton.addActionListener(new buttonAction());
        goBackToMainMenuFromLoadGameButton.addActionListener(new buttonAction());
        goToMainGameFromLoadGameButton.addActionListener(new buttonAction());
        showRulesFromMainMenuButton.addActionListener(new buttonAction());
        showRulesFromGameButton.addActionListener(new buttonAction());
    }
    
    class buttonAction implements ActionListener{
        refereeStub refereeStub = new refereeStub();
        boolean firstPlayer;
        
        public buttonAction() {
            firstPlayer  = true;
        }
        
        private void manageContinueToNewGameButton(){
            refereeStub.setMessaage("Im working through: startNewGameButton" );
            System.out.println("Referee stubs says: " + refereeStub.getMessage());
            viewManager.swapViewToNewGame();
        }
        
        private void manageContinueToLoadGameButton(){
            refereeStub.setMessaage("Im working through: startLoadGameButton" );
            System.out.println("Referee stubs says: " + refereeStub.getMessage());
            viewManager.swapViewToLoadGame();
        }
        
        private void manageStartNewGame(){
            System.out.println("First player es: " + firstPlayer);
            if (firstPlayer) {
                refereeStub.setMessaage("Im working through: goToMainGameFromNewGame" );
                System.out.println("Referee stubs says: " + refereeStub.getMessage());
                viewManager.swapViewToNewGame();
                firstPlayer = false;
            } else {
                viewManager.swapViewToMainGame();
            }
        }
        
        private void manageStartLoadGame(){
            refereeStub.setMessaage("Im working through: goToMainGameFromLoadGame" );
            System.out.println("Referee stubs says: " + refereeStub.getMessage());
            viewManager.getFileName();
            viewManager.swapViewToNewGame();
        }
        
        private void manageGoBackToMainMenu(){
            refereeStub.setMessaage("Im working through: goBackToMainMenuFromLoadGame" );
            System.out.println("Referee stubs says: " + refereeStub.getMessage());
            viewManager.swapViewToMainMenu();
        }
        
        private void manageShowRules(){
            viewManager.showRules();
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
            }
        }
    }
}
