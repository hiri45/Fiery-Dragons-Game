package src;

import src.board.BoardArray;
import src.board.DragonCardArray;
import src.utils.Controller;
import src.utils.PlayerManager;

public class Game{

    public static void main(String[] args) {
        int playerCount;
        //Create a turn manager and add players
        playerCount = 4;
        PlayerManager playerManager = PlayerManager.getInstance();
        playerManager.addPlayers(playerCount);
        //Create the board
        BoardArray boardArray = BoardArray.getInstance();
        boardArray.addVolcanoCards(8,3);
        boardArray.shuffleAndCombine();
        boardArray.addPosition(8,3);
        //Create the chit cards
        DragonCardArray dragonCardArray = new DragonCardArray();





        Controller uiController = new Controller();
        uiController.createGameBoard();
    }

}