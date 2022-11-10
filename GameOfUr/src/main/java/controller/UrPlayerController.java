/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.PlayerModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.UrPlayerModel;
import model.UrDiceModel;
import view.PlayerView;
import view.UrDiceView;
import view.WinnerView;

/**
 *
 * @author Mauricio Palma
 */
public class UrPlayerController extends PlayerController{
    private PlayerModel playersArray[];
    private PlayerView gameView;
    private WinnerView winnerView;
    private UrDiceModel diceModel;
    private UrDiceView diceView;

    private int playerNumber; 
    
    public UrPlayerController(PlayerView aGameView, WinnerView aWinnerView, UrDiceController diceController){
        playersArray = super.playersArray;
        playerNumber  = (int)(Math.random()*2);
        
        winnerView = aWinnerView; // TODO delete this
        gameView = aGameView;
        UrDiceController.DiceListener diceListener = initializeDice(diceController); // TODO move this
        this.diceView.addDiceListener(diceListener);    

    }
    
    @Override
    public void start() {
        
        this.gameView.addButtonClickListener( new GameViewListener());
    }
    
    private UrDiceController.DiceListener initializeDice(UrDiceController diceController){
        diceModel = diceController.getDiceModel();
        diceView = diceController.getDiceView();
        UrDiceController.DiceListener diceListener = diceController.new DiceListener();
        return diceListener;
    }
    
    class GameViewListener implements ActionListener{
    
        @Override
        public void actionPerformed(ActionEvent e) {
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
        
        private int throwDice() {
            int diceResult = -1;
            diceView.cleanDice();
            diceModel.rollDice();
            diceResult = diceModel.getRollResult();
            diceView.showThrow(diceResult);
            diceView.setMoves(diceResult);
            return diceResult;
        }
        
        private void checkIfWinner(int player) {
            if (playersArray[player].getPlayerScore()>= 7) {
             gameView.setVisible(false);
             winnerView.setwinnerPlayerText(player+1);
             winnerView.setVisible(true);
            }
        }
    }
}
