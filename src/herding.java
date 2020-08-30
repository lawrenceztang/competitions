import java.util.*;
import java.io.*;

public class herding {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("herding.in"));
        int n = in.nextInt();
        ArrayList<Chunk> chunks = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int temp = in.nextInt();
            chunks.add(new Chunk(temp, temp + 1));
        }

        //move closest to middle is best
        int count = 0;
        while(chunks.size() > 1) {
            mergeChunks(chunks);

            boolean decreaseEnd = false;
            int moveIndex = -1;
            boolean moveToLeft = false;
            chunks.get(chunks.size() - 1).end--;
            double middle;
            if(chunks.get(chunks.size() - 1).end > chunks.get(chunks.size() - 1).start) {
                middle = (chunks.get(0).start + chunks.get(chunks.size() - 1).end) / 2;
            }
            else {
                middle = (chunks.get(0).start + chunks.get(chunks.size() - 2).end) / 2;
            }
            double minDistanceFromMiddle = Integer.MAX_VALUE;
            for(int i = 0; i < chunks.size(); i++) {
                if(chunks.get(i).start <= middle && chunks.get(i).end > middle) {
                    if(middle - chunks.get(i).start < chunks.get(i).end - middle) {
                        moveToLeft = true;
                    }
                    else {
                        moveToLeft = false;
                    }
                    moveIndex = i;
                    minDistanceFromMiddle = Math.min(middle - chunks.get(i).start, chunks.get(i).end - middle);
                    decreaseEnd = true;
                    break;
                }
            }
            if(minDistanceFromMiddle == Integer.MAX_VALUE) {
                decreaseEnd = true;
                minDistanceFromMiddle = middle % 1;
                moveIndex = -1;
            }

            double oldMiddle = middle;
            chunks.get(chunks.size() - 1).end++;
            chunks.get(0).start++;
            if(chunks.get(0).start < chunks.get(0).end) {
                middle = (chunks.get(0).start + chunks.get(chunks.size() - 1).end) / 2;
            }
            else {
                middle = (chunks.get(1).start + chunks.get(chunks.size() - 1).end) / 2;
            }
            boolean done = false;
            for(int i = 0; i < chunks.size(); i++) {
                if(chunks.get(i).start < middle && chunks.get(i).end > middle) {
                    double temp = Math.min(middle - chunks.get(i).start, chunks.get(i).end - middle);
                    if(temp < minDistanceFromMiddle) {
                        minDistanceFromMiddle = temp;
                        if(middle - chunks.get(i).start < chunks.get(i).end - middle) {
                            moveToLeft = true;
                        }
                        else {
                            moveToLeft = false;
                        }
                        moveIndex = i;
                        decreaseEnd = false;
                    }
                    done = true;
                    break;
                }
            }
            if(!done) {
                minDistanceFromMiddle = Math.min(minDistanceFromMiddle, middle % 1);
                if(middle % 1 == minDistanceFromMiddle) {
                    decreaseEnd = false;
                    moveIndex = -1;
                }
            }
            if(decreaseEnd) {
                chunks.get(chunks.size() - 1).end--;
                if(chunks.get(chunks.size() - 1).start == chunks.get(chunks.size() - 1).end) {
                    chunks.remove(chunks.size() - 1);
                }
            }
            else {
                chunks.get(0).start++;
                if(chunks.get(0).start == chunks.get(0).end) {
                    chunks.remove(0);
                }
            }
            if(moveIndex == -1) {
                if(decreaseEnd) {
                    chunks.add(new Chunk((int)oldMiddle, (int)oldMiddle + 1));
                }
                else {
                    chunks.add(new Chunk((int)middle, (int)middle + 1));
                }
            }
            else if(moveToLeft) {
                chunks.get(moveIndex).start++;
            }
            else {
                chunks.get(moveIndex).end++;
            }
            count++;
        }

        int sum = count;
        PrintWriter out = new PrintWriter(new File("herding.out"));
        out.println(sum);
        out.close();
    }

    public static class Chunk {
        public int start;
        public int end;


        public Chunk (int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getSize() {
            return end - start;
        }
    }

    public static void mergeChunks(ArrayList<Chunk> chunks) {
        Collections.sort(chunks, new ChunkComparator());
        for(int i = 0; i < chunks.size() - 1; i++) {
            if(chunks.get(i).end == chunks.get(i + 1).start) {
                chunks.get(i).end = chunks.get(i + 1).end;
                chunks.remove(i + 1);
                i--;
            }
        }
    }

    static class ChunkComparator implements Comparator<Chunk> {
        @Override
        public int compare(Chunk o1, Chunk o2) {
            return o1.start - o2.start;
        }
    }
}