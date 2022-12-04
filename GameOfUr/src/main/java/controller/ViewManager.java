/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.JTextField;
import model.Player;
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
    private MainMenu urMainMenu;
    private LoadGame urLoadGame;
    private NewGame urNewGame;
    private MainGame urMainGame;
    private ShowRules urShowRules;
    private Player player; // TODO delete it and make it just Player?
    private JFrame mainFrame;
    private JPanel mainPanel;
    private CardLayout cardLayout; // TODO add an integer saying "current player starting at 1?"
    private int currentPlayer;
    
    public ViewManager() {
        try {
            this.urMainMenu = new MainMenu();
            this.urLoadGame = new LoadGame();
            this.urNewGame = new NewGame(1);
            this.urMainGame = new MainGame();
            this.urShowRules = new ShowRules();
            
            this.urNewGame.addTextFieldFocusistener(new SetColorClickListener());
            
            currentPlayer = 1;
            manageCardLayout();
        } catch (IOException ex) {
            Logger.getLogger(ViewManager.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public void showRules(){
    //public void showRules(String[] rules){
        urShowRules.showRules();
    }
    
    public Player getPlayerData(){
        Color playerColor = urNewGame.getPlayerColor();
        String playerName = urNewGame.getPlayerName();
        player = null;
        System.out.println("Color: [" + playerColor+"]");
        System.out.println("Nombre: [" + playerName+"]");
        if (playerColor != null && !playerName.equals("Enter player name")) { // TODO remove "Enter..." and make it a const variable
            player = new Player(playerColor, playerName);
        } else {
            System.out.println("Color or name is null");
        }
        return player;
    }
    
    public void updateNewGameForNextPlayer(){
        this.currentPlayer++;
        this.urNewGame.resetPlayerNameTextField();
        this.urNewGame.setPlayerTitle(currentPlayer);
    }
    
    public void setPlayers(ArrayList<Player> playerArray){ // TODO make a urViewManager?
        this.urMainGame.setFirstPlayerNameToLabel(playerArray.get(0).getName()); // These can be private methods and can be implemented under abstract method "setPlayers()"
        this.urMainGame.setFirstPlayerPieceColor(playerArray.get(0).getColor());
        
        this.urMainGame.setSecondPlayerNameToLabel(playerArray.get(1).getName());
        this.urMainGame.setSecondPlayerPieceColor(playerArray.get(1).getColor());
    }
    
    public void hideColors(Color color){
        urNewGame.hideColorButton(color);
        //urNewGame.revalidate();
        //urNewGame.repaint();
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
    
    /*Getters */
    public JButton getStartNewGameButton(){
        return urMainMenu.getStartNewGameButton();
    }
    
    public JButton getStartLoadGameButton(){
        return urMainMenu.getLoadFormerGameButton();
    }
    
    public JButton getContinueButtonFromNewGame(){
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
    
    public JButton getExitAndSave(){
        return urMainGame.getExitAndSaveButton();
    }
    
    class SetColorClickListener implements FocusListener{
        
        public SetColorClickListener(){
            System.out.println("SetColorClickedListener greets you");
        }
        
        @Override
        public void focusGained(FocusEvent e) {
            System.out.println("Focused...");
            JTextField currentInput = urNewGame.getPlayerNameTextField();
            System.out.println("Name when focus: " + currentInput.getText());
            if (currentInput.getText().equals("Enter player name")) {
                currentInput.setText("");
            }        
        }

        @Override
        public void focusLost(FocusEvent e) {
            System.out.println("Focusedn't...");
            JTextField currentInput = urNewGame.getPlayerNameTextField();
            System.out.println("Name when not focus: " + currentInput.getText());
            if (currentInput.getText().equals("")) {
                urNewGame.resetPlayerNameTextField();
            }     
        }
    }
}
