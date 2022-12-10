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
public class CommandGetObjectFromInventory<InventoryItemType extends InventoryItem> implements CommandInterface
{
    private ArrayList<InventoryItem> inventoryArray;
    private InventoryItemType receivedItem;
    private MutableInt itemIndex;
    
    public CommandGetObjectFromInventory(ArrayList<InventoryItem> array, InventoryItemType item, MutableInt itemIndex) {
        this.inventoryArray = array;
        this.receivedItem = item;
        this.itemIndex = itemIndex;
    }
    
    @Override
    public boolean execute() {
        boolean success = false;
        
        InventoryItem item = this.inventoryArray.get(this.itemIndex.intValue());
        this.receivedItem.copyFromItem(item);
        
        return success;
    }

    @Override
    public boolean unExecute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
