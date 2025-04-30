package org.example;

import org.example.models.*;
import org.example.models.Character;


import java.util.*;

public class Main {
    public static void main(String[] args) {



        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter size of board (minimum 3)");
        int size = scanner.nextInt();
        if(size < 3)
            throw new IllegalArgumentException("Board size must be greater than 3");

        Deque<Player> playerList = new ArrayDeque<>();
        System.out.println("Enter number of players between 2 to 4");
        int playerCount = scanner.nextInt();

        if(playerCount < 2 || playerCount > 4){
            throw  new IllegalArgumentException("Number of players must be between 2 and 4");
        }


        for(int i = 0 ; i < playerCount ; i++){
            System.out.println("Enter name of player " + (i+1));
            String name = scanner.next();
            playerList.add(new Player(name , new Character(PieceType.getPieceType(i))));
        }

        Board board = Board.getInstance(size); // board should follow singleton pattern

        Game game = new Game(playerList , board);

        Player player = game.startGame();

        if(player == null){
            System.out.println("Game has been drawn");
        }
        else{
            System.out.println(player.getName() + " has won the game");
        }
    }
}