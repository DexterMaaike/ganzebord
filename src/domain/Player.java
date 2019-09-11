package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private static List<Player> playerList=new ArrayList<>();
    private boolean isDood = false;
    private int plaats = 0;
    private String naam;

    public Player(String naam){
        setNaam(naam);
        playerList.add(this);
    }
    public int getPlaats() {
        return plaats;
    }

    public void setPlaats(int plaats) {
        this.plaats = plaats;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public boolean isDood() {
        return isDood;
    }

    public void setDood(boolean dood) {
        isDood = dood;
    }

    public static List<Player> getPlayerList() {
        return playerList;
    }

    public static void setPlayerList(List<Player> playerList) {
        Player.playerList = playerList;
    }
}
