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

    protected CommandAddToInventory addInventoryCommand;
    protected CommandRemoveFromInventory removeInventoryCommand;
    protected CommandGetObjectFromInventory getObjectFromInventoryCommand;
    
    private ArrayList<InventoryItem> inventoryArray;
    private InventoryItem receivedItem;
    private MutableInt itemIndex;

    public Inventory() {
        this.addInventoryCommand = new CommandAddToInventory(inventoryArray, receivedItem);
        this.removeInventoryCommand = new CommandRemoveFromInventory(inventoryArray, itemIndex);
        this.getObjectFromInventoryCommand = new CommandGetObjectFromInventory(inventoryArray, receivedItem, itemIndex);
    }
     
    @Override
    public String toString() {
        return "inventoryArray: " + inventoryArray;
    }
    
    public void addToInventory(InventoryItem item){
        receivedItem.copyFromItem(item);
        addInventoryCommand.execute();
    }
    
    public void removeFromInventory(){
        removeInventoryCommand.execute();
    }
    
    public InventoryItem getObjectFromInventory(){
        getObjectFromInventoryCommand.execute();
        return receivedItem;
    }
    
}
