/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;
import view.SelectColorView;

/**
 *
 * @author mauup
 */
public class SelectColorController {
    SelectColorView selectColorView;
    
    public SelectColorController(SelectColorView selectedColor){
        this.selectColorView = selectedColor;
    }
    
    public void start(){
        this.selectColorView.addTextFieldClickListener(new SetColorClickListener());
    }
    
    public void getPlayerColor(){
        // TODO implement
    }
    
    public void getPlayerName(){
        // TODO implement
    }
    
    class SetColorClickListener implements FocusListener{
        @Override
        public void focusGained(FocusEvent e) {
            JTextField currentInput = selectColorView.getPlayerNameTextField();
            System.out.println("Texfield controller says hey");
            if (currentInput.getText().equals("Enter player name")) {
                currentInput.setText("");
            }        }

        @Override
        public void focusLost(FocusEvent e) {
        }
    }
}
