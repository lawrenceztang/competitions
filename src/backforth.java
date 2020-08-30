import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class backforth {
    static HashSet<Integer> possibleNums;

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("backforth.in"));
        ArrayList<Integer> bucketSizes1 = new ArrayList<>();
        ArrayList<Integer> bucketSizes2 = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            bucketSizes1.add(in.nextInt());
        }
        for(int i = 0; i < 10; i++) {
            bucketSizes2.add(in.nextInt());
        }
        possibleNums = new HashSet<>();

        moveMilk(bucketSizes1, bucketSizes2, true, 4);

        PrintWriter out = new PrintWriter(new File("backforth.out"));
        out.println(possibleNums.size());
        out.close();
    }

    public static void moveMilk(ArrayList<Integer> first, ArrayList<Integer> second, boolean order, int times) {
        if(times == 0) {
            int sum = 0;
            for(int i = 0; i < first.size(); i++) {
                sum += first.get(i);
            }
            possibleNums.add(sum);
            return;
        }


        if(order) {

            for (int j = 0; j < first.size(); j++) {

                ArrayList<Integer> copy1 = new ArrayList<>();
                for(int i = 0; i < first.size(); i++) {
                    copy1.add(first.get(i));
                }
                ArrayList<Integer> copy2 = new ArrayList<>();
                for(int i = 0; i < second.size(); i++) {
                    copy2.add(second.get(i));
                }
                copy2.add(copy1.get(j));
                copy1.remove(j);

                moveMilk(copy1, copy2, !order, times - 1);
            }
        }
        else {
            for (int j = 0; j < second.size(); j++) {

                ArrayList<Integer> copy1 = new ArrayList<>();
                for(int i = 0; i < first.size(); i++) {
                    copy1.add(first.get(i));
                }
                ArrayList<Integer> copy2 = new ArrayList<>();
                for(int i = 0; i < second.size(); i++) {
                    copy2.add(second.get(i));
                }
                copy1.add(copy2.get(j));
                copy2.remove(j);

                moveMilk(copy1, copy2, !order, times - 1);
            }
        }
    }

}