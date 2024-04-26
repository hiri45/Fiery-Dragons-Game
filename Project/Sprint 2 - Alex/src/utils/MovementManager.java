package src.utils;

import src.actors.DragonToken;
import src.gui.DragonTokenPanel;
import src.gui.SquarePanel;
import src.gui.WindowPanel;

public class MovementManager {
    private WindowPanel windowPanel;
    private static MovementManager instance;

    private static PlayerManager playerManager;

    public static void main(String[] args) {
        playerManager = PlayerManager.getInstance();
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

    public boolean canMove(DragonToken dragonToken, int noPositions){

        //return false if any other dragon token is on the position the current dragon token wants to move onto
//        for(DragonToken player: playerManager.getPlayers()){
//            if (player.getPosition() == dragonToken.getPosition() + noPositions){
//                return false;
//            }
//        }

        //return true if the new position is greater than distance to the cave
        boolean res = dragonToken.getPosition()+noPositions >= (windowPanel.getBoardPanels().size());
        if(res){
            dragonToken.setPosition(dragonToken.getPosition() - windowPanel.getBoardPanels().size());
            return true;
        }
        return true;

    }

    public void updatePosition(DragonToken dragonToken, int noPositions){
        int newPosition;
        //if the player's position is still in their cave move out of the cave
        if (dragonToken.getCave().getCavePosition() == dragonToken.getPosition() && dragonToken.isInCave()){
            newPosition = dragonToken.getPosition() + noPositions;
            dragonToken.setInCave(false);
        }else{
            newPosition = dragonToken.getPosition()+noPositions;
        }
        if(dragonToken.getPosition()+noPositions == dragonToken.getCave().getCavePosition() + 1){
            System.out.println("you have won the game");
        }

        dragonToken.setPosition(newPosition);
        dragonToken.addMovement(noPositions);

        // Update UI
        windowPanel.moveToken(dragonToken.getDragonTokenPanel());
    }

    public void isMatch(){

    }
}
