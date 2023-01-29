package seminar3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Homework {

    // Пусть дан произвольный список целых чисел
    public static void main(String[] args) {
        int size = 15;
        int lowBoundary = 0, highBoundary = 300;
        List<Integer> srcList = new ArrayList<>();
        for(int i =0; i<size; i++){
            srcList.add((int) (Math.random()*(highBoundary-lowBoundary)+lowBoundary));
        }
        System.out.println("Исходный список: "+srcList);
        System.out.println("Список нечетных чисел: "+removeEvenValue(srcList));
        System.out.println("Минимум: "+getMin(srcList));
        System.out.println("Максимум: "+getMax(srcList));
        System.out.println("Среднее: "+getAverage(srcList));

    }

    // Нужно удалить из него четные числа
    public static List<Integer> removeEvenValue(List<Integer> list){
        Iterator<Integer> iterator = list.iterator();
        List<Integer> modifiedList = new ArrayList<>(list);
        while (iterator.hasNext()) {
            Integer value = iterator.next();
            if (value % 2 == 0) {
                modifiedList.remove(value);
            }
        }
        return modifiedList;
    }

    // Найти минимальное значение
    public static Integer getMin(List<Integer> list){
        Collections.sort(list);
        return list.get(0);
    }

    // Найти максимальное значение
    public static Integer getMax(List<Integer> list){
        Collections.sort(list);
        return list.get(list.size()-1);
    }

    // Найти среднее значение
    public static Integer getAverage(List<Integer> list){
        double sum = 0;
        for(Integer value : list){
            sum+=value;
        }
        return (int) (sum/list.size());
    }
}