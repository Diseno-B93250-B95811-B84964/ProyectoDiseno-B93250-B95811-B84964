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
        UrSerializerModel state = new UrSerializerModel(board, player1, player2);
        
        // jugador 1
        UrPieceModel piece1 = player1.getPlayerPiece(0);
        System.out.println("piece1: " + piece1.getColor());

        // jugador 2
        UrPieceModel piece2 = player2.getPlayerPiece(1);
        System.out.println("piece2: " + piece2.getColor());

        
        UrPieceModel piece3 = player2.getPlayerPiece(2);
        System.out.println("piece3: " + piece3.getColor());
        // jugador 2
        UrPieceModel piece4 = player1.getPlayerPiece(2);
        System.out.println("piece4: " + piece4.getColor());
        
        // jugador 1
        board.setPiece(4, 0, piece1);
        
        /*
        // jugador 2
        board.setPiece(1, 2, piece2);
        // jugador 2
        board.setPiece(6, 1, piece3);
        // jugador 1
        board.setPiece(3, 0, piece4);*/
        
        // player 2 quiere mover pieza 2 a pieza 1
        UrTileModel tileExample1 = board.getPossibleTile(board.getTile(4, 0), 2, player1.getColor());
        System.out.println("tileExample1: " + tileExample1.getRow() + " " + tileExample1.getColumn());
        //System.out.println(piece2.getX());
        // falta reemplazar y quitar safe
        if (tileExample1 != null) {
            System.out.println("Setting piece");
            board.setPieceTile(board.getTile(piece2.getX(), piece2.getY()), tileExample1);
        }
        System.out.println(state.saveGameState());
        System.out.println(piece1.getX() + " " + piece1.getY());
    }
}
