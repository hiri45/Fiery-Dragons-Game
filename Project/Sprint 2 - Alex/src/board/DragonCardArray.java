package src.board;


import java.util.ArrayList;

public class DragonCardArray {
    ArrayList<DragonCardArray> dragonCardArray;
    public static DragonCardArray instance;

    private static final int dragonCardCount = 16;
    public static DragonCardArray getInstance(){
        if(instance == null){
            instance = new DragonCardArray();
        }
        return instance;
    }

    public void createDragonCards(){
        for(int i = 0; i < dragonCardCount; i++){
//            dragonCardArray.add(new DragonCard());
        }
    }
}
