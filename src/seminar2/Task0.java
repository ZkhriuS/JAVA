package seminar2;

import java.io.*;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

public class Task0 {
    private static final String FILENAME = "result.txt";
    private static byte[] bytes;
    private static Logger logger = Logger.getLogger(Task0.class.getName());
    private static Handler handler;
    private static XMLFormatter formatter;

    public static void write(String path, byte[] data, boolean append) throws FileNotFoundException, NullPointerException, IOException, SecurityException {
        File file = new File(path);
        OutputStream fileOStream = new BufferedOutputStream(new FileOutputStream(file, append));
        fileOStream.write(data);
        fileOStream.close();
    }

    public static byte[] read(String path) throws NullPointerException, IOException, FileNotFoundException, SecurityException {
        File file = new File(path);
        InputStream fileIStream = new BufferedInputStream(new FileInputStream(file));
        byte[] data = fileIStream.readAllBytes();
        fileIStream.close();
        return data;
    }

    public static Integer menu() {
        Scanner scanner = null;
        try {
            System.out.println("1. Считать файл\n2. Записать байт в файл\n3. Перезаписать файл\n4. Выход");
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

    public static Byte inputByte() {
        Scanner scanner = null;
        try {
            System.out.println("Введите байт");
            scanner = new Scanner(System.in);
            byte data = scanner.nextByte();
            return data;
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

    public static void printBytes() {
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        try {
            handler = new FileHandler("log.txt", true);
            logger.addHandler(handler);
            formatter = new XMLFormatter();
            handler.setFormatter(formatter);
            while (true) {
                switch (menu()) {
                    case 1: {
                        bytes = read(FILENAME);
                        printBytes();
                        logger.info("Файл прочитан");
                    }
                    break;
                    case 2: {
                        bytes = new byte[1];
                        while (true) {
                            Byte value = inputByte();
                            if (value != null) {
                                bytes[0] = value;
                                break;
                            }
                        }
                        write(FILENAME, bytes, true);
                        logger.info("Добавлен байт в файл");
                    }
                    break;
                    case 3: {
                        bytes = new byte[1];
                        while (true) {
                            Byte value = inputByte();
                            if (value != null) {
                                bytes[0] = value;
                                break;
                            }
                        }
                        write(FILENAME, bytes, false);
                        logger.info("Файл перезаписан");
                    } break;
                    case 4: {
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
}
