import java.io.*;
import java.util.*;

public class countcross {

    static Field[][] fields;

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("countcross.in"));
        int n = in.nextInt();
        int k = in.nextInt();
        int r = in.nextInt();


        fields = new Field[n][];
        for (int i = 0; i < n; i++) {
            fields[i] = new Field[n];
            for (int j = 0; j < n; j++) {
                fields[i][j] = new Field();
            }
        }

        for (int i = 0; i < r; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            int x2 = in.nextInt() - 1;
            int y2 = in.nextInt() - 1;

            if (y2 < y) {
                fields[x][y].blocked[2] = true;
                fields[x2][y2].blocked[0] = true;
            }
            if (y2 > y) {
                fields[x][y].blocked[0] = true;
                fields[x2][y2].blocked[2] = true;
            }
            if (x < x2) {
                fields[x][y].blocked[1] = true;
                fields[x2][y2].blocked[3] = true;
            }
            if (x > x2) {
                fields[x][y].blocked[3] = true;
                fields[x2][y2].blocked[1] = true;
            }
        }

        for (int i = 0; i < k; i++) {
            fields[in.nextInt() - 1][in.nextInt() - 1].cow = true;
        }

        in.close();

        ArrayList<Integer> groups = new ArrayList<Integer>();


        int totalLength = 0;
        outer:
        while (true) {


            for (int l = 0; l < fields.length; l++) {
                for (int j = 0; j < fields[l].length; j++) {
                    if (!fields[l][j].filled) {
                        groups.add(floodFill(l, j));
                        totalLength += groups.get(groups.size() - 1);
                        continue outer;
                    }
                }
            }
            break;
        }


        int sum = 0;
        for (int i = 0; i < groups.size(); i++) {
            sum += groups.get(i) * (totalLength - groups.get(i));
        }

        sum /= 2;


        PrintWriter out = new PrintWriter(new File("countcross.out"));
        System.out.println(sum);
        out.println(sum);
        out.close();
    }

    public static int floodFill(int x, int y) {

        int cowCount = 0;

        if (fields[x][y].filled) {
            return 0;
        }
        if (fields[x][y].cow) {
            cowCount++;
        }


        for (int xAdd = -1; xAdd <= 1; xAdd++) {
            for (int yAdd = -1; yAdd <= 1; yAdd++) {
                if (Math.abs(xAdd) == Math.abs(yAdd)) {
                    //nothing
                } else {
                    int blockedIndex1 = -1;
                    int blockedIndex2 = -1;
                    if (xAdd == -1 && yAdd == 0) {
                        blockedIndex1 = Field.INDEX_LEFT;
                        blockedIndex2 = Field.INDEX_RIGHT;
                    } else if (xAdd == 1 && yAdd == 0) {
                        blockedIndex1 = Field.INDEX_RIGHT;
                        blockedIndex2 = Field.INDEX_LEFT;
                    } else if (xAdd == 0 && yAdd == 1) {
                        blockedIndex1 = Field.INDEX_TOP;
                        blockedIndex2 = Field.INDEX_DOWN;
                    } else if (xAdd == 0 && yAdd == -1) {
                        blockedIndex1 = Field.INDEX_DOWN;
                        blockedIndex2 = Field.INDEX_TOP;
                    }

                    fields[x][y].filled = true;
                    if (x + xAdd < fields.length && x + xAdd >= 0 && y + yAdd < fields.length && y + yAdd >= 0 && !fields[x + xAdd][y + yAdd].filled && !fields[x + xAdd][y + yAdd].blocked[blockedIndex2] && !fields[x][y].blocked[blockedIndex1]) {
                        cowCount += floodFill(x + xAdd, y + yAdd);
                    }

                }
            }
        }
        return cowCount;
    }

    public static class Field {
        static final int INDEX_TOP = 0;
        static final int INDEX_RIGHT = 1;
        static final int INDEX_DOWN = 2;
        static final int INDEX_LEFT = 3;

        boolean[] blocked; // top, right, down, left
        boolean cow = false;
        boolean filled = false;

        public Field() {
            blocked = new boolean[4];
        }
    }
}
