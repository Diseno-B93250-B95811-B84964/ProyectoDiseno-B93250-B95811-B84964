/*
 * Issue #27 - View Manager.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package view;

import javax.swing.JButton;
import javax.swing.JFileChooser;

/**
 * An interface that contains methods necessary to load a game visually.
 * @author Mauricio Palma
 */
public interface LoadGameInterface
{
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
     * Returns JFileChooser object that allows user to select which file to load.
     * @return FileChooser object.
     */
    public JFileChooser getFileChooser();
}
