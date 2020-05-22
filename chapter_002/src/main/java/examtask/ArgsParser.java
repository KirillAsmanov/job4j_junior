package examtask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArgsParser {
    private final String[] args;
    private final static List<String> MASK_CONSTANTS = List.of("-m", "-f", "-r");
    private HashMap<String, String> argsValues = new HashMap<>();

    public ArgsParser(String[] args) {
        this.args = args;
        valid();
    }

    /**
     * Initiates checks on valid argument values and count
     */
    private void valid() {
        if (args.length == 0) {
            throw new IllegalArgumentException("You should use the arguments. " + System.lineSeparator()
                    + "Usage java -jar find.jar -d FOLDER_PATH -n SEARCH_VALUE -m/f/r as filter -o OUTPUT_NAME");
        }
        for (int i = 0; i < args.length; i++) {
            if (isKey(args[i])) {
                if (MASK_CONSTANTS.contains(args[i])) {
                    argsValues.put("-m/f/r", args[i]);
                } else {
                    if ((i == args.length - 1) || (isKey(args[i + 1]) || args[i + 1].isEmpty())) {
                        throw new IllegalStateException("The value after key " + args[i] + " is incorrect."
                                + System.lineSeparator()
                                + "Usage java -jar find.jar -d FOLDER_PATH -n SEARCH_MASK -m/f/r as filter -o OUTPUT_NAME");
                    }
                    argsValues.put(args[i], args[i + 1]);
                }
            }
        }
    }

    public String detDirectoryPath() {
        return argsValues.get("-d");
    }

    public String getSearchMask() {
        return argsValues.get("-n");
    }

    public String getFilter() {
        String command = argsValues.get("-m/f/r");
        if (command == null) {
            return "-m";
        }
        return command;
    }

    public String getOutputName() {
        return argsValues.get("-o");
    }


    private boolean isKey(String value) {
        return value.startsWith("-") && value.length() == 2;
    }
}
