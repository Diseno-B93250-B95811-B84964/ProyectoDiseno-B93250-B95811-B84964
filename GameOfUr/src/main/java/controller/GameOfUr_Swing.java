/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package controller;

import model.PlayerModel;
import model.UrDice;
import view.PlayerView;
import controller.MainMenuController;
import model.UrPiece;
import view.MainMenuView;
import view.UrDiceView;
import view.WinnerView;

/**
 *
 * @author mauup
 */
public class GameOfUr_Swing {

    public static void main(String[] args) {
        startGame();
        displayMainMenu();
    }
    
    private static void startGame() {
        PlayerView gameView = new PlayerView();
        WinnerView winnerView = new WinnerView();
        PlayerModel firstPlayer = new PlayerModel();
        PlayerModel secondPlayer = new PlayerModel();
        
        PlayerController playerController = new PlayerController(
                firstPlayer, secondPlayer, gameView, winnerView);
        
        gameView.setVisible(true);
        winnerView.setVisible(false);

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
