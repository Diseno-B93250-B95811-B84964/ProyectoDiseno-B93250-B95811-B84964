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
import javax.swing.JTextField;
import model.tempPlayer;
import view.SelectColorView;

/**
 *
 * @author mauup
 */
public class SelectColorController {
    SelectColorView selectColorView;
    Color playerColor;
    String playerName;
    tempPlayer playerArray [] = new tempPlayer[2];
    int position = 0;
    
    ContinueButtonListener a;
    
    public SelectColorController(int playerNumber){
        this.playerName = "";
        this.selectColorView = new SelectColorView(playerNumber);
    }
    
    public void start(){
        this.selectColorView.addTextFieldFocusistener(new SetColorClickListener());
        
        a = new ContinueButtonListener();
        this.selectColorView.addContinueButtonClickListener(a);
    }
    
    public Color getPlayerColor(){
        return playerColor;
    }
    
    public String getPlayerName(){
        //ContinueButtonListener a = new ContinueButtonListener();
        //return a.getPlayerName();
        return selectColorView.getPlayerNameTextField().getText();
    }
    
    public tempPlayer[] getTempPlayerArray(){
        return playerArray;
    }
    
    public SelectColorView getView(){
        return this.selectColorView;
    }
    
    class SetColorClickListener implements FocusListener{
        @Override
        public void focusGained(FocusEvent e) {
            JTextField currentInput = selectColorView.getPlayerNameTextField();
            if (currentInput.getText().equals("Enter player name")) {
                currentInput.setText("");
            }        
        }

        @Override
        public void focusLost(FocusEvent e) {
            JTextField currentInput = selectColorView.getPlayerNameTextField();
            if (currentInput.getText().equals("")) {
                currentInput.setText("Enter player name");
            }     
        }
    }
    
    class ContinueButtonListener implements ActionListener{

        private String myPlanerName;

        public ContinueButtonListener() {
            myPlanerName = "";
        }
        
        
        
        public String getPlayerName(){
            return myPlanerName;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            // || !(selectColorView.getPlayerNameTextField().getText().equals("Enter player name")))
            System.out.println("continueButton says: " + "[" + selectColorView.getPlayerNameTextField().getText() + "]");
            if (!(selectColorView.getPlayerNameTextField().getText().equals("Enter player name"))) {
                this.myPlanerName = selectColorView.getPlayerNameTextField().getText();
            } else{
                System.out.println("Cuak");
            }
            
            /*
            playerColor = selectColorView.getPlayerColor();
            playerName = selectColorView.getPlayerNameTextField().getText();
            playerArray[position].setPlayerColor(playerColor);
            playerArray[position].setPlayerName(playerName);
            System.out.println("Going to print playerArrayTemp: ");
            System.out.println("Color: " + playerColor );
            System.out.println("Name: " + playerName );
            if (position==0){
                position +=1;
            } else {
                position=0;
            }*/
        }
    }
}
