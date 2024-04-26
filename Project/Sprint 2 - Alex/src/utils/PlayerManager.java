package src.utils;

import src.actors.DragonToken;
import src.board.BoardArray;
import src.board.Cave;
import src.board.VolcanoCard;

import java.util.ArrayList;

public class PlayerManager {
    private int playerTurn;
    private static PlayerManager instance;
    public ArrayList<DragonToken> players;

    public int playerCount;
    public PlayerManager(){
        players = new ArrayList<>();
        playerTurn = 0;
        this.playerCount = 0;
        //Add a dragon token for every player in the game


    }

    public static PlayerManager getInstance(){
        if(instance == null){
            instance = new PlayerManager();
        }
        return instance;
    }

    public ArrayList<DragonToken> getPlayers() {
        return players;
    }

    public void updatePlayerTurn(){
        //Update the player's turn
        if (playerTurn < (players.size()-1)){
            players.get(playerTurn).setTurn(false);
            playerTurn += 1;
            players.get(playerTurn).setTurn(true);
        }else{
            players.get(playerTurn).setTurn(false);
            playerTurn = 0;
            players.get(playerTurn).setTurn(true);

        }

    }
    public void addPlayers(int numberOfPlayers){
        for(int i = 0; i < numberOfPlayers; i++){
            players.add(new DragonToken(i));
        }
        playerCount += numberOfPlayers;
        assignCaves();
    }
    public void resetPlayerCount(){
        //at the end of the game, if a player wants to choose a different number of players, reset the player count
        playerCount = 0;
        players = new ArrayList<>();
    }

    public void assignCaves(){

        BoardArray boardArray = BoardArray.getInstance();
        ArrayList<VolcanoCard> board = boardArray.getBoard();
    for(DragonToken dragonToken: players){
        // Flag to indicate if the token has been assigned a cave
        boolean tokenAssignedCave = false;

        // For every volcano card on the board
        for(VolcanoCard volcanoCard: board){
            // If the volcano card has a cave and that cave does not have an owner
            if(volcanoCard.hasCave() && volcanoCard.getCave().getCaveOwner() == null){
                // Set the owner of the cave to the dragon token
                volcanoCard.getCave().setCaveOwner(dragonToken);
                dragonToken.setPosition(volcanoCard.getCave().getCavePosition());
                // Mark that the token has been assigned a cave
                tokenAssignedCave = true;
                break; // Break out of the loop after assigning a cave
            }
        }

        // If a token has been assigned, skip to the next token
        if(tokenAssignedCave){
            continue;
        }
        }
    }
}
