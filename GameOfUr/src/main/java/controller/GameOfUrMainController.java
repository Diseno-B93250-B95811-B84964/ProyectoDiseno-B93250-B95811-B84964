/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.UrDiceModel;
import model.UrPieceModel; 
import view.MainGameView;
import view.MainMenuView;
import view.MainMenuViewOld;
import view.SelectColorView;
import view.UrDiceView;

public class GameOfUrMainController {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameOfUrMainController::startGame);
    }
    
    /**
    *
    * @author Mauricio Palma, Alvaro Miranda
    */
    
    
    private static void startGame() {
            
        try {
            MainMenuView mainMenu = new MainMenuView();
            MainGameView mainGame = new MainGameView();
            SelectColorView colorView = new SelectColorView();

            MainGameController mainController = new MainGameController(mainGame, mainMenu);
            SelectColorController colorController = new SelectColorController(colorView);
            colorController.start();
            
            JFrame mainFrame = new JFrame("CardLayout Trials");
            JPanel panelCont = new JPanel();
            CardLayout card = new CardLayout();

            JButton startNewGameButton = mainMenu.getStartNewGameButton();
            JButton goBackFromColorViewToMainMenu = colorView.getBackButton();
            JButton continueFromColorViewToMainGame = colorView.getContinueButton();
            
            panelCont.setLayout(card);
            
            addButtonToPanel(mainMenu, startNewGameButton);
            addButtonToPanel(colorView, goBackFromColorViewToMainMenu);
            addButtonToPanel(colorView, continueFromColorViewToMainGame);
            
            addToComponentToPanel(panelCont, mainMenu, "mainMenu");
            addToComponentToPanel(panelCont, mainGame, "mainGame");     
            addToComponentToPanel(panelCont, colorView, "colorView");

            addActionListenerToButton(card, panelCont, startNewGameButton, "colorView");       
            addActionListenerToButton(card, panelCont, goBackFromColorViewToMainMenu, "mainMenu"); 
            addActionListenerToButton(card, panelCont, continueFromColorViewToMainGame, "mainGame");
            
            card.show(panelCont, "mainMenu");
            mainFrame.add(panelCont);
            mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            mainFrame.pack();
            mainFrame.setVisible(true);
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setResizable(false);
        }
        catch(IOException e) {
            System.out.println("Images not found! Please check images path");
        }
    }
    
    private static void addActionListenerToButton(CardLayout baseCard, JPanel basePanel, JButton button, String finalPanelName){
        button.addActionListener((ActionEvent arg0) -> {
            baseCard.show(basePanel, finalPanelName);
        });
    }
    
    private static void addButtonToPanel(JPanel basePanel, JButton button){            
        basePanel.add(button);
        basePanel.revalidate();
        basePanel.repaint();
    }
    
    private static void addToComponentToPanel(JPanel basePanel, JPanel newComponent ,String panelsName){
        basePanel.add(newComponent, panelsName);
        basePanel.revalidate();
        basePanel.repaint();
    }

}
