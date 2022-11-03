/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package controller;

import model.PlayerModel;
import view.GameView;

/**
 *
 * @author mauup
 */
public class GameOfUr_Swing {

    public static void main(String[] args) {
        GameView gameView = new GameView();
        PlayerModel firstPlayer = new PlayerModel();
        PlayerModel secondPlayer = new PlayerModel();
        PlayerController playerController = new PlayerController(firstPlayer, secondPlayer, gameView);
        gameView.setVisible(true);
    }
}
