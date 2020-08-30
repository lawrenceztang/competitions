import java.util.*;
import java.io.*;

public class convention {

    static int[] timesArrive;

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("convention.in"));
        int numCows = in.nextInt();
        int numBuses = in.nextInt();
        int cowsPerBus = in.nextInt();
        timesArrive = new int[numCows];
        for(int i = 0; i < numCows; i++) {
            timesArrive[i] = in.nextInt();
        }
        Arrays.sort(timesArrive);

        int cowsPerBusReal = timesArrive.length / numBuses;
        Chunk[] busChunks = new Chunk[numBuses];

        for(int i = 0; i < busChunks.length; i++) {
            if(i == busChunks.length - 1) {
                busChunks[i] = new Chunk(i * cowsPerBusReal, timesArrive.length, i);
            }
            else {
                busChunks[i] = new Chunk(i * cowsPerBusReal, i * cowsPerBusReal + cowsPerBusReal, i);
            }
        }

        Chunk[] order = Arrays.copyOf(busChunks, busChunks.length);
        Arrays.sort(order, new ChunkOrderComparator());


        int leastMaxWait = Integer.MAX_VALUE;
        while (true) {

            Arrays.sort(busChunks, new ChunkComparator());
            int maxWait = timesArrive[busChunks[busChunks.length - 1].endIndex - 1] - timesArrive[busChunks[busChunks.length - 1].startIndex];
            if(maxWait > leastMaxWait) {
                break;
            }
            else {
                leastMaxWait = maxWait;
            }


            Chunk max = busChunks[busChunks.length - 1];
            Chunk min = busChunks[0];

            if(min.startIndex < max.startIndex) {
                max.startIndex++;

                for(int i = max.position - 1; i > min.position; i--) {
                    order[i].startIndex++;
                    order[i].endIndex++;
                }

                min.endIndex++;
            }


            if(min.startIndex > max.startIndex) {
                max.endIndex--;

                for(int i = max.position + 1; i < min.position; i++) {
                    order[i].startIndex--;
                    order[i].endIndex--;
                }

                min.startIndex--;
            }

        }


        PrintWriter out = new PrintWriter(new File("convention.out"));
        out.println(leastMaxWait);
        out.close();
    }

    static class Chunk {
        int startIndex;
        int endIndex;
        int position;

        public Chunk(int startIndex, int endIndex, int position) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.position = position;
        }
    }

    static class ChunkComparator implements Comparator<Chunk> {
        @Override
        public int compare(Chunk o1, Chunk o2) {
            return (timesArrive[o2.startIndex] - timesArrive[o2.endIndex - 1]) - (timesArrive[o1.startIndex] - timesArrive[o1.endIndex - 1]);
        }
    }
    static class ChunkOrderComparator implements Comparator<Chunk> {
        @Override
        public int compare(Chunk o1, Chunk o2) {
            return o1.startIndex - o2.startIndex;
        }
    }
}