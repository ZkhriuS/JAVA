package seminar1;

import java.util.Arrays;
import java.util.Scanner;

public class Task1 {
    private static int max, min;
    private static double average;
    private static int[] array;
    public static void createArray(int size){
        array = new int[size];
        for (int i=0; i<size; i++){
            array[i] = (int) (Math.random()*300);
        }
    }
    public static int findMax(){
        max = array[0];
        for (int i=1; i< array.length; i++){
            if(array[i]>max)
                max = array[i];
        }
        return max;
    }
    public static int findMin(){
        min = array[0];
        for (int i=1; i< array.length; i++){
            if(array[i]<min)
                min = array[i];
        }
        return min;
    }
    public static double findAvg(){
        double sum = 0;
        for (int i=0; i< array.length; i++){
            sum += array[i];
        }
        average = sum/ array.length;
        return average;
    }
    public static void arrayInfo(){
        System.out.println(Arrays.toString(array));
        System.out.println("Max: " + findMax() + "\nMin: " + findMin() + "\nAverage: " + findAvg());
    }
    public static void main(String[] args) {
        System.out.print("Введите размер массива: ");
        Scanner scanner = new Scanner(System.in);
        createArray(scanner.nextInt());
        arrayInfo();
    }
}
