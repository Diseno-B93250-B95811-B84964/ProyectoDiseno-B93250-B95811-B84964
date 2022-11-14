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

import model.UrPieceModel;
import view.MainMenuViewOld;

/**
 *
 * @author Jimena Gdur
 */
public class MainMenuController
{
    private UrPieceModel piece;
    private MainMenuViewOld menu;
    
    public MainMenuController(UrPieceModel piece, MainMenuViewOld view) {
        this.piece = piece;
        this.menu = view;
        
        this.menu.addColorButtonClickListener( new MenuViewListener());
        this.menu.addRulesButtonClickListener( new RulesViewListener());

    }
    
    class MenuViewListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            menu.chooseColor();
            Color color = menu.getChoosenColor();            
            piece.setColor(color);
            menu.setColorChooser();
        }
    }
    
    class RulesViewListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            menu.showRules();
        }
    }
 
}
