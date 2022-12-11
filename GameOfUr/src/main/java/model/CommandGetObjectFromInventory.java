/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import org.apache.commons.lang3.mutable.MutableInt;

/**
 * Validates the ability to get an object from the player's inventory.
 * @author Jimena Gdur, Mauricio Palma.
 */
public class CommandGetObjectFromInventory<InventoryItemType extends InventoryItem> implements CommandInterface
{
    /**
     * Array that holds the items from the inventory.
     */
    private ArrayList<InventoryItem> inventoryArray;
    /*
     * The item received from the inventory.
     */
    private InventoryItemType receivedItem;
    /*
     * Where to find the item in the inventory.
     */
    private MutableInt itemIndex;
    /*
     * Constructor for the command class.
     * @param array Inventory array.
     * @param item Item to get.
     * @param itemIndex Where to find the item.
     */
    public CommandGetObjectFromInventory(ArrayList<InventoryItem> array, InventoryItemType item, MutableInt itemIndex) {
        this.inventoryArray = array;
        this.receivedItem = item;
        this.itemIndex = itemIndex;
    }
    /**
     * Executes an action, to follow the command pattern.
     * @return Whether the operation was successful.
     */
    @Override
    public boolean execute() {
        boolean success = false;
        
        InventoryItem item = this.inventoryArray.get(this.itemIndex.intValue());
        this.receivedItem.copyFromItem(item);
        
        return success;
    }
    /**
     * Reverts an execution, to follow the command pattern.
     * @return Whether the operation was successful.
     */
    @Override
    public boolean unExecute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
