/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.UrDiceModel;
import model.UrPieceModel; 
import model.tempPlayer;
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
                 
        MainGameController mainController = new MainGameController();
        MainMenuView mainMenu = mainController.getMainMenuView();
        MainGameView mainGame = mainController.getMainGameView();
        
        JFrame mainFrame = new JFrame("Royal Game Of Ur");
        JPanel panelCont = new JPanel();
        CardLayout card = new CardLayout();

        JButton startNewGameButton = mainMenu.getStartNewGameButton();

        panelCont.setLayout(card);

        addButtonToPanel(mainMenu, startNewGameButton);

        addToComponentToPanel(panelCont, mainMenu, "mainMenu");
        addToComponentToPanel(panelCont, mainGame, "mainGame");              

        addActionListenerToButton(card, panelCont, startNewGameButton, "colorView");       

        
        setPlayersColors(card, panelCont);

        card.show(panelCont, "mainMenu");
        mainFrame.add(panelCont);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
    }
    
    private static void setPlayersColors(CardLayout baseCard, JPanel basePanel){
        int maxPlayers = 2;
        for (int index = 0; index < maxPlayers; index++) {
            SelectColorController colorController = new SelectColorController(index+1);
            SelectColorView colorView = colorController.getView();
            colorController.start();
            JButton goBackButton = colorView.getBackButton();
            JButton continueButton = colorView.getContinueButton();
            addButtonToPanel(colorView, goBackButton);
            addButtonToPanel(colorView, continueButton);
            addActionListenerToButton(baseCard, basePanel, goBackButton, "mainMenu");
            if (index==0) {
                addToComponentToPanel(basePanel, colorView, "colorView");
                addActionListenerToButton(baseCard, basePanel, continueButton, "colorView"+(index+1));
            } else {
                addToComponentToPanel(basePanel, colorView, "colorView"+index);
                addActionListenerToButton(baseCard, basePanel, continueButton, "mainGame");
            }
            System.out.println("Going to print color controller data: ");
            String name = colorController.getPlayerName();
            Color playerColor = colorController.getPlayerColor();
            System.out.println("Name is: " + name);
            System.out.println("Player color is: " + playerColor);
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
