/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package controller;

import model.PlayerModel;
import view.GameView;
import controller.MainMenuController;
import model.UrPiece;
import view.MainMenuView;

/**
 *
 * @author mauup
 */
public class GameOfUr_Swing {

    public static void main(String[] args) {
        //startGame();
        displayMainMenu();
    }
    
    private static void startGame() {
        GameView gameView = new GameView();
        
        PlayerModel firstPlayer = new PlayerModel();
        PlayerModel secondPlayer = new PlayerModel();
        
        PlayerController playerController = new PlayerController(
                firstPlayer, secondPlayer, gameView);
        
        gameView.setVisible(true);
    }
    
    private static void displayMainMenu() {
        MainMenuView view = new MainMenuView();
        UrPiece piece = new UrPiece();
        
        MainMenuController menu = new MainMenuController(piece, view);
        
        view.setVisible(true);
    }
}
