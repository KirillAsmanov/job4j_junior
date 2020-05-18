package inputoutput.search;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.FileVisitResult.*;

/**
 * 4.1. Сканирование файловой системы. [#281971]
 * @since 18.05.2020
 * @author Kirill Asmanov
 */
public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar search.jar ROOT_FOLDER FILE_EXTENSION");
        }
        Path start = Paths.get(args[0]);
        search(start, args[1]).forEach(System.out::println);
    }

    /**
     * Founds files with input extension in directory
     * @param root path to directory
     * @param ext found extension
     * @return list of found files
     * @throws IOException
     */

    public static List<Path> search(Path root, String ext) throws IOException {
        if (!Files.exists(root)) {
            throw new IllegalArgumentException(String.format("Cannot find %s", root.toString()));
        }
        if (!Files.isDirectory(root)) {
            throw new IllegalArgumentException(String.format("Not directory %s", root.toString()));
        }
        List<Path> foundFiles = new ArrayList<>();
        SimpleFileVisitor fileVisitor = new SimpleFileVisitor() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.getFileName().toString().endsWith(ext)) {
                    foundFiles.add(file);
                }
                return CONTINUE;
            }
        };
        Files.walkFileTree(root, fileVisitor);
        return foundFiles;
    }
}
