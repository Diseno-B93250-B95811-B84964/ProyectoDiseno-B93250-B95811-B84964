/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import view.LoadGameView;

/**
 *
 * @author Mauricio Palma
 */
public class FileChooserController {

    private LoadGameView fileChooser;
    private ArrayList<String> fileAsStringArray = new ArrayList();
    
    public FileChooserController(){
        this.fileChooser = new LoadGameView();
        this.fileChooser.addButtonToFileChooser(new FileChooserClickListener());
    }
    
    public LoadGameView getLoadGameView(){
        return this.fileChooser;
    }
    
    public ArrayList<String> getFileAsStringArray(){
        return fileAsStringArray;
    }
    
    class FileChooserClickListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = fileChooser.getFileChooser();
            File file = new File(chooser.getSelectedFile().getAbsolutePath());
            System.out.println("File content is: ");
            try {
                Scanner lineScanner = new Scanner(file);
                if (lineScanner != null) {
                    while (lineScanner.hasNextLine()) {
                        fileAsStringArray.add(lineScanner.nextLine());
                    }
                }
            } catch (FileNotFoundException ev) {
                System.out.println("File not found!");
            }
        } 
    }
    
}
