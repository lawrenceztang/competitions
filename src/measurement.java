import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class measurement {

	public static void main(String[] args) throws IOException {
		String[] in = doIt("C:/Users/Lawrence/Documents/Saved From Backup/Codes/USACO/Test Text/nums2.txt");

		int mildred = 7;
		int bessie = 7;
		int elsie = 7;
		int count = 0;

		for (int i = 1; i < 10000; i++) {
			boolean[] topGuy = new boolean[3];
			if (mildred >= bessie && mildred >= elsie) {
				topGuy[0] = true;
			}
			if (bessie >= mildred && bessie >= elsie) {
				topGuy[1] = true;
			}
			if (elsie >= bessie && elsie >= mildred) {
				topGuy[2] = true;
			}

			boolean changed = false;
			for (int a = 1; a < in.length; a++) {
				
				if (in[a].substring(0, (int) (Math.log10(i)) + 1).equals(Integer.toString(i)) && in[a].charAt((int) (Math.log10(i)) + 1) == ' ') {
					int amountAdded = 0;
					for (int p = 0; p < in[a].length(); p++) {
						if (in[a].charAt(p) == '+') {
							amountAdded = Integer.parseInt(in[a].substring(p + 1, in[a].length()));
						}
						if (in[a].charAt(p) == '-') {
							amountAdded = -Integer.parseInt(in[a].substring(p + 1, in[a].length()));
						}
					}
					int space = 0;
					for(int u = 0; u < in[a].length(); u++) {
						if(in[a].charAt(u) == ' ') {
							space = u;
							break;
						}
					}
					
					if (in[a].charAt(space + 1) == 'M') {
						mildred += amountAdded;
					}
					if (in[a].charAt(space + 1) == 'B') {
						bessie += amountAdded;
					}
					if (in[a].charAt(space + 1) == 'E') {
						elsie += amountAdded;
					}

					boolean[] topGuys = new boolean[3];
					if (mildred >= bessie && mildred >= elsie) {
						topGuys[0] = true;
					}
					if (bessie >= mildred && bessie >= elsie) {
						topGuys[1] = true;
					}
					if (elsie >= bessie && elsie >= mildred) {
						topGuys[2] = true;
					}
					boolean trues = true;
					for (int y = 0; y < 3; y++) {
						if (topGuy[y] == topGuys[y]) {

						} else {
							trues = false;
						}
					}
					if (trues == false && changed == false) {
						count++;
						changed = true;
					}

				}
			}
		}
		System.out.println(count);
		writeToFile(Integer.toString(count), "measurement.out");
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
