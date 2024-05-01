package game.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Shuffle {
    private final String[] stringArray;

    public Shuffle(String[] stringArray) {
        this.stringArray = stringArray;
    }
    public void shuffleDragon() {
        List<String> list = Arrays.asList(this.stringArray);
        Collections.shuffle(list);
        list.toArray(this.stringArray);
    }
}
