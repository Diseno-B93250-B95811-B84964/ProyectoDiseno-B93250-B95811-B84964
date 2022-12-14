/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Manages the data of the classes to load into a JSON file.
 * @author Álvaro Miranda.
 */
public class JSONSerializer extends DataManager{
    /**
    * JSONObject that holds all the information associated to player 1.
    */
    private JSONObject jsonPlayer1;
    /**
    * JSONObject that holds all the information associated to player 1.
    */
    private JSONObject jsonPlayer2;
    /**
    * JSONObject that holds all the information associated to the game board.
    */
    private JSONObject jsonBoard;
    /**
    * JSONArray that holds all the information associated to the vertices that conform the board.
    */
    private JSONArray jsonVertices;
    
    /**
     * Creates a new Serializer with the current board and active players.
     * @param gameBoard Current game board.
     * @param players Array that holds the active players.
    */
    public JSONSerializer(Board gameBoard, Player[] players){
        jsonPlayer1 = new JSONObject();
        jsonPlayer2 = new JSONObject();
        jsonBoard = new JSONObject();
        jsonVertices = new JSONArray();
        this.gameBoard = gameBoard;
        this.gamePlayers = players;
        this.mainManager = new FileManager();
    }
    
    /**
     * Calls methods that creates a json file using the file contents that come from the game.
     * @return success Indicates if the creation of the file was a success.
    */
    @Override
    public void execute(){
        try {
            manageFile();
            mainManager.saveFile("output", ".json", "src\\main\\java\\auxiliaryFiles\\");
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * Calls methods collect the data from the game sections.
    */
    @Override
    protected void manageFile(){
        manageBoard();
        managePlayers();
        JSONObject mainBoard = new JSONObject();
        mainBoard.put("board", jsonBoard);
        mainManager.getFileContents().add(jsonPlayer1.toString());
        mainManager.getFileContents().add(jsonPlayer2.toString());
        mainManager.getFileContents().add(mainBoard.toString());
    }
    
    /**
     * Collects all the information from the game board and saves it in a JSONObject.
    */
    @Override
    protected void manageBoard(){
        manageVertices();
    }
    
    /**
     * Collects all the information from the actives players and saves it in a JSONObject.
    */
    @Override
    protected void managePlayers(){
        JSONArray playersPiecesArray = new JSONArray();
        JSONObject playerToInsert;

        int currentPlayer = 1;
        ArrayList<UrPiece> playerPieces = new ArrayList<>(gamePlayers[0].getPiecesAmount());
        playerPieces = gamePlayers[0].getPiecesArray();
        
        for (Player gamePlayer : gamePlayers) {
            playerToInsert = new JSONObject();
            
            playerToInsert.put("playerColor", (int)gamePlayer.getColor().getRGB());
            playerToInsert.put("name", gamePlayer.getName());
            playerToInsert.put("score", gamePlayer.getScore());
            
            playersPiecesArray = managePlayerPieces(playerPieces);
            
            playerToInsert.put("pieces",playersPiecesArray);
            
            if(currentPlayer == 1){
                jsonPlayer1.put("player1", playerToInsert);
            } else {
                jsonPlayer2.put("player2", playerToInsert);
            }
            currentPlayer++;
            playerPieces = gamePlayers[1].getPiecesArray();
        }
    }
    
    /**
     * Collects all the information from the current player array of pieces and saves it in a JSONArray.
     * @param playerPieces Contains the players array of pieces.
     * @return playersPiecesArray Json information from the pieces of the current player.
    */
    private JSONArray managePlayerPieces(ArrayList<UrPiece> playerPieces){
        JSONArray playersPiecesArray = new JSONArray();
        JSONObject individualPieceFromArray = new JSONObject();
        
        for(int pieces = 0; pieces < playerPieces.size(); pieces++){
            individualPieceFromArray.put("pieceColor", playerPieces.get(pieces).getColor().getRGB());
            playersPiecesArray.add(individualPieceFromArray);
        }
        return playersPiecesArray;
    }
    
    /**
     * Collects all the information from the vertices that conform the board of 
     * pieces and saves it in a JSONArray, that gets put into the game board JSONObject.
    */
    private void manageVertices(){
        JSONObject pieceData;
        JSONObject currentTileToInsert;
        UrPiece auxPiece;
        int verticesAmount = gameBoard.getVerticesAmount();
        
        ArrayList<UrTile> vertices = new ArrayList<>(verticesAmount);
        vertices = gameBoard.getVerticesArray();
        
        for (int vertexIndex = 0; vertexIndex < verticesAmount; vertexIndex++) {
            currentTileToInsert = new JSONObject();
            currentTileToInsert.put("row", vertices.get(vertexIndex).getRow());
            currentTileToInsert.put("column", vertices.get(vertexIndex).getColumn());
            if(vertices.get(vertexIndex).getPiece() != null){
                pieceData = new JSONObject();
                pieceData.put("pieceColor", vertices.get(vertexIndex).getPiece().getColor().getRGB());
                auxPiece = (UrPiece)vertices.get(vertexIndex).getPiece();
                currentTileToInsert.put("piece", pieceData);
            } else {
                currentTileToInsert.put("piece", "null");
            }
            currentTileToInsert.put("isVacant", vertices.get(vertexIndex).isVacant());
            currentTileToInsert.put("isSafe", vertices.get(vertexIndex).isSafe());
            jsonVertices.add(currentTileToInsert);
        }
        jsonBoard.put("vertices", jsonVertices);
    }
    
    /**
     * Returns the player 1 json information.
     * @return jsonPlayer1.toString() Player 1's information.
    */
    public String getJSONPlayer1(){
        return jsonPlayer1.toString();
    }
    
    /**
     * Returns the player 2 json information.
     * @return jsonPlayer2.toString() Player 1's information.
    */
    public String getJSONPlayer2(){
        return jsonPlayer2.toString();
    }
    
    /**
     * Returns the main game information.
     * @return jsonBoard.toString() Player 1's information.
    */
    public String getJSONBoard(){
        return jsonBoard.toString();
    }
}
