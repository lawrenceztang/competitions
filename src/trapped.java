import java.util.*;
import java.io.*;

public class trapped {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("trapped.in"));
        int n = in.nextInt();
        int b = in.nextInt();

        int[][] hayBales = new int[n][];
        for(int i = 0; i < n; i++) {
            hayBales[i] = new int[2];
            hayBales[i][0] = in.nextInt();
            hayBales[i][1] = in.nextInt();
        }

        Arrays.sort(hayBales, new compareLocation());

//        for(int i = )



        PrintWriter out = new PrintWriter(new File("trapped.out"));
        out.println(0);
        out.close();
    }

    static class compareLocation implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[0] - o2[0];
        }
    }
}