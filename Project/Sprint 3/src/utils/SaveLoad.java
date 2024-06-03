package src.utils;

import src.gui.WindowPanel;

import java.io.BufferedReader;
import java.io.FileReader;

public class SaveLoad {
    WindowPanel windowPanel;
    public SaveLoad(WindowPanel windowPanel) {
        this.windowPanel = windowPanel;
    }
    public void loadGame(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("savedGame.txt"));
            int playerTurn = Integer.parseInt(bufferedReader.readLine());
            bufferedReader.close();

            PlayerManager playerManager = PlayerManager.getInstance();
            playerManager.setPlayerTurn(playerTurn);
            windowPanel.repaint();
        }
        catch (Exception e){

        }

        //windowPanel.currentPLayer = playerTurn;
    }
}
