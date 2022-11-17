/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class UrPlayerController {


    /**
     *
     * @param aGameView
     * @param aWinnerView
     * @param diceController
    public UrPlayerController(PlayerView aGameView, WinnerView aWinnerView, UrDiceController diceController){
        playerNumber  = (int)(Math.random()*2);

    }

    
    class GameViewListener implements ActionListener{

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
        * Method that checks if a player won the match to stop the game
        private void checkIfWinner(int player) {
            if (playersArray[player].getPlayerScore()>= 7) {
             gameView.setVisible(false);
             winnerView.setwinnerPlayerText(player+1);
             winnerView.setVisible(true);
            }
        }
    }
}

        */
