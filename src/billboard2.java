import java.io.*;

public class billboard2 {
    static int lawnx1;
    static int lawnx2;
    static int lawny1;
    static int lawny2;

    static int frontBillx1;
    static int frontBillx2;
    static int frontBilly1;
    static int frontBilly2;

    static int lawnHeight;
    static int lawnWidth;

    static String[] in;

    public static void main(String[] args) throws Exception {
        in = doIt("billboard.in");
        setVars();
        int areaNeeded = lawnHeight * lawnWidth;

        if (frontBillx1 <= lawnx1 && frontBillx2 >= lawnx2 && frontBilly1 <= lawny1 && frontBilly2 >= lawny2) {
            writeToFile("0", "billboard.out");
            System.exit(0);
        }

        if (frontBillx1 <= lawnx1 && frontBillx2 >= lawnx2) {
            //inside
            if (frontBilly1 <= lawny2 && frontBilly1 >= lawny1) {
                if (frontBilly2 > lawny2) {
                    areaNeeded -= lawnWidth * (lawny2 - frontBilly1);
                }

            }
            if (frontBilly2 >= lawny1 && frontBilly2 <= lawny2) {
                if (frontBilly1 <= lawny1) {
                    areaNeeded -= lawnWidth * (frontBilly2 - lawny1);
                }
            }
        }
        if (frontBilly1 <= lawny1 && frontBilly2 >= lawny2) {
            if (frontBillx1 >= lawnx1 && frontBillx1 <= lawnx2) {
                if (frontBillx2 >= lawnx2) {
                    areaNeeded -= lawnHeight * (lawnx2 - frontBillx1);
                }
            }
            if (frontBillx2 >= lawnx1 && frontBillx2 <= lawnx2) {
                if (frontBillx1 <= lawnx1) {
                    areaNeeded -= lawnHeight * (frontBillx2 - lawnx1);
                }
            }
        }

        writeToFile(Integer.toString(areaNeeded), "billboard.out");


    }


    public static void setVars() {
        int previousSpace = 0;
        outer:
        for (int i = 0; i < in[0].length(); i++) {
            if (in[0].charAt(i) == ' ') {
                lawnx1 = Integer.parseInt(in[0].substring(previousSpace, i));
                previousSpace = i;
                for (int p = previousSpace + 1; p < in[0].length(); p++) {
                    if (in[0].charAt(p) == ' ') {
                        lawny1 = Integer.parseInt(in[0].substring(previousSpace + 1, p));
                        previousSpace = p;
                        for (int o = previousSpace + 1; o < in[0].length(); o++) {
                            if (in[0].charAt(o) == ' ') {
                                lawnx2 = Integer.parseInt(in[0].substring(previousSpace + 1, o));
                                previousSpace = o;
                                lawny2 = Integer.parseInt(in[0].substring(previousSpace + 1, in[0].length()));
                                break outer;
                            }
                        }
                    }
                }
            }
        }

        previousSpace = 0;
        outer:
        for (int i = 0; i < in[1].length(); i++) {
            if (in[1].charAt(i) == ' ') {
                frontBillx1 = Integer.parseInt(in[1].substring(previousSpace, i));
                previousSpace = i;
                for (int p = previousSpace + 1; p < in[1].length(); p++) {
                    if (in[1].charAt(p) == ' ') {
                        frontBilly1 = Integer.parseInt(in[1].substring(previousSpace + 1, p));
                        previousSpace = p;
                        for (int o = previousSpace + 1; o < in[1].length(); o++) {
                            if (in[1].charAt(o) == ' ') {
                                frontBillx2 = Integer.parseInt(in[1].substring(previousSpace + 1, o));
                                previousSpace = o;
                                frontBilly2 = Integer.parseInt(in[1].substring(previousSpace + 1, in[1].length()));
                                break outer;
                            }
                        }
                    }
                }
            }
        }

        lawnHeight = lawny2 - lawny1;
        lawnWidth = lawnx2 - lawnx1;

    }


    public static void writeToFile(String textLine, String path) throws IOException {
        FileWriter write = new FileWriter(path, true);
        BufferedWriter bw = new BufferedWriter(write);
        bw.write(textLine);
        bw.newLine();
        bw.close();
        write.close();
    }

    public static String[] doIt(String path) throws IOException {
        int lineNum = 0;
        FileReader fr = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fr);
        boolean continues = true;
        while (continues == true) {
            if (textReader.readLine() != null) {
                lineNum++;
            } else {
                continues = false;
            }
        }

        String[] textData = new String[lineNum];
        FileReader fr1 = new FileReader(path);
        BufferedReader textReader1 = new BufferedReader(fr1);
        int i;
        for (i = 0; i < lineNum; i++) {
            textData[i] = textReader1.readLine();
        }
        textReader1.close();
        textReader.close();
        return textData;
    }
}
