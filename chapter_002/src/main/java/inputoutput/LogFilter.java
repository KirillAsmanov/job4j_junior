package inputoutput;

import java.io.BufferedReader;
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

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        for (String s : log) {
            System.out.println(s);
        }
    }
}
