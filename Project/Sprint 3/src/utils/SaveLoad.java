package src.utils;

import src.gui.WindowPanel;

import javax.swing.*;
import java.io.*;
import java.util.Objects;

public class SaveLoad {
    WindowPanel windowPanel;
    PlayerManager playerManager;
    public SaveLoad() {}

    public void loadGame(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("savedGame.txt"));
            int playerAmount = Integer.parseInt(bufferedReader.readLine());
            int playerTurn = Integer.parseInt(bufferedReader.readLine());
            bufferedReader.close();
            // Loading details regarding the player
            WindowPanel.resetInstance(playerAmount);
            windowPanel = WindowPanel.getInstance();
            //System.out.println(playerTurn);
            //System.out.println(playerAmount);
            playerManager = PlayerManager.getInstance();
            playerManager.setPlayerTurn(playerTurn);
            playerManager.setPlayerCount(playerAmount);

        }
        catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(windowPanel, "Failed to load game", "Error", JOptionPane.ERROR_MESSAGE);

        }

        //windowPanel.currentPLayer = playerTurn;
    }

    public void saveGame() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("savedGame.txt"));
            PlayerManager playerManager = PlayerManager.getInstance();
            writer.write(""+playerManager.getPlayerCount());
            writer.newLine();
            writer.write(""+playerManager.getPlayerTurn());
            // Save other game state information as needed
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WindowPanel getWindowPanel() {
        return windowPanel;
    }


}
