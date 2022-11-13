/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import view.MainGameView;

/**
 *
 * @author mauup
 */
public class MainGameController {
    private final static int ROWS = 8;
    private final static int COLUMNS = 3;
    MainGameView gameView;
    
    public MainGameController(MainGameView gameView){
        this.gameView = gameView;
        JLabel currentLabel;
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                currentLabel = gameView.getLabel(ROWS, COLUMNS);
                this.gameView.addMouseListener(new TileMouseListener(currentLabel,row,column));
            }
        }
        
    }
    
    class TileMouseListener extends MouseAdapter {
        JLabel label;
        int row;
        int column;

        TileMouseListener(JLabel label, int row, int column){
            this.label = label;
            this.row = row;
            this.column = column;
        }

        @Override
        public void mousePressed(MouseEvent entered){
            this.label.setBackground(Color.red);
        }

        @Override
        public void mouseEntered(MouseEvent entered){
            this.label.setBackground(Color.decode("#2D3553"));
        }
    }
}
