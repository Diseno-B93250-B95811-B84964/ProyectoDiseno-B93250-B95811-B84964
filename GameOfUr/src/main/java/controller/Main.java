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
import model.JSONDeserializer;
import model.CommandInterface;
import model.CommandMovePiece;
import model.CommandValidateWinner;
import model.Dice;
import model.FileManager;

import model.Piece;
import model.Player;
import model.Referee;
import model.Rules;
import model.Tile;
import model.UrPiece;
import model.UrPlayer;
import model.UrTile;
import model.FileManager;
import model.JSONSerializer;
import org.apache.commons.lang3.mutable.MutableBoolean;

/**
* Main class that starts program.
* @author Mauricio Palma, Alvaro Miranda, Jimena Gdur
*/
public class Main {
    
    public static CommandInterface winnerCommand;
    public static CommandInterface moveCommand;
    
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
            
            //System.out.println("Testing Referee classes");
            //System.out.println("Testing Dice class");
            //testDiceClass();
            
            //System.out.println("Testing file creation");
            //testSavingFile();
            
            //System.out.println("Testing vertices to string");
            //System.out.println(testGameToJSON());
            
            //System.out.println("Testing json file creation");
            //testJSONCreation();
            
            System.out.println("Testing deserializer");
            testDeserializer();
            System.out.println("Testing Referee classes");
            //testCommandValidateWinner();
            testCommandMovePiece();
        });
    }
    
    
    
    public static void testTileAndPieceClasses() {
        Tile tile = new UrTile(2,3);
        System.out.println("Tile after constructor: " + tile);
        Piece piece = new UrPiece(Color.RED);
        System.out.println("Piece after constructor: " + piece);
        System.out.println("Setting piece in tile: " + piece);
        tile.setPiece(piece);
        System.out.println("Tile after: " + tile.toString());
        System.out.println("Piece after: " + piece.toString());
        
        // TODO: piece isInPlay is not changed
    }
    
    public static Board testBoardClasses() {
        int vertexNumberFromFile = 24;
        int rowNumberFromFile = 8;
        int colNumberFromFile = 3;
        Tile urTile = new UrTile();
        
        /*
        Board board = new Board(UrTile::new, vertexNumberFromFile, rowNumberFromFile, colNumberFromFile);
        Player player1 = new UrPlayer(UrPiece::new, 7, Color.RED, "Maria");
        Player player2 = new UrPlayer(UrPiece::new, 7, Color.BLUE, "Edgardo");*/
        
        Player[] players = new Player[2];
        
        /*players[0] = player1;
        players[1] = player2;*/
        Board board = new Board(vertexNumberFromFile, rowNumberFromFile, colNumberFromFile, urTile);
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


        //System.out.println("Board before setting adjacentMatrix: " + board);
        ArrayList<ArrayList<Boolean>> boolMatrix = readAdjacentMatrixFromFile();
        board.setAdjacentMatrix(boolMatrix);
        //System.out.println("Board after setting adjacentMatrix: " + board);
        
        //System.out.println("Getting tile adjacents:");
        //ArrayList<Integer> possibleTiles = board.getTilesAdjacents(5,1, 3);
        //System.out.println("possibleTiles: " + possibleTiles);
        /*
        board.setPieceInTile(3,0, player1.getAvailablePiece());
        System.out.println("Board before setting adjacentMatrix: " + board);
        Serializer serializer = new Serializer(board, players);
        serializer.execute();*/
        //Deserializer deserializer = new Deserializer(board, players);
        
        //ArrayList<Integer> possibleTiles1 = board.getTilesAdjacents(7,1, 3);
        
        return board;
    }
    
    private static ArrayList<ArrayList<Boolean>> readAdjacentMatrixFromFile(){
        // Reads from file
        FileManager fileManager = new FileManager();
        fileManager.loadFile("gameData.csv", "src/main/java/auxiliaryFiles/");
        ArrayList<String> stringArray = fileManager.getFileContents();
        
        // Gets amount of rows and columns
        //String boardDimensions = stringArray.get(0);
        
        // Gets amount of vertices
        //String amountVertices = stringArray.get(1);
        
        // Creates new array without first 2 rows
        System.out.println(stringArray.size());
        int length = stringArray.size() - 3;
        int currentRow = 3;
    
        ArrayList<String> adjacentArray = new ArrayList<>(length);
        for (int rowIndex = 0; rowIndex < length; rowIndex++) {
            adjacentArray.add(stringArray.get(currentRow++));
        }
        
        // Converts from ArrayList<String> to ArrayList<ArrayList<Boolean>>
        ArrayList<ArrayList<String>> stringMatrix = fileManager.splitArray(adjacentArray, ",");
        ArrayList<ArrayList<Boolean>> boolMatrix = fileManager.convertFromStringToBoolean(stringMatrix);
        
        return boolMatrix;
    }
    
    public static void testPlayerClasses() {
        //  public UrPlayer(int amountPieces, Color color, String name)
        
        Piece urPiece = new UrPiece();
        // int amountPieces, Color color, String name, PieceType pieceType
        Player urPlayer = new UrPlayer(7,Color.BLACK,"San Juan de Diosito", (UrPiece) urPiece);
        
        Tile urTile = new UrTile();
        //urPlayer.initializePiecesArray(urPiece);
        
        //Player<UrPiece> player = new UrPlayer<>(7,Color.RED,"Miguelito", urPiece);
        
        //System.out.println("ToString: " + player);
        //public Referee(int players, int pieces, PlayerType playerType, PieceType pieceType)
        
        
        ArrayList<ArrayList<Boolean>> boolMatrix = readAdjacentMatrixFromFile();
        Referee<UrPlayer, UrPiece, UrTile> ref = new Referee(8,3,2,7,24, boolMatrix, urPlayer, urPiece, urTile);


        System.out.println("After ref...");
        
        System.out.println("Ref is " + ref.getPlayerString()); // TODO check is color
        
        /*
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
        System.out.println("Player after setting 8 pieces in play:\n" + player + "\n");*/
    }

    private static void testRulesClass() {
        Rules rules = new Rules();
        System.out.println("rules: " + rules.getRules()); 
    }
    
    private static Dice testDiceClass() {
        int[] probabilities = { 20, 20, 20, 20, 20 };
        Dice dice = new Dice(5, probabilities);
        /*System.out.println("dice: " + (dice.throwDice() - 1));
        System.out.println("dice: " + (dice.throwDice() - 1));
        System.out.println("dice: " + (dice.throwDice() - 1));
        System.out.println("dice: " + (dice.throwDice() - 1));
        System.out.println("dice: " + (dice.throwDice() - 1));*/
        System.out.println("dice: " + (dice.throwDice() - 1));
        return dice;
    }
    
    public static void testCommandValidateWinner(){
        Piece urPiece = new UrPiece();
        Player urPlayer1 = new UrPlayer(7,Color.RED,"San Juan", (UrPiece) urPiece);
        Player urPlayer2 = new UrPlayer(7,Color.BLUE,"Martin", (UrPiece) urPiece);
        Player urPlayer3 = new UrPlayer(7,Color.WHITE,"Maria", (UrPiece) urPiece);

        ArrayList<Player> playerArray = new ArrayList<>();
        playerArray.add(urPlayer1);
        playerArray.add(urPlayer2);

        MutableBoolean pieceEaten = new MutableBoolean(false);
        
        int currentPlayer = 0;
        winnerCommand = new CommandValidateWinner(playerArray, currentPlayer, 7);
        
        boolean result = winnerCommand.execute();
        System.out.println("Winner command result is: " + result);

        playerArray.add(urPlayer3);
        result = winnerCommand.execute();
        System.out.println("Winner command result is: " + result);
        
        playerArray.get(0).setScore(2);
        for (int i = 0; i < 10; i++) {
            result = winnerCommand.execute();
            System.out.println("Winner command result is: " + result);
        }
        playerArray.get(0).setScore(7);
        result = winnerCommand.execute();
        System.out.println("Winner command result is: " + result);
    }
    
    public static void testCommandMovePiece() {
        Board board = testBoardClasses();
        UrTile safeTile1 = (UrTile) board.getTile(0, 0);
        safeTile1.setAsSafe();
        UrTile safeTile2 = (UrTile) board.getTile(0, 2);
        safeTile2.setAsSafe();
        UrTile safeTile3 = (UrTile) board.getTile(6, 0);
        safeTile3.setAsSafe();
        UrTile safeTile4 = (UrTile) board.getTile(6, 2);
        safeTile4.setAsSafe();
        
        Dice dice = testDiceClass();
        
        Tile clickedTile = board.getTile(0, 0);
        Tile nextTile = new Tile();
        Boolean pieceEaten = new Boolean(false);
        
        Piece urPiece = new UrPiece();
        ArrayList<Player> urPlayers = new ArrayList<>(2);
        urPlayers.add(0, new UrPlayer(7,Color.BLACK,"San Juan de Diosito", (UrPiece) urPiece));
        urPlayers.add(1, new UrPlayer(7,Color.RED,"Lucia", (UrPiece) urPiece));
        int[] playerColumns = {0,2};
        
        UrPiece player2piece = (UrPiece) urPlayers.get(1).getAvailablePiece();
        board.getTile(2, 1).setPiece(player2piece);
        System.out.println("board.getTile(2, 1): " + board.getTile(2, 1));
        player2piece.setInPlay();
        
        System.out.println("Sending nextTile: " + nextTile);
        moveCommand = new CommandMovePiece(board, urPlayers, 0, dice, clickedTile, nextTile, playerColumns, 1, pieceEaten);
        moveCommand.execute();
        System.out.println("After setting nextTile: " + nextTile);
        System.out.println("After setting pieceEaten: " + pieceEaten);
    }
    
    public static String testGameToJSON(){
        int vertexNumberFromFile = 24;
        int rowNumberFromFile = 8;
        int colNumberFromFile = 3;
        String output = "";
        
        /*Player player1 = new UrPlayer(UrPiece::new, 7, Color.RED, "Maria");
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
        System.out.println(testSerializer.getJSONPlayer2());*/
        return output;
    }
    
    public static void testJSONCreation(){
        int vertexNumberFromFile = 24;
        int rowNumberFromFile = 8;
        int colNumberFromFile = 3;
           
        /*Player player1 = new UrPlayer(UrPiece::new, 7, Color.RED, "Maria");
        Player player2 = new UrPlayer(UrPiece::new, 7, Color.BLUE, "Edgardo");
        Player[] players = new Player[2];
        
        players[0] = player1;
        players[1] = player2;
        
        Board board = new Board(UrTile::new, vertexNumberFromFile, rowNumberFromFile, colNumberFromFile);
        
        Serializer testSerializer = new Serializer(board, players);
        
        testSerializer.execute();*/
    }
    
        public static void testDeserializer(){
        int vertexNumberFromFile = 24;
        int rowNumberFromFile = 8;
        int colNumberFromFile = 3;
           
        /*Player player1 = new UrPlayer(UrPiece::new, 7, Color.YELLOW, "Julio");
        Player player2 = new UrPlayer(UrPiece::new, 7, Color.BLACK, "Juana");
        Player[] players = new Player[2];
        
        players[0] = player1;
        players[1] = player2;
        
        Board board = new Board(UrTile::new, vertexNumberFromFile, rowNumberFromFile, colNumberFromFile);
        
        Deserializer testDeserializer = new Deserializer(board, players);
        
        testDeserializer.execute();
        System.out.println(board);
        System.out.println(player1);
        System.out.println(player2);*/
        
    }
}
