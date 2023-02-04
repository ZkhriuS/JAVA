package seminar5;

import java.io.*;
import java.util.*;

public class Hometask {
    // Реализуйте структуру телефонной книги с помощью HashMap, учитывая, что 1 человек может иметь несколько телефонов.
    // Пусть дан список сотрудников:Иван Иванов (и остальные)
    // Написать программу, которая найдет и выведет повторяющиеся имена с количеством повторений.
    // Отсортировать по убыванию популярности.
    public static void main(final String[] args) {
        String filename = "phonebook.txt";
        HashMap<String, HashMap<String, List<String>>> phonebook = new HashMap<>();
        fillPhonebook(phonebook, readFile(filename));
        printPopular(duplicateNames(phonebook));
    }

    public static List<String> readFile(String filename) {
        File file = new File(filename);
        List<String> result = new ArrayList<>();
        try (BufferedReader fr = new BufferedReader( new FileReader(file))){
            while(fr.ready())
                result.add(fr.readLine());
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("Файл не найден");
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("Ошибка создания ресурса");
        }
        return result;
    }
    public static void fillPhonebook(HashMap<String, HashMap<String, List<String>>> phonebook, List<String> data){
        for (var item: data) {
            String[] info = item.split(" ");
            if(!phonebook.containsKey(info[0])) {
                HashMap<String, List<String>> surnamesPhones = new HashMap<>();
                List<String> phones = new ArrayList<>();
                phones.add(info[2]);
                surnamesPhones.put(info[1], phones);
                phonebook.putIfAbsent(info[0], surnamesPhones);
            }
            else if(!phonebook.get(info[0]).containsKey(info[1])){
                List<String> phones = new ArrayList<>();
                phones.add(info[2]);
                phonebook.get(info[0]).put(info[1], phones);
            }
            else if(!phonebook.get(info[0]).get(info[1]).contains(info[2]))
            {
                phonebook.get(info[0]).get(info[1]).add(info[2]);
            }
        }
    }

    public static NavigableMap<Integer, List<String>> duplicateNames (HashMap<String, HashMap<String, List<String>>> phonebook){
        TreeMap<Integer, List<String>> sortedMap = new TreeMap<>();
        for (var item: phonebook.keySet()) {
            int count = phonebook.get(item).size();
            if(sortedMap.containsKey(count)){
                sortedMap.get(count).add(item);
            }
            else if(count > 1){
                List<String> temp = new ArrayList<>();
                temp.add(item);
                sortedMap.put(count, temp);
            }
        }
        return sortedMap.descendingMap();
    }

    public static void printPopular(NavigableMap<Integer, List<String>> sortedMap){
        for (var count: sortedMap.keySet()) {
            for (String name: sortedMap.get(count)) {
                System.out.println(name + " - " + count);
            }
        }
    }
}
