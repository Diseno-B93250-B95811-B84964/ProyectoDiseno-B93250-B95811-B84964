/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.LoadGame;
import view.MainGame;
import view.MainMenu;
import view.NewGame;
import view.ShowRules;

/**
 *
 * @author Mauricio Palma
 */
public class ViewManager {
    MainMenu urMainMenu;
    LoadGame urLoadGame;
    NewGame urNewGame;
    MainGame urMainGame;
    ShowRules urShowRules;
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
            this.urShowRules = new ShowRules();
            //ArrayList<Player> playerArray = new ArrayList<Player>();
            manageCardLayout();
        } catch (IOException ex) {
            Logger.getLogger(ViewManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getPlayers(){
        
    }
    public void setPlayers(){
        
    }
    public File getFileNameToLoadGame(){
        JFileChooser fileChooser = urLoadGame.getFileChooser();
        File file = null;
        if (fileChooser != null) {
            var selectedFile = fileChooser.getSelectedFile();
            if (selectedFile != null) {
                try {
                    file = new File(selectedFile.getAbsolutePath());
                    /*Delete this*/
                    Scanner lineScanner = new Scanner(file);
                    if (lineScanner != null) {
                        while (lineScanner.hasNextLine()) {
                        System.out.println("Content is: " + lineScanner.nextLine());
                        }
                        /*
                        while (lineScanner.hasNextLine()) {
                            String[] lineAsArray = lineScanner.nextLine().split(",");         
                            fileAsStringArray.add(removeQuotationMarks(lineAsArray));*/
                        }
                    } 
                    /*Until here*/
                catch (FileNotFoundException ex) {
                    Logger.getLogger(ViewManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return file;
    }
    
    public void updateMainGameView(){
    }
    
    /*Swap methods */
    
    private void swapView(String viewName){
        this.cardLayout.show(this.mainPanel, viewName);
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
    
    /*CardLayout configuration */
    
    public void manageCardLayout(){
        mainFrame = new JFrame("Royal Game Of Ur");
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
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
    
    private  void addComponentToPanel(JPanel basePanel, JPanel newComponent ,String panelsName){
        basePanel.add(newComponent, panelsName);
        basePanel.revalidate();
        basePanel.repaint();
    }
    
    public void showRules(){
    //public void showRules(String[] rules){
        urShowRules.showRules();
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
    
    public JButton getRulesButtonFromMainMenu(){
        return urMainMenu.getShowRulesButton();
    }
    
    public JButton getRulesButtonFromGame(){
        return urMainGame.getShowRulesButton();
    }
}
