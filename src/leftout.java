import java.util.*;
import java.io.*;


public class leftout {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("leftout.in"));
        int n = in.nextInt();
        boolean[][] cowDirections = new boolean[n][];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            cowDirections[i] = new boolean[n];
            String str = in.next();
            for (int j = 0; j < n; j++) {
                cowDirections[i][j] = (str.charAt(j) == 'R');
            }
        }


        outer:
        while (true) {
            for (int i = 0; i < n; i++) {
                if (countFalsesColumn(cowDirections, i) > n / 2) {
                    swapColumn(cowDirections, i);
                    continue outer;
                }
                if (countFalsesRow(cowDirections, i) > n / 2) {
                    swapRow(cowDirections, i);
                    continue outer;
                }
            }
            break;
        }

        ArrayList<int[]> notTrues = new ArrayList<int[]>();
        for (int i = 0; i < cowDirections.length; i++) {
            for (int j = 0; j < cowDirections.length; j++) {
                if (!cowDirections[i][j]) {
                    notTrues.add(new int[]{i, j});
                }
            }
        }

        ArrayList<int[]> leastRows = new ArrayList<>();
        int least = Integer.MAX_VALUE;
        for (int i = 0; i < notTrues.size(); i++) {
            if (notTrues.get(i)[0] < least) {
                least = notTrues.get(i)[0];
            }
        }
        for (int i = 0; i < notTrues.size(); i++) {
            if (notTrues.get(i)[0] == least) {
                leastRows.add(notTrues.get(i));
            }
        }
        int[] sol = new int[]{least, Integer.MAX_VALUE};
        for (int i = 0; i < leastRows.size(); i++) {
            if (leastRows.get(i)[1] < sol[1]) {
                sol[1] = leastRows.get(i)[1];
            }
        }

        PrintWriter out = new PrintWriter(new File("leftout.out"));
        out.print((sol[1] + 1) + " " + (sol[1] + 1));
        out.close();
    }

    static int countFalsesRow(boolean[][] cowDirections, int row) {
        int falses = 0;
        for (int i = 0; i < cowDirections.length; i++) {
            if (!cowDirections[row][i]) {
                falses++;
            }
        }
        return falses;
    }

    static int countFalsesColumn(boolean[][] cowDirections, int column) {
        int falses = 0;
        for (int i = 0; i < cowDirections.length; i++) {
            if (!cowDirections[i][column]) {
                falses++;
            }
        }
        return falses;
    }

    static void swapColumn(boolean[][] cowDirections, int column) {
        for (int i = 0; i < cowDirections.length; i++) {
            cowDirections[i][column] = !cowDirections[i][column];
        }
    }

    static void swapRow(boolean[][] cowDirections, int row) {
        for (int i = 0; i < cowDirections.length; i++) {
            cowDirections[row][i] = !cowDirections[row][i];
        }
    }
}