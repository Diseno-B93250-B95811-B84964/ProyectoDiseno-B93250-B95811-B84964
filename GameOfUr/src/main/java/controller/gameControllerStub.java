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
    private JButton goBackToMainMenuFromNewGame;
    private JButton goToMainGameFromNewGame;
    private JButton goBackToMainMenuFromLoadGame;
    private JButton goToMainGameFromLoadGame;
    
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
        goBackToMainMenuFromNewGame = viewManager.getGoBackButtonFromNewGame();
        goToMainGameFromNewGame = viewManager.getContinueButtonFromNewGame();
        goBackToMainMenuFromLoadGame = viewManager.getGoBackFromLoadGame();
        goToMainGameFromLoadGame = viewManager.getContinueButtonFromLoadGame();
        
        startNewGameButton.addActionListener(new buttonAction());
        startLoadGameButton.addActionListener(new buttonAction());
        goBackToMainMenuFromNewGame.addActionListener(new buttonAction());
        goToMainGameFromNewGame.addActionListener(new buttonAction());
        goBackToMainMenuFromLoadGame.addActionListener(new buttonAction());
        goToMainGameFromLoadGame.addActionListener(new buttonAction());
    }
    
    class buttonAction implements ActionListener{
        
        boolean firstPlayer;
        
        public buttonAction() {
            firstPlayer  = true;
        }
        
        @Override
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            refereeStub refereeStub = new refereeStub();
            if (source == startNewGameButton) {
                refereeStub.setMessaage("Im working through: startNewGameButton" );
                System.out.println("Referee stubs says: " + refereeStub.getMessage());
                viewManager.swapViewToNewGame();
            } else if (source == startLoadGameButton) {
                refereeStub.setMessaage("Im working through: startLoadGameButton" );
                System.out.println("Referee stubs says: " + refereeStub.getMessage());
                viewManager.swapViewToLoadGame();
            } else if (source == goToMainGameFromNewGame) {
                System.out.println("First player es: " + firstPlayer);
                if (firstPlayer) {
                    refereeStub.setMessaage("Im working through: goToMainGameFromNewGame" );
                    System.out.println("Referee stubs says: " + refereeStub.getMessage());
                    viewManager.swapViewToNewGame();
                    firstPlayer = false;
                } else {
                    viewManager.swapViewToMainGame();
                }
            } else if (source == goBackToMainMenuFromLoadGame || source == goBackToMainMenuFromNewGame) {
                refereeStub.setMessaage("Im working through: goBackToMainMenuFromLoadGame" );
                System.out.println("Referee stubs says: " + refereeStub.getMessage());
                viewManager.swapViewToMainMenu();
            } 
        }
    }
}
