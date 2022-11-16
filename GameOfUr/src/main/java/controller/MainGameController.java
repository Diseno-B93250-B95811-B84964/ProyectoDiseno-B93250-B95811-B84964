/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.UrDiceModel;
import model.UrPieceModel;
import view.MainGameView;
import view.MainMenuView;
import view.SelectColorView;
import view.UrDiceView;

/**
 *
 * @author mauup
 */
public class MainGameController {
    private final static int ROWS = 8;
    private final static int COLUMNS = 3;
    private MainGameView gameView;
    private UrPieceModel piece;
    private MainMenuView mainMenuView;
    private UrDiceModel diceModel;

    public MainGameController(){
        try {
            this.diceModel = new UrDiceModel();
            this.gameView = new MainGameView();
            this.piece = new UrPieceModel();
            this.mainMenuView = new MainMenuView();
            initializeLabels();
            chooseNextPossibleLabel();        
            menuHandler();
        } catch(IOException e) {
            System.out.println("Images not found! Please check images path");
        }
    }
    
    public MainGameView getMainGameView() {
        return this.gameView;
    }
    
    public MainMenuView getMainMenuView(){
        return this.mainMenuView;
    }
    
    private void menuHandler(){
        //this.mainMenuView.addColorButtonClickListener( new MenuViewListener());
        this.gameView.addSaveAndLeaveButtonClickListener(new SaveAndLeaveClickListener());
        this.gameView.addthrowDiceButtonClickListener(new ThrowDiceClickListener());
        System.out.println("Going to call exit button of main menu");
        this.mainMenuView.addExitButtonClickListener(new ExitClickListener());
        this.mainMenuView.addLoadGameButtonClickListener(new LoadGameClickListener());
    }
    
    private void initializeLabels(){
        JLabel currentLabel;
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                currentLabel = gameView.getLabel(row, column);
                currentLabel.addMouseListener(new TileMouseListener(currentLabel,row,column));
            }
        }
    }
    
    /*Setters */
    private void chooseNextPossibleLabel(){
        gameView.setNextPossibleLabel(2,2);
    }

    public void setFirstPlayerName(String name){
        gameView.setFirstPlayerName(name);
    }
    
    public void setFirstPlayerColor(Color color){
        System.out.println("As mainGame controller, color of first player " + color);
    }
    
    public void setSecondPlayerName(String name){
       gameView.setSecondPlayerName(name);
    }
    
    public void setSecondPlayerColor(Color color){
        System.out.println("As mainGame controller, color of second player " + color);
    }
    
    /* Listeners */
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
            chooseNextPossibleLabel();     
        }

        @Override
        public void mouseEntered(MouseEvent entered){
            this.label.setBackground(Color.decode("#2D3553"));
        }
    }
    
    class MenuViewListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            mainMenuView.chooseColor();
            Color color = mainMenuView.getChoosenColor();            
            piece.setColor(color);
            //menu.setColorChooser();
            System.out.println("Hey there color picker!");
        }
    }
    
    class SaveAndLeaveClickListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        } 
    }
    
    class ThrowDiceClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int diceResult = getDiceResult();
        }
        
        private int getDiceResult(){
            gameView.cleanDice();
            diceModel.rollDice();
            int diceResult = diceModel.getRollResult();
            gameView.showThrow(diceResult);
            gameView.setMoves(diceResult);
            return diceResult;
        }

    }
    
    class ExitClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    
    class LoadGameClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Teach me how to load a game!");
            
        }
    }
}
