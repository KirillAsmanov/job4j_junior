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
     * Adds to file info about unavailable server periods
     * @param source address of log file
     * @param target address of target file
     */
    public void unavailable(String source, String target) {
        List<String[]> lines = new ArrayList<>();
        readFromFile(source).forEach(l -> {
            String[] strings = l.split("\\s+");
            lines.add(strings);
        });
        if (lines.isEmpty()) {
            return;
        }
        boolean isAvailable = true;
        StringBuilder unavailableDiapasons = new StringBuilder();
        for (String[] line : lines) {
            if (isAvailable && !isActive(line[0])) {
                isAvailable = isActive(line[0]);
                unavailableDiapasons.append(line[1]).append(";");
            } else if (!isAvailable && isActive(line[0])) {
                isAvailable = isActive(line[0]);
                unavailableDiapasons.append(line[1]).append(System.lineSeparator());
            }
        }
        if (unavailableDiapasons.lastIndexOf(";") == unavailableDiapasons.length() - 1) {
            unavailableDiapasons.append("--:--:--");
        }
        writeToFile(unavailableDiapasons.toString(), target);
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
     * Read log lines into list
     * @param source address of log file
     * @return list of log lines
     */
    private List<String> readFromFile(String source) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            in.lines().filter(l -> {
                try { // проверяем перед записью на всякий случай то, является ли первое "слово" в логе числом
                    Integer.parseInt(l.split("\\s+")[0]);
                } catch (NumberFormatException | NullPointerException e) {
                    return false;
                }
                return true;
            }).forEach(lines::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * Write unavailability report on file
     * @param diapasonString string with report
     * @param target address of report file
     */
    private void writeToFile(String diapasonString, String target) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
                out.write(diapasonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       Analyze an = new Analyze();
       an.unavailable("./chapter_002/data/server.log", "./chapter_002/data/unavailable.csv");
    }
}