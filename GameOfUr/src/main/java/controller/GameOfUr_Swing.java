/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.UrPieceModel; 
import view.MainGameView;
import view.MainMenuView;
import view.MainMenuViewOld;

public class GameOfUr_Swing {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameOfUr_Swing::startGame);
    }
    
    /**
    *
    * @author Mauricio Palma, Alvaro Miranda
    */
    private static void startGame() {
        try {
            MainMenuView mainMenu = new MainMenuView();
            MainGameView mainGame = new MainGameView();
            MainGameController mainController = new MainGameController(mainGame);
            JFrame mainFrame = new JFrame("CardLayout Trials");
            JPanel panelCont = new JPanel();
            CardLayout card = new CardLayout();
            JButton buttonOne = new JButton("Switch to second panel/workspace");
            buttonOne.setBounds(35,30,300,15);
            JButton buttonSecond = new JButton("Switch to first panel/workspace");
            buttonSecond.setBounds(35,30,300,15);
            panelCont.setLayout(card);
            mainMenu.add(buttonOne);
            mainMenu.revalidate();
            mainMenu.repaint();
            mainGame.add(buttonSecond);
            mainGame.revalidate();
            mainGame.repaint();
            panelCont.add(mainMenu, "mainMenu");
            panelCont.revalidate();
            panelCont.repaint();
            panelCont.add(mainGame, "mainGame");
            panelCont.revalidate(); 
            panelCont.repaint();
            card.show(panelCont, "mainGame");

            buttonOne.addActionListener((ActionEvent arg0) -> {
                card.show(panelCont, "mainGame");
            });

            buttonSecond.addActionListener((ActionEvent arg0) -> {
                card.show(panelCont,"mainMenu");
            });
            mainFrame.add(panelCont);
            mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            mainFrame.pack();
            mainFrame.setVisible(true);
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setResizable(false);
        }
        catch(IOException e) {
        }
    }
    
    /**
    *
    * @author Jimena Gdur
    */
    private static void displayMainMenu() {
        MainMenuViewOld view = new MainMenuViewOld();
        UrPieceModel piece = new UrPieceModel();
        
        MainMenuController menu = new MainMenuController(piece, view);
        
        view.setVisible(true);
    }
    
    private static void CardLayoutMaker(){
    
    }

}
