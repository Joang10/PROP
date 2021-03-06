package Domain;



import java.io.*;
import java.time.Duration;
import java.time.Instant;

import java.util.*;
import java.lang.String;
import Domain.Pieces.Piece;



public class Problem {

    public static boolean iscorrectFen(String FEN) {
        int K, k, Q, q, R, r, B, b, N, n, P, p, index;
        K = k = Q = q = R = r = B = b = N = n = P = p = index = 0;
        while (index < FEN.length() && FEN.charAt(index) == ' ') { //erase the spaces at the front
            ++index;
        }
        int total_rows = 0;
        while (index < FEN.length() && FEN.charAt(index) != ' ') {
            int row = 0;
            while (index < FEN.length() && FEN.charAt(index) != '/' && FEN.charAt(index) != ' ') {
                if (FEN.charAt(index) == 'K') {
                    ++K;
                    ++row;
                }
                else if (FEN.charAt(index) == 'k') {
                    ++k;
                    ++row;
                }
                else if (FEN.charAt(index) == 'Q') {
                    ++Q;
                    ++row;
                }
                else if (FEN.charAt(index) == 'q') {
                    ++q;
                    ++row;
                }
                else if (FEN.charAt(index) == 'R') {
                    ++R;
                    ++row;
                }
                else if (FEN.charAt(index) == 'r') {
                    ++r;
                    ++row;
                }
                else if (FEN.charAt(index) == 'B') {
                    ++B;
                    ++row;
                }
                else if (FEN.charAt(index) == 'b') {
                    ++b;
                    ++row;
                }
                else if (FEN.charAt(index) == 'N') {
                    ++N;
                    ++row;
                }
                else if (FEN.charAt(index) == 'n') {
                    ++n;
                    ++row;
                }
                else if (FEN.charAt(index) == 'P') {
                    ++P;
                    ++row;
                }
                else if (FEN.charAt(index) == 'p') {
                    ++p;
                    ++row;
                }
                else if (Character.getNumericValue(FEN.charAt(index)) > 0 && Character.getNumericValue(FEN.charAt(index)) <= 8) {
                    row += Character.getNumericValue(FEN.charAt(index));
                }
                else return false;
                if (K > 1 || k > 1 || Q > 1 || q > 1 || R > 2 || r > 2 || B > 2 || b > 2 || N > 2 || n > 2 || P > 8 || p > 8) return false;
                ++index;
            }
            ++total_rows;
            if (row != 8) return false;
            if (index < FEN.length() && FEN.charAt(index) == ' ' && total_rows != 8) return false;
            if (index < FEN.length() && FEN.charAt(index) == '/') ++index;
        }
        while (index < FEN.length()) {
            if (FEN.charAt(index) != ' ') return false;
            ++index;
        }
        return (total_rows == 8 && k == 1 && K == 1);
    }


    static public String ConvertInputtoFEN(String input){
        String FEN = "";
        int w = 0;
        while (input.charAt(w) == ' ') ++w;
        while (w < input.length() && input.charAt(w) != ' '){
            FEN = FEN + input.charAt(w);
            ++w;
        }
        return FEN;
    }

    static public boolean ConvertInputtoplayer_who_start(String input){
        int w = 0;
        while (input.charAt(w) == ' ') ++w;
        while (input.charAt(w) != ' ') ++w;
        while (input.charAt(w) == ' ') ++w;
        return input.charAt(w) == 'w';
    }

    static public boolean ConvertInputtoplayer_who_has_to_win(String input){
        int w = 0;
        while (input.charAt(w) == ' ') ++w;
        while (input.charAt(w) != ' ') ++w;
        while (input.charAt(w) == ' ') ++w;
        ++w;
        while (input.charAt(w) == ' ') ++w;
        return input.charAt(w) == 'w';
    }

    static public int ConvertInputtonumber_of_play(String s){
        int w = 0;
        String input = String.valueOf(s);
        while (input.charAt(w) == ' ') ++w;
        while (input.charAt(w) != ' ') ++w;
        while (input.charAt(w) == ' ') ++w;
        ++w;
        while (input.charAt(w) == ' ') ++w;
        ++w;
        while (input.charAt(w) == ' ') ++w;
        StringBuilder str = new StringBuilder(s);
        str.delete(0,w);
        input = str.toString();
        return Integer.parseInt(input);
    }
    static public String convertParameterstoFEN(String FEN, boolean player_who_start, boolean player_who_has_to_win, int number_of_play){
        String player_who_startString;
        String player_who_has_to_winString;
        if (player_who_start) player_who_startString = "w";
        else player_who_startString = "b";
        if (player_who_has_to_win) player_who_has_to_winString = "w";
        else player_who_has_to_winString = "b";
        return FEN + " " + player_who_startString + " " + player_who_has_to_winString + " " + number_of_play;
    }

    static public void print_list_of_pieces(List<Piece> p) {
        for (int i = 0; i < p.size(); ++i) System.out.println(p.get(i).getName() + ": (" + p.get(i).getPosition().getI() + ", " + p.get(i).getPosition().getJ() + ")");
        System.out.println();
    }

    static public void print_list_of_movements(List<Cell> m, String name) {
        for (int i = 0; i < m.size(); ++i) {
            if (i == 0) System.out.print(name + ": (" + m.get(0).getI() + ", " + m.get(0).getJ() + ")");
            else {
                System.out.print(", (" + m.get(i).getI() + ", " + m.get(i).getJ() + ")");
            }
        }
        System.out.println();
    }

    static public boolean isCorrectProblem(String FEN,boolean player_who_start,boolean player_who_has_to_win,int number_of_play) {
        FEN = ConvertInputtoFEN(FEN);
        Table t = new Table(FEN);
        return achieve_the_goal(t,player_who_start,number_of_play,player_who_has_to_win);
    }

    static public boolean achieve_the_goal(Table t, boolean player_turn, int number_of_play, boolean player_who_has_to_win) {
        List<Piece> pieces2 = t.getPieces(player_turn);
        pieces2 = t.getPieces(player_turn);
        //print_list_of_pieces(pieces2);
        if (number_of_play == 0) return t.checkmate(player_who_has_to_win, true) && t.checkmate(player_who_has_to_win, false);
        if (number_of_play > 0) {
            if (t.checkmate(player_who_has_to_win, player_turn)) {
                return true;
            }
            //print_list_of_pieces(pieces2);
            if (t.checkmate(!player_who_has_to_win, player_turn)) return false;
            //print_list_of_pieces(pieces2);
            if (player_who_has_to_win == player_turn){
                List<Piece> pieces = t.getPieces(player_turn);
                //print_list_of_pieces(pieces);
                for (int i = 0; i<pieces.size(); ++i){
                    List<Cell> movement = pieces.get(i).getMovement();
                    int i_origen = pieces.get(i).getPosition().getI();
                    int j_origen = pieces.get(i).getPosition().getJ();
                    for (int j = 0; j<movement.size(); ++j){
                        int i_destino = movement.get(j).getI();
                        int j_destino = movement.get(j).getJ();
                        Table aux = new Table();
                        aux.setTable(t.getTable());
                        if (aux.CorrectMove(i_origen, j_origen, i_destino, j_destino)){
                            aux.MovePiece(i_origen, j_origen, i_destino, j_destino);
                            if (achieve_the_goal(aux,!player_turn,number_of_play-1,player_who_has_to_win)) return true;
                        }
                    }
                }
                return false;
            }else{
                List<Piece> pieces = t.getPieces(player_turn);
                for (int i = 0; i<pieces.size(); ++i){
                    List<Cell> movement = pieces.get(i).getMovement();
                    int i_origen = pieces.get(i).getPosition().getI();
                    int j_origen = pieces.get(i).getPosition().getJ();
                    for (int j = 0; j<movement.size(); ++j){
                        int i_destino = movement.get(j).getI();
                        int j_destino = movement.get(j).getJ();
                        Table aux = new Table();
                        aux.setTable(t.getTable());
                        if (aux.CorrectMove(i_origen, j_origen, i_destino, j_destino)){
                            aux.MovePiece(i_origen, j_origen, i_destino, j_destino);
                            if (!achieve_the_goal(aux,!player_turn,number_of_play-1,player_who_has_to_win)) return false;
                        }
                    }
                }
                return true;
            }

        }
        return false;
        /*
        if (number_of_play == 0){
            return t.checkmate(player_who_has_to_win, player_turn);
        }
         */
    }

    public static String getOS() {
        String os = System.getProperty("os.name").toLowerCase();
        return os;
    }

    public static List<String> load_problem_fromBD(String name_of_file) throws IOException {
        String s = getOS();
        String path;
        if (s.charAt(0) == 'w') path = "..\\FONTS\\src\\Data\\";
        else path = "../FONTS/src/Data/";
        File file = new File(path + name_of_file);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        List <String> result = new ArrayList<String >();
        String linea;
        while ((linea = br.readLine()) != null) {
            result.add(linea);
        }
        fr.close();
        return result;
    }

    public static List<String> marks_of_problem(String FEN) throws IOException {
        String s = getOS();
        String path;
        if (s.charAt(0) == 'w') path = "..\\FONTS\\src\\Data\\";
        else path = "../FONTS/src/Data/";
        File file = new File(path + create_file_name(FEN));
        List<String> result = new ArrayList<String>();
        if (file.exists()) {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                result.add(linea);
            }
            fr.close();
        }
        return result;
    }



    private static long getdurationofplayer(String line){
        int i = 0;
        while (line.charAt(i) != ' ') ++i;
        while (line.charAt(i) == ' ') ++i;
        StringBuilder str = new StringBuilder(line);
        str.delete(0,i);
        String input = str.toString();
        return Long.valueOf(input);
    }

    private static String create_file_name(String FEN){
        return FEN.replaceAll("/", "-");
    }

    public static void introduce_user_result(String FEN, String username, Instant now, Instant end_of_game) throws IOException {
       long duration_of_game = Duration.between(now, end_of_game).toMillis();
       duration_of_game = duration_of_game/1000;
       List <String> output = marks_of_problem(FEN);
       String s = getOS();
       String path;
       if (s.charAt(0) == 'w') path = "..\\FONTS\\src\\Data\\";
       else path = "../FONTS/src/Data/";
       FileWriter file = new FileWriter(path + create_file_name(FEN));
       PrintWriter pw  = new PrintWriter (file);
       boolean added = false;
       for (int i = 0; i<output.size(); ++i){
            if(getdurationofplayer(output.get(i))  > duration_of_game && !added){
                pw.println(username + " " + duration_of_game);
                pw.println(output.get(i));
                added = true;
            }else pw.println(output.get(i));
       }
       if (!added) pw.println(username + " " + duration_of_game);
       pw.close();
    }


    public static void introduce_problem_toBD(String FEN,boolean player_who_start,boolean player_who_has_to_win,int number_of_play) throws IOException{
        List <String> problems = load_problem_fromBD("BD_USERPROBLEMS");
        String s = getOS();
        String path;
        if (s.charAt(0) == 'w') path = "..\\FONTS\\src\\Data\\";
        else path = "../FONTS/src/Data/";
        FileWriter file = new FileWriter(path +"BD_USERPROBLEMS");
        PrintWriter pw  = new PrintWriter (file);
        String FEN_to_introduce = convertParameterstoFEN(FEN,player_who_start,player_who_has_to_win,number_of_play);
        for (int i = 0; i<problems.size(); ++i){
            pw.println(problems.get(i));
        }
        pw.println(FEN_to_introduce);
        pw.close();
    }
    public static void delete_problem_fromBD(String FEN) throws IOException {
        List <String> problems = load_problem_fromBD("BD_USERPROBLEMS");
        String s = getOS();
        String path;
        if (s.charAt(0) == 'w') path = "..\\FONTS\\src\\Data\\";
        else path = "../FONTS/src/Data/";
        FileWriter file = new FileWriter(path + "BD_USERPROBLEMS");
        PrintWriter pw  = new PrintWriter (file);
        for (int i = 0; i<problems.size(); ++i){
            if (!problems.get(i).equals(FEN)){
                pw.println(problems.get(i));
            }
        }
        pw.close();
    }

    public static void modificate_problem_fromBD(String old_FEN, String new_FEN) throws IOException {
        List <String> problems = load_problem_fromBD("BD_USERPROBLEMS");
        String s = getOS();
        String path;
        if (s.charAt(0) == 'w') path = "..\\FONTS\\src\\Data\\";
        else path = "../FONTS/src/Data/";
        FileWriter file = new FileWriter(path + "BD_USERPROBLEMS");
        PrintWriter pw  = new PrintWriter (file);
        for (int i = 0; i<problems.size(); ++i){
            if (!problems.get(i).equals(old_FEN)){
                pw.println(problems.get(i));
            }else pw.println(new_FEN);
        }
        pw.close();
    }

}
