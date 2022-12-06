/*
 * Issue #26 - Game Logic.
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
        
    }
    /**
     * Creates file with given name and extension in given directory 
     * @param name Contains the name of the new file.
     * @param extension Contains the extension of the file.
     * @param path Contains the path in which the file will be stored.
     * @param contents Contains the contents that will be stored in file.
     * @return whether operation was successful.
     */
    public boolean saveFile(String name, String extension, String path, ArrayList<String> contents) {
        boolean success = false;
        
        /*try {
            FileWriter file = new FileWriter("E:/output.json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
        }*/
        
        return success;
    }
    /**
     * Loads file with given name, located in given path, into fileContents.
     * @param fileName Contains the name of the file.
     * @param dirPath Contains the path in which the file is stored.
     * @return whether operation was successful.
     */
    public boolean loadFile(String fileName, String dirPath) {
        fileContents = new ArrayList<>();
        boolean success = false;
        
        try {
            File myObj = new File(dirPath + fileName);
            Scanner reader = new Scanner(myObj);
            while (reader.hasNextLine()) {
              String data = reader.nextLine();
              fileContents.add(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(
                "An error occurred. File: " + dirPath + fileName + " not found");
        }
        
        return success;
    }
    /**
     * Returns contents read or written to file.
     * @return fileContents array.
     */
    public ArrayList<String> getFileContents() {
        return this.fileContents;
    }
}
