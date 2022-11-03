/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package controller;

import model.PlayerModel;
import model.UrDiceModel;
import model.UrPieceModel;

import view.PlayerView;
import view.MainMenuView;
import view.UrDiceView;
import view.WinnerView;


public class GameOfUr_Swing {

    public static void main(String[] args) {
        startGame();
        displayMainMenu();
    }
    
    /**
    *
    * @author Mauricio Palma, Alvaro Miranda
    */
    private static void startGame() {
        PlayerView gameView = new PlayerView();
        WinnerView winnerView = new WinnerView();
        PlayerModel firstPlayer = new PlayerModel();
        PlayerModel secondPlayer = new PlayerModel();
        
        PlayerController playerController = new PlayerController(
                firstPlayer, secondPlayer, gameView, winnerView);
        
        gameView.setVisible(true);
        winnerView.setVisible(false);
        
        UrDiceModel dice = new UrDiceModel();
        UrDiceView view = new UrDiceView();
        UrDiceController controller =  new UrDiceController(dice, view);
        view.setVisible(true);
    }
    
    /**
    *
    * @author Jimena Gdur
    */
    private static void displayMainMenu() {
        MainMenuView view = new MainMenuView();
        UrPieceModel piece = new UrPieceModel();
        
        MainMenuController menu = new MainMenuController(piece, view);
        
        view.setVisible(true);
    }

}
