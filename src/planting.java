import java.util.*;
import java.io.*;

public class planting {


    //binary search method
    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("planting.in"));
        int n = in.nextInt();
        Field[] fields = new Field[n];
        for (int i = 0; i < n - 1; i++) {
            fields[i] = new Field(in);
        }
        fields[n - 1] = new Field();
        Arrays.sort(fields, new FieldComparator());
        int previous = -1;
        int numMissing = 0;
        for (int i = 0; i < fields.length - 1; i++) {
            if (fields[i].num - previous != 1) {
                numMissing = fields[i].num;
                break;
            }
            previous = fields[i].num;
        }

        Field missingField = new Field();
        missingField.num = numMissing - 1;

        fields[fields.length - 1] = missingField;

        Arrays.sort(fields, new FieldComparator());
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].connectsTo.size() > 0) {
                boolean notThere = true;
                for(int j = 0; j < fields[fields[i].connectsTo.get(0)].connectsTo.size(); j++) {
                    if(fields[fields[i].connectsTo.get(0)].connectsTo.get(j) == i) {
                        notThere = false;
                    }
                }
                if(notThere) {
                    fields[fields[i].connectsTo.get(0)].connectsTo.add(i);
                }

            }
        }

        //binary search
        int upperLimit = 100000;
        int lowerLimit = 0;
        while (upperLimit != lowerLimit) {
            Field[] fieldsCopy = new Field[fields.length];
            for (int i = 0; i < fieldsCopy.length; i++) {
                fieldsCopy[i] = new Field(fields[i]);
            }

            int mid = (upperLimit + lowerLimit) / 2;
            if (floodFill(fields, mid, 0) == true) {
                upperLimit = mid;
            } else {
                lowerLimit = mid + 1;
            }
        }
        PrintWriter out = new PrintWriter(new File("planting.out"));
        out.println(upperLimit);
        out.close();
    }

    static boolean floodFill(Field[] fields, int numGrassTypes, int location) {

        Set<Integer> grassesUsed = new HashSet<>();
        for (int i = 0; i < fields[location].connectsTo.size(); i++) {
            for (int j = 0; j < fields[i].connectsTo.size(); j++) {
                if (fields[fields[i].connectsTo.get(j)].typeGrass != -1) {
                    grassesUsed.add(fields[fields[i].connectsTo.get(j)].typeGrass);
                }
            }
            if (fields[i].typeGrass != -1) {
                grassesUsed.add(fields[i].typeGrass);
            }
        }
        if (grassesUsed.size() >= numGrassTypes) {
            return false;
        } else {
            for (int i = 0; i < numGrassTypes; i++) {
                if (!grassesUsed.contains(i)) {
                    fields[location].typeGrass = i;
                }
            }
        }

        for (int i = 0; i < fields[location].connectsTo.size(); i++) {
            fields[location].filled = true;
            if (!fields[fields[location].connectsTo.get(i)].filled) {
                floodFill(fields, numGrassTypes, fields[location].connectsTo.get(i));
            }
        }
        return true;
    }

    static class Field {
        int num;
        ArrayList<Integer> connectsTo;
        int typeGrass = -1;
        boolean filled = false;

        public Field() {
            connectsTo = new ArrayList<>();
            num = Integer.MAX_VALUE;
        }

        public Field(Field field) {
            this.num = field.num;
            this.connectsTo = new ArrayList<>();
            for (int i = 0; i < field.connectsTo.size(); i++) {
                this.connectsTo.add(field.connectsTo.get(i));
            }
        }

        public Field(Scanner in) {
            num = in.nextInt() - 1;
            connectsTo = new ArrayList<>();
            connectsTo.add(in.nextInt() - 1);
        }
    }

    static class FieldComparator implements Comparator<Field> {

        public int compare(Field f1, Field f2) {
            return f1.num - f2.num;
        }
    }

}