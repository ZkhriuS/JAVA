package seminar2;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.*;

public class Task1 {

    private static final String FILENAME = "result.txt";
    private static Logger logger = Logger.getLogger(Task0.class.getName());
    private static Handler handler;
    private static SimpleFormatter formatter;
    // Напишите программу, которая
// 1) по указанной строке URL скачивает файл и возвращает строковое содержимое файла
// 2) изменяет строковое содержимое файла подставляя по маске "%s" ваше имя
// 3) сохраняет файл локально
// 4) читает сохраненный файл и выводит содержимое в логгер
// 5) удаляет сохраненный файл
    public static void main(String[] args) {
        try {
            String fileUrl = "https://raw.githubusercontent.com/aksodar/JSeminar_2/master/src/main/resources/example.txt";
            handler = new FileHandler("log1.txt", true);
            logger.addHandler(handler);
            formatter = new SimpleFormatter();
            handler.setFormatter(formatter);
            while (true) {
                switch (menu()) {
                    case 1: {
                        System.out.println(download(fileUrl, FILENAME));
                        logger.info("Файл загружен");
                    }
                    break;
                    case 2: {
                        System.out.print("Введите имя: ");
                        Scanner scanner = new Scanner(System.in);
                        String name = scanner.nextLine();
                        saveOnLocal(FILENAME, change(name, read(FILENAME)));
                        logger.info("Файл изменен");
                    }
                    break;
                    case 3:{
                        String fileContext = read(FILENAME);
                        System.out.println(fileContext);
                        logger.info(fileContext);
                    }break;
                    case 4:{
                        if(removeFromLocale(FILENAME))
                            logger.info("Файл удален");
                        else
                            logger.info("Файла не существует");
                    }break;
                    case 5: {
                        logger.info("Выход из программы");
                        return;
                    }
                    default:
                        logger.severe("Ошибка выбора пункта меню");
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.severe("Файл не найден");
        } catch (SecurityException e) {
            e.printStackTrace();
            logger.severe("Нет прав доступа к файлу");
        } catch (NullPointerException e) {
            e.printStackTrace();
            logger.severe("Не задан путь к файлу");
        } catch (IOException e) {
            e.printStackTrace();
            logger.severe("Файл не может быть открыт или является директорией");
        }
    }

    public static Integer menu() {
        Scanner scanner = null;
        try {
            System.out.println("1. Загрузить файл\n2. Изменить файл\n3. Считать сохраненный файл\n4. Удалить файл\n5. Выход");
            scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            return choice;
        } catch (InputMismatchException e) {
            e.printStackTrace();
            logger.severe("Данные не являются числом");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            logger.severe("Достигнут конец потока");
        } catch (IllegalStateException e) {
            e.printStackTrace();
            logger.severe("Поток закрыт");
        }
        return null;
    }

    public static String download(String url, String localFilename) {
        try {
            URL website = new URL(url);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(localFilename);
            fos.getChannel().transferFrom(rbc, 0, 1024);
            fos.close();
            logger.info("Файл загружен");
            return read(localFilename);
        } catch(MalformedURLException e){
            e.printStackTrace();
            logger.severe("Протокол не определен");
        } catch(IOException e){
           e.printStackTrace();
            logger.severe("Ошибка в потоке ввода-вывода");
        }
        return "";
    }

    public static String change(String name, String fileContent) {
        return fileContent.replaceAll("%s", name);
    }

    public static String read(String localFilename) throws IOException{
        File file = new File(localFilename);
        FileReader fileReader = new FileReader(file);
        char[] buf = new char[1024];
        int characters = fileReader.read(buf);
        fileReader.close();
        return String.copyValueOf(buf).substring(0, characters);
    }

    public static void saveOnLocal(String fileName, String fileContent) throws IOException{
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(fileContent);
        fileWriter.close();
    }

    public static boolean removeFromLocale(String fileName) throws NullPointerException{
        File file = new File(fileName);
        return file.delete();
    }
}