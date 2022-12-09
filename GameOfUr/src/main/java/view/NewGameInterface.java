/*
 * Issue #27 - View Manager.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package view;

import java.awt.Color;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * An interface that contains methods necessary to create new game view.
 * @author Mauricio Palma
 */
public interface NewGameInterface
{
    /**
     * Sets player number in view title.
     * @param playerNumber Player's identification.
     */
    public void setPlayerTitle(int playerNumber);
    /**
     * Adds listener that detects player has clicked a color.
     * @param listenForButton Button listener.
     */
    public void addTextFieldFocusistener(FocusListener listenForButton);
    /**
     * Returns JTextField where player's name is stored.
     * @return JTextField object.
     */
    public JTextField getPlayerNameTextField();
    /**
     * Returns chosen color for current player.
     * @return player color.
     */
    public Color getPlayerColor();
    /**
     * Returns given name for current player.
     * @return player name.
     */
    public String getPlayerName();
    /**
     * Returns button that will continue to next panel.
     * @return Continue button.
     */
    public JButton getContinueButton();
    /**
     * Returns button that will go back to previous panel.
     * @return Back button.
     */
    public JButton getBackButton();
    /**
     * Hides button associated with given color.
     * @param color Button to be hid.
     */
    public void hideColorButton(Color color);
    /**
     * Resets text field and variables for further use.
     */
    public void resetPlayerNameTextField();
    /**
     * Resets variables storing chosen color.
     */
    public void resetColorChosen();
}
