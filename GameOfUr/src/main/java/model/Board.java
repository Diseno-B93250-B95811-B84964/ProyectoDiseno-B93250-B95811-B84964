/*
 * Issue #25 - Graph logic.
 * Jimena Gdur Vargas - B93250.
 * Álvaro Miranda Villegas - B84964.
 * Ronald Palma Villegas - B95811.
 */
package model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Creates a game board using a graph to store the information.
 * @author Jimena Gdur.
 * @param <TileType> Tile's child class.
 */
public final class Board<TileType extends Tile> extends GameObject
{
    /**
     * The graph's adjacent matrix.
     * It indicates all possible routes.
     */
    private ArrayList<ArrayList<Boolean>> graphAdjacentMatrix;
    
    /**
     * Amount of vertices in graph.
     */
    private final int verticesAmount;
    /**
     * Stores all of the graph's vertices.
     * It also serves as a list of all game tiles.
     */
    private ArrayList<TileType> vertices;
    /**
     * Amount of rows in game board
     */
    private int amountRows;
    /**
     * Amount of columns in game board
     */
    private int amountColumns;
    /**
     * Generic data type that extends Tile model
     */
    private final TileType tileType;

    /**
     * Creates a new Board represented as a graph given the amount of vertices given.
     * @param vertices The number of vertices to be created.
     * @param rows Amount of rows board has.
     * @param cols Amount of columns board has.
     * @param tileType Type of tile board has
    */
    public Board(int vertices, int rows, int cols, TileType tileType) {
        this.verticesAmount = vertices;
        this.tileType = tileType;
        setBoardDimensions(rows, cols);
        createVerticesArray();
        createAdjacentMatrix();
    }
    
    /**
     * Creates a vertices array with the specific tile received through parameter.
    */
    private void createVerticesArray() {
        vertices = new ArrayList<>(verticesAmount);
        for (int vertexIndex = 0; vertexIndex < verticesAmount; vertexIndex++) {
            int x = getRowThroughVertexIndex(vertexIndex);
            int y = getColumnThroughVertexIndex(vertexIndex); 
            try {
                vertices.add(makeNewTile(x,y));
            } catch (IllegalAccessException | InstantiationException | 
                    NoSuchMethodException | IllegalArgumentException | 
                    InvocationTargetException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
    * Creates a new object of generic type TileType and stores it into vertices arraylist.
     * @param x 
     * @param y
     * @param safeTile
    * @throws IllegalAccessException Exception that is thrown if object tries to access an invalidad memory reference
    * @throws InstantiationException Exception that is thrown if object cannot be instantiated
    * @throws NoSuchMethodException Exception that is thrown if method called does not exist
    * @throws IllegalArgumentException Exception that is thrown if arguments do not match requested method
    * @throws InvocationTargetException Exception that is thrown if target cannot be invoked
    */
    public TileType makeNewTile(int x, int y) throws IllegalAccessException,InstantiationException, 
            NoSuchMethodException, IllegalArgumentException, InvocationTargetException 
    {
        TileType newTile = (TileType)tileType.getClass()
                .getConstructor(int.class, int.class)
                .newInstance(x,y);
        return newTile;
    }
    
    /**
     * Creates the graph's adjacent matrix.
    */
    private void createAdjacentMatrix() {
        graphAdjacentMatrix = new ArrayList<>(verticesAmount);
        for (int vertexIndex1 = 0; vertexIndex1 < verticesAmount; vertexIndex1++) {
            graphAdjacentMatrix.add(new ArrayList<>(verticesAmount));
            for (int vertexIndex2 = 0; vertexIndex2 < verticesAmount; vertexIndex2++) {
                graphAdjacentMatrix.get(vertexIndex1).add(vertexIndex2, false);
            }
        }
    }
    /**
     * Sets board dimensions with given parameters.
     * @param rows Amount of rows board has.
     * @param columns Amount of columns board has.
     */
    private void setBoardDimensions(int rows, int columns) {
        this.amountRows = rows;
        this.amountColumns = columns;
    }
    /**
     * Sets the given adjacent matrix to this board's adjacent matrix
     * @param adjacentMatrix
     */
    public void setAdjacentMatrix(ArrayList<ArrayList<Boolean>> adjacentMatrix) {
        this.graphAdjacentMatrix = adjacentMatrix;
    }
    public void setRows(int rows) {
        this.amountRows = rows;
    }
    public void setColumns(int columns) {
        this.amountColumns = columns;
    }
    /**
     * Converts from array to matrix.
     * @param vertexIndex Tile's index in vertices array
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
     * @param vertexIndex Tile's index in vertices array
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
    public int getVertexIndexThroughXYCoordinates(int x, int y) {
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
     * Gets a specific tile using the given coordinates.
     * @param x Row in which tile is located.
     * @param y Column in which tile is located.
     * @return A generic type whose parent class is Tile.
     */
    public ArrayList<TileType> getVertices(int x, int y) {
        ArrayList<TileType> tileVertices = new ArrayList<>();
        int rowIndex = getVertexIndexThroughXYCoordinates(x, y);
        
        for(int columnIndex = 0; columnIndex < verticesAmount; columnIndex++) {
            if (columnIndex != rowIndex) {
                if(graphAdjacentMatrix.get(rowIndex).get(columnIndex) == true) {
                    tileVertices.add(vertices.get(columnIndex));
                }
            }
        }
        return tileVertices;
    }
    /**
     * Sets given piece in given coordinates.
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
     * Gets amount of rows in game board.
     * @return amountRows value.
     */
    public int getAmountRows() {
        return this.amountRows;
    }
    /**
     * Gets amount of columns in game board.
     * @return amountColumns value.
     */
    public int getAmountColumns() {
        return this.amountColumns;
    }
    
    public ArrayList<TileType> getVerticesArray(){
        return this.vertices;
    }
    
    public int getVerticesAmount(){
        return this.verticesAmount;
    }
    
    public Tile getTile(int index){
        return this.vertices.get(index);
    }
    
    /**
     * Converts board into a string.
     * @return a string representing a board
     */
    @Override
    public String toString() {
        String string  = "graphAdjacentMatrix:\n";
        for (ArrayList<Boolean> row : this.graphAdjacentMatrix) {
            string += row + "\n";
        }
        string += "\nvertices:\n";
        for (int vertexIndex = 0; vertexIndex < verticesAmount; vertexIndex++) {
            string += "vertex " + vertexIndex + ": " + this.vertices.get(vertexIndex) + "\n";
        }
        
        return string;
    }
}
