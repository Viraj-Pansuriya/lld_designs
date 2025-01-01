package org.example.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class Board {
    private PieceType[][] board;
    private int remaining;
    private static Board boardInstance;
    private int size;
    private Board() {

        if(boardInstance != null){
            throw new RuntimeException("Board is already initialized");
        }
        System.out.println("Board is initialized");
    }

    public  static Board getInstance(int size){
        if(boardInstance == null){
            boardInstance = new Board();
            boardInstance.board = new PieceType[size][size];
            boardInstance.remaining = size*size;
            boardInstance.size = size;
        }
        return boardInstance;
    }

    @Override
    public String toString() {
        return "Board{" +
                "board=" + Arrays.toString(board) +
                ", remaining=" + remaining +
                '}';
    }

    public boolean fillBoard(Character character , int x , int y){
        try{
            if(x < 0 || x > size || y < 0 || y > size){
                throw new IllegalArgumentException("x and y must be between 0 and " + size);
            }
            if(board[x][y] != null ){
                throw new IllegalArgumentException("Cell is already filled");
            }
        }
        catch (Exception e){
            System.out.println("ERROR : " + e.getMessage());
            return false;
        }

        board[x][y] = character.getPieceType();
        return true;

    }

    public void displayBoard(){
        boolean isFirstColumn = true;
        for (PieceType[] row : board) {
            for (PieceType cell : row) {
                if(isFirstColumn)
                    System.out.print("|");
                if(cell == null)
                    System.out.print(" -" + " |");
                else
                    System.out.print(" " + cell + " |");
                isFirstColumn = false;
            }
            isFirstColumn = true;
            System.out.println();
        }
    }



    public boolean isWinner(PieceType type) {
        boolean rowMatch, columnMatch, diagonalMatch = true, antiDiagonalMatch = true;

        // Check rows and columns
        for (int i = 0; i < size; i++) {
            rowMatch = true;
            columnMatch = true;

            for (int j = 0; j < size; j++) {
                // Check row
                if (board[i][j] != type) {
                    rowMatch = false;
                }
                // Check column
                if (board[j][i] != type) {
                    columnMatch = false;
                }
            }

            // If either a row or column matches completely, return true
            if (rowMatch || columnMatch) {
                return true;
            }
        }

        // Check main diagonal
        for (int i = 0; i < size; i++) {
            if (board[i][i] != type) {
                diagonalMatch = false;
                break;
            }
        }

        // Check anti-diagonal
        for (int i = 0; i < size; i++) {
            if (board[i][size - 1 - i] != type) {
                antiDiagonalMatch = false;
                break;
            }
        }

        // If either diagonal matches completely, return true
        return diagonalMatch || antiDiagonalMatch;
    }

}
