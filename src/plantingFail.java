import java.util.*;
import java.io.*;

public class plantingFail {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("planting.in"));
        int n = in.nextInt();
        Field[] fields = new Field[n];
        for(int i = 0; i < n - 1; i++) {
            fields[i] = new Field(in);
        }
        fields[n - 1] = new Field();
        Arrays.sort(fields, new FieldComparator());
        int previous = 0;
        int numMissing = 0;
        for(int i = 1; i < fields.length; i++) {
            if(fields[i].num - previous != 1) {
                numMissing = fields[i].num - 1;
                break;
            }
            previous = fields[i].num;
        }

        Field missingField = new Field();
        missingField.num = numMissing;
        for(int i = 1; i < fields.length; i++) {
            if(fields[i].connectsTo.get(0) == numMissing) {
                missingField.connectsTo.add(fields[i].num);
            }
        }

        fields[0] = missingField;

        for(int i = 0; i < fields.length; i++) {
            fields[fields[i].connectsTo.get(0) - 1].connectsTo.add(i);
        }

        int maxConnect = -1;
        for(int i = 0; i < fields.length; i++) {
            maxConnect = Math.max(fields[i].connectsTo.size(), maxConnect);
        }

        PrintWriter out = new PrintWriter(new File("planting.out"));
        out.println(maxConnect);
        out.close();
    }

    static class Field {
        int num;
        ArrayList<Integer> connectsTo;

        public Field() {
            connectsTo = new ArrayList<>();
            num = -1;
        }

        public Field(Scanner in) {
            num = in.nextInt();
            connectsTo = new ArrayList<>();
            connectsTo.add(in.nextInt());
        }
    }

    static class FieldComparator implements Comparator<Field> {

        public int compare(Field f1, Field f2) {
            return f1.num - f2.num;
        }
    }
}