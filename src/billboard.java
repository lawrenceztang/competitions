import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class billboard {

	public static void main(String args[]) throws IOException {
		int[][] in = doIt("C:/Users/Lawrence/Documents/Saved From Backup/Codes/USACO/Test Text/nums.txt");
		int totalArea = 0;

		totalArea += (in[0][3] - in[0][1]) * (in[0][2] - in[0][0]);
		totalArea += (in[1][3] - in[1][1]) * (in[1][2] - in[1][0]);

		for (int i = 0; i < 2; i++) {
			if (pointInside(in[i][0], in[i][1], in[i][2], in[i][3], in[2][0], in[2][1])
					&& pointInside(in[i][0], in[i][1], in[i][2], in[i][3], in[2][2], in[2][3])) {
				totalArea -= (in[2][3] - in[2][1]) * (in[2][2] - in[2][0]);
				break;
			}
			boolean done = false;
			// left side
			if (pointInside(in[i][0], in[i][1], in[i][2], in[i][3], in[2][0], in[2][1])
					&& pointInside(in[i][0], in[i][1], in[i][2], in[i][3], in[2][0], in[2][3])) {
				totalArea -= (in[2][3] - in[2][1]) * (in[i][3] - in[2][0]);
				done = true;
			}
			// top side
			if (pointInside(in[i][0], in[i][1], in[i][2], in[i][3], in[2][2], in[2][3])
					&& pointInside(in[i][0], in[i][1], in[i][2], in[i][3], in[2][0], in[2][3])) {
				totalArea -= (in[2][2] - in[2][0]) * (in[2][3] - in[i][1]);
				done = true;
			}
			// right side
			if (pointInside(in[i][0], in[i][1], in[i][2], in[i][3], in[2][2], in[2][3])
					&& pointInside(in[i][0], in[i][1], in[i][2], in[i][3], in[2][2], in[2][1])) {
				totalArea -= (in[2][3] - in[2][1]) * (in[2][2] - in[i][0]);
				done = true;
			}
			// bottom
			if (pointInside(in[i][0], in[i][1], in[i][2], in[i][3], in[2][0], in[2][1])
					&& pointInside(in[i][0], in[i][1], in[i][2], in[i][3], in[2][2], in[2][1])) {
				totalArea -= (in[2][2] - in[2][0]) * (in[i][3] - in[2][1]);
				done = true;
			}
			if (done == false) {
				// bottom left
				if (pointInside(in[i][0], in[i][1], in[i][2], in[i][3], in[2][0], in[2][1])) {
					totalArea -= (in[i][3] - in[2][1]) * (in[i][2] - in[2][0]);
				}
				// bottom right
				if (pointInside(in[i][0], in[i][1], in[i][2], in[i][3], in[2][2], in[2][1])) {
					totalArea -= (in[2][3] - in[i][1]) * (in[2][2] - in[i][0] );
				}
				// right top
				if (pointInside(in[i][0], in[i][1], in[i][2], in[i][3], in[2][2], in[2][3])) {
					totalArea -= (in[2][2] - in[i][0]) * (in[2][3] - in[i][1]);
				}
				// left top
				if (pointInside(in[i][0], in[i][1], in[i][2], in[i][3], in[2][0], in[2][3])) {
					totalArea -= (in[2][3] - in[i][1]) * (in[i][2] - in[2][0]);
				}
			}

			if (pointInside(in[2][0], in[2][1], in[2][2], in[2][3], in[i][0], in[i][1])
					&& pointInside(in[2][0], in[2][1], in[2][2], in[2][3], in[i][2], in[i][3])) {
				totalArea -= (in[i][3] - in[i][1]) * (in[i][2] - in[i][0]);
				continue;
			}

			done = false;
			// left side
			if (pointInside(in[2][0], in[2][1], in[2][2], in[2][3], in[i][0], in[i][1])
					&& pointInside(in[2][0], in[2][1], in[2][2], in[2][3], in[i][0], in[i][3])) {
				totalArea -= (in[i][3] - in[i][1]) * (in[2][3] - in[i][0]);
				done = true;
			}
			// top side
			if (pointInside(in[2][0], in[2][1], in[2][2], in[2][3], in[i][2], in[i][3])
					&& pointInside(in[2][0], in[2][1], in[2][2], in[2][3], in[i][0], in[i][3])) {
				totalArea -= (in[i][2] - in[i][0]) * (in[i][3] - in[2][1]);
				done = true;
			}
			// right side
			if (pointInside(in[2][0], in[2][1], in[2][2], in[2][3], in[i][2], in[i][3])
					&& pointInside(in[2][0], in[2][1], in[2][2], in[2][3], in[i][2], in[i][1])) {
				totalArea -= (in[i][3] - in[i][1]) * (in[i][2] - in[2][0]);
				done = true;
			}
			// bottom
			if (pointInside(in[2][0], in[2][1], in[2][2], in[2][3], in[i][0], in[i][1])
					&& pointInside(in[2][0], in[2][1], in[2][2], in[2][3], in[i][2], in[i][1])) {
				totalArea -= (in[i][2] - in[i][0]) * (in[2][3] - in[i][1]);
				done = true;
			}

		}
		System.out.println(totalArea);
		writeToFile(Integer.toString(totalArea), "billboard.out");
	}

	public static boolean pointInside(int billx, int billy, int billx2, int billy2, int pointx, int pointy) {
		if (pointx > billx && pointx < billx2 && pointy < billy2 && pointy > billy) {
			return true;
		}
		return false;

	}

	public static void writeToFile(String textLine, String path) throws IOException {
		FileWriter write = new FileWriter(path, true);
		BufferedWriter bw = new BufferedWriter(write);
		bw.write(textLine);
		bw.newLine();
		bw.close();
		write.close();
	}

	public static int[][] doIt(String path) throws IOException {
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
		int[][] output = new int[textData.length][];
		for (int z = 0; z < output.length; z++) {
			textData[z] = textData[z] + " ";
			int startNum = -1;
			int outCounter = 0;

			int spaceCounter = 0;
			for (int y = 0; y < textData[z].length(); y++) {
				if (textData[z].charAt(y) == ' ') {
					spaceCounter++;
				}
			}
			output[z] = new int[spaceCounter];

			for (int p = 0; p < textData[z].length(); p++) {

				if (textData[z].charAt(p) != ' ' && startNum == -1) {
					startNum = p;
				} else if (textData[z].charAt(p) == ' ' && startNum != -1) {
					output[z][outCounter] = Integer.parseInt(textData[z].substring(startNum, p));
					outCounter++;
					startNum = -1;
				}
			}

		}
		return output;
	}
}
