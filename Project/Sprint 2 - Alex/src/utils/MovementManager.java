package src.utils;

import src.actors.DragonToken;
import src.board.BoardArray;
import src.gui.DragonTokenPanel;
import src.gui.SquarePanel;
import src.gui.WindowPanel;

public class MovementManager {
    private WindowPanel windowPanel;
    private static MovementManager instance;


    public static void main(String[] args) {
    }
    public static MovementManager getInstance(){
        if(instance == null){
            instance = new MovementManager();
        }
        return instance;
    }

    public void setWindowPanel(WindowPanel windowPanel) {
        this.windowPanel = windowPanel;
    }

//    public boolean canMove(DragonToken dragonToken, int noPositions){
//
////        return false if any other dragon token is on the position the current dragon token wants to move onto
////        for(DragonToken player: playerManager.getPlayers()){
////            if (player.getPosition() == dragonToken.getPosition() + noPositions){
////                return false;
////            }
////        }
//
//        //return true if the new position is greater than distance to the cave
//        boolean res = dragonToken.getPosition()+noPositions >= (windowPanel.getBoardPanels().size());
//        if(res){
//            dragonToken.setPosition(dragonToken.getPosition() - windowPanel.getBoardPanels().size());
//            return true;
//        }
//        return true;
//
//    }
    public boolean canMove(DragonToken dragonToken, int noPositions) {
        PlayerManager playerManager = PlayerManager.getInstance();
        int newPosition = (dragonToken.getPosition() + noPositions) % windowPanel.getBoardPanels().size();

        // Check if the new position is occupied by another token
        for (DragonToken player : playerManager.getPlayers()) {
            if (player != dragonToken && player.getPosition() == newPosition && !player.isInCave()) {
                // Another token is in the new position, can't move here
                return false;
            }
        }
        if (noPositions < 0 && dragonToken.getCave().getCavePosition() < noPositions){
            return false;
        }
        return true;

    }

    private boolean isCaveWithinReach(DragonToken dragonToken) {
        if (dragonToken.getCave().getCavePosition() - dragonToken.getPosition() < 3) {
            return true;
        }
        return false;
    }

    private int calculateDistanceToCave(DragonToken dragonToken) {
        int distance = dragonToken.getCave().getCavePosition() - dragonToken.getPosition();
        if (distance == 0){
            return 1;
        }
        return distance;
    }


    public void updatePosition(DragonToken dragonToken, int noPositions){
        int newPosition = dragonToken.getPosition() + noPositions;
        BoardArray boardArray = BoardArray.getInstance();
        int boardSize = boardArray.getSquares().size();

        int currentPosition = dragonToken.getPosition();
        int cavePos = dragonToken.getCave().getCavePosition();
        if(dragonToken.getPosition() > boardSize-2){
            dragonToken.setPosition(dragonToken.getPosition()-boardSize);
        }
        //if the player's position is still in their cave move out of the cave
        if (dragonToken.isInCave()){
            newPosition = currentPosition;
            if (noPositions > 1){
                newPosition = currentPosition + noPositions - 1;
            }
            //handles backwards movement
        }else if (noPositions < 0){
            if (newPosition < cavePos){
                newPosition = dragonToken.getCave().getCavePosition() + noPositions;
            }

        }else{
            newPosition = dragonToken.getPosition()+noPositions;
        }


        dragonToken.setPosition(newPosition);
        dragonToken.addMovement(noPositions);

        // Update UI
        windowPanel.moveToken(dragonToken.getDragonTokenPanel(),noPositions);
    }

    public void isMatch(){

    }
}
