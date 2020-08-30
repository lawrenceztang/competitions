import java.io.*;

public class teleport {

    public static void main(String[] args) throws Exception {
        String[] in = doIt("teleport.in");
        in[0] = in[0] + " ";
        int[] inn = new int[4];

        int previousSpace = -1;
        int innIndex = 0;
        for(int i = 0; i < in[0].length(); i++) {
            if(in[0].charAt(i) == ' ') {
                inn[innIndex] = Integer.parseInt(in[0].substring(previousSpace + 1, i));
                innIndex++;
                previousSpace = i;
            }
        }

        int leastTraveled = 100000;

        if(absoluteValue(inn[0] - inn[2]) + absoluteValue(inn[3] - inn[1]) < leastTraveled) {
            leastTraveled = absoluteValue(inn[0] - inn[2]) + absoluteValue(inn[3] - inn[1]);
        }
        if(absoluteValue(inn[0] - inn[3]) + absoluteValue(inn[2] - inn[1]) < leastTraveled) {
            leastTraveled = absoluteValue(inn[0] - inn[3]) + absoluteValue(inn[2] - inn[1]);
        }
        if(absoluteValue(inn[1] - inn[0]) < leastTraveled) {
            leastTraveled = absoluteValue(inn[1] - inn[0]);
        }

        writeToFile(Integer.toString(leastTraveled), "teleport.out");
    }

    public static int absoluteValue(int in) {
        if(in < 0) {
            return in * -1;
        }
        return in;
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
