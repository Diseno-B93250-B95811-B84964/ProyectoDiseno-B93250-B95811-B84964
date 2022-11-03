/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.PlayerModel;
import view.PlayerView;
import view.WinnerView;

/**
 *
 * @author mauup
 */
public class PlayerController {
    private PlayerModel playersArray[];
    private PlayerView gameView;
    private WinnerView winnerView;
    private int firstPlayer; 
    
    public PlayerController(PlayerModel aFirstPlayer, PlayerModel aSecondPlayer, PlayerView aGameView, WinnerView aWinnerView){
        playersArray = new PlayerModel[2];
        winnerView = aWinnerView;
        playersArray[0] = aFirstPlayer;
        playersArray[1] = aSecondPlayer;
        gameView = aGameView;
        firstPlayer  = (int)(Math.random()*2);
        this.gameView.addButtonClickListener( new GameViewListener());
    }
    
    class GameViewListener implements ActionListener{
    
        public void actionPerformed(ActionEvent e) {
            
            try {
                gameView.setplayerTurnsText(firstPlayer+1);
                playersArray[firstPlayer].addToScore();
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
    }
}
