/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.UrPlayerModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.UrDiceModel;
import view.PlayerView;
import view.UrDiceView;
import view.WinnerView;

/**
 *
 * @author Mauricio Palma
 */
public class UrPlayerController extends PlayerController{
    private PlayerView gameView;
    private WinnerView winnerView;
    private UrDiceModel diceModel;
    private UrDiceView diceView;

    private int playerNumber; 
    
    /**
     *
     * @param aGameView
     * @param aWinnerView
     * @param diceController
     */
    public UrPlayerController(PlayerView aGameView, WinnerView aWinnerView, UrDiceController diceController){
        playerNumber  = (int)(Math.random()*2);
        
        winnerView = aWinnerView; // TODO delete this
        gameView = aGameView;
        UrDiceController.DiceListener diceListener = initializeDice(diceController); // TODO move this
        this.diceView.addDiceListener(diceListener);    

    }
    
    /**
     * {@inheritDoc}
     * It starts the controller by calling the listeners needed to get user´s inputs
     */
    @Override
    public void start() {
        
        this.gameView.addButtonClickListener( new GameViewListener());
    }

    /**
    * Method that initializes the Dice as an UrDice to get user´s input
    */
    private UrDiceController.DiceListener initializeDice(UrDiceController diceController){ // TODO change it to DiceController
        diceModel = diceController.getDiceModel();
        diceView = diceController.getDiceView();
        UrDiceController.DiceListener diceListener = diceController.new DiceListener();
        return diceListener;
    }
    
    class GameViewListener implements ActionListener{
    
        /**
        * {@inheritDoc}
        * Method that holds the logic to set points to the players.
        * It starts working when a player clicks the option to play 
        * @param  event The event that trigger the action
        */
        @Override
        public void actionPerformed(ActionEvent event) {
            int diceResult = -1;
            try {
                gameView.setplayerTurnsText(playerNumber + 1);
                diceResult = throwDice();
                if (diceResult > 0) {                    
                    playersArray[playerNumber].addScoreToPlayer();
                    gameView.setFirstPlayerScore(playersArray[playerNumber].getPlayerScore());
                    checkIfWinner(playerNumber);
                    playerNumber++;
                    playerNumber %= playersArray.length;
                    gameView.setSecondPlayerScore(playersArray[playerNumber].getPlayerScore());
                }
            }
            catch(Exception exception) {
                System.out.println(exception);
            }
        }
        
        /**
        * Method that throws the dice to know how many positions can a player move
        */
        private int throwDice() {
            int diceResult = -1;
            diceView.cleanDice();
            diceModel.rollDice();
            diceResult = diceModel.getRollResult();
            diceView.showThrow(diceResult);
            diceView.setMoves(diceResult);
            return diceResult;
        }
        
        /**
        * Method that checks if a player won the match to stop the game
        */
        private void checkIfWinner(int player) {
            if (playersArray[player].getPlayerScore()>= 7) {
             gameView.setVisible(false);
             winnerView.setwinnerPlayerText(player+1);
             winnerView.setVisible(true);
            }
        }
    }
}
