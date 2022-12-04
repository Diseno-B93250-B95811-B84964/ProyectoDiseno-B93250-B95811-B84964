/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.LoadGame;
import view.MainGame;
import view.MainMenu;
import view.NewGame;

/**
 *
 * @author Mauricio Palma
 */
public class ViewManager {
    MainMenu urMainMenu;
    LoadGame urLoadGame;
    NewGame urNewGame;
    MainGame urMainGame;
    //ArrayList<Player> playerArray;
    JFrame mainFrame;
    JPanel mainPanel;
    CardLayout cardLayout;
    
    
    public ViewManager() {
        try {
            this.urMainMenu = new MainMenu();
            this.urLoadGame = new LoadGame();
            this.urNewGame = new NewGame(1);
            this.urMainGame = new MainGame();
            //ArrayList<Player> playerArray = new ArrayList<Player>();
            mainFrame = new JFrame("Royal Game Of Ur");
            mainPanel = new JPanel();
            cardLayout = new CardLayout();
            mainPanel.setLayout(cardLayout);
        } catch (IOException ex) {
            Logger.getLogger(ViewManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getPlayers(){
    }
    public void setPlayers(){
    }
    public void getFileName(){
    }
    public void swapViewToLoadGame(){
        this.swapView("loadGame");
    }
    public void swapViewToMainMenu(){
        this.swapView("mainMenu");
    }
    public void swapViewToMainGame(){
        this.swapView("mainGame");
    }
    public void swapViewToNewGame(){
        this.swapView("newGame");
    }
    public void configViewFlow(){ // Jbutton, Card
    }
    public void updateMainGameView(){
    }
    public void manageCardLayout(){
        addComponentToPanel(mainPanel, urMainMenu, "mainMenu");
        addComponentToPanel(mainPanel, urNewGame, "newGame");
        addComponentToPanel(mainPanel, urLoadGame, "loadGame");
        addComponentToPanel(mainPanel, urMainGame, "mainGame");


        cardLayout.show(mainPanel, "mainMenu");
        mainFrame.add(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
 
        
    }
    
    public void getUserChoice(){
    }
    public void showRules(){
    }
    
    private  void addComponentToPanel(JPanel basePanel, JPanel newComponent ,String panelsName){
        basePanel.add(newComponent, panelsName);
        basePanel.revalidate();
        basePanel.repaint();
    }
    
    public JButton getStartNewGameButton(){
        return urMainMenu.getStartNewGameButton();
    }
    
    public JButton getStartLoadGameButton(){
        return urMainMenu.getLoadFormerGameButton();
    }
    
    public JButton getContinueButtonFromNewGame(){
        System.out.println("New game button has been passed!");
        return urNewGame.getContinueButton();
    }
    
    public JButton getGoBackButtonFromNewGame(){
        return urNewGame.getBackButton();
    }

    public JButton getContinueButtonFromLoadGame(){
        return urLoadGame.getContinueButton();
    }
    
    public JButton getGoBackFromLoadGame(){
        return urLoadGame.getBackButton();
    }
    
    private void swapView(String viewName){
        this.cardLayout.show(this.mainPanel, viewName);
    }
}
