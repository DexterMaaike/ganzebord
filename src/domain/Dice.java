package domain;

import java.util.Random;

public class Dice {
    

    public int roll() {
        Random r = new Random();
        int number = 1 + r.nextInt(6);

        // nog even checken
        return number;
    }
}
