/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.opencsv.CSVWriter;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.UrDeserializerModel;

import model.UrBoardModel;
import model.UrDiceModel;
import model.UrPieceModel;
import model.UrPlayerModel;
import model.UrSerializerModel;
import model.UrTileModel;

import view.MainGameView;
import view.MainMenuView;

/**
 *
 * @author Mauricio Palma, Ximena Gdur, Alvaro Villegas
 */
public class GameController {
    private final static int ROWS = 8;
    private final static int COLUMNS = 3;
    
    private UrBoardModel board;
    
    private UrDiceModel diceModel;
    private boolean diceThrown;
    
    private UrPlayerModel[] playerArray;
    private UrPlayerModel currentPlayer;
    private int currentPlayerNum;
    private boolean winner;
    
    private HashMap<UrPieceModel, UrTileModel> possiblePaths;
    
    private UrDeserializerModel deSerializer;
    private UrSerializerModel serializer;
    
    private MainGameView gameView;
    private MainMenuView mainMenuView;
 
    /* Initialize Game Controller */
    
    public GameController(){
        try {
            this.diceModel = new UrDiceModel();
            this.gameView = new MainGameView();
            this.mainMenuView = new MainMenuView();
            this.possiblePaths = new HashMap();
            this.winner = false;
            this.playerArray = new UrPlayerModel[2];
            
            playerArray[0] = new UrPlayerModel(0);
            playerArray[1] = new UrPlayerModel(2);
            
            initializeLabels();        
            menuHandler();
        } catch(IOException e) {
            System.out.println("Images not found! Please check images path");
        }
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
    
    private void menuHandler(){
        this.gameView.addSaveAndLeaveButtonClickListener(new SaveAndLeaveClickListener());
        this.mainMenuView.addExitButtonClickListener(new ExitClickListener());
        //this.gameView.throwDiceButtonClickListener(new ThrowDiceClickListener());
    }
    
    /* Start Game Logic */
    
    public void startGame() {
        this.board = new UrBoardModel(playerArray[0].getColor(), playerArray[1].getColor());
        int result = -1;
        UrTileModel chosenTile, possibleTile;
        while(!winner) {
            currentPlayer = playerArray[currentPlayerNum];
            result = throwDice();
            if (result > 0){
                possiblePaths.clear();
                calculateAllPossiblePathsPerTurn(result);
                if (!possiblePaths.isEmpty()) {
                    // wait for action listener or something
                    int x = 0, y = 0;
                    possibleTile = getPossibleTile(x, y);
                    // wait for user conformation
                    chosenTile = board.getTile(x, y);
                    saveChosenTile(chosenTile, possibleTile);
                    addToScore(possibleTile);
                } else {
                    // show no possible moves
                }
            }
            winner = determineIfWinnerElseChangePlayer();
        }
        // TODO destroy frame
        // TODO create winningFrame
        // TODO exit
    }
    
    private int throwDice(){
        gameView.cleanDice();
        diceThrown  = true;
        diceModel.rollDice();
        int diceResult = diceModel.getRollResult();
        gameView.showThrow(diceResult);
        gameView.setMoves(diceResult);
        return diceResult; 
    }
    
    private void calculateAllPossiblePathsPerTurn(int amountOfMoves){
        Color playerColor = currentPlayer.getColor();
        
        UrTileModel currentTile;
        UrTileModel nextPossibleTile;
        
        for(UrPieceModel piece : currentPlayer.getPlayerPieces()){
            currentTile = board.getTile(piece.getX(), piece.getY());
            nextPossibleTile = board.getPossibleTile(currentTile, amountOfMoves, playerColor);
            
            if(nextPossibleTile != null) {
                possiblePaths.put(piece, nextPossibleTile);
            }
        }
    }
    
    private UrTileModel getPossibleTile(int x, int y) {
        UrPieceModel currentPiece = board.getTile(x,y).getPiece();
        UrTileModel possibleTile = possiblePaths.get(currentPiece);
        
        return possibleTile;
    }
    
    private void saveChosenTile(UrTileModel chosenTile, UrTileModel possibleTile) {
        // sets piece in possible tile
        board.setPieceTile(chosenTile, possibleTile);

        int x = possibleTile.getRow();
        int y = possibleTile.getColumn();

        // parte visual
        // tile viejo ya no tiene pieza
        // pieza del otro jugador vuelve a estado original
        //gameView.setNextPossibleLabel(x,y);
    }
    
    private void addToScore(UrTileModel definitiveTile) {
        int x = definitiveTile.getRow();
        int y = definitiveTile.getColumn();
        if (x == 4 && (y == 0 || y == 2) ) {
            currentPlayer.addScoreToPlayer();
            currentPlayer.removePiece(definitiveTile.getPiece());
            definitiveTile.resetTile();
        }
    }
    
    private boolean determineIfWinnerElseChangePlayer() {
        boolean winner = false;
        if (currentPlayer.getPlayerScore() == 7) {
            winner = true;
        } else {
            currentPlayerNum ++;
            currentPlayerNum %= playerArray.length;
        }
        return winner;
    }
    
    /* Serializer */
      
    public void saveGame() {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter("testData//sample.csv"));
            ArrayList<String[]> array = serializer.saveGameState();

            for (String[] rowString : array) {
                for (String string : rowString) {
                    System.out.print(string + ",");
                }
                System.out.println();
                writer.writeNext(rowString);
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadGame(ArrayList<String[]> fileContents) {
        loadGameState(fileContents);
        loadGameView();
    }
    
    private void loadGameState(ArrayList<String[]> fileContents) {
        deSerializer = new UrDeserializerModel(playerArray, board);
        deSerializer.loadGameState(fileContents);
    }
    
    private void loadGameView() {
        gameView.setFirstPlayerNameToLabel(playerArray[0].getPlayerName());
        gameView.setSecondPlayerNameToLabel(playerArray[1].getPlayerName());
        gameView.addScoreToFirstPlayer(6);
        gameView.addScoreToSecondPlayer(6);
        try {
            System.out.println("Color first player" + playerArray[0].getColor());
            gameView.setFirstPlayerPieceColor(playerArray[0].getColor());
            System.out.println("Color second player" + playerArray[1].getColor());    
            Color color = new Color(0,102,255);
            gameView.setSecondPlayerPieceColor(color);
        } catch(IOException e) {
            System.out.println("Images not found! Please check images path");
        }
        for (int index = 0; index < playerArray[0].getPlayerScore(); index++) {
            gameView.desactiveAPieceForFirstPlayer();
        }
        for (int index = 0; index < playerArray[1].getPlayerScore(); index++) {
            gameView.desactiveAPieceForSecondPlayer();
        }
        
        int limit = playerArray[0].getPlayerPieces().size();
        for (int index = 0; index < limit; index++) {
            if (playerArray[0].getPlayerPiece(index).getX() != -1) {
                gameView.desactiveAPieceForFirstPlayer();
            } else {
                int x = playerArray[0].getPlayerPiece(index).getX();
                int y = playerArray[0].getPlayerPiece(index).getY();
                Color color = playerArray[0].getColor();
                ImageIcon colorPieceIcon = gameView.getPieceImageColor(color);
                gameView.setNextPossibleLabel(x,y,colorPieceIcon);
            }
        }
            
        limit = playerArray[1].getPlayerPieces().size();
        for (int index = 0; index < limit; index++) {
            if (playerArray[1].getPlayerPiece(index).getX() != -1) {
                gameView.desactiveAPieceForSecondPlayer();
            } else {
                int x = playerArray[1].getPlayerPiece(index).getX();
                int y = playerArray[1].getPlayerPiece(index).getY();
                Color color = playerArray[1].getColor();
                ImageIcon colorPieceIcon = gameView.getPieceImageColor(color);
                gameView.setNextPossibleLabel(x,y,colorPieceIcon);
            }
        }
    }
    
    /* Gets and sets */

    public void setFirstPlayerName(String name){
        gameView.setFirstPlayerNameToLabel(name);
        playerArray[0].setPlayerName(name);
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
    
    public MainGameView getMainGameView() {
        return this.gameView;
    }
    
    public MainMenuView getMainMenuView(){
        return this.mainMenuView;
    }
    
    /* Listeners */
    
    public void printHello(){
        System.out.println("Hello from 348!!!");
    }
    
    class ThrowDiceClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int result = getDiceResult();
        } 
        
        private int getDiceResult(){
            gameView.cleanDice();
            diceThrown  = true;
            diceModel.rollDice();
            int diceResult = diceModel.getRollResult();
            gameView.showThrow(diceResult);
            gameView.setMoves(diceResult);
            return diceResult; 
        }
    }
    
    class TileMouseListener extends MouseAdapter {
        JLabel label;
        int row;
        int column;
        
        TileMouseListener(JLabel label, int row, int column){
            this.label = label;
            this.row = row;
            this.column = column;
            
        }

        @Override
        public void mousePressed(MouseEvent entered){
            if(diceThrown == true) {
                this.label.setBackground(Color.red);
                gameView.setNextPossibleLabel(0,0,gameView.getFirsPlayerIcon());
                //chooseNextPossibleLabel(row, column);
            }
            printHello();
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
   
}
