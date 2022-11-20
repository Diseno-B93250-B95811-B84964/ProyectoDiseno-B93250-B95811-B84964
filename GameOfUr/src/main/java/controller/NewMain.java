/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controller;

import com.opencsv.CSVWriter;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import model.UrBoardModel;
import model.UrDeserializerModel;
import model.UrTileModel;
import model.UrPieceModel;
import model.UrPlayerModel;
import model.UrSerializerModel;

/**
 *
 * @author mauup
 */
public class NewMain {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        UrPlayerModel player1 = new UrPlayerModel(Color.RED, "Rodolfo", 0, 0);
        UrPlayerModel player2 = new UrPlayerModel(Color.BLUE, "Maria", 0, 2);
        UrBoardModel board = new UrBoardModel(Color.RED, Color.BLUE);
        UrSerializerModel state = new UrSerializerModel(board, player1, player2);
        
        // jugador 1
        UrPieceModel piece1 = player1.getPlayerPiece(0);
        board.setPiece(0, 1, piece1);

        // jugador 2
        UrPieceModel piece2 = player2.getPlayerPiece(1);
        board.setPiece(1, 2, piece2);

        // jugador 2
        UrPieceModel piece3 = player2.getPlayerPiece(2);
        board.setPiece(6, 1, piece3);
        
        // jugador 1
        UrPieceModel piece4 = player1.getPlayerPiece(2);
        board.setPiece(3, 0, piece4);
        board.setPlayerTurn(player1.getColor());
        
        ArrayList<String[]> array = state.saveGameState();
        
        CSVWriter writer = new CSVWriter(new FileWriter("testData//sample.csv"));
        for (String[] rowString : array) {
            for (String string : rowString) {
                System.out.print(string + ",");
            }
            System.out.println();
            writer.writeNext(rowString);
        }
        writer.flush();
        
        ////////////////////////////////////////////////////////////////////////
        
        UrPlayerModel[] playerArray = new UrPlayerModel[2];
        playerArray[0] = new UrPlayerModel(0);
        playerArray[1] = new UrPlayerModel(2);
        
        UrBoardModel board2 = new UrBoardModel();
        
        UrDeserializerModel des = new UrDeserializerModel(playerArray, board2);
        System.out.println("\nbefore load BOARD: " + board2.getPlayerTurn());
        des.loadGameState(array);
        
        System.out.println("\nafter load BOARD: " + board2.getPlayerTurn());
        for (int x = 0; x < UrBoardModel.ROWS; x++) {
            for (int y = 0; y < UrBoardModel.COLUMNS; y++) {
                UrTileModel tile = board2.getTile(x, y);
                if (!tile.isVacant()) {
                    System.out.println(x + "," + y + ": " + tile.getPiece().getColor());
                }
            }
        }
        
        System.out.println("");
        System.out.println("");

        UrSerializerModel state2 = new UrSerializerModel(board2, playerArray[0], playerArray[1]);
        
        ArrayList<String[]> array3 = state2.saveGameState();
        for (String[] rowString : array3) {
            for (String string : rowString) {
                System.out.print(string + ",");
            }
            System.out.println();
        }
        
        /*String[] bookHeader = {"BookName", "Author Of the book"};
        String[] bookName1 = {"25St", "Chetak"};
        String[] bookName2 = {"Open", "Andre"};
        String[] bookName3 = {"Power", "Jamek"};

        
        List<String[]> s = new ArrayList<String[]>();
        s.add(bookHeader);
        s.add(bookName1);
        s.add(bookName2);
        s.add(bookName3);
        
        //FileOutputStream fos = new FileOutputStream("D:\\Respaldo Escritorio DELL\\DESKTOP\\Escritorio 2\\a.csv");
        //OutputStreamWriter osw = new OutputStreamWriter(fos);
        //CSVWriter writer = new CSVWriter(osw);
        
        //CSVWriter writer = new CSVWriter(new FileWriter("D:\\Respaldo Escritorio DELL\\DESKTOP\\Escritorio 2//sample1.csv")); 
        CSVWriter writer = new CSVWriter(new FileWriter("testData//sample.csv"));
        //writer.writeAll(s);
        //writer.close();
        //osw.close();
        //fos.close();
        writer.writeNext(bookName1);
        writer.writeNext(bookName2);
        writer.writeNext(bookName3);
        
        writer.flush();
        System.out.println("Data entered!");*/
    }
    
    // Pasar OpenCSV a la hora de guardar datos.
    // Opcional? Si al hacer el jar se crea la carpeta
    // Hacer ventana de ganador
    // Llamar métodos de oontrolador desde cada Listener
    // Hacer global para comprobar en cada Listener 
    // Probar cargado de datos que funcione correctamente, debuggearlo
    // Quitar else de la 113 de GameController
    // Opcional? Ver si hay forma de esperar?
    // Hacer random en GameController!
    // Tirar dado de forma automatica en cada inicio de partida
    // Comprobar que el getPossibleTile grafico se encienda segun corresponda
    // ¿hacer variable global para saber cual pieza estoy moviendo al tile final de destino?
    // Hacer un activeLabel si uan ficha es comida y vuelve al tablero
    // Cambiar desactive por una variable que le mande un valor entre 1 y 7, ver como y quien tiene esa información
    // Hacer video probando juego
    
    /* Orden de las cosas
    1. Meter el código de NewMain dentro de GameController a la hora de guardar un archivo. ¿Cambiar nombre de carpeta?
    2. Debugguear el cargado de un archivo, actualmente tiene valores quemados y no funcionaba bien. Posibles errores de for
    3. Implementar métodos con Action Listeners
    4. Quitar clases de prueba del proyecto y revisar comentarios basura 
    5. Revisar el LucidChart, actualizar
    */
    
    
    
    
}
