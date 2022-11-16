/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.UrDiceModel;
import model.UrPieceModel; 
import model.tempPlayer;
import view.MainGameView;
import view.MainMenuView;
import view.MainMenuViewOld;
import view.SelectColorView;
import view.UrDiceView;



public class GameOfUrMainController {

    public static void main(String[] args) {
        mainMainController main = new mainMainController();
        main.startGame();
        //SwingUtilities.invokeLater(GameOfUrMainController::startGame);
    }
    
    /**
    *
    * @author Mauricio Palma, Alvaro Miranda
    */
    
    

}
