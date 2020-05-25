package examtask;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Find {
    private final String directory;
    private final String searchMask;
    private final String filter;
    private final String output;

    public Find(ArgsParser argsParser) {
        this.directory = argsParser.detDirectoryPath();
        this.searchMask = argsParser.getSearchMask();
        this.filter = argsParser.getFilter();
        this.output = argsParser.getOutputName();
    }

    /**
     * Founds files with input extension in directory
     * @return list of found files
     * @throws IOException
     */
    public List<Path> search() throws IOException {
        Path root = Paths.get(directory);
        if (!Files.exists(root)) {
            throw new IllegalArgumentException(String.format("Cannot find %s", root.toString()));
        }
        if (!Files.isDirectory(root)) {
            throw new IllegalArgumentException(String.format("Not directory %s", root.toString()));
        }
        SearchFiles fileVisitor = new SearchFiles(setPredicate());
        Files.walkFileTree(root, fileVisitor);
        return fileVisitor.getFoundFiles();
    }

    /**
     * Write list of paths to file
     * @param paths found paths
     */
    public void writeToFile(List<Path> paths) {
        try (FileWriter fileWriter = new FileWriter(new File(output), true);
             BufferedWriter out = new BufferedWriter(fileWriter);) {
            for (Path path: paths) {
                out.write(path.toString() + System.lineSeparator());
            }
            out.close(); // закрываем поток
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set predicate to search by key
     * @return search predicate
     */
    private Predicate<Path> setPredicate() {
        Predicate<Path> result;
        if (filter.equals("-r")) {
            result = new Predicate<Path>() {
                @Override
                public boolean test(Path path) {
                    Pattern pattern = Pattern.compile(searchMask, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(path.getFileName().toString());
                    return matcher.find();
                }
            };
        } else if (filter.equals("-m")) {
            result = p -> p.toFile().getName().contains(searchMask);
        } else {
            result = p -> p.toFile().getName().equalsIgnoreCase(searchMask);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        ArgsParser argsParser = new ArgsParser(args);
        Find find = new Find(argsParser);
        var paths = find.search();
        // Для проверки в консоли
        for (Path path : paths) {
            System.out.println(path.toString() + System.lineSeparator());
        }
        find.writeToFile(paths);
    }
}
