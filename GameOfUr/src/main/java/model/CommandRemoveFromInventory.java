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
public class CommandRemoveFromInventory<InventoryItemType extends InventoryItem> implements CommandInterface {
    private ArrayList<InventoryItem> inventoryArray;
    private MutableInt itemIndex;
    
    public CommandRemoveFromInventory(ArrayList<InventoryItem> array, MutableInt itemIndex) {
        this.inventoryArray = array;
        this.itemIndex = itemIndex;
    }
    
    @Override
    public boolean execute() {
        boolean success = false;
        
        this.inventoryArray.remove(this.itemIndex.intValue());
        
        return success;
    }

    @Override
    public boolean unExecute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
