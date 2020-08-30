import java.util.*;
import java.io.*;

public class mixmilk {

    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(new File("mixmilk.in"));

        Bucket[] buckets = new Bucket[3];
        for(int i = 0; i < 3; i++) {
            buckets[i] = new Bucket(scan);
        }

        int bucketToPour = 0;
        for(int i = 0; i < 100; i++) {
            int amountPoured = Math.min(buckets[bucketToPour].amountIn, buckets[(bucketToPour + 1) % 3].capacity - buckets[(bucketToPour + 1) % 3].amountIn);
            buckets[(bucketToPour + 1) % 3].amountIn += amountPoured;
            buckets[bucketToPour].amountIn -= amountPoured;
            bucketToPour = (bucketToPour + 1) % 3;
        }

        PrintWriter out = new PrintWriter(new File("mixmilk.out"));
        for(int i = 0; i < 3; i++) {
            out.println(buckets[i].amountIn);
        }
        out.close();
    }

    static class Bucket {
        int capacity;
        int amountIn;
        public Bucket(Scanner scan) {
            capacity = scan.nextInt();
            amountIn = scan.nextInt();
        }
    }
}
