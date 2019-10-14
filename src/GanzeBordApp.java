import domain.Ganzebord;
import domain.Player;

import java.util.List;
import java.util.Scanner;

public class GanzeBordApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hoeveel spelers zijn er?");
        int aantalSpelers = Integer.parseInt(input.nextLine());
        Ganzebord ganzebord = new Ganzebord(aantalSpelers);
        List<Player> spelersLijst = Player.getPlayerList();
        boolean verderGaan = true;
        while (verderGaan) {
            for (Player player : spelersLijst) {
                verderGaan = ganzebord.beurt(player);
            }
        }
    }
}
