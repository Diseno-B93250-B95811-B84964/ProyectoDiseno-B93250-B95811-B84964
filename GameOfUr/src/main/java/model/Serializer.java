/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Usuario1
 */
public class Serializer extends JSONManager{
    private JSONObject jsonPlayer1;
    private JSONObject jsonPlayer2;
    private JSONArray jsonPlayerPieces;
    private JSONObject jsonBoard;
    private JSONArray jsonAdyacentMatrix;
    private JSONArray jsonVertices;
    private JSONObject jsonTile;
    private JSONObject jsonPiece;
    
    public Serializer(Board gameBoard, Player[] players){
        jsonPlayer1 = new JSONObject();
        jsonPlayer2 = new JSONObject();
        jsonPlayerPieces = new JSONArray();
        jsonBoard = new JSONObject();
        jsonAdyacentMatrix = new JSONArray();
        jsonVertices = new JSONArray();
        jsonPiece = new JSONObject();
        this.gameBoard = gameBoard;
        this.gamePlayers = players;
        this.mainManager = new FileManager();
    }
    
    @Override
    public boolean execute(){ // add players array
        boolean success = true;
        try {
            manageFile(mainManager.getFileContents());
            mainManager.saveFile("output", ".json", "C:\\Users\\Usuario1\\Documents\\NetBeansProjects\\ProyectoDiseno-B93250-B95811-B84964\\");
        }
        catch(Exception e) {
            System.out.println(e);
            success = false;
        }
        return success;
    }
    
    @Override
    protected void manageFile(ArrayList<String> fileContents){
        JSONArray jsonGameData = new JSONArray();
        manageBoard();
        managePlayers();
        fileContents.add(jsonBoard.toString());
        fileContents.add(jsonPlayer1.toString());
        fileContents.add(jsonPlayer2.toString());
    }
    
    @Override
    public void manageBoard(){
        jsonBoard.put("verticesAmount", gameBoard.getVerticesAmount());
        jsonBoard.put("amountRows", gameBoard.getAmountRows());
        jsonBoard.put("amountColumns", gameBoard.getAmountColumns());
        manageVertices();
        manageAdjacentMatrix();
        //return jsonBoard.toString();
    }
    
    @Override
    public void managePlayers(){
        JSONObject testPiece;
        JSONObject testPlayer;
        JSONArray testPlayerPieces;
        int currentPlayer = 1;
        ArrayList<UrPiece> playerPieces = new ArrayList<>(gamePlayers[0].getPiecesAmount());
        playerPieces = gamePlayers[0].getPiecesArray();
        for (Player gamePlayer : gamePlayers) {
            testPlayer = new JSONObject();
            testPlayerPieces = new JSONArray();
            testPlayer.put("playerColor", gamePlayer.getColor());
            testPlayer.put("name", gamePlayer.getName());
            testPlayer.put("score", gamePlayer.getScore());
            testPlayer.put("piecesAmount", gamePlayer.getPiecesAmount());
            for(int pieces = 0; pieces < playerPieces.size(); pieces++){
                testPiece = new JSONObject();
                
                testPiece.put("pieceColor", playerPieces.get(pieces).getColor());
                testPiece.put("isInPlay", playerPieces.get(pieces).isInPlay());
                testPlayerPieces.add(testPiece);
            }
            testPlayer.put("pieces",testPlayerPieces);

            //jsonPlayers.add(testPlayer);
            if(currentPlayer == 1){
                jsonPlayer1.put("player1", testPlayer);
            } else {
                jsonPlayer2.put("player2", testPlayer);
            }
            currentPlayer++;
            playerPieces = gamePlayers[1].getPiecesArray();
        }
    }
    /*
    public void manageVertices(){
        int verticesAmount = gameBoard.getVerticesAmount();
        ArrayList<UrTile> vertices = new ArrayList<>(verticesAmount);
        vertices = gameBoard.getVerticesArray();
        //vertices = new ArrayList<>(verticesAmount);
        for (int vertexIndex = 0; vertexIndex < verticesAmount; vertexIndex++) {
            System.out.println("Printing vertices size");
            System.out.println(gameBoard.getVerticesArray().size());
            System.out.println("Vertices ROW");
            //System.out.println(vertices.get(vertexIndex).getRow());
            jsonTile.put("row", vertices.get(vertexIndex).getRow());
            jsonTile.put("column", vertices.get(vertexIndex).getColumn());
            if(vertices.get(vertexIndex).getPiece() != null){
                jsonTile.put("piece", vertices.get(vertexIndex).getPiece().toString());
            } else {
                jsonTile.put("piece", "null");
            }
            jsonTile.put("isVacant", vertices.get(vertexIndex).isVacant());
            jsonTile.put("isSafe", vertices.get(vertexIndex).isSafe());
            jsonVertices.add(jsonTile);
        }
        jsonBoard.put("vertices", jsonVertices);
    }*/
    
    //TODO clean method
    public void manageVertices(){
        JSONObject testTile;
        int verticesAmount = gameBoard.getVerticesAmount();
        ArrayList<UrTile> vertices = new ArrayList<>(verticesAmount);
        vertices = gameBoard.getVerticesArray();
        //vertices = new ArrayList<>(verticesAmount);
        for (int vertexIndex = 0; vertexIndex < verticesAmount; vertexIndex++) {
            testTile = new JSONObject();
            //System.out.println(vertices.get(vertexIndex).getRow());
            testTile.put("row", vertices.get(vertexIndex).getRow());
            testTile.put("column", vertices.get(vertexIndex).getColumn());
            if(vertices.get(vertexIndex).getPiece() != null){
                testTile.put("piece", vertices.get(vertexIndex).getPiece().toString());
            } else {
                testTile.put("piece", "null");
            }
            testTile.put("isVacant", vertices.get(vertexIndex).isVacant());
            testTile.put("isSafe", vertices.get(vertexIndex).isSafe());
            jsonVertices.add(testTile);
        }
        jsonBoard.put("vertices", jsonVertices);
    }
    
    //TODO clean method
    public void manageAdjacentMatrix(){
        String boardState = "";
        int verticesAmount = gameBoard.getVerticesAmount();
        boolean[][] adjacentMatrix = new boolean[verticesAmount][verticesAmount];
        adjacentMatrix = gameBoard.getAdjacentMatrix();
        for (int vertexIndex1 = 0; vertexIndex1 < verticesAmount; vertexIndex1++) {
            for (int vertexIndex2 = 0; vertexIndex2 < verticesAmount; vertexIndex2++) {
                if(adjacentMatrix[vertexIndex1][vertexIndex2] == true){
                    jsonAdyacentMatrix.add(true);
                } else {
                    jsonAdyacentMatrix.add(false);
                }
            }
        }
        jsonBoard.put("graphAdjacentMatrix", jsonAdyacentMatrix);
    }
    
    public String getJSONPlayer1(){
        return jsonPlayer1.toString();
    }
    
    public String getJSONPlayer2(){
        return jsonPlayer2.toString();
    }
    
    public String getJSONBoard(){
        return jsonBoard.toString();
    }
}
