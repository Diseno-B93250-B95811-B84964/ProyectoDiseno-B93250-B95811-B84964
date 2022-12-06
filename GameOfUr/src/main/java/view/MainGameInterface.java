/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package view;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author B95811
 */
public interface MainGameInterface {
    public JButton getShowRulesButton();
    public JButton getThrowDiceButton();
    public JButton getExitAndSaveButton();
    public JLabel[][] getTilesMatrix();
    public void activeAPieceForPlayer(Color color);
    public void desactiveAPieceForPlayer(Color color);
    public JLabel getLabel(int row, int column);
    public void setNextPossibleLabel(int row, int column, Icon icon);
    public void removeIconFromTile(int row, int column);
    public ImageIcon getPieceImageColor(Color color);
    public void showThrownDice(int rollResult);
    public void cleanDice();
    public void setMoves(int rollResult);
    public Icon getPlayerIcon(Color color);
    public void changePlayerTurn(int number);
    public void declarePlayerWinner(int number);
    
}
