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
    public void setPlayerTitle(int playerNumber);
    public void addTextFieldFocusistener(FocusListener listenForButton);
    public JTextField getPlayerNameTextField();
    public Color getPlayerColor();
    public String getPlayerName();
    public JButton getContinueButton();
    public JButton getBackButton() ;
    public void hideColorButton(Color color);
    public void resetPlayerNameTextField();
    public void resetColorChosen();
}
