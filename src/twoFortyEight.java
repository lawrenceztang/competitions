import java.util.*;
import java.io.*;

public class twoFortyEight {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("248.in"));
        int n = in.nextInt();
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            dp[i][i] = in.nextInt();
        }
        


        PrintWriter out = new PrintWriter(new File("248.out"));
        out.println(sum);
        out.close();
    }

    static int merge() {

    }
}