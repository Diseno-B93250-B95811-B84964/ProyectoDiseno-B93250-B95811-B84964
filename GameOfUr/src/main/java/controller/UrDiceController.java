/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.UrDice;
import view.UrDiceView;

/**
 *
 * @author Usuario1
 */
public class UrDiceController {
    private UrDice model;
    private UrDiceView view;
    
    public UrDiceController(UrDice model, UrDiceView view)
    {
        this.model = model;
        this.view = view;
        this.view.addDiceListener(new DiceListener());
    }
    
    public void rollDice()
    {
        model.rollDice();
    }
    
    public int getRollResult()
    {
        return model.getRollResult();
    }
    
    class DiceListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            int diceResult;

            try{
                view.cleanDice();
                model.rollDice();
                diceResult = model.getRollResult();
                view.showThrow(diceResult);
                view.setMoves(diceResult);
            }
            catch(Exception ex){
                System.out.println(ex);
            }
        }
    }
}