/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.tempPlayer;
import view.MainGameView;
import view.MainMenuView;
import view.SelectColorView;

/**
 *
 * @author mauup
 */
public class MainController {
    
        tempPlayer playerArray [] = new tempPlayer[2];
        int currentPlayer = 0;
        
        public void startGame() {           
            GameController mainController = new GameController();
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


            setPlayersColors(card, panelCont, mainController);

            card.show(panelCont, "mainMenu");
            mainFrame.add(panelCont);
            mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            mainFrame.pack();
            mainFrame.setVisible(true);
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setResizable(false);  
    }
    
    private  void setPlayersColors(CardLayout baseCard, JPanel basePanel, GameController main){
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
                addAnotherActionListenerToButton(baseCard, basePanel, continueButton, "colorView"+(index+1), colorController, main);
            } else {
                addToComponentToPanel(basePanel, colorView, "colorView"+index);
                addAnotherActionListenerToButton(baseCard, basePanel, continueButton, "mainGame", colorController, main);
            }
        }
    }
    
    private void addAnotherActionListenerToButton(CardLayout baseCard, JPanel basePanel, JButton button, String finalPanelName, SelectColorController colorController, GameController main){       
        button.addActionListener((ActionEvent arg0) -> {     
            checkColorViewData(baseCard, basePanel,  finalPanelName,  colorController, main);
        });
         // button.doClick();
    }
    
    private  void checkColorViewData(CardLayout baseCard, JPanel basePanel, String finalPanelName, SelectColorController colorController, GameController main){
        if (!(colorController.getPlayerName().equals(""))) {
            if (!(("Enter player name").equals(colorController.getPlayerName()))
                &&  (colorController.getPlayerColor() != Color.WHITE)) {  
                if (currentPlayer == 0) {
                    main.setFirstPlayerName(colorController.getPlayerName());
                    main.setFirstPlayerColor(colorController.getPlayerColor());
                    currentPlayer+=1;
                } else {
                    main.setSecondPlayerName(colorController.getPlayerName());
                    main.setSecondPlayerColor(colorController.getPlayerColor());
                    currentPlayer-=1;
                }
                //colorController.hideButton(colorController.getPlayerColor());
                baseCard.show(basePanel, finalPanelName);
            }    
        }
    }
    
    private  void addActionListenerToButton(CardLayout baseCard, JPanel basePanel, JButton button, String finalPanelName){
        button.addActionListener((ActionEvent arg0) -> {
            baseCard.show(basePanel, finalPanelName);
        });
    }
    
    private  void addButtonToPanel(JPanel basePanel, JButton button){            
        basePanel.add(button);
        basePanel.revalidate();
        basePanel.repaint();
    }
    
    private  void addToComponentToPanel(JPanel basePanel, JPanel newComponent ,String panelsName){
        basePanel.add(newComponent, panelsName);
        basePanel.revalidate();
        basePanel.repaint();
    }

}
