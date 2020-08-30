import java.util.*;
import java.io.*;

public class cowdance {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("cowdance.in"));


        int n = in.nextInt();
        int[] timeOnStage = new int[n];
        int tMax = in.nextInt();
        for(int i = 0; i < n; i++) {
            timeOnStage[i] = in.nextInt();
        }
        in.close();

        int lowerBound = 0;
        int uppperBound = n;
        while (uppperBound != lowerBound) {
            if(getTotalTime(timeOnStage, (lowerBound + uppperBound) / 2) > tMax) {
                lowerBound = (lowerBound + uppperBound) / 2 + 1;
            }
            else {
                uppperBound = (lowerBound + uppperBound) / 2;
            }
        }

        PrintWriter out = new PrintWriter(new File("cowdance.out"));
        System.out.println(lowerBound);
        out.println(lowerBound);
        out.close();
    }

    public static int getTotalTime(int[] timeOnStage, int k) {
        int[] timeLeftOnStage = new int[k];
        int nextCow = k;
        for(int i = 0; i < k; i++) {
            timeLeftOnStage[i] = timeOnStage[i];
        }
        int timePassed = 0;

        while(true) {
            if(nextCow >= timeOnStage.length) {
                int maxTime = 0;
                for(int i = 0; i < timeLeftOnStage.length; i++) {
                    if (timeLeftOnStage[i] > maxTime) {
                        maxTime = timeLeftOnStage[i];
                    }
                }
                return timePassed + maxTime;
            }

            int minIndex = 0;
            for(int i = 0; i < k; i++) {
                if (timeLeftOnStage[i] < timeLeftOnStage[minIndex]) {
                    minIndex = i;
                }
            }
            timePassed += timeLeftOnStage[minIndex];

            timeLeftOnStage[minIndex] = timeOnStage[nextCow];
            nextCow++;

            for(int i = 0; i < k; i++) {
                if (i != minIndex){
                    timeLeftOnStage[i] -= timePassed;
                }
            }
        }
    }
}