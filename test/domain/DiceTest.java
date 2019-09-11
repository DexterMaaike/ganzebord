package domain;

import org.junit.Assert;
import org.junit.Test;

public class DiceTest {

    Dice dice = new Dice();

    @Test
    public void rollTest() {
        int number = dice.roll();
        Assert.assertTrue(number > 0 && number < 7);
    }
}
