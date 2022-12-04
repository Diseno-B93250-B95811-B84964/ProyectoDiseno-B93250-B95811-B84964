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
        JFileChooser chooser = urLoadGame.getFileChooser();
        try {
            File file = new File(chooser.getSelectedFile().getAbsolutePath());
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
        catch (FileNotFoundException ex) {
            Logger.getLogger(ViewManager.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public void showRules(){
        //UrRulesModel rules = new UrRulesModel();
        JFrame frame = new JFrame("Rules");        
        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel.setLayout(layout);     
        final JLabel title = new JLabel();
        title.setFont(new Font("Century Schoolbook", 1, 36));
        //title.setText(rules.getRules()[0]);
        title.setText("Some serious rules over here...");
        panel.add(title);    
        /*for (int index = 1; index < rules.getLength(); index++) {
            final JLabel label = new JLabel();
            label.setFont(new Font("Century Schoolbook", 0, 18));
            label.setText(rules.getRules()[index]);
            panel.add(label);
        }*/
        frame.getContentPane().add(panel, BorderLayout.CENTER);   
        frame.setSize(600, 420);      
        frame.setLocationRelativeTo(null);  
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private  void addComponentToPanel(JPanel basePanel, JPanel newComponent ,String panelsName){
        basePanel.add(newComponent, panelsName);
        basePanel.revalidate();
        basePanel.repaint();
    }
    
    private void swapView(String viewName){
        this.cardLayout.show(this.mainPanel, viewName);
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
