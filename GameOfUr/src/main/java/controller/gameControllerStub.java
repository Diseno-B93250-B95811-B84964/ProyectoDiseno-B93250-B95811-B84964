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
 * @author mauup
 */
public class gameControllerStub {
    private JButton startNewGameButton;
    private JButton startLoadGameButton;
    private JButton goBackToMainMenuFromNewGame;
    private JButton goToMainGameFromNewGame;
    private JButton goBackToMainMenuFromLoadGame;
    private JButton goToMainGameFromLoadGame;
    
    private ViewManager view;
    
    public gameControllerStub(){
        try {
            SwingUtilities.invokeAndWait(() -> {
                view = new ViewManager();
                view.manageCardLayout();
                manageButtons();
                
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(mainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void manageButtons(){
        startNewGameButton = view.getStartNewGameButton();
        startLoadGameButton = view.getStartLoadGameButton();
        goBackToMainMenuFromNewGame = view.getGoBackToMainMenuFromNewGameButton();
        goToMainGameFromNewGame = view.getGoToMainGameFromLoadGameButton();
        goBackToMainMenuFromLoadGame = view.getGoBackToMainMenuFromLoadGameButton();
        goToMainGameFromLoadGame = view.getGoToMainGameFromNewGameButton();
        
        startNewGameButton.addActionListener(new buttonAction());
        startLoadGameButton.addActionListener(new buttonAction());
        goBackToMainMenuFromNewGame.addActionListener(new buttonAction());
        goToMainGameFromNewGame.addActionListener(new buttonAction());
        goBackToMainMenuFromLoadGame.addActionListener(new buttonAction());
        goToMainGameFromLoadGame.addActionListener(new buttonAction());
    }
    
    class buttonAction implements ActionListener{
        public buttonAction() {
            System.out.println("Im alive!");
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            refereeStub refereeStub = new refereeStub();
            if (source == startNewGameButton) {
                refereeStub.setMessaage("Im working through: startNewGameButton" );
                System.out.println("Referee stubs says: " + refereeStub.getMessage());
                //view.swapViewToMainMenu();
            } else if (source == startLoadGameButton) {
                refereeStub.setMessaage("Im working through: startLoadGameButton" );
                System.out.println("Referee stubs says: " + refereeStub.getMessage());
                //view.swapViewToLoadGame();
            } else if (source == goBackToMainMenuFromLoadGame) {
                refereeStub.setMessaage("Im working through: goBackToMainMenuFromLoadGame" );
                System.out.println("Referee stubs says: " + refereeStub.getMessage());
                //view.swapViewToMainMenu();
            }
        }
    }
}
