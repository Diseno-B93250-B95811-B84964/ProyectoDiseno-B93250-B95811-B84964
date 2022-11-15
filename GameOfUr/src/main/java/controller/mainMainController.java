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
import view.MainGameView;
import view.MainMenuView;
import view.SelectColorView;

/**
 *
 * @author mauup
 */
public class mainMainController {
        public void startGame() {
                 
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

        
        setPlayersColors(card, panelCont, mainController);

        card.show(panelCont, "mainMenu");
        mainFrame.add(panelCont);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        
        
    }
    
    private  void setPlayersColors(CardLayout baseCard, JPanel basePanel, MainGameController main){
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
                //addActionListenerToButton(baseCard, basePanel, continueButton, "colorView"+(index+1));
                addAnotherActionListenerToButton(baseCard, basePanel, continueButton, "colorView"+(index+1), colorController, main);
            } else {
                addToComponentToPanel(basePanel, colorView, "colorView"+index);
                //addActionListenerToButton(baseCard, basePanel, continueButton, "mainGame");
                addAnotherActionListenerToButton(baseCard, basePanel, continueButton, "mainGame", colorController, main);
            }
        }
    }
    
    private void addAnotherActionListenerToButton(CardLayout baseCard, JPanel basePanel, JButton button, String finalPanelName, SelectColorController colorController, MainGameController main){
        System.out.println("Hello from anotherAction");
       
        button.addActionListener((ActionEvent arg0) -> {
            
            checkColorViewData(baseCard, basePanel,  finalPanelName,  colorController, main);
        });
         // button.doClick();
    }
    
    private  void checkColorViewData(CardLayout baseCard, JPanel basePanel, String finalPanelName, SelectColorController colorController, MainGameController main){
        //SelectColorController.ContinueButtonListener a = new SelectColorController(1).ContinueButtonListener();
        //a.ContinueButtonListener;
        System.out.println("Controller get player name says: " + "[" + colorController.getPlayerName() + "]");
        if (!(colorController.getPlayerName().equals(""))) {
            System.out.println("It is not empty! it is: " + "[" + colorController.getPlayerName() + "]");
            if (!(("Enter player name").equals(colorController.getPlayerName()))) {
            System.out.println("It should be a valid name..." + "[" + colorController.getPlayerName() + "]");
            baseCard.show(basePanel, finalPanelName);
            main.setFirstPlayerName(colorController.getPlayerName());
            main.setSecondPlayerName(colorController.getPlayerName());
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