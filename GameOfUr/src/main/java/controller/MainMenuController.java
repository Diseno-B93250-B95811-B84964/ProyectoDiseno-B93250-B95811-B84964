/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import model.UrPiece;
import view.MainMenuView;

/**
 *
 * @author Jimena Gdur Vargas
 */
public class MainMenuController
{
    private UrPiece piece;
    private MainMenuView menu;
    
    public MainMenuController(UrPiece piece, MainMenuView view) {
        this.piece = piece;
        this.menu = view;
        
        this.menu.addButtonClickListener( new MenuViewListener());
    }
    
    class MenuViewListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            menu.chooseColor();
            Color color = menu.getChoosenColor();            
            piece.setColor(color);
            menu.setColorChooser(color);
        }
    }
 
}
