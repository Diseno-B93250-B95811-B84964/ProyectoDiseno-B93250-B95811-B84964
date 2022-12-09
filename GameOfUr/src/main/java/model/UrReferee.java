/*
 * Issue #26 - Game Logic.
 * 
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package model;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Game referee, manages game moves and game score.
 * @author Jimena Gdur.
 */
public class UrReferee extends Referee
{
    /*
    /**
     * Creates referee class for Royal Game of Ur.
     * @param rows Amount of rows in game board.
     * @param cols  Amount of columns in game board.
     * @param players Amount of players in game.
     * @param pieces Amount of pieces for each player.
     * @param tiles Amount of tiles in game board.
     * @param boolMatrix A matrix with all adjacents.
     */
    public UrReferee(int rows, int cols, int players, int pieces, int tiles, ArrayList<ArrayList<Boolean>> boolMatrix, Player playerType, Piece pieceType)
    {
        super(rows, cols, players, pieces, tiles, boolMatrix, playerType, pieceType);
    }
    
    public UrReferee(int players, int pieces, Player playerType, Piece pieceType) {
        super(players, pieces, playerType, pieceType);
    }

    /*
    /**
     * Creates players and stores them in playerArray.
     *//*
    @Override
    protected void createPlayers() {
        playerArray = new ArrayList<>(playerAmount);
        for (int playerIndex = 0; playerIndex < playerAmount; playerIndex++) {
            playerArray.add(playerIndex, new UrPlayer(UrPiece::new, pieceAmount));
        }
    }/*
    /**
     * Created for the Royal Game of Ur with UrTiles.
     *//*
    @Override
    protected void createBoard(ArrayList<ArrayList<Boolean>> boolMatrix) {
        gameBoard = new Board(UrTile::new, tileAmount, amountRows, amountCols);
        gameBoard.setAdjacentMatrix(boolMatrix);
    }
    /**
     * Sets player information with given arrays.
     * @param playerNames All player names.
     * @param playerColors All player colors.
     * @return whether the operation was successful.
     *//* 
    public boolean setPlayerInfo(ArrayList<String> playerNames, ArrayList<Color> playerColors) {
        boolean sucess = false;
        if (playerAmount == playerNames.size() && playerNames.size() == playerColors.size()) {
            for(int playerIndex = 0; playerIndex < playerAmount; playerIndex++) {
                playerArray.get(playerIndex).setColor(playerColors.get(playerIndex));
                playerArray.get(playerIndex).setName(playerNames.get(playerIndex));
            }
        }
        return sucess;
    }
    @Override
    protected boolean validateMove(int playerId, int x, int y, int moves) {
        boolean canMove = false;
        
        
        return canMove;
    }
    @Override
    protected boolean validateScore(int playerId, int lastMoveRow, int lastMoveCol) {
        boolean canMove = false;
        
        //if 
        
        return canMove;
    }*/

    @Override
    protected void createBoard(ArrayList boolMatrix) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected boolean validateMove(int playerId, int x, int y, int moves) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected boolean validateScore(int playerId, int lastMoveRow, int lastMoveCol) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected int validateScore() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
