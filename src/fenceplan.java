import java.util.*;
import java.io.*;

public class fenceplan {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("fenceplan.in"));
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] cowLocations = new int[n][];
        for(int i = 0; i < n; i++) {
            cowLocations[i] = new int[]{in.nextInt(), in.nextInt()};
        }
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++) {
            int in1 = in.nextInt();
            int in2 = in.nextInt();
            graph[in1 - 1].add(in2 - 1);
            graph[in2 - 1].add(in1 - 1);
        }

        int[] socialGroups = new int[n];
        Arrays.fill(socialGroups, -1);

        int minPerim = Integer.MAX_VALUE;
        outer:
        while(true) {
            for(int i = 0; i < n; i++) {
                if (socialGroups[i] == -1) {
                    int[] bounds = fillGroup(graph, i, socialGroups, cowLocations);
                    minPerim = Math.min(Math.abs(bounds[2] - bounds[0]) * 2 + Math.abs(bounds[3] - bounds[1]) * 2, minPerim);
                    continue outer;
                }
            }
            break;
        }


        PrintWriter out = new PrintWriter(new File("fenceplan.out"));
        out.println(minPerim);
        out.close();
    }

    static int[] fillGroup(ArrayList<Integer>[] graph, int startIndex, int[] socialGroups, int[][] cowLocations) {
        Deque<Integer> queue = new LinkedList<>();
        queue.push(startIndex);
        int[] bounds = new int[4];
        bounds[0] = Integer.MAX_VALUE;
        bounds[1] = Integer.MAX_VALUE;
        bounds[2] = Integer.MIN_VALUE;
        bounds[3] = Integer.MIN_VALUE;
        while(queue.size() > 0) {
            int num = queue.pop();

            bounds[0] = Math.min(bounds[0], cowLocations[num][0]);
            bounds[1] = Math.min(bounds[1], cowLocations[num][1]);
            bounds[2] = Math.max(bounds[2], cowLocations[num][0]);
            bounds[3] = Math.max(bounds[3], cowLocations[num][1]);

            socialGroups[num] = startIndex;
            for(int j = 0; j < graph[num].size(); j++) {
                if(socialGroups[graph[num].get(j)] == -1) {
                    queue.push(graph[num].get(j));
                }
            }
        }
        return bounds;
    }
}