/**
 * File: MovementManager.java
 *
 * Description:
 * Manages the logic for moving dragon tokens on the game board within the Fiery Dragons game.
 * This class is responsible for checking move validity, updating token positions, and managing
 * the interaction between game rules and user actions.
 *
 * Author: Alex Ung
 * Last Modified: 29/04/2024
 */
package src.utils;

import src.actors.DragonToken;
import src.board.BoardArray;
import src.gui.WindowPanel;

public class MovementManager {
    private WindowPanel windowPanel;
    private static MovementManager instance;


    /**
     * Retrieves the single instance of MovementManager, creating it if it does not yet exist.
     * This method implements the Singleton design pattern to ensure that only one instance
     * of MovementManager is used throughout the game.
     *
     * @return The single instance of MovementManager.
     */
    public static MovementManager getInstance(){
        if(instance == null){
            instance = new MovementManager();
        }
        return instance;
    }

    /**
     * Sets the WindowPanel that this manager will interact with.
     *
     * @param windowPanel The WindowPanel to be used for game interactions.
     */
    public void setWindowPanel(WindowPanel windowPanel) {
        this.windowPanel = windowPanel;
    }
    /**
     * Determines if a dragon token can move the specified number of positions on the board.
     *
     * @param dragonToken The dragon token attempting to move.
     * @param noPositions The number of positions to move.
     * @return true if the move is possible, false otherwise.
     */
    public boolean canMove(DragonToken dragonToken, int noPositions) {
        PlayerManager playerManager = PlayerManager.getInstance();
        int newPosition = (dragonToken.getPosition() + noPositions) % windowPanel.getBoardPanels().size();

        //If the current token is in the cave, remove one position from the number of positions to move
        //because cave and square in front both have the same position
        if (dragonToken.isInCave()){
            newPosition = dragonToken.getPosition()+noPositions - 1;
        }

        for (DragonToken player : playerManager.getPlayers()) {
            if (player != dragonToken && player.getPosition() == newPosition && (!player.isInCave() || dragonToken.isInCave())) {
                return false;// Block move if another token is in the target position.
            }
        }
        if (noPositions < 0 && dragonToken.getCave().getCavePosition() < noPositions){
            return false;// Block backward movement into the cave if not aligned with the cave entry rules.
        }
        return true;

    }
    /**
     * Updates the position of a dragon token based on the number of positions to move.
     * Handles wrapping around the board and interactions with caves.
     *
     * @param dragonToken The dragon token to update.
     * @param noPositions The number of positions to move the token.
     */
    public void updatePosition(DragonToken dragonToken, int noPositions){

        int newPosition = dragonToken.getPosition() + noPositions;
        BoardArray boardArray = BoardArray.getInstance();
        int boardSize = boardArray.getSquares().size();

        int currentPosition = dragonToken.getPosition();
        int cavePos = dragonToken.getCave().getCavePosition();

        //if the new position is greater than the size of the board
        int tempVar = (newPosition) % (boardSize);

        if( tempVar <= 2 && newPosition >= 23 ){
            newPosition = tempVar; // Reset position if wrapping around the board.
        }else{
            //if the player's position is still in their cave move out of the cave
            if (dragonToken.isInCave()){
                newPosition = currentPosition;
                if (noPositions > 1){
                    newPosition = currentPosition + noPositions - 1; // Adjust for movement out of the cave.
                }
                //handles backwards movement
            }else if (noPositions < 0){
                if(newPosition < 0){
                    newPosition = boardSize + noPositions;
                }else if(newPosition < cavePos){
                    newPosition = dragonToken.getCave().getCavePosition() + noPositions;
                }

            }else{
                newPosition = dragonToken.getPosition()+noPositions;
            }
        }

        dragonToken.setPosition(newPosition);
        dragonToken.addMovement(noPositions);

        // Update UI
        windowPanel.moveToken(dragonToken.getDragonTokenPanel(),noPositions);
    }

    /**
     * Placeholder method for future functionality to check if the dragon card drawn matches the requirements.
     */
    public void isMatch(){
        // Implementation to be added
    }
}
