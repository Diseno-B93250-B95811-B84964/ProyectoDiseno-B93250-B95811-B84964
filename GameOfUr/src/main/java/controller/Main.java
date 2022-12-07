/*
 * Game of Ur based on MARDA framework.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package controller;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import model.Board;
import model.Deserializer;
import model.Dice;

import model.Piece;
import model.Player;
import model.Rules;
import model.Tile;
import model.UrPiece;
import model.UrPlayer;
import model.UrTile;
import model.FileManager;
import model.Serializer;

/**
* Main class that starts program.
* @author Mauricio Palma, Alvaro Miranda, Jimena Gdur
*/
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Doesn't test other methods that can't be accesed through parent
            System.out.println("Testing classes");
            
            //System.out.println("\nTesting Tile and Piece classes");
            //testTileAndPieceClasses();
            
            //System.out.println("\nTesting Board class");
            //testBoardClasses();
            
            //System.out.println("Testing Player classes");
            //testPlayerClasses();
            
            //System.out.println("Testing Rules class");
            //testRulesClass();
            
            //System.out.println("Testing Dice class");
            //testDiceClass();
            
            //System.out.println("Testing Player classes");
            //testPlayerClasses();
            
            //System.out.println("Testing file creation");
            //testSavingFile();
            
            //System.out.println("Testing vertices to string");
            //System.out.println(testGameToJSON());
            
            System.out.println("Testing json file creation");
            testJSONCreation();
            
            System.out.println("Testing deserializer");
            testDeserializer();
        });
    }
    
    public static void testTileAndPieceClasses() {
        Tile tile = new UrTile(2,3, false);
        System.out.println("Tile after constructor: " + tile);
        Piece piece = new UrPiece(Color.RED);
        System.out.println("Piece after constructor: " + piece);
        System.out.println("Setting piece in tile: " + piece);
        tile.setPiece(piece);
        System.out.println("Tile after: " + tile.toString());
        System.out.println("Piece after: " + piece.toString());
        
        // TODO: piece isInPlay is not changed
    }
    
    public static void testBoardClasses() {
        int vertexNumberFromFile = 24;
        int rowNumberFromFile = 8;
        int colNumberFromFile = 3;
        
        Board board = new Board(UrTile::new, vertexNumberFromFile, rowNumberFromFile, colNumberFromFile);
        //System.out.println("Board after constructor: " + board);
        
        // TODO: set safe tiles
        Tile safeTile1 = board.getTile(0, 0);
        //safeTile1.setAsSafe();
        Tile safeTile2 = board.getTile(0, 2);
        //safeTile2.setAsSafe();
        Tile safeTile3 = board.getTile(6, 0);
        //safeTile3.setAsSafe();
        Tile safeTile4 = board.getTile(6, 2);
        //safeTile4.setAsSafe();
        
        boolean[][] adjacentMatrixFromFile =
        {
            {false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, // 0
            {false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, // 1
            {false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, // 2
            {true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, // 3
            {false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, // 4
            {false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, // 5
            {false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, // 6
            {false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false}, // 7
            {false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, // 8
            {false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, // 9
            {false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false}, // 10
            {false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, // 11
            {false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, // 12
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false}, // 13
            {false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false}, // 14
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, // 15
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false}, // 16
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, // 17
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false}, // 18
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false}, // 19
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false}, // 20
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false}, // 21
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, true},  // 22
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false}, // 23
        };
        board.setAdjacentMatrix(adjacentMatrixFromFile);
        //System.out.println("Board after setting adjacentMatrix: " + board);
        
        System.out.println("Getting tile adjacents:");
        ArrayList<Integer> possibleTiles = board.getTilesAdjacents(5,1, 3);
        System.out.println("possibleTiles: " + possibleTiles);
        //board.setPieceInTile(3,0, )
                
        //ArrayList<Integer> possibleTiles1 = board.getTilesAdjacents(7,1, 3);
    }
    
    public static void testPlayerClasses() {
        Player player = new UrPlayer(UrPiece::new, 7, Color.RED, "Maria");
        System.out.println(player + "\n");
        
        // TODO set is in playe during game making sure piece is not null
        UrPiece piece1 = (UrPiece)player.getAvailablePiece();
        System.out.println("Available piece 1: " + piece1);
        piece1.setInPlay();
        System.out.println("Player after setting 1 piece in play:\n" + player + "\n");
        
        UrPiece piece2 = (UrPiece)player.getAvailablePiece();
        System.out.println("Available piece 2: " + piece2);
        piece2.setInPlay();
        System.out.println("Player after setting 2 pieces in play:\n" + player + "\n");
        
        UrPiece piece3 = (UrPiece)player.getAvailablePiece();
        System.out.println("Available piece 3: " + piece3);
        piece3.setInPlay();
        System.out.println("Player after setting 3 pieces in play:\n" + player + "\n");
        
        UrPiece piece4 = (UrPiece)player.getAvailablePiece();
        System.out.println("Available piece 4: " + piece4);
        piece4.setInPlay();
        System.out.println("Player after setting 4 pieces in play:\n" + player + "\n");
        
        UrPiece piece5 = (UrPiece)player.getAvailablePiece();
        System.out.println("Available piece 5: " + piece5);
        piece5.setInPlay();
        System.out.println("Player after setting 5 pieces in play:\n" + player + "\n");
        
        UrPiece piece6 = (UrPiece)player.getAvailablePiece();
        System.out.println("Available piece 6: " + piece6);
        piece6.setInPlay();
        System.out.println("Player after setting 6 pieces in play:\n" + player + "\n");
        
        UrPiece piece7 = (UrPiece)player.getAvailablePiece();
        System.out.println("Available piece 7: " + piece7);
        piece7.setInPlay();
        System.out.println("Player after setting 7 pieces in play:\n" + player + "\n");
        
        UrPiece piece8 = (UrPiece)player.getAvailablePiece();
        System.out.println("Available piece 8: " + piece8);
        //piece8.setInPlay();
        player.modifyScore();
        System.out.println("Player after setting 8 pieces in play:\n" + player + "\n");
    }

    private static void testRulesClass() {
        Rules rules = new Rules();
        System.out.println("rules: " + rules.getRules()); 
    }
    
    private static void testDiceClass() {
        float[] probabilities = { 10, 10, 50, 10, 20 };
        Dice dice = new Dice(5, probabilities);
        System.out.println("dice: " + (dice.throwDice() - 1));
        System.out.println("dice: " + (dice.throwDice() - 1));
        System.out.println("dice: " + (dice.throwDice() - 1));
        System.out.println("dice: " + (dice.throwDice() - 1));
        System.out.println("dice: " + (dice.throwDice() - 1));
        System.out.println("dice: " + (dice.throwDice() - 1));
    }
    
    public static void testSavingFile() {
        String fileSeparator = System.getProperty("file.separator");
        //"C:" + File.separator + "hello" + File.separator + "hi.txt";
        FileManager testManager = new FileManager();
        testManager.fileContents.add("{\"name\":\"sonoo\",\"salary\":600000.0,\"age\":27}");
        testManager.saveFile("output", ".json", "C:\\Users\\Usuario1\\Documents\\NetBeansProjects\\ProyectoDiseno-B93250-B95811-B84964\\");
        System.out.println("Creating file was a success");
    }
    
    public static String testGameToJSON(){
        int vertexNumberFromFile = 24;
        int rowNumberFromFile = 8;
        int colNumberFromFile = 3;
        String output = "";
        
        Player player1 = new UrPlayer(UrPiece::new, 7, Color.RED, "Maria");
        Player player2 = new UrPlayer(UrPiece::new, 7, Color.BLUE, "Edgardo");
        Player[] players = new Player[2];
        
        players[0] = player1;
        players[1] = player2;
        
        Board board = new Board(UrTile::new, vertexNumberFromFile, rowNumberFromFile, colNumberFromFile);
        
        System.out.println("Vertices Amount");
        System.out.println(board.getVerticesAmount());
        Serializer testSerializer = new Serializer(board, players);
        //output = testSerializer.manageBoard();
        testSerializer.managePlayers();
        System.out.println(testSerializer.getJSONPlayer1());
        System.out.println(testSerializer.getJSONPlayer2());
        return output;
    }
    
    public static void testJSONCreation(){
        int vertexNumberFromFile = 24;
        int rowNumberFromFile = 8;
        int colNumberFromFile = 3;
           
        Player player1 = new UrPlayer(UrPiece::new, 7, Color.RED, "Maria");
        Player player2 = new UrPlayer(UrPiece::new, 7, Color.BLUE, "Edgardo");
        Player[] players = new Player[2];
        
        players[0] = player1;
        players[1] = player2;
        
        Board board = new Board(UrTile::new, vertexNumberFromFile, rowNumberFromFile, colNumberFromFile);
        
        Serializer testSerializer = new Serializer(board, players);
        
        testSerializer.execute();
    }
    
        public static void testDeserializer(){
        int vertexNumberFromFile = 24;
        int rowNumberFromFile = 8;
        int colNumberFromFile = 3;
           
        Player player1 = new UrPlayer(UrPiece::new, 7, Color.RED, "Maria");
        Player player2 = new UrPlayer(UrPiece::new, 7, Color.BLUE, "Edgardo");
        Player[] players = new Player[2];
        
        players[0] = player1;
        players[1] = player2;
        
        Board board = new Board(UrTile::new, vertexNumberFromFile, rowNumberFromFile, colNumberFromFile);
        
        Deserializer testDeserializer = new Deserializer(board, players);
        
        testDeserializer.execute();
    }
}
