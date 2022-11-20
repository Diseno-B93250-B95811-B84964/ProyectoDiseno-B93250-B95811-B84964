/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controller;

import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

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
        String[] bookHeader = {"BookName", "Author Of the book"};
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
        System.out.println("Data entered!");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        in.readLine();
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
