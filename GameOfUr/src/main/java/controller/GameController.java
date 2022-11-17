/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

import model.UrBoardModel;

import model.UrDiceModel;
import model.UrPieceModel;
import model.UrPlayerModel;
import model.UrSerializerConstructor;
import model.UrTileModel;

import view.MainGameView;
import view.MainMenuView;


/**
 *
 * @author Mauricio Palma
 */
public class GameController {
    private final static int ROWS = 8;
    private final static int COLUMNS = 3;
    
    private UrBoardModel board;
    private UrDiceModel diceModel;
    private UrPieceModel piece;
    private MainMenuView mainMenuView;

    /*Atributos serializador?*/
    //private UrPlayerModel player1;
    //private UrPlayerModel player2;
    private UrPlayerModel[] playerArray;
    
    private UrSerializerConstructor serializer;
    
    private MainGameView gameView;
    private MainMenuView menu;

    
    private Color currentPlayer;
    private boolean diceThrown;
    
    private Map pathMap;
    
    private boolean winner;
    
    private int currentPlayerNum;
    
    public GameController(){
        try {
            this.diceModel = new UrDiceModel();
            this.gameView = new MainGameView();
            this.piece = new UrPieceModel();
            this.mainMenuView = new MainMenuView();
            this.pathMap = new HashMap();
            this.winner = false;
            this.playerArray = new UrPlayerModel[2];
                    
            initializeLabels();
            chooseNextPossibleLabel();        
            menuHandler();
        } catch(IOException e) {
            System.out.println("Images not found! Please check images path");
        }
    }
    
    public MainGameView getMainGameView() {
        return this.gameView;
    }
    
    public MainMenuView getMainMenuView(){
        return this.mainMenuView;
    }
    
    /* 
    public GameController(MainGameView gameView, UrPieceModel piece,
        MainMenuView menu, UrDiceController diceController)
    {
        this.gameView = gameView;
        this.piece = piece;
        this.menu = menu;
        
        this.diceThrown = false;
        
        this.serializer = new UrSerializerConstructor(board, player1, player2);
        
        initializeLabels();
        //chooseNextPossibleLabel();        
        menuHandler();
        UrDiceController.DiceListener diceListener = initializeDice(diceController); // TODO move this
        this.diceView.addDiceListener(diceListener);  
    }*/
    
    private void menuHandler(){
        this.gameView.addSaveAndLeaveButtonClickListener(new SaveAndLeaveClickListener());
        this.mainMenuView.addExitButtonClickListener(new ExitClickListener());
        this.mainMenuView.addLoadGameButtonClickListener(new LoadGameClickListener());
    }
    
    private void initializeLabels(){
        JLabel currentLabel;
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                currentLabel = gameView.getLabel(row, column);
                currentLabel.addMouseListener(new TileMouseListener(currentLabel,row,column));
            }
        }
    }
    
    private void playerTurn() {
        // while
            // tirar el dado
            // esperar que se estripe
            // chooseNextPossibleLabel
            
    }
    
    private void chooseNextPossibleLabel(int row, int column){
        UrTileModel chosenTile = board.getTile(row, column);
        int diceValue = diceModel.getRollResult();
        UrTileModel possibleTile = board.getPossibleTile(chosenTile, diceValue, currentPlayer);
        
        int x = possibleTile.getRow();
        int y = possibleTile.getColumn();
        
        //gameView.setNextPossibleLabel(x,y);
    }
      
    public void save_game() {
        try (PrintWriter writer = new PrintWriter(new File("gameState.csv"))) {
            writer.write(serializer.saveGameState());
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    /*Setters */
    private void chooseNextPossibleLabel(){
        //gameView.setNextPossibleLabel(2,2);
    }

    public void setFirstPlayerName(String name){
        gameView.setFirstPlayerNameToLabel(name);
        this.playerArray[0].setPlayerName(name);
    }
    
    public void setFirstPlayerColor(Color color){
        try {
            gameView.setFirstPlayerPieceColor(color);
            this.playerArray[0].setColor(color);
        } catch (IOException e) {
            System.out.println("Color piece icon not found");
        }
    }
    
    public void setSecondPlayerName(String name){
       gameView.setSecondPlayerNameToLabel(name);
       this.playerArray[1].setPlayerName(name);
    }
    
    public void setSecondPlayerColor(Color color){
        try {
            gameView.setSecondPlayerPieceColor(color);
            this.playerArray[1].setColor(color);
        } catch (IOException e) {
            System.out.println("Color piece icon not found");
        }
    }

    public void startGame() {
        
        this.board = new UrBoardModel(playerArray[0].getColor(), playerArray[1].getColor());
        Color currentColor = new Color(255,255,255);
        int result = -1;
        while(!winner) {
            currentColor = playerArray[currentPlayerNum].getColor();
            result = getDiceResult();
            if (result>0){
                //CalculateAllPossiblePathsPerTurn();
                if (!pathMap.isEmpty()) {
                    //gameLogic(int x, int y)
                }
            }
        }
        // TODO destroy frame
        // TODO create winningFrame
        // TODO exit
    }
    
    public int getDiceResult(){
        gameView.cleanDice();
        diceThrown  = true;
        diceModel.rollDice();
        int diceResult = diceModel.getRollResult();
        gameView.showThrow(diceResult);
        gameView.setMoves(diceResult);
        return diceResult;
    }
    
    private void gameLogic() {
       GameController.TileMouseListener a = new GameController().new TileMouseListener();
             //  .this.sayHey();
    }
    
    /* Listeners */
    class TileMouseListener extends MouseAdapter {
        JLabel label;
        int row;
        int column;
        
        TileMouseListener(){
        }
        
        TileMouseListener(JLabel label, int row, int column){
            this.label = label;
            this.row = row;
            this.column = column;
        }
        
        public void sayHey(){
            System.out.println("Hola");
        }

        @Override
        public void mousePressed(MouseEvent entered){
            if(diceThrown == true) {
                this.label.setBackground(Color.red);
                gameView.setNextPossibleLabel(0,0,gameView.getFirsPlayerIcon());
                //chooseNextPossibleLabel(row, column);
            }
        }

        @Override
        public void mouseEntered(MouseEvent entered){
            try {
                gameView.removeIconFromLabel(0,0); // TODO these numbers are not used yet.
            } catch (IOException e) {
                System.out.println("Images not found!");
            }
            this.label.setBackground(Color.decode("#2D3553"));
        }
    }
   
    class SaveAndLeaveClickListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        } 
    }
    
    class ExitClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    
    class LoadGameClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Teach me how to load a game!");
            
        }
    }
}
