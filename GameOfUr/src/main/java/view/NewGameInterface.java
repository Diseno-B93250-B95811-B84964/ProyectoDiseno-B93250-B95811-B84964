/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author B95811
 */
public interface NewGameInterface {

    /**
     *
     * @param playerNumber
     */
    public void setPlayerTitle(int playerNumber);

    /**
     *
     * @param listenForButton
     */
    public void addTextFieldFocusistener(FocusListener listenForButton);

    /**
     *
     * @return
     */
    public JTextField getPlayerNameTextField();

    /**
     *
     * @return
     */
    public Color getPlayerColor();

    /**
     *
     * @return
     */
    public String getPlayerName();

    /**
     *
     * @return
     */
    public JButton getContinueButton();

    /**
     *
     * @return
     */
    public JButton getBackButton();

    /**
     *
     * @param color
     */
    public void hideColorButton(Color color);

    /**
     *
     */
    public void resetPlayerNameTextField();

    /**
     *
     */
    public void resetColorChosen();
}
