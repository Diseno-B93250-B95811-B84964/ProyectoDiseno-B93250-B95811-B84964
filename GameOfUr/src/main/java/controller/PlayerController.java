/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.PlayerModel;
import model.UrDiceModel;
import view.PlayerView;
import view.UrDiceView;
import view.WinnerView;

/**
 *
 * @author Mauricio Palma
 */
public class PlayerController {
    private PlayerModel playersArray[];
    private PlayerView gameView;
    private WinnerView winnerView;
    private UrDiceModel diceModel;
    private UrDiceView diceView;

    private int firstPlayer; 
    
    public PlayerController(PlayerModel aFirstPlayer, PlayerModel aSecondPlayer, PlayerView aGameView,
            WinnerView aWinnerView, UrDiceModel aDiceModel, UrDiceView aDiceView,
            UrDiceController diceController){
        playersArray = new PlayerModel[2];
        winnerView = aWinnerView;
        playersArray[0] = aFirstPlayer;
        playersArray[1] = aSecondPlayer;
        gameView = aGameView;
        firstPlayer  = (int)(Math.random()*2);
        this.gameView.addButtonClickListener( new GameViewListener());
        
        diceModel = aDiceModel;
        diceView = aDiceView;
        UrDiceController.DiceListener diceListener = diceController.new DiceListener();
        this.diceView.addDiceListener(diceListener);    
    }
    
    class GameViewListener implements ActionListener{
    
        public void actionPerformed(ActionEvent e) {
            int diceResult = -1;
            try {
                gameView.setplayerTurnsText(firstPlayer+1);
                diceResult = throwDice();
                if (diceResult > 0) {
                    playersArray[firstPlayer].addToScore();
                }
                gameView.setplayer0Score(playersArray[firstPlayer].getScore());             
                if (playersArray[firstPlayer].getScore()>= 7) {
                    gameView.setVisible(false);
                    winnerView.setwinnerPlayerText(firstPlayer+1);
                    winnerView.setVisible(true);
                }
                firstPlayer++;
                firstPlayer %= playersArray.length;
                gameView.setplayer1Score(playersArray[firstPlayer].getScore());

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
    }
}
