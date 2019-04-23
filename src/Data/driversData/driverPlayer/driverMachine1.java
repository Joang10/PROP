package Data.driversData.driverPlayer;

import java.util.Scanner;
import Data.Game;
import Data.Player.Machine1;
import Data.Player.Player;

public class driverMachine1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce your FEN: ");
        String s = sc.nextLine();
        Game g = new Game();
        g.prepareTablewithFEN(s);
        Player p1 = new Machine1();
        Player p2 = new Machine1();
        p1.setColor(true);
        p2.setColor(false);
        g.setPlayer1(p1);
        g.setPlayer2(p2);
        g.getTable().print_table();
        p1.move_piece(g);
        g.setPlayer_who_plays(!g.getPlayer_who_plays());
        p2.move_piece(g);
    }
}
