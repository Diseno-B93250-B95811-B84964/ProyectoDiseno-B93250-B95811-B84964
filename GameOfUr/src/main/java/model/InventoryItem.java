/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mauricio Palma, Ximena Gdur
 */
public abstract class InventoryItem {

    public InventoryItem() {
        
    }
    
    public abstract InventoryItem makeCopy();
    
    public abstract void copyFromItem(InventoryItem original);
}
