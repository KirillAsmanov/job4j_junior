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

        Scanner in = new Scanner(System.in);
        System.out.println("Начните общение, либо введите команды: " + System.lineSeparator()
                + "-  СТОП чтобы бот перестал отвечать," + System.lineSeparator()
                + "-  ПРОДОЛЖИТЬ чтобы бот возобновил общение," + System.lineSeparator()
                + "-  ЗАКОНЧИТЬ чтобы выйти из программы." + System.lineSeparator()
        );
        while (!isStopped) {
            String input = in.nextLine();
            logWrite(input);
            if (!checkIsCommand(input) && !isPaused) {
                String phrase = generatePhrase();
                System.out.println(phrase);
                logWrite(phrase);
            }
            if (checkIsCommand(input)) {
                if (input.equalsIgnoreCase("СТОП")) {
                    String phrase = "Работа приостановлена.";
                    System.out.println(phrase);
                    logWrite(phrase);
                    isPaused = true;
                } else if (input.equalsIgnoreCase("ПРОДОЛЖИТЬ")) {
                    String phrase = "Работа возобновлена.";
                    System.out.println(phrase);
                    logWrite(phrase);
                    isPaused = false;
                } else if (input.equalsIgnoreCase("ЗАКОНЧИТЬ")) {
                    String phrase = "Работа завершена.";
                    System.out.println(phrase);
                    logWrite(phrase);
                    isStopped = true;
                }
            }
        }
    }

    /**
     * Check is string is command or another message
     * @param input string
     * @return true if is command
     */
    private boolean checkIsCommand(String input) {
        String command = input.trim();
        return command.equalsIgnoreCase("СТОП")
                || command.equalsIgnoreCase("ПРОДОЛЖИТЬ")
                || command.equalsIgnoreCase("ЗАКОНЧИТЬ");

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
     * writes log record into log file
     * @param word writable record
     */
    private void logWrite(String word) {
        try {
            File log = new File(target);
            FileWriter fileWriter = new FileWriter(log, true);
            BufferedWriter out = new BufferedWriter(fileWriter);
            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy-kk:mm:ss");
            String record = formatForDateNow.format(dateNow) + " " + word + System.lineSeparator();
            out.write(record);
            out.close();
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
