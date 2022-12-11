/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import org.apache.commons.lang3.mutable.MutableInt;

/**
 * Removing an inventory object from the inventory.
 * @author Mauricio Palma.
 */
public class CommandRemoveFromInventory<InventoryItemType extends InventoryItem> implements CommandInterface {
    /*
     * Holds the objects inside the inventory.
     */
    private ArrayList<InventoryItem> inventoryArray;
    /*
     * Location of the item within the array.
     */
    private MutableInt itemIndex;
    
    /*
     * Constructor for the command class.
     * @param array Holds the objects inside the inventory.
     * @param itemIndex Location of the item within the array.
     */
    public CommandRemoveFromInventory(ArrayList<InventoryItem> array, MutableInt itemIndex) {
        this.inventoryArray = array;
        this.itemIndex = itemIndex;
    }
    
    /**
     * Executes an action, to follow the command pattern.
     * @return Whether the operation was successful.
     */
    @Override
    public boolean execute() {
        boolean success = false;
        
        this.inventoryArray.remove(this.itemIndex.intValue());
        
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
