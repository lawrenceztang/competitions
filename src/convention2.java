import java.util.*;
import java.io.*;

public class convention2 {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("convention2.in"));
        int numCows = in.nextInt();
        Cow[] cows = new Cow[numCows];
        for (int i = 0; i < numCows; i++) {
            cows[i] = new Cow(in, i);
        }
        Arrays.sort(cows, new StartTimeComparator());
        int lastTime = cows[cows.length - 1].arriveTime;
        PriorityQueue<Cow> notWaiting = new PriorityQueue<Cow>(1, new StartTimeComparator());
        for(int i = 0; i < cows.length; i++) {
            notWaiting.add(cows[i]);
        }
        int result = -1;
        Cow grazer = null;
        PriorityQueue<Cow> waitingList = new PriorityQueue<Cow>(1, new SeniorityComparator());
        int time = notWaiting.peek().arriveTime;
        while(true) {

            if (waitingList.size() == 0 && time > lastTime) {
                break;
            }
            while (notWaiting.size() != 0 && time >= notWaiting.peek().arriveTime) {
                waitingList.add(notWaiting.poll());
            }

            if(waitingList.size() != 0) {
                grazer = waitingList.poll();
                grazer.grazeArriveTime = time;
                result = Math.max(result, grazer.grazeArriveTime - grazer.arriveTime);
                time += grazer.timeThere;
            }
            else {
                if(notWaiting.size() > 0) {
                    waitingList.add(notWaiting.poll());
                    time = waitingList.peek().arriveTime;
                    while(notWaiting.size() > 0 && notWaiting.peek().arriveTime == time) {
                        waitingList.add(notWaiting.poll());
                    }
                    grazer = waitingList.poll();
                }
                else {
                    break;
                }
            }


        }


        PrintWriter out = new PrintWriter(new File("convention2.out"));
        out.println(result);
        out.close();
    }

    static class Cow {
        int seniority;
        int arriveTime;
        int timeThere;
        int grazeArriveTime;

        public Cow(Scanner in, int i) {
            arriveTime = in.nextInt();
            timeThere = in.nextInt();
            seniority = i;
        }
    }

    static class SeniorityComparator implements Comparator<Cow> {
        @Override
        public int compare(Cow o1, Cow o2) {
            return o1.seniority - o2.seniority;
        }
    }

    static class StartTimeComparator implements Comparator<Cow> {
        @Override
        public int compare(Cow o1, Cow o2) {
            return o1.arriveTime - o2.arriveTime;
        }
    }
}