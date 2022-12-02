/*
 * Issue #25 - Game Logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package model;

import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * Creates a game board using a graph to store the information.
 * @author Jimena Gdur.
 * @param <TileType> Tile's child class
 */
public final class Board<TileType extends Tile> extends GameObject {
    /**
     * The graph's adjacent matrix.
     * It indicates all possible routes.
     */
    protected boolean[][] graphAdjacentMatrix;
    
    /**
     * Amount of vertices in graph.
     */
    protected int verticesAmount;
    /**
     * Stores all of the graph's vertices.
     * It also serves as a list of all game tiles.
     */
    protected ArrayList<TileType> vertices;
    /**
     * Amount of rows in game board
     */
    protected int amountRows;
    /**
     * Amount of columns in game board
     */
    protected int amountColumns;

    /**
     * Creates a new Board represented as a graph given the amount of vertices given.
     * @param supplier Supplier class that contains instance of tile's child
     * @param numberOfVertices The number of vertices to be created.
    */
    public Board(Supplier<TileType> supplier, int numberOfVertices) {
        verticesAmount = numberOfVertices;
        createVerticesArray(supplier);
        createAdjacentMatrix();
    }
    /**
     * Creates a vertices array with the specific tile received through parameter.
     * @param supplier Supplier class that contains instance of tile's child
    */
    private void createVerticesArray(Supplier<TileType> supplier) {
        vertices = new ArrayList<>(verticesAmount);
        for (int vertexIndex = 0; vertexIndex < verticesAmount; vertexIndex++) {
            int x = getRowThroughVertexIndex(vertexIndex);
            int y = getColumnThroughVertexIndex(vertexIndex);
            
            vertices.add(vertexIndex, supplier.get());
            vertices.get(vertexIndex).setRow(x);
            vertices.get(vertexIndex).setColumn(y);
            // TODO: falta definir las que son seguras
        }
    }
    private void createAdjacentMatrix() {
        graphAdjacentMatrix = new boolean[verticesAmount][verticesAmount];
        for (int vertexIndex1 = 0; vertexIndex1 < verticesAmount; vertexIndex1++) {
            for (int vertexIndex2 = 0; vertexIndex2 < verticesAmount; vertexIndex2++) {
                graphAdjacentMatrix[vertexIndex1][vertexIndex2] = false;
            }
        }
    }
    /**
     * Sets board dimensions with given parameters.
     * @param rows Amount of rows board has.
     * @param columns Amount of columns board has.
     */
    public void setBoardDimensions(int rows, int columns) {
        this.amountRows = rows;
        this.amountColumns = columns;
    }
    /**
     * Sets the given adjacent matrix to this board's adjacent matrix
     * @param adjacentMatrix
     */
    public void setAdjacentMatrix(boolean[][] adjacentMatrix) { // TODO: Check if stored correctly
        this.graphAdjacentMatrix = adjacentMatrix; // no se si caerle encima es lo mejor, pero ocupa menos tiempo
    }
    /**
     * Converts from array to matrix.
     * @param verticesIndex Tile's index in vertices array
     * @return calculated row
     */
    private int getRowThroughVertexIndex(int vertexIndex) {
        if (amountColumns != 0) {
            return vertexIndex / amountColumns;
        } else {
            return -1;
        }
    }
    /**
     * Converts from array to matrix.
     * @param verticesIndex Tile's index in vertices array
     * @return calculated column
     */
    private int getColumnThroughVertexIndex(int vertexIndex) {
        if (amountColumns != 0) {
            return vertexIndex % amountColumns;
        } else {
            return -1;
        }
    }
    /**
     * Converts from array to matrix.
     * @param x Row position in matrix
     * @param y Column position in matrix
     * @return calculated index in vertices array
     */
    private int getVertexIndexThroughXYCoordinates(int x, int y) {
        return (x * amountColumns) + y;
    }
    /**
     * Gets a specific tile using the given index.
     * @param x Row in which tile is located.
     * @param y Column in which tile is located.
     * @return A generic type whose parent class is Tile.
     */
    public TileType getTile(int x, int y) {
        TileType specifiedTile = null;
        if (x >= 0 && x < amountRows && y >= 0 && y < amountColumns) {
            int verticesIndex = getVertexIndexThroughXYCoordinates(x, y);
            specifiedTile = vertices.get(verticesIndex);
        }
        return specifiedTile;
    }
    /**
     *
     * @param x Row in which tile is located.
     * @param y Column in which tile is located.
     * @param piece Piece to be set in specified tile.
     */
    public void setPieceInTile(int x, int y, Piece piece) {
        TileType specifiedTile = getTile(x, y);
        if (specifiedTile != null) {
            specifiedTile.setPiece(piece);
        }
    }
    /**
     *
     * @param x
     * @param y
     * @param tileJumps
     * @return
     */
    public boolean[] getTilesAdjacents(int x, int y, int tileJumps) {
        if (x >= 0 && x < amountRows && y >= 0 && y < amountColumns) {
            int currentVertexIndex = getVertexIndexThroughXYCoordinates(x, y);
            while(tileJumps > 0) {
                for(int columnIndex = 0; columnIndex < vertices.size(); columnIndex++) {
                    if(graphAdjacentMatrix[currentVertexIndex][columnIndex] == true) {
                        // TODO REVISAR METODO SI HAY MAS DE UN ADYACENCIA
                    }
                }
            }
            return graphAdjacentMatrix[currentVertexIndex];
        } else {
            return null;
        }
    }
    @Override
    public String toString() {
        String string  = 
            "graphAdjacentMatrix: " + this.graphAdjacentMatrix + ", " + 
            "vertices: " + this.vertices;
        return string;
    }
}
