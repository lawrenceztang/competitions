import java.util.*;
import java.io.*;

public class blist {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("blist.in"));
        int n = in.nextInt();
        CowMilk[] cowMilks = new CowMilk[n];
        for (int i = 0; i < n; i++) {
            cowMilks[i] = new CowMilk(in);
        }

        Arrays.sort(cowMilks, new SortByBegin());

        outer:
        for(int j = 1; j < 100000; j++) {


            PriorityQueue<CowMilk> queue = new PriorityQueue<>(1, new SortByEnd());
            for (int i = 0; i < cowMilks.length; i++) {
                while (true) {
                    if (queue.size() > 0 && queue.peek().endTime <= cowMilks[i].startTime) {
                        queue.remove();
                    }
                    else {
                        break;
                    }
                }
                queue.add(cowMilks[i]);
                int sum = 0;
                Object[] milking =  queue.toArray();
                for(int u = 0; u < milking.length; u++) {
                    sum += ((CowMilk) (milking[u])).numBuckets;
                }
                if(sum > j) {
                    continue outer;
                }
            }

            PrintWriter out = new PrintWriter(new File("blist.out"));
            out.println(j);
            out.close();
            break;

        }


    }


    static class CowMilk {
        int startTime;
        int endTime;
        int numBuckets;

        public CowMilk(Scanner in) {
            startTime = in.nextInt();
            endTime = in.nextInt();
            numBuckets = in.nextInt();
        }
    }

    static class SortByEnd implements Comparator<CowMilk> {
        public int compare(CowMilk a, CowMilk b) {
            return a.endTime - b.endTime;
        }
    }

    static class SortByBegin implements Comparator<CowMilk> {
        public int compare(CowMilk a, CowMilk b) {
            return a.startTime - b.startTime;
        }
    }
}