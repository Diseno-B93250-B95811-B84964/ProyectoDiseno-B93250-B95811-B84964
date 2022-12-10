/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Supplier;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Manages the data of the classes to load into a JSON file.
 * @author Álvaro Miranda.
 */
public class Deserializer extends JSONManager{
    
    //private JSONObject playerObject;
    
    private JSONObject mainBoardObject;
    
    ArrayList<JSONObject> jsonPlayers;
    
    int currentPlayer;
    /**
     * Creates a new Serializer with the current board and active players.
     * @param gameBoard Current game board.
     * @param players Array that holds the active players.
    */
    public Deserializer(Board gameBoard, Player[] players){
        this.gameBoard = gameBoard;
        this.gamePlayers = players;
        this.mainBoardObject = new JSONObject();
        this.mainManager = new FileManager();
        this.jsonPlayers = new ArrayList<JSONObject>(players.length);
        this.currentPlayer = 0;
    }
    
    @Override
    public void execute(){
        manageFile();
    }
    
    /**
     * Calls methods collect the data from the game sections.
    */
    @Override
    protected void manageFile(){
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        try{
            mainManager.loadFile("output.json", "src\\main\\java\\auxiliaryFiles\\");
            String jsonContents = String.join(", ", mainManager.getFileContents());
            Object allGameObjects = jsonParser.parse(jsonContents);
            JSONArray gameObjects = (JSONArray) allGameObjects;
            for (int i = 0; i < gamePlayers.length; i++) {
                jsonPlayers.add(i, (JSONObject)gameObjects.get(i));
                managePlayers();
                currentPlayer++;
            }
            mainBoardObject = (JSONObject)gameObjects.get(gameObjects.size() - 1);
            manageBoard();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Collects all the information from the game board and saves it in a JSONObject.
    */
    @Override
    public void manageBoard(){
        JSONObject boardAtributes = new JSONObject();
        boardAtributes = (JSONObject)mainBoardObject.get("board");
        manageBoardVertices(boardAtributes);
    }
    
    public void manageBoardRows(){
        gameBoard.setRows(castLongToInt((Long) mainBoardObject.get("amountRows")));
    }
    
    public void manageBoardColumns(){
        gameBoard.setColumns(castLongToInt((Long) mainBoardObject.get("amountColumns")));
    }
    
    public void manageBoardVertices(JSONObject boardAtributes){
        UrTile currentTile;
        JSONArray vertices = new JSONArray();
        vertices = (JSONArray) boardAtributes.get("vertices");
        for (int i = 0; i < vertices.size(); i++) {
            JSONObject tileJSONObject = (JSONObject)vertices.get(i);
            currentTile = (UrTile)gameBoard.getVerticesArray().get(i);
            manageRowVertices(tileJSONObject, currentTile);
            manageColumnVertices(tileJSONObject, currentTile);
            manageTilePieces(tileJSONObject, currentTile);
            currentTile.setIsSafe((boolean)tileJSONObject.get("isSafe"));
            manageIsVacant(tileJSONObject, currentTile);
        }
    }
    
    public void manageIsVacant(JSONObject tileJSONObject, UrTile currentTile){
        currentTile.setIsVacant((boolean)tileJSONObject.get("isVacant"));
    }
        
    public void manageRowVertices(JSONObject tileJSONObject, UrTile currentTile){
        currentTile.setRow(castLongToInt((Long)tileJSONObject.get("row")));
    }
    
    public void manageColumnVertices(JSONObject tileJSONObject, UrTile currentTile){
        currentTile.setColumn(castLongToInt((Long)tileJSONObject.get("column")));
    }
    
    public void manageTilePieces(JSONObject tileJSONObject, UrTile currentTile){
        int currentPlayer;
        var jsonTile = tileJSONObject.get("piece");
        Color pieceColor;
        if(jsonTile instanceof String){
            currentTile.setPiece(null);
        } else {
            JSONObject pieceAtributes = new JSONObject((JSONObject)jsonTile);
            pieceColor = new Color(castLongToInt((Long)pieceAtributes.get("pieceColor")));
            currentPlayer = getPlayerColorMatch(pieceColor);
            UrPiece pieceInTile = (UrPiece)gamePlayers[currentPlayer].getAvailablePiece();
            pieceInTile.setColor(pieceColor);
            pieceInTile.setIsInPlay(true);
            currentTile.setPiece(pieceInTile);
        }
    }
    
    /**
     * Collects all the information from the actives players and saves it in a JSONObject.
    */
    @Override
    public void managePlayers(){
        String playerKey = "player" + (currentPlayer + 1);
        JSONObject auxPlayer = new JSONObject();
        JSONObject playerAtributes = new JSONObject();
        auxPlayer = jsonPlayers.get(currentPlayer);
        playerAtributes = (JSONObject)auxPlayer.get(playerKey);
        manageCurrentPlayer(playerAtributes, currentPlayer);
    }
    
    public void manageCurrentPlayer(JSONObject jsonPlayer, int currentPlayer){
        managePlayerName(jsonPlayer, currentPlayer);
        managePlayerColor(jsonPlayer, currentPlayer);
        managePlayerScore(jsonPlayer, currentPlayer);
        managePlayerPieces(jsonPlayer, currentPlayer);
    }
    
    public void managePlayerName(JSONObject jsonPlayer, int currentPlayer){
        gamePlayers[currentPlayer].setName((String)jsonPlayer.get("name"));
    }
    
    public void managePlayerColor(JSONObject jsonPlayer, int currentPlayer){
        Color currentPlayerColor = new Color(castLongToInt((Long)jsonPlayer.get("playerColor")));
        gamePlayers[currentPlayer].setColor(currentPlayerColor);
    }
    
    public void managePlayerScore(JSONObject jsonPlayer, int currentPlayer){
        //System.out.println(mainBoardObject.get("score"));
        gamePlayers[currentPlayer].setScore(castLongToInt((Long) jsonPlayer.get("score")));
    }
    
    public void managePlayerPieces(JSONObject jsonPlayer, int currentPlayer){
        JSONArray piecesJSONArray = (JSONArray) jsonPlayer.get("pieces");
        int piecesArraySize = gamePlayers[currentPlayer].getPiecesArray().size();
        UrPiece tempPiece;
        Color pieceColor;
        for(int currentPiece = 0; currentPiece < piecesArraySize; currentPiece++){
            JSONObject jsonPiece = new JSONObject();
            jsonPiece = (JSONObject)piecesJSONArray.get(currentPiece);
            tempPiece = (UrPiece)gamePlayers[currentPlayer].getPiecesArray().get(currentPiece);
            pieceColor = new Color(castLongToInt((Long)jsonPiece.get("pieceColor")));
            tempPiece.setColor(pieceColor);
        }
    }
    
    public String getPlayerKey(int currentPlayer){
        String playerKey = "";
        if(currentPlayer == 0){
            playerKey = "player1";
        } else {
            playerKey = "player2";
        }
        return playerKey;
    }
    
    public int castLongToInt(Long value){
        return value.intValue();
    }
    
    public int getPlayerColorMatch(Color pieceColor){
        int player = 0;
        for(int currentPlayer = 0; currentPlayer < gamePlayers.length; currentPlayer++){
            if(pieceColor.getRGB() == gamePlayers[currentPlayer].getColor().getRGB()){
                player = currentPlayer;
                break;
            }
        }
        return player;
    }
}
