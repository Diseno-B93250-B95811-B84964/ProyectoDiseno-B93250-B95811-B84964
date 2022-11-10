/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package controller;

import model.UrPlayerModel;
import model.UrDiceModel;
import model.UrPieceModel;

import view.PlayerView;
import view.MainMenuView;
import view.UrDiceView;
import view.WinnerView;


public class GameOfUr_Swing {

    public static void main(String[] args) {
        startGame();
        //displayMainMenu();
    }
    
    /**
    *
    * @author Mauricio Palma, Alvaro Miranda
    */
    private static void startGame() {
        PlayerView gameView = new PlayerView();
        WinnerView winnerView = new WinnerView();
        UrPlayerModel firstPlayer = new UrPlayerModel(); // These have to edited after using main menu view
        UrPlayerModel secondPlayer = new UrPlayerModel();
        
        UrDiceModel dice = new UrDiceModel();
        UrDiceView urView = new UrDiceView();
        UrDiceController urController =  new UrDiceController(dice, urView);
         
        PlayerController playerController = new PlayerController(gameView, winnerView, urController);
        
        playerController.setPlayers(firstPlayer, secondPlayer);
        
        gameView.setVisible(true);
        winnerView.setVisible(false);
        urView.setVisible(true);
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
