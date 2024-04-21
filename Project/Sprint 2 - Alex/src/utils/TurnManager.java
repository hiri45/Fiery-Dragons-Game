package src.utils;

import src.actors.DragonToken;

import java.util.ArrayList;

public class TurnManager {
    private int playerTurn;
    private static TurnManager instance;
    public ArrayList<DragonToken> players;

    public int playerCount;
    public TurnManager(int playerCount){
        players = new ArrayList<>();
        playerTurn = 0;
        this.playerCount = playerCount;
        //Add a dragon token for every player in the game
        for(int i = 0; i < this.playerCount; i++){
            players.add(new DragonToken());
        }

    }

    public static TurnManager getInstance(int playerCount){
        if(instance == null){
            instance = new TurnManager(playerCount);
        }
        return instance;
    }

    public void players(int playerCount){

    }
    public void updatePlayerTurn(){
        if (playerTurn < (players.size()-1)){
            playerTurn += 1;
        }else{
            playerTurn = 0;
        }

    }
    public void resetPlayerCount(){
        playerCount = 0;
        players = new ArrayList<>();
    }
}
