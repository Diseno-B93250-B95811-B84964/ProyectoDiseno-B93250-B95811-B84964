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
 * @author Mauricio Palma
 */
public class PlayerController {
    private PlayerModel playersArray[];
    private GameView gameView;
    private int firstPlayer; 
    
    public PlayerController(PlayerModel aFirstPlayer, PlayerModel aSecondPlayer, GameView aGameView){
        playersArray = new PlayerModel[2];
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
                firstPlayer++;
                firstPlayer %= playersArray.length;
            }
            catch(Exception exception) {
                System.out.println(exception);
            }
        }
    }
}
