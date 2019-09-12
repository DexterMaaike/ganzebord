package domain;

import exceptions.LostPlayerException;

import java.util.Scanner;

public class Ganzebord {
    private Dice dice = new Dice();
    private Scanner input = new Scanner(System.in);
    private int numberOfPlayers;

    public Ganzebord(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        for (int i = 0; i < this.numberOfPlayers; i++) {
            int nummer = i + 1;
            System.out.println("Speler " + nummer + " , voer naam in:");
            new Player(input.nextLine());
        }
    }

    public void beurt(Player player) {
        if (this.numberOfPlayers > 1) {
            if (player.getBeurtOverslaan()) {
                System.out.println(player.getNaam() + " slaat deze beurt over");
                player.setAantalBeurtenOvergeslagen(player.getAantalBeurtenOvergeslagen() - 1);
                if (player.getAantalBeurtenOvergeslagen() == 0) {
                    player.setBeurtOverslaan(false);
                }
            } else {
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
            }
        } else {
            if (this.numberOfPlayers == 1) {
                System.out.println(player.getNaam() + " heeft gewonnen!");
                System.exit(0);
            } else {
                throw new LostPlayerException("Something went wrong. There are no more players in the game.");
            }
        }
    }

    public void plaatsActie(int plek, int number, Player player) {
        if (plek > 63) {
            int overschot = plek - 63;
            System.out.println("Je bent over de finish. Je moet weer " + overschot + " plaatsen terug.");
            plek = 63 - overschot;
        }

        switch (plek) {
            case 0:
                System.out.println("Je staat op start");
                break;
            case 6:
                System.out.println("Je kwam op 6. Hier is een brug. Je gaat verder naar 12.");
                player.setPlaats(12);
                break;
            case 19:
                player.setBeurtOverslaan(true);
                player.setAantalBeurtenOvergeslagen(1);
                System.out.println("Je bent in de herberg. Je moet een beurt overslaan.");
                break;
            case 31:
                player.setBeurtOverslaan(true);
                System.out.println("Je zit in de put. Je moet hier blijven tot iemand je komt redden");
                for (Player speler : Player.getPlayerList()) {
                    if (!(speler.equals(player))) {
                        if (speler.getPlaats() == 31) {
                            speler.setBeurtOverslaan(false);
                            System.out.println(speler.getNaam() + " is weer uit de put!");
                        }
                    }
                }
                break;
            case 42:
                player.setPlaats(39);
                System.out.println("Je bent in het doolhof beland. Je gaat terug naar plaats 39.");
                break;
            case 58:
                player.setPlaats(0);
                System.out.println("Je bent dood en moet terug naar start!");
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
                System.out.println("Je kwam op " + plek + ". Je mag nog een keer " + number + " plaatsen vooruit.");
                plek += number;
                player.setPlaats(plek);
                plaatsActie(plek, number, player);
                break;
            default:
                System.out.println(player.getNaam() + ", je staat op plaats " + plek + ", Niks aan de hand.");
                player.setPlaats(plek);
                break;

        }
        if (!input.nextLine().equals("")) {
            System.exit(0);
        }
    }
}
