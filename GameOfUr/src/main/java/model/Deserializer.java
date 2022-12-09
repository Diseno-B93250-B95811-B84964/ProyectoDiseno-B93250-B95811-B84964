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
    
    private JSONObject playerObject;
    //private JSONObject player2Object;
    private JSONObject mainBoardObject;
    //private Supplier<TileType> test;
    /**
     * Creates a new Serializer with the current board and active players.
     * @param gameBoard Current game board.
     * @param players Array that holds the active players.
    */
    public Deserializer(Board gameBoard, Player[] players){
        this.gameBoard = gameBoard;
        this.gamePlayers = players;
        this.playerObject = new JSONObject();
        this.mainBoardObject = new JSONObject();
        this.mainManager = new FileManager();
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
            for (int i = 0; i < gameObjects.size(); i++) {
                JSONObject jsonObject = (JSONObject)gameObjects.get(i);
                parseGameObject(jsonObject, i);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            //success = false;
        }
    }
    
    //Try to generalize
    private void parseGameObject(JSONObject gameObject, int counter) {
        if(counter == 0) {
            playerObject = (JSONObject) gameObject.get("player1");
            managePlayers();
        } else{
            if(counter == 1){
                playerObject = (JSONObject) gameObject.get("player2");
                managePlayers();
            } else {
                mainBoardObject = (JSONObject) gameObject.get("board");
                manageBoard();
            }
        }
    }
    
    /**
     * Collects all the information from the game board and saves it in a JSONObject.
    */
    @Override
    public void manageBoard(){
        manageBoardRows();
        manageBoardColumns();
        //manageBoardVerticesAmount();
        manageBoardVertices();
        //manageGraphAdjacentMatrix();
        //System.out.println(gameBoard.toString());
    }
    
    public void manageBoardRows(){
        int valueToInsert = 0;
        Long amountRows = (Long) mainBoardObject.get("amountRows");
        valueToInsert = amountRows.intValue();
        gameBoard.setRows(valueToInsert);
    }
    
    public void manageBoardColumns(){
        int valueToInsert = 0;
        Long amountColumns = (Long) mainBoardObject.get("amountColumns");
        valueToInsert = amountColumns.intValue();
        gameBoard.setColumns(valueToInsert);
    }
    
    public void manageBoardVertices(){
        UrTile currentTile;
        Long valueToCast = null;
        int valueToInsert = 0;
        JSONArray vertices = new JSONArray();
        vertices = (JSONArray) mainBoardObject.get("vertices");
        for (int i = 0; i < vertices.size(); i++) {
            JSONObject jsonObject = (JSONObject)vertices.get(i);
            currentTile = (UrTile) gameBoard.getVerticesArray().get(i);
            manageRowVertices(jsonObject, currentTile, valueToCast, valueToInsert);
            manageColumnVertices(jsonObject, currentTile, valueToCast, valueToInsert);
            //currentTile.setPiece((Piece)jsonObject.get("piece")); //Needs changes
            currentTile.setIsSafe((boolean)jsonObject.get("isSafe"));
        }
    }
        
    public void manageRowVertices(JSONObject jsonObject, UrTile currentTile, Long valueToCast, int valueToInsert){
        valueToCast = (Long)jsonObject.get("row");
        valueToInsert = valueToCast.intValue();
        currentTile.setRow(valueToInsert);
    }
    
    public void manageColumnVertices(JSONObject jsonObject, UrTile currentTile, Long valueToCast, int valueToInsert){
        valueToCast = (Long)jsonObject.get("column");
        valueToInsert = valueToCast.intValue();
        currentTile.setColumn(valueToInsert);
    }
    /*
    public void manageGraphAdjacentMatrix(){
        JSONArray jsonGraphAdjacentMatrix = (JSONArray) mainBoardObject.get("graphAdjacentMatrix");
        int verticesAmount = gameBoard.getVerticesAmount();
        //boolean[][] graphAdjacentMatrix = new boolean[verticesAmount][verticesAmount];
        //graphAdjacentMatrix = (boolean[][])jsonGraphAdjacentMatrix.get("graphAdjacentMatrix");
        for (int vertexIndex1 = 0; vertexIndex1 < verticesAmount; vertexIndex1++) {
            for (int vertexIndex2 = 0; vertexIndex2 < verticesAmount; vertexIndex2++) {
                gameBoard.getAdjacentMatrix()[vertexIndex1][vertexIndex1] = (boolean)jsonGraphAdjacentMatrix.get(vertexIndex2);
            }
        }
    }*/
    
    
    
    /**
     * Collects all the information from the actives players and saves it in a JSONObject.
    */
    @Override
    public void managePlayers(){
        //Get player1's name
        String name = (String) playerObject.get("name");
        System.out.println(name);
        //Get player1's score
        Long score = (Long) playerObject.get("score");    
        System.out.println(score);
        //Get player1's piecesAmount
        Long piecesAmount = (Long) playerObject.get("piecesAmount");    
        System.out.println(piecesAmount);
        //Get player1's playerColor
        String playerColor = (String) playerObject.get("playerColor");    
        System.out.println(playerColor);
        //Get player1's pieces
        JSONArray pieces = (JSONArray) playerObject.get("pieces");    
        System.out.println(pieces);
    }
    /*
    public void managePlayersName(int currentPlayer){
        String name = (String) player1Object.get("name");
        gamePlayers[currentPlayer];
    }*/
}
