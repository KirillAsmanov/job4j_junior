package inputoutput.archive;

/**
 * 5.1. Архивировать проект. [#281980]
 * @since 19.05.2020
 * @author Kirill Asmanov
 */
public class ArgZip {

    private final String[] args;
    private String directory;
    private String extension;
    private String output;

    public ArgZip(String[] args) {
        this.args = args;
        valid();
    }

    /**
     * Initiates checks on valid argument values and count
     */
    private void valid() {
        if (args.length == 0) {
            throw new IllegalArgumentException("You should use the arguments. " + System.lineSeparator()
                    + "Usage java -jar pack.jar -d FOLDER_PATH -e *.EXTENSION -o NAME.zip");
        }
        for (int i = 0; i < args.length; i++) {
            if (isKey(args[i])) {
                if ((i == args.length - 1) || (isKey(args[i + 1]) || args[i + 1].isEmpty())) {
                    throw new IllegalStateException("The value after key " + args[i] + " is incorrect."
                            + System.lineSeparator() + "Usage java -jar pack.jar -d FOLDER_PATH -e *.EXTENSION -o NAME.zip");
                }
                if (args[i].equals("-d")) {
                    directory = args[i + 1].trim();
                }
                if (args[i].equals("-e")) {
                    String[] ext = args[i + 1].toLowerCase().trim().split("\\.");
                    if (ext.length != 2) {
                        throw new IllegalStateException("The value after key -e is incorrect. Usage *.EXTENSION format");
                    }
                    extension = ext[1];
                }
                if (args[i].equals("-o")) {
                    if (!args[i + 1].endsWith(".zip")) {
                        throw new IllegalStateException("The value after key -o is incorrect. Usage NAME.zip format");
                    }
                    output = args[i + 1];
                }
            }
        }
    }

    public String directory() {
        return directory;
    }

    public String exclude() {
        return extension;
    }

    public String output() {
        return directory + "\\" + output;
    }

    private boolean isKey(String value) {
        return value.startsWith("-") && value.length() == 2;
    }
}