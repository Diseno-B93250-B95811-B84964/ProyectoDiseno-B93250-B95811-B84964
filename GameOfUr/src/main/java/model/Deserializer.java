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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Manages the data of the classes to load into a JSON file.
 * @author Álvaro Miranda.
 */
public class Deserializer<TileType extends Tile> extends JSONManager{
    
    private Object mainObject;
    
    private JSONArray mainJSONArray;
    
    private int objectCounter;
    
    /**
     * Creates a new Serializer with the current board and active players.
     * @param gameBoard Current game board.
     * @param players Array that holds the active players.
    */
    public Deserializer(Board gameBoard, Player[] players){
        objectCounter = 0;
        this.gameBoard = gameBoard;
        this.gamePlayers = players;
        mainJSONArray = new JSONArray();
        this.mainManager = new FileManager();
    }
    
    /**
     * Calls methods that creates a json file using the file contents that come from the game.
     * @return success Indicates if the creation of the file was a success.
    */
    /*
    @Override
    public boolean execute(){
        boolean success = true;
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("src\\main\\java\\auxiliaryFiles\\output.json")) {
            JSONObject testJSONArray = new JSONObject();
            mainObject = parser.parse(reader);
            mainJSONArray.add(mainObject);
            System.out.println(mainJSONArray);
            //mainJSONObject =  (JSONObject) mainObject;
            //System.out.println(mainJSONObject.toString());
            for(int remolacha = 0; remolacha <= 0; remolacha++){
                //testJSONArray = mainJSONArray.get(remolacha);
            }
            //ArrayList<TileType> vertices = (ArrayList<TileType>) mainJSONArray.get("vertices");
            //System.out.println(vertices);

            //UrPlayer player1 = (UrPlayer) mainObject.get("player1");
            //System.out.println(player1.toString());
            /*
            // loop array
            JSONArray adjacentMatrix = (JSONArray) mainObject.get("graphAdjacentMatrix");
            Iterator<String> iterator = adjacentMatrix.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }

        } catch (IOException e) {
            e.printStackTrace();
            success = false;
        } catch (ParseException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }*/
    
    //@SuppressWarnings("unchecked")
    public boolean execute(){
        boolean success = true;
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        /*
        try (FileReader reader = new FileReader("src\\main\\java\\auxiliaryFiles\\output.json"))
        {
            //Read JSON file
            Object allGameObjects = jsonParser.parse(reader);
 
            JSONArray gameObjects = (JSONArray) allGameObjects;
            //System.out.println(employeeList);
            //Iterate over game object array
            gameObjects.forEach( gameObject -> parseGameObject( (JSONObject) gameObject, objectCounter ) );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        try{
            mainManager.loadFile("output.json", "src\\main\\java\\auxiliaryFiles\\");
            String jsonContents = String.join(", ", mainManager.getFileContents());
            Object allGameObjects = jsonParser.parse(jsonContents);
            JSONArray gameObjects = (JSONArray) allGameObjects;
            for (int i = 0; i < gameObjects.size(); i++) {
                JSONObject jsonObject = (JSONObject)gameObjects.get(i);
                parseGameObject(jsonObject, i);
                //JSONObject player1Object = (JSONObject) jsonObject.get("player1");
                //String name = (String) player1Object.get("name");    
                //System.out.println(name);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

 

        return success;
    }
 
    private void parseGameObject(JSONObject gameObject, int counter) 
    {
        //objectCounter++;
        if(counter == 0) {
            manageIndividualPlayer(gameObject, "player1");
        } else{
            if(counter == 1){
                manageIndividualPlayer(gameObject, "player2");
            } else {
                manageBoard(gameObject, "board");
            }
        }
        
        
        System.out.println("I just entered");
        //Get employee object within list
        //JSONObject employeeObject = (JSONObject) employee.get("player1");
         
        //Get employee first name
        //JSONArray firstName = (JSONArray) employeeObject.get("pieces");    
        //System.out.println(firstName);
    }
    
    public void manageIndividualPlayer(JSONObject gameObject, String currentPlayer){
        //Get player1 object within list
        JSONObject player1Object = (JSONObject) gameObject.get(currentPlayer);
         
        //Get player1's name
        String name = (String) player1Object.get("name");    
        System.out.println(name);
        //Get player1's score
        Long score = (Long) player1Object.get("score");    
        System.out.println(score);
        //Get player1's name
        Long piecesAmount = (Long) player1Object.get("piecesAmount");    
        System.out.println(piecesAmount);
        //Get player1's name
        String playerColor = (String) player1Object.get("playerColor");    
        System.out.println(playerColor);
        //Get player1's name
        JSONArray pieces = (JSONArray) player1Object.get("pieces");    
        System.out.println(pieces);
    }
    
    
    public void manageBoard(JSONObject gameObject, String gameBoard){
        //Get player1 object within list
        JSONObject player1Object = (JSONObject) gameObject.get(gameBoard);
         
        //Get player1's name
        Long amountOfPlayers = (Long) player1Object.get("amountOfPlayers");    
        System.out.println(amountOfPlayers);
        //Get player1's score
        Long amountRows = (Long) player1Object.get("amountRows");    
        System.out.println(amountRows);
        //Get player1's name
        Long amountColumns = (Long) player1Object.get("amountColumns");    
        System.out.println(amountColumns);
        //Get the amount of vertices
        Long verticesAmount = (Long) player1Object.get("verticesAmount");    
        System.out.println(verticesAmount);
        //Get player1's name
        JSONArray vertices = (JSONArray) player1Object.get("vertices");    
        System.out.println(vertices);
        //Get player1's name
        JSONArray graphAdjacentMatrix = (JSONArray) player1Object.get("graphAdjacentMatrix");    
        System.out.println(graphAdjacentMatrix);
    }
    
    /**
     * Calls methods collect the data from the game sections.
    */
    @Override
    protected void manageFile(ArrayList<String> fileContents){
        
    }
    
    /**
     * Collects all the information from the game board and saves it in a JSONObject.
    */
    @Override
    public void manageBoard(){
        
    }
    
    /**
     * Collects all the information from the actives players and saves it in a JSONObject.
    */
    @Override
    public void managePlayers(){
        
    }
}
