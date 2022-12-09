/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Mauricio Palma
 */
public class CommandMovePiece implements CommandInterface {

    private Board board;
    private ArrayList<Player> playerArray;
    private int currentPlayer;
    private Dice dice;
    private Tile lastMovedTile;
    
    private Tile nextMoveTile;

    /**
     *
     * @param board
     * @param playerArray
     * @param currentPlayer
     * @param dice
     */
    public CommandMovePiece(Board board, ArrayList<Player> playerArray, int currentPlayer, Dice dice, Tile lastMovedTile, Tile nextMoveTile) {
        this.board = board;
        this.playerArray = playerArray;
        this.currentPlayer = currentPlayer;
        this.dice = dice;
        this.lastMovedTile = lastMovedTile;
        this.nextMoveTile = nextMoveTile;
    }
    
    private void updateCurrentPlayer(){
        currentPlayer++;
        currentPlayer = currentPlayer % playerArray.size();
    }
    /*
    private ArrayList<Integer> getTilesAdjacents(int x, int y, int tileJumps) {
       /* ArrayList<Integer> adjacents = new ArrayList<>();
        
        if (x >= 0 && x < amountRows && y >= 0 && y < amountCols) {
            int currentVertexIndex = getVertexIndexThroughXYCoordinates(x, y);
            //System.out.println("x: " + x + ", y: " + y);
            System.out.println("currentVertexIndex: " + currentVertexIndex);
            System.out.println("tileJumps: " + tileJumps);

            while(tileJumps > 1 && currentVertexIndex < verticesAmount) {
                foundAdjacent = false;
                for(int columnIndex = 0; columnIndex < vertices.size(); columnIndex++) {
                    if(foundAdjacent != true && graphAdjacentMatrix.get(currentVertexIndex).get(columnIndex) == true) {
                        currentVertexIndex = columnIndex;
                        foundAdjacent = true;
                        System.out.println("currentVertexIndex: " + currentVertexIndex);
                    }
                }
                tileJumps--;
            }
            System.out.println("Final currentVertexIndex: " + currentVertexIndex);
            for(int columnIndex = 0; columnIndex < vertices.size(); columnIndex++) {
                if(graphAdjacentMatrix.get(currentVertexIndex).get(columnIndex) == true) {
                    adjacents.add(columnIndex);
                }
            }
        }
        return adjacents;
    }*/
    
    @Override
    public boolean execute() {
        // saber pieza inicial, se sabe de player
        // 
        return true;
    }

    @Override
    public boolean unExecute() {
        return false;    
    }
    
}
