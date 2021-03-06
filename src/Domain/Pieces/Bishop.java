package Domain.Pieces;



import Domain.Cell;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.min;

public class Bishop extends Piece {
    public Bishop(boolean color){
        super.color = color;
        if (color) super.name = "B";
        else super.name = "b";
    }



    public boolean getColor() {
        return super.color;
    }



    public boolean correct_movement(Cell[][] t, Cell origen, Cell destino){
        int i_destino = destino.getI();
        int j_destino = destino.getJ();
        int i_origen = origen.getI();
        int j_origen = origen.getJ();
        boolean movimiento_ok = Math.abs(i_destino - i_origen) == Math.abs(j_destino - j_origen);
        return (movimiento_ok && Nobody_in_trajectory(t,origen,destino)) && (destino.getPiece() == null || destino.getPiece().getColor() != origen.getPiece().getColor());
    }



    public String getName() {
        return super.name;
    }

    public void updateMovement(Cell[][] t, Cell origen) {
        int i = origen.getI();
        int j = origen.getJ();
        //System.out.println(i + " " + j);
        super.position = origen;

        List<Cell> resultat = new ArrayList<Cell>();
        int diagonal1i = i-min(i,j);
        int diagonal1j = j-min(i,j);
        while (diagonal1i < 8 && diagonal1j<8){
            if (i != diagonal1i && diagonal1j != j && Nobody_in_trajectory(t,origen,t[diagonal1i][diagonal1j])) {
                //System.out.println(i + " " + j + "a");
                //System.out.println(diagonal1i + " " + diagonal1j + "b");
                resultat.add(t[diagonal1i][diagonal1j]);
                //Problem.print_list_of_movements(resultat, "Bis");
            }
            ++diagonal1i;
            ++diagonal1j;
        }
        diagonal1i = i-min(i,7-j);
        diagonal1j = j+min(i,7-j);
        while (diagonal1i <8 && diagonal1j >= 0 && diagonal1i >= 0 && diagonal1j <8){
            if (i != diagonal1i && j != diagonal1j && Nobody_in_trajectory(t,origen,t[diagonal1i][diagonal1j])) {
                //System.out.println(i + " " + j + "a");
                //System.out.println(diagonal1i + " " + diagonal1j + "b");
                resultat.add(t[diagonal1i][diagonal1j]);
                //Problem.print_list_of_movements(resultat, "Bis");
            }
            ++diagonal1i;
            --diagonal1j;
        }
        super.Movement = resultat;
    }
}
