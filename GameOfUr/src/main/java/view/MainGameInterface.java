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

    /**
     *
     * @return
     */
    public JButton getShowRulesButton();

    /**
     *
     * @return
     */
    public JButton getThrowDiceButton();

    /**
     *
     * @return
     */
    public JButton getExitAndSaveButton();

    /**
     *
     * @return
     */
    public JLabel[][] getTilesMatrix();

    /**
     *
     * @param color
     */
    public void activeAPieceForPlayer(Color color);

    /**
     *
     * @param color
     */
    public void desactiveAPieceForPlayer(Color color);

    /**
     *
     * @param row
     * @param column
     * @return
     */
    public JLabel getLabel(int row, int column);

    /**
     *
     * @param row
     * @param column
     * @param icon
     */
    public void setNextPossibleLabel(int row, int column, Icon icon);

    /**
     *
     * @param row
     * @param column
     */
    public void removeIconFromTile(int row, int column);

    /**
     *
     * @param color
     * @return
     */
    public ImageIcon getPieceImageColor(Color color);

    /**
     *
     * @param rollResult
     */
    public void showThrownDice(int rollResult);

    /**
     *
     */
    public void cleanDice();

    /**
     *
     * @param rollResult
     */
    public void setMoves(int rollResult);

    /**
     *
     * @param color
     * @return
     */
    public Icon getPlayerIcon(Color color);

    /**
     *
     * @param number
     */
    public void changePlayerTurn(int number);

    /**
     *
     * @param number
     */
    public void declarePlayerWinner(int number);
    
}
