/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Usuario1
 */
abstract public class JSONManager {
    protected FileManager mainManager;
    //protected ArrayList<String[]> fileContents;
    protected Board gameBoard;
    protected Player[] gamePlayers;
    
    abstract public boolean execute();
    abstract protected void manageFile(ArrayList<String> fileContents);
    abstract protected void manageBoard();
    abstract protected void managePlayers();
}
