import java.io.*;

public class hoofball {
    public static void main(String[] args) throws Exception{
        String[] in = doIt("hoofball.in");

        in[1] = in[1] + " ";
        int[] inn = new int[Integer.parseInt(in[0])];

        int previousSpace = -1;
        int innIndex = 0;
        for(int i = 0; i < in[1].length(); i++) {
            if(in[1].charAt(i) == ' ') {
                inn[innIndex] = Integer.parseInt(in[1].substring(previousSpace + 1, i));
                innIndex++;
                previousSpace = i;
            }
        }

        int[] innn = sort(inn);

        int numBalls = 0;
        boolean[] thrownTo = new boolean[innn.length];
        for(int ballNum = 1; ballNum < innn.length; ballNum++) {
            int mostCover = 0;
            int mostCoverIndex = 0;

            for(int b = 0; b < innn.length; b++) {
                int cover = 0;
                boolean right;

                if(!thrownTo[b]) {
                    cover++;
                }

                if(b != innn.length - 1 && (b == 0 || innn[b + 1] - innn[b] < innn[b] - innn[b - 1])) {
                    right = true;
                }
                else {
                    right = false;
                }

                if(right) {
                    if(!thrownTo[b + 1]) {
                        cover++;
                    }
                }
                else {
                    if(!thrownTo[b - 1]) {
                        cover++;
                    }
                }

                if(right) {
                    int added = 1;
                    while (true) {
                        if(innn[b + added + 1] - innn[b + added] < innn[b + added] - innn[b + added - 1]) {
                            if(thrownTo[b + added + 1] == false) {
                                cover++;
                                added++;
                            }
                        }
                        else {
                            break;
                        }
                    }
                }
                else {
                    int added = 1;
                    while (true) {
                        if(innn[b - added] - innn[b - added - 1] <= innn[b - added + 1] - innn[b - added]) {
                            if(thrownTo[b - added - 1] == false) {
                                cover++;
                                added++;
                            }
                        }
                        else {
                            break;
                        }
                    }
                }

                if(cover > mostCover) {
                    mostCover = cover;
                    mostCoverIndex = b;
                }
            }

            boolean right;
            if(mostCoverIndex != innn.length - 1 && (mostCoverIndex == 0 || innn[mostCoverIndex + 1] - innn[mostCoverIndex] < innn[mostCoverIndex] - innn[mostCoverIndex - 1])) {
                right = true;
            }
            else {
                right = false;
            }
            if(right) {
                int added = 0;
                while (true) {
                    if(innn[mostCoverIndex + added + 1] - innn[mostCoverIndex + added] < innn[mostCoverIndex + added] - innn[mostCoverIndex + added - 1]) {
                        if(thrownTo[mostCoverIndex + added + 1] == false) {
                            thrownTo[mostCoverIndex + added + 1] = true;
                            added++;
                        }
                    }
                    else {
                        break;
                    }
                }
            }
            else {
                int added = 0;
                while (true) {
                    if(mostCoverIndex == innn.length - 1 || (mostCoverIndex != 0 && innn[mostCoverIndex - added] - innn[mostCoverIndex - added - 1] <= innn[mostCoverIndex - added + 1] - innn[mostCoverIndex - added])) {
                        if(mostCoverIndex - added - 1 > 0 && thrownTo[mostCoverIndex - added - 1] == false) {
                            thrownTo[mostCoverIndex - added - 1] = true;
                            added++;
                        }
                    }
                    else {
                        break;
                    }
                }
            }
            boolean done = true;

            for(int a = 0; a < thrownTo.length; a++) {
                if(!thrownTo[a]) {
                    done = false;
                    break;
                }
            }
            if(done) {
                writeToFile(Integer.toString(ballNum), "hoofball.out");
            }
        }


        }



    public static int[] sort(int[] array) {
        if(array.length == 1) {
            return array;
        }

        int largest = 0;
        for(int i = 0; i < array.length; i++) {
            if(array[i] < array[largest]) {
                largest = i;
            }
        }
        int[] output = new int[array.length - 1];
        boolean found = false;
        for(int i = 0; i < output.length; i++) {
            if(i == largest) {
                found = true;
                output[i] = array[i + 1];
            }
            else if(found == true) {
                output[i] = array[i + 1];
            }
            else if(found == false) {
                output[i] = array[i];
            }
        }

        int[] realOut = new int[array.length];
        realOut[0] = array[largest];
        int[] sorted = sort(output);
        for(int i = 0; i < sorted.length; i++) {
            realOut[i + 1] = sorted[i];
        }
        return realOut;
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
