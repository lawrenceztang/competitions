import java.util.*;
import java.io.*;

public class revegetate {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("revegetate.in"));
        int n = in.nextInt();
        int m = in.nextInt();

        //count the number of chunks of fields connected
        Islands islands = new Islands();
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            in.next();
            int in1 = in.nextInt() - 1;
            int in2 = in.nextInt() - 1;

            graph[in1].add(in2);
            graph[in2].add(in1);
        }

        int sum = islands.countIslands(graph);
        int output = (int) Math.pow(10, sum);

        PrintWriter out = new PrintWriter(new File("revegetate.out"));
        out.println(output);

        out.close();
    }

    static class Islands {
        boolean[] visited;

        void DFS(ArrayList<Integer>[] graph, int row) {

            visited[row] = true;

            for (int i = 0; i < graph[row].size(); i++) {
                if (!visited[graph[row].get(i)]) {
                    DFS(graph, graph[row].get(i));
                }
            }

        }

        int countIslands(ArrayList<Integer>[] graph) {
            visited = new boolean[graph.length];

            int count = 0;
            for (int i = 0; i < visited.length; i++) {
                if (!visited[i] && graph[i].size() > 0) {
                    DFS(graph, i);
                    count++;
                }
            }
            return count;
        }
    }
}