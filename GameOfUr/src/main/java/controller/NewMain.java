/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controller;

import com.opencsv.CSVWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
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
        
        FileOutputStream fos = new FileOutputStream("D:\\Respaldo Escritorio DELL\\DESKTOP\\Escritorio 2\\a.csv");
        OutputStreamWriter osw = new OutputStreamWriter(fos);
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
    }
    
}
