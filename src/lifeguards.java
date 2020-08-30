import java.io.*;

public class lifeguards {

public static void main(String[] args) throws Exception{
    String[] in = doIt("lifeguards.in");
    int[] hours = new int[in.length - 1];
    int[] hoursEnd = new int[in.length - 1];
    for(int d = 1; d < in.length; d++) {
        int spaceNum = 0;
        for(int lineChar = 0; lineChar < in[d].length(); lineChar++) {
            if(in[d].charAt(lineChar) == ' ') {
                spaceNum = lineChar;
                break;
            }
        }
        hours[d - 1] = Integer.parseInt(in[d].substring(0, spaceNum));
        hoursEnd[d - 1] = Integer.parseInt(in[d].substring(spaceNum + 1, in[d].length()));
    }

//    int[] hours = new int[] {5, 1, 3};
//    int[] hoursEnd = new int[] {9, 4, 7};

    int maxHours = 0;
    for(int n = 0; n < hours.length; n++) {
        boolean[] hourDone = new boolean[1000];
        int hoursDone = 0;
        for(int i = 0; i < hours.length; i++) {

            if(i != n) {
                for(int a = hours[i]; a < hoursEnd[i]; a++) {
                    hourDone[a] = true;
                }
            }
        }

        for(int u = 0; u < hourDone.length; u++) {
            if(hourDone[u] == true) {
                hoursDone++;
            }
        }
        if(hoursDone > maxHours) {
            maxHours = hoursDone;
        }
    }
    System.out.println(maxHours);
    writeToFile(Integer.toString(maxHours), "lifeguards.out");
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
