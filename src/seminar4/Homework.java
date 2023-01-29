package seminar4;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Homework {

    //Даны два Deque представляющие два целых числа. Цифры хранятся в обратном порядке,
    // и каждый из их узлов содержит одну цифру.
    public static void main(String[] args) {
        Homework hw = new Homework();
        System.out.println(hw.multiple(new ArrayDeque<>(Arrays.asList(5,2)), new ArrayDeque<>(Arrays.asList(4))));
        // result [0,0,1]
        System.out.println(hw.sum(new ArrayDeque<>(Arrays.asList(5,-2)), new ArrayDeque<>(Arrays.asList(5))));
        // result [0,-2]
        System.out.println(hw.multiple(new ArrayDeque<>(Arrays.asList(4, 7, 8)), new ArrayDeque<>(Arrays.asList(3,-2))));
        System.out.println(hw.sum(new ArrayDeque<>(Arrays.asList(9, 1, 5, -9)), new ArrayDeque<>(Arrays.asList(0, 6))));
    }

    // Умножьте два числа и верните произведение в виде связанного списка.
    public Deque<Integer> multiple(Deque<Integer> d1, Deque<Integer> d2){
        return toDeque(toInt(d1) * toInt(d2));
    }

    // Сложите два числа и верните сумму в виде связанного списка. Одно или два числа должны быть отрицательными
    public Deque<Integer> sum(Deque<Integer> d1, Deque<Integer> d2){
        return toDeque(toInt(d1) + toInt(d2));
    }
    public int toInt(Deque<Integer> deque){
        int value = 0;
        int sign = (deque.getLast() > 0)? 1 : -1;
        while(!deque.isEmpty()){
            value = value*10 + Math.abs(deque.removeLast());
        }
        return value*sign;
    }

    public ArrayDeque<Integer> toDeque (int value){
        if (value == 0) return new ArrayDeque<>(List.of(0));
        int sign = (value > 0)? 1 : -1;
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        while (Math.abs(value) > 0){
            arrayDeque.add(Math.abs(value) % 10);
            value /= 10;
        }
        arrayDeque.addLast(arrayDeque.removeLast()*sign);
        return arrayDeque;
    }
}
