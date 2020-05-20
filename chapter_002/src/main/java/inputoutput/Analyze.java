package inputoutput;

import java.io.*;
import java.io.FileReader;
import java.util.*;
/**
 * 2. Анализ доступности сервера. [#281979]
 * @since 15.05.2020
 * @author Kirill Asmanov
 */
public class Analyze {
    /**
     * Reads log file and adds to source file info about unavailable server periods
     * @param source address of log file
     * @param target address of target file
     */
    public void createUnavailableLog(String source, String target) {
        var state = new Object() { // Обертка для того, чтобы значение доступности можно было бы использовать в stream
            boolean available = true;
            public boolean isAvailable() {
                return available;
            }
            public void setAvailable(boolean available) {
                this.available = available;
            }
        };
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            in.lines()                          // создаем поток строк из файла лога
            .filter(s -> !s.isBlank())          // убираем пустые строки из потока
            .map(s -> s.split("\\s+"))    // приводим его к виду массива слов
            .filter(s -> {                      // убираем строки, не содержащие в первом слове кода ошибки
                try {
                    Integer.parseInt(s[0]);
                } catch (NumberFormatException | NullPointerException e) {
                    return false;
                }
                return true;
            }).map(s -> {                        // заполняем только те строки, которые являются контрольными точками
                String result = "";
                if (state.isAvailable() && !isActive(s[0])) {
                    state.setAvailable(isActive(s[0]));
                    result = s[1] + ";";
                } else if (!state.isAvailable() && isActive(s[0])) {
                    state.setAvailable(isActive(s[0]));
                    result = s[1] + System.lineSeparator();
                }
                return result;
            }).filter(s -> !s.isBlank())           // убираем пустые строки, не являющиеся контрольными точками
            .forEach(s -> writeToFile(s, target)); // записывем все элементы потока в файл

            if (!state.isAvailable()) {
                writeToFile("--:--:--", target);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks activity of server
     * @param logLine line with state report
     * @return true if available
     */
    private boolean isActive(String logLine) {
        int lineCode = Integer.parseInt(logLine);
        return !(400 <= lineCode) || !(lineCode <= 500);
    }

    /**
     * Write unavailability report on file
     * @param diapasonString string with report
     * @param target address of report file
     */
    private void writeToFile(String diapasonString, String target) {
        try {
            File log = new File(target);
            FileWriter fileWriter = new FileWriter(log, true);
            BufferedWriter out = new BufferedWriter(fileWriter);

            out.write(diapasonString);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       Analyze an = new Analyze();
       an.createUnavailableLog("./chapter_002/data/server.log", "./chapter_002/data/unavailable.csv");
    }
}