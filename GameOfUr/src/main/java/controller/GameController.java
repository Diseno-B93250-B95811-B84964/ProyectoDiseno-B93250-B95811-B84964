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
    private int diceResult;
    
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
            this.playerArray[0] = new UrPlayerModel(0);
            this.playerArray[1] = new UrPlayerModel(2);
            
            
            this.currentPlayerNum = 0;
            
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
        this.gameView.addthrowDiceButtonButtonClickListener(new ThrowDiceClickListener());
    }
    
    public void makeBoard(){
        this.board = new UrBoardModel(playerArray[0].getColor(), playerArray[1].getColor());
    }
    
    public UrBoardModel getBoard(){
        return this.board;
    }
    
    /* Start Game Logic */
    
   /* public UrTileModel interactWithTile(int x, int y) {
        UrTileModel chosenTile; 
        UrTileModel nextMove;
        chosenTile = board.getTile(x, y); // current
        nextMove = getPossibleTile(x, y); // next possible tile
        System.out.println("Dentro de interactWithTile");
        System.out.println("NextMove: " + nextMove);

        if (nextMove != null) {
            System.out.println("InteractWithTile NO es null!");
            nextMove = movePiece(chosenTile, nextMove);
            checkIfScored(nextMove);
        }
        return nextMove;
    }*/
    
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
        UrTileModel possibleTile = null;
        UrPieceModel currentPiece = null;
        System.out.println("Dentro de getPossibleTile");

        if (board.getTile(x,y).getPiece() != null) {
                if (board.getTile(x,y).getPiece().getColor() == currentPlayer.getColor()) {
                    currentPiece = board.getTile(x,y).getPiece();
                    possibleTile = possiblePaths.get(currentPiece);
                }
            }
        return possibleTile;
    }
    
    private void checkIfScored(UrTileModel definitiveTile) {
        int x = definitiveTile.getRow();
        int y = definitiveTile.getColumn();
        if (x == 4 && (y == 0 || y == 2) ) {
            currentPlayer.addScoreToPlayer();
            currentPlayer.removePiece(definitiveTile.getPiece());
            definitiveTile.resetTile();
        }
    }
    
    private boolean checkIfWinner() {
        boolean isWinner = false;
        if (currentPlayer.getPlayerScore() == 7) {
            isWinner = true;
        }
        return isWinner;
    }
    
    /* Serializer */
    public void saveGame() {
        try {
            this.serializer = new UrSerializerModel(this.board, this.playerArray[0], this.playerArray[1]);
            boolean dir = new File("saves").mkdirs();
            CSVWriter writer = new CSVWriter(new FileWriter("saves\\gameState.csv"));
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
        makeBoard();
        deSerializer = new UrDeserializerModel(playerArray, board);
        deSerializer.loadGameState(fileContents);
    }
    
    private void loadGameView() {
        gameView.setFirstPlayerNameToLabel(playerArray[0].getPlayerName());
        gameView.setSecondPlayerNameToLabel(playerArray[1].getPlayerName());
        gameView.addScoreToFirstPlayer(playerArray[0].getPlayerScore());
        gameView.addScoreToSecondPlayer(playerArray[1].getPlayerScore());

        try {
            gameView.setFirstPlayerPieceColor(playerArray[0].getColor());
            gameView.setSecondPlayerPieceColor(playerArray[1].getColor());
        } catch(IOException e) {
            System.out.println("Images not found! Please check images path");
        }
        for (int index = 0; index < playerArray[0].getPlayerScore(); index++) {
            gameView.desactiveAPieceForFirstPlayer();
        }
        for (int index = 0; index < playerArray[1].getPlayerScore(); index++) {
            gameView.desactiveAPieceForSecondPlayer();
        }
        
        for (var currentPiece : playerArray[0].getPlayerPieces()) {
            System.out.println("Is in play de J1" + currentPiece.isInPlay());
            if (currentPiece.isInPlay()) {
                gameView.desactiveAPieceForFirstPlayer();
                int x = currentPiece.getX();
                int y = currentPiece.getY();
                Color color = playerArray[0].getColor();
                ImageIcon colorPieceIcon = gameView.getPieceImageColor(color);
                gameView.setNextPossibleLabel(x,y,colorPieceIcon);
                System.out.println("Nueva pieza?");
            } 
        }
        
        for (var currentPiece : playerArray[1].getPlayerPieces()) {
            System.out.println("Is in play de J2" + currentPiece.isInPlay());
            if (currentPiece.isInPlay()) {
                gameView.desactiveAPieceForSecondPlayer();
                int x = currentPiece.getX();
                int y = currentPiece.getY();
                Color color = playerArray[1].getColor();
                ImageIcon colorPieceIcon = gameView.getPieceImageColor(color);
                gameView.setNextPossibleLabel(x,y,colorPieceIcon);
                System.out.println("Nueva pieza?");
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

    private void makeNormalMove(UrTileModel clickedTile){
        UrTileModel possibleTile = null;
        UrPieceModel clickedPiece = clickedTile.getPiece();
        int x = clickedTile.getRow();
        int y = clickedTile.getColumn();
        if (!clickedTile.isVacant()) {
            possibleTile = possiblePaths.get(clickedPiece);
            board.setPieceTile(clickedPiece, possibleTile);
            checkIfScored(possibleTile);
        }
    }  
    
    private void makeInitialMove() {
        UrPieceModel piece = getPieceToPlay();
        UrTileModel possibleTile = possiblePaths.get(piece);
        possibleTile.setPiece(piece);
        checkIfScored(possibleTile);
    }
    
    public UrPieceModel getPieceToPlay(){
        UrPieceModel pieceToPlay = null;
        for(int currentPiece = 0; currentPiece < currentPlayer.getPlayerPieces().size(); currentPiece++){
            if(!currentPlayer.getPlayerPieces().get(currentPiece).isInPlay()){
                pieceToPlay = currentPlayer.getPlayerPieces().get(currentPiece);
            }
        }
        return pieceToPlay;
    }
    
    /* Listeners */
    
    class TileMouseListener extends MouseAdapter {
        UrTileModel tile;
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
            UrTileModel movedTile = null;
            this.label.setBackground(Color.decode("#DC3333")); 
            movedTile = startListening();
            if (movedTile != null) {
                System.out.println("movedTile en 334 NO es nulo");
                int x = movedTile.getRow();
                int y = movedTile.getColumn();
                if (currentPlayer == playerArray[0]) {
                    gameView.setNextPossibleLabel(x,y,gameView.getFirstPlayerIcon());
                } else {
                    gameView.setNextPossibleLabel(x,y,gameView.getSecondPlayerIcon());
                }
            }
            this.label.setBackground(Color.decode("#2D3553"));
        }

        @Override
        public void mouseEntered(MouseEvent entered){
           /* try {
                gameView.removeIconFromLabel(0,0); // TODO these numbers are not used yet.
            } catch (IOException e) {
                System.out.println("Images not found!");
            }*/
            //this.label.setBackground(Color.decode("#2D3553"));
        }
        
        public UrTileModel startListening() {
            board = getBoard();
            tile = board.getTile(this.row, this.column); // TODO Delete?
            UrTileModel movedTile = null;
            System.out.println("Estado de diceThrown: " + diceThrown);
            System.out.println("Estado de Winner: " + winner);    
            if (diceThrown) {
                System.out.println("El dado ha sido tirado");
                if(!winner) { // winner == false
                    currentPlayer = playerArray[currentPlayerNum];
                    board.setPlayerTurn(currentPlayer.getColor());
                    System.out.println("Printing Color of player turn: " +currentPlayer.getColor());
                    if (diceResult > 0){
                        System.out.println("Dice result es: " + diceResult);
                        possiblePaths.clear();
                        calculateAllPossiblePathsPerTurn(diceResult);
                        System.out.println("PossiblePath is empty?"  + possiblePaths.isEmpty());
                            possiblePaths.entrySet().forEach(entry -> {
                            System.out.println("Imprimiendo llaves: " + entry.getKey().getX() + entry.getKey().getY() +  " " 
                                    + "Imprimiendo valores: " +entry.getValue().getRow() + " " + entry.getValue().getColumn());
                            });
                        if (!possiblePaths.isEmpty()) {
                            System.out.println("Possible path encontro algo!, printing it...");
                            // check if it is first move
                            if (this.row == 4 && this.column != 1) {
                                makeInitialMove();           // Grafico               
                            } else {
                                makeNormalMove(tile);
                            }
                            /// Removes piece if there was one
                            tile.resetTile();
                        } 
                    }
                    winner = checkIfWinner();
                    currentPlayerNum ++;
                    currentPlayerNum %= playerArray.length;          
                }
                diceThrown = false;
            }
            return movedTile;
        }
    }
   
    class SaveAndLeaveClickListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (diceThrown) {
                currentPlayer = playerArray[currentPlayerNum];
                board.setPlayerTurn(currentPlayer.getColor());
                saveGame();
                System.exit(0);
            }
        } 
    }
    
    class ExitClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
   
    class ThrowDiceClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            diceResult = 0;
            gameView.cleanDice();
            diceThrown  = true;
            diceModel.rollDice();
            diceResult = diceModel.getRollResult();
            gameView.showThrow(diceResult);
            gameView.setMoves(diceResult); 
        }
    }
}
