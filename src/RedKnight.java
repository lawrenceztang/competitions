import java.util.Scanner;


public class RedKnight {

	final static int L = 6;
	final static int LL = 5;
	final static int LR = 4;
	final static int R = 3;
	final static int UR = 2;
	final static int UL = 1;
	static int boardSize;
	static int iEnd;
	static int jEnd;

	static void printShortestPath(int n, int i_start, int j_start, int i_end, int j_end) {
		boardSize = n;
		iEnd = i_end;
		jEnd = j_end;

		int[] output = solve(i_end, j_start);
		
		for(int i = 0; i < output.length; i++) {
			if(output[i] == L) {
				System.out.print(L  + " ");
			}
			if(output[i] == UL) {
				System.out.print(UL  + " ");
			}
			if(output[i] == UR) {
				System.out.print(UR  + " ");
			}
			if(output[i] == R) {
				System.out.print(R  + " ");
			}
			if(output[i] == LR) {
				System.out.print(LR  + " ");
			}
			if(output[i] == LL) {
				System.out.print(LL + " ");
			}
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int i_start = in.nextInt();
		int j_start = in.nextInt();
		int i_end = in.nextInt();
		int j_end = in.nextInt();
		printShortestPath(n, i_start, j_start, i_end, j_end);
		in.close();
	}

	public static int[] solve(int iStartIn, int jStartIn) {

		int best[] = new int[0];
		for (int moveType = 1; moveType < 8; moveType++) {
			int jStart = jStartIn;
			int iStart = iStartIn;
			if (moveType == UL) {
				iStart += -2;
				jStart += -1;
			}
			if (moveType == UR) {
				iStart += -2;
				jStart += 1;
			}
			if (moveType == R) {
				jStart += 2;
			}
			if (moveType == LR) {
				iStart += -2;
				jStart += 2;
			}
			if (moveType == LL) {
				iStart += -1;
				jStart += 2;
			}
			if (moveType == L) {
				iStart += -2;
			}

			if (iStart < 0 || jStart < 0 || iStart > boardSize - 1 || jStart > boardSize - 1) {

			} else if (iStart == iEnd && jStart == jEnd) {
				int[] output = new int[moveType];
				return output;
			} else {
				int[] temp = solve(iStart, jStart);
				if (temp != null) {
					int[] output = new int[temp.length + 1];
					output[0] = moveType;
					for (int i = 0; i < temp.length; i++) {
						output[i + 1] = temp[i];
						if (output.length < best.length) {
							best = output;
						}
					}
				}
			}
		}

		return best;
	}

}
