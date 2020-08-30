import java.awt.*;
import java.util.*;
import java.io.*;

public class mooyomooyo {

    static ArrayList<ArrayList<Integer>> board;

    public static void main(String[] args) throws Exception {

        PrintWriter out = new PrintWriter(new File("mooyomooyo.out"));
        //read input
        Scanner in = new Scanner(new File("mooyomooyo.in"));
        int boardHeight = in.nextInt();
        int numForBreak = in.nextInt();
        board = new ArrayList<>();
        filled = new ArrayList<>();
        for (int i = 0; i < boardHeight; i++) {
            filled.add(new ArrayList<>(10));
            board.add(new ArrayList<>(10));
            String temp = in.next();
            for (int j = 0; j < 10; j++) {
                filled.get(i).add(false);
                board.get(i).add(temp.charAt(j) - '0');
            }
        }
        //board: boardHeight * width

        while (true) {
            //reset filled
            filled = new ArrayList<>();
            for (int i = 0; i < boardHeight; i++) {
                filled.add(new ArrayList<>(10));
                for (int j = 0; j < 10; j++) {
                    filled.get(i).add(false);
                }
            }

            boolean broken = false;
            //search for chunks over number
            for (int x = 0; x < board.size(); x++) {
                for (int y = 0; y < board.get(x).size(); y++) {
                    if (board.get(x).get(y) != 0) {
                        Object[] coordsRemove = floodfill(x, y, board.get(x).get(y)).toArray();

                        //if big enough to break, break
                        if (coordsRemove.length >= numForBreak) {
                            broken = true;
                            for (int i = 0; i < coordsRemove.length; i++) {
                                board.get(((Point) coordsRemove[i]).x).set(((Point) coordsRemove[i]).y, 0);
                            }
                        }

                    }
                }
            }


            if (!broken) {
                break;
            }

            //gravity: pieces fall

            //each column
            for (int i = 0; i < 10; i++) {

                while (notFinished(i)) {
                    //search for empty squares starting from bottom
                    for (int j = boardHeight - 1; j >= 0; j--) {
                        if (board.get(j).get(i) == 0) {
                            //all pieces in column fall
                            for (int q = j; q >= 0; q--) {
                                //set to piece above
                                if (q - 1 < 0) {
                                    board.get(q).set(i, 0);
                                } else {
                                    board.get(q).set(i, board.get(q - 1).get(i));
                                }
                            }
                        }
                    }
                }


            }
        }


        for (int x = 0; x < board.size(); x++) {
            for (int y = 0; y < board.get(x).size(); y++) {
                out.print(board.get(x).get(y));
            }
            out.println();
        }
        out.close();
    }

    static public boolean notFinished(int column) {
        boolean seenEmpty = false;
        for (int i = board.size() - 1; i >= 0; i--) {
            if (board.get(i).get(column) == 0) {
                seenEmpty = true;
            } else if (board.get(i).get(column) != 0 && seenEmpty) {
                return true;
            }

        }
        return false;
    }


    static ArrayList<ArrayList<Boolean>> filled;

    static Set<Point> floodfill(int x, int y, int color) {
        HashSet<Point> out = new HashSet<Point>();

        if (x >= 0 && x < board.size() && y >= 0 && y < 10 && board.get(x).get(y) == color && !filled.get(x).get(y)) {
            filled.get(x).set(y, true);
            out.addAll(floodfill(x + 1, y, color));
            out.addAll(floodfill(x - 1, y, color));
            out.addAll(floodfill(x, y - 1, color));
            out.addAll(floodfill(x, y + 1, color));
            out.add(new Point(x, y));
        } else {
        }

        return out;

    }
}