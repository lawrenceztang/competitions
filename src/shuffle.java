import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class shuffle {
	public static void main(String[] args) {
		
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
