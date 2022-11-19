/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package model;

import java.awt.Color;

public class JavaTests {

    public static void main(String[] args) {
        UrPlayerModel player1 = new UrPlayerModel(Color.RED, 0);
        UrPlayerModel player2 = new UrPlayerModel(Color.BLUE, 2);
        UrBoardModel board = new UrBoardModel(Color.RED, Color.BLUE);
        
        // jugador 1
        UrPieceModel piece1 = player1.getPlayerPiece(1);
        System.out.println("piece1: " + piece1.getColor());
        // jugador 2
        UrPieceModel piece2 = player2.getPlayerPiece(0);
        System.out.println("piece2: " + piece2.getColor());
        
        // jugador 1
        board.setPiece(0, 1, piece1);
        // jugador 2
        board.setPiece(1, 0, piece2);
        
        // player 2 quiere mover pieza 2 a pieza 1
        UrTileModel tileExample1 = board.getPossibleTile(board.getTile(1, 0), 2, player2.getColor());
        System.out.println("tileExample1: " + tileExample1);
        
        // falta reemplazar y quitar safe
        if (tileExample1 != null) {
            System.out.println("Setting piece");
            board.setPiece(3, 1, piece2);
        }
        
    }
}
