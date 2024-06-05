package src.utils;

import src.Creature.Creature;
import src.board.Cave;
import src.gui.CavePanel;
import src.gui.DragonTokenPanel;
import src.gui.WindowPanel;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;


public class SaveLoad {
    /**
     * This class is used to save and load instances of the game
     * @param windowPanel which is the instance of the game panel
     * */
    WindowPanel windowPanel;
    PlayerManager playerManager;
    public SaveLoad(WindowPanel windowPanel) {
        this.windowPanel = windowPanel;
        this.playerManager = PlayerManager.getInstance();
    }

    /**
     * this method is used to load a previously saved version of the game, or an edited text file
     * */
    public void loadGame(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("savedGame.txt"));
            int playerAmount = Integer.parseInt(bufferedReader.readLine());
            int playerTurn = Integer.parseInt(bufferedReader.readLine());

            // Loading details regarding the player
            WindowPanel.resetInstance(playerAmount);
            windowPanel = WindowPanel.getInstance();
            playerManager.setPlayerTurn(playerTurn);
            playerManager.setPlayerCount(playerAmount);

            // Load dragon token positions
            for (int i = 0; i < playerAmount; i++) {
                String[] position = bufferedReader.readLine().split(",");
                int x = Integer.parseInt(position[0]);
                int y = Integer.parseInt(position[1]);
                DragonTokenPanel dragonTokenPanel = windowPanel.getDragonTokenPanel(i);
                dragonTokenPanel.moveDragonToken(x, y);
            }
            // Load the creature types for each cave
            ArrayList<CavePanel> cavePanels = windowPanel.getCavePanels();
            for (CavePanel cavePanel : cavePanels) {
                String creatureTypeString = bufferedReader.readLine();
                Creature creature = Creature.stringToCreature(creatureTypeString);
                Cave cave = cavePanel.getCave();
                cave.setCreatureType(creature);
                cavePanel.refreshCavePanel();
            }

            bufferedReader.close();

        }
        catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(windowPanel, "Failed to load game", "Error", JOptionPane.ERROR_MESSAGE);

        }

    }

    /**
     * this method is used to save an instance of the game when the save button on the game panel is pressed
     * */
    public void saveGame() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("savedGame.txt"));
            // save the player details
            bufferedWriter.write(""+playerManager.getPlayerCount());
            bufferedWriter.newLine();
            bufferedWriter.write(""+playerManager.getPlayerTurn());
            bufferedWriter.newLine();
            // Save dragon token positions
            ArrayList<DragonTokenPanel> dragonTokenPanels = windowPanel.getDragonTokenPanels();
            for (DragonTokenPanel dragonTokenPanel : dragonTokenPanels) {
                bufferedWriter.write(dragonTokenPanel.getX() + "," + dragonTokenPanel.getY());
                bufferedWriter.newLine();
            }

            // Save the creature types for each cave
            ArrayList<CavePanel> cavePanels = windowPanel.getCavePanels();
            for (CavePanel cavePanel : cavePanels) {
                Cave cave = cavePanel.getCave();
                bufferedWriter.write(cave.getCreatureType().getName());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WindowPanel getWindowPanel() {
        return windowPanel;
    }


}
