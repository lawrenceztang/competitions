import java.util.*;
import java.io.*;
import java.math.*;

public class cowjump {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("cowjump.in"));
        int n = in.nextInt();
        PriorityQueue<BigDecimal[]> startCoordQueue = new PriorityQueue<>(new StartCoordComparator());
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
            startCoordQueue.add(new BigDecimal[]{new BigDecimal(in1), new BigDecimal(in2), new BigDecimal(in3), new BigDecimal(in4), new BigDecimal(i)});
        }

        BigDecimal[] lineComparedTo;
        PriorityQueue<BigDecimal[]> endCoordQueue = new PriorityQueue<>(new EndCoordComparator());
        int[] numIntersections = new int[n];
        while(startCoordQueue.size() > 0) {
            if(endCoordQueue.size() > 0) {
                lineComparedTo = endCoordQueue.poll();
            }
            else {
                lineComparedTo = startCoordQueue.poll();
            }
            while(startCoordQueue.size() > 0 && startCoordQueue.peek()[0].compareTo(lineComparedTo[2]) < 0) {
                endCoordQueue.add(startCoordQueue.poll());
            }
            while(endCoordQueue.size() > 0 && endCoordQueue.peek()[2].compareTo(lineComparedTo[0]) < 0) {
                endCoordQueue.poll();
            }

            Object[] arr = endCoordQueue.toArray();
            for(int i = 0; i < arr.length; i++) {
                if(findIntersection(lineComparedTo, (BigDecimal[]) arr[i])) {
                    numIntersections[lineComparedTo[4].intValue()]++;
                    numIntersections[((BigDecimal[]) arr[i])[4].intValue()]++;
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

    static class EndCoordComparator implements Comparator<BigDecimal[]> {
        @Override
        public int compare(BigDecimal[] o1, BigDecimal[] o2) {
            if(o1[2].subtract(o2[2]).compareTo(BigDecimal.ZERO) > 0) {
                return 1;
            }
            return -1;
        }
    }

    static class StartCoordComparator implements Comparator<BigDecimal[]> {
        @Override
        public int compare(BigDecimal[] o1, BigDecimal[] o2) {
            if(o1[0].subtract(o2[0]).compareTo(BigDecimal.ZERO) > 0) {
                return 1;
            }
            return -1;
        }
    }

    static boolean findIntersection(BigDecimal[] in1, BigDecimal[] in2) {
        BigDecimal slope1 = findSlope(in1);
        BigDecimal intercept1 = findYIntercept(in1, slope1);
        BigDecimal slope2 = findSlope(in2);
        BigDecimal intercept2 = findYIntercept(in2, slope2);

        BigDecimal intersection = (intercept2.subtract(intercept1)).divide(slope1.subtract(slope2), 50, BigDecimal.ROUND_HALF_UP);

        if(in1[2].subtract(in1[0]).compareTo(BigDecimal.ZERO) == 0) {
            if(slope2.multiply(in1[2]).add(intercept2).compareTo(in1[1].min(in1[3])) > 0 && slope2.multiply(in1[2]).add(intercept2).compareTo(in1[1].max(in1[3])) < 0) {
                return true;
            }
            else {
                return false;
            }
        }
        if(in2[2].subtract(in2[0]).compareTo(BigDecimal.ZERO) == 0) {
            if(slope1.multiply(in2[2]).add(intercept1).compareTo(in2[1].min(in2[3])) > 0 && slope1.multiply(in2[2]).add(intercept2).compareTo(in1[2].max(in2[3])) < 0) {
                return true;
            }
            else {
                return false;
            }
        }

        if(intersection.compareTo(in1[0]) < 0 || intersection.compareTo(in2[0]) < 0 || intersection.compareTo(in1[2]) > 0 || intersection.compareTo(in2[2]) > 0) {
            return false;
        }
        else {
            return true;
        }

    }

    static BigDecimal findSlope(BigDecimal[] in) {
        if(in[2].subtract(in[0]).compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.valueOf(Double.MAX_VALUE);
        }
        return (in[3].subtract(in[1])).divide(in[2].subtract(in[0]), 50, BigDecimal.ROUND_HALF_UP);
    }

    static BigDecimal findYIntercept(BigDecimal[] in, BigDecimal slope) {
        return in[1].subtract(slope.multiply(in[0]));
    }


}