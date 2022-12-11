/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 * Validates the ability to add a new object to the inventory.
 * @author Jimena Gdur, Mauricio Palma.
 */
public class CommandAddToInventory<InventoryItemType extends InventoryItem> implements CommandInterface
{   
    /**
     * Array of objects inside the inventory
     */
    private ArrayList<InventoryItem> inventoryArray;
    /**
     * Item to be added
     */
    private InventoryItemType item;

    public CommandAddToInventory(ArrayList<InventoryItem> array, InventoryItemType item) {
        this.inventoryArray = array;
        this.item = item;
    }
    /**
     * Executes the action of eating a piece.
     * @return Indicates if the action of eating a piece has been successful.
     */
    @Override
    public boolean execute() {
        boolean success = false;
        
        InventoryItem itemCopy = this.item.makeCopy();
        this.inventoryArray.add(itemCopy);
        
        return success;
    }
    /**
     * Reverts an action.
     * @return whether the operation was successful.
     */
    @Override
    public boolean unExecute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
