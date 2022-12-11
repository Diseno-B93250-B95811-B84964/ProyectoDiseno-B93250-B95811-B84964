/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Manages objects from the inventory.
 * @author Mauricio Palma, Ximena Gdur
 */
public abstract class InventoryItem {
    /*
     * Constructor for the inventory item class
     */
    public InventoryItem() {
        
    }
    /*
    * Ability to make a copy of an item.
    * @return The copy of the item.
    */
    public abstract InventoryItem makeCopy();
    /*
    * Making a copy of an item from an already existing item.
    * @param original The original item.
    */
    public abstract void copyFromItem(InventoryItem original);
}
