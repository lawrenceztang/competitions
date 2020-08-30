import java.util.*;
import java.io.*;
import java.math.*;

public class cowjump2 {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("cowjump.in"));
        int n = in.nextInt();
        PriorityQueue<double[]> startCoordQueue = new PriorityQueue<>(new StartCoordComparator());
        for(int i = 0; i < n; i++) {
            long in1 = in.nextLong();
            long in2 = in.nextLong();
            long in3 = in.nextLong();
            long in4 = in.nextLong();
            if(in3 < in1) {
                long temp = in3;
                in3 = in1;
                in1 = temp;
                temp = in4;
                in4 = in2;
                in2 = temp;
            }
            startCoordQueue.add(new double[]{(double) in1, (double) in2, (double) in3, (double) in4, (double) i});
        }

        double[] lineComparedTo;
        PriorityQueue<double[]> endCoordQueue = new PriorityQueue<>(new EndCoordComparator());
        int[] numIntersections = new int[n];
        while(startCoordQueue.size() > 0) {
            if(endCoordQueue.size() > 0) {
                lineComparedTo = endCoordQueue.poll();
            }
            else {
                lineComparedTo = startCoordQueue.poll();
            }
            while(startCoordQueue.size() > 0 && startCoordQueue.peek()[0] < lineComparedTo[2]) {
                endCoordQueue.add(startCoordQueue.poll());
            }
            while(endCoordQueue.size() > 0 && endCoordQueue.peek()[2] < lineComparedTo[0]) {
                endCoordQueue.poll();
            }

            Object[] arr = endCoordQueue.toArray();
            for(int i = 0; i < arr.length; i++) {
                if(findIntersection(lineComparedTo, (double[]) arr[i])) {
                    numIntersections[(int)lineComparedTo[4]]++;
                    numIntersections[(int)((double[]) arr[i])[4]]++;
                }
            }
        }

        int max = -1;
        for(int i = 0; i < numIntersections.length; i++) {
            max = Math.max(numIntersections[i], max);
        }
        int index = -1;
        for(int i = 0; i < numIntersections.length; i++) {
            if(numIntersections[i] == max) {
                index = i;
                break;
            }
        }

        PrintWriter out = new PrintWriter(new File("cowjump.out"));
        out.println(index + 1);
        out.close();
    }

    static class EndCoordComparator implements Comparator<double[]> {
        @Override
        public int compare(double[] o1, double[] o2) {
            if(o1[2] - o2[2] > 0) {
                return 1;
            }
            return -1;
        }
    }

    static class StartCoordComparator implements Comparator<double[]> {
        @Override
        public int compare(double[] o1, double[] o2) {
            if(o1[0] - o2[0] > 0) {
                return 1;
            }
            return -1;
        }
    }

    static boolean findIntersection(double[] in1, double[] in2) {
        double slope1 = findSlope(in1);
        double intercept1 = findYIntercept(in1, slope1);
        double slope2 = findSlope(in2);
        double intercept2 = findYIntercept(in2, slope2);

        double intersection = (intercept2 - intercept1) / (slope1 - slope2);
        if(in1[2] - in1[0] == 0 && in2[2] - in2[0] == 0) {
            if((in1[0] < Math.max(in2[0], in2[2]) && in1[0] > Math.min(in2[0], in2[2])) || (in1[2] < Math.max(in2[0], in2[2]) && in1[2] > Math.min(in2[0], in2[2]))) {
                return true;
            }
            else {
                return false;
            }
        }
        if(in1[2] - in1[0] == 0) {
            if(slope2 * in1[2] + intercept2 > Math.min(in1[1], in1[3]) && slope2 * in1[2] + intercept2 < Math.max(in1[1], in1[3])) {
                return true;
            }
            else {
                return false;
            }
        }
        if(in2[2] - in2[0] == 0) {
            if(slope1 * in2[2] + intercept1 > Math.min(in2[1], in2[3]) && slope1 * in2[2] + intercept1 < Math.max(in2[1], in2[3])) {
                return true;
            }
            else {
                return false;
            }
        }
        if(intersection < in1[0] || intersection < in2[0] || intersection > in1[2] || intersection >in2[2]) {
            return false;
        }
        else {
            return true;
        }

    }

    static double findSlope(double[] in) {
        if(in[2] - in[0] == 0) {
            return 9999999999999d;
        }
        return (in[3] - in[1]) / (in[2] - in[0]);
    }

    static double findYIntercept(double[] in, double slope) {
        return in[1] - slope * in[0];
    }


}