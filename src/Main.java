import Data.Cell;
import Data.Game;
import Data.Table;
import Interface.MainInterface;
import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;
import static Data.Problem.isCorrectProblem;
import static Data.Problem.iscorrectFen;
import java.awt.*;
import java.awt.event.*;

public class Main {
    /*public static void main(String args[]) {
        System.out.println("hola");
        MainInterface start = new MainInterface();
    }*/

    public static void main(String[] args) throws CloneNotSupportedException, IOException {
        System.out.println("Select your option by writting the number");
        System.out.println("1. Start a new game");
        System.out.println("2. See the ranking table");
        System.out.println("3. Quit");
        Scanner sc = new Scanner(System.in);
        int instr = sc.nextInt();
        if (instr == 1) {
            System.out.println("Select your option by writting the number");
            System.out.println("1. Introduce your own FEN");
            System.out.println("2. Select a FEN from the Database");
            instr = sc.nextInt();
            sc.nextLine();
            if (instr == 1) {
                String s = sc.nextLine();
                boolean b = iscorrectFen(s);
                while (!b) {
                    System.out.println("FEN is incorrect, introduce the correct FEN: ");
                    s = sc.nextLine();
                    b = iscorrectFen(s);
                }
                Game g = new Game();
                g.setTable(new Table(s));
                g.getTable().print_table();
                System.out.println("Select your option by writting the number");
                System.out.println("1. Player1 vs Player2");
                System.out.println("2. Player vs CPU(Easy)");
                System.out.println("3. Player vs CPU(Hard)");
                System.out.println("4. CPU1(Easy) vs CPU2(Easy)");
                System.out.println("5. CPU1(Easy) vs CPU2(Hard)");
                System.out.println("6. CPU1(Hard) vs CPU2(Hard)");
                instr = sc.nextInt();
                sc.nextLine();
                if (instr == 1) {
                    System.out.println("Select your option by writting the number");
                    System.out.print("Enter Player1's name: ");
                    String name1 = sc.nextLine();
                    System.out.print("Enter Player2's name: ");
                    String name2 = sc.nextLine();
                    System.out.println("1. " + name1 + " plays the White Team and " + name2 + "plays the Black Team");
                    System.out.println("2. " + name1 + " plays the Black Team and " + name2 + "playes the White Team");
                    instr = sc.nextInt();
                    if (instr == 1) {

                    }
                    else if (instr == 2) {

                    }
                    else {
                        System.out.println("Error, this option is not available");
                        System.exit(1);
                    }
                }
                else if (instr == 2) {

                }
                else if (instr == 3) {

                }
                else if (instr == 4) {

                }
                else if (instr == 5) {

                }
                else if (instr == 6) {

                }
                else {
                    System.out.println("Error, this option is not available");
                    System.exit(1);
                }
            }
            else if (instr == 2) {
                System.out.println("Select your option by writting the number");
                System.out.println("1. Easy Problems");
                System.out.println("2. Hard Problems");

            }
            else {
                System.out.println("Error, this option is not available");
                System.exit(1);
            }
        }
        else if (instr == 2) {
            System.out.println("Feature not available yet");
            System.exit(0);
        }
        else if (instr == 3) {
            System.exit(0);
        }
        else {
            System.out.println("Error, this option is not available");
            System.exit(1);
        }
    }
}
    /*
    public static void main(String[] args) throws CloneNotSupportedException, IOException {
        Game t = new Game();
    }
    */
    /*
         String s = "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w - - 0 1";

    String s = "2QR3r/1q2Pb2/1bpBpp2/p6n/KNpkpN2/1P3P2/3PP3/n3r3 w w 2";
    Table t = testConstructor(s);
    Cell[][] t2 = testgettable(t);
    print_table(t2);
        System.out.println(" ");
                boolean b;


        b = t.MovePiece(2,6,4,7);
        if (b) System.out.println("bieeenn");
        else System.out.println("ostia neeng");
        t2 = testgettable(t);
        print_table(t2);


                System.out.println("prueba2");
                System.out.println("");

                b =  isCorrectProblem(s);
                if (b) System.out.println("bieeenn");
                else System.out.println("ostia neeng");



                }

private static Table testConstructor(String s) throws CloneNotSupportedException{
        return new Table(s);
        }
private static Cell[][] testgettable(Table t){
        return t.getTable();
        }

public static void print_table(Cell[][] table){
        for (int i = 0; i<8; ++i){
        for  (int j = 0; j<8; ++j){
        if (table[i][j].getPiece() == null)System.out.printf(".");
        else System.out.printf(table[i][j].getPiece().getName());

        }
        System.out.println(" ");
        }
        }
        */