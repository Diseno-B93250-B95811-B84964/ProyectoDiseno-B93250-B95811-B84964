/*
 * Issue #27 - View Manager.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Player;

import view.UrLoadGame;
import view.UrMainGame;
import view.UrMainMenu;
import view.UrNewGame;
import view.ShowRules;

/**
 * Manages all view events.
 * @author Mauricio Palma
 * @param <MainGameType> MainGame's child class.
 * @param <LoadGameType> LoadGame's child class.
 * @param <MainMenuType> MainMenu's child class.
 * @param <NewGameType> NewGame's child class.
 * @param <ShowRulesType> ShowRules's child class.
 */
public class ViewManager
        <
            MainGameType extends UrMainGame, 
            LoadGameType extends UrLoadGame,
            MainMenuType extends UrMainMenu,
            NewGameType extends UrNewGame,
            ShowRulesType extends ShowRules
        > {
    
    private MainMenuType mainMenu;
    private LoadGameType loadGame;
    private NewGameType newGame;
    private MainGameType mainGame;
    private ShowRulesType showRules;
    private JFrame mainFrame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    
    private Color currentPlayerColor;
    private boolean movedPiece;
    private boolean canClick;
    private int clickedRow;
    private int clickedColumn;
    
    public ViewManager (
            Supplier<MainGameType> mainGameSupplier,
            Supplier<LoadGameType> loadGameSupplier,
            Supplier<MainMenuType> mainMenuSupplier,
            Supplier<NewGameType> newGameSupplier,
            Supplier<ShowRulesType> rulesSupplier
            ) {
        this.mainMenu = mainMenuSupplier.get();
        this.loadGame = loadGameSupplier.get();
        this.newGame = newGameSupplier.get();
        this.mainGame = mainGameSupplier.get();
        this.showRules = rulesSupplier.get();
        initializeListeners();
        this.movedPiece = false;
        this.clickedRow = -1;
        this.clickedColumn = -1;
        this.canClick = false;
        this.currentPlayerColor = null;
        manageCardLayout();
    }
    
    /* Methods to play a match */
    public void playMove(int diceResult, int currentPlayer, Color playerColor){
        this.setIfPieceMoved(false);
        this.canClick = true;
        this.currentPlayerColor = playerColor;
        this.mainGame.cleanDice();
        this.mainGame.showThrownDice(diceResult);
        this.mainGame.setMoves(diceResult);
        this.mainGame.changePlayerTurn(currentPlayer);
    }
    
    public void updateMainGameView(){
    }
    
    public void showRules(){
    //public void showRules(String[] rules){
        showRules.showRules();
    }
    
    
    /*Methods to load a former match */
    
    public File getFileNameToLoadGame(){
        JFileChooser fileChooser = loadGame.getFileChooser();
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
    
    /* Methods to create a new game */
    public String getPlayerData(){
        Color playerColor = newGame.getPlayerColor();
        String playerName = newGame.getPlayerName();
        String playerData = null;
        System.out.println("Color: [" + playerColor+"]");
        System.out.println("Nombre: [" + playerName+"]");
        if (playerColor != null && !playerName.equals("Enter player name")) { // TODO remove "Enter..." and make it a const variable
            playerData = playerColor.getRGB() + "," + playerName;
        } else {
            System.out.println("Color or name is null");
        }
        return playerData;
    }
    
    public void updateNewGameForNextPlayer(int currentPlayer){
        //this.currentPlayer++;
        this.newGame.resetPlayerNameTextField();
        this.newGame.setPlayerTitle(currentPlayer);
    }
    
    /* Methods to personalize a match */
    
    public void setPlayers(ArrayList<Player> playerArray){ // TODO make a urViewManager?
        this.mainGame.setFirstPlayerNameToLabel(playerArray.get(0).getName()); // These can be private methods and can be implemented under abstract method "setPlayers()"
        this.mainGame.setFirstPlayerPieceColor(playerArray.get(0).getColor());
        
        this.mainGame.setSecondPlayerNameToLabel(playerArray.get(1).getName());
        this.mainGame.setSecondPlayerPieceColor(playerArray.get(1).getColor());
    }
    
    public void hideColors(Color color){
        newGame.hideColorButton(color);
        newGame.revalidate();
        newGame.repaint();
    }
    
    public void resetColorChosen () {
        newGame.resetColorChosen();
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
        addComponentToPanel(mainPanel, mainMenu, "mainMenu");
        addComponentToPanel(mainPanel, newGame, "newGame");
        addComponentToPanel(mainPanel, loadGame, "loadGame");
        addComponentToPanel(mainPanel, mainGame, "mainGame");
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
    
    /* GUI Configuration */
    
    private void initializeListeners(){
        this.newGame.addTextFieldFocusistener(new SetColorClickListener());
        initializeLabels();
    }
    
    private void initializeLabels(){
        JLabel currentLabel;
        for (int row = 0; row < 8; row++) { // TODO change magic number
            for (int column = 0; column < 3; column++) { // TODO change magic number
                currentLabel = this.mainGame.getLabel(row, column);
                currentLabel.addMouseListener(new TileMouseListener(row,column));
            }
        }
    }
    
    /* Check if piece moved*/
    
    public boolean getIfPieceMoved(){
        return this.movedPiece;
    }
    
    public void setIfPieceMoved(boolean bool){
        this.movedPiece = bool;
    }
    
    private void setClickedTile(int row, int column){
        this.clickedRow = row;
        this.clickedColumn = column;
    }
    
    public int getClickedRow(){
        return this.clickedRow;
    }
    
    public int getClickedColum(){
        return this.clickedColumn;
    }
    
    /*Button Getters */
    
    public JButton getStartNewGameButton(){
        return mainMenu.getStartNewGameButton();
    }
    
    public JButton getStartLoadGameButton(){
        return mainMenu.getLoadFormerGameButton();
    }
    
    public JButton getContinueButtonFromNewGame(){
        return newGame.getContinueButton();
    }
    
    public JButton getGoBackButtonFromNewGame(){
        return newGame.getBackButton();
    }

    public JButton getContinueButtonFromLoadGame(){
        return loadGame.getContinueButton();
    }
    
    public JButton getGoBackFromLoadGame(){
        return loadGame.getBackButton();
    }
    
    public JButton getRulesButtonFromMainMenu(){
        return mainMenu.getShowRulesButton();
    }
    
    public JButton getRulesButtonFromGame(){
        return mainGame.getShowRulesButton();
    }
    
    public JButton getExitAndSave(){
        return mainGame.getExitAndSaveButton();
    }
    
    public JButton getThrowDiceButton(){
        return mainGame.getThrowDiceButton();
    }
    
    /* Methods to update GUI */
    
    private void cleanTile(int row, int column) {
        mainGame.removeIconFromTile(row, column);
    }
    
    public void moveTile(int currentRow, int currentColumn, int nextRow, int nextColumn) {
        cleanTile(currentRow, currentColumn);
        mainGame.setNextPossibleLabel(nextRow, nextColumn, mainGame.getPlayerIcon(currentPlayerColor));
    }
    
    public void declareWinner(String winnerName){
    
    }
    /* Auxiliary inner classes */
    
    class SetColorClickListener implements FocusListener{

        @Override
        public void focusGained(FocusEvent event) {
            JTextField currentInput = newGame.getPlayerNameTextField();
            if (currentInput.getText().equals("Enter player name")) {
                currentInput.setText("");
            }        
        }

        @Override
        public void focusLost(FocusEvent event) {
            JTextField currentInput = newGame.getPlayerNameTextField();
            if (currentInput.getText().equals("")) {
                newGame.resetPlayerNameTextField();
            }     
        }
    }
    
    class TileMouseListener extends MouseAdapter {
        int row;
        int column;
        
        TileMouseListener(int row, int column){
            this.row = row;
            this.column = column;        
        }

        @Override
        public void mousePressed(MouseEvent entered){
            if (canClick) {
                canClick = false;
                setIfPieceMoved(true);
                setClickedTile(this.row, this.column);
                mainGame.setNextPossibleLabel(this.row, this.column, mainGame.getPlayerIcon(currentPlayerColor));
                mainGame.desactiveAPieceForPlayer(currentPlayerColor);
                currentPlayerColor = null;
            }
        }
        /*
            // check if it is first move
            if (this.row == 4 && this.column != 1) {
                validMove = makeInitialMove();               
            } else {
                validMove = makeNormalMove(tile);
            }
        
            if (validMove) {
                tile.resetTile();
                removeIconFromTile(this.row, this.column);
            }
        }     
        }*/
    }
}
