package cache;

import java.io.*;
import java.util.stream.Collectors;
/**
 * Реализации кеша на SoftReference [#282027]
 * @since 30.08.2020
 * @author Kirill Asmanov
 */
public class TextLoader implements Loader<String> {
    ICache textCache;

    public TextLoader(ICache textCache) {
        this.textCache = textCache;
    }

    /**
     * loads file with entry from system
     * @param name name of file
     * @return entry of file
     * @throws IOException
     */
    private String loadFromFile(String name) throws IOException {
        String result;
        String pathToFile = "./chapter_004/data/" + name;
        File source = new File(pathToFile);
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            result = in.lines().parallel().collect(Collectors.joining("\n"));
        }
        return result;
    }

    /**
     * Load data from cache if entry of file with specified name are in it.
     * Else loads file from system
     * @param name name of file
     * @return entry of file
     * @throws IOException
     */
    @Override
    public String loadData(String name) throws IOException {
        String data = (String) textCache.getFromCache(name);
        if (data == null) {
            data = loadFromFile(name);
            System.out.println("(from file)");
            textCache.saveToCache(name, data);
        }
        return data;
    }

    public static void main(String[] args) throws IOException {
        TextLoader tl = new TextLoader(new TextCache());
        System.out.println(tl.loadData("5.txt"));
        System.out.println(tl.loadData("5.txt"));
        System.out.println(tl.loadData("5.txt"));

    }
}
