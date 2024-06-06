package src.utils;

import src.Creature.Creature;
import src.board.*;
import src.gui.*;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;


public class SaveLoad {
    WindowPanel windowPanel;
    PlayerManager playerManager;

    public SaveLoad(WindowPanel windowPanel) {
        this.windowPanel = windowPanel;
        this.playerManager = PlayerManager.getInstance();
    }

    public void loadGame() {
        // opens up directly with a "SaveGames" directory in which the user can select a file to laod up
        String userHome = System.getProperty("user.home");
        JFileChooser fileChooser = new JFileChooser(userHome + File.separator + "SavedGames");
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(selectedFile))) {
                int playerAmount = Integer.parseInt(bufferedReader.readLine());
                int volcanoCardAmount = Integer.parseInt(bufferedReader.readLine());
                int volcanoCardSquaresAmount = Integer.parseInt(bufferedReader.readLine());
                int playerTurn = Integer.parseInt(bufferedReader.readLine());

                BoardArray boardArray = BoardArray.getInstance();
                boardArray.setVolcanoCardCount(volcanoCardAmount);
                boardArray.setSquaresPerVC(volcanoCardSquaresAmount);
                boardArray.addVolcanoCards(volcanoCardAmount, volcanoCardSquaresAmount);
                boardArray.addPosition();

                WindowPanel.resetInstance(playerAmount, volcanoCardAmount, volcanoCardSquaresAmount);
                windowPanel = WindowPanel.getInstance();

                playerManager.setPlayerTurn(playerTurn);
                playerManager.setPlayerCount(playerAmount);

                for (int i = 0; i < playerAmount; i++) {
                    String[] position = bufferedReader.readLine().split(",");
                    int x = Integer.parseInt(position[0]);
                    int y = Integer.parseInt(position[1]);
                    DragonTokenPanel dragonTokenPanel = windowPanel.getDragonTokenPanel(i);
                    dragonTokenPanel.moveDragonToken(x, y);
                }

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

                for (VolcanoCard volcanoCard : volcanoCards) {
                    ArrayList<Square> squares = volcanoCard.getSquares();
                    String[] creatureNames = bufferedReader.readLine().split(", ");
                    for (int i = 0; i < squares.size(); i++) {
                        Creature creature = Creature.stringToCreature(creatureNames[i]);
                        squares.get(i).setCreature(creature);
                    }
                    for (SquarePanel squarePanel : squarePanels) {
                        squarePanel.updateCreatureImage();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(windowPanel, "Failed to load game", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void saveGame() {
        Random rand = new Random();
        int uniqueID = rand.nextInt(999999);
        // creates a new directory called SavedGames if there isn't one already and places all the saved files in there which creates ease of use
        String userHome = System.getProperty("user.home");
        String directoryPath = userHome + File.separator + "SavedGames";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String fileName = directoryPath + File.separator + "savedGame_" + uniqueID + ".txt";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write("" + playerManager.getPlayerCount());
            bufferedWriter.newLine();
            bufferedWriter.write("" + windowPanel.getVolcanoCardCount());
            bufferedWriter.newLine();
            bufferedWriter.write("" + windowPanel.getSquaresPerVC());
            bufferedWriter.newLine();
            bufferedWriter.write("" + playerManager.getPlayerTurn());
            bufferedWriter.newLine();

            ArrayList<DragonTokenPanel> dragonTokenPanels = windowPanel.getDragonTokenPanels();
            for (DragonTokenPanel dragonTokenPanel : dragonTokenPanels) {
                bufferedWriter.write(dragonTokenPanel.getX() + "," + dragonTokenPanel.getY());
                bufferedWriter.newLine();
            }

            ArrayList<CavePanel> cavePanels = windowPanel.getCavePanels();
            for (CavePanel cavePanel : cavePanels) {
                Cave cave = cavePanel.getCave();
                bufferedWriter.write(cave.getCreatureType().getName());
                bufferedWriter.newLine();
                bufferedWriter.write(cave.getCavePosition() + "," + cavePanel.getX() + "," + cavePanel.getY());
                bufferedWriter.newLine();
            }

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

            ArrayList<VolcanoCard> volcanoCards = windowPanel.getVolcanoCards();
            for (VolcanoCard volcanoCard : volcanoCards) {
                bufferedWriter.write("" + volcanoCard.getStartPosition());
                bufferedWriter.newLine();
            }

            ArrayList<SquarePanel> squarePanels = windowPanel.getSquarePanels();
            for (SquarePanel squarePanel : squarePanels) {
                bufferedWriter.write(squarePanel.getXCoordinate() + "," + squarePanel.getYCoordinate());
                bufferedWriter.newLine();
            }

            for (VolcanoCard volcanoCard : volcanoCards) {
                ArrayList<Square> squares = volcanoCard.getSquares();
                StringBuilder creaturesStringBuilder = new StringBuilder();
                for (int i = 0; i < squares.size(); i++) {
                    creaturesStringBuilder.append(squares.get(i).getCreature().getName());
                    if (i < squares.size() - 1) {
                        creaturesStringBuilder.append(", ");
                    }
                }
                bufferedWriter.write(creaturesStringBuilder.toString());
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WindowPanel getWindowPanel() {
        return windowPanel;
    }
}
