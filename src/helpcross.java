// "Why Did the Cow Cross the Road"
// Bronze February 2017 Problem 1
// http://usaco.org/index.php?page=viewproblem2&cpid=714

import java.util.*;
import java.io.*;

public class helpcross {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("helpcross.in"));
        int c = in.nextInt();
        int n = in.nextInt();
        int[] chickenTimes = new int[c];
        int[][] cowTimeRanges = new int[n][];

        for (int i = 0; i < c; i++) {
            chickenTimes[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            cowTimeRanges[i] = new int[2];
            cowTimeRanges[i][0] = in.nextInt();
            cowTimeRanges[i][1] = in.nextInt();
        }
        Arrays.sort(cowTimeRanges, new StartTimeComparator());
        Arrays.sort(chickenTimes);
        in.close();


        int startTimeIndex = 0;
        PriorityQueue<int[]> cowsInRange = new PriorityQueue<int[]>(new EndTimeComparator());
        int result = 0;
        for (int i = 0; i < chickenTimes.length; i++) {

            while (startTimeIndex < cowTimeRanges.length && cowTimeRanges[startTimeIndex][0] < chickenTimes[i]) {
                cowsInRange.add(cowTimeRanges[startTimeIndex]);
                startTimeIndex++;
            }

            while (cowsInRange.peek() != null && cowsInRange.peek()[1] < chickenTimes[i]) {
                cowsInRange.remove();
            }

            if (cowsInRange.peek() != null) {
                cowsInRange.remove();
                result++;
            }
        }

        PrintWriter out = new PrintWriter(new File("helpcross.out"));
        System.out.println(result);
        out.println(result);
        out.close();
    }

    static class StartTimeComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            return a[0] - b[0];
        }
    }

    static class EndTimeComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            return a[1] - b[1];
        }
    }
}
