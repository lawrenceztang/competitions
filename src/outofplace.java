import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class outofplace {
    public static void main(String[] args) throws Exception {



        String[] input = doIt("outofplace.in");

        ArrayList<Integer> array =  new ArrayList<>();

        for(int i = 1; i < input.length; i++) {
            array.add(Integer.parseInt(input[i]));
        }

//        ArrayList<Integer> arraySorted = (ArrayList<Integer>) array.clone();
//
//        for (int p = 0; p < 10000; p++) {
//            for (int i = 0; i < arraySorted.size() - 1; i++) {
//                if (arraySorted.get(i + 1) < arraySorted.get(i)) {
//
//                    int swapper = arraySorted.get(i + 1);
//                    arraySorted.remove(i + 1);
//                    arraySorted.add(i, swapper);
//                }
//            }
//        }

        int count = 0;
        for (int p = 0; p < 10000; p++) {
            for (int i = 0; i < array.size(); i++) {
                int subtract;
                for (subtract = 1; subtract < 100; subtract++) {
                    if (i - subtract >= 0 && array.get(i - subtract) > array.get(i)) {

                    } else {
                        break;
                    }
                }
                if(subtract != 1) {
                    int swapper = array.get(i);
                    array.remove(i);
                    array.add(i - subtract + 1, swapper);
                    count++;
                }
            }

        }
//        int count = 0;
//        for (int i = 0; i < 10000; i++) {
//            int unsortedPos;
//            boolean foundPair = false;
//            for (unsortedPos = 0; unsortedPos < array.size(); unsortedPos++) {
//                if (array.get(unsortedPos) != arraySorted.get(unsortedPos)) {
//
//                    for (int findMatch = 0; findMatch < array.size(); findMatch++) {
//                        if (array.get(unsortedPos) == arraySorted.get(findMatch) && arraySorted.get(unsortedPos) == array.get(findMatch)) {
//                            int swapper = array.get(findMatch);
//                            array.set(findMatch, array.get(unsortedPos));
//                            array.set(unsortedPos, swapper);
//
//                            count++;
//                            foundPair = true;
//                            break;
//                        }
//                    }
//                }
//            }
//            for (unsortedPos = 0; unsortedPos < array.size(); unsortedPos++) {
//                if (!foundPair) {
//
//                    for (int findMatch = 0; findMatch < array.size(); findMatch++) {
//                        if ((array.get(unsortedPos) == arraySorted.get(findMatch) || arraySorted.get(unsortedPos) == array.get(findMatch)) && (array.get(unsortedPos) != arraySorted.get(unsortedPos)) && (array.get(findMatch) != arraySorted.get(findMatch))) {
//                            int swapper = array.get(findMatch);
//                            array.set(findMatch, array.get(unsortedPos));
//                            array.set(unsortedPos, swapper);
//
//                            count++;
//                            break;
//                        }
//                    }
//                }
//            }
//
//        }


        writeToFile(Integer.toString(count), "outofplace.out");
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
