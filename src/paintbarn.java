import java.util.*;
import java.io.*;

public class paintbarn {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("paintbarn.in"));
        int n = in.nextInt();
        int k = in.nextInt();
        int[][] layersCovered = new int[1000][1000];
        for(int i = 0; i < n; i++) {
            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();
            for(int x = x1; x < x2; x++) {
                for(int y = y1; y < y2; y++) {
                    layersCovered[x][y]++;
                }
            }
        }

        int sum = 0;
        for(int x = 0; x < layersCovered.length; x++) {
            for (int y = 0; y < layersCovered[x].length; y++) {
                if(layersCovered[x][y] == k) {
                    sum++;
                }
            }
        }


        PrintWriter out = new PrintWriter(new File("paintbarn.out"));
        out.println(sum);
        out.close();
    }
}