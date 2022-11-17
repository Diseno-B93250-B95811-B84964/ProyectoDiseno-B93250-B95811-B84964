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
import javax.swing.JFileChooser;
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

    /* Atributos serializador? */
    private UrPlayerModel[] playerArray;
    
    private UrSerializerConstructor serializer;
    
    private MainGameView gameView;


    
    private Color currentPlayer;
    private boolean diceThrown;
    
    private HashMap<UrPieceModel, UrTileModel> possiblePaths;
    
    private boolean winner;
    
    private int currentPlayerNum;
    
    public GameController(){
        try {
            this.diceModel = new UrDiceModel();
            this.gameView = new MainGameView();
            this.piece = new UrPieceModel();
            this.mainMenuView = new MainMenuView();
            this.possiblePaths = new HashMap();
            this.winner = false;
            this.playerArray = new UrPlayerModel[2];
            for (int index = 0; index < playerArray.length; index++) {
                playerArray[index] = new UrPlayerModel();
            }
                    
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
      
    public void saveGame() {
        try (PrintWriter writer = new PrintWriter(new File("gameState.csv"))) {
            writer.write(serializer.saveGameState());
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void loadGame() {
        // file chooser returns file name
        // open file name and get string
        // create array of strings by splitting by \n
        String fileContents = "";
        loadGameState(fileContents.split("\n", 0));
    }
    
    private UrPlayerModel createPlayer(String[] player) {
        Color playerColor = new Color(Integer.parseInt(player[0]));
        int playerScore = Integer.parseInt(player[2]);
        // Color playerColor, String playerName, int playerScore
        return new UrPlayerModel(playerColor, player[1], playerScore);
    }
    
    private void loadGameState(String[] fileContents) {
        // read player colors and score
        int fileContentsIndex = 0;
        int pieceIndex = 0;
        playerArray[0] = createPlayer(fileContents[fileContentsIndex].split("[,]", 0));
        
        fileContentsIndex++;
        playerArray[1] = createPlayer(fileContents[fileContentsIndex].split("[,]", 0));
        
        fileContentsIndex++;
        board = new UrBoardModel(playerArray[0].getColor(), playerArray[1].getColor());
        
        int actualCol = 0;
        for(int row = 0; row < UrBoardModel.ROWS; row++) {
            for(int col = 0; col < UrBoardModel.COLUMNS + 2; col++) {
                char character = fileContents[fileContentsIndex].charAt(col);
                if (character != ',') {
                    UrPieceModel piece;
                    if (Character.getNumericValue(character) == UrSerializerConstructor.OCCUPIED_P1) {
                        piece = playerArray[0].getPlayerPiece(pieceIndex);
                        board.setPiece(row, actualCol, piece);
                    } else if (Character.getNumericValue(character) == UrSerializerConstructor.OCCUPIED_P2) {
                        piece = playerArray[1].getPlayerPiece(pieceIndex);
                        board.setPiece(row, actualCol, piece);
                    }
                    actualCol++;
                }
            }
            fileContentsIndex++;
        }
        board.setPlayerTurn(new Color(Integer.parseInt(fileContents[fileContentsIndex])));
    }
    
    /*Setters */
    private void chooseNextPossibleLabel(){
        //gameView.setNextPossibleLabel(2,2);
    }
        
    /*Setters */


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
    
    public void resetMapPiecesPerTurn(UrPlayerModel currentPlayer){
        possiblePaths.clear();
        for(int pieces = 0; pieces < currentPlayer.getPlayerPieces().size(); pieces++) {
            if(currentPlayer.getPlayerPieces().get(pieces).isInPlay()){
                possiblePaths.put(currentPlayer.getPlayerPieces().get(pieces), 
                    new UrTileModel(currentPlayer.getPlayerPieces().get(pieces).getX(), 
                    currentPlayer.getPlayerPieces().get(pieces).getY()));
            } else{
                possiblePaths.put(currentPlayer.getPlayerPieces().get(pieces), null);
            }
        }
    }
    
    public void calculateAllPossiblePathsPerTurn(UrPlayerModel currentPlayer, int amountOfMoves){
        Color playerColor = currentPlayer.getColor();
        UrTileModel currentTile = new UrTileModel();
        UrTileModel auxTile = new UrTileModel();
        if(diceThrown){
            for(int pieces = 0; pieces < possiblePaths.size(); pieces++){
                if(possiblePaths.get(currentPlayer.getPlayerPieces().get(pieces)) != null){
                    currentTile = possiblePaths.get(currentPlayer.getPlayerPieces().get(pieces));
                    auxTile = board.getPossibleTile(currentTile, amountOfMoves, playerColor);
                    possiblePaths.put(currentPlayer.getPlayerPieces().get(pieces), auxTile);
                }
            }
        }
    }
    
   // CalculateAllPossiblePathsPerTurn() {
        //Cada vez que se tira dado:
        //atributoMap<PieceOriginalPosition, TilePossiblePosition>          	
        //Por cada piece de getPlayerNPieces()		
        //Map.TilePossiblePath = choosePlayerPossiblePath(PieceOriginalPosition)
        //buscarEnMapa(atributoMapa) {
        //atributoMap = atributoMap.search(todo lo que NO sea NULL
       
    //}

    public void startGame() {
        
        this.board = new UrBoardModel(playerArray[0].getColor(), playerArray[1].getColor());
        Color currentColor = new Color(255,255,255);
        int result = -1;
        while(!winner) {
            currentColor = playerArray[currentPlayerNum].getColor();
            result = getDiceResult();
            if (result>0){
                //CalculateAllPossiblePathsPerTurn();
                if (!possiblePaths.isEmpty()) {
                    //gameLogic(int x, int y)
                    /*if (playerArray[currentPlayerNum]) {
                    
                    }*/
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
       //GameController.TileMouseListener a = new GameController().new TileMouseListener();
       
    }
    
    /* Listeners */
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
