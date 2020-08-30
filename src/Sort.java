import java.nio.charset.MalformedInputException;
import java.util.Random;

public class Sort {
	public static void main(String[] args) {
		Random random = new Random();
	int[] array = new int[5];
	for(int i = 0; i < array.length; i++) {
		array[i] = random.nextInt(100);
	}
	int[] out = sort(array);
	for(int i = 0; i < out.length; i++) {
		System.out.print(out[i] + " ");
	}
	}
	
    public static int[] sort(int[] array) {
        if(array.length == 1) {
            return array;
        }
        
        int largest = 0;
        for(int i = 0; i < array.length; i++) {
            if(array[i] > array[largest]) {
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
}

