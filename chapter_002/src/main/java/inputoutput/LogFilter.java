package inputoutput;

import java.io.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
/**
 * 0.3. BufferedReader. [#281975]
 * @since 14.05.2020
 * @author Kirill Asmanov
 */
public class LogFilter {
    /**
     * Read file and returns list of rows, where penultimate value is "404"
     * @param file file with logs
     * @return list of rows
     */
    public static List<String> filter(String file) {
        List<String> lines = new ArrayList<String>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().filter(l -> {
                String[] strings = l.split("\\s+");
                return strings[strings.length - 2].equals("404");
            }).forEach(lines::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * Write strings in list into file
     * @param log list of strings
     * @param file name of file to output
     */
    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String s : log) {
                out.write(s + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        save(filter("log.txt"), "404.txt");
    }
}
