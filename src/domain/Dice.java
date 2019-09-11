package domain;

import java.util.Random;

public class Dice {

    public int roll(){
        Random r=new Random();
        int number = r.nextInt(6);
        while(number==0){
            number =r.nextInt(6);
        }
        return number;
    }
}
