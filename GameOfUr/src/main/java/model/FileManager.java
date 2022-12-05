/*
 * Issue #25 - Game Logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package model;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

/**
 * Manages file operations.
 * @author Jimena Gdur.
 */
public final class FileManager {
    /**
     * Creates a new Rules class.
    */
    public FileManager() {
        
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
        
}
