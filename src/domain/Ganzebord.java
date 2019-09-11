package domain;

import java.util.Scanner;

public class Ganzebord {
    private Dice dice = new Dice();
    private Player player;
    private Scanner input = new Scanner(System.in);
    private int numberOfPlayers;

    public Ganzebord(int numberOfPlayers) {
        this.numberOfPlayers=numberOfPlayers;
        for (int i = 0; i < this.numberOfPlayers; i++) {
            int nummer = i + 1;
            System.out.println("Speler " + nummer + " , voer naam in:");
            new Player(input.nextLine());
        }
    }

    public void beurt(Player player) {
        if (!player.isDood()&&this.numberOfPlayers>1) {
            int plek = player.getPlaats();
            if (plek == 0) {
                System.out.println(player.getNaam() + ", je staat op start! Gooi je dobbelsteen.");
            } else {
                System.out.println(player.getNaam() + ", je staat op " + plek + ". Gooi je dobbelsteen.");
            }
            if (input.nextLine().equals("")) {
                int number = dice.roll();
                System.out.println("Je hebt " + number + " gegooid.");
                plek += number;
                plaatsActie(plek, number, player);
            } else {
                System.out.println("Je hebt het spel beeindigd.");
                System.exit(0);
            }
        }else{
            if(!player.isDood()&&this.numberOfPlayers==1){
                System.out.println(player.getNaam() + " heeft gewonnen!");
                System.exit(0);
            }
        }
    }

    public void plaatsActie(int plek, int number, Player player) {
        if (plek >= 63) {
            int overschot = plek - 63;
            System.out.println("Je bent over de finish. Je moet weer " + overschot + " plaatsen terug.");
            plek -= overschot;
        }

        switch (plek) {
            case 0:
                System.out.println("Je staat op start");
                break;
            case 23:
                System.out.println(player.getNaam() + " kwam in de gevangenis.");
                player.setDood(true);
                this.numberOfPlayers--;
                break;
            case 63:
                System.out.println(player.getNaam() + " heeft gewonnen!!");
                System.exit(0);
                break;
            case 24:
            case 45:
                player.setPlaats(0);
                System.out.println(player.getNaam() + " gaat terug naar start");
                break;
            case 10:
            case 20:
            case 30:
            case 40:
            case 50:
                System.out.println("Je kwam op " + plek +". Je mag nog een keer " + number + " plaatsen vooruit.");
                plek += number;
                player.setPlaats(plek);
                plaatsActie(plek, number, player);
                break;
            default:
                System.out.println(player.getNaam() + ", je staat op plaats " + plek + ", Niks aan de hand.");
                player.setPlaats(plek);
                break;

        }
        if(!input.nextLine().equals("")){
            System.exit(0);
        }
    }
}
