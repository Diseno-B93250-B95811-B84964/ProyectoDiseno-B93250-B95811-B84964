/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package controller;

import model.PlayerModel;
import model.UrDice;
import view.GameView;
import controller.MainMenuController;
import model.UrPiece;
import view.MainMenuView;
import view.UrDiceView;

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
        
        UrDice dice = new UrDice();
        UrDiceView view = new UrDiceView();
        UrDiceController controller =  new UrDiceController(dice, view);
        view.setVisible(true);
    }
    
    private static void displayMainMenu() {
        MainMenuView view = new MainMenuView();
        UrPiece piece = new UrPiece();
        
        MainMenuController menu = new MainMenuController(piece, view);
        
        view.setVisible(true);
    }

}
