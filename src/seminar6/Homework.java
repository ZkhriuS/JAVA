package seminar6;

import java.io.*;
import java.util.*;

public class Homework {

    // 1) Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
    // 2) Создать коллекцию ноутбуков.
    // 3) Написать мапу, которая будет содержать критерий (или критерии) фильтрации и выведет
    //      ноутбуки, отвечающие фильтру.
    //      Пример: ОЗУ - Значение, Объем ЖД - Значение, Операционная система - Значение, Цвет - Значение
    // 4) Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям.
    public static void main(String[] args) {
        while(true){
            FilterObject filterObject = new FilterObject();
            List<Notebook> catalog = initListNotebooks();
            Map<String, List<String>> criterias = new HashMap<>();
            if(filterObject.repeat("Задать фильтры для ОЗУ?")){
                ArrayList<Integer> rams = (ArrayList<Integer>) sort("ram", catalog);
                List<String> values = new ArrayList<>();
                values.add(rams.get(0).toString());
                values.add(rams.get(rams.size()-1).toString());
                if(filterObject.repeat("Задать нижнюю границу?"))
                    values.set(0, String.valueOf(filterObject.setBoundary(Integer.parseInt(values.get(0)), Integer.parseInt(values.get(1)))));
                if(filterObject.repeat("Задать верхнюю границу?"))
                    values.set(1, String.valueOf(filterObject.setBoundary(Integer.parseInt(values.get(0)), Integer.parseInt(values.get(1)))));
                criterias.put("ram", values);
            }
            if(filterObject.repeat("Задать фильтры для ЖД?")){
                ArrayList<Integer> roms = (ArrayList<Integer>) sort("rom", catalog);
                List<String> values = new ArrayList<>();
                values.add(roms.get(0).toString());
                values.add(roms.get(roms.size()-1).toString());
                if(filterObject.repeat("Задать нижнюю границу?"))
                    values.set(0, String.valueOf(filterObject.setBoundary(Integer.parseInt(values.get(0)), Integer.parseInt(values.get(1)))));
                if(filterObject.repeat("Задать верхнюю границу?"))
                    values.set(1, String.valueOf(filterObject.setBoundary(Integer.parseInt(values.get(0)), Integer.parseInt(values.get(1)))));
                criterias.put("rom", values);
            }
            if(filterObject.repeat("Задать фильтры для ОС?")){
                Set<String> values = new HashSet<>();
                for (Notebook item: catalog) {
                    values.add(item.getOs());
                }
                criterias.put("os", filterObject.setValues(values));
            }
            if(filterObject.repeat("Задать фильтры для цвета?")){
                Set<String> values = new HashSet<>();
                for (Notebook item: catalog) {
                    values.add(item.getColor());
                }
                criterias.put("color", filterObject.setValues(values));
            }
            System.out.println(filter(criterias, catalog));
            if(filterObject.repeat("Выйти из магазина?"))
                return;
        }
    }

    public static List<Notebook> initListNotebooks(){
        List<Notebook> catalog = new ArrayList<>();
        File file = new File("notebooks.txt");
        try (BufferedReader fr = new BufferedReader( new FileReader(file))){
            while(fr.ready()) {
                String[] data = fr.readLine().split(" ");
                Notebook temp = new Notebook();
                temp.setModelName(data[0]);
                temp.setRam(Integer.parseInt(data[1]));
                temp.setRom(Integer.parseInt(data[2]));
                temp.setOs(data[3]);
                temp.setColor(data[4]);
                catalog.add(temp);
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("Файл не найден");
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("Ошибка создания ресурса");
        }
        return catalog;
    }

    public static List<Notebook> filter(Map<String, List<String>> params, List<Notebook> notebooks){
        for (String paramKey: params.keySet()) {
            List<Notebook> filteredNotebooks = new ArrayList<>(notebooks.size());
            for (Notebook item: notebooks) {
                switch (item.getFilterObject().chooseParam(paramKey)){
                    case 0:
                        if(filterRam(item, params.get(paramKey)))
                                filteredNotebooks.add(item);
                        break;
                    case 1:
                        if(filterRom(item, params.get(paramKey)))
                            filteredNotebooks.add(item);
                        break;
                    case 2:
                        if(filterOs(item, params.get(paramKey)))
                            filteredNotebooks.add(item);
                        break;
                    case 3:
                        if(filterColor(item, params.get(paramKey)))
                            filteredNotebooks.add(item);
                        break;
                }
                item.getFilterObject().setCompareBoundary('.');
            }
            notebooks.retainAll(filteredNotebooks);
        }
        return notebooks;
    }
    public static List<Integer> sort(String param, List<Notebook> notebooks){
        FilterObject filterObject = new FilterObject();
        List<Integer> range = new ArrayList<>(notebooks.size());
        switch(filterObject.chooseParam(param)){
            case 0:
                for (var item: notebooks) {
                    range.add(item.getRam());
                }
                break;
            case 1:
                for (var item: notebooks) {
                    range.add(item.getRom());
                }
                break;
        }
        Collections.sort(range);
        return range;
    }
    public static boolean filterRam(Notebook item, List<String> values){
        Notebook tempMin = new Notebook();
        Notebook tempMax = new Notebook();
        FilterObject filter = item.getFilterObject();
        filter.setRamFilter(true);
        item.setFilterObject(filter);
        tempMin.setRam(Integer.parseInt(values.get(0)));
        tempMax.setRam(Integer.parseInt(values.get(values.size()-1)));
        tempMin.getFilterObject().setCompareBoundary('>');
        tempMax.getFilterObject().setCompareBoundary('<');
        return item.equals(tempMin) && item.equals(tempMax);
    }
    public static boolean filterRom(Notebook item, List<String> values){
        Notebook tempMin = new Notebook();
        Notebook tempMax = new Notebook();
        FilterObject filter = item.getFilterObject();
        filter.setRomFilter(true);
        item.setFilterObject(filter);
        tempMin.setRom(Integer.parseInt(values.get(0)));
        tempMax.setRom(Integer.parseInt(values.get(values.size()-1)));
        tempMin.getFilterObject().setCompareBoundary('>');
        tempMax.getFilterObject().setCompareBoundary('<');
        return item.equals(tempMin) && item.equals(tempMax);
    }
    public static boolean filterOs(Notebook item, List<String> values){
        boolean result = false;
        FilterObject filter = item.getFilterObject();
        filter.setOsFilter(true);
        item.setFilterObject(filter);
        for (String os: values) {
            Notebook temp = new Notebook();
            temp.getFilterObject().setCompareBoundary('=');
            temp.setOs(os);
            result =  result || item.equals(temp);
        }
        return result;
    }
    public static boolean filterColor(Notebook item, List<String> values){
        boolean result = false;
        FilterObject filter = item.getFilterObject();
        filter.setColorFilter(true);
        item.setFilterObject(filter);
        for (String color: values) {
            Notebook temp = new Notebook();
            temp.getFilterObject().setCompareBoundary('=');
            temp.setColor(color);
            result =  result || item.equals(temp);
        }
        return result;
    }
}
