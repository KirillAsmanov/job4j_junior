package inputoutput.chat;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * 6. Кодировка. [#281981]
 * @since 20.05.2020
 * @author Kirill Asmanov
 */
public class ConsoleChat {
    private final List<String> wordPool;
    private final String source;
    private final String target;

    private enum Commands {
        STOP,
        CONTINUE,
        CLOSE;
    }

    public ConsoleChat(String source, String target) {
        this.source = source;
        this.target = target;
        this.wordPool = fileRead();
    }

    /**
     * Runs program
     */
    public void run() {
        boolean isStopped = false;
        boolean isPaused = false;

        List<String> logs = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Начните общение, либо введите команды: " + System.lineSeparator()
                + "-  СТОП чтобы бот перестал отвечать," + System.lineSeparator()
                + "-  ПРОДОЛЖИТЬ чтобы бот возобновил общение," + System.lineSeparator()
                + "-  ЗАКОНЧИТЬ чтобы выйти из программы." + System.lineSeparator()
        );
        while (!isStopped) {
            String input = in.nextLine();
            logs.add(createLogRecord(input));
            if (!checkIsCommand(input) && !isPaused) {
                String phrase = generatePhrase();
                System.out.println(phrase);
                logs.add(createLogRecord(phrase));
            }
            if (checkIsCommand(input)) {
                String phrase = "";
                if (input.equalsIgnoreCase(String.valueOf(Commands.STOP))) {
                    phrase = "Работа приостановлена.";
                    isPaused = true;
                } else if (input.equalsIgnoreCase(String.valueOf(Commands.CONTINUE))) {
                    phrase = "Работа возобновлена.";
                    isPaused = false;
                } else if (input.equalsIgnoreCase(String.valueOf(Commands.CLOSE))) {
                    phrase = "Работа завершена.";
                    isStopped = true;
                }
                System.out.println(phrase);
                logs.add(createLogRecord(phrase));
            }
        }
        logWrite(logs);
    }

    /**
     * Check is string is command or another message
     * @param inputStr string value
     * @return true if is command
     */
    private boolean checkIsCommand(String inputStr) {
        String input = inputStr.trim();

        for (Commands e : Commands.values()) {
            if (input.equalsIgnoreCase(String.valueOf(e))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generates a phrase from three random words from wordPool
     * @return complex phrase
     */
    private String generatePhrase() {
        if (wordPool.size() == 0) {
            throw new IllegalArgumentException("Readable file is empty");
        }
        String firstWord = wordPool.get(randomize(wordPool.size() - 1));
        String secondWord = wordPool.get(randomize(wordPool.size() - 1));
        String thirdWord = wordPool.get(randomize(wordPool.size() - 1));
        return firstWord + " " + secondWord + " " + thirdWord;
    }

    /**
     * Generates random int
     * @param max max border of random int
     * @return random int
     */
    private int randomize(int max) {
        return (int) (Math.random() * max);
    }

    /**
     * Read all words from file and puts in at List<String>
     * @return list of words
     */
    private List<String> fileRead() {
        List<String> wordPool = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            in.lines()
                    .map(l -> l.split("\\s"))
                    .flatMap(Arrays::stream)
                    .filter(s -> !s.isBlank())
                    .map(s -> {
                        if (s.endsWith(".") || s.endsWith(",")) {
                            return s.substring(0, s.length() - 1).toLowerCase();
                        }
                        return s.toLowerCase();
                    })
                    .forEach(wordPool::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wordPool;
    }

    /**
     * Create log record to DATE-TIME MESSAGE format
     * @param message input message
     * @return formatted record
     */
    private String createLogRecord(String message) {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy-kk:mm:ss");
        return formatForDateNow.format(dateNow) + " " + message + System.lineSeparator();
    }

    /**
     * writes log record into log file
     * @param logs list of a writable record
     */
    private void logWrite(List<String> logs) {
        try {
            File logFile = new File(target);
            FileWriter fileWriter = new FileWriter(logFile, true);
            BufferedWriter out = new BufferedWriter(fileWriter);

            for (String record: logs) {
                out.write(record);
            }
            out.close(); // закрываем поток
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat chat = new ConsoleChat("./chapter_002/data/Lorem Ipusum.txt",
                "./chapter_002/data/chatLog.txt");
        chat.run();
    }
}
