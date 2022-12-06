/*
 * Issue #25 - Game Logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.JSONObject;

/**
 * Manages file operations.
 * @author Jimena Gdur.
 */
public final class FileManager {
    /**
     * Contains file contents.
     * Can contain either the one who will be saved in a file or read from one.
    */
    public ArrayList<String> fileContents;
    
    /**
     * Creates a new FileManager class.
    */
    public FileManager() {
        fileContents = new ArrayList<String>();
    }
    /**
     *
     * @param fileName Contains the name of the new file .
     * @param fileExt Contains the extension of the file.
     * @param dirPath Contains the path in which the file will be stored
     * @return
     */
    public boolean saveFile(String fileName, String fileExt, String dirPath) {
        boolean success = false;
        String completeFileName = dirPath + fileName + fileExt;
        try { 
            FileWriter file = new FileWriter(completeFileName);
            file.write(fileContents.toString()); 
            file.flush(); 
            file.close(); 
 
        } catch (IOException e) { 
            System.out.println(
                "An error occurred. File: " + dirPath + fileName + fileExt + " not found");
        } 
        return success;
    }
    
    public void setFileContents(ArrayList<String> fileContents){
       this.fileContents = fileContents; 
    }
    
    public ArrayList<String> getFileContents(){
       return fileContents;
    }
}
