package inputoutput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
/**
 * 1. Читаем файл конфигурации [#281978]
 * @since 14.05.2020
 * @author Kirill Asmanov
 */
public class Config {
    /**
     * Contains address of config file
     */
    private final String path;
    /**
     * Contains pair property-value
     */
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Fill the map of values with the properties. Ignore comments and empty strings
     */
    public void load() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().filter(
                    l -> l != null && !l.startsWith("#") && l.trim().length() != 0
            ).forEach(l -> {
                String[] strings = l.split("=");
                values.put(strings[0], strings[1]);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns value of property from map
     * @param key name of property
     * @return value
     */
    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("./chapter_002/data/app.properties"));
    }
}