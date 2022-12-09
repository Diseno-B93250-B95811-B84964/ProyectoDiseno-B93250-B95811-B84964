/*
 * Issue #27 - View Manager.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package view;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * An interface that contains methods necessary to create main game view.
 * @author Mauricio Palma
 */
public interface MainGameInterface
{
    /**
     * Returns button that will display game rules.
     * @return ShowRules button.
     */
    public JButton getShowRulesButton();
    /**
     * Returns button that updates dice with new random value.
     * @return ThrowDice button.
     */
    public JButton getThrowDiceButton();
    /**
     * Returns button that will save game and exit.
     * @return ExitAndSave button.
     */
    public JButton getExitAndSaveButton();
    /**
     * Returns matrix that stores all labels, representing game tiles.
     * @return JLabel matrix.
     */
    public JLabel[][] getTilesMatrix();
    /**
     * Displays piece for player with given color.
     * @param color Player's color.
     */
    public void activeAPieceForPlayer(Color color);
    /**
     * Makes player's piece invisible.
     * @param color Player's color.
     */
    public void desactiveAPieceForPlayer(Color color);
    /**
     * Returns specific label located in given row and column.
     * @param row Row in which tile is located.
     * @param column Column in which tile is located.
     * @return specific JLabel object.
     */
    public JLabel getLabel(int row, int column);
    /**
     * Sets icon in label located in given row and column.
     * @param row  Row in which tile is located.
     * @param column Column in which tile is located.
     * @param icon Icon that will be assigned.
     */
    public void setNextPossibleLabel(int row, int column, Icon icon);
    /**
     * Removes temporary icon from tile located in given row and column.
     * @param row  Row in which tile is located.
     * @param column Column in which tile is located.
     */
    public void removeIconFromTile(int row, int column);
    /**
     * Returns an image of a piece associated with given color.
     * @param color The color that needs to be retrieved.
     * @return the icon representing colored piece.
     */
    public ImageIcon getPieceImageColor(Color color);
    /**
     * Displays the result of thrown dice.
     * @param rollResult The result of the throw.
     */
    public void showThrownDice(int rollResult);
    /**
     * Resets dice labels to prepare for next throw.
     */
    public void cleanDice();
    /**
     * Sets label with amount of moves.
     * @param rollResult Result of dice.
     */
    public void setMoves(int rollResult);
    /**
     * Returns an icon representing player's chosen color.
     * @param color The color that needs to be retrieved.
     * @return the icon representing colored piece.
     */
    public Icon getPlayerIcon(Color color);
    /**
     * Changes player turn to certain player.
     * @param number Id of new current player.
     */
    public void changePlayerTurn(int number);
    /**
     * Declares a player has won and updates labels.
     * @param number
     */
    public void declarePlayerWinner(int number);
}
