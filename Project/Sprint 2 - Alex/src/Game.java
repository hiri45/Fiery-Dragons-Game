package src;

import src.board.BoardArray;
import src.board.DragonCardArray;
import src.utils.TurnManager;

public class Game{

    public static void main(String[] args) {
        int playerCount;

        //Create the board
        BoardArray boardArray = BoardArray.getInstance();

        //Create the chit cards
        DragonCardArray dragonCardArray = new DragonCardArray();


        //Create a turn manager and add players
        playerCount = 4;
        TurnManager turnManager = TurnManager.getInstance(playerCount);
    }

}