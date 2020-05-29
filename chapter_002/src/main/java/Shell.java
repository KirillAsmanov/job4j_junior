import java.util.ArrayList;
import java.util.List;

/**
 * Mini CMD
 * @author Kirill Asmanov
 * @since 28.05.2020
 */
class Shell {
    /**
     * Contains name of opened folders/files
     */
    private List<String> fileNames = new ArrayList<>();

    /**
     * Returns instance of this class after transition on input path
     * @param path path
     * @return instance
     */
    public Shell cd(final String path) {
        if (!commandRun(path)) {
            splitPath(path);
        }
        return this;
    }

    /**
     * If input is command than runs it
     * @param input input string
     * @return true if command runs successfully
     */
    private boolean commandRun(String input) {
        if (input.equals("..")) {
            fileNames.remove(fileNames.size() - 1);
            return true;
        } else if (input.startsWith("//") && input.endsWith("///")) {
            fileNames = new ArrayList<>();
            fileNames.add(input.replaceAll("/", ""));
            return true;
        } else if (input.equals("/") || input.equals("./")) {
            return true;
        }
        return false;
    }

    /**
     * Splits input path on "/"
     * @param input input path
     */
    private void splitPath(String input) {
        String[] paths = input.split("/");
        for (String s : paths) {
            if (!commandRun(s)) {
                fileNames.add(s);
            }
        }
    }

    /**
     * Returns path in required format
     * @return formatted path
     */
    public String path() {
        StringBuilder result = new StringBuilder("/");
        if (fileNames.isEmpty()) {
            return result.toString();
        }
        for (int i = 0; i < fileNames.size(); i++) {
            result.append(fileNames.get(i));
            if (i < fileNames.size() - 1) {
                result.append("/");
            }
        }
        return result.toString();
    }
}
