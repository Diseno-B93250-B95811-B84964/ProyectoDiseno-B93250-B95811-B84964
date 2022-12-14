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
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.json.simple.JSONObject;

/**
 * Manages file operations.
 * @author Jimena Gdur.
 */
public final class FileManager
{
    /**
     * Contains file contents.
     * Can contain either the one who will be saved in a file or read from one.
    */
    private ArrayList<String> fileContents;
    
    /**
     * Creates a new FileManager class.
    */
    public FileManager() {
        fileContents = new ArrayList<String>();
    }
    /**
     * Creates file with given name and extension in given directory 
     * @param fileName Contains the name of the new file.
     * @param fileExt Contains the extension of the file.
     * @param dirPath Contains the path in which the file will be stored.
     * @return Whether operation was successful.
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
    /**
     * Loads file with given name, located in given path, into fileContents.
     * @param fileName Contains the name of the file.
     * @param dirPath Contains the path in which the file is stored.
     * @return Whether operation was successful.
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
     * Sets the file contents of the file manager.
     * @param fileContents The contents from the file.
     */
    public void setFileContents(ArrayList<String> fileContents){
       this.fileContents = fileContents; 
    }
    /**
     * Returns contents read or written to file.
     * @return FileContents array.
     */
    public ArrayList<String> getFileContents() {
        return this.fileContents;
    }
    /**
     * Creating a matrix from fileContents by splitting each row using commas as delimiter.
     * @param contentsArray An array with the file contents.
     * @param splitBy Character with which to split the array.
     * @return FileContents matrix.
     */
    public ArrayList<ArrayList<String>> splitArray (ArrayList<String> contentsArray, String splitBy) {
        ArrayList<ArrayList<String>> contentsMatrix = new ArrayList<>(contentsArray.size());
        
        for(int rowIndex = 0; rowIndex < contentsArray.size(); rowIndex++) {
            String[] rowArray = contentsArray.get(rowIndex).split(splitBy);
            contentsMatrix.add(rowIndex, new ArrayList<>(rowArray.length));
            for (int colIndex = 0; colIndex < rowArray.length; colIndex++) {
                contentsMatrix.get(rowIndex).add(colIndex, rowArray[colIndex]);
            }
        }
        return contentsMatrix;
    }
    /**
     * Convert from a string matrix to a true or false matrix.
     * @param stringMatrix A string matrix with file contents.
     * @return FileContents matrix as true or false values.
     */
    public ArrayList<ArrayList<Boolean>> convertFromStringToBoolean(ArrayList<ArrayList<String>> stringMatrix) {
        ArrayList<ArrayList<Boolean>> boolMatrix = new ArrayList<>(stringMatrix.get(0).size());
        
        for(int rowIndex = 0; rowIndex < stringMatrix.size(); rowIndex++) {
            ArrayList<String> rowArray = stringMatrix.get(rowIndex);
            boolMatrix.add(rowIndex, new ArrayList<>(rowArray.size()));
            for (int colIndex = 0; colIndex < rowArray.size(); colIndex++) {
                boolean value = Boolean.parseBoolean(stringMatrix.get(rowIndex).get(colIndex));
                boolMatrix.get(rowIndex).add(colIndex, value);
            }
        }
        return boolMatrix;
    }
}
