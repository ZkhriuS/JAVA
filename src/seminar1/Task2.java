package seminar1;

import java.util.Arrays;
import java.util.Scanner;

public class Task2 {
    private static int[] array;
    public static void createArray(int size){
        array = new int[size];
        for (int i=0; i<size; i++){
            array[i] = (int) (Math.random()*300);
        }
    }
    public static void printArray(){
        System.out.println(Arrays.toString(array));
    }
    public static void bubbleSort(){
        for(int j = 0; j< array.length - 1; j++) {
            for (int i = j; i < array.length; i++) {
                if (array[j] > array[i]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.print("Введите размер массива: ");
        Scanner scanner = new Scanner(System.in);
        createArray(scanner.nextInt());
        printArray();
        bubbleSort();
        printArray();
    }
}
