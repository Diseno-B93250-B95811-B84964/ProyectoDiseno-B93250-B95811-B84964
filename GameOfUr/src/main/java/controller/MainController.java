/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.LoadGameView;
import view.MainGameView;
import view.MainMenuView;
import view.SelectColorView;

/**
 *
 * @author mauup
 */
public class MainController {
            int currentPlayer = 0;
        
        public void startGame() {           
            GameController mainController = new GameController();
            MainMenuView mainMenu = mainController.getMainMenuView();
            MainGameView mainGame = mainController.getMainGameView();

            JFrame mainFrame = new JFrame("Royal Game Of Ur");
            JPanel panelCont = new JPanel();
            CardLayout card = new CardLayout();

            JButton startNewGameButton = mainMenu.getStartNewGameButton();
            JButton startFormerGameButton = mainMenu.getLoadFormerGameButton();

            panelCont.setLayout(card);

            addButtonToPanel(mainMenu, startNewGameButton);
            addButtonToPanel(mainMenu, startFormerGameButton);
            
            addToComponentToPanel(panelCont, mainMenu, "mainMenu");
            addToComponentToPanel(panelCont, mainGame, "mainGame");              
            
            addActionListenerToButton(card, panelCont, startNewGameButton, "colorView");       
            addActionListenerToButton(card, panelCont, startFormerGameButton, "loadGame");  
 
            setPlayersAttributesForNewGame(card, panelCont, mainController);
            setPlayersAttributesForFormerGame(card, panelCont, mainController);
            
            card.show(panelCont, "mainMenu");
            mainFrame.add(panelCont);
            mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            mainFrame.pack();
            mainFrame.setVisible(true);
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setResizable(false);  
    }
    
    private void setPlayersAttributesForFormerGame(CardLayout baseCard, JPanel basePanel, GameController main){
        FileChooserController fileController = new FileChooserController();
        LoadGameView loadGameView = fileController.getLoadGameView();
        JButton continueToPlayButton = loadGameView.getContinueButton();
        JButton goBackToMainMenuFromLoadGameView = loadGameView.getBackButton(); 
        addToComponentToPanel(basePanel, loadGameView, "loadGame");
        addActionListenerToButton(baseCard, basePanel, goBackToMainMenuFromLoadGameView, "mainMenu");       
        
        addCheckingDataForFormerGameActionListenerToButton(baseCard, basePanel, continueToPlayButton, "mainGame", fileController, main);  
    }
    
    private void addCheckingDataForFormerGameActionListenerToButton(CardLayout baseCard, JPanel basePanel, JButton button, String finalPanelName, FileChooserController loadController, GameController main){       
        button.addActionListener((ActionEvent arg0) -> {     
            checkFormerGameData(baseCard, basePanel,  finalPanelName,  loadController, main);
        });
    }
    
    private  void checkFormerGameData(CardLayout baseCard, JPanel basePanel, String finalPanelName, FileChooserController loadController, GameController main){
        ArrayList<String> fileArray = loadController.getFileAsStringArray();
        if (!fileArray.isEmpty()) {
            main.loadGame(fileArray);
            baseCard.show(basePanel, finalPanelName);
        }
    }
    
    private  void setPlayersAttributesForNewGame(CardLayout baseCard, JPanel basePanel, GameController main){
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
                addCheckingDataForNewGameActionListenerToButton(baseCard, basePanel, continueButton, "colorView"+(index+1), colorController, main);
            } else {
                addToComponentToPanel(basePanel, colorView, "colorView"+index);
                addCheckingDataForNewGameActionListenerToButton(baseCard, basePanel, continueButton, "mainGame", colorController, main);
            }
        }
    }
    
    private void addCheckingDataForNewGameActionListenerToButton(CardLayout baseCard, JPanel basePanel, JButton button, String finalPanelName, SelectColorController colorController, GameController main){       
        button.addActionListener((ActionEvent arg0) -> {     
            checkColorViewData(baseCard, basePanel,  finalPanelName,  colorController, main);
        });
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
