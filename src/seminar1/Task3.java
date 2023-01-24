package seminar1;

import java.util.Arrays;

public class Task3 {
    private static int[] array;
    private static int size = 0;
    public static void createArray(int start, int end){
        array = new int[end-start];
    }
    public static void printArray(int newLength){
        System.out.println(Arrays.toString(Arrays.copyOf(array, newLength)));
    }
    public static boolean checkDividers(int value, int start){
        for(int i = start; i < value; i++){
            if(value%i==0)
                return false;
        }
        return true;
    }
    public static int setArray(int start, int end){
        int step = 0;
        for (int i=start; i<=end; i++){
            if(checkDividers(i, start))
                array[step++]=i;
        }
        return step;
    }
    public static void main(String[] args) {
        createArray(2, 100);
        printArray(setArray(2, 100));
    }
}
