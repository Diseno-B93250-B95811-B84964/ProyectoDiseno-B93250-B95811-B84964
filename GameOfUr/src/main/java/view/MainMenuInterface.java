/*
 * Issue #27 - View Manager.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package view;

import javax.swing.JButton;

/**
 * An interface that contains methods necessary to create main menu view.
 * @author Mauricio Palma
 */
public interface MainMenuInterface
{
    /**
     * Returns button that will start a new game.
     * @return StartNewGame button.
     */
    public JButton getStartNewGameButton();
    /**
     * Returns button that will load a former game.
     * @return LoadFormerGame button.
     */
    public JButton getLoadFormerGameButton();
    /**
     * Returns button that will display game rules.
     * @return ShowRules button.
     */
    public JButton getShowRulesButton();
}