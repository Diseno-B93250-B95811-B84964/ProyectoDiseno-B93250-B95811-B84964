/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package controller;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.PlayerModel;

import model.UrPlayerModel;
import model.UrDiceModel; // TODO change this to DiceModel
import model.UrPieceModel; // TODO change this to PieceModel
import view.MainGameView;

import view.PlayerView;
import view.MainMenuView;
import view.MainMenuViewPanel;
import view.UrDiceView; // TODO change this to DiceView
import view.UrDiceViewPanel;
import view.WinnerView;


public class GameOfUr_Swing {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                startGame();
            }
            });
        
        //displayMainMenu();
    }
    
    /**
    *
    * @author Mauricio Palma, Alvaro Miranda
    */
    private static void startGame() {
        /*
        PlayerView gameView = new PlayerView();
        WinnerView winnerView = new WinnerView();
        PlayerModel firstPlayer = new UrPlayerModel(); // These have to edited after using main menu view
        PlayerModel secondPlayer = new UrPlayerModel();
        
        UrDiceModel dice = new UrDiceModel();
        UrDiceView urView = new UrDiceView();
        UrDiceController urController =  new UrDiceController(dice, urView);
         
        PlayerController playerController = new UrPlayerController(gameView, winnerView, urController);
        
        playerController.setPlayers(firstPlayer, secondPlayer);
        playerController.start();
        
        //gameView.setVisible(true);
        //winnerView.setVisible(false);
        //urView.setVisible(true);
        
        MainMenuView view = new MainMenuView();
        UrPieceModel piece = new UrPieceModel();
        MainMenuController menu = new MainMenuController(piece, view);
         */
        MainMenuViewPanel menuViewPanel = new MainMenuViewPanel();
        //UrDiceViewPanel diceViewPanel = new UrDiceViewPanel();
        //MainGame1 diceViewPanel = null;
        
        try {
            MainGameView diceViewPanel = new MainGameView();
            MainGameController mainController = new MainGameController(diceViewPanel);
            JFrame frame = new JFrame("CardLayout Trials");
            JPanel panelCont = new JPanel();
            CardLayout card = new CardLayout();
            JButton buttonOne = new JButton("Switch to second panel/workspace");
            buttonOne.setBounds(35,30,300,15);
            JButton buttonSecond = new JButton("Switch to first panel/workspace");
            buttonSecond.setBounds(35,30,300,15);

            panelCont.setLayout(card);
            //panelCont.add(new JButton("Switch to second panel/workspace"));
            //panelCont.add(new JButton("Switch to first panel/workspace"));

            menuViewPanel.add(buttonOne);
            //panelCont.revalidate(); // invokes layout manager
            //panelCont.repaint();
            diceViewPanel.add(buttonSecond);
            //panelCont.revalidate(); // invokes layout manager
            //panelCont.repaint();

            //menuViewPanel.setBackground(Color.BLUE);
            //diceViewPanel.setBackground(Color.RED);


            panelCont.add(menuViewPanel, "menuView");
            //panelCont.revalidate(); // invokes layout manager
            //panelCont.repaint();
            panelCont.add(diceViewPanel, "diceView");
            //panelCont.revalidate(); // invokes layout manager
            //panelCont.repaint();
            card.show(panelCont, "diceView");

            buttonOne.addActionListener((ActionEvent arg0) -> {
                card.show(panelCont, "diceView");
            });

            buttonSecond.addActionListener((ActionEvent arg0) -> {
                card.show(panelCont,"menuView");
            });
            frame.add(panelCont);
            //frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
        catch(IOException e) {
          e.printStackTrace();
        }
        
        
        
        /*
        try {
            MainGameView gameView = new MainGameView();
            MainGameController mainController = new MainGameController(gameView);
            gameView.setVisible(true);
        } catch (IOException e){
          e.printStackTrace();
        }
        */
         

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
    
    private static void CardLayoutMaker(){
    
    }

}
