import java.util.*;
import java.io.*;

public class mountains {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("mountains.in"));
        int n = in.nextInt();
        Mountain[] mountains = new Mountain[n];
        for(int i = 0; i < n; i++) {
            mountains[i] = new Mountain(in);
        }

        Arrays.sort(mountains, new Base1Comparator());
//        PriorityQueue<Mountain> mountainsInLocation = new PriorityQueue<>(1, new Base2Comparator());
//
//        for(int i = 0; i < mountains.length; i++) {
//
//            while(mountainsInLocation.size() > 0 && mountainsInLocation.peek().base2 <= mountains[i].base1) {
//                mountainsInLocation.poll();
//            }
//            Object[] temp = mountainsInLocation.toArray();
//            if(mountainsInLocation.size() > 0 && ((Mountain)temp[temp.length - 1]).base2 >= mountains[i].base2) {
//                mountains[i].covered = true;
//            }
//            mountainsInLocation.add(mountains[i]);
//
//        }

        Mountain mountainsInLocation = null;

        for(int i = 0; i < mountains.length; i++) {

            if(mountainsInLocation != null && mountainsInLocation.base2 < mountains[i].base1) {
                mountainsInLocation = null;
            }
            if(mountainsInLocation != null && mountainsInLocation.base2 >= mountains[i].base2) {
                mountains[i].covered = true;
            }
            if(mountainsInLocation == null || mountains[i].base2 > mountainsInLocation.base2) {
                mountainsInLocation = mountains[i];
            }
        }

        int sum = 0;
        for(int i = 0; i < mountains.length; i++) {
            if(!mountains[i].covered) {
                sum++;
            }
        }

        PrintWriter out = new PrintWriter(new File("mountains.out"));
        out.println(sum);
        out.close();
    }

    static class Mountain {
        //location of peak, base
        int x;
        int y;
        int base1;
        int base2;
        boolean covered = false;
        public Mountain(Scanner in) {
            x = in.nextInt();
            y = in.nextInt();
            base1 = x - y;
            base2 = x + y;
        }
    }

    static class Base1Comparator implements Comparator<Mountain> {
        public int compare(Mountain m1, Mountain m2) {
            int dif =  m1.base1 - m2.base1;;
            if(dif == 0) {
                return m2.base2 - m1.base2;
            }
            return dif;
        }
    }

    static class Base2Comparator implements Comparator<Mountain> {
        public int compare(Mountain m1, Mountain m2) {
            return m1.base2 - m2.base2;
        }
    }
}