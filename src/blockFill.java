import java.util.Arrays;

public class blockFill {

    public static void main(String[] args) {
//        String[] families = new String[]{"yak","newt","aardvark","zebra"};
////        int[] familySizes = new int[]{1,5,4,2};
////        String[] stalls = new String[1];
////        Arrays.fill(stalls, null);
////        System.out.println(fillFamilies(stalls, families, familySizes));

        int[] uv = new int[]{2,3,4,5,7,9,12,12,12,10,8,6,3,1,1,0,0,0};
        int[] bessie = new int[]{72, -46};
        System.out.println(avgUV(uv, bessie));
    }

    static int fillFamilies(String[] stalls, String[] families, int[] familySizes) {

        boolean left = true;
        int leftCounter = 0;
        int rightCounter = 0;
        for(int i = 0; i < families.length; i++) {
            if(left) {
                if(leftCounter + familySizes[i] - 1 < stalls.length && stalls[leftCounter + familySizes[i] - 1] == null) {
                    for(int j = 0; j < familySizes[i]; j++) {
                        stalls[leftCounter + j] = families[i];
                    }
                }
                else {
                    return i;
                }
                leftCounter += familySizes[i];
            }
            else {
                if(stalls.length - 1 - rightCounter - familySizes[i] - 1 >= 0 && stalls[stalls.length - 1 - rightCounter - familySizes[i] - 1] == null) {
                    for(int j = 0; j < familySizes[i]; j++) {
                        stalls[stalls.length - 1 - rightCounter - j] = families[i];
                    }
                }
                else {
                    return i;
                }
                rightCounter += familySizes[i];
            }

            left = !left;
        }
        return -1;

    }

    static double avgUV(int[] uvByLatitude, int[] bessieLatitudes) {
        int sum = 0;
        for(int i = 0; i < bessieLatitudes.length; i++) {
            sum += uvByLatitude[(bessieLatitudes[i] + 90) / 10];
        }
        return sum / bessieLatitudes.length;
    }
}
