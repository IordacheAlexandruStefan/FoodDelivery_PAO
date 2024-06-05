package Servicii;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager<T> {
    private static FileManager instance = null;

    private FileManager() {}

    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

    public List<T> readFromFile(String fileName, CSVParser<T> parser) {
        List<T> items = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                T item = parser.parse(line);
                if (item != null) {
                    items.add(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    public void writeToFile(String fileName, List<T> items, CSVFormatter<T> formatter) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (T item : items) {
                pw.println(formatter.format(item));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface CSVParser<T> {
        T parse(String csvLine);
    }

    public interface CSVFormatter<T> {
        String format(T item);
    }
}

