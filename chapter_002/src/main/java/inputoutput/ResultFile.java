package inputoutput;

import java.io.FileOutputStream;

public class ResultFile {

    public static void printMultiple() {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int k = 1; k < 10; k++) {
                for (int j = 1; j < 10; j++) {
                    String str = " " + k * j;
                    out.write(str.getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ResultFile.printMultiple();
    }
}
