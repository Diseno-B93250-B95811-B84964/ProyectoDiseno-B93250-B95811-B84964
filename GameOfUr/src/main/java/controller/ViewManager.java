/*
 * Issue #27 - View Manager.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.function.Supplier;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Player;

import view.UrLoadGame;
import view.UrMainGame;
import view.UrMainMenu;
import view.UrNewGame;
import view.ShowRules;

/**
 * Manages all view events.
 * @author Mauricio Palma
 * @param <MainGameType> MainGame's child class.
 * @param <LoadGameType> LoadGame's child class.
 * @param <MainMenuType> MainMenu's child class.
 * @param <NewGameType> NewGame's child class.
 * @param <ShowRulesType> ShowRules's child class.
 */
public class ViewManager
    <MainGameType extends UrMainGame, 
    LoadGameType extends UrLoadGame,
    MainMenuType extends UrMainMenu,
    NewGameType extends UrNewGame,
    ShowRulesType extends ShowRules>
{
    /**
     * Stores an instance of a view.
     * Displays main menu with all different game options.
     */
    private final MainMenuType mainMenu;
    /**
     * Stores an instance of a view.
     * Displays a JFileChooser window that allows user to select a saved match.
     */
    private final LoadGameType loadGame;
    /**
     * Stores an instance of a view.
     * Displays windows allowing user to select their names and colors.
     */
    private final NewGameType newGame;
    /**
     * Stores an instance of a view.
     * Displays main game with board and player inventories.
     */
    private final MainGameType mainGame;
    /**
     * Stores an instance of a view.
     * Displays game rules.
     */
    private final ShowRulesType showRules;
    /**
     * Game's main frame.
     */
    private JFrame mainFrame;
    /**
     * Game's main panel.
     */
    private JPanel mainPanel;
    /**
     * Stores an instance of a card layout class.
     * Allow transitioning between views.
     */
    private CardLayout cardLayout;
    /**
     * Stores the color of current player.
     */
    private Color currentPlayerColor;
    /**
     * Whether piece was moved.
     */
    private boolean movedPiece;
    /**
     * Whether a tile can be clicked.
     */
    private boolean canClick;
    /**
     * The row clicked on interface.
     */
    private int clickedRow;
    /**
     * The column clicked on interface.
     */
    private int clickedColumn;
    /**
     * The next row.
     */
    private int nextRow;
    /**
     * The next column.
     */
    private int nextColumn;
    
    /**
     * Creates a view manager.
     * @param mainGameSupplier Provides an instance of MainGame.
     * @param loadGameSupplier Provides an instance of LoadGame.
     * @param mainMenuSupplier Provides an instance of MainMenu.
     * @param newGameSupplier Provides an instance of NewGame.
     * @param rulesSupplier Provides an instance of ShowRules.
     */
    public ViewManager (
        Supplier<MainGameType> mainGameSupplier,
        Supplier<LoadGameType> loadGameSupplier,
        Supplier<MainMenuType> mainMenuSupplier,
        Supplier<NewGameType> newGameSupplier,
        Supplier<ShowRulesType> rulesSupplier)
    {
        this.mainMenu = mainMenuSupplier.get();
        this.loadGame = loadGameSupplier.get();
        this.newGame = newGameSupplier.get();
        this.mainGame = mainGameSupplier.get();
        this.showRules = rulesSupplier.get();
        initializeListeners();
        this.movedPiece = false;
        this.clickedRow = -1;
        this.clickedColumn = -1;
        this.canClick = false;
        this.currentPlayerColor = null;
        manageCardLayout();
    }
    /**
     * Play a match for current player.
     * @param diceResult The result of throwing the dice.
     * @param currentPlayer Id of current player.
     * @param playerColor Color of current player.
     */
    public void playMove(int diceResult, int currentPlayer, Color playerColor) {
        this.setIfPieceMoved(false);
        if (diceResult>0) {
            this.canClick = true;
        }
        this.currentPlayerColor = playerColor;
        this.mainGame.cleanDice();
        this.mainGame.showThrownDice(diceResult);
        this.mainGame.setMoves(diceResult);
        this.mainGame.changePlayerTurn(currentPlayer);
    }
   
    /**
     * Show game rules.
     */
    public void showRules(ArrayList<String> rules) {
        // TODO: receive rules object from referee
        showRules.showRules(rules);
    }
    /**
     * Gets file name that allows a former match to be loaded.
     * @return name of chosen file.
     */
    public String getFileNameToLoadGame() {
        JFileChooser fileChooser = loadGame.getFileChooser();
        String fileName = null;
        if (fileChooser != null) {
            var selectedFile = fileChooser.getSelectedFile();
            if (selectedFile != null) {
                fileName = selectedFile.getAbsolutePath();    
            }
        }
        return fileName;
    }
    /**
     * Gets current player's data.
     * @return A string with player name and color.
     */
    public String getPlayerData(){
        Color playerColor = newGame.getPlayerColor();
        String playerName = newGame.getPlayerName();
        String playerData = null;
        if (playerColor != null && !playerName.equals("Enter player name")) { // TODO remove "Enter..." and make it a const variable
            playerData = playerColor.getRGB() + "," + playerName;
        } else {
            System.out.println("Color or name is null");
        }
        return playerData;
    }
    /**
     * Updates player name and title to reflect current player information.
     * @param currentPlayer Id of current player.
     */
    public void updateNewGameForNextPlayer(int currentPlayer){
        this.newGame.resetPlayerNameTextField();
        this.newGame.setPlayerTitle(currentPlayer);
    }
    /**
     * Updates labels to reflect the chosen names and colors of each player.
     * @param playerArray An array with Player objects.
     */
    public void setPlayers(ArrayList<Player> playerArray){
        this.mainGame.setFirstPlayerNameToLabel(playerArray.get(0).getName());
        this.mainGame.setFirstPlayerPieceColor(playerArray.get(0).getColor());
        
        this.mainGame.setSecondPlayerNameToLabel(playerArray.get(1).getName());
        this.mainGame.setSecondPlayerPieceColor(playerArray.get(1).getColor());
    }
    /**
     * Hides the colors that have already been chosen.
     * @param color Color that needs to be hidden.
     */
    public void hideColors(Color color){
        newGame.hideColorButton(color);
        newGame.revalidate();
        newGame.repaint();
    }
    /**
     * Resets the chosen color, allowing the user to select.
     */
    public void resetColorChosen () {
        newGame.resetColorChosen();
    }
    /**
     * Swaps to certain view, defined by given string.
     * @param viewName Name of view to swap to.
     */
    private void swapView(String viewName){
        this.cardLayout.show(this.mainPanel, viewName);
    }
    /**
     * Swaps to load game view.
     */
    public void swapViewToLoadGame(){
        this.swapView("loadGame");
    }
    /**
     * Swaps to main menu view.
     */
    public void swapViewToMainMenu(){
        this.swapView("mainMenu");
    }
    /**
     * Swaps to main game view.
     */
    public void swapViewToMainGame(){
        this.swapView("mainGame");
    }
    /**
     * Swaps to new game view.
     */
    public void swapViewToNewGame(){
        this.swapView("newGame");
    }
    /**
     * Configures card layout, adds different views and creates main frame.
     */
    private void manageCardLayout(){
        mainFrame = new JFrame("Royal Game Of Ur");
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        addComponentToPanel(mainPanel, mainMenu, "mainMenu");
        addComponentToPanel(mainPanel, newGame, "newGame");
        addComponentToPanel(mainPanel, loadGame, "loadGame");
        addComponentToPanel(mainPanel, mainGame, "mainGame");
        cardLayout.show(mainPanel, "mainMenu");
        mainFrame.add(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
    }
    /**
     * Adds given component to base panel.
     */
    private void addComponentToPanel(JPanel basePanel, JPanel newComponent, String panelsName){
        basePanel.add(newComponent, panelsName);
        basePanel.revalidate();
        basePanel.repaint();
    }
    /**
     * Initializes listener for selecting color, initializes labels.
     */
    private void initializeListeners(){
        this.newGame.addTextFieldFocusistener(new SetColorClickListener());
        initializeLabels();
    }
    /**
     * Initializes labels that represent each game tile.
     */
    private void initializeLabels(){
        JLabel currentLabel;
        for (int row = 0; row < 8; row++) { // TODO change magic number
            for (int column = 0; column < 3; column++) { // TODO change magic number
                currentLabel = this.mainGame.getLabel(row, column);
                currentLabel.addMouseListener(new TileMouseListener(row, column));
            }
        }
    }
    /**
     * Checks if piece has been moved.
     * @return Whether the piece has been moved.
     */
    public boolean getIfPieceMoved(){
        return this.movedPiece;
    }
    /**
     * Indicates if piece has been moved or not.
     * @param beenMoved True or false value indicating if tile has been moved.
     */
    public void setIfPieceMoved(boolean beenMoved){
        this.movedPiece = beenMoved;
    }
    /**
     * Indicates which tile has been clicked.
     * @param row The row of the tile that was clicked.
     * @param column The column of the tile that was clicked.
     */
    private void setClickedTile(int row, int column){
        this.clickedRow = row;
        this.clickedColumn = column;
    }
    /**
     * Gets the last row that was clicked.
     * @return Last row clicked.
     */
    public int getClickedRow(){
        return this.clickedRow;
    }
    /**
     * Gets the last column that was clicked.
     * @return Last column clicked.
     */
    public int getClickedColum(){
        return this.clickedColumn;
    }
    /**
     * Gets the button that starts a new game.
     * @return StartNewGame button.
     */
    public JButton getStartNewGameButton(){
        return mainMenu.getStartNewGameButton();
    }
    /**
     * Gets the button that loads a game.
     * @return StartLoadGame button.
     */
    public JButton getStartLoadGameButton(){
        return mainMenu.getLoadFormerGameButton();
    }
    /**
     * Gets the button that continues from new game window.
     * @return Continue button.
     */
    public JButton getContinueButtonFromNewGame(){
        return newGame.getContinueButton();
    }
    /**
     * Gets the button that goes back from new game window.
     * @return GoBack button.
     */
    public JButton getGoBackButtonFromNewGame(){
        return newGame.getBackButton();
    }
    /**
     * Gets the button that continues from load game window.
     * @return Continue button.
     */
    public JButton getContinueButtonFromLoadGame(){
        return loadGame.getContinueButton();
    }
    /**
     * Gets the button that goes back from load game window.
     * @return GoBack button.
     */
    public JButton getGoBackFromLoadGame(){
        return loadGame.getBackButton();
    }
    /**
     * Gets the button that displays rules from main menu window.
     * @return Rules button.
     */
    public JButton getRulesButtonFromMainMenu(){
        return mainMenu.getShowRulesButton();
    }
    /**
     * Gets the button that displays rules from game window.
     * @return Rules button.
     */
    public JButton getRulesButtonFromGame(){
        return mainGame.getShowRulesButton();
    }
    /**
     * Gets the button that exits and saves.
     * @return ExitAndSave button.
     */
    public JButton getExitAndSave(){
        return mainGame.getExitAndSaveButton();
    }
    /**
     * Gets the button that throws the dice.
     * @return ThrowDice button.
     */
    public JButton getThrowDiceButton(){
        return mainGame.getThrowDiceButton();
    }
    /**
     * Updates interface by removing icon from tile.
     * @param row The row of the label that needs to be cleaned.
     * @param column The column of the label that needs to be cleaned.
     */
    private void cleanTile(int row, int column) {
        mainGame.removeIconFromTile(row, column);
    }
    
    public void resetBackground(int formerRow, int formerColumn){
        if (formerRow == 4 && formerColumn != 1){
            mainGame.getLabel(formerRow, formerColumn).setBackground(Color.decode("#E0E0E0"));
        } else {
            mainGame.getLabel(formerRow, formerColumn).setBackground(Color.decode("#2D3553"));
        }
    }
    
    /**
     * Updates interface by moving piece from old tile to new one.
     * @param currentRow The row of the piece that will be moved to a new tile.
     * @param currentColumn The column of the piece that will be moved to a new tile.
     * @param nextRow The row of the new tile, where the piece is now located.
     * @param nextColumn The column of the new tile, where the piece is now located.
     */
    public void movePiece(int formerRow, int formerColumn, int nextRow, int nextColumn) {
        resetBackground(formerRow, formerColumn);
        cleanTile(formerRow, formerColumn);
        mainGame.setNextPossibleLabel(nextRow, nextColumn, mainGame.getPlayerIcon(currentPlayerColor));
        if (formerRow == 4 && formerColumn != 1) {
            mainGame.desactiveAPieceForPlayer(currentPlayerColor);
        }
    }
    
    public void desactivatePiece(Color otherPlayer) {
        mainGame.activeAPieceForPlayer(otherPlayer);
    }
    
    /**
     * Updates label to indicate a player has won the game.
     * @param winnerName Name of the player that has won.
     */
    public void declareWinner(int playerNumber){
        this.mainGame.declarePlayerWinner(playerNumber);
    }
    
    public void changeThrowDiceButtonText(String text){
        this.mainGame.changeButtonText(text);
    }
    
    public void setNextTilePosition(int row, int column){
        this.nextRow = row;
        this.nextColumn = column;
    }
    
    public int getNextRowPosition(){
        return this.nextRow;
    }
    
    public int getNextColumnPosition(){
        return this.nextColumn;
    }
    
    public void addScoreToPlayer(Color color){
        this.mainGame.addScoreToPlayer(color);
    }
    
    /**
     * A listener that detects when a user has chosen a color.
     */
    class SetColorClickListener implements FocusListener{
        /**
         * An event that detects that someone has clicked on a specific color.
         */
        @Override
        public void focusGained(FocusEvent event) {
            JTextField currentInput = newGame.getPlayerNameTextField();
            if (currentInput.getText().equals("Enter player name")) {
                currentInput.setText("");
            }        
        }
        /**
         * An event that detects that someone has clicked elsewhere.
         */
        @Override
        public void focusLost(FocusEvent event) {
            JTextField currentInput = newGame.getPlayerNameTextField();
            if (currentInput.getText().equals("")) {
                newGame.resetPlayerNameTextField();
            }     
        }
    }
    
    /**
     * A listener that detects when a user has clicked a tile.
     */
    class TileMouseListener extends MouseAdapter {
        /**
         * The row of specific label in visual matrix.
         */
        int row;
        /**
         * The column of specific label in visual matrix.
         */
        int column;
        
        /**
         * Initializes listener with fiven data.
         * @param row Tile's row.
         * @param column Tile's column.
         */
        TileMouseListener(int row, int column){
            this.row = row;
            this.column = column;        
        }
        /**
         * Detects when user has clicked label.
         * @param entered Provides further information regarding event.
         */
        @Override
        public void mousePressed(MouseEvent entered){
            if (canClick) {
                canClick = false;
                setClickedTile(this.row, this.column);
                setIfPieceMoved(true);
                mainGame.getLabel(this.row, this.column).setBackground(Color.decode("#A12525"));   
                 /*int row = getNextRowPosition();
                int column = getNextColumnPosition();
                mainGame.setNextPossibleLabel(row, column, mainGame.getPlayerIcon(currentPlayerColor));*/

                //mainGame.setNextPossibleLabel(this.row, this.column, mainGame.getPlayerIcon(currentPlayerColor));
                // mainGame.desactiveAPieceForPlayer(currentPlayerColor);
                //currentPlayerColor = null;
            }
        }
        /*
            // check if it is first move
            if (this.row == 4 && this.column != 1) {
                validMove = makeInitialMove();               
            } else {
                validMove = makeNormalMove(tile);
            }
        
            if (validMove) {
                tile.resetTile();
                removeIconFromTile(this.row, this.column);
            }
        }     
        }*/
    }
}
