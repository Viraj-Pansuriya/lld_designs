package org.example.models;

import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Game {
    private final Deque<Player> players;
    private final Board board;

    public Game(Deque<Player> players, Board board) {
        this.players = players;
        this.board = board;
    }


    public Player startGame(){

        Scanner scanner = new Scanner(System.in);
        int remaining = board.getRemaining();
        boolean gameOver = false;
        board.displayBoard();
        while(board.getRemaining() > 0){


            Player player = players.poll();
            if(player == null){
                throw new RuntimeException("No more players");
            }
            System.out.println(player.getName() + "'s turn");
            boolean isValidMove = false;

            while(!isValidMove){
                System.out.println("Enter x and y for player : " + player.getName() + " and character :  "  + player.getCharacter().getPieceType().name() +  ". Format :  x,y (comma seperated) ");
                String[] input = scanner.next().split(",");
                int x = Integer.parseInt(input[0]);
                int y = Integer.parseInt(input[1]);
                isValidMove = board.fillBoard(player.getCharacter() , x , y);
            }
            gameOver = board.isWinner(player.getCharacter().getPieceType());
            board.displayBoard();
            if(gameOver){
                return player;
            }

            players.add(player);
            remaining--;
            board.setRemaining(remaining);

        }
        return null;
    }
}
