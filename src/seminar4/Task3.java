package seminar4;

import java.util.ArrayDeque;
import java.util.Deque;

public class Task3 {

//Дана строка содержащая только символы '(', ')', '{', '}', '[' и ']', определите,
// является ли входная строка логически правильной.
// Входная строка логически правильная, если:
// 1) Открытые скобки должны быть закрыты скобками того же типа.
// 2) Открытые скобки должны быть закрыты в правильном порядке. Каждая закрывающая скобка имеет соответствующую
// открытую скобку того же типа.
// ()[] = true
// () = true
// {[()]} = true
// ()() = true
// )()( = false

    public static void main(String[] args) {
        String src = "{[{[[]()]}{()}]()()}";
        System.out.println(src + " - " + validate(src));
    }

    public static boolean validate(String srcString) {
        String[] brackets = {"({[", ")}]"};
        Deque<Character> deque = new ArrayDeque<>();
        for (char item : srcString.toCharArray()) {
            int openPos = brackets[0].indexOf(String.valueOf(item));
            int closePos = brackets[1].indexOf(String.valueOf(item));
            if (openPos != -1) {
                deque.add(item);
            }
            if (closePos != -1) {
                if (!deque.isEmpty() && brackets[0].indexOf(deque.getLast()) == closePos) {
                    deque.removeLast();
                    System.out.println(deque);
                } else
                    return false;
            }
        }
        return deque.isEmpty();
    }
}
