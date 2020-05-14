package inputoutput;

import java.io.FileInputStream;

/**
 * 0.2. FileInputStream [#281982]
 * @since 14.05.2020
 * @author Kirill Asmanov
 */
public class FileReader {
    /**
     * Read values from file
     * @return String[] splited by lineSeparator
     */
    public String[] readNumberFile() {
        StringBuilder sb = new StringBuilder();
        try (FileInputStream in = new FileInputStream("input.txt")) {
            int read;
            while ((read = in.read()) != -1) {
                sb.append((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString().split(System.lineSeparator());
    }

    /**
     * Print on console are the value even or odd
     * @param lines values, readed from file
     */
    public void printEvenOdd(String[] lines) {
        for (String s : lines) {
            if (Integer.parseInt(s) % 2 == 0) {
                System.out.println(s + " - четное");
            } else {
                System.out.println(s + " - нечетное");
            }
        }
    }

    public static void main(String[] args) {
        FileReader reader = new FileReader();
        reader.printEvenOdd(reader.readNumberFile());
    }
}
