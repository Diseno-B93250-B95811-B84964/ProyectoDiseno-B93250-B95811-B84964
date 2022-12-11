/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Manages the data of the classes to load into a JSON file.
 * @author Álvaro Miranda.
 */
public class JSONDeserializer extends DataManager{
    /**
    * JSONObject that holds all the information associated to the board.
    */
    private JSONObject mainBoardObject;
    /**
    * ArraList of JSONObjects that holds all the information associated to the players.
    */
    private ArrayList<JSONObject> jsonPlayers;
    /**
    * Reference to the players in the array.
    */
    private int currentPlayer;
    /**
     * Creates a new Serializer with the current board and active players.
     * @param gameBoard Current game board.
     * @param players Array that holds the active players.
    */
    public JSONDeserializer(Board gameBoard, Player[] players){
        this.gameBoard = gameBoard;
        this.gamePlayers = players;
        this.mainBoardObject = new JSONObject();
        this.mainManager = new FileManager();
        this.jsonPlayers = new ArrayList<JSONObject>(players.length);
        this.currentPlayer = 0;
    }
    /**
     * Calls the methods that manage the deserialization of the file.
    */    
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
    protected void manageBoard(){
        JSONObject boardAtributes = new JSONObject();
        boardAtributes = (JSONObject)mainBoardObject.get("board");
        manageBoardVertices(boardAtributes);
    }
    /**
     * Deserializes the games amount of rows.
     */
    private void manageBoardRows(){
        gameBoard.setRows(castLongToInt((Long) mainBoardObject.get("amountRows")));
    }
    /**
     * Deserializes the games amount of columns.
     */
    private void manageBoardColumns(){
        gameBoard.setColumns(castLongToInt((Long) mainBoardObject.get("amountColumns")));
    }
    /**
     * Deserializes the boards vertices.
     * @param boardAtributes All the attributes associated with the board.
    */
    private void manageBoardVertices(JSONObject boardAtributes){
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
    /**
     * Deserializes if a tile from the vertice is vacant.
     * @param tileJSONObject All the attributes associated with a tile on the board.
     * @param currentTile Reference to a tile int the board.
    */
    private void manageIsVacant(JSONObject tileJSONObject, UrTile currentTile){
        currentTile.setIsVacant((boolean)tileJSONObject.get("isVacant"));
    }
    /**
     * Deserializes the tiles row.
     * @param tileJSONObject All the attributes associated with a tile on the board.
     * @param currentTile Reference to a tile int the board.
    */
    private void manageRowVertices(JSONObject tileJSONObject, UrTile currentTile){
        currentTile.setRow(castLongToInt((Long)tileJSONObject.get("row")));
    }
    /**
     * Deserializes the tiles column.
     * @param tileJSONObject All the attributes associated with a tile on the board.
     * @param currentTile Reference to a tile int the board.
    */
    private void manageColumnVertices(JSONObject tileJSONObject, UrTile currentTile){
        currentTile.setColumn(castLongToInt((Long)tileJSONObject.get("column")));
    }
    /**
     * Deserializes depending on if a tile has a piece or not.
     * @param tileJSONObject All the attributes associated with a tile on the board.
     * @param currentTile Reference to a tile int the board.
    */
    private void manageTilePieces(JSONObject tileJSONObject, UrTile currentTile){
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
    protected void managePlayers(){
        String playerKey = "player" + (currentPlayer + 1);
        JSONObject auxPlayer = new JSONObject();
        JSONObject playerAtributes = new JSONObject();
        auxPlayer = jsonPlayers.get(currentPlayer);
        playerAtributes = (JSONObject)auxPlayer.get(playerKey);
        manageCurrentPlayer(playerAtributes, currentPlayer);
    }
    /**
     * Manages the deserialization of an individual player.
     * @param jsonPlayer The object that contains all the information from a player.
     * @param currentPlayer The position of the current player within the array of players.
    */
    private void manageCurrentPlayer(JSONObject jsonPlayer, int currentPlayer){
        managePlayerName(jsonPlayer, currentPlayer);
        managePlayerColor(jsonPlayer, currentPlayer);
        managePlayerScore(jsonPlayer, currentPlayer);
        managePlayerPieces(jsonPlayer, currentPlayer);
    }
    /**
     * Deserializes the players name.
     * @param jsonPlayer The object that contains all the information from a player.
     * @param currentPlayer The position of the current player within the array of players.
    */
    private void managePlayerName(JSONObject jsonPlayer, int currentPlayer){
        gamePlayers[currentPlayer].setName((String)jsonPlayer.get("name"));
    }
    /**
     * Deserializes the players color.
     * @param jsonPlayer The object that contains all the information from a player.
     * @param currentPlayer The position of the current player within the array of players.
    */
    private void managePlayerColor(JSONObject jsonPlayer, int currentPlayer){
        Color currentPlayerColor = new Color(castLongToInt((Long)jsonPlayer.get("playerColor")));
        gamePlayers[currentPlayer].setColor(currentPlayerColor);
    }
    /**
     * Deserializes the players score.
    */
    private void managePlayerScore(JSONObject jsonPlayer, int currentPlayer){
        //System.out.println(mainBoardObject.get("score"));
        gamePlayers[currentPlayer].setScore(castLongToInt((Long) jsonPlayer.get("score")));
    }
    /**
     * Deserializes the players array of pieces.
     * @param jsonPlayer The object that contains all the information from a player.
     * @param currentPlayer The position of the current player within the array of players.
    */
    private void managePlayerPieces(JSONObject jsonPlayer, int currentPlayer){
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
    /**
     * Gets the player key to obtain the JSONObject.
     * @param currentPlayer The position of the current player within the array of players.
     * @return The players key.
    */
    private String getPlayerKey(int currentPlayer){
        String playerKey = "";
        if(currentPlayer == 0){
            playerKey = "player1";
        } else {
            playerKey = "player2";
        }
        return playerKey;
    }
    /**
     * Casts a long to an int.
     * @param value Long to be cast to int
     * @return The value in int.
    */
    private int castLongToInt(Long value){
        return value.intValue();
    }
    /**
     * Find the match in color between a piece and a certain player.
     * @param pieceColor Color from the piece to find the match.
     * @return The player that got the match.
    */
    private int getPlayerColorMatch(Color pieceColor){
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
