package seminar6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

public class FilterObject {
    private String[] params;
    private boolean ramFilter;
    private boolean romFilter;
    private boolean osFilter;
    private boolean colorFilter;
    private char compareBoundary;
    public FilterObject(){
        params = new String[]{"ram", "rom", "os", "color"};
        ramFilter = false;
        romFilter = false;
        osFilter = false;
        colorFilter = false;
        compareBoundary = '=';
    }
    public void setColorFilter(boolean colorFilter) {
        this.colorFilter = colorFilter;
        ramFilter = false;
        romFilter = false;
        osFilter = false;
    }

    public void setOsFilter(boolean osFilter) {
        this.osFilter = osFilter;
        ramFilter = false;
        romFilter = false;
        colorFilter = false;
    }

    public void setRamFilter(boolean ramFilter) {
        this.ramFilter = ramFilter;
        colorFilter = false;
        romFilter = false;
        osFilter = false;
    }

    public void setRomFilter(boolean romFilter) {
        this.romFilter = romFilter;
        ramFilter = false;
        colorFilter = false;
        osFilter = false;
    }

    public void setCompareBoundary(char compareBoundary) {
        this.compareBoundary = compareBoundary;
    }

    public boolean isOsFilter() {
        return osFilter;
    }

    public boolean isColorFilter() {
        return colorFilter;
    }

    public boolean isRamFilter() {
        return ramFilter;
    }

    public boolean isRomFilter() {
        return romFilter;
    }

    public char getCompareBoundary() {
        return compareBoundary;
    }

    public String[] getParams(){
        return params;
    }

    public int setBoundary(int min, int max){
        boolean back = false;
        int boundary = -1;
        while(!back){
            System.out.println("Введите число: ");
            Scanner scanner = new Scanner(System.in);
            if(scanner.hasNextInt())
                boundary = scanner.nextInt();
            if(boundary < min || boundary > max)
                System.out.println("Задаваемый параметр находится за пределами диапазона: ["+min+"; "+max+"]");
            else return boundary;
            back = !repeat("Задать границу диапазона заново?");
        }
        return -1;
    }
    public boolean repeat(String message){
        Set<String> answers = new HashSet<>(Arrays.asList("да", "нет"));
        String answer = "нет";
        try {
            do {
                if (!answers.contains(answer))
                    System.out.println("Введите " + Arrays.toString(answers.toArray()) + "!");
                System.out.println(message);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                answer = bufferedReader.readLine();
                answer = answer.toLowerCase(Locale.ROOT);
            }while(!answers.contains(answer));
            return answer.equals("да");
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Ошибка в потоке считывания!");
        }
        return false;
    }
    public int chooseParam(String param){
        for(int i=0; i<params.length; i++){
            if(param.equals(params[i]))
                return i;
        }
        return -1;
    }
    public List<String> setValues(Set<String> set){
        List<String> values = new ArrayList<>();
        boolean back = false;
        try {
            int count = set.size();
            while (!back) {
                System.out.println("Выберите категорию:");
                int i = 1;
                for (var value : set) {
                    System.out.println(i++ + ". " + value);
                }
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String answer = bufferedReader.readLine();
                if(set.contains(answer)) {
                    values.add(answer);
                    set.remove(answer);
                } else{
                    System.out.println("Категория отсутствует");
                }
                if(values.size() < count)
                    back = repeat("Завершить выбор?");
                else back = true;
            }
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Ошибка в потоке считывания!");
        }
        return values;
    }
}
