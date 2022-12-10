/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Mauricio Palma
 */
public class Inventory extends GameObject{

    protected CommandAddToInventory addInventoryCommand;
    protected CommandRemoveFromInventory removeInventoryCommand;
    protected CommandSearchInventory searchInventoryCommand;
    protected CommandGetObjectFromInventory getObjectFromInventoryCommand;
    
    private ArrayList<InventoryItem> inventoryArray;

    public Inventory() {
        this.addInventoryCommand = addInventoryCommand;
        this.removeInventoryCommand = removeInventoryCommand;
        this.searchInventoryCommand = searchInventoryCommand;
        this.getObjectFromInventoryCommand = getObjectFromInventoryCommand;
        this.inventoryArray = inventoryArray;
    }
     
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void addToInventory(){
        addInventoryCommand.execute();
    }
    
    public void removeFromInventory(){
        removeInventoryCommand.execute();
    }
    
    public void searchInventoryCommand(){
        searchInventoryCommand.execute();
    }
    
    public void getObjectFromInventory(){
        getObjectFromInventoryCommand.execute();
    }
    
}
