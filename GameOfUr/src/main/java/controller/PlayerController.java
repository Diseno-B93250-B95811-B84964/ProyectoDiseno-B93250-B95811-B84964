/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.PlayerModel;
import view.GameView;

/**
 *
 * @author mauup
 */
public class PlayerController {
    private PlayerModel playersArray[];
    private GameView gameView;
    
    public PlayerController(PlayerModel aFirstPlayer, PlayerModel aSecondPlayer, GameView aGameView){
        playersArray = new PlayerModel[2];
        playersArray[0] = aFirstPlayer;
        playersArray[1] = aSecondPlayer;
        gameView = aGameView;
        int firstPlayerID = 1;
        int secondPlayerID = 2;
        this.gameView.setplayerTurnsText(firstPlayerID);
        this.gameView.setplayerTurnsText(secondPlayerID);
        this.gameView.addButtonClickListener( new GameViewListener());
    }
    
    class GameViewListener implements ActionListener{
    
        public void actionPerformed(ActionEvent e) {
            int firstPlayerID = 1;
            int secondPlayerID = 2;
            int firstPlayer = (int)(Math.random()*2); 
            int gameOver = 0;
            try {

                while (gameOver < 5) {
                    //playersArray[firstPlayer];
                    firstPlayer++;
                    firstPlayer %= playersArray.length;
                    System.out.println("FirstPlayr: " + firstPlayer);
                    gameOver++;
                }
            }
            catch(Exception exception) {
                System.out.println(exception);
            }
        }
    }
}
