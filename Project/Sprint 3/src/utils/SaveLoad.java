package src.utils;

import src.Creature.Creature;
import src.board.*;
import src.gui.*;

import javax.swing.*;
import java.awt.desktop.SystemEventListener;
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
            int volcanoCardAmount = Integer.parseInt(bufferedReader.readLine());
            int volcanoCardSquaresAmount = Integer.parseInt(bufferedReader.readLine());
            int playerTurn = Integer.parseInt(bufferedReader.readLine());

            // creating board with saved data
            BoardArray boardArray = BoardArray.getInstance();
            boardArray.setVolcanoCardCount(volcanoCardAmount);
            boardArray.setSquaresPerVC(volcanoCardSquaresAmount);
            boardArray.addVolcanoCards(volcanoCardAmount, volcanoCardSquaresAmount);
            boardArray.addPosition();

            WindowPanel.resetInstance(playerAmount, volcanoCardAmount, volcanoCardSquaresAmount);
            windowPanel = WindowPanel.getInstance();

            // Loading details regarding the player
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
                String[] position = bufferedReader.readLine().split(",");
                int cavePosition = Integer.parseInt(position[0]);
                int xPos = Integer.parseInt(position[1]);
                int yPos = Integer.parseInt(position[2]);
                cave.setCavePosition(cavePosition);
                cavePanel.setX(xPos);
                cavePanel.setY(yPos);
                cavePanel.refreshCavePanel();
            }

            // Load dragon cards
            DragonCardPool dragonCardPool = windowPanel.getDragonCardPool();
            ArrayList<DragonCard> dragonCards = new ArrayList<>();
            int numCards = Integer.parseInt(bufferedReader.readLine());
            for (int i = 0; i < numCards; i++) {
                String creatureTypeString = bufferedReader.readLine();
                int creatureAmount = Integer.parseInt(bufferedReader.readLine());
                boolean flipped = Boolean.parseBoolean(bufferedReader.readLine());
                Creature creature = Creature.stringToCreature(creatureTypeString);
                DragonCard card = new DragonCard(creature, creatureAmount);
                card.setFlipped(flipped);
                dragonCards.add(card);
            }
            dragonCardPool.setDragonCards(dragonCards);

            // save volcano card squares and positions
            ArrayList<VolcanoCard> volcanoCards = windowPanel.getVolcanoCards();
            for (VolcanoCard volcanoCard : volcanoCards) {
                int volcStartPos = Integer.parseInt(bufferedReader.readLine());
                volcanoCard.setStartPosition(volcStartPos);
            }

            ArrayList<SquarePanel> squarePanels = windowPanel.getSquarePanels();
            for (SquarePanel squarePanel : squarePanels) {
                String[] position = bufferedReader.readLine().split(",");
                int x = Integer.parseInt(position[0]);
                int y = Integer.parseInt(position[1]);
                squarePanel.setX(x);
                squarePanel.setY(y);
            }
            // Set creatures for squares of each volcano card
            for (VolcanoCard volcanoCard : volcanoCards) {
                ArrayList<Square> squares = volcanoCard.getSquares();
                String[] creatureNames = bufferedReader.readLine().split(", ");
                for (int i = 0; i < squares.size(); i++) {
                    Creature creature = Creature.stringToCreature(creatureNames[i]);
                    squares.get(i).setCreature(creature);
                }
                for(SquarePanel squarePanel:squarePanels){
                    squarePanel.updateCreatureImage();
                }
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
            bufferedWriter.write(""+windowPanel.getVolcanoCardCount());
            bufferedWriter.newLine();
            bufferedWriter.write(""+windowPanel.getSquaresPerVC());
            bufferedWriter.newLine();
            bufferedWriter.write(""+playerManager.getPlayerTurn());
            bufferedWriter.newLine();
            // Save dragon token positions
            ArrayList<DragonTokenPanel> dragonTokenPanels = windowPanel.getDragonTokenPanels();
            for (DragonTokenPanel dragonTokenPanel : dragonTokenPanels) {
                bufferedWriter.write(dragonTokenPanel.getX() + "," + dragonTokenPanel.getY());
                bufferedWriter.newLine();
            }

            // Save the creature type and position for each cave
            ArrayList<CavePanel> cavePanels = windowPanel.getCavePanels();
            for (CavePanel cavePanel : cavePanels) {
                Cave cave = cavePanel.getCave();
                bufferedWriter.write(cave.getCreatureType().getName());
                bufferedWriter.newLine();
                bufferedWriter.write(cave.getCavePosition()+ "," + cavePanel.getX() + "," + cavePanel.getY());
                bufferedWriter.newLine();
            }

            // Save dragon cards
            DragonCardPool dragonCardPool = windowPanel.getDragonCardPool();
            ArrayList<DragonCard> dragonCards = dragonCardPool.getDragonCards();
            bufferedWriter.write("" + dragonCards.size());
            bufferedWriter.newLine();
            for (DragonCard card : dragonCards) {
                bufferedWriter.write(card.getCreature().getName());
                bufferedWriter.newLine();
                bufferedWriter.write("" + card.getCreatureAmount());
                bufferedWriter.newLine();
                bufferedWriter.write("" + card.isFlipped());
                bufferedWriter.newLine();
            }

            // save volcano card squares and positions
            ArrayList<VolcanoCard> volcanoCards = windowPanel.getVolcanoCards();
            for (VolcanoCard volcanoCard:volcanoCards){
                bufferedWriter.write("" + volcanoCard.getStartPosition());
                bufferedWriter.newLine();
            }

            ArrayList<SquarePanel> squarePanels = windowPanel.getSquarePanels();
            for (SquarePanel squarePanel: squarePanels) {
                bufferedWriter.write(squarePanel.getXCoordinate() + "," + squarePanel.getYCoordinate());
                bufferedWriter.newLine();
            }
            for (VolcanoCard volcanoCard : volcanoCards) {
                ArrayList<Square> squares = volcanoCard.getSquares();
                StringBuilder creaturesStringBuilder = new StringBuilder(); // to ensure that the creatures are separated properly with ","
                for (int i = 0; i < squares.size(); i++) {
                    creaturesStringBuilder.append(squares.get(i).getCreature().getName());
                    if (i < squares.size() - 1) {
                        creaturesStringBuilder.append(", ");
                    }
                }
                bufferedWriter.write(creaturesStringBuilder.toString());
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
