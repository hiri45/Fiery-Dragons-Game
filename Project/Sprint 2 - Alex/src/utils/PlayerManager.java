/**
 * File: PlayerManager.java
 *
 * Description:
 * Manages the players within the Fiery Dragons game, handling player turns, player registration,
 * and assigning caves to dragon tokens. This class ensures that players are rotated correctly
 * and that each dragon token is assigned to a cave if available.
 *
 * Author: Alex Ung
 * Last Modified: 29/04/2024
 */
package src.utils;

import src.actors.DragonToken;
import src.board.BoardArray;
import src.board.VolcanoCard;

import java.util.ArrayList;

public class PlayerManager {
    private int playerTurn;
    private static PlayerManager instance;
    public ArrayList<DragonToken> players;

    public int playerCount;

    /**
     * Constructs a new PlayerManager, initializing the player list and setting the initial player turn.
     */
    private PlayerManager(){
        players = new ArrayList<>();
        playerTurn = 0;
        this.playerCount = 0;
        //Add a dragon token for every player in the game


    }
    /**
     * Ensures that only one instance of PlayerManager is used throughout the application.
     * Implements the Singleton pattern.
     *
     * @return The single instance of PlayerManager.
     */
    public static PlayerManager getInstance(){
        if(instance == null){
            instance = new PlayerManager();
        }
        return instance;
    }
    /**
     * Returns the list of all players.
     *
     * @return ArrayList of DragonToken, representing the players.
     */
    public ArrayList<DragonToken> getPlayers() {
        return players;
    }
    /**
     * Updates the player turn, rotating among the available players and ensuring that each
     * player gets their turn sequentially.
     */
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
    /**
     * Adds a specified number of players to the game and assigns them dragon tokens.
     *
     * @param numberOfPlayers The number of players to add.
     */
    public void addPlayers(int numberOfPlayers){
        for(int i = 0; i < numberOfPlayers; i++){
            players.add(new DragonToken(i));
        }
        playerCount += numberOfPlayers;
        assignCaves();
    }
    /**
     * Resets the player count and clears the list of players, usually called at the end of a game.
     */
    public void resetPlayerCount(){
        //at the end of the game, if a player wants to choose a different number of players, reset the player count
        playerCount = 0;
        players = new ArrayList<>();
    }
    /**
     * Assigns caves to dragon tokens. Each token is given a cave if available, ensuring no two tokens
     * are assigned the same cave.
     */
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
    public int getPlayerTurn() {
        return playerTurn;
    }


}
