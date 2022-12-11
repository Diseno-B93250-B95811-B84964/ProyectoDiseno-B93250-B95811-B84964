/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import org.apache.commons.lang3.mutable.MutableInt;

/**
 *
 * @author Mauricio Palma, Ximena Gdur
 */
public class Inventory extends GameObject{
    /*
    * Command to add new items to the inventory.
    */
    protected CommandAddToInventory addInventoryCommand;
    /*
    * Command to remove items from the inventory.
    */
    protected CommandRemoveFromInventory removeInventoryCommand;
    /*
    * Command to get items from the inventory.
    */
    protected CommandGetObjectFromInventory getObjectFromInventoryCommand;
    /*
    * Array of inventory items.
    */
    private ArrayList<InventoryItem> inventoryArray;
    /*
    * Item received from the inventory.
    */
    private InventoryItem receivedItem;
    /*
    * Index where the object is located.
    */
    private MutableInt itemIndex;
    /*
    * Constructor for the inventory with its command classes.
    */
    public Inventory() {
        this.addInventoryCommand = new CommandAddToInventory(inventoryArray, receivedItem);
        this.removeInventoryCommand = new CommandRemoveFromInventory(inventoryArray, itemIndex);
        this.getObjectFromInventoryCommand = new CommandGetObjectFromInventory(inventoryArray, receivedItem, itemIndex);
    }
    /*
    * Method to print the inventory.
    * @return String The print from the inventory.
    */
    @Override
    public String toString() {
        return "inventoryArray: " + inventoryArray;
    }
    /*
    * Add a new item to the inventory.
    * @param item Item to add to the inventory.
    */
    public void addToInventory(InventoryItem item){
        receivedItem.copyFromItem(item);
        addInventoryCommand.execute();
    }
    /*
    * Remove an item from the inventory.
    */
    public void removeFromInventory(){
        removeInventoryCommand.execute();
    }
    /*
    * Get an object from the inventory.
    */
    public InventoryItem getObjectFromInventory(){
        getObjectFromInventoryCommand.execute();
        return receivedItem;
    }
    
}
