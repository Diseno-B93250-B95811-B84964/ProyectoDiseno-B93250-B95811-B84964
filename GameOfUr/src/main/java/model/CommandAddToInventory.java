/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Mauricio Palma, Ximena Gdur
 */
public class CommandAddToInventory<InventoryItemType extends InventoryItem> implements CommandInterface
{
    private ArrayList<InventoryItem> inventoryArray;
    private InventoryItemType item;

    public CommandAddToInventory(ArrayList<InventoryItem> array, InventoryItemType item) {
        this.inventoryArray = array;
        this.item = item;
    }
    
    @Override
    public boolean execute() {
        boolean success = false;
        
        InventoryItem itemCopy = this.item.makeCopy();
        this.inventoryArray.add(itemCopy);
        
        return success;
    }

    @Override
    public boolean unExecute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
