package src.utils;

import src.actors.DragonToken;

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
    }
    public void resetPlayerCount(){
        //at the end of the game, if a player wants to choose a different number of players, reset the player count
        playerCount = 0;
        players = new ArrayList<>();
    }
}
