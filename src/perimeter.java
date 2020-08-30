import java.util.*;
import java.io.*;

public class perimeter {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("perimeter.in"));
        int n = in.nextInt();
        Chunk[][] grid = new Chunk[n][n];
        for(int i = 0; i < n; i++) {
            String temp = in.next();
            for(int j = 0; j < n; j++) {
                grid[i][j] = new Chunk();
                grid[i][j].type = temp.charAt(j);
            }
        }

        int maxArea = Integer.MIN_VALUE;
        int leastPerim = Integer.MAX_VALUE;

        outer:
        while(true) {

            for(int x = 0; x < n; x++) {
                for(int y = 0; y < n; y++) {
                    int[] result = floodFill(grid, x, y);
                    if(result[0] > maxArea) {
                        maxArea = result[0];
                        leastPerim = result[1];
                        continue outer;
                    }
                    else if(result[0] == maxArea) {
                        leastPerim = Math.min(result[1], leastPerim);
                    }
                }
            }
            break;

        }


        PrintWriter out = new PrintWriter(new File("perimeter.out"));
        out.println(maxArea + " " + leastPerim);
        out.close();
    }

    static class Chunk {
        boolean filled;
        char type;
        public Chunk() {

        }
    }

    public static int[] floodFill(Chunk[][] grid, int x, int y) {
        if(x < 0 || y < 0 || x >= grid.length || y >= grid.length || grid[x][y].type == '.') {
            return new int[]{0, 1};
        }
        else if(grid[x][y].filled) {
            return new int[]{0, 0};
        }
        else {
            grid[x][y].filled = true;

            int[] count = new int[]{1, 0};
            int[] result = floodFill(grid, x, y + 1);
            count[1] += result[1];
            count[0] += result[0];

            result = floodFill(grid, x + 1, y);
            count[1] += result[1];
            count[0] += result[0];

            result = floodFill(grid, x - 1, y);
            count[1] += result[1];
            count[0] += result[0];

            result = floodFill(grid, x, y - 1);
            count[1] += result[1];
            count[0] += result[0];
            return count;
        }
    }
}
